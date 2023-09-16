# Python 3: lazyness i part funcional

* Per poder tenir el paradigma funcional, que en part està fonamentat en els Iteradors i generadors.

[TOC]

### Iteradors i generadors

A Python tenim un protocol per poder tenir tipus iterables. Es defineix amb els mètodes ``__iter__`` i ``next`` i llençant l'excepció ``StopIteration`` en acabar.

`````python
s = 'abc'
it = iter(s) #agafem l'iterador de la variable.
while True:
    try:
        print(it.__next__())
    except StopIteration:
        break
`````

Els generadors permeten fer avaluació *lazy* en python3. Es fa amb la crida a ``yield`` que para l'execució fins a la següent invocació ``next``:

`````python
def fib(n):
    a = 0
    yield a
    b = 1
    while True:
        if b <= n:
            yield b
            a, b = b, a + b
        else:
            raise StopIteration

## amb list comprehension:
[x for x in fib(25)]
`````

* Els generadors poden ser infinits, treien l' ``StopIteration``

* Les classes també poden ser generadors.

### Funcions anònimes i d'ordre superior

Les funcions son de tipus ``<class function>``.

`````python
lambda par: expressió
doble = lambda x: 2*x
doble(3) 👉  6
type(doble)  👉  <class 'function'>
`````

Tenim les següents funcions d'ordre superior:

* map(fun, iterable) : Aplica la funció a cada element

* filter(fun, iterable) : Retorna el subiterable amb els elements que fan cert la fun.

* reduce(funcio, iterable [, val_inicial]) : (a.k.a. fold) desplega una funció per l'esquerrra.

  ````python
  from functools import reduce
  reduce(lambda acc,y: acc+y, [3,6,8,1])  👉  18
  reduce(lambda acc,y: acc+y, [3,6,8,1], 0)  👉  18
  ````

### Llistes per comprensió

``[expressió for variable in iterable if expressió]``

`````python
[x ** 2 for x in range(4)]  👉  [0, 1, 4, 9]
[x for x in [0, 1, 4, 9] if x % 2 == 0]  👉  [0, 4]
[(x, y) for x in [1, 2] for y in 'ab']
👉  [(1, 'a'), (1, 'b'), (2, 'a'), (2, 'b')]

#amb conjunts
{x for x in range(4) if x % 2 == 0} 👉  {0, 2}

#amb diccionaris
{x: x % 2 == 0 for x in range(4)} 
👉  {0: True, 1: False, 2: True, 3: False}

#amb generadors:
from itertools import count      # count és un generador infinit
g = (x**2 for x in count(1))     # g és un generador
                                 # dels quadrats dels naturals
`````

### Mòduls interesants

* operator: La llibreria *operator* conté tots els operadors estàndard en forma de funcions, per a ser usades en funcions d'ordre superior

  ````python
  from operator import mul
  from functools import reduce
  factorial = lambda n: reduce(mul, range(1, n+1))
  factorial(5)  👉  120
  ````

* itertools: La llibreria conté mes funcions relacionades amb iterar.

  ````python
  from operator import mul
  from itertools import accumulate
  factorials = lambda n: accumulate(range(1, n + 1), mul)
  [x for x in factorials(5)]  👉  [1, 2, 6, 24, 120]
  ````

  [Referencia itertools](https://docs.python.org/3.7/library/itertools.html)

  

  

  

  