# @ vector --> 8[ebp]
# elementos --> 12[ebp]
# i --> -8[ebp]
# res --> -4[ebp]

.text
	.align 4
	.globl OperaVec
	.type	OperaVec, @function
OperaVec:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$16, %esp
	pushl	%ebx
	pushl	%esi
	pushl	%edi
	movl	8(%ebp), %eax
	movl	(%eax), %eax
	movl	%eax, -4(%ebp)
# Aqui has de introducir el codigo

		movl	$1, -8(%ebp)
		movl 	8(%ebp), %eax 			#@vector -> eax
for:	movl	-8(%ebp), %ecx			#i -> ecx
		cmpl	12(%ebp), %ecx			#i < elementos (%ecx)
		jge		fi_for					
		movl	(%eax,%ecx,4), %ebx		#ebx <- vector[i]
if:		cmpl	-4(%ebp), %ebx			#res > vector[i] ?
		jge 	fiif
		movl	%ebx, -4(%ebp)			#res = vector[i]
fiif:	incl 	-8(%ebp)
		jmp for
fi_for:

# El final de la rutina ya esta programado
	movl	-4(%ebp), %eax
	popl	%edi
	popl	%esi
	popl	%ebx
	movl %ebp,%esp
	popl %ebp
	ret
