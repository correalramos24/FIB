
import Dataset
import Seta ( listAtributeName )
--import GenDecisionTree ( createDecisionTree )
import Utils ( borrarI )
import GenDecisionTree

import Clasificacion 
--main

--crearArbol

main :: IO()
main = do
    --leemos la entrada y generamos la lista de setas
    contents <- readFile "agaricus-lepiota.data"

    let c = lines contents
    let ds = startDS c
    
    --quitamos el atributo 11
    let dsD = dropMissingAtributes ds
    let atributos = borrarI 11 listAtributeName
    
    --generamos el arbol
    putStrLn "Generando arbol..."
    let t = createDecisionTree dsD atributos
    print t
    putStrLn "Arbol generado"
    
    --pedimos al usuario los datos para clasificar su seta:
    putStrLn "Introduzca para cada atributo que se pide su atributo para clasificar:"
    clasificar t
    