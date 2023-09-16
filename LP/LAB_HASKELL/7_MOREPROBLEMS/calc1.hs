

sumMultiples35 :: Integer -> Integer
sumMultiples35 n = sum (multiples3 n) + sum (multiples5 n)
    where 
        multiples3 lim = [x | x <-[1..lim-1], x `mod` 3 == 0]
        multiples5 lim = [x | x <-[1..lim-1], x `mod` 5 == 0]

fib :: Int->(Integer, Integer)
fib 0 = (0,1)
fib n
    | even n = (c, d)
    | otherwise = (d, c+d)
    where 
        (a,b) = fib(div n 2)
        c = a * (b * 2 - a)
        d = a * a + b * b

fibonacci :: Int -> Integer
fibonacci 0 = 0
fibonacci 1 = 1       
fibonacci x = fst(fib x)

--lista infinita de fibs
fibs :: [Integer]
fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

sumEvenFibonaccis :: Integer -> Integer
sumEvenFibonaccis n = foldl (+) 0 llistaFibEven
    where 
        llistaFibEven = [x | x <- take (fromIntegral(n-1)) $ (fibs), even x]

divisors :: Integer -> [Integer]
divisors n = [x | x <-[1..n], n `mod` x == 0]

isPrime :: Int -> Bool
isPrime n = divisors (fromIntegral(n)) == [1,fromIntegral(n)]

largestPrimeFactor :: Int -> Int
largestPrimeFactor n = maximum lista
    where
        lista = [x | x <- [2..n], (isPrime x) && (gcd n x == x)]

reversal :: Integral a => a -> a
reversal = go 0
  where go a 0 = a
        go a b = let (q,r) = b `divMod` 10 
            in go (a*10 + r) q

isPalindromic :: Integer -> Bool
isPalindromic n = reversal n == n