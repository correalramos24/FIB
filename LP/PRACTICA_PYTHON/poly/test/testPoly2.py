

import sys
sys.path.insert(0, '..')

from printPoly import *
from polygons import *
import unittest



class Test(unittest.TestCase):

    def test01(self):
        """Convex Hull & representacio test"""
        p1 = convexPolygon([vertex(2, 3), vertex(5, 5), vertex(8, 4),
                            vertex(2, 5), vertex(2, 9), vertex(5, 6),
                            vertex(8, 1)])
        # Result vertexs -> (2,3) (2,9) (8,4) (8,1)
        self.assertEqual(p1.numVertexs(), 4)
        self.assertEqual(
            repr(p1), "2.000 3.000 2.000 9.000 8.000 4.000 8.000 1.000 ")

        p2 = convexPolygon([vertex(0, 0), vertex(
            0, 1), vertex(1, 1), vertex(0.2, 0.8)])
        self.assertEqual(p2.numVertexs(), 3)
        self.assertEqual(repr(p2), "0.000 0.000 0.000 1.000 1.000 1.000 ")

    def test02(self):
        """Test isInside"""
        p1 = convexPolygon([vertex(0.1, 0.7), vertex(0.2, 0.8)])
        p2 = convexPolygon([vertex(0, 0), vertex(0, 1),
                            vertex(1, 1), vertex(0.2, 0.8)])
        p3 = convexPolygon([vertex(0, 0), vertex(0, 2), vertex(2, 2)])
        self.assertFalse(p2.isInside(p1))
        self.assertTrue(p1.isInside(p2))
        self.assertTrue(p2.isInside(p3))
        self.assertFalse(p3.isInside(p2))

    def test03(self):
        """Test consultes"""
        p1 = convexPolygon([vertex(1, 0), vertex(2, 1), vertex(
            0, 3), vertex(-1, 2), vertex(-2, -1)])
        self.assertEqual(p1.centroid()[0], -0.083)
        self.assertEqual(p1.centroid()[1], 0.917)

        p2 = convexPolygon([vertex(0, 0), vertex(
            0, 1), vertex(1, 1), vertex(0.2, 0.8)])
        self.assertEqual(p2.getVertexs(), [vertex(
            0, 0), vertex(0, 1), vertex(1, 1)])
        self.assertEqual(p2.area(), 0.500)
        self.assertEqual(p2.perimetre(), 3.41)
        self.assertFalse(p2.esRegular())

        p3 = convexPolygon([])
        self.assertEqual(p3.area(), 0.000)
        self.assertEqual(p3.perimetre(), 0.000)
        self.assertEqual(p3.centroid(), 0.000)
        self.assertTrue(p3.esRegular())

    def test04(self):
        """Test Convex Union"""
        p1 = convexPolygon([vertex(2, 3), vertex(5, 5), vertex(8, 4),
                            vertex(2, 5), vertex(2, 9), vertex(5, 6),
                            vertex(8, 1)])
        # Result vertexs -> (2,3) (2,9) (8,4) (8,1)
        p2 = convexPolygon([vertex(0, 0), vertex(0, 1),
                            vertex(1, 1), vertex(0.2, 0.8)])
        p12 = p1.convexUnion(p2)
        p21 = p2.convexUnion(p1)
        self.assertEqual(p12, p21)

    def test05(self):
        """Test interseccio poly -> No buida"""
        p3 = convexPolygon([vertex(-4, 3), vertex(2, 5), vertex(5, 1)])
        p4 = convexPolygon([vertex(0, 0), vertex(2, 2),
                            vertex(0, 2), vertex(2, 0)])
        p34 = p3.interseccioPoly(p4)
        p43 = p4.interseccioPoly(p3)
        self.assertEqual(p34.vertexs, p43.vertexs)

    def test06(self):
        """Test interseccio poly -> Buida"""
        p1 = convexPolygon([vertex(2, 3), vertex(5, 5), vertex(8, 4),
                            vertex(2, 5), vertex(2, 9), vertex(5, 6),
                            vertex(8, 1)])
        p5 = convexPolygon([vertex(1, 0), vertex(2, 1), vertex(0, 3),
                            vertex(-1, 2), vertex(-2, -1)])
        p15 = p1.interseccioPoly(p5)
        self.assertEqual(p15.numVertexs(), 0)

    def test07(self):
        """Test interseccio poly -> Moltes interseccions"""
        p1 = convexPolygon([vertex(0, 0), vertex(0, 1),
                            vertex(1, 1), vertex(0.2, 0.8)])
        p2 = convexPolygon([vertex(0, 0), vertex(1, 0),
                            vertex(1, 1)])
        p12 = p1.interseccioPoly(p2)
        self.assertEqual(p12.vertexs,[vertex(0,0), vertex(1,1)])

    def test08(self):
        """Equal polygons Test"""
        p1 = convexPolygon([vertex(0, 0), vertex(
            0, 1), vertex(1, 1), vertex(0.2, 0.8)])
        p2 = convexPolygon([vertex(0, 0), vertex(0, 1), vertex(
            1, 1), vertex(0.2, 0.8), vertex(0.4, 0.4)])
        p3 = convexPolygon([vertex(4, 3), vertex(2, 5), vertex(5, 1)])
        self.assertTrue(p1 == p2)
        self.assertTrue(p2 == p1)
        self.assertTrue(p1 != p3)

    def test09(self):
        """Test boundingBox"""
        p1 = convexPolygon([vertex(0, 0), vertex(0, 1),
                            vertex(1, 1), vertex(0.2, 0.8)])
        self.assertTrue(p1.boundingBox(), [vertex(0,0), 
                            vertex(0, 1), vertex(1, 1), 
                            vertex(1, 0)])

        p2 = convexPolygon([vertex(0, 0), vertex(1, 0),
                            vertex(1, 1)])
        self.assertTrue(p2.boundingBox(), [vertex(0,0), 
                            vertex(0, 1), vertex(1, 1), 
                            vertex(1, 0)])
        
if __name__ == '__main__':
    unittest.main(verbosity=4)
