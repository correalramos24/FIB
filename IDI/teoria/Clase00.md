    Marta Fairén(Coordinadora Assignatura)
        mfairen@cs.upc.edu
        Avaluacio:
            Parcial 25% 23-Abril    TEO1
            Final   50% 21-Juny     TEO2
           Lab     25% --> individual i codi propi
                            OpenGL 3.3 VISUALITZACIO 3D
                            Qt 5.6 INTERFICIES
                            EntreguesLab: Entregar 4 problemes per assolir 1 punt extra
                            ProvaLab(25%): 31-Maig
        Competencia Transeversal, 2 temes extres a cada TEOi

## Informatica Grafica (INTRODUCCIÓ)
Comunicar informacio utilitzant imatges generades per un computador a partir de models de dades i interactuar amb les dades i les seces visualizacions. 

Els elements d'un sistema gràfic estan integrats per un model (format per unes dades), que dona lloc a una visualitzacio que es transmet a uns perifèrics(mitjançant píxels) que a traves d'una interacció d'usuari es pot varia la visualitzacio i el model.

A IDI, nomes objectes sòlids, representat amb triangles, a la sortida seràn pixels.
El punt de la càmera, amb un mode de pintat, llum, etc faran complementar la visualizació.

## Sortida, elements d'un sistema gràfic

A IDI, pantalles d'escombrat/raster nomes, existeixen mes tecnologies.

Pixel, unitat minima a una pantalla que es pinta d'un color, a una posicio concreta x, y
Imatge, conjunt de pixels amb una resolucio x i y que defineixen tots els pixels i uns colors per cada pixel.

Model RGB ( red,    green,  blue), on s'utilitza una síntesis additiva del color.

| RED     | GREEN     | BLUE |  COLOR |
| :------------- | :------------- |:-------------|
|0 |      0    |   0 | NEGRE|
|1 |      0    |   0 | VERMELL|
|0 |      1     |  0 | VERD |
|0  |     1     |  1 | CIAN |
|1   |    0   |    1 | MAGENTA|
|1    |   1    |   0 | GROC|
|1     |  1    |   1 | BLANC|
Cada color(Canal) es defineix amb 8 bits, donant lloc a 16.7 M colors.
Donant el color al pixel, tenim que el pixel es distingeix amb les coordenades x, y dona lloc a **pixel(x, y, c)**.
### Frame Buffer & Doble Frame Buffer
3 matrius de resolucions[x, y], una per cada canal, on l'element x, y correspon al color.
Per evitar tremolors degut a l'acces temporal de memoria, s'utilitzen dos buffers i un cop estigui estabilitzat es fa el "swap buffers".

## Model Geometric 

### GUARDAR ELS TRIANGLES
Necesitem un sistema de coordenades de referència per guardar la posicio del triangle.
A IDI, utilitzem la ma dreta de referencia.

S'ha de guardar aspectes de geometria i de Topologia(encara que sigui implicita). Existeixen diversos models per guardar les dades(Model de fronteres).

#### Model de fronteres:
Un model de fronteres es vàlid <=>  Les cares son "orientades"

Per visutalitzar el model, OpenGL, cal comunicar la unitat de proces de la targeta gràfica amb la CPU => Pasar el model nomes un cop, caldra una estructura auxiliar.

Un VAO encapsula les dades del objecte, que conten buffers VBO. Existiran identificadors de cada objecte, alhora que es pasa la informacio del model al VBO.

Per ultim, glDrawArrays() farà que OpenGL pinti el model.