module DecisionTree where
    
{-
Definición de arbol de decision, podemos tener los siguientes constructores:


-- DecisionTree campos  [DecisionTree]  -> Atributo, lista de decisiones.
-- TreeNode atributo    [DecisionTree]  -> Nodo interno, la decision depende de otros
-- TreeNodeE/P valor    Edible/Poisnous -> Nodo hoja, la decision esta tomada
-}    

data DecisionTree = Buit |
                    DecisionTree String [DecisionTree] | 
                    TreeNode String [DecisionTree] |
                    TreeNodeEdible String |
                    TreeNodePoisonous String

--operacion auxiliar
indent :: Int -> String
indent n = concat $ replicate n " "

showListDecisionTree :: Int -> [DecisionTree] -> String
showListDecisionTree _ [] = ""
showListDecisionTree n (x:xs) = indent n ++ showDecisionTree n x ++ 
                                showListDecisionTree n xs

showDecisionTree :: Int -> DecisionTree -> String
showDecisionTree _ Buit = "Arbre Buit"
showDecisionTree n (TreeNode y l) = "|" ++ y ++ " ->" ++ showListDecisionTree n l
showDecisionTree _ (TreeNodeEdible y) = "|" ++ y ++ "-> Edible\n"
showDecisionTree _ (TreeNodePoisonous y) = "|" ++ y ++ "-> Poisnous\n"
showDecisionTree n (DecisionTree y l) = "*" ++ y ++ ": \n" ++ showListDecisionTree (n+1) l

instance Show DecisionTree where
    show dt = showDecisionTree 0 dt


getAtributo :: DecisionTree -> String
getAtributo (Buit) = ""
getAtributo (TreeNodePoisonous _) = ""
getAtributo (TreeNodeEdible _) = ""
getAtributo (DecisionTree atributo _) = atributo
getAtributo (TreeNode atributo  _)   = atributo


noesArbolVacio (Buit) = False
noesArbolVacio (TreeNodePoisonous _) = True
noesArbolVacio (TreeNodeEdible _)= True 
noesArbolVacio (DecisionTree _ _) = True
noesArbolVacio (TreeNode _  _) = True


{-
d1 = TreeNodeEdible "a"
d2 = TreeNodeEdible "b"
d3 = TreeNodePoisonous "z"
t1 = DecisionTree "atb1" [d1,d2,d3]


getOptions :: [DecisionTree] -> [Either [DecisionTree] String]
getOptions [] = []
getOptions (x:xs) = getOptions' x : getOptions xs

getOptions' :: DecisionTree -> Either [DecisionTree] String
getOptions' Buit = Right ""
getOptions' (DecisionTree _ listaOptions) = Left listaOptions
getOptions' (TreeNodeEdible _) = Right "Comestible"
getOptions' (TreeNodePoisonous _) = Right "Venenoso"
getOptions' (TreeNode _ listaOptions) = Left listaOptions
-}