myFoldl :: (a -> b -> a) -> a -> [b] -> a
myFoldl _ n [] = n
myFoldl f n (x:xs) = myFoldl f primer xs 
    where
        primer = ((f n) x) 

myFoldr :: (a -> b -> b) -> b -> [a] -> b
myFoldr _ n [] = n
myFoldr fun inicial (x:xs) = x `fun` foldr fun inicial xs
    
myIterate :: (a -> a) -> a -> [a]
myIterate f x  = x : myIterate f anterior
    where anterior = f x

myUntil :: (a -> Bool) -> (a -> a) -> a -> a
myUntil pred fun n
    | pred n = n
    | otherwise = myUntil pred fun anterior
    where anterior = fun n

myMapv2 :: (a -> b) -> [a] -> [b]
myMapv2 funAB lista = tail $scanl (\x y -> funAB(y)) primerElemento lista
    where primerElemento = funAB (head lista)

myMap :: (a -> b) -> [a] -> [b]
myMap f lista = [f a | a<-lista]

myFilter :: (a -> Bool) -> [a] -> [a]
myFilter predicado lista = [i | i <- lista, predicado i]

myAll :: (a -> Bool) -> [a] -> Bool
myAll b l = and (map b l)

myAllv2 :: (a -> Bool) -> [a] -> Bool
myAllv2 b l = length (takeWhile b l) == length l

myAny :: (a -> Bool) -> [a] -> Bool
myAny b l = or (map b l)

myAnyv2 :: (a -> Bool) -> [a] -> Bool
myAnyv2 b l = length (takeWhile b l) >= 1

myZip :: [a] -> [b] -> [(a, b)]
myZip _ [] = []
myZip [] _ = []
myZip (x:xs) (y:ys) = [(x,y)] ++ myZip xs ys

myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
myZipWith fun l1 l2 = [fun x y | (x,y) <- zip l1 l2]

myZipWithv2 :: (a -> b -> c) -> [a] -> [b] -> [c]
myZipWithv2 f l1 l2 = myMap expandOperation lista
    where 
        lista = myZip l1 l2                 
        expandOperation (p1,p2) = f p1 p2