quetelet :: Float -> Float -> String
quetelet massa altura
    | imc < 18 = "magror"
    | imc < 25 = "corpulencia normal"
    | imc < 30 = "sobrepes"
    | imc < 40 = "obesitat"
    | otherwise= "obesitat morbida"
    where
        imc = (massa) / (altura^2)
        


formatSortida :: String -> String
formatSortida entrada = (head $ words entrada) ++ ": " ++ quetelet pes altura
    where 
        pes =  read $ head $ tail $ words entrada  :: Float
        altura =  read $ last $ tail $ words entrada :: Float
    

main :: IO()
main = do
    l <- getLine
    if(l /= "*") then do
        putStrLn (formatSortida l)
        main
    else 
        return ()
