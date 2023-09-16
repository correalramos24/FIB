
def absValue(x):
    if x < 0:
        return x * (-1)
    return x


def power(x, p):
    return powerRec(x, p)


def powerIterative(x, p):
    ret = x
    while(p > 0):
        ret = ret * x
        p = p - 1
    return ret


def powerRec(x, p):
    if p == 0:
        return 1
    else:
        return (x * powerRec(x, p-1))


def isPrime(x):
    divisors = [n for n in range (1, x+1) if x % n == 0]
    return divisors == [1, x]


def slowFib(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    else:
        return slowFib(n-1) + slowFib(n-2)


def quickFib(n):
    dp = dict()
    dp[0] = 0
    dp[1] = 1
    for i in range(2, n+1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n]
