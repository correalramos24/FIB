#low -16(%ebp)
#high -12(%ebp)
#mid -8(%ebp)
#trobat -4(%ebp)
#&v +8(%ebp)
#X +12(%ebp)
#N +24(%ebp)

 .text
	.align 4
	.globl Buscar
	.type Buscar,@function
Buscar:
		pushl %ebp
		movl %esp, %ebp	#edinamico

		addl $-16, %esp	#locales
		movl $-1, -4(%ebp)	#trobat = -1
		movl $0, -8(%ebp)	#low = 0

		movl -8(%ebp),%eax 	
		movl %eax, -16(%ebp)	#mid = low;

		movl 24(%ebp), %eax	#N
		decl %eax		#N-1
		movl %eax, -12(%ebp)	#high = n-1

while:		movl -16(%ebp), %eax	#low
		cmpl -12(%ebp), %eax	#high, low
		jg	fi_while
			
		pushl 8(%ebp);		#push de &S1 v[]
		pushl 20(%ebp)		#push de X.m
		pushl 16(%ebp)		#push de X.k
		pushl 12(%ebp)		#push de X.c con alineado

		leal -8(%ebp), %eax
		pushl %eax;		#push de &mid
		leal -12(%ebp), %eax
		pushl %eax;		#push de &high
		leal -16(%ebp), %eax
		pushl %eax		#push de &low					

		call BuscarElemento

		addl $28, %esp		#borrar variables de llamada

		movl %eax, -4(%ebp)		#trobat = return 
		cmpl $0, -4(%ebp)		#0 - trobat
		jg fi_while			#break
		jmp while
fi_while:
		movl -4(%ebp), %eax		#ret por %eax
		movl %ebp, %esp
		popl %ebp		
		ret

## Errores:
#	1. No apilar las direcciones al hacer la llamada a BuscarElemento.
#	2. Reservar espacio agrandando la pila sumando directamente.
#	3. Cojer mal una variable local al hacer la comparacion del while.
#	4. Mala condicion al traducir la linia del break.

