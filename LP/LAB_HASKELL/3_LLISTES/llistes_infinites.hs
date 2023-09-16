ones :: [Integer]
ones = iterate id 1

nats :: [Integer]
nats = iterate (+1) 0

cremallera :: [a] -> [a] -> [a]
cremallera x [] = x
cremallera [] y = y
cremallera (x:xs) (y:ys) = x : y : cremallera xs ys

ints :: [Integer]
ints = 0 : cremallera intsPos intsNeg
    where
        intsPos = iterate (1+) 1
        intsNeg = iterate (negMas1) (-1)
            where
                negMas1 :: Integer -> Integer
                negMas1 x
                    | x < 0 = x - 1
                    | otherwise = (x * (-1)) -1

triangulars :: [Integer]
triangulars = 0 : scanl funScan 1 lista
    where
        funScan = (\x y -> x + y)
        lista = drop 2 nats

factorials :: [Integer]
factorials = 1 : scanl funScan 1 lista
    where
        funScan = (\x y -> x*y)
        lista = drop 2 nats

fibs :: [Integer]
fibs = 0 : 1 : zipWith (+) fibs (tail fibs)

isPrimeRec :: Integer -> Integer -> Bool
isPrimeRec x d
    | d == 1 = True
    | mod x d == 0 = False
    | otherwise = isPrimeRec x (d - 1)

isPrime :: Integer -> Bool
isPrime x
    | x == 0 = False
    | x == 1 = False
    | otherwise = isPrimeRec x (floor (sqrt (fromIntegral x)))

primes :: [Integer]
primes = primers 2 
    where
        primers :: Integer -> [Integer]
        primers x
            | isPrime x = x : primers (x+1)
            | otherwise = primers(x + 1)
        
--Els nombres de Hamming són aquells que només tenen 2, 3 i 5 com a divisors primers.
hammings :: [Integer]
hammings = 1 : intercala (map (2*) hammings) (map (3*) hammings) (map (5*) hammings)
    where 
        intercala l1 l2 l3 = intercala2 (intercala2 l1 l2) l3
            where
                intercala2 l1 [] = l1
                intercala2 [] l2 = l2
                intercala2 (a:ax) (b:bx)
                    | a < b = a : intercala2 ax (b:bx)
                    | b < a = b : intercala2 (a:ax) bx
                    | otherwise = intercala2 (a:ax) bx



lookNsay :: [Integer]
lookNsay = iterate count 1

count :: Integer -> Integer
count a = read $ next $ show a

next :: [Char] -> [Char]
next [] = []
next cs = (show n) ++ [pr] ++ next cua
  where 
    pr = head cs
    n = length $ takeWhile ( == pr) cs
    cua = dropWhile ( == pr) cs

tartaglia :: [[Integer]]
tartaglia = (iterate (pascal) [1])

pascal :: [Integer] -> [Integer]
pascal xs = zipWith (+) (xs ++ [0]) ([0] ++ xs)
