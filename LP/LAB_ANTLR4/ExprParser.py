# Generated from Expr.g by ANTLR 4.7.1
# encoding: utf-8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys

def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16")
        buf.write("F\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2")
        buf.write("\3\2\7\2\21\n\2\f\2\16\2\24\13\2\3\2\3\2\7\2\30\n\2\f")
        buf.write("\2\16\2\33\13\2\3\2\5\2\36\n\2\3\3\3\3\3\3\3\3\3\3\3\3")
        buf.write("\5\3&\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\6")
        buf.write("\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3")
        buf.write("\6\7\6A\n\6\f\6\16\6D\13\6\3\6\2\3\n\7\2\4\6\b\n\2\2\2")
        buf.write("J\2\35\3\2\2\2\4%\3\2\2\2\6\'\3\2\2\2\b+\3\2\2\2\n.\3")
        buf.write("\2\2\2\f\r\5\4\3\2\r\16\7\2\2\3\16\36\3\2\2\2\17\21\5")
        buf.write("\4\3\2\20\17\3\2\2\2\21\24\3\2\2\2\22\20\3\2\2\2\22\23")
        buf.write("\3\2\2\2\23\25\3\2\2\2\24\22\3\2\2\2\25\36\7\2\2\3\26")
        buf.write("\30\5\4\3\2\27\26\3\2\2\2\30\33\3\2\2\2\31\27\3\2\2\2")
        buf.write("\31\32\3\2\2\2\32\34\3\2\2\2\33\31\3\2\2\2\34\36\7\f\2")
        buf.write("\2\35\f\3\2\2\2\35\22\3\2\2\2\35\31\3\2\2\2\36\3\3\2\2")
        buf.write("\2\37 \5\6\4\2 !\7\f\2\2!&\3\2\2\2\"#\5\n\6\2#$\7\f\2")
        buf.write("\2$&\3\2\2\2%\37\3\2\2\2%\"\3\2\2\2&\5\3\2\2\2\'(\7\13")
        buf.write("\2\2()\7\t\2\2)*\5\n\6\2*\7\3\2\2\2+,\7\n\2\2,-\7\13\2")
        buf.write("\2-\t\3\2\2\2./\b\6\1\2/\60\7\3\2\2\60B\3\2\2\2\61\62")
        buf.write("\f\b\2\2\62\63\7\b\2\2\63A\5\n\6\b\64\65\f\7\2\2\65\66")
        buf.write("\7\6\2\2\66A\5\n\6\b\678\f\6\2\289\7\7\2\29A\5\n\6\7:")
        buf.write(";\f\5\2\2;<\7\4\2\2<A\5\n\6\6=>\f\4\2\2>?\7\5\2\2?A\5")
        buf.write("\n\6\5@\61\3\2\2\2@\64\3\2\2\2@\67\3\2\2\2@:\3\2\2\2@")
        buf.write("=\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\13\3\2\2\2DB")
        buf.write("\3\2\2\2\b\22\31\35%@B")
        return buf.getvalue()


