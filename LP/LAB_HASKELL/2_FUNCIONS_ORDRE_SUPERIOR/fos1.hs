eql :: [Int] -> [Int] -> Bool
eql a b = length a == length b && all (== 0) (zipWith (-) a b)

prod :: [Int] -> Int
prod a = foldl (*) 1 a

prodOfEvens :: [Int] -> Int
prodOfEvens a = prod (filter even a)

powersOf2 :: [Int]
powersOf2 = iterate (*2) 1


sumList a = foldl (+) 0 a

scalarProduct :: [Float] -> [Float] -> Float
scalarProduct a b = sumList $ zipWith (*) a b