countIfv2 :: (Int -> Bool) -> [Int] -> Int
countIfv2 predicado lista = foldl funcion inicial lista
    where
        funcion anterior actual
            | predicado actual = anterior + 1
            | otherwise = anterior
        inicial = 0        

countIf :: (Int -> Bool) -> [Int] -> Int
countIf f a = length (filter f a)

aplicarOpLista :: [Int] -> (Int -> Int) -> [Int]
aplicarOpLista [] _ = []
aplicarOpLista (x:xn) fun = (fun x) : aplicarOpLista xn fun

pamv1 :: [Int] -> [Int -> Int] -> [[Int]]
pamv1 _ [] = []
pamv1 lista (f1:fn) = aplicarOpLista lista f1 : pam lista fn

pam :: [Int] -> [Int -> Int] -> [[Int]]
pam xs fs = [map fun xs| fun <- fs]

pam2 ::[Int] -> [Int -> Int] -> [[Int]]
pam2 xs fs = [ map (flip ($) x) fs | x <- xs ]

filterFoldl :: (Int -> Bool) -> (Int -> Int -> Int) -> Int -> [Int] -> Int
filterFoldl pred funFilter inicial lista = (foldl funFilter inicial lFiltrada)
    where
        lFiltrada = filter pred lista

insert :: (Int -> Int -> Bool) -> [Int] -> Int -> [Int]
insert _ [] x = [x]
insert relacion (x:xs) elemento
    | x `relacion` elemento = x : insert relacion xs elemento 
    | otherwise = elemento : x : xs


insertionSort :: (Int -> Int -> Bool) -> [Int] -> [Int]
insertionSort _ [] = []
insertionSort relacion lista = foldl (insert relacion) [] lista
