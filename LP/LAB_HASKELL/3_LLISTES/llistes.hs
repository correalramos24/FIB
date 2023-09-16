myMap :: (a -> b) -> [a] -> [b]
myMap fun llista = [fun x | x <- llista]

myFilter :: (a -> Bool) -> [a] -> [a]
myFilter pred llista = [x | x <- llista, pred x]

myZipWith :: (a -> b -> c) -> [a] -> [b] -> [c]
myZipWith fun llista1 llista2 = [fun x y | (x, y) <- zip llista1 llista2]

--donades dues llistes d’enters, genera la llista que aparella els elements si l’element de la segona llista divideix al de la primera.
thingify :: [Int] -> [Int] -> [(Int, Int)]
thingify llista1 llista2 = [(x,y) | x <- llista1, y <-llista2, x `mod` y == 0]

factors :: Int -> [Int]
factors n = [x | x <- [1..n], mod n x == 0]