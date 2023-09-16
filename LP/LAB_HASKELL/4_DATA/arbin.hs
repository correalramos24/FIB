data Tree a = Node a (Tree a) (Tree a) | Empty 
    deriving (Show)

size :: Tree a -> Int
size Empty = 0
size (Node _ fd fe) = 1 + (size fd) + (size fe)

height :: Tree a -> Int
height Empty = 0
height (Node _ fd fe) = 1 + max (height fd) (height fe)

equal :: Eq a => Tree a -> Tree a -> Bool 
equal Empty Empty = True
equal Empty _   = False
equal _ Empty   = False
equal (Node aa fdA feA) (Node bb fdB feB) = (aa == bb) && 
                    (fdA `equal` fdB) && (feA `equal` feB)
                    
isomorphic :: Eq a => Tree a -> Tree a -> Bool
isomorphic Empty Empty = True
isomorphic Empty _ = False
isomorphic _ Empty = False
isomorphic (Node aa fdA feA) (Node bb fdB feB) = (aa == bb) && 
        ((equal fdA fdB && equal feA feB) || (equal fdA feB && equal feA fdB))

preOrder :: Tree a -> [a]
preOrder Empty = []
preOrder (Node a fd fe) = [a] ++ preOrder fd ++ preOrder fe

postOrder :: Tree a -> [a] 
postOrder Empty = []
postOrder (Node a fd fe) = postOrder fd ++ postOrder fe ++ [a] 

inOrder :: Tree a -> [a]
inOrder Empty = []
inOrder (Node a fd fe) = inOrder fd ++ [a] ++ inOrder fe

breadthFirst :: Tree a -> [a]
breadthFirst Empty = []
breadthFirst a = i_breadthFirst [a]

i_breadthFirst :: [Tree a] -> [a]
i_breadthFirst [] = []
i_breadthFirst (Empty:ts) = i_breadthFirst ts
i_breadthFirst ((Node x fe fd):ts) = x : (i_breadthFirst $ ts ++ [fe, fd])

build :: Eq a => [a] -> [a] -> Tree a
build [] [] =                           Empty
build (x:preordre) inordre = Node x     (build leftPreordre leftInordre)
                                        (build rightPreordre rightInordre)
        where
                leftInordre   = takeWhile (/= x) inordre
                leftPreordre  = take (length leftInordre) preordre
                rightPreordre = drop (length leftInordre) preordre
                rightInordre  = tail (dropWhile (/= x) inordre)
 
overlap :: (a -> a -> a) -> Tree a -> Tree a -> Tree a
overlap _ Empty Empty = Empty
overlap _ Empty a = a
overlap _ a Empty= a
overlap fun (Node ax feA fdA) (Node bx feB fdB) = Node (ax `fun` bx) (overlap fun feA feB) (overlap fun fdA fdB)