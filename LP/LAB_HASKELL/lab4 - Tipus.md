## Tipus a Haskell

Haskell conte els clàssics tipus predefinits simples (Int, Integer, Bool, etc..) i estructurats (Llistes, tuples, funcions). Els identificadors de tipus comencen amb majúscula.

Afegits a aquests tipus tenim:

### Tipus polimòrfics

Una variable de tipus ens dona capacitat de **polimorfisme**; es necessari que la substitució de variables adient.

````haskell
--sigui quin sigui el tipus a
lentght :: [a] -> Int
--estem dient que "a" ha de ser alhora bool i char:
map :: (a -> b) -> [a] -> [b] 
map not ['b', 'c']
````

### Tipus sinònims

La construcció ``type`` permet substituir un tipus complex per un nou nom. Aquests només donen claredat al codi.

````haskell
type Euros = Float
sou :: Persona -> Euros
````

### Tipus enumerats

````haskell
--especifiquem la llista de valors possibles dels objectes:
data Jugada = Pedra | Paper | Tisores
data Operador = Suma | Resta | Producte | Divisio
--Per fer operacions, tiem de patrons
guanya :: Jugada -> Jugada -> Bool
    -- diu si la primera jugada guanya a la segona
guanya Paper Pedra = True
guanya Pedra Tisores = True
guanya Tisores Paper = True
guanya _ _ = False
````

### Tipus algebraics

En aquest cas, defineixen constructors amb **zero o mes tipus de dades associades.** Igualment, es poden desconstruir amb patrons per elaborar funcions.

Es útil aplicar la clàusula ```deriving (Show`)`` per tal que Haskell pugui escriure per l’interpret: 

````haskell
data Forma
    = Rectangle Float Float         -- alçada, amplada
    | Quadrat Float                 -- mida
    | Cercle Float                  -- radi
    | Punt
data Punt = Punt Int Int
    deriving (Show)
data Rectangle = Rectangle Punt Punt
    deriving (Show)
    
area :: Forma -> Float
area (Rectangle amplada alçada) = amplada * alçada
area (Quadrat mida) = area (Rectangle mida mida)
area (Cercle radi) = pi * radi^2
area Punt = 0
````

## Aplicacions de tipus algebraics

Els tipus algebraics es poden definir recursivament. Podem deconstruir via patrons.

* Un arbre binari es buit o be tenim un node (de tipus enter) i un fill esquerra i un dret.

````haskell
data Arbin = Buit | Node Int Arbin Arbin 
	deriving(Show)
	
alçada :: Arbin -> Int
alçada Buit = 0
alçada (Node _ fe fd) = 1 + max (alçada fe) (alçada fd)

````

## Tipus genèrics predefinits

* Lliste genèriques: les llistes de haskell es defineixen com:

  ````haskell
  data Llista a = Buida | a `DavantDe` (Llista a)
  --exactament igual que:
  data [a] = [] | a : [a]
  ````

* Maybe a : Expressa dues posibilitats, o la presència d'un valor o la seva absència.

  ````haskell
  data Maybe a = Just a | Nothing
  ````

  **Aplicacions:** Es fa servir per indicar valors nuls, absencia de resultat o reportar errors.

* Either a b : També opcions de dos valors

  ````haskell
  data Either a b = Left a | Right b
  --usualment el left es un string que reporta l'error.
  secDiv :: Float -> Float -> Either String Float
  secDiv _ 0 = Left "divisió per zero"
  secDiv x y = Right (x / y)
  ````

  **Aplicacions**: Un valor pot ser, alternativament, de dos tipus. Reportar errors.

## Classes de tipus

Semblant al interface de Java (defineix comportament). Son una forma de donar sobrecàrrega i un altre forma de polimorfisme. 

* Amb la comanda :info T podem veure de quines classes és la instància de tipus T.

* Els tipus poden ser instancias implementat la interfície requerida.
* Podem dir que un tipus sigui d'una classe amb la ``deriving(class)``.

Es poden forçar que els tipus genèrics ``a`` siguin d'una determinada classe ``Classe a => definicio``

````haskell
elem :: Eq a => a -> [a] -> Bool
--aqui requereix que el tipus A sigui de la classe EQ.

data Jugada = Pedra | Paper | Tisora --aqui ENCARA NO DÓNA SUPORT A EQ
Pedra `elem` [Paper, Pedra, Paper] -- No instance for (Eq Jugada) arising from elem
-- !el compilador es el que instancia (en aquet cas es l'igualtat estructural)
data Jugada = Pedra | Paper | Tisora
    deriving (Eq)
    
--podem definir cada clase:
data Racional = Racional Int Int
instance Eq Racional where
	(Racional n1 d1) == (Racional n2 d2) = n1*d2 == n2*d1
--la classe Eq te les seguents operacions
class Eq a where
    (==) :: a -> a -> Bool
    (/=) :: a -> a -> Bool
````

| Classe | Definicions necesaries                |                        |
| ------ | ------------------------------------- | ---------------------- |
| Eq     | Només cal definir ==                  |                        |
| Ord    | Cal definir <= o compare              |                        |
| Show   | Per defecte, usant sintàxis haskell   | ``show:: a-> String``  |
| Read   |                                       | ``read:: String -> a`` |
| Num    | Suport d'operadors aritmètics (*,+,-) |                        |

#### Us de la classe Show/Read

````haskell
instance Show Racional where
    show (Racional n d) = (show $ div n m) ++ "/" ++ (show $ div d m)
        where m = gcd n d
        
read "38" --error
(read "38")::Int -- = 38
(read "38")::Float -- = 38.0
````

