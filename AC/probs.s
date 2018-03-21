19 c) return (*x + aux.i3)

	movl 12(%ebp), %eax	#@*x
	movl (%eax), %eax	#CONTENIDO *X -->%eax
	movl -12(%ebp),%ebx #aux.i3 --> %ebx
	addl %ebx, %eax		#suma
	
19 d) aux.i1 = F(&(*p1).tabla[j], y)

	pushl 16(%ebp)           #push y
	movl -44(%ebp), %ecx     #j -> %ecx
	movl +8(%ebp), %ebx      #(*p1)
	imult %ecx, $38          #j*38
	addl %ebx, %ecx          #@base + .taula[j]
	pushl %ebx
	call F          # resultat per %eax
	movl %eax, -38(%ebp) #aux.i1 = %eax

19 e) i = j * y

	movl -44(%ebp), %eax   # j -> %eax
	imul +16(%ebp), %eax   # eax = j * y
	movl %eax ,%esp        # i = j * y

19 f) aux.c2[i] = aux.c2[23]

	leal 4(%ebp, -38, 1),%eax  #@base aux.c2
	movl %eax, %ecx            #copia @base
	addl $23, %eax             #@base + 23 = aux.c2[23]

	movl %esp, %ebx             #i
	addl %ecx, %ebx             #i+@base
	movl %eax, (%ebx)           #aux.c2[i] = aux.c2[23]

19 g) for (i=0; (i<y) && (i<(*p1).n) ; i=i+5)
		(*p1).tabla[i].i1 = (*p1).tabla[i].i3 + i;
	
	xorl %ecx, %ecx		# i = 0;
for:cmpl %ecx, -4(%ebp),#i<y
	jge fi_for
	movl 12(%ebp),%ebx			#*p1 a %ebx
	movl 3800(%ebx), %edx		#p1->n
	cmp %ecx, %edx			#i < (*p1.n)
	jge fi_for
	imul $38,%ecx,%edx		#%edx <-- 38*i
	movl (%ebx,%ecx),%eax		#@base s2 + i * 38 = p1->tabla[i].i1
	movl %eax, 34(%ebx, %ecx)		#@base s2 + i * 38 + 34 = p1->tabla[i].i3
	addl $5, %ecx
	jmp for
end_for:

19 h) 	if (aux.i1 != y) aux.i3 = i;
		else aux.i3 = j;
if:	movl -46(%ebp), %eax 	#%eax <-- aux.i1
	movl -12(%ebp), %ebx	#aux.i3 -->%ebx
	cmpl %eax, 20(%ebp)		#aux.i1 != y
	jne	else
	movl -8(%ebp),%ebx 		#aux.i3 = i
	jmp fi_if
else:
	movl -4(%ebp),%ebx 		#aux.i3 = j
fi_if:

19 i)	i = 0;
		while (aux.c2[i] != ‘.’) {
			aux.c2[i] = ‘#’;
			i++;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		