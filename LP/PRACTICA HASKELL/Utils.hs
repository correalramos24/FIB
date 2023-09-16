module Utils where

--Funciones genericas de soporte:

--Borrar el i-esimo elemento de una lista
borrarI :: Int -> [a] -> [a]
borrarI n a = take n a ++ drop (n+1) a

--Dividir un string en base a un predicado
splitString     :: (Char -> Bool) -> String -> [String]
splitString p s =  case dropWhile p s of
                      "" -> []
                      s' -> w : splitString p s''
                            where (w, s'') = break p s'

--Funciones para tuplas de tres elementos:
get1 :: (a, b, c) -> a
get1 (x,_,_) = x

get2 :: (a, b, c) -> b
get2 (_,x,_) = x

get3 :: (a, b, c) -> c
get3 (_,_,x) = x
 
--Funcion para buscar el maximo y su posición en la lista.
myMax :: Ord a => [a] -> Integer
myMax [] = 0
myMax l = get2 resultado
   where
      resultado = foldl funFold (head l,0,0) l
      --valor maximo, posicion maximo, contador 
      funFold :: Ord a => (a,Integer,Integer) -> a -> (a,Integer,Integer)
      funFold acumulado actual
         | actual > get1 acumulado = (actual, get3 acumulado, get3 acumulado +1)
         | otherwise = (get1 acumulado, get2 acumulado, get3 acumulado+1)

