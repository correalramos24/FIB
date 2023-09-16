def my_map(f, L):
    return [f(x) for x in L]


def my_filter(f, L):
    return [x for x in L if f(x)]


def factors(n):
    return [1] + [x for x in range(2, n+1) if n % x == 0]


def triplets(n):
    return  [(a, b, c) for a in range(1, n+1) for b in range(1, n+1) for c in range(1, n+1) if (a**2) + (b**2) == (c**2)]
