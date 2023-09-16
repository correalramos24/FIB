if __name__ is not None and "." in __name__:
    from .poliParser import poliParser
    from .poliVisitor import poliVisitor
    from .poliLexer import poliLexer

else:
    from poliParser import poliParser
    from poliVisitor import poliVisitor
    from poliLexer import poliLexer

from antlr4 import *

import sys
sys.path.insert(0, '..')
from polygons import *
from printPoly import *

# Constants per llegir imatge:
CNSTGETIMG = "()()****()()"


def str2parse(calc, comanda: str):
    """Funció per convertir la comanda string a tokens i aplicar
    el parser.
    """
    input_stream = InputStream(comanda)
    lexer = poliLexer(input_stream)
    token_stream = CommonTokenStream(lexer)
    parser = poliParser(token_stream)
    tree = parser.root()
    return calc.visit(tree)


def getPathIMG(result):
    if CNSTGETIMG in result:
        return result.replace(CNSTGETIMG, '')
    else:
        return None


class myEval(poliVisitor):
    """Classe per evaluar les expressions definides a poli.g4
    Depenent de la crida, retornem un valor
    """

    def __init__(self):
        self.vars = {}  # Inicializem les variables a buit.

    @staticmethod
    def __formatFloat(resultat):
        """Format de tres decimals a la sortida.
        """
        return "{:.3f}".format(resultat)

    @staticmethod
    def __formatBool(resultat):
        """Format de si/no a la sortida.
        """
        if resultat:
            return "yes"
        else:
            return "no"

    def visitRoot(self, ctx: poliParser.RootContext):
        """Punt inicial de la visita a una expressió.

        Retorna:
            Visita al fills de l'expressió.
        """
        n = next(ctx.getChildren())
        return self.visit(n)

    def visitVertex(self, ctx: poliParser.VertexContext):
        """ Visita un vèrtex amb coordenades X, Y.

        Retorna:
            vertex: Vertex corresponent a les coordenades.
        """
        c = [n for n in ctx.getChildren()]
        strX = c[0].getText()
        strY = c[1].getText()

        coordX = float(strX)
        coordY = float(strY)

        return vertex(coordX, coordY)

    def visitVertexList(self, ctx: poliParser.VertexListContext):
        """Visita una llista de vertexs amb format:
            [x y x' y' x'' y'' ...]

        Retorna:
            convexPolygon: Poligon convex amb
            la llista de objectes vertex.
        """
        c = [n for n in ctx.getChildren()]
        c.pop(0)
        c.pop(-1)
        lV = []
        for vertex in c:
            lV.append(self.visit(vertex))

        return convexPolygon(lV)

    def visitNewPoly(self, ctx: poliParser.NewPolyContext):
        """Registre d'un poligon nou.

        Returns:
        [String] : Format <id> <llista vertexs convex hull>
        """
        c = [n for n in ctx.getChildren()]
        newID = c[0].getText()
        pol = self.visit(c[2])
        self.vars[newID] = pol
        return None

    def visitPolyID(self, ctx: poliParser.PolyIDContext):
        """Cerca d'identificadors de poligon.

        Raises:
            NameError: l'identificador no existeix.

        Retorna:
            convexPolygon: Retorna el poligon corresponent a la variable.
        """
        nodeID = next(ctx.getChildren()).getText()

        if nodeID in self.vars:
            return self.vars[nodeID]
        else:
            raise NameError('Variable {} not defined!'.format(nodeID))

    def visitOperacionsPOLY1(self, ctx: poliParser.OperacionsPOLY1Context):
        """Visita a les operacions amb format:
            #|! poly|INT

        Retorna:
            convexPolygon: poligon corresponent a la operació realitzada.
        """
        c = [n for n in ctx.getChildren()]
        operador = c[0].getSymbol().type
        if operador == poliParser.OPERATORBOUNDINGBOX:
            pol = self.visit(c[1])      # poligon a operar
            polBB = pol.boundingBox()
            return convexPolygon(polBB, hull=False, sort=False)

        elif operador == poliParser.OPERATORRANDOMPOLY:
            nV = int(c[1].getText())    # numero de vertexs
            return convexPolygon(nV, rnd=True)

    def visitOperacionsPOLY2(self, ctx: poliParser.OperacionsPOLY2Context):
        """Visita a les operacions amb format:
            poly1 *|+ poly2

        Retorna:
            [convexPolygon]: poligon corresponent a la operació realitzada.
        """
        c = [n for n in ctx.getChildren()]
        operador = c[1].getSymbol().type
        pol1 = self.visit(c[0])
        pol2 = self.visit(c[2])
        if operador == poliParser.OPERATORUNION:  # '+'
            return pol1.convexUnion(pol2)
        elif operador == poliParser.OPERATORINTER:  # '*'
            return pol1.interseccioPoly(pol2)

    def visitConsultaPOLY(self, ctx: poliParser.ConsultaPOLYContext):
        """Visita a les consultes per un poligon. Trobem els formats:
            1) area|print|perimeter|centroid|vertices poly
            2) inside|equal pol1, pol2
            3) draw <fName> poligonList

        Retorna per les consultes 1) 2):
            [String]: String amb el resultat de la consulta.
        Retorna per la consulta 3)
            [String]: String amb el filename.
        """
        c = [n for n in ctx.getChildren()]
        operador = c[0].getSymbol().type
        if operador == poliParser.CMDAREA:
            pol = self.visit(c[1])
            return myEval.__formatFloat(pol.area())

        elif operador == poliParser.CMDPERIMETRE:
            pol = self.visit(c[1])
            return myEval.__formatFloat(pol.perimetre())

        elif operador == poliParser.CMDVERTICES:
            pol = self.visit(c[1])
            return str(pol.numVertexs())

        elif operador == poliParser.CMDCENTROID:
            pol = self.visit(c[1])
            return myEval.__formatFloat(pol.centroid()[0]) + " " + myEval.__formatFloat(pol.centroid()[1])

        elif operador == poliParser.CMDPRINT:
            pol = self.visit(c[1])
            result = str(pol)
            result.replace('[', '')
            result.replace(']', '')
            return result

        elif operador == poliParser.CMDEQ:
            pol1 = self.visit(c[1])
            pol2 = self.visit(c[3])
            return myEval.__formatBool(pol1 == pol2)

        elif operador == poliParser.CMDINSIDE:
            pol1 = self.visit(c[1])
            pol2 = self.visit(c[3])
            return myEval.__formatBool(pol1.isInside(pol2))

        elif operador == poliParser.CMDDRAW:
            fName = c[1].getText().replace('"', '')
            polList = [self.visit(c[3])]  # visitar primer polygon
            for i in range(4, len(c)):
                if c[i].getText() != ',':
                    polI = self.visit(c[i])  # i-s poligon
                    polList.append(polI)
            
            absPath = printAndSave(polList, fileName=fName)
            return CNSTGETIMG+absPath

    def visitPrintMSG(self, ctx: poliParser.PrintMSGContext):
        """Visita una operació print

        Retorna:
            String: Missatge a escriure
        """
        c = [n for n in ctx.getChildren()]
        text = c[1].getText()
        return (text.replace('"', ''))

    def visitColorList(self, ctx: poliParser.ColorListContext):
        """Construcció d'una llista de colors.

        Raises:
            NameError: Si alguna de les components supera el limit (1.0)

        Retorna:
            Llista int: Llista de 3 elements, corresponents a RGB.
        """
        c = [n for n in ctx.getChildren()]
        R = float(c[1].getText())
        G = float(c[2].getText())
        B = float(c[3].getText())
        if(R > 1.0 or G > 1.0 or B > 1.0):
            raise NameError('Incorrect color {}. The \
                            value must be in [0,1]'.format([R, G, B]))
        return [R, G, B]

    def visitAssignColor(self, ctx: poliParser.AssignColorContext):
        """Visita assignació de color per un poligon.

        Retorna:
            String: String de confirmació.
        """
        c = [n for n in ctx.getChildren()]
        poly = self.visit(c[1])
        colorList = self.visit(c[3])  # c[2] -> ','
        poly.setColorsRGB(colorList[0], colorList[1], colorList[2])
        return None
