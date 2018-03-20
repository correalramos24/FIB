
19 d)

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

19 g)
