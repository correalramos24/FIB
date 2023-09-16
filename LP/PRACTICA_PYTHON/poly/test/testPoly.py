
import sys
sys.path.insert(0, '..')
from polygons import *
from printPoly import *


v1 = [vertex(0, 0), vertex(1, 0), vertex(1, 1)]
v2 = [vertex(0, 0), vertex(0, 1), vertex(1, 1),
      vertex(0.2, 0.8)]


d1 = convexPolygon(v1)
d2 = convexPolygon(v2)

d1.setColorsRGB(1, 0, 0)
d2.setColorsRGB(0, 1, 0)

printAndSave([d1], show=False, fileName="pol1.png")
printAndSave([d2], show=False, fileName="pol2.png")
printAndSave([d1, d2], show=True, fileName="pol12.png")
