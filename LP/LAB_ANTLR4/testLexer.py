import sys
from antlr4 import *
from ExprLexer import ExprLexer
from ExprParser import ExprParser
import os

"""
Test del parser.
"""

os.system('antlr4 -Dlanguage=Python3 -no-listener -visitor Expr.g')

input_stream = FileStream('test.txt') 
lexer = ExprLexer(input_stream)
token_stream = CommonTokenStream(lexer)
parser = ExprParser(token_stream)
tree = parser.root()
print(tree.toStringTree(recog=parser))