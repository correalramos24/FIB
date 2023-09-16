## Interpret de Haskell, metaoperacions i prefixos/sufixos

S'invoca amb ``ghci``. Algunes de les meta operacions disponibles a l'interpret són:

* :load "nom_programa.hs"
* :reload
* :type
* :quit

Els operadors (com +) son infixes, entre parèntesis actuaran com funcions.

Les funcions so (com div) són prefixes per defecte, si es vol usar com infixes s'han d'usar backticks.

````haskell
2 + 3 		--infixe
(+) 2 3 	--prefixe
div 9 4 	--prefixe
9 `div` 4 	--infixe
````

## Haskell: Tipus bàsics

Si un identificador de tipus no comença amb majúscula, llavors es tracta d'un identificador de tipus. Els tipus disponibles són:

| Familia de tipus | Tipus   | Operadors                                            | Peculiaritats                                 |
| ---------------- | ------- | ---------------------------------------------------- | --------------------------------------------- |
| **Booleans**     | Bool    | not, \|\|, &&                                        | Possibles valors, True i False                |
| **Enters**       | Int     | +,-,/, mod, rem, ^ i <, <=, >=, ==, /=               | 64 bits en Ca2.                               |
|                  | Integer | div -> divisio entera                                | Arbitràriament llargs (no overflow)           |
| **Reals**        | Float   | Mateixos que els enters, pero la divisió es / (real) | 32 bits. Es pot usar notació científica(1e-9) |
|                  | Double  |                                                      | 64 bits                                       |
| **Caràcters**    | Char    | Operadors relacionals habituals.                     | S'escriuen entre cometes simples.             |
| **Strings**      | [Char]  | Operadors de llistes.                                | S'escriuen entre cometes dobles (realment :). |

### Conversions de tipus
* Enter a real -> ``fromIntegral``
* Real a enter -> ``round, floor, ceiling``(Amb import Data.Char)
* Carácter a enter -> ``ord``
* Enter a carácter -> ``chr``


````haskell
--Si a es de la classe integral(Int o Integer) retorna bool
even :: Integral a ==> a -> bool
odd  :: Integral a ==> a -> bool
--Si a es ord, retorna el minim o el maxim (de tipus a tmb)
min :: Ord a ==> a -> a -> a
max :: Ord a ==> a -> a -> a
--Altres funcions matemàtiques:
gcd  :: Integral a ==> a -> a -> a
lcm  :: Integral a ==> a -> a -> a
--Si a es num, llavorns donat un a retorna un a
abs  :: Num a => a -> a
sqrt :: Floating a ==> a -> a
log :: Floating a ==> a -> a
exp :: Floating a ==> a -> a
cos :: Floating a ==> a -> a
sin :: Floating a ==> a -> a
````

## Haskell: Funcions

**Funcios "pures", es a dir, retornen resultat calcuats sobre els seus paràmetres.** Un identificador de funció comença amb minúscula.

Sempre s'ha de donar la seva definició amb paràmetres formals. La capçalera es opcional, si no dóna cap Haskell infereix el tipus

````haskell
doble :: Int -> Int --aquesta capçalera pot ser inferida per haskell.
doble x = 2 * 2

--donat dos enters, retornem un enter: 
perimetre :: Int -> Int -> Int
perimetre amplada alçada = doble(amplada+alçada)

factorial :: Integer -> Integer
factorial n = if n == 0 then 1 else n*factorial (n-1)
````

Es poden definir amb **patrons**(mes elegants que if-then-else). Aquets patrons s'avaluen d'adalt abaix.

````haskell
factorial :: Integer -> Integer

factorial 0 = 1
factorial n = n * factorial (n-1)
````

Existeixen variables '**joker**' que indican cualsevol valor:

````haskell
nand :: Bool -> Bool -> Bool

nand True True = False
nand _ _ = True -- la nand de cualsevols altres dos valors es certa:
````

