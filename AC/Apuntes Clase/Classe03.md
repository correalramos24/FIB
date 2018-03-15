## Jerarquia de Memorias

### Conceptos Basicos

​	**Procesador --> Memoria Cache(1MB) --> Memoria Princial(8GB) --> Disco**

La velocidad de los procesadores agravan la poca velocidad de la MP. La MC es 8000 veces mas rapida MP que MC, MP es mas barato que MC.

El 90% de todas las referencias a memoria son realizadas por el 10% del codigo, esto es denominado con la regla del 90/10.

El comportamiento de los programas con instruccciones y aceso a Memoria que se repiten, afavorecen los dos tipos de localidad(Espacial y Temporal), que nos dan a utilidad a la Memoria Cache.

* Localidad Espacial:  "Si accedemos a una posicion, problablemente acederemos a la siguiente".
* Localidad Temporal: "Si accedemos a una posicion, probablemente accederemos otra vez en un futuro a esa posicion".

Para aprovechar estas dos localidades, que son propias de nuestros programas, se utiliza unas jerarquias de Memorias, obteniendo mayor velocidad en los acesos a memoria.Hay que destacar que el bus entre MP i MC es muy ancho, para poder almacenar los datos en formal de bloques.

### Principos de Memoria Cache

El **algoritmo de emplazamiento** es el que nos dice a donde va cada bloque de MP en la MC.

* Emplazamiento Directo

  Cada bloque de MP solo va a un bloque de MC.

* Emplazamiento Completamente Associativo

  Cada linea de MC puede contener cualquier bloque de MP.

* Emplazamiento Associativo por conjuntos

  Se divide la MC en N bloques y cada bloque de MP siempre va a un Conjunto predeterminado.

En la Memoria Cache, se guardara un TAG para saber cual es la @ real en MP. Para identificar la linea de MC, sobre una @ de MP,  se utilizan K bits que permiten identificar la linea de MP, menos en el emplazamiento completamente asociativo. 

Los **algoritmos de reemplazo** son los que nos dicen, en el caso que la MC este llena, que bloque sacaremos:

Hay que destacar que estos algoritmos se implementan en HW, por lo que deben ser muy rapidos y senzillos.

* LRU(Least Recently Used)
* Random
* FIFO(First In First Out == Queue)

Tambien hay una **politica de escritura**(30/51), para realizar las escrituras en la memoria, que responden a <u>Cuando</u> se escribe en MP y a <u>Que hacer en caso de fallo de cache</u>.

* Write Through(escritura a través)
* Copy Back(escritura diferida)
* Write Allocate(migración)
* Write No Allocate(no migración)

Tasa de fallos + 1