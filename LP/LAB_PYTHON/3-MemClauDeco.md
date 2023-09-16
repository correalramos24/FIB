# Python 3: memorització, clausures i decoradors

Alguns elements de Python interessants per poder facilitar el codi.

Molts exemples utilitzen `TicToc` del paquet `ttictoc`. Permet calcular el temps d'execució.

[TOC]

### Memorització amb valors per defecte

````python
def fib(n):
    if n in [0, 1]:
        return n
    return fib(n-1) + fib(n-2)

#facilment es pot aplicar memorització:
def efib (n, mem={0:0, 1:1}):
    if n not in mem:
        mem[n] = efib(n-1) + efib(n-2)
    return mem[n]
````

### Funcions niuades

Es poden definir funcions dintre de funcions(des de Python3); aquestes no son visibles per la resta del codi:

````python
from ttictoc import TicToc
def test(f, n):
    def prec(x):
        return '{:.5f}'.format(x)
    t = TicToc() ## TicToc("name")
    t.tic();
    valor = f(n)
    t.toc();
    print('valor:', valor)
    print('temps(s):', prec(t.elapsed))
````

Podem doncs reescriure el codi anterior:

````python
def efib2(x):
    mem = {0:0, 1:1}       # la memòria
    def mfib(n):
        if n not in mem: 
            mem[n] = mfib(n-1) + mfib(n-2)
        return mem[n]
    return mfib(x)
````

### Clausures

Es tracta d'una funció de *callback*. S'utilitza per "amagar dades" i així evitar variables globals.

````python
def test(n):
    def prec(x):
        return '{:.5f}'.format(x)
    def clausura(f):
        t = TicToc() ## TicToc("name")
        t.tic();
        valor = f(n)
        t.toc();
        print('valor:', valor)
        print('temps(s):', prec(t.elapsed))
    return clausura
test40 = test(40) 	#n=40
test40(efib) 		#f = efib
````

* Les variables n i prec son locals dintre de test i son **capturades** en el moment de crear la funció clausura. L'estat es guarda dins del retorn *clausura* i podrà ser accedit en el moment de la crida.

Les variables dintre funcions niuades poden ser considerades locals o no locals.

````python
def myfunc1():
  x = "John"
  def myfunc2():
    nonlocal x #a la linia 10 no esta aquesta crida
    x = "hello"
  myfunc2() 
  return x

print(myfunc1()) #retorna "hello", x es global
print(myfunc1()) #retorna "John", x no es glogal
````

#### Memorització genèrica

Podem afegir un *callback* per memoritzar valors de $\forall$ funció. Cal però redefinir la funció recursiva per cridar  en les succesives crides recursives primer a memoritza (que cridarà a fib)

````python
def memoritza (f):
    # Això es pot fer perquè els diccionaris són mutables.
    mem = {}       # la memòria
    def f2 (x):
        if x not in mem:
            mem[x] = f(x)
        return mem[x]
    return f2
def fib(n):
    if n in [0, 1]:
        return n
    return fib(n-1) + fib(n-2)

# Com que la funció és recursiva hem de redefinir la funció
test40 = test(40)
fib = memoritza(fib)
test40(fib)
````

### Decoradors

Mitjançant les clausures podem alterar quelcom invocable, afegint algun mètode abans/després de una crida a funció:

````python
def testDec(f):
    def wrapper(*args):
        valor = f(*args)
        print('f(' + str(args[0]) + ') = ' + str(valor))
        return valor
    return wrapper

@testDec
def efib(n, mem={0:0,1:1}):    
    if n not in mem:
        mem[n] = efib2 (n-1) + efib2 (n-2)
    return mem[n]
````

* Es poden utilitzar paràmetres, si la funció testDec tingues paràmetres.

L'anterior memorització genèrica es pot escriure com:

````python
def memoritza (f):
    mem = {}
    def f2 (x):
        if x not in mem:
            mem[x] = f(x)
        return mem[x]
    return f2
@memoritza
def fib(n):
    if n in [0, 1]:
        return n
    return fib(n-1) + fib(n-2)
````

