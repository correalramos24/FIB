def myLength(L):
    r = 0
    for x in L:
        r = r + 1
    return r

def myMaximum(L):
    m = L[0]
    for elem in L:
        if elem > m:
            m = elem
    return m


def average(L):
    acu = 0
    for elem in L:
        acu = acu + elem
    return (acu/(len(L)))


def flipList(L):
    if myLength(L) == 0:
        return []
    else:
        actual = L.pop()
        return [actual] + flipList(L) 

    
def buildPalindrome(L):
    Linvertida = flipList(L[:])
    return  Linvertida + L


def flatten(L):
    if isinstance(L, list):
        if L != []:
            fParcial = flatten (L.pop())
            return (flatten(L) + fParcial)
        else:
            return []
    else:
        return [L]


def remove(L1, L2):
    r = []
    for elem in L1:
        if not elem in L2:
            r.append(elem)
    return r


def oddsNevens(L):
    impares = [i for i in L if i%2 != 0]
    pares = [i for i in L if i%2 == 0]
    t = (impares, pares)
    return t


def isPrime(x):
    divisors = [n for n in range (1, x+1) if x % n == 0]
    return divisors == [1, x]


def primeDivisors(n):
    if isPrime(n):
        return [n]
    return [i for i in range(1,n) if (n%i == 0) and isPrime(i)] 