També es poden definir les **guardes**(es comproven d'adalt abaix). Si cap branca es certa, error d'execució.

````haskell
valAbs :: Int -> Int
valAbs n
	| n >= 0 = n
	| otherwise = -n
--otherwise es el mateix que True
````

###  Definicions locals

Es poden definir expresions locals a dins d'una funció. Amb les clàusules ``let-in`` o ``where``:

````haskell
fastExp :: Integer -> Integer -> Integer
fastExp _ 0 = 1
fastExp x n =
			let y = fastExp x n_halved
				n_halved = div n 2
            in 
            	if even n
            	then y * y
            	else y * y * x
            	
fastExp :: Integer -> Integer -> Integer
fastExp _ 0 = 1
fastExp x n 
	| even n = y * y 
	| otherwise = y * y * x
	where 
		y = fastExp x n_halved
		n_halved = div n 2
````

## Haskell: Currying - Currificació

A haskell totes les funcions tenen **un únic paràmetre**. Si son de més d'un paràmetre en realitat es **retorna una nova funció**.

````haskell
prod :: Int -> Int -> Int
prod 3 5
--en realitat la capçalera es una funció que donat un enter retorna una funció que donat un altre enter retorna un enter.
prod :: Int -> (Int -> Int)
(prod 3 ) 5 :: Int
(prod 3) :: (Int -> Int)
````



## Haskell: Tuples

Tipus estructurat heterogeni, el nombre de camps es fix. Hi han predefinides dues operacions per accedir al primer (``fst``) i segon (``snd``) camp de la tupla.

Hi ha una tupla buida, anomenada **unit**, es designa com ``()``.

````haskell
--tupla: (2,'z', False) :: (Int, Char, Bool)
distancia :: (Float, Float) -> (Float, Float) -> Float
distancia (x1,y1) (x2, y2) = sqrt((x1-x2)^2 + (y1-y2)^2)

distancia p1 p2 = sqrt (sqr dx + sqr dy)
	where
		(x1, y1) = p1
		(x2, y2) = p2
		dx = x1 - x2
		dy = y1 - y1
		sqr x = x*x
````

## Haskell: Llistes i funcions de llistes

Tipus estructurat homogeni, es tracta d'una seqüència d'elements.  A haskell les llistes son simplement encadenades.

Les llistes tenen el constructor de llista buida ``[]`` i l'operador afegir per davant ``:``. 

````haskell
[] --lista buida
[3, 9, 27] :: [Int] --llista
3 : 12 : 21 : []
[[87], [1,2,4], []] :: [[Int]] --llista de llistes
[1..10] --llista amb els enters del 1 al 10
[1,3..10] --lista amb enters del 1 al 10 (amb dif 3-1)
````

Habitualment per funcions amb llistes s'utilitzen patrons i les clàusules ``case``, ``where`` i ``let``.

````haskell
suma :: [Int] -> Int
suma [] = 0
suma (x:xs) = x + suma xs

suma llista = 
	case llista of
		[] 		-> 0
		x:xs 	-> x + suma xs
divImod n m
    | n < m      = (0, n)
    | otherwise  = (q + 1, r)
    where (q, r) = divImod (n - m) m
    
primerIsegon llista =
    let primer:segon:resta = llista
    in (primer, segon)    

````

Algunes funcions predefinides i operadors són:

| Nom/Operador                     |                 Descripció                  |
| -------------------------------- | :-----------------------------------------: |
| head :: [a] -> a                 |         Primer element de la llista         |
| last ::[a] -> a                  |         Últim element de la llista          |
| tail :: [a] -> [a]               |         Llista sense el 1º element          |
| init :: [a] -> [a]               |        Llista sense l'últim element         |
| reverse :: [a] -> [a]            |              Llista del revés               |
| length :: [a] -> Int             |              Nombre d'elements              |
| null :: [a] -> Bool              |               ? Llista buida                |
| elem :: Eq a => a -> [a] -> Bool |            ? x esta a la llista             |
| take :: Int -> [a] -> [a]        |     Prefixe de llargada n de la llista      |
| drop :: Int -> [a] -> [a]        |     Llista sense els n primers elements     |
| zip :: [a] -> [b] -> [(a, b)]    | Combina dos llistes en una llista de tuples |
| repeat :: a -> [a]               |             llista infinita d'a             |
| concat :: [[a]] -> [a]           |    llista de llistes a llista d'elements    |
| maximum :: Ord a => [a] -> a     |        element més gran de la llista        |
| minimum :: Ord a => [a] -> a     |       element més petit de la llista        |
| sum :: Num a => [a] -> a         |       suma dels elements de la llista       |
| product :: Num a => [a] -> a     |     producte dels elements de la llista     |
| and :: [Bool] -> Bool            |    Conjunció dels elements de la llista     |
| or :: [Bool] -> Bool             |    Disjunció dels elements de la llista     |
| (!!) :: [a] -> Int -> a          |      Operador d'indexació (inici a 0)       |
| (++) :: [a] -> [a] -> [a]        |          Operador de concatenació           |

