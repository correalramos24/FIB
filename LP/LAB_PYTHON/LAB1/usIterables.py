def count_unique(L):
    """
    Indica quants elements únics té una llista L
    """
    it = iter(L)
    s = set()
    while True:
        try:
            s.add(it.__next__())
        except StopIteration:
            break
    return len(s)


def remove_duplicates(L):
    """
    Retorna els elements d’una llista sense repeticions
    """
    it = iter(L)
    s = set()
    while True:
        try:
            s.add(it.__next__())
        except StopIteration:
            break
    return s


def flatten(L):
    """
    aplani una llista de llistes d’elements produint una llista d’elements.
    """
    it = iter(L)
    ret = []
    while True:
        try:
            actual = it.__next__()
            ret += actual
        except StopIteration:
            break
    return ret


def flatten_rec(L):
    """
    llistes amb diferents nivells.
    Pista: feu-la recursiva usant la funció estàndard isinstance
    """
    if isinstance(L, list):
        it = iter(L)
        ret = []
        while True:
            try:
                ret += flatten_rec(it.__next__())
            except StopIteration:
                break
    else:
        ret = [L]
    return ret
