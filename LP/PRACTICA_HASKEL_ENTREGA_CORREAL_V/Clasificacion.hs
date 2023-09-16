module Clasificacion where

import DecisionTree
import Seta


--Funcionalidades para recorrer el arbol y clasificar


--Interación con el usuario:
clasificar :: DecisionTree -> IO()
clasificar Buit = putStrLn "Algo ha ido mal, es posible que el atributo entrado sea erroneo!"

clasificar (TreeNodeEdible _) = putStrLn "Es comestible!"
clasificar (TreeNodePoisonous _) = putStrLn "Es Venenoso!"

clasificar (TreeNode atributo l) = do
    putStrLn ("Valor para atributo :" ++ show atributo)
    respuesta <- getLine
    let arbolSiguiente = buscarRespuesta respuesta l
    clasificar arbolSiguiente

clasificar (DecisionTree atributo l) = do
    putStrLn ("Valor para atributo :" ++ show atributo)
    respuesta <- getLine
    let arbolSiguiente = buscarRespuesta respuesta l
    clasificar arbolSiguiente

--Funcion para recorrer el arbol y buscar si existe el atributo que ha entrado el usuario
buscarRespuesta :: String -> [DecisionTree] -> DecisionTree
buscarRespuesta _ [] = Buit
buscarRespuesta _ [Buit] = Buit 

buscarRespuesta entradaUSR ((DecisionTree valor lista):xs)
    | entradaUSR == valor = DecisionTree (getAtributo $head lista) lista
    | estaEnLaLista lista= buscarRespuesta entradaUSR lista
    | otherwise = buscarRespuesta entradaUSR xs
    where
        estaEnLaLista :: [DecisionTree] -> Bool
        estaEnLaLista l = noesArbolVacio (buscarRespuesta entradaUSR l)

buscarRespuesta entradaUSR ((TreeNode valor lista):xs)
    | entradaUSR == valor = TreeNode (getAtributo $head lista) lista
    | estaEnLaLista lista = buscarRespuesta entradaUSR lista
    | otherwise = buscarRespuesta entradaUSR xs
    where
        estaEnLaLista :: [DecisionTree] -> Bool
        estaEnLaLista l = noesArbolVacio (buscarRespuesta entradaUSR l)

buscarRespuesta entradaUSR ((TreeNodeEdible valor):xs)
    | entradaUSR == valor = TreeNodeEdible valor
    | otherwise = buscarRespuesta entradaUSR xs

buscarRespuesta entradaUSR ((TreeNodePoisonous valor):xs)
    | entradaUSR == valor = TreeNodePoisonous valor
    | otherwise = buscarRespuesta entradaUSR xs
