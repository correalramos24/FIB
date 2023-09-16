*Comments*

Two bars (`//`) introduce a comment up to the end of the line.


*Polygon identifiers*

Identifiers are as usual:
`p`, `Q`, `p1`, `p2`, `pol_gr`, ...


*Points and polygons*

Points in the commands are given by two pairs of real numbers, in standard
notation, to denote the X and Y coordinates. For instance, `0 0` or `3.14
-5.5`. When printed, all real numbers must be formatted with three digits
after the decimal dot.


*Colors*

Colors in the commands are given, between curly braces, by three real numbers
in [0,1], in standard notation, to denote the RGB color. For instance, `{0 0
0}` denotes black, `{1 0 0}` denotes red, and `{1 0.64 0}` denotes orange.


*The assigment command*

The assignment command (`:=`) associates an variable with a convex polygon.
If the polygon identifier is new, it will create it. If it already existed, it
will overwrite the previous polygon. New polygons are black by default. It is
an error to use a variable not yet defined.

*The `print` command*

The `print` command prints a given polygon or a text.

- For polygons, the output must only contain the vertices in the convex hull of the
polygon, in clockwise order, starting from the vertex will lower X (and the
vertex with lower Y in case of ties).

- For texts, the text is given as a string of (simple) characters between quotes.


*The `area` command*

The `area` command prints the area of the given polygon.


*The `perimeter` command*

The `perimeter` command prints the perimeter of the given polygon.


*The `vertices` command*

The `vertices` command prints the number of vertices of the convex hull of the
given polygon.


*The `centroid` command*

The `centroid` command prints the centroid of the given polygon.


*The `color` command*

The `color` command associates a color to the given polygon variable.


*The `inside` command*

Given two polygons, the `inside` command prints `yes` or `no` to tell whether
the first is inside the second or not.


*The `equal` command*

Given two polygons, the `equal` command prints `yes` or `no` to tell whether
the two polygons are the same or not.


*The `draw` command*

The `draw` command draws a list of polygons in a PNG file, each one with its
associated color. The image should be of 400x400 pixels, with white background
and the coordinates of the vertices should be scaled to fit in the 398x398
central part of the image, while preserving the original aspect ratio.


*Operators*

`*` represents the intersection of two polygons.

`+` represents the convex union of two polygons.

`#` is the unary operator that returns the bounding box of a polygon
(it computes a new polygon with the four vertices corresponding to the
bounding box of the given polygon).

`!n` is an operator that (applied to a natural number `n`)
returns a convex polygon made with `n` points drawn at random in the unit square.