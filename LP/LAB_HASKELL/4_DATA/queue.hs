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