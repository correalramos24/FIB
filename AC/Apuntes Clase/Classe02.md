### Optimizacion codigo para matrizes

* Ver la matriz como un vector y recorrerlo, recortas un bulce (-30%).
* Desenrollar Codigo, instruciones de salto consumen mucho tiempo(-30%).
* Las instrucciones multimedia, pueden mover 128 bytes de golpe, simplifican el desenrollar. [..]

### Instrucciones Multimedia (mmx)

Sirven para hacer operaciones con una cantidad grande de bytes(128).

[tabla de instrucciones]

### Structs

Ensamblador no soporta los datos estrucurados, los programadores deben mantenerlos manualmente.

### Alineaminento de Datos (linux 32 bits)

Para asegurar que los sistemas de cache i paginación funcionan correctamente y para poder hacer acesos a memoria por longword o quadword, los datos deben alinearse. El compilador es el que inserta "espacios en blanco".

Por tanto, "una direccion debe ser múltiplo de k".

* char alineado a 1-byte (@ cualquiera)
* short alineado a 2-bytes (@ ...0)
* int alineado a 4-bytes (@ ...00)
* puntero(4 bytes), double(8 bytes), Long double(12 bytes) alineado a 4 bytes

Las estructuras, debe cumplir la restriccion del elemento maximo(maxima alineacion) que contien sus campos, tambien su @ inicio lo tiene que cumplir. El orden de los campos de un struct pueden hacer que este ocupe mas.



## GESTIO DE SUBRUTINAS

En C-linux 32 bits, los parametros se pasan por la pila, **de derecha a izquierda**.

* Vectores y matrices siempre se pasan por &.
* Los structs se pasan por valor siempre.
* Char(1byte) ocupan 4 bytes y Short(2bytes) ocupan 4 bytes.
* Las variables locales se alinean en la pila como siempre. El tamaño de variables locales debe ser multiplo de 4.
* %ebp, %esp se salvan siempre implicitamente.
* %ebs, %esi, %edi se han de salvar si son modificados.
* %eax, %ecx, %edx se pueden modificar dentro de la subrutina, el llamador debe salvarlos.
* Los resultados se devuelven por %eax.
* La pila siempre debe estar alineado a 4.

### Bloque de activacion - Ejemplo de Subrutina







