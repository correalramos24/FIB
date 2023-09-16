# Hello World - Bàsics

#escirure
print('Hello world!')

#llegir
nom = input ('Com et dius?')
print('Hola' + nom + '!')

#Asignació de variables (declaració implicita), Tenim un tipatge dinàmic
x = y = 2

total = x*100 + \
        y*10
#Es poden partir les línies llarges amb \


## Tipus
#Tenim els tipus int (no te rang), float, complex, boolean
#Es pot demanar el tipus amb la comanda type(variable) o isinstance
type('Hola')
isinstance(3, int)

# Strings
"""
Els strings poden ser accedits amb la notació [i] on l'index pot ser un rang inici:final 
o un unic index(desde 0 a n-1)

Els string son immutables, si volem un nou valor cal declar-ne un de nou.

També poden ser concatenats amb l'operador +, multiplicats amb el *

La comanda len(var_string) retorna la llargada.

La construcció in/not in retorna un bool si troba un substring a un string.

find busca la primera ocurrència d'un substring.

count conta els substring a un string

L'operació strip elimina un subtring
L'operació split separa en una llista
"""


###Estructures

## Condicionals
if x < 0:
    signe = -1
elif x > 0:
    signe = 1
else: 
    signe = 0 
#Expressió condicional
x = 'parell' if 5 % 2 == 0 else 'senar'

#Iteracions
#Es poden usar break i continue amb la semàntica habitual.

##Loops
n, i = int(input('n? ')), 1
while i <= 10:
    print(n, 'x', i, '=', n * i)
    i += 1

##For
n = input('n? ')
for i in range(1,11):
    print(n, 'x', i, "=", n * i)

###Funcions

def primer(n):
    for d in range(2, n // 2 + 1): 
        if n % d == 0:
            return False
    return True

#Podem retornar diversos valor
def divisio(a, b):
    return a // b, a % b

x, y = divisio(7,2)

#Valors per defecte
from string import punctuation
def remPunc(s, tl=True):
    rt = ''
    for c in s.lower() if tl else s:
        if c not in punctuation:
            rt = rt + c
    return rt

### Estructures de dades

#Llistes
# poden ser heterogenies
#Son iterables, mateixes operacions que els strings, a mes de
#append, coount, pop
#Recorreguts paral·lels -> zip

z = list() #instanciació explícita
z = []
def prodEscalar(v, w):
    res = 0
    for x, y in zip(v, w):
        res += x * y
    return res

#Tuples
# Elements immutalbes, només lectura

#Set
 

#Diccionaris
#Parells Clau valor
"""
Si  fem una consulta, si no existeix la clua, retorna error 
"""
dic = {}
dic = {"nom": "albert","num":37899, "dept": "computer science"} 
del(dic['prim'])

#Classes
# Es poden definir mètodes i atributs.
class Treballador:
    treCompt = 0  
    salaris = 0             # atribut de classe: 
                            # un únic valor per classe
    def __init__(self):  # constructor
      # self representa el objecte creat
        Treballador.treCompt += 1
    def getCompt(self):         # definició de mètode
        return Treballador.treCompt
    def getSalari(self):
        return getCompt()