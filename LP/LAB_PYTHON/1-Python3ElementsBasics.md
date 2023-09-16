# Python3 : elements bàsics

* Tenim paradigmes imperatiu, orientat a objectes i funcional.
* Una gran quantitat de llibreries, a part de *lazyness*, codi molt llegible.

[TOC]

### Entrada/Sortida

Podem printar variables amb `print(string)` i llegir pel terminal amb `input`

### Tipus simples, variables, assignació

Tenim els tipus simples `int`(sense rang),`bool`(True, False), `float` i `complex`.

Declaració de valors implícits, per valor. Les crides `isinstance` i `type` ens permeten veure el tipus d’una variable.

#### Tipus iterables - Strings

El tipus `str` es el tipus dels string. Per tots els objectes iterables tindrem les seguents operacions:

````python
z = 'Llenguatges'
z[2] 👉 'e'										#posició
z[2] = 't'	#error, els str son immutables
z[3:6] 👉 'ngu' 								#subcadena - slice
z[:4] 👉  'Llen'								#prefix
z[8:] 👉 'ges' 									#sufix
z + 'Programació' 👉 'Llenguatges Programació'  #concatenar 
z * 3 👉 d 										#repetir
len(z) 👉 11 									#mida
'ng' in z 👉 True								#pertenencia
'ng' not in 👉 False 
z.find('ng') 👉 3								#cerca i retorna posició
z.count('e') 👉 2								#comptar aparicions
'Hello world!\n'.strip()  👉  'Hello world!'   	#treu el \n
'1,2,3'.split(',') = ['1', '2', '3']			#parteix amb el caracter
','.join([1,2,3]) = '1,2,3'						#inversa

````

#### Altres iterables

* Les `list` son col·leccions heterogènies de diferents valors. Son iterables i tenen mateixes operacions que strings i d’altres addicionals (append, count, pop). Es poden recorre dos o més iterables amb zip.

  ````  python
  def prodEscalar(v, w):
      res = 0
      for x, y in zip(v, w):
          res += x * y
      return res
  ````

* Les `tuple` son com les llistes però només de lectura (immutables).

  ````python
  z = ("hola",5,"llenguatge",6.63,2)
  z = (5,)      # tupla d'un sol element
                # (5) és l'enter 5
  ````

* Els `set` son la representació dels conjunts matemàtics. Tenim les operacions `let, in, not in, <= `(es subconjunt) `>=` (es superconjunt) `|, &, -`(unió, intersecció i diferència).

* Els `dict` son parells clau-valor.

  ````python
  dic = {}                   # diccionari buit
  dic["prim"] = "el primer"  # afegir o actualitzar un element
  del(dic['prim`])           # esborrar-lo 
  dic = {"nom": "albert","num":37899, "dept": "computer science"} 
                             # inicialitzar-lo amb dades
  def suma(d):
      s = 0
      for k in d: #iterem les keys
          s += d[k]
      return s
  suma({'a': 1, 'b': 2})  👉  3
  ````

### Construccions bàsiques

`````python
#if, elif, else:
if x < 0:
    signe = -1
elif x > 0:
    signe = 1
else:
    signe = 0

#expresio if
x = 'parell' if 5 % 2 == 0 else 'senar'

#while
n, i = int(input('n? ')), 1
while i <= 10:
    print(n, 'x', i, '=', n * i)
    i += 1
    
#for -> funciona sobre tipus iterables.
n = int(input('n? '))
for i in range(1, 11):
    print(n, 'x', i, '=', n * i)    
`````

### Definició de funcions

Les funcions a Python3 es declara amb la paraula `def`. Es poden retornar múltiples valors (s’interpreta com una tupla). Es poden assignar valors per defecte.

`````python
def divisio(a, b=2): #valor per defecte b=2, si no es proporciona b
    return a // b, a % b
`````

### Definició de classes i herència

Per definir classes tenim `class <Nom de la clase>:`. 

Les característiques generals son:

````python
class Treballador:
    treCompt = 0                # atribut de classe: 
                                # un únic valor per classe
    __treCompt = 0
    def __init__(self, nom, salari):  # constructor
        self.nom = nom          # definició d'atribut  
        self.salari = salari    # self representa el objecte creat
        Treballador.treCompt += 1
    def getCompt(self):         # definició de mètode
        return Treballador.treCompt
    def getSalari(self):
        return self.salari

````

* Els atributs de classe son únics per tota la classe (no d'instàncies). Els atributs privats tenen la paraula `self` davant.

    ````python
    tre1 = Treballador("Pep", 2000)
    tre2 = Treballador("Joan", 2500)
    tre1.getSalari()  				👉  2000
    tre1.salari  					👉  2000 
    tre1.getCompt() 				👉  2
    Treballador.treCompt  			👉  2
    ````

* Els atributs es poden afegir, eliminar(`del inst.nomAtb`) o modificar (`hasattr(objecte,<nomAtb>)`). `dir` ens retorna la llista dels atributs d'un objecte.

  ````python
  tre1.edat = 8
  tre1.edat  👉  8
  hasattr(tre1, 'edat')  👉  True
  del tre1.edat
  hasattr(tre1, 'edat')  👉  False
  ````

* Per tenim atributs ocults, l'atribut ha de començar per "__"

  ```python
  tre1.__treCompt  ❌
  tre1.tre1._Treballador__treCompt  👉  2
  ```

Podem tenir herència múltiple, les comprovacions `ìssubclass(sub,sup)` i `isinstance(obj, Class)`

```python
class Fill(Treballador):    # pare entre ( )
    def fillMetode(self):
        print(’Cridem al metode del fill’)
tre3 = Fill('Manel', 1000)
tre3.fillMetode()

class C(A, B): # subclasse de A i B
.....
```

