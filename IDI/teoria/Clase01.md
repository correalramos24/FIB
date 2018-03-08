## Model Geometric: Escenes

Per tal de formar escenes, es representaran un seguit de VAO's (s'estructuran en OBJ's) i es pinta cada model per separat sobre la mateixa visualització.

Per tal d'ajustar les coordenades, caldra aplicar Transformacions Geometriques als models, per fer que tot quedi cuadrat. 

## Visualització 

El S.C.A (sistema de coordenades Aplicacio) es el sistema de coordenades on representem els nostres models. 

Per tal de donar lloc a una visualització a través d'una càmera, on cal definir el **punt del observador respecte el SCA** (OBS) i el **volum de visió** (window) del nostre model. Per tal d'enmarcar la foto cal prevenir possibles deformacions del model.

### Paradigma visualitzacio OpenGL 3.3

1. Processament de vertexs

   Els vertexs del model (VAO) s'assimilen en un proces on interve la posicio de la càmera i el viewport. 

   Per un Vertex de l'aplicacio (Va) se li aplica una matriu(View Matrix) funcio de la posicio de la càmera (donant lloc a una Visio d'observador Vo) i seguidament s'aplica una matriu(Project Matrix) funcio del viewport. Aquest proces es programable al OpenGL i s'anomena Vertex Shader.

   Un cop s'han aplicat aquestes transformacions, s'apliquen algoritmes de Clipping & Prespective Division per ajustar quines zones del model queden dins de la prespectiva de la viewport i la camera.

2. Rasterització

   Un cop processats els vertexs, les coordenades de tres components pasen a ser representades per pixels en l'espais 2D de la pantalla, aixo dona lloc a fragments

3. Processament de Fragments

   Ultima etapa, on es poden aplicar, a traves de la programacio del Fragment Shader colors i altres coses a sobre dels fragments. Per ultim s'apliquen algoritmes d'EPA per eliminar vertexs que queden tapats.

## Transformacions Geometriques

Existeixen tres tipus de transformacions, cadascuna té la seva propia matriu per aplicar als vertexs. Cal destacar que son matrius 4x4 ja que OpenGL treballa amb coordenades Homogenies.

Per transforma una coordenada Cartesiana a una de Homogenia: (x, y, z) = (x, y, z, 1). 

Per fer la transformacio inversa: (x, y, z, w) = (x/w, y/w, z/w).

* Traslació

  ​

* Rotació

  ​

* Escalar

La composicio de transformacions es sensible al ordre en que apliquem les composicions.

Per model no simples, la capsa minima contenidora del model (CapsaMinCont) ...