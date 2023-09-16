

import math
from functools import reduce
from random import random


class vertex:
    """Representació d'un vèrtex 2-dimensional (x,y)."""

    def __init__(self, x, y):
        """Contructor, cal passar dos valors numèrics."""
        if(isinstance(x, (int, float)) and isinstance(y, (int, float))):
            self.x = x
            self.y = y
        else:
            raise NameError('vertexNoNumeric')

    def __str__(self):
        """Format string per mostrar les dades."""
        return "Vèrtex (x: {}, y: {})".format(self.x, self.y)

    def __repr__(self):
        return "{:.3f} {:.3f} ".format(self.x, self.y)

    def __eq__(self, other):
        """Dos punts son iguals si les seves coordenades son iguals."""
        return (self.getX() == other.getX()) and (self.getY() == other.getY())

    def __gt__(self, other):
        """Ordenem els punts en clockwiseorder."""
        if self.x > other.x:
            return True
        if self.x < other.x:
            return False
        else:
            return (self.y > other.y)

    def getX(self):
        """Retorna la coordenada X (horitzontal) del vèrtex."""
        return self.x

    def getY(self):
        """Retorna la coordenada Y (vertical) del vèrtex."""
        return self.y

    def traslacio(self, incX, incY):
        """Incrementa en incX, incY el vèrtex."""
        self.x += incX
        self.y += incY
        return self

    def escala(self, incX, incY):
        """Escala el vertexs en incX, incY."""
        self.x *= incX
        self.y *= incY
        return self

    def __hash__(self):
        return int(self.getX()*100 + self.getY()*10)


class digon:
    """
    Representació d'un poligon de dos punts (segment) amb dos dimensions.
    """

    def __init__(self, v1, v2):
        """Constructor d'un segment.
        Args:
            v1, v2 de tipus vèrtex.
        """
        self.v1 = v1
        self.v2 = v2
        self.__calcRecta()

    def __repr__(self):
        return "Digon =>" + repr(self.v1) + " " + repr(self.v2) + "\n"

    def __calcRecta(self):
        self.A = self.v2.getY() - self.v1.getY()
        self.B = self.v1.getX() - self.v2.getX()
        self.C = self.A * self.v1.getX() + self.B * self.v1.getY()

    def distanciaEuclidia(self):
        """
        Càlcul de la distància euclidia entre dos punts, amb dos decimals.
        Retorna:
            float: Distància euclidia entre v1 i v2
        """
        dX = (self.v1.getX() - self.v2.getX())**2
        dY = (self.v1.getY() - self.v2.getY())**2
        return round(math.sqrt(dX + dY), 2)

    def distanciaPuntDigon(self, point):
        """
        Distància entre el digon i un punt.
        """
        difY = self.v2.getY() - self.v1.getY()
        difX = self.v2.getX() - self.v1.getX()
        r = difX * (point.getY() - self.v1.getY()) -\
            difY * (point.getX() - self.v1.getX())
        return (abs(r))

    def estaPerSobreDigon(self, point):
        """
        Indica si un punt està per sobre del segment.
        """
        difY = self.v2.getY() - self.v1.getY()
        difX = self.v2.getX() - self.v1.getX()
        r = difX * (point.getY() - self.v1.getY()) - \
            difY * (point.getX() - self.v1.getX())
        return (r > 0)

    @staticmethod
    def calcInterseccio(digon1, digon2):
        """Calcula el punt de tall entre els dos segments.

        Args:
            digon1 (digon): Segment A
            digon2 (digon): Segment B
        Retorna:
            Fals si no hi ha intersecció o be
            un vertex on es tallen els dos segments.
        """
        determinant = digon1.A * digon2.B - digon2.A * digon1.B
        if determinant == 0:
            return False  # linies paral·leles

        else:
            x = (digon2.B * digon1.C - digon1.B * digon2.C) / determinant
            y = (digon1.A * digon2.C - digon2.A * digon1.C) / determinant
            if x == -0.000:
                x = 0.000
            if y == -0.000:
                y = 0.000
            lin1 = (min(digon1.v1.getX(), digon1.v2.getX())) <= x and \
                (max(digon1.v1.getX(), digon1.v2.getX()) >= x) and \
                (min(digon1.v1.getY(), digon1.v2.getY()) <= y) and \
                (max(digon1.v1.getY(), digon1.v2.getY()) >= y)

            lin2 = (min(digon2.v1.getX(), digon2.v2.getX())) <= x and \
                (max(digon2.v1.getX(), digon2.v2.getX()) >= x) and \
                (min(digon2.v1.getY(), digon2.v2.getY()) <= y) and \
                (max(digon2.v1.getY(), digon2.v2.getY()) >= y)
            if lin1 and lin2:
                return vertex(x, y)
            else:
                return False


