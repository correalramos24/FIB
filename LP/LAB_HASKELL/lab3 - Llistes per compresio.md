# Llistes per comprehensió

Haskell permet definir llistes per rangs, inclus infinites:

````haskell
[1..10] 	--llista d'1 a 10
[1,3..10]	--llista d'1 a 10 saltant amb la diferència del primer al segon element.
[1..]		--llista infinita desde l'1
````

Mimetitzant la notació de conjunts  matemàtica, podem construir llistes amb la seguent notació:

````haskell
l = [x*x | x <- [1..100], capicua x] --semblant a map i filter
p = [(x, y) | x <- [1..10], y <- [1..10]] --producte cartesià
[q | x <- [10..], let q = x*x, let s = show q, s == reverse s]
````

Es important centrase en l'ordre, podem evitar temps computacional:

````haskell
ternes n = [(x, y, z) | x <- [1..n], y <- [x..n], z <- [y..n], x*x + y*y == z*z]
🐌 temps cubic
ternes n = [(x, y, z) | x <- [1..n],
                        y <- [x..n],
                        let z = floor $ sqrt $ fromIntegral $ x*x + y*y,
                        z <= n,
                        x*x + y*y == z*z]
😄temps cuadratic (nomes)
````

# Avaluació lazy

* Només avalua el que cal; _thunk_ representa un valor d'alló que encara no s'ha avaluat.

* Les expressions es tradueixen a un **graf** (no arbre) que és recorregut per obtenirels elements necessaris. :arrow_right: Indeterminisme en l'execucció :arrow_right: tractar estructures molt grans o "infinites".

  

### Llistes infinites

Existeixen diverses maneres de generar llistes infinites, amb algunes funcions d'ordre superior.