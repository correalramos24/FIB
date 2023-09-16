data Queue a = Queue [a] [a]
     deriving (Show)

create :: Queue a
create = Queue [] []

push :: a -> Queue a -> Queue a
push p (Queue h l) = Queue h (p:l)

pop :: Queue a -> Queue a
pop (Queue [] l) = (Queue (tail(reverse l)) [])
pop (Queue (_:xs) l) = (Queue xs l)

top :: Queue a -> a
top (Queue [] l) = top $ Queue (reverse l) []
top (Queue (x:_) _) = x

empty :: Queue a -> Bool
empty (Queue [] []) = True
empty _ = False

instance Eq a => Eq (Queue a) where
    (Queue l1a l2a) == (Queue l1b l2b) = (l1a ++ reverse l2a) == (l1b ++ reverse l2b)

instance Functor Queue where
    fmap f (Queue lcap lcua) = Queue (map f lcap) (map f lcua)

translation :: Num b => b -> Queue b -> Queue b
translation num cua = fmap (+ num) cua

--un operador dentro de una cola, opera una cola y devuelve otra cola.
--q1 cola de funciones [(+2)] 
--q1 cola de valores [1,2,3]

joinQueues :: Queue a -> [a]
joinQueues (Queue l1 l2) = l1 ++ (reverse l2)

instance Applicative Queue where
    pure x = Queue [x] []
    (<*>) q1 q2 = (Queue lResultado [])
        where 
            lResultado = lFunctions <*> lValues
            lFunctions = joinQueues q1
            lValues = joinQueues q2

--d'una cua i una funcio que retorna cues, retorna la llista operada
instance Monad Queue where
    return x = (Queue [x] [])
    (>>=) cua fun = (Queue l [])
        where
            l = (joinQueues cua) >>= (joinQueues . fun)

kfilter :: (p -> Bool) -> Queue p -> Queue p
kfilter predicat cua = do
    actual <- cua
    if (predicat actual) then return actual else (Queue [] [])

--kfilter2 :: (p -> Bool) -> Queue p -> Queue p
--kfilter2 predicat cua = (cua >>= \actual -> if (predicat actual) then return actual else (Queue [][]))