## Lenguaje Maquina x86

Instrucciones referencian varios operandos(inmediatos, registros, memoria).

Instrucciones aritmeticas pueden leer/escribir(solo 1 puede estar en memoria).

Las referencias de Memoria se calcula como: MEM[A+Rb+Ri*s] -->A(Rb, Ri, s). Sirven para navegar por tipos de datos.

Las instrucciones tienen <u>longuitud variable</u>.

Hay pocos registros.

### Vision del Programador

EIP: Contador de programa, apunta a la siguiente instruccion a ejecutar.

Registros: Espacio <u>lineal</u> de 2^32^ posiciones de 1 byte.

* [poner diferentes registros que hay].

EEFlags(Codigo Condicion): Informacion sobre comportamiento de las inst.

Memoria:

* Codigo Objecto, Datos Programa, Datos SO. Direccionable a nivel de byte.
* Stack del proceso
* Modos de direccionamiento:
  * Inmediato: $19  (Byte, Word o LongWord)
  * Registro: %eax, %ah, %esi
  * Memoria: D(rB, rI, s) -> M[rB+rI*s + D]

Tipos de datos Basicos: 

* Enteros(1, 2 o 4 Bytes), Reales(Float 4, 8 o 10 Bytes). 


* Se utiliza Little Endian!
* [Rangos ...]

Operaciones Primitivas: Aritmeticologicas, Transferencia de datos y Saltos.

[Instrucciones....]

#### Instrucciones(17/69):

OpCode: 1-2 Bytes

Modo: 1 byte

SIB: 

* Scale(2 bits): 0, 1, 2, 3 son los possibles valores.
* Index(3 bits)
* Base(3 bits)

Desplazamiento: 0, 1,2 o 4 Bytes.

Inmediato: 0, 1, 2 o 4 bytes.

### Traduccion de Sentencias C a Ensamblador

...

### Tipos de datos Estructurados

* Vectores: "Seq de datos iguales almacenados en memoria".

  v[i] -> @v + i * sizeof(tipo).

* Matrices: Vectores almacenado por filas.

  A i, j : @A + (i* NumCols + j) * sizeof(tipo)

* Matrices 3d: Se almacenan tmb en posiciones consecutivas.

  3d i, j, k: @3d + (i * Numero_cara2 * Numero_cara3 + j * Numero_cara3 + k) * sizeof(tipo)

A la hora de acceder a una matriz de 50 por 80, se ejecutan 32267 instruciones, hace falta mejorarlo.