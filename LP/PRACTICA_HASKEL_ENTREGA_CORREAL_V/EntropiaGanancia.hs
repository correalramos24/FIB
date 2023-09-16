module EntropiaGanancia where

import Seta
import Dataset
import Utils

--Funcionalidades para generar el heurístico en la generacion del arbol.


{-
Dado una lista de setas, devolvemos el numero del atributo que mejor dividi el dataset
Entrada : Lista de setas
Salida : numero de orden que mejor divide la lista de setas
-}
mejorGanancia :: [Seta] -> Integer
mejorGanancia ds = myMax (estudiarGanancias ds) 


{-
Dada una lista de setas obtenemos el estadistico que reprensenta la ganancia de información para ese atributo.

Entrada: Lista de seta
Salida: Lista de ganancias 
-}
estudiarGanancias :: [Seta] -> [Double]
estudiarGanancias dataSet  = [estudiarGanancias' atributoI | atributoI <- [0..numAtributos-1]]
  where
    entropiaDataSet = calculateEntropy dataSet
    numAtributos = toInteger $ length $ getAtbs $ head dataSet 
    estudiarGanancias' atributoI = gananciaInformacio (toInteger $ length dataSet) entropiaDataSet (entropiaAtributoI atributoI)
    entropiaAtributoI atributoI = entropyAtb (splitDS3 atributoI dataSet)

{-
Calcula la entropia de una lista de setas
Entrada: Lista de setas
Salida: Entropia de la lista
-}
calculateEntropy :: [Seta] -> Double 
calculateEntropy d
    | nP == 0 && nE == 0 = 0
    | nP == 0 = - (pEdible * logBase 2 pEdible)
    | nE == 0 = - (pPoison * logBase 2 pPoison)
    | otherwise = -(pEdible * logBase 2 pEdible) - (pPoison * logBase 2 pPoison)
    where
        pEdible = (nE / totalDS) :: Double
        pPoison = (nP / totalDS) :: Double
        (nE, nP) = countEdiblePoison d
        totalDS = fromIntegral $ length d ::Double

{-
Dada una lista de atributos (con todas las setas associadas a ese valor), obtendremos
para cada valor del atributo su entropia
Entrada: Lista asociativa de valores y setas con este valor.
Salida: Lista asociativa de cada valor con su entropia
-}
entropyAtb :: [(String, [Seta])] -> [(String, Double)]
entropyAtb [] = []
entropyAtb l = map (\x -> (fst x, entropia (snd x))) l
  where
    entropia lista 
      | nP lista  == 0 && nE lista == 0 = 0
      | nP lista == 0 = - (propE lista * logBase 2 (propE lista))
      | nE lista == 0 = - (propL lista * logBase 2 (propL lista))
      | otherwise = -((propE lista) * logBase 2 (propE lista)) - ((propL lista) * logBase 2 (propL lista))
    propE l = nE l/ fromIntegral (length l)
    propL l = nP l/  fromIntegral (length l)
    nE l = fst (vals l)
    nP l = snd (vals l)
    vals v = countEdiblePoison v

{-
Dado un atributo 
Entrada:
Salida:
-}
ponderacionAtb :: Integer ->[(String, [Seta])] -> [(String, Double)]
ponderacionAtb n = map (\x -> (fst x, proporcion (snd x)))
  where
    proporcion valor = fromIntegral (length valor) / fromIntegral n


{-
Calculamos la ganancia del dataset 
Entrada: tamaño dataset, entropia dataset, lista entropiasAtributo
Salida: Ganancia de informacion para el atributo de la lista de entropias
-}
gananciaInformacio :: Integer -> Double -> [(String, Double)] -> Double
gananciaInformacio tamDS entropoiaDS entropiasAtb = entropoiaDS - sumaPonderada
  where
    sumaPonderada =  foldl (\acu value -> acu + (snd value / fromIntegral tamDS)) 0.0 entropiasAtb

