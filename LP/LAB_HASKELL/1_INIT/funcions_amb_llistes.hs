myLength :: [Int] -> Int
myLength [] = 0
myLength(_:xs) = 1 + myLength xs

myMaximum :: [Int] -> Int
myMaximum[a] = a
myMaximum(a:xs) 
    | myMaximum xs > a = myMaximum xs
    | otherwise = a

suma :: [Int] -> Int
suma[] = 0
suma(x:xs) = x + suma xs

average :: [Int] -> Float
average (xs) = fromIntegral (div (suma xs) (myLength xs))

buildPalindrome :: [Int] -> [Int]
buildPalindrome(xs) = reverse xs ++ xs

--si 
remove :: [Int] -> [Int] -> [Int]
remove a []= a
remove [] _ = []
remove (x:xs) b 
    | elem x b = remove xs b
    | otherwise = x : remove xs b

flatten :: [[Int]] -> [Int]
flatten [] = []
flatten (a:xs) = a ++ flatten xs


oddList :: [Int] -> [Int]
oddList [] = []
oddList (a:xs) 
    | mod a 2 == 0 = a : oddList xs
    | otherwise = oddList xs

evenList :: [Int] -> [Int]
evenList [] = []
evenList (a:xs)
    | mod a 2 /= 0 = a : evenList xs
    | otherwise = evenList xs

oddsNevens :: [Int] -> ([Int],[Int])
oddsNevens [] = ([],[])
oddsNevens xs = (evenList xs, oddList xs)

divisors :: Int -> [Int]
divisors n = [x | x <-[1..n], n `mod` x == 0]

isPrime :: Int -> Bool
isPrime n = divisors n == [1,n]

primeDivisors :: Int -> [Int]
primeDivisors n = [x | x <-[1..n], mod n x == 0, isPrime x]
