import Debug.Trace

data Bolet = Bolet Char [String]        
    deriving (Show)

data Tree = Empty | Node String [Tree] | NodeEdible String | NodePoison String 
    deriving (Show)
-- x=  Node "atb1" ["valor1" -> E, "valor2" -> P]
-- Node "atb2" [...,Valor2 -> x]
listAtributeName :: [String]
listAtributeName = [nomAtribut x | x <- [0..21]]

get1 :: (a, b, c) -> a
get1 (x,_,_) = x
get2 :: (a, b, c) -> b
get2 (_,x,_) = x
get3 :: (a, b, c) -> c
get3 (_,_,x) = x

-- Esborra fila de matriu
-- Pre : Enter de la fila/columna a esborrar i la fila/columna
-- Post : S'ha eliminat la fila/columna del paràmetre d'entrada
borrarI :: Int -> [a] -> [a]
borrarI n a = take n a ++ drop (n+1) a

-- Retorna el nom de l'atribut
-- Pre : Enter amb valors entre 0 < 22
-- Post : Retorna el nom associat al enter

nomAtribut :: Integer -> String
nomAtribut 0 = "cap-shape"
nomAtribut 1 = "cap-surface"
nomAtribut 2 = "cap-color"
nomAtribut 3 = "bruises?"
nomAtribut 4 = "odor"
nomAtribut 5 = "gill-attachment"
nomAtribut 6 = "gill-spacing"
nomAtribut 7 = "gill-size"
nomAtribut 8 = "gill-color"
nomAtribut 9 = "stalk-shape"
nomAtribut 10 = "stalk-root"
nomAtribut 11 = "stalk-surface-above-ring"
nomAtribut 12 = "stalk-surface-below-ring"
nomAtribut 13 = "stalk-color-above-ring"
nomAtribut 14 = "stalk-color-below-ring"
nomAtribut 15 = "veil-type"
nomAtribut 16 = "veil-color"
nomAtribut 17 = "ring-number"
nomAtribut 18 = "ring-type"
nomAtribut 19 = "spore-print-color"
nomAtribut 20 = "population"
nomAtribut 21 = "habitat"

-- Obtenir atributs
-- Pre : Bolet
-- Post : Retorna una llista amb tots els atributs del bolet

veureAtbs :: Bolet -> [String]
veureAtbs (Bolet _ l) = l

-- Obtenir atribut especific
-- Pre : Bolet amb atributs >= Index
-- Post : Retorna el valor de la columna index

veureAtbN :: Bolet -> Int -> String
veureAtbN (Bolet _ l) i
    |null l = error "l. buida"
    |length l < i = error "!!"
    |otherwise =  l !! i

-- Obtenir Edible / Poisonous
-- Pre : Bolet
-- Post : Retorna True si poisonous , altrament false

esVerinos :: Bolet -> Bool
esVerinos (Bolet c _)= c == 'p'


-- Esborrar atributs
-- Pre : Bolet amb atributs >= index
-- Post : Retorna el bolet sense l'atribut indicat per l'index

borrarAtb :: Bolet -> Int -> Bolet
borrarAtb (Bolet c l) i = Bolet c (borrarI i l)


splitString     ::  String -> [String]
splitString s =  case dropWhile (==',') s of
                      "" -> []
                      s' -> w : splitString s''
                            where (w, s'') = break (==',') s'

-- Parseja bolets
-- Pre : Llista d'strings
-- Post : Llista de bolets on cada bolet conté atribut ["e" o "p"] i tots els seus atributs

parseBolets :: [String] -> [Bolet]
parseBolets entrada = map mapFun entrada
    where
        mapFun :: String -> Bolet
        mapFun s = Bolet (head s) (splitString $ tail s)



-- Conta tots els edibles, poisonus
-- Pre: L'entrada te el format ["p, e," ...]
-- Post: Retorna una tupla on el primer valor indica els P i el segon els E

