
stringMultiN :: Int -> Either Int String
stringMultiN x 
    | (x `mod` 3 == 0) && (x `mod` 5 == 0) = Right "FizzBuzz"
    | x `mod` 3 == 0 = Right "Fizz"
    | x `mod` 5 == 0 = Right "Buzz"
    | otherwise = Left x

fizzBuzz :: [Either Int String]
fizzBuzz = [stringMultiN x | x <- [0..]]