# A introduction

#### Bibliography

* [Algorithm Design Book]([http://www.cs.sjtu.edu.cn/~jiangli/teaching/CS222/files/materials/Algorithm%20Design.pdf](https://meet.google.com/linkredirect?authuser=2&dest=http%3A%2F%2Fwww.cs.sjtu.edu.cn%2F~jiangli%2Fteaching%2FCS222%2Ffiles%2Fmaterials%2FAlgorithm%20Design.pdf))

* [Introduction To Algoriths]([https://pirateproxy.cloud/description.php?id=8078082](https://meet.google.com/linkredirect?authuser=2&dest=https%3A%2F%2Fpirateproxy.cloud%2Fdescription.php%3Fid%3D8078082))

* [Alg]([http://algorithmics.lsi.upc.edu/docs/Dasgupta-Papadimitriou-Vazirani.pdf](https://meet.google.com/linkredirect?authuser=2&dest=http%3A%2F%2Falgorithmics.lsi.upc.edu%2Fdocs%2FDasgupta-Papadimitriou-Vazirani.pdf))

# Basic Algorithmic concepts

[TOC]

### Algorithms

> Precise recipe for a precise computational task, where each step of the process must be clear and unambiguos

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

### Graphs concepts

### The classes P and NP 

