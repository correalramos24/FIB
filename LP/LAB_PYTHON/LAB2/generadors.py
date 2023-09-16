def fibs():
    a = 0
    yield a
    b = 1
    yield b
    while True:
        a, b= b, a+b
        yield b


def roots(x):
    fN = x;
    yield fN
    while True:
        fN = (1/2)*(fN + (x/fN))
        yield fN


def primes():
    q = 2
    D = {}
    while True: 
        if q not in D:
            yield q
            D[q*q] = [q]
        else:
            for p in D[q]:
                D.setdefault(p+q, []).append(p)
            del D[q]
        q += 1

def is_prime(x):
    divisors = [n for n in range (1, x+1) if x % n == 0]
    return divisors == [1, x]
#def primes2():
#    q = 2
#    while True:
#        if is_prime(q):
#            yield q
#        q += 1    


def is_hamming(x):
    divisors = [n for n in range (2, x+1) if x % n == 0 and is_prime(n)]
    if ((2 in divisors) or (3 in divisors) or (5 in divisors)) and max(divisors) <= 5:
        return True 
    else:
        return False
        
def hammings():
    h = 1
    yield h
    while True:
        if is_hamming(h):
            yield h 
        h += 1
#g1 = fibs()
#print([next(g1) for n in range(10)])
#g2 = roots(4)
#print([round(next(g2), 10) for n in range(10)])
#g3 = primes()
#print([next(g3) for n in range(20)])    
##g3 = primes2()
##print([next(g3) for n in range(20)])    
#g4 = hammings()
#print([next(g4) for n in range(20)])