countTotalEP :: [Bolet] -> (Integer, Integer)
countTotalEP matrix = foldl funCountEP (0,0) matrix
    where
        funCountEP acu act
            | esVerinos act = ((fst $ acu)+1, snd acu)
            | otherwise = (fst acu, (snd acu)+1)


--Funcio per recorre columnes
--Pre: Matriu
--Post retorna la columna
getColum :: [Bolet] -> Integer -> [String]
getColum l atribut = foldl (\acu actual-> veureAtbN actual (fromIntegral atribut): acu) [] l
    

-- Funcio per obtenir la columna d'edibles poisonous 
-- Pre : Matriu de bolets
-- Post : Retorna una llista de Strings de la columna edibles poisonous

getColumEP :: [Bolet] -> [String]
getColumEP l = foldl funFold [] l
    where
        funFold acu actual 
            | esVerinos actual = acu ++ ["p"]
            | otherwise = acu ++ ["e"]

-- #################### Millor atribut:


-- Pre : Entra llista d'strings
-- Post : Retorna una llista amb els elements diferents que hi apareixen

diferentTypes :: [String] -> [String]
diferentTypes [] = []
diferentTypes (x:xs) = x : diferentTypes (filter(/= x) xs)

-- Retorna el total de aparicions d'E i P per un atribut lletra
-- (#E, #P, #total d'aquet atribut)
buscaAparacions:: [(String, String)] -> String -> (Integer,Integer)
buscaAparacions llista lletra = foldl funfold (0,0) llista
    where 
        funfold:: (Integer,Integer) -> (String,String) -> (Integer,Integer)
        funfold ant act
            | snd act == lletra && fst act == "e" = ( fst ant + 1 , snd ant )
            | snd act == lletra && fst act == "p" = ( fst ant , snd ant + 1)
            | otherwise = ant


-- Pre : Entra matriu de bolets
-- Post : Retorna l'index de la columna amb la màxima guanyança
maxGain3:: [Bolet] -> Integer
maxGain3 matrix = result
    where 
        numAtributs = toInteger $ length $ veureAtbs $ head matrix
        colums = [getColum matrix y | y <- [0..(numAtributs-1)]]
        columnaEP = getColumEP matrix
        guanyances = map (\x -> (gainPW (zip columnaEP x))/ (fromIntegral $ length matrix)) colums

        result = maxColumn guanyances +1
      
--Entrem fila EP y columna 
--primera conv => ([String], [String]) -> Valor -> Suma E, Suma == bucaAparicions 

-- mirar tots els tipus que hi han i per cada un sumu quans te P i quans E i agafo el max. Finalment divideixo per T
gainPW :: [(String, String)] -> Double
gainPW a = sumaMillors
    where
        columnaAtribut = foldl (\acumulat actual -> acumulat ++ [snd actual]) [] a
        valorsDiferents = diferentTypes columnaAtribut

        valorsEP = map (buscaAparacions a) valorsDiferents
        sumaMillors = fromIntegral $ foldl (\acumulat actual -> acumulat + (max (fst actual) (snd actual))) 0 valorsEP
        
-- Obtenció de la columna màxima
-- Pre : llista de guanyances
-- Post : Retorna la posició on es troba la guanyança maxima 
 
maxColumn :: [Double] -> Integer
maxColumn [] = 0
maxColumn l = get2 resultado
   where
        resultado = foldl funFold (head l,0,0) l
        funFold :: (Double,Integer,Integer) -> Double -> (Double,Integer,Integer)   
        funFold acum act
            | act > get1 acum = (act, get3 acum, get3 acum +1)
            | otherwise = (get1 acum, get2 acum, get3 acum+1)

-- generacio de l'arbre
-- Pre : 1. Dataset 2. Llista d'atributs
-- Post : Retorna l'arbre generat 
--data Tree = Empty | Node String [Tree] | NodeEdible String | NodePoison String 
generateTreeDecision2 :: [Bolet] -> [String] -> Tree
generateTreeDecision2 [] [] = Empty
generateTreeDecision2 [] _ = Empty
generateTreeDecision2 llista noms = nodePare
    where
        millorAtribut = maxGain3 llista
        nomMillorAtribut = noms !! (fromInteger millorAtribut)
        dividirMatrix = splitMatrix millorAtribut llista
        novaLlistaAtributs = borrarI (fromInteger millorAtribut) noms

        nodePare = Node nomMillorAtribut (arbreVerinosos ++ arbreComestibles ++ nodesRecursius)

        arbreVerinosos = map (\x -> (NodePoison (fst x)) ) valorsPerClasificarVerinosos
        arbreComestibles = map (\x -> (NodeEdible (fst x))) valorsPerClasificarComestibles
        
        nodesRecursius = map (\x -> (Node (fst x) [recursiu x novaLlistaAtributs])) valorsPerClasificarRecursiu
        
        recursiu :: (String, [Bolet]) -> [String] -> Tree
        recursiu _ [] = error "empty atributs"
        recursiu fff llistaAtributs | trace ("fff:" ++ show fff) False = undefined
        recursiu fff llistaAtributs = generateTreeDecision2 (snd fff) llistaAtributs
    

        valorsPerClasificarVerinosos = filter (\x-> sonTotsVerinosos(snd x)) dividirMatrix  --NodePoison fst head valors....
        valorsPerClasificarComestibles = filter (\x-> sonTotsComestibles(snd x)) dividirMatrix --NodeEdible fst head valors....
        valorsPerClasificarRecursiu = filter (\x-> (not (sonTotsComestibles(snd x)) && (not (sonTotsVerinosos(snd x))))) dividirMatrix
        
        


-- Pre : Matriu de Bolets
-- Post : Retorna si tota la matriu es verinosa     
sonTotsVerinosos :: [Bolet] -> Bool
sonTotsVerinosos l = all ((==True) . esVerinos) l

sonTotsComestibles :: [Bolet] -> Bool
sonTotsComestibles l = all ((==False) . esVerinos) l
-- Pre : matriu strings
-- Post : Retorna el número de columnes totals
columnCount :: [[String]] -> Integer
columnCount (x:_) = fromIntegral $ length x

-- 1 matrix 2 nom atributs 3 columna eliminar  4. Nom Atribut retorna matrix nova i llista atributs nova
splitMatrix :: Integer -> [Bolet]-> [(String, [Bolet])]
splitMatrix _ [] = []
splitMatrix n (s:xs) = (veureAtbN s (fromIntegral n), [boletTreienAtb s]) # splitMatrix n xs
    where
        boletTreienAtb b = borrarAtb b (fromIntegral n)

(#) ::  (String, [Bolet]) ->  [(String, [Bolet])] -> [(String, [Bolet])]
(#) new [] =  [new]
(#) new (x:xs)
    | fst new == fst x =  (fst x, snd x ++ snd new) : xs
    | otherwise = x : (new # xs)

------------------------------------ MAIN -------------------------------------

main :: IO()
main = do
    --contents <- readFile "agaricus-lepiota.data"
    contents <- readFile "test2.txt"
    let reader = lines contents
    let matrix = parseBolets reader
    print matrix

    --let listAtributeName = [nomAtribut x | x <- [0..21]]
        
    --putStrLn "Generant arbre.."

    --int $ maxGain3 matrix
    --t s1 = splitMatrix (maxGain3 matrix) matrix
    --int s1
    --t s2 = splitMatrix (1) (snd $ head s1)
    --int $ maxGain3 (snd $ head s1)
    --int s2
    --t s3 = splitMatrix (0) (snd $ head s2)
    --int s3
    --let tree = generateTreeDecision2 matrix ["cap-shape", "cap-color", "gill-color"]
    ---let tree = generateTreeDecision matrix listAtributeName
    --print(tree)
    --return ()
    