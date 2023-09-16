

import sys
sys.path.insert(0, '..')
from cl.myVisitor import *
from cl.poliParser import poliParser
from cl.poliLexer import poliLexer

from antlr4 import *


calc = myEval()

while True:
    input_stream = InputStream(input('> '))
    lexer = poliLexer(input_stream)

    token_stream = CommonTokenStream(lexer)
    parser = poliParser(token_stream)

    tree = parser.root()

    print('tree:')
    print(tree.toStringTree(recog=parser))

    print('evaluate:')
    result = calc.visit(tree)
    print(result)
