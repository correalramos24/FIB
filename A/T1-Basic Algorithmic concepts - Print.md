# Basic Algorithmic concepts

### Algorithms

> Precise recipe for a precise computational task, where each step of the process must be clear and unambiguous

Is relevant to know the **correctness** and its **efficiency** (computing time, memory, communication cost).

The algorithms complexity is given as the computing T(n) function, with n as the input size. The worst case analysis will be used to know the T(n). It’s interesting to study the behavior of T(n) when tends to infinity.

#### Asymptotic notation

| Symbol                | $L = \lim_{n->+\infin} f(n)/g(n)$ | Intuition |           |
| --------------------- | --------------------------------- | --------- | --------- |
| $f(n) = O(g(n))$      | $L < \infin$                      | $f\leq g$ | upper fit |
| $f(n) = \Omega(g(n))$ | $L > 0$                           | $f\geq g$ | lower fit |
| $f(n) = \theta(g(n))$ | $0 < L < \infin$                  | $f = g$   | exact fit |
| $f(n) = o(g(n))$      | $L = 0$                           | $f< g$    |           |
| $f(n) = \omega(g(n))$ | $L = \infin$                      | $f > g$   |           |

Some useful relations for the asymptotic notation: 

* $f \in \Omega(g) \iff g \in O(f)$
* $\theta(f) = 0(f) \cap \Omega(f)$
* $O(g)\iff g(n) < c * f(n)$ 
* $\Omega(f) \iff g(n) \ge c* f(n)$

### Graphs concepts

A graph is a pair of unsorted **vertexs** and **edges**; with $G= (V,E) \rightarrow |V| = n, |E| = m$. 

We’re talking about a dense graph if $m = \theta(n^2)$ and a sparse graph if $m = o(n^2)$.

* Two classes: undirected (**graphs**) and directed (**digraphs**).
* The degree of $v$ ($d(v)$) is the number of edges which are incident to $v$.
* A graph is **connected** if there is a path between any two vertex. Then $\frac{n(n-1)}2 \ge m \ge n-1$
  * A connected component is a maximal connected sub-graph of G.
  * Every digraph has a dag on its strongly connected components.
* For the **complete** $K_n$ graph, then $m = \frac{n(n-1)}2$
* For a digraph, the connectivity is achieved if there is a directed path between any two vertex. $m \le n*(n-1)$.

There are two principal ways to represent G:

* Adjacent list: For each vertex, a list of his neighbor is given.
* Adjacent matrix: Where $A[i,j] = 1 if (i,j) \in E(G). 0 \text{ otherwise}$

|                        | Adjacent Matrix | Adjacent List     |
| ---------------------- | --------------- | ----------------- |
| Space                  | $\Theta (n^2)$  | $\Theta (n+m)$    |
| Adding a new Edge      | $\Theta (1)$    | $\Theta (1)$      |
| Erase a vertex         | $\Theta (n)$    | $O(m)$            |
| Query for a (u,v) edge | $\Theta (1)$    | $O(n)$            |
| Erase an edge          | Same as query   |                   |
| Explore all neighbors  | $\Theta (n)$    | $\Theta (|d(v)|)$ |

#### Searching in a graph

* Breadth First Search (**BFS**) => Adjacent Matrix $O(|V|^2)$, Adjacent list $O(|V|+|E|)$

  Uses a **queue** to keep the neighbors of a visited vertex. 

  For the current vertex, add all the neighbors to the queue and repeat until all vertex visited or the queue is empty.

* Depth First Search (**DFS**) => Adjacent Matrix $O(|V|^2)$, Adjacent list $O(|V|+|E|)$

  Uses a **stack**.

This two algorithms uses a structure to keep the visited vertex. 

In **weighted graphs**, this algorithms will not necessarily give a correct solution.

#### Tarjan's Find connected components $O(|V|+|E|)$

Use DFS and keep track of the set of vertices visited in each explore call.

###  The classes P and NP 

* A problem belong to the class P if there exists an algorithm that is **polynomial in the worst-case** analysis, for any input.

* A decisional (some property is fulfilled) problem belongs to the NP if the algorithms is non-deterministic in polynomial time.

### Divide-and-conquer strategy

1. Break the problem into smaller subproblems,

2. recursively solve each problem,
3. appropriately combine their answers.

In this case of problems, is common to write the cost with reccurences:

#### Recurrences - Teorema Master

* Recurrencies substractives $T(n) = a* T(n-c) + g(n^k)$ 

  Tenint $g$ com una funció arbitrària i $\in (n^k) | k \ge 0$:


$$
  T(n) \in
  \begin{cases}
  \Theta (n^k), a < 1\\
  \Theta (n^{k+1}), a = 1\\
  \Theta (n^{\frac n c}), a >1\\
  \end{cases}
$$

* Recurrencies divisores $T(n) = a* T(\frac n b) + g(n^k)$

  Tenint $g$ com una funció arbitrària amb$g \in (n^k) | k \ge 0$ ,  $b>1$ i $\alpha =\log_b(a)$

  

$$
T(n) \in
\begin{cases}
\Theta (n^k), \alpha < k\\
\Theta (n^k log n), \alpha = k\\
\Theta (n^\alpha), \alpha > k\\
\end{cases}
$$

* Altres recurrencies