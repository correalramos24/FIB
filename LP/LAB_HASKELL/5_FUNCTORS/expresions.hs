data Expr = Val Int | 
            Add Expr Expr | 
            Sub Expr Expr | 
            Mul Expr Expr | 
            Div Expr Expr
            

eval1 :: Expr -> Int
eval1 (Val x) = x
eval1 (Add x y) = (eval1 x) + (eval1 y)
eval1 (Sub x y) = (eval1 x) - (eval1 y)
eval1 (Mul x y) = (eval1 x) * (eval1 y)
eval1 (Div x y) = (eval1 x) `div` (eval1 y)

--divisió per 0 -> resultat Nothing
safe2Div :: Int -> Int -> Maybe Int
safe2Div x y
    |y == 0 = Nothing
    |otherwise = Just (x `div` y)

eval2 :: Expr -> Maybe Int
eval2 (Val x) = Just x
eval2 (Div _ (Val 0)) = Nothing
eval2 (Add x y) = do 
                    op1 <- eval2(x) 
                    op2 <- eval2(y)
                    return (op1 + op2)
eval2 (Sub x y) = do 
                    op1 <- eval2(x) 
                    op2 <- eval2(y)
                    return (op1 - op2)

eval2 (Mul x y) = eval2(x) >>= \op1 -> eval2(y) >>= \op2 -> return (op1 * op2)

eval2 (Div x y) = do
                    op1 <- eval2(x)
                    op2 <- eval2(y)
                    safe2Div op1 op2

--div/0 -> missatge "div0"
safe3Div :: Int -> Int -> Either String Int
safe3Div n m
    | m == 0    = Left "div0"
    | otherwise = Right (n `div` m)

eval3 :: Expr -> Either String Int
eval3 (Val x) = Right x
eval3 (Add x y) = do {op1 <- eval3(x); op2 <- eval3(y); return (op1 + op2)}
eval3 (Sub x y) = do {op1 <- eval3(x); op2 <- eval3(y); return (op1 - op2)}
eval3 (Mul x y) = do {op1 <- eval3(x); op2 <- eval3(y); return (op1 * op2)}
eval3 (Div x y) = do {op1 <- eval3(x); op2 <- eval3(y); (safe3Div op1 op2)}