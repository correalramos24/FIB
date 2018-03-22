 .text
	.align 4
	.globl Buscar
	.type Buscar,@function
Buscar:
		pushl %ebp;
		movl %esp, %ebp;	#edinamico
		addl $-16, %esp;	#locales
		movl $-1, -4(%ebp);	#trobat = -1
		movl $0, -8(%ebp);	#low = 0
		movl -8(%ebp),%eax; #mid = low;
		movl %eax, -16(%ebp);
		movl 24(%ebp), %eax;	#N
		addl $-1, %eax;			#N-1
		movl %eax, -12(%ebp);	#high = n-1
While:	movl -8(%ebp), %eax;	#eax = low
		cmpl -12(%ebp),%eax;	#low - high
		jg	fi_while
		addl $-28, %esp; 		#sitio para parametros
		pushl 8(%ebp);			#push de &S1 v[]
		pushl 12(%ebp, $8);		#push de X.m
		pushl 12(%ebp, $4);		#push de X.k
		pushl 12(%ebp);			#push de X.c con alineado
		pushl -16(%ebp);		#push de &mid
		pushl -12(%ebp);		#push de &high
		pushl -8(%ebp);			#push de &low					
		call BuscarElemento;
		addl $28, %esp;			#borrar variables de llamada
		movl %eax, -4(%ebp);	#trobat = return 
		cmp -4(%ebp), $0;		#0 - trobat
		jl fi_while;			#break
		jmp while
fi_while:
		movl -4(%ebp), %eax;	#ret por %eax
		addl $16, %esp;			#locales
		popl %ebp;		
		ret;


	

