## Selección

> El problema de selección, sobre una lista A no ordenada trata de encontrar el i-ésimo elemento mas pequeño de A.

Sobre este problema, con $i \in \Z, 1 \le i \le n$ tenemos:
$$
i = 1 \rightarrow Minimo(A) \\
i = n \rightarrow Maximo(A) \\
i = floor (\frac {n+1} 2)\\
$$
Ordenar A tarda $O(n*lg n)$. La selección hace que el tiempo se reduzca hasta el tiempo lineal.

Crearemos un algoritmo determinista para este problema de selección:

1. Algoritmo para encontrar un buen elemento (X) para hacer _split_.
2. Usar (X) para determinar donde esta el i-ésimo elemento (respecto x).

3. Recursividad

### Algoritmo determinista

Primeramente hay que definir el elemento para partir la lista; hay varias maneras de encontrar este elemento _splitting_. Una posible implementación:

* Dividir en subgrupos de 5 (arbitrario) elementos, ordenamos para buscar la mediana (Coste $\Theta \lceil \frac n 5\rceil$). Entonces, el elemento _split_ (X) sera la mediana de los elementos medios de los subgrupos.

* X es el pivote, ordenamos los subgrupos respecto al elemento X.

### Coste del algoritmo