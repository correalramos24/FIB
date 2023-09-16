flatten :: [[Int]] -> [Int]
flatten a = foldl (++) [] a



myLength :: String -> Int
myLength [] = 0
myLength a = foldl (sumaChar) 0 a
    where
        sumaChar = (\y _ -> y+1)
        
concatExp = (\ x act -> act ++ [x])
myReverse :: [Int] -> [Int]
myReverse xs = foldr (concatExp) [] xs

apariciones :: [Int] -> Int -> [Int]
apariciones [] _ = [0]
apariciones l n = [length $ filter (== n) l]

countIn :: [[Int]] -> Int -> [Int]
countIn [] _ = []
countIn (x:xs) n = (apariciones x n) ++ countIn xs n
    
esEspai = (== ' ')
noEsEspai = (/= ' ')

firstWord :: String -> String
firstWord a = takeWhile noEsEspai (dropWhile esEspai a)