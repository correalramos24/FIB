insert :: [Int] -> Int -> [Int]
insert [] a = [a]
insert (i:xs) n
    | n < i = n : i : xs 
    | otherwise = i : insert xs n

isort :: [Int] -> [Int]
isort [] = []
isort [a] = [a]
isort (i:xs)
    | no_buida && i > head xs = isort (insert xs i)
    | otherwise = i : isort xs
    where
        no_buida = not (null xs)
--asumint que l'element sempre esta a la llista:
remove :: [Int] -> Int -> [Int]
remove [] _ = []
remove (i:xs) a
    | i == a = xs
    | otherwise = i : remove xs a 

ssort :: [Int] -> [Int]
ssort [] = []
ssort a = minimum a : ssort (remove a (minimum a))

--les llistes estan ordenades:        
merge :: [Int] -> [Int] -> [Int]
merge [] [] = []
merge [] a = a
merge a [] = a
merge (a:xs) (b:xn) 
    | a > b = b : merge (a:xs) xn
    | otherwise = a : merge xs (b:xn)

msort :: [Int] -> [Int]
msort [] = []
msort [a] = [a]

msort xs = 
    let
        firstHalf l = take (div (length l) 2) l
        secondhalf l = drop (div (length l) 2) l
    in
        merge (msort (firstHalf xs)) (msort (secondhalf xs))

qsort :: [Int] -> [Int] 
qsort [] = []
qsort (x:xs) = 
    let 
        smallerSorted = qsort [a | a <- xs, a  <= x]
        biggerSorted = qsort[a | a <- xs, a > x]
    in smallerSorted ++ [x] ++ biggerSorted

genQsort :: Ord a => [a] -> [a]
genQsort [] = []
genQsort (x:xs) =
   let 
        smallerSorted = genQsort [a | a <- xs, a <= x]
        biggerSorted = genQsort [a | a <- xs, a > x]
  in  smallerSorted ++ [x] ++ biggerSorted