class convexPolygon:
    """
    Representació d'un polígon convex. Els vertexs es respresenten en
    el sentit de les agulles del rellotge.
        p.points conté els punts inicials
        p.vertex conté els punts del poligon convèx.
    """

    def __init__(self, points, hull=True, color=[0, 0, 0], rnd=False, sort=True):

        if rnd:
            self.points = []
            for v in range(0, 100):
                vi = vertex(random(), random())
                self.points.append(vi)
        else:
            self.points = points    # punts inicials

        self.color = color   # default color is black

        if hull:
            self.vertexs = self.__quickHull(self.points)  # punts del poligon convex
        else:
            self.vertexs = self.points
            if sort:
                self.vertexs.sort()
        
        self.__calcMinMax()

    def __quickHull(self, llistaV):
        """[Implementació del algoritme de quickHull]
            https://en.wikipedia.org/wiki/Quickhull

        Args:
            llistaV (vertex): Llista d'objectes vertex

        Retorna:
            list: llistat del vèrtex que conformen el Hull, en l'ordre
            de les agulles del rellotge
        """
        if len(llistaV) < 2:
            return llistaV.copy()
        vertexs = llistaV.copy()
        result = list()

        # 1. vertex amb minima i màxima X
        def calcMaxMin(llistaV):
            vMax = llistaV[0]
            vMin = llistaV[0]
            for v in llistaV:
                if v.getX() < vMin.getX():
                    vMin = v
                if v.getX() > vMax.getX():
                    vMax = v
                elif (v.getX() == vMin.getX() and
                      v.getY() < vMin.getY()):  # mateixa X, menor Y
                    vMin = v
                elif (v.getX() == vMax.getX() and
                      v.getY() > vMax.getY()):  # mateixa X, major Y
                    vMax = v
            return (vMax, vMin)

        (vMin, vMax) = calcMaxMin(vertexs)

        # 2. dividir en dos: 1. puntos por encima de la recta
        # 2. puntos por debajo de la recta
        def calcDivisioPunts(segment, llistaVertex):
            """
            Retorna una llista amb els punts que
            n'hi han per sobre i per sota del segment.
            """
            up = list(filter(segment.estaPerSobreDigon, llistaVertex))
            return up

        d1 = digon(vMin, vMax)
        d2 = digon(vMax, vMin)
        vertexs.remove(vMax)
        vertexs.remove(vMin)

        subVertex1 = calcDivisioPunts(d1, vertexs)
        subVertex2 = calcDivisioPunts(d2, vertexs)

        # 3.Recursivament, tractar els dos subhulls
        def subHull(segment, llista):
            if(len(llista) != 0):
                # busquem el vertex mes llunyá
                mapDist = max(
                    map(lambda v: (segment.distanciaPuntDigon(v), v), llista))

                llista.remove(mapDist[1])
                puntC = mapDist[1]

                # print("add: ", puntC)
                d1 = digon(segment.v1, puntC)
                d2 = digon(puntC, segment.v2)

                subVertex11 = calcDivisioPunts(d1, llista)
                subVertex12 = calcDivisioPunts(d2, llista)
                # print("split1Up: ", subVertex11)
                # print("split1Up: ", subVertex12)

                # crides recursives:
                subHull(d1, subVertex11)
                result.append(puntC)  # l'afegim al resultat
                subHull(d2, subVertex12)

        result.append(vMin)
        subHull(d1, subVertex1)
        result.append(vMax)
        subHull(d2, subVertex2)

        return result

    def __calcMinMax(self):
        if(len(self.vertexs) > 0):
            self.maxPointX = self.minPointX = \
                self.maxPointY = self.minPointY = self.vertexs[0]
            for v in self.vertexs:
                if v.getX() < self.minPointX.getX():
                    self.minPointX = v
                if v.getX() == self.minPointX.getX() \
                   and v.getY() < self.minPointX.getY():
                    self.minPointX = v
                if v.getX() > self.maxPointX.getX():
                    self.maxPointX = v
                if v.getY() < self.minPointY.getY():
                    self.minPointY = v
                if v.getY() > self.maxPointY.getY():
                    self.maxPointY = v

    def __sortedList(self):
        i = self.vertexs.index(self.minPointX)  # Posicio del menorX
        sorted = self.vertexs[i:] + self.vertexs[0:i]  # Fem un cercle
        return sorted

    def __eq__(self, other):
        """
            Igualtat entre dos poligons convexos.
            Dos poligons convexos son iguals si tenen els mateixos vertexs.
        """
        if self.numVertexs() != other.numVertexs():
            return False
        else:
            return (self. __sortedList() == other. __sortedList())

    def __repr__(self):
        sorted = self.__sortedList()
        ret = ""
        for vert in sorted:
            ret += repr(vert)
        return ret

    def getVertexs(self):
        """Retorna els vertexs del poligon convex. Ordenats.

        """
        i = self.vertexs.index(self.minPointX)
        # Fem un cercle sobre la list
        sorted = self.vertexs[i:] + self.vertexs[0:i]
        return sorted

    def numVertexs(self):
        """[summary] Retorna el nombre de

        Retorna:
            [int]: Vertexs del poligon convex.
        """
        return len(self.vertexs)

    def perimetre(self):
        """
            Perimetre d'un poligon convex, suma la distància entre els punts.
            Un poligon amb 0 o 1 vèrtex té un perimetre de 0.00
            Retorna:
                [Float] que representa el perimetre del poligon.
        """
        if self.numVertexs() <= 1:
            return 0.000
        lista2 = self.vertexs[1:] + [self.vertexs[0]]
        perimetre = 0.000
        for vI1, vI2 in zip(self.vertexs, lista2):
            d = digon(vI1, vI2)
            dist = d.distanciaEuclidia()
            perimetre += dist
        return perimetre

    def area(self):
        """
            Calcula l'area del poligon convex.
            Es calcula amb la formula:
                A = (1/2) [x1y2 + x2y3 + x3y4 + ... + xny1] -
                [y1x2 + y2x3 + y3x4 + ... + ynx1]
                https://www.mathwords.com/a/area_convex_polygon.htm
            Retorna:
                Float : Area del poligon convex.
        """
        if self.numVertexs() <= 1:
            return 0.000
        coordX = list(map(lambda v: v.getX(), self.vertexs))
        coordY = list(map(lambda v: v.getY(), self.vertexs))
        coordXinv = coordX[1:] + [coordX[0]]
        coordYinv = coordY[1:] + [coordY[0]]
        suma = zip(coordX, coordYinv)
        resta = zip(coordXinv, coordY)

        S = 0
        R = 0
        for xy, yx in zip(suma, resta):
            S += (xy[0] * xy[1])
            R += (yx[0] * yx[1])

        return abs(0.5 * (S - R))

    def centroid(self):
        """
            Calcul del centroide d'un poligon convex.
                https://bell0bytes.eu/centroid-convex/

            Retorna:
                Vertex : Coordenades x, y del centroide.
        """
        if self.numVertexs() <= 1:
            return 0.000
        vertexList = self.vertexs
        centroidX = centroidY = 0.0
        det = 0.0
        nVertices = len(vertexList)
        for i, _ in enumerate(vertexList):
            if (i + 1 == nVertices):
                j = 0
            else:
                j = i + 1
            tmpDet = (vertexList[i].getX() * vertexList[j].getY() -
                      vertexList[j].getX() * vertexList[i].getY())
            det += tmpDet
            centroidX += (vertexList[i].getX() + vertexList[j].getX()) * tmpDet
            centroidY += (vertexList[i].getY() + vertexList[j].getY()) * tmpDet

        centroidX /= 3 * det
        centroidY /= 3 * det

        return (round(centroidX, 3), round(centroidY, 3))

    def esRegular(self):
        """Indica si el poligon convex es regular.
        Fa falta que totes les distàncies siguin igual.

        Retorna:
            bool: Indica si el poligon es regular
        """
        if self.numVertexs() <= 2:
            return True

        z = list(zip(self.vertexs, self.vertexs[1:] + [self.vertexs[0]]))
        dist1 = digon(z[0][0], z[0][1]).distanciaEuclidia()
        for vI1, vI2 in z:
            d = digon(vI1, vI2)
            distN = d.distanciaEuclidia()
            if dist1 != distN:
                return False
        return True

    def interseccioPoly(self, poly2):
        """Calcula la intersecció entre dos poligons.
            https://www.swtestacademy.com/intersection-convex-polygons-algorithm/
            El resultat es un nou poligon convex amb la
            intersecció dels dos poligons.

            Retorna:
                Retorna un nou poligon amb la intersecció de self i poly2.
        """
        # Cas Base: Un dintre del altre.
        if self.isInside(poly2):  # self dins->resultat es self
            return convexPolygon(self.vertexs)
        elif poly2.isInside(self):  # other dins->resultat es other
            return convexPolygon(poly2.vertexs)
        # Cas: Buscar punts de tall:
        else:
            # 1. Vertexs de self dins de poly2. -> l1
            l1 = []
            for v in self.vertexs:
                if poly2.puntDinsPerimetre(v):
                    l1.append(v)
            
            # 2. Vertexs se poly2 dins de self. -> l2
            l2 = []
            for v in poly2.vertexs:
                if self.puntDinsPerimetre(v):
                    l2.append(v)
            # 3. Vertexs de tall entre self i poly2:
            l3 = []
            for i1, vertex1 in enumerate(self.vertexs):
                if (i1 + 1 == len(self.vertexs)):
                    j1 = 0
                else:
                    j1 = i1 + 1
                # i actual, j següent ciclic (n, n+1) o (n-1, 0)
                dAux1 = digon(self.vertexs[i1], self.vertexs[j1])
                for i2, vertex2 in enumerate(poly2.vertexs):
                    if(i2 + 1 == len(poly2.vertexs)):
                        j2 = 0
                    else:
                        j2 = i2 + 1
                    dAux2 = digon(poly2.vertexs[i2], poly2.vertexs[j2])
                    r = digon.calcInterseccio(dAux1, dAux2)
                    if not isinstance(r, bool):
                        l3.append(r)
            lR = l1 + l2 + l3
            lRnorepetits = list(set(lR))
            return convexPolygon(lRnorepetits, hull=False)

    def convexUnion(self, poly2):
        """[summary] Operació que calcula el hull de dos poligons convexos.
        Equivalent a convex hull entre self i el poly2

        Args:
            poly2 ([convexPolygon]): Poligon per fer la convex union
        Retorna:
            Un poligon convex amb la convex union de self i poly2.
        """
        vertexs = self.vertexs + poly2.vertexs
        result = convexPolygon(vertexs)
        return result

    def boundingBox(self):
        """
            Retorna la capsa contenedora del poligon convex.

        Retorna:
            [Llista de vertexs] : Retorna els vertexs corresponents
            a la bounding Box, en ordre 
        """
        bLeftY = min(self.minPointX.getY(), self.minPointY.getY())
        bottomLeft = vertex(self.minPointX.getX(), bLeftY)
        tRightY = max(self.maxPointX.getY(), self.maxPointY.getY())
        topRight = vertex(self.maxPointX.getX(), tRightY)

        height = self.maxPointY.getY() - self.minPointY.getY()

        topLeft = vertex(self.minPointX.getX(), bLeftY + height)
        bottomRight = vertex(self.maxPointX.getX(), tRightY - height)
        return [bottomLeft, topLeft, topRight, bottomRight]

    def setColorsRGB(self, R, G, B):
        """Assigna un color RGB al poligon.

        Args:
            R (float): Component R del color.
            G (float): Component G del color.
            B (float): Component B del color.

        Raises:
            NameError: Si alguna component surt del rang [0, 1]
        """
        if(R > 1 or G > 1 or B > 1):
            raise NameError("Incorrect color numbers. Must be in [0, 1].")
        self.color = [R, G, B]

    def getColorsRGB(self):
        """Retorna els colors del poligon en format RGB.
        Els valors pertanyen al interval [0, 1].

        Retorna:
            Llista: Llista de tres camps amb els valors R, G, B.
        """
        R = self.color[0]
        G = self.color[1]
        B = self.color[2]
        return (R, G, B)

    def puntDinsPerimetre(self, punt):
        """
            puntDinsPerimetre(p) indica si el punt esta
            dins del poligon convex.
            Per definició, si el poligon té <= 2 vèrtex
            no pot contenir cap punt dins

        Args:
            point (vertex): Vertex amb coordenades x, y.

        Retorna:
            Bool: Indica si el punt p esta dins del poligon
        """
        if(self.numVertexs() <= 2):
            return False

        if (punt.getX() <= self.maxPointX.getX() and
                punt.getY() <= self.maxPointY.getY() and
                punt.getX() >= self.minPointX.getX() and
                punt.getY() >= self.minPointY.getY()):
            # Esta dins de la caixa, pero pot no estar dins del poligon:
            pos = 0
            neg = 0
            for i, vertex in enumerate(self.vertexs):
                if vertex == punt:
                    return True
                if i + 1 == len(self.vertexs):
                    j = 0
                else:
                    j = i + 1
                x1 = self.vertexs[i].getX()
                y1 = self.vertexs[i].getY()
                x2 = self.vertexs[j].getX()
                y2 = self.vertexs[j].getY()
                d = (punt.getX() - x1) * (y2 - y1) - \
                    (punt.getY() - y1) * (x2 - x1)

                if d > 0:
                    pos += 1
                if d < 0:
                    neg += 1

                if(pos > 0 and neg > 0):
                    return False
            return True

        else:
            return False

    def isInside(self, other):
        """Consulta si el poligon self esta dins del poligon v.
        Args:
            v (convexPolygon): convex poligon que volem saber si conte self.

        Retorna:
            Bool : Indica si el poligon esta dins del v.
        """
        for vertexI in self.vertexs:
            if not other.puntDinsPerimetre(vertexI):
                return False
        return True

    def transport(self, incX, incY):
        """Retorna una copia dels vertexs, aplicant una transformació
        de traslació als seus vertexs.

        Args:
            incX ([int/float]): increment en l'eix Y.
            incY ([int/float]): increment en l'eix Y.

        """
        for vert in self.vertexs:
            vert.traslacio(incX, incY)

    def scale(self, incX, incY):
        """Retorna una copia dels vertexs, aplicant una transformació
        de escalat als vertexs.

        Args:
            incX ([int/float]): increment en l'eix Y.
            incY ([int/float]): increment en l'eix Y.

        """
        self.vertexs = list(map(lambda v: v.escala(incX, incY), self.vertexs))
