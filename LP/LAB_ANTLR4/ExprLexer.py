# Generated from Expr.g by ANTLR 4.7.1
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\16")
        buf.write("d\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7")
        buf.write("\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\3\2")
        buf.write("\6\2\35\n\2\r\2\16\2\36\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3")
        buf.write("\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\6\n")
        buf.write("\65\n\n\r\n\16\n\66\3\13\3\13\6\13;\n\13\r\13\16\13<\3")
        buf.write("\13\3\13\6\13A\n\13\r\13\16\13B\3\13\6\13F\n\13\r\13\16")
        buf.write("\13G\3\13\6\13K\n\13\r\13\16\13L\5\13O\n\13\3\f\6\fR\n")
        buf.write("\f\r\f\16\fS\3\f\3\f\3\r\3\r\3\r\3\r\7\r\\\n\r\f\r\16")
        buf.write("\r_\13\r\3\r\3\r\3\r\3\r\2\2\16\3\3\5\4\7\5\t\6\13\7\r")
        buf.write("\b\17\t\21\n\23\13\25\f\27\r\31\16\3\2\6\3\2\62;\4\2C")
        buf.write("\\c|\4\2\13\13\"\"\4\2\f\f\17\17\2n\2\3\3\2\2\2\2\5\3")
        buf.write("\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2")
        buf.write("\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2")
        buf.write("\2\27\3\2\2\2\2\31\3\2\2\2\3\34\3\2\2\2\5 \3\2\2\2\7\"")
        buf.write("\3\2\2\2\t$\3\2\2\2\13&\3\2\2\2\r(\3\2\2\2\17*\3\2\2\2")
        buf.write("\21-\3\2\2\2\23\64\3\2\2\2\25N\3\2\2\2\27Q\3\2\2\2\31")
        buf.write("W\3\2\2\2\33\35\t\2\2\2\34\33\3\2\2\2\35\36\3\2\2\2\36")
        buf.write("\34\3\2\2\2\36\37\3\2\2\2\37\4\3\2\2\2 !\7-\2\2!\6\3\2")
        buf.write("\2\2\"#\7/\2\2#\b\3\2\2\2$%\7,\2\2%\n\3\2\2\2&\'\7\61")
        buf.write("\2\2\'\f\3\2\2\2()\7`\2\2)\16\3\2\2\2*+\7<\2\2+,\7?\2")
        buf.write("\2,\20\3\2\2\2-.\7y\2\2./\7t\2\2/\60\7k\2\2\60\61\7v\2")
        buf.write("\2\61\62\7g\2\2\62\22\3\2\2\2\63\65\t\3\2\2\64\63\3\2")
        buf.write("\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67\24\3")
        buf.write("\2\2\289\7\17\2\29;\7\f\2\2:8\3\2\2\2;<\3\2\2\2<:\3\2")
        buf.write("\2\2<=\3\2\2\2=O\3\2\2\2>?\7\f\2\2?A\7\17\2\2@>\3\2\2")
        buf.write("\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2CO\3\2\2\2DF\7\f\2\2E")
        buf.write("D\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HO\3\2\2\2IK\7")
        buf.write("\17\2\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2")
        buf.write("\2\2N:\3\2\2\2N@\3\2\2\2NE\3\2\2\2NJ\3\2\2\2O\26\3\2\2")
        buf.write("\2PR\t\4\2\2QP\3\2\2\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2T")
        buf.write("U\3\2\2\2UV\b\f\2\2V\30\3\2\2\2WX\7\61\2\2XY\7\61\2\2")
        buf.write("Y]\3\2\2\2Z\\\n\5\2\2[Z\3\2\2\2\\_\3\2\2\2][\3\2\2\2]")
        buf.write("^\3\2\2\2^`\3\2\2\2_]\3\2\2\2`a\5\25\13\2ab\3\2\2\2bc")
        buf.write("\b\r\2\2c\32\3\2\2\2\f\2\36\66<BGLNS]\3\b\2\2")
        return buf.getvalue()


class ExprLexer(Lexer):

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    NUM = 1
    MES = 2
    RESTA = 3
    MULT = 4
    DIVISIO = 5
    POW = 6
    ASSIGN = 7
    WRITE = 8
    ID = 9
    ENDLINE = 10
    WS = 11
    COMMENT = 12

    channelNames = [ u"DEFAULT_TOKEN_CHANNEL", u"HIDDEN" ]

    modeNames = [ "DEFAULT_MODE" ]

    literalNames = [ "<INVALID>",
            "'+'", "'-'", "'*'", "'/'", "'^'", "':='", "'write'" ]

    symbolicNames = [ "<INVALID>",
            "NUM", "MES", "RESTA", "MULT", "DIVISIO", "POW", "ASSIGN", "WRITE", 
            "ID", "ENDLINE", "WS", "COMMENT" ]

    ruleNames = [ "NUM", "MES", "RESTA", "MULT", "DIVISIO", "POW", "ASSIGN", 
                  "WRITE", "ID", "ENDLINE", "WS", "COMMENT" ]

    grammarFileName = "Expr.g"

    def __init__(self, input=None, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.7.1")
        self._interp = LexerATNSimulator(self, self.atn, self.decisionsToDFA, PredictionContextCache())
        self._actions = None
        self._predicates = None


