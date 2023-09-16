from PIL import Image, ImageDraw
from PIL import ImagePath
import os
import copy


def printAndSave(polig: list, backColor=(255, 255, 255),
                 show=False, fileName='img.png'):
    """Crear imatges d'una llista de poligons. Automàticament
    ajusta a els tamanys per fer encabir a una imatge de 400, 400
    sense afectar al

    Args:
        backColor (tuple (R,G,B)): Color de fons del poligon
        poligons (list): Llista d'objectes poligon.
        borderColor ((tuple (R,G,B)): Color de la bora.
        show (bool, optional): Esenyar resultat. Defaults to False.
        fileName (str, optional): Nom del archiu. Defaults to 'img.png'.

    Resultat:
        Guarda la imatge correspoent al actual path. Deixa dos pixels de marge.
    """
    poligons = copy.deepcopy(polig)
    # Calcular BoundingBox original:
    minX = min(map(lambda p: p.minPointX, poligons))
    minY = min(map(lambda p: p.minPointY, poligons))
    maxX = max(map(lambda p: p.maxPointX, poligons))
    maxY = max(map(lambda p: p.maxPointY, poligons))

    originalWidth = maxX.getX() - minX.getX()
    originalHeight = maxY.getY() - minY.getY()

    # Calcular escalatX i Y:
    scaleX = 396 / originalWidth
    scaleY = 396 / originalHeight

    for pol in poligons:
        pol.scale(scaleX, scaleY)

    minX = min(map(lambda p: p.minPointX, poligons))
    minY = min(map(lambda p: p.minPointY, poligons))

    # Portem els vertex fins al origen 0,0.
    # Apliquem +2 per deixar 2 pixels de marge.
    incX = (-1) * minX.getX() + 2
    incY = (-1) * minY.getY() + 2

    for pol in poligons:
        pol.transport(incX, incY)

    i = Image.new("RGB", (400, 400), color=backColor)
    draw = ImageDraw.Draw(i)

    for p in poligons:
        colorAUX = formatRGB(p.getColorsRGB())
        coordAUX = formatPointPNG(p.vertexs)
        if pol.numVertexs() >= 2:
            draw.polygon(coordAUX, outline=colorAUX)
        else:
            draw.point(coordAUX, fill=colorAUX)

    # Volem el (0, 0) abaix a l'esquerra, PIL el col·loca adalt a l'esquerra.
    iF = i.rotate(90)

    if show:
        iF.show()
    iF.save(fileName)
    return os.path.abspath(fileName)


def formatRGB(color01):
    """Els poligons tenen les components RGB al interval [0, 1]
    PIL necesita valors entre RGB 0 i 255 en una tupla
    """
    return (int(color01[0]*255), int(color01[1]*255), int(color01[2]*255))


def formatPointPNG(vertexs):
    """
    PIL requereix un format de punts tal : [(x1, y1), (x2, y2), ...]
    """
    return list(zip(map(lambda v: v.getX(), vertexs),
                    map(lambda v: v.getY(), vertexs)))
