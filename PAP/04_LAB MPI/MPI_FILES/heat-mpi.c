#include <stdio.h>
#include <stdlib.h>
#include "heat.h"
#include <mpi.h>



/*
 *
 *
 *
 */
void usage( char *s ){
    fprintf(stderr, "Usage: %s <input file> [result file]\n\n", s);
}

int main( int argc, char *argv[] ){
    int columns, rows;
    int iter, maxiter;
    double residual=0.0;

    int myid, numprocs, len;
    MPI_Status status;
    char hostname[MPI_MAX_PROCESSOR_NAME];

    MPI_Init(&argc, &argv);
    MPI_Comm_size(MPI_COMM_WORLD, &numprocs);
    MPI_Comm_rank(MPI_COMM_WORLD, &myid);
    MPI_Get_processor_name(hostname, &len);

    if (myid == 0) {
        printf("I am the master running on %s, distributing work to %d additional workers ...\n", hostname, numprocs-1);

        // Input and output files
        FILE *infile, *resfile;
        char *resfilename;

        // algorithmic parameters
        algoparam_t param;

        double runtime, flop;

        // check arguments
        if( argc < 2 ) {
            usage( argv[0] );
            return 1;
        }

        // check input file
        if( !(infile=fopen(argv[1], "r")) ) {
            fprintf(stderr, "\nError: Cannot open \"%s\" for reading.\n\n", argv[1]);
            usage(argv[0]);
            return 1;
        }

        // check result file
        resfilename= (argc>=3) ? argv[2]:"heat.ppm";

        if( !(resfile=fopen(resfilename, "w")) ) {
            fprintf(stderr, "\nError: Cannot open \"%s\" for writing.\n\n", resfilename);
            usage(argv[0]);
            return 1;
        }

        // check input
        if( !read_input(infile, &param) ) {
            fprintf(stderr, "\nError: Error parsing input file.\n\n");
            usage(argv[0]);
            return 1;
        }
        print_params(&param);

        if( !initialize(&param) ) {
            fprintf(stderr, "Error in Solver initialization.\n\n");
            usage(argv[0]);
            return 1;
        }

        maxiter = param.maxiter;
        // full size (param.resolution are only the inner points)
        columns = param.resolution + 2;
        rows = columns;
	int rowsToSolve = (rows / numprocs) + 2;
	int size = rowsToSolve*columns;
	printf("The chunks have %d elements\n", rowsToSolve*columns);
        // starting time
        runtime = wtime();

        // send to workers the necessary information to perform computation
        for (int i=0; i<numprocs; i++) {
            if (i>0) {
		//Send side size:
                MPI_Send(&maxiter, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
                MPI_Send(&columns, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
		MPI_Send(&rowsToSolve, 1, MPI_INT, i, 0, MPI_COMM_WORLD);
		//Each worker needs [Base, Base+itemsPerChunk]
		int index = columns * rowsToSolve * i;
                MPI_Send(&param.u[index],size, MPI_DOUBLE, i, 0, MPI_COMM_WORLD);
                MPI_Send(&param.uhelp[index],size , MPI_DOUBLE, i, 0, MPI_COMM_WORLD);
		printf("Worker %i recive the[%i]\n", i, index);
            }

        }

	//master resolution: the lower boundary needs to be nofied to proces 1
	int lastCol = (columns*rowsToSolve)-columns;
	printf("\t===> Master notify proc 1 the %d row\n", lastCol);
        iter = 0;
        while(1) {

		residual = relax_jacobi(param.u, param.uhelp, rowsToSolve, columns);

		double * tmp = param.u;
		param.u = param.uhelp;
		param.uhelp = tmp;

		MPI_Send(&param.u[lastCol], columns, MPI_DOUBLE, 1, 0, MPI_COMM_WORLD);
		MPI_Send(&param.uhelp[lastCol],columns , MPI_DOUBLE, 1, 0, MPI_COMM_WORLD);

		iter++;
		// solution good enough ?
		// if (residual < 0.00005) break;

		// max. iteration reached ? (no limit with maxiter=0)
		if (maxiter>0 && iter>=maxiter) break;
        }

	printf("Master end his loop.\n");


	for (int i=0; i<numprocs; i++) {
            if (i > 0) {
		int index = columns * rowsToSolve * i;
		printf("MASTER: reciving from %d to u[%d]. Size = %i\n", i, index, size);
                MPI_Recv(&param.u[index], size-1, MPI_DOUBLE, i, 0, MPI_COMM_WORLD, &status);
            }
        }

        // stopping time
        runtime = wtime() - runtime;

        // Flop count after iter iterations
        flop = iter * 11.0 * param.resolution * param.resolution;

        fprintf(stdout, "Time: %04.3f \n", runtime);
        fprintf(stdout, "Flops and Flops per second: (%3.3f GFlop => %6.2f MFlop/s)\n",
                flop/1000000000.0,
                flop/runtime/1000000);
        fprintf(stdout, "Convergence to residual=%f: %d iterations\n", residual, iter);


        if (param.resolution < 1024) {
            coarsen( param.u, rows, columns, param.uvis, param.visres+2, param.visres+2 );
            write_image( resfile, param.uvis, param.visres+2, param.visres+2 );
        }


	printf("Master: All done\n");
	finalize( &param );

        MPI_Finalize();
        return 0;
    }
    else {
        printf("I am worker %d on %s and ready to receive work from master ...\n", myid, hostname);

        // receive information from master to perform computation locally
        MPI_Recv(&maxiter, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
        MPI_Recv(&columns, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);
	MPI_Recv(&rows, 1, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

	fprintf(stdout, "Worker %d: have %i columns and %i rows\n" ,myid, columns, rows);

        // allocate memory for worker
        double * u     = calloc( sizeof(double),rows*columns );
        double * uhelp = calloc( sizeof(double),rows*columns );

        if( !u || !uhelp ) {
            fprintf(stderr, "Error: Cannot allocate memory\n");
            return 0;
        }

        // fill initial values for matrix with values received from master
        MPI_Recv(&u[0], rows*columns, MPI_DOUBLE, 0, 0, MPI_COMM_WORLD, &status);
        MPI_Recv(&uhelp[0], rows*columns, MPI_DOUBLE, 0, 0, MPI_COMM_WORLD, &status);

        iter = 0;
	int lastCol = columns *(rows-1);
	printf("\t====>Between proces, send the %d column\n", lastCol);
	//process n recive his upper boundary from n-1 and send the lower boundary to n+1
        while(1) {

		MPI_Recv(&u[0], columns, MPI_DOUBLE, (myid - 1), 0, MPI_COMM_WORLD, &status);
		MPI_Recv(&uhelp[0], columns, MPI_DOUBLE, (myid - 1), 0, MPI_COMM_WORLD, &status);

		residual = relax_jacobi(u, uhelp, rows, columns);

		double * tmp = u;
		u = uhelp;
		uhelp = tmp;

		if(myid < numprocs - 1){
			MPI_Send(&u[rows-1],columns, MPI_DOUBLE, (myid + 1), 0, MPI_COMM_WORLD);
			MPI_Send(&uhelp[rows-1],columns, MPI_DOUBLE, (myid + 1), 0, MPI_COMM_WORLD);
		}


		iter++;

		// solution good enough ?
		// if (residual < 0.00005) break;

		// max. iteration reached ? (no limit with maxiter=0)
		if (maxiter>0 && iter>=maxiter) break;
        }

        fprintf(stdout, "Process %d finished computing after %d iterations with residual value = %f\n", myid, iter, residual);
	printf("Process %d sending the results to master, total of %i\n", myid, rows*columns);

	MPI_Send(&u[0], (rows*columns)-1, MPI_DOUBLE, 0,0, MPI_COMM_WORLD);

 	printf("Process %d : MPI_finalize()\n", myid);
        MPI_Finalize();
        return 0;
    }
}


