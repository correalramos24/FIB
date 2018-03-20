### Rendimiento Cache - Tasa de Falos/Aciertos

$$
h = aciretos/referencias
$$

$$
m = fallos/referencias = 1-h
$$

* Depende del tamaño cache, Tamaño bloque, Algoritmo Emp y Rem y el programa.

**Programa:** Depende del programa, aleatoriamente cambia la tasa de fallos.

**Tamaño cache:** <u>Aumentar el tamaño de cache</u>(de datos o de instrucciones) provoca que la tasa de fallos disminuya.

**Tamaño del Bloque:** Depende del tamaño de la cache tambien; 

* Con cache pequeña y tamaño de bloque alto ==> Muchos fallos (si hay poca localidad)
* Con cache intermedia, bloque grande van mejor
* Con cache grande y tamaño de bloque alto ==> Pocos fallos (con cache )

No hay un tamaño de bloque bueno, una vez elegido el tamaño de la cache luego hay que elegir el tamaño de bloque, un bloque muy grande puede provocar muchos fallos(41/51).

**Associatividad:** Siempre, <u>a mayor grado de associatividad</u> (vias) mejor; se nota mas en caches pequeñas de bloques.

### Rendimiento - Tiempo de Servicio en Acierto (TSA)

Depende del tamaño de Cache y de los algoritmos de Emplazamiento y Reemplazo, se estudia porque el acceso a cache esta en el camino critico de un procesador y puede modificar el tiempo de ciclo de este.

**Associatividad:** A mayor grado de associatividad(vias), peor TSA.

**Tamaño Cache:** A mayor tamaño, pero TSA.

### Rendimiento - Tiempo medio de acceso (TMA)

* Coste de acceso en fallo = tsa + tpf(penalty por fallo)

$$
TMA = h*tsa + m*tsf  = tsa + m*tpf
$$

Solo aplicable en casos de lectura! no se tiene en cuenta politicas de escritura.

### Rendimiento - Tiempo Ejecucción de un Programa

$$
Tejec = N*CPI*Tc
$$

$$
CPImem = nr * (Tma -Tsa) = nr * m * tpf(siendo solo lectura)
$$

siendo nr: numero medio de refs por instruccion.

## Tema 3 Memoria Virtual

Se crea una distincion en todo el sistema(desde Procesador hasta Disco), donde existen unas direcciones lógicas para cada programa, haciendo los programas reubicables. Se necesita una traducción de direcciones y un sitio donde guardar la correspndencia entre @virtual y @fisica (tabla de páginas completamente associativo).