class ExprParser ( Parser ):

    grammarFileName = "Expr.g"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "<INVALID>", "'+'", "'-'", "'*'", "'/'", 
                     "'^'", "':='", "'write'" ]

    symbolicNames = [ "<INVALID>", "NUM", "MES", "RESTA", "MULT", "DIVISIO", 
                      "POW", "ASSIGN", "WRITE", "ID", "ENDLINE", "WS", "COMMENT" ]

    RULE_root = 0
    RULE_comanda = 1
    RULE_newVar = 2
    RULE_write = 3
    RULE_expr = 4

    ruleNames =  [ "root", "comanda", "newVar", "write", "expr" ]

    EOF = Token.EOF
    NUM=1
    MES=2
    RESTA=3
    MULT=4
    DIVISIO=5
    POW=6
    ASSIGN=7
    WRITE=8
    ID=9
    ENDLINE=10
    WS=11
    COMMENT=12

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7.1")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None



    class RootContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def comanda(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(ExprParser.ComandaContext)
            else:
                return self.getTypedRuleContext(ExprParser.ComandaContext,i)


        def EOF(self):
            return self.getToken(ExprParser.EOF, 0)

        def ENDLINE(self):
            return self.getToken(ExprParser.ENDLINE, 0)

        def getRuleIndex(self):
            return ExprParser.RULE_root

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitRoot" ):
                return visitor.visitRoot(self)
            else:
                return visitor.visitChildren(self)




    def root(self):

        localctx = ExprParser.RootContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_root)
        self._la = 0 # Token type
        try:
            self.state = 27
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,2,self._ctx)
            if la_ == 1:
                self.enterOuterAlt(localctx, 1)
                self.state = 10
                self.comanda()
                self.state = 11
                self.match(ExprParser.EOF)
                pass

            elif la_ == 2:
                self.enterOuterAlt(localctx, 2)
                self.state = 16
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==ExprParser.NUM or _la==ExprParser.ID:
                    self.state = 13
                    self.comanda()
                    self.state = 18
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                self.state = 19
                self.match(ExprParser.EOF)
                pass

            elif la_ == 3:
                self.enterOuterAlt(localctx, 3)
                self.state = 23
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                while _la==ExprParser.NUM or _la==ExprParser.ID:
                    self.state = 20
                    self.comanda()
                    self.state = 25
                    self._errHandler.sync(self)
                    _la = self._input.LA(1)

                self.state = 26
                self.match(ExprParser.ENDLINE)
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class ComandaContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def newVar(self):
            return self.getTypedRuleContext(ExprParser.NewVarContext,0)


        def ENDLINE(self):
            return self.getToken(ExprParser.ENDLINE, 0)

        def expr(self):
            return self.getTypedRuleContext(ExprParser.ExprContext,0)


        def getRuleIndex(self):
            return ExprParser.RULE_comanda

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitComanda" ):
                return visitor.visitComanda(self)
            else:
                return visitor.visitChildren(self)




    def comanda(self):

        localctx = ExprParser.ComandaContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_comanda)
        try:
            self.state = 35
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [ExprParser.ID]:
                self.enterOuterAlt(localctx, 1)
                self.state = 29
                self.newVar()
                self.state = 30
                self.match(ExprParser.ENDLINE)
                pass
            elif token in [ExprParser.NUM]:
                self.enterOuterAlt(localctx, 2)
                self.state = 32
                self.expr(0)
                self.state = 33
                self.match(ExprParser.ENDLINE)
                pass
            else:
                raise NoViableAltException(self)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class NewVarContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def ID(self):
            return self.getToken(ExprParser.ID, 0)

        def ASSIGN(self):
            return self.getToken(ExprParser.ASSIGN, 0)

        def expr(self):
            return self.getTypedRuleContext(ExprParser.ExprContext,0)


        def getRuleIndex(self):
            return ExprParser.RULE_newVar

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitNewVar" ):
                return visitor.visitNewVar(self)
            else:
                return visitor.visitChildren(self)




    def newVar(self):

        localctx = ExprParser.NewVarContext(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_newVar)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 37
            self.match(ExprParser.ID)
            self.state = 38
            self.match(ExprParser.ASSIGN)
            self.state = 39
            self.expr(0)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class WriteContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def WRITE(self):
            return self.getToken(ExprParser.WRITE, 0)

        def ID(self):
            return self.getToken(ExprParser.ID, 0)

        def getRuleIndex(self):
            return ExprParser.RULE_write

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitWrite" ):
                return visitor.visitWrite(self)
            else:
                return visitor.visitChildren(self)




    def write(self):

        localctx = ExprParser.WriteContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_write)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 41
            self.match(ExprParser.WRITE)
            self.state = 42
            self.match(ExprParser.ID)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx

    class ExprContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def NUM(self):
            return self.getToken(ExprParser.NUM, 0)

        def expr(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(ExprParser.ExprContext)
            else:
                return self.getTypedRuleContext(ExprParser.ExprContext,i)


        def POW(self):
            return self.getToken(ExprParser.POW, 0)

        def MULT(self):
            return self.getToken(ExprParser.MULT, 0)

        def DIVISIO(self):
            return self.getToken(ExprParser.DIVISIO, 0)

        def MES(self):
            return self.getToken(ExprParser.MES, 0)

        def RESTA(self):
            return self.getToken(ExprParser.RESTA, 0)

        def getRuleIndex(self):
            return ExprParser.RULE_expr

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitExpr" ):
                return visitor.visitExpr(self)
            else:
                return visitor.visitChildren(self)



    def expr(self, _p:int=0):
        _parentctx = self._ctx
        _parentState = self.state
        localctx = ExprParser.ExprContext(self, self._ctx, _parentState)
        _prevctx = localctx
        _startState = 8
        self.enterRecursionRule(localctx, 8, self.RULE_expr, _p)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 45
            self.match(ExprParser.NUM)
            self._ctx.stop = self._input.LT(-1)
            self.state = 64
            self._errHandler.sync(self)
            _alt = self._interp.adaptivePredict(self._input,5,self._ctx)
            while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                if _alt==1:
                    if self._parseListeners is not None:
                        self.triggerExitRuleEvent()
                    _prevctx = localctx
                    self.state = 62
                    self._errHandler.sync(self)
                    la_ = self._interp.adaptivePredict(self._input,4,self._ctx)
                    if la_ == 1:
                        localctx = ExprParser.ExprContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expr)
                        self.state = 47
                        if not self.precpred(self._ctx, 6):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 6)")
                        self.state = 48
                        self.match(ExprParser.POW)
                        self.state = 49
                        self.expr(6)
                        pass

                    elif la_ == 2:
                        localctx = ExprParser.ExprContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expr)
                        self.state = 50
                        if not self.precpred(self._ctx, 5):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 5)")
                        self.state = 51
                        self.match(ExprParser.MULT)
                        self.state = 52
                        self.expr(6)
                        pass

                    elif la_ == 3:
                        localctx = ExprParser.ExprContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expr)
                        self.state = 53
                        if not self.precpred(self._ctx, 4):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 4)")
                        self.state = 54
                        self.match(ExprParser.DIVISIO)
                        self.state = 55
                        self.expr(5)
                        pass

                    elif la_ == 4:
                        localctx = ExprParser.ExprContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expr)
                        self.state = 56
                        if not self.precpred(self._ctx, 3):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 3)")
                        self.state = 57
                        self.match(ExprParser.MES)
                        self.state = 58
                        self.expr(4)
                        pass

                    elif la_ == 5:
                        localctx = ExprParser.ExprContext(self, _parentctx, _parentState)
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_expr)
                        self.state = 59
                        if not self.precpred(self._ctx, 2):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 2)")
                        self.state = 60
                        self.match(ExprParser.RESTA)
                        self.state = 61
                        self.expr(3)
                        pass

             
                self.state = 66
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,5,self._ctx)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.unrollRecursionContexts(_parentctx)
        return localctx



    def sempred(self, localctx:RuleContext, ruleIndex:int, predIndex:int):
        if self._predicates == None:
            self._predicates = dict()
        self._predicates[4] = self.expr_sempred
        pred = self._predicates.get(ruleIndex, None)
        if pred is None:
            raise Exception("No predicate with index:" + str(ruleIndex))
        else:
            return pred(localctx, predIndex)

    def expr_sempred(self, localctx:ExprContext, predIndex:int):
            if predIndex == 0:
                return self.precpred(self._ctx, 6)
         

            if predIndex == 1:
                return self.precpred(self._ctx, 5)
         

            if predIndex == 2:
                return self.precpred(self._ctx, 4)
         

            if predIndex == 3:
                return self.precpred(self._ctx, 3)
         

            if predIndex == 4:
                return self.precpred(self._ctx, 2)
         




