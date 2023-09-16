module GenDecisionTree where
import DecisionTree 
import Seta
import Dataset
import Utils
import EntropiaGanancia

--Funcionalidades para generar el arbol


{-
Funcion para generar el arbol
Entrada: Lista de setas y lista de nombres de atributos
Salida: El arbol de decisiones para la lista de setas entrada
-}
createDecisionTree :: [Seta] ->  [String] -> DecisionTree
createDecisionTree [] _ = Buit
createDecisionTree _ [] = error "El arbol no tiene solucion"
createDecisionTree datasetSetas nombreCampos = DecisionTree nomAtb (createDecisionTree' sortedDataSplited nuevosCampos)
    where
        mejorAtb = mejorGanancia datasetSetas 
        dataSplited = splitDS3 mejorAtb datasetSetas
        sortedDataSplited = sortDS dataSplited
        nomAtb = nombreCampos !! fromInteger mejorAtb
        nuevosCampos = borrarI (fromInteger mejorAtb) nombreCampos
{-
Funcion para classificar las lista asociativas de valor, setas
Entrada: Lista asociativa de valores y setas, lista de nombres de atributos
Salida: Lista de arboles dependientes de la lista asociada
-}
createDecisionTree' :: [(String, [Seta])] -> [String] -> [DecisionTree]
createDecisionTree' [] _ = []
createDecisionTree' ((atributo, lista):xs) campos
    | allSameDecision lista && isEdible (head lista) = TreeNodeEdible atributo : createDecisionTree' xs campos
    | allSameDecision lista && isPoisonous (head lista) = TreeNodePoisonous atributo : createDecisionTree' xs campos
    | otherwise = TreeNode atributo [recursie] : createDecisionTree' xs campos
    where 
        recursie = createDecisionTree lista campos

{-
Ordenamos las listas asociadas de valores, para mostrar el arbol con mas sencillez
Entrada: Lista asociada valores, setas
Salida: Lista asociada valores, setas ordenada
-}  
sortDS :: [(String, [Seta])] -> [(String, [Seta])]
sortDS [] = []
sortDS ((atributo, lista):xs)
    | allSameDecision lista = (atributo, lista) : sortDS xs
    | otherwise = sortDS xs ++ [(atributo, lista)]