import unittest
from antlr4 import *

import sys
sys.path.insert(0, '..')

from cl.poliLexer import poliLexer
from cl.poliParser import poliParser
from cl.myVisitor import *


inputTest = [
    "// sample script",
    "p1 := [0 0  0 1  1 1  0.2 0.8]",
    "color p1, {1 0 0}",
    "print p1",
    "area p1",
    "perimeter p1",
    "vertices p1",
    "centroid p1",
    "print \"---\" ",
    "p2 := [0 0  1 0  1 1]",
    "color p2, {0 1 0}",
    "print p2",
    "equal p1, p2",
    "inside p1, p2",
    "inside [0.8 0.2], p2",
    "draw \"image.png\", p1, p2",
    "print \"---\" ",
    "print p1 + p2                           // convex union",
    "print p1 * p2                           // intersection",
    "print #p2                               // bounding box",
    "equal p1 + p2, #p2                      // complex operations",
    "r := !100                               // convex polygon made with 100 random points",
]
outputExpect = [
    None,
    None,
    None,
    "0.000 0.000 0.000 1.000 1.000 1.000 ",
    "0.500",
    "3.410",
    "3",
    "0.333 0.667",
    "---",
    None,
    None,
    "0.000 0.000 1.000 1.000 1.000 0.000 ",
    "no",
    "no",
    "yes",
    "image.png",
    "---",
    "0.000 0.000 0.000 1.000 1.000 1.000 1.000 0.000 ",
    "0.000 0.000 1.000 1.000 ",
    "0.000 0.000 0.000 1.000 1.000 1.000 1.000 0.000 ",
    "yes",
    None
]

class Test(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        cls.comandes = iter(inputTest)
        cls.output = iter(outputExpect)
        cls.calc = myEval()

    def nextComand(self):
        input_stream = InputStream(next(self.comandes))
        lexer = poliLexer(input_stream)
        token_stream = CommonTokenStream(lexer)
        parser = poliParser(token_stream)
        tree = parser.root()
        #print('tree:', tree.toStringTree(recog=parser))
        print('result:', self.calc.visit(tree))
        return self.calc.visit(tree)    
        

    def test1(self):
        "Test script1"
        try:
            while True:
                result = self.nextComand()
                expected = next(self.output)
                if isinstance(result, str) and "()()****()()" in result:
                    print("image saved at", result)
                else: 
                    self.assertEqual(result, expected)
        except StopIteration:
            print("end commands")


if __name__ == '__main__':
    unittest.main(verbosity=4)
