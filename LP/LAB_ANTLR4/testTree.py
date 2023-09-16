import sys
from antlr4 import *
from ExprLexer import ExprLexer
from ExprParser import ExprParser
from TreeVisitor import TreeVisitor

import os

"""
Crear un tree visitor.
"""
print(os.system('antlr4 -Dlanguage=Python3 -no-listener -visitor Expr.g'))
while True:
    input_stream = InputStream(input('> '))

    lexer = ExprLexer(input_stream)
    token_stream = CommonTokenStream(lexer)
    parser = ExprParser(token_stream)
    tree = parser.root() 

    visitor = TreeVisitor()
    visitor.visit(tree)