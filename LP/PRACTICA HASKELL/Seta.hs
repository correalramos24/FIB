module Seta where
import Utils


--Definición de Seta y algunas operaciones utiles.

data Seta = Edible [String] | Poisonous [String]
    deriving(Eq, Ord)

instance Show Seta where
    show (Edible atb) = "Edible : " ++  show atb ++ "\n"
    show (Poisonous atb) = "Poisonous: " ++ show atb ++ "\n"

{-
Entrada: Una seta
Salida: Devuelve si es comestible
-}
isEdible :: Seta -> Bool
isEdible (Edible _) = True
isEdible (Poisonous _) = False

{-
Entrada: Una seta
Salida: Devuelve si es venenosa
-}
isPoisonous :: Seta -> Bool
isPoisonous (Edible _) = False
isPoisonous (Poisonous _) = True

{-
Entrada: Una seta
Salida: Devuelve los atributos de la seta
-}
getAtbs :: Seta -> [String]
getAtbs (Edible atb)    = atb  
getAtbs (Poisonous atb) = atb

{-
Entrada: Una seta, entero n
Salida: Devuelve el atributo n-esimo de la seta 
-}
getAtbN:: Seta -> Integer -> String
getAtbN (Edible atb) n = atb !! fromIntegral n
getAtbN (Poisonous atb) n = atb !! fromIntegral n

{-
Entrada: Una seta, entero n
Salida: Elimina el atributo n-esimo de la seta
-}
delAtbN :: Seta -> Int -> Seta
delAtbN (Edible a) n = Edible (borrarI n a)
delAtbN (Poisonous a) n = Poisonous (borrarI n a)
    
--Definimos las correspondencias entre el ordinal del numero de atributo y su nombre "real"
listAtributeName = [atributName x | x <- [0..21]]          

atributName :: Integer -> String
atributName 0 = "cap-shape"
atributName 1 = "cap-surface"
atributName 2 = "cap-color"
atributName 3 = "bruises?"
atributName 4 = "odor"
atributName 5 = "gill-attachment"
atributName 6 = "gill-spacing"
atributName 7 = "gill-size"
atributName 8 = "gill-color"
atributName 9 = "stalk-shape"
atributName 10 = "stalk-root"
atributName 11 = "stalk-surface-above-ring"
atributName 12 = "stalk-surface-below-ring"
atributName 13 = "stalk-color-above-ring"
atributName 14 = "stalk-color-below-ring"
atributName 15 = "veil-type"
atributName 16 = "veil-color"
atributName 17 = "ring-number"
atributName 18 = "ring-type"
atributName 19 = "spore-print-color"
atributName 20 = "population"
atributName 21 = "habitat"