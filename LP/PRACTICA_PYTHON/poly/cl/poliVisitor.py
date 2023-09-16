# Generated from poli.g4 by ANTLR 4.7.1
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .poliParser import poliParser
else:
    from poliParser import poliParser

# This class defines a complete generic visitor for a parse tree produced by poliParser.

class poliVisitor(ParseTreeVisitor):

    # Visit a parse tree produced by poliParser#root.
    def visitRoot(self, ctx:poliParser.RootContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#poly.
    def visitPoly(self, ctx:poliParser.PolyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#vertex.
    def visitVertex(self, ctx:poliParser.VertexContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#vertexList.
    def visitVertexList(self, ctx:poliParser.VertexListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#colorList.
    def visitColorList(self, ctx:poliParser.ColorListContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#newPoly.
    def visitNewPoly(self, ctx:poliParser.NewPolyContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#assignColor.
    def visitAssignColor(self, ctx:poliParser.AssignColorContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#consultaPOLY.
    def visitConsultaPOLY(self, ctx:poliParser.ConsultaPOLYContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#printMSG.
    def visitPrintMSG(self, ctx:poliParser.PrintMSGContext):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#operacionsPOLY1.
    def visitOperacionsPOLY1(self, ctx:poliParser.OperacionsPOLY1Context):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#operacionsPOLY2.
    def visitOperacionsPOLY2(self, ctx:poliParser.OperacionsPOLY2Context):
        return self.visitChildren(ctx)


    # Visit a parse tree produced by poliParser#polyID.
    def visitPolyID(self, ctx:poliParser.PolyIDContext):
        return self.visitChildren(ctx)



del poliParser