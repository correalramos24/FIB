#### Classe 05

### JERARQUIA DE MEMORIAS: CONCEPTOS AVANZADOS - OPTIMIZACIONES

$$
T_{exe} = N * (CPI_{id} + N_r * m * t_{pf}+CPI_{WR}) * T_c
$$

### Fallos de Cache

* Carga: Asimptota de la f(x), se produce la primera vez que se accede a una posicion de memoria.
* Conflicto: Se producen cuando varios bloques se mapean en el mismo lugar de la Memoria Cache. Mayor fallos por conflicto, a menor associatividad
* Capacidad: Se produce dado que la MC no es infinita, es decir, todas las lineas estan ocupadas y se necesita una linia adiccional. A menor tamaño de cache mayor fallos por capacidad

**POSSIBLES SOLUCIONES BASICAS**

<u>Aumentar el tamaño de bloque</u> (m baja). Reduce los fallos de carga,
pero puede ser contraproducente (m sube).

<u>Aumentar el tamaño de cache</u> (baja m). Reduce los fallos de capacidad (y conflicto),
pero aumenta el tiempo de acceso a la cache (sube tsa) y el consumo (sube W).

<u>Aumentar el grado de asociatividad</u> (baja m). Reduce los fallos de conflicto,
pero aumenta el tiempo de acceso a la cache (sube tsa).

<u>Caches multinivel</u> (baja t pf). L1 pequeña (m sube y tsa baja) y L2 grande (m baja y tsa sube).

Dar más <u>prioridad a las lecturas</u> que a las escrituras (CPI WR baja).
El coste de las escrituras se puede reducir (ocultar) utilizando buffers de escrituras, haciendo que las escrituras no sea bloqueante.

### TECNICAS AVANZADAS

<u>Reducir el coste de un acierto en cache (tsa):</u>

* Caches pequeñas y simples

  Cache directa: Acceso (MDatos y etiquetas), Compara etiqueta y validar línea.

  Cache Associativa por Conjuntos: AccesoCompara etiqueta, validar linea y selecionar via

  Cache Completamente Asociativa: Acceso(MDatos), Compara etiqueta, validar

  linea y Acceso(MDatos).

  **Menos associativa, Mas simple y menor tiempo de acceso.**

  **Menos bits, menos niveles de puertas, menos tiempo de acceso.**

* Predicción de vía 

  Cache con acceso paralelo Etiquetas-Datos: Acceder a todas las etiquetas y a todos los datos.

  Cache secuencial: Leer todas las etiquetas, selecionar la via y acceder a la unica linea que vamos a acceder.

  Cache con way prediction: Leer una etiqueta con prediccion, y ver si es un hit o un miss.

  * La prediccion se hace a traves de una tabla direccionada con unos bits del PC; se aprovecha la localidad de los programas.

* Trace caches

  Se necesita que el compilador divida el codigo en fragmentos, segun las direcciones de salto que hay y hacia donde apuntan.

  La trace cache almacena secuencias de ejecucion de instruciones consecutivass, para explotar la localidad espacial de los programas.

<u>Aumentar el ancho de banda de cache (BW)</u>

* Caches segmentada

  Se puede desacoplar la parte de busqueda de tag de la busqueda de datos, es decir, se puede acceder a un dato con un tag y a la vez buscar el siguiente tag necesario. El Tsa augmenta respecto a la no segmentacio, pero el ancho de banda se dobla, se puede lanzar un nuevo acceso cada 0,4 ns, si segmentacion son 0,7 ns.

  Se puede augmentar el grado de segmentación, con mas registros.

* Caches multi-banco

  ​

* Caches no bloqueantes

  Al producirse un fallo de cache, se detiene la ejecuccion; pero quizas el dato se necesita mucho mas adelanta. El compilador puede modificar el orden de las instrucciones si fuera necesario.

  Si la cache no bloquea al procesador, se puede paralelamente seguir ejecutando instrucciones y por otro lado resolver el fallo de cache.

  Para esto, hace falta una estructura (MissStatusHandlerRegister) para gestionar los fallos pendientes.

<u>Reducir el coste de los fallos (t pf)</u>

* early restart
* merging write buffers

<u>Reducir la tasa de fallos (m)</u>

* optimizaciones del compilador

 Reducir el coste de los fallos (t pf ) y la tasa de fallos (m) vía paralelismo:
pre-búsqueda hardware y pre-búsqueda software.

