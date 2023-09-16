import sys
from antlr4 import *
from ExprLexer import ExprLexer
from ExprParser import ExprParser
from TreeVisitor import TreeVisitor, EvalVisitor


while True:
    input_stream = InputStream(input('> '))

    lexer = ExprLexer(input_stream)
    token_stream = CommonTokenStream(lexer)
    parser = ExprParser(token_stream)
    tree = parser.root() 

    eval = EvalVisitor()
    eval.visit(tree)