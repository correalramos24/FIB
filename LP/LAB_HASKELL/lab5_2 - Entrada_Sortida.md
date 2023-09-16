# Entrada / Sortida

El programa principal és del tipus `main:: IO()`. 

* El constructor ``IO`` és una instància de mònada, es sol utilitzar amb la notació do.

`````haskell
main = do
    putStrLn "Com et dius?"
    nom <- getLine
    putStrLn $ "Hola " ++ nom + "!"
`````

Pot semblar que tenim “efectes laterals”; cosa que en llenguatges funcionals no pot ser.

:arrow_right: Les operacions d’E/S reben un “món” i en retornen un altre i addicionalment entren i surten dades.

Les principals operacions d’E/S son:

````haskell
getChar     :: IO Char              -- obté següent caràcter
getLine     :: IO String            -- obté següent línia
getContents :: IO String            -- obté tota l'entrada
putChar     :: Char -> IO ()        -- escriu un caràcter
putStr      :: String -> IO ()      -- escriu un text
putStrLn    :: String -> IO ()      -- escriu un text i un salt de línia
print       :: Show a => a -> IO () -- escriu qualsevol showable
````

### Característiques

* L'E/S també és *lazy*, no cal preocupar-se perquè l'entrada sigui massa llarga.

  * Podem definir “variables” (no ho son, es llenguatge funcional estem definit “álias”).

    ````haskell
    main = do
        x <- getLine
        let f = factorial (read x)
        print f
    --alternativament podriem fer:
    	f <- return $ factorial (read x)
    ````

    

### Exemple

Un programa que rep un paraula i la retorna girada: 

````haskell
--Primer example: Programa base
main = do
    x <- getLine
    let y = reverse x
    putStrLn x
    putStrLn y

--Segon exemple: Llegint fins *
main = do
    line <- getLine
    if line /= "*" then do	
        putStrLn $ reverse line
        main
    else
        return ()			--sempre s'ha de retornar un IO()
main = do
    contents <- getContents
    mapM (putStrLn . reverse) (lines contents) --reverse, escriure
````

Alternativament, podem utilitzar `interact`, que va llegint línies 

````haskell
interact :: (String -> String) -> IO ()
main = interact reverse
````

