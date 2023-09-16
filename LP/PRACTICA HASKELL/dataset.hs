module Dataset where

--Aqui encontramos las funcionalidades necesarias para gestionar la lista de setas, puesto
--que necesitaremos para construir el arbol de decisiones ir selecionando  aquel atributo que maximiza
--la ganancia de información.

import Seta
import Utils 


{-
Funcion para escribir con estilo una listas de setas
-}
showDataset :: [Seta] -> String
showDataset = foldr ((++) . show) ""

{-
Construción de una lista de setas
Entrada: Lista de strings con el formato e/p,atb1,atb2,...,atbN
Salida : Lista de setas correspondiente
-}
startDS :: [String] -> [Seta]
startDS = map parseSeta
  where 
    parseSeta :: String -> Seta
    parseSeta entrada
      | head entrada == 'e' = Edible atributos
      | otherwise = Poisonous atributos
        where 
            atributos = splitString (==',') (tail entrada)

{-
Divide un dataset por sus diferentes atributos
Entrada: Atribuo por el que vamos a dividir, lista de setas 
Salida: [(Valor, [lista con todas las setas de este valor])]
-}
splitDS3 :: Integer -> [Seta] -> [(String, [Seta])]
splitDS3 _ [] = []
splitDS3 n (s:xs) = (getAtbN s n, [setaSinAtbI]) # splitDS3 n xs
  where
    setaSinAtbI = delAtbN s (fromInteger n)


{-
Operador de soporte para recorrer la lista en una sola vuelta.
-}
(#) ::  (String, [Seta]) ->  [(String, [Seta])] -> [(String, [Seta])]
(#) nuevo [] =  [nuevo]
(#) nuevo (x:xs)
  | fst nuevo == fst x = (fst x, snd x ++ snd nuevo) : xs
  | otherwise = x : (nuevo # xs)

--todos los elementos de una lista son edible
allEdible :: [Seta] -> Bool
allEdible = all ((== True) . isEdible)

--todos los elementos de una lista son posionous.
allPoisonous :: [Seta] -> Bool
allPoisonous = all ((== False) . isEdible)

--indica si todas las setas son comestibles o venenosas
allSameDecision :: [Seta] -> Bool
allSameDecision l = all ((== primer) . isEdible) resta
  where
    primer = isEdible $ head l
    resta = tail l

--elimina el atributo de cada seta
dropMissingAtributes :: [Seta] -> [Seta]
dropMissingAtributes = map (\x -> delAtbN x 11)

--Cuenta el numero de setas edible y poisonous que hay en un dataset
countEdiblePoison :: [Seta] -> (Double, Double)
countEdiblePoison = foldl funCount (0.0, 0.0)
  where
    funCount acumulado actual
      | isEdible actual = (fst acumulado + 1, snd acumulado)
      | otherwise = (fst acumulado, snd acumulado + 1)