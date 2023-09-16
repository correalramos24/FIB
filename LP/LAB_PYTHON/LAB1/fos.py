from functools import reduce


def evens_product(L):
    """
    multiplica tots el nombres parells d’una llista d’enters.
    """
    if L == []:
        return 0
    return reduce(lambda acc, y: acc * y if y % 2 ==0 else acc, L)


def reverse(L):
    """
    Inverteix els elements d’una llista usant la funció reduce
    """
    return reduce(lambda acc, y: [y] + acc, L, [])


def zip_with(f, L1, L2):
    """
    Equivalent a ZipWith de Haskell
    """
    z = zip(L1, L2)
    return reduce(lambda acc, y: [f(y[0],y[1])] + acc, z, [])


def count_if(f, L):
    """
    donada un predicat i una llista, 
    ens retorna el nombre d’elements de la llista que satisfan la propietat.
    """
    f = list(filter(lambda x: f(x), L))
    return len(f)

