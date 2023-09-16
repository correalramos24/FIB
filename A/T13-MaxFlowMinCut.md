## Tema 13 : Max-flow Min-cut



### Flow network

* Concepto de red de flujo (o red). La diferencia con un grafo dirigido es que añadimos campos.

* Una red tiene Nodos, aristas, capacidades(pesos de las aristas), vértices de entrada(*source*) y vértice de salida(*sink*).
  $$
  N=(V,E,c,s,t)
  $$
  

* La idea es reprensentar que el *source* o fuente transmite hacia *sink* o sumidero.

Un flujo es una asignación de pesos a las aristas(en los graficos -> Valor de flujo/valor de capacidad.). Debe cumplir las normas de Kirchoff:

* El flujo tiene que ser menor o iugal que la capacidad
* El flujo se conserve (excepto para s y t). Si entran N unidades de flujo deben salir N unidades también

$|f| = \sum f(s,v) = f(V,t)= f(s,t)$

Podem cojer la suma o verlo como el flujo que entra en el sumidero (sink) o sale del (*source*).

* Las aristas saturadas son aquellas que el flujo es igual a su capacidad.

### Problema de max-flow

Dada una red $N$, encontrar el valor con máximo flujo.

### Cortes en redes de flujos

Sobre una red $N=(V,E,c,s,t)$ un $(s,t)-cut$  es una partición de los vértices en dos conjuntos disjuntos(intersección vacía).

* La capacidad del cortes es la suma de los pesos que **salen** de S. Se denota por "c(S,T)"

* Flujo a través del corte : Balance entre los flujos que entran y salen: Sobre los flujos con origen en S y destino en T; restamos origen en T y destino en S (siempre es positivo).
* Tendremos que $f(S,T) \le c(S,T)$

