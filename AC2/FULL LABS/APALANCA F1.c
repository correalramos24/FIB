#include <stdio.h>
#include <math.h>
#include <string.h>
#define MAX_CHARS 5

int main(){

	char plou, pneumatics, lletra, dig_lletra;
	char resultat [MAX_CHARS], codi_generat [MAX_CHARS];
	int i;
	int nd, v, opcio, dorsal, cursa, dig1, dig2, o1, o2;


	do{
			printf("\n");
			printf("*************************************************\n");
			printf("*                                               *\n");
			printf("*    Benvingut a LS Strategist!                 *\n");
			printf("*                                               *\n");
  		printf("*    Si us plau, tria una opcio del menu:       *\n");
    	printf("*                                               *\n");
  		printf("*    1. Estrategia de pneumatics.               *\n");
      printf("*    2. Obtencio del codi de radio.             *\n");
      printf("*    3. Comunicar−se amb el pilot.              *\n");
      printf("*    4. Simular carrera.                        *\n");
      printf("*    5. Sortir.                                 *\n");
  		printf("*                                               *\n");
    	printf("*************************************************\n");

      printf("Opcio seleccionada: ");
      scanf("%d", &opcio);

			//CONTROL DE ERRORS:
      while(opcio<1 || opcio>5){
            printf("ERROR: Els valors admesos es troben entre l’1 i el 5.\n");
            printf("Si us plau, torneu a introduir la opcio: ");
            scanf("\n%d", &opcio);
      }

			//OPCION1: pneumatic
    	if(opcio == 1){
            do{
				printf("Esta plovent sobre la pista? (S / N): ");
				scanf("\n%c", &plou);
            }while(!(plou == 's' || plou == 'S' || plou == 'n' || plou == 'N'));


            if(plou == 's' || plou == 'S' || plou == 'n' || plou == 'N'){
                printf("Quin nivell de degradacio provoca la pista sobre el pneumatic? (0−4): ");
                scanf("%d", &nd);

               while(nd<0 || nd>4){
                    printf("ERROR: Si us plau, introduiu un valor entre 0 i 4\n");
                    printf("Quin nivell de degradacio provoca la pista sobre el pneumatic? (0−4): ");
                    scanf("%d", &nd);
                }

                do{
                	printf("Com es vol prioritzar la velocitat del pneumatic sobre la durabilitat? (1−5): ");
					scanf("%d", &v);
                }while(v<1 || v>5);

            }

            pneumatics = fabs(v-nd);
            if(plou == 'n' || plou == 'N'){
                if(v < 3){
                    printf("El compost adecuat es el C%d i haureu de realitzar 1 parada a boxes", pneumatics);
                } else {
                    if(v = 3){
                        printf("El compost adecuat es el C%d i haureu de realitzar 2 parades a boxes", pneumatics);
                    } else {
                        if(v > 3){
                            printf("El compost adecuat es el C%d i haureu de realitzar 3 parades a boxes", pneumatics);
                        }
                    }
                }
            }

            if(plou == 's' || plou == 'S'){
                if(pneumatics < 3){
                    if(v < 3){
                        printf("El compost adecuat es el W i haureu de realitzar 1 parada a boxes");
                    } else {
                        if(v = 3){
                            printf("El compost adecuat es el W i haureu de realitzar 2 parades a boxes");
                       } else {
                            if(v > 3){
                                printf("El compost adecuat es el W i haureu de realitzar 3 parades a boxes");
                            }
                        }
                    }
                }

                if(pneumatics >= 3){
                    if(v < 3){
                        printf("El compost adecuat es el I i haureu de realitzar 1 parada a boxes");
                    } else {
                        if(v = 3){
                            printf("El compost adecuat es el I i haureu de realitzar 2 parades a boxes");
                        } else {
                            if(v > 3){
                                printf("El compost adecuat es el I i haureu de realitzar 3 parades a boxes");
                            }
                        }
                    }
                }
            } o1 = 1;
        }
			//OPCIO 2: CODI RADIOS
  	  if(opcio == 2){
				printf("Quin es el nombre de dorsal del pilot amb qui es vol comunicar? ");
     		scanf("%d", &dorsal);

 		 		while(dorsal < 1 || dorsal > 99){
					printf("ERROR: Has introduit un valor invalid.\n");
					printf("Quin es el nombre de dorsal del pilot amb qui es vol comunicar?");
					scanf("%d", &dorsal);
        }
				printf("Quin es el nombre de la cursa en el calendari? ");
				scanf("%d", &cursa);

				while(cursa < 1 || cursa > 21){
       		 printf("ERROR: Has introduit un valor invalid.\n");
       		 printf("Quin es el nombre de la cursa en el calendari? ");
					 scanf("%d", &cursa);
			 	}
				dig1 = dorsal/cursa; //-> char
				printf("%d", dig1);
				char aux[3]; //PONER ARRIBA
				sprintf(&aux, "%d", dig1);
				printf("%s", aux);
				dig2 = dorsal/26; // -> char
 		 		lletra = dorsal % 26; // char
 			 	dig_lletra = 'A' + lletra - 1; // a mayusculas
				strcat(codi_generat, &dig_lletra);
				strcat(codi_generat, &aux);
				strcat(codi_generat, dig2 - '0');
        //TODO: MIRAR CONCATS CORRECTAMENTE!
				if(dorsal > 1 || dorsal <=99){
						printf("CODI GENERAT: %c%d%d",dig_lletra, dig1, dig2);
      	}
				o2 = 1; //ara ja podem anar a l'opcio 3
    	}
			//FI OPCIO 2
			if(opcio == 3) {
				if(o2 != 1 && o1!= 1) {
					printf("ERROR: Encara no s'ha generat cap codi de xifrat per a la radio");

				}else if(o2 = 1) {
					printf("Introduiu el codi de radio: ");
					scanf("%s", resultat);

					while(resultat[0] != dig_lletra && resultat[1] != dig1 && resultat[2] != dig2) { //No se com solucionar si al codi hi ha 4 variables
						printf("ERROR: Aquest codi de radio no existeix.\n");
						printf("Introduiu el codi de radio: ");
						scanf("%s", resultat);

					}
					if(resultat[0] == dig_lletra || resultat[1] == dig1 || resultat[2] == dig2) {
						printf("Codi correcte.");

				}
			}
		}
	} while(opcio <= 5);

	return 0;
}

