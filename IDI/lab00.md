## LABORATORIO IDI 1

Open GL: API per a visualitzar gràfics 3D.

Qt: API per a disseny d'inteficies (objecte d'openGl insertat).



### Projecte Qt

Es codi c++, on es poden fer interficies GUI amb widgets.

* main.cpp es on es llança l'aplicaccio.
* *.pro conte la informacio del projecte.
* qmake (qmake-qt5) genera el Makefile a partir del .pro

Utilitzarem un widget del tipus QOpenGLWidget, afegint al .pro opengl i derivar la classe virtual QOpenGLWidget, implementant els metodes:

* InitializeGL()

  Inicialitzacio openGL.

* paintGL()

  Redibuixar l'escena.

* resizeGL()

  Redefinir la finestra(area de dibux == viewport).

### Model Geometric

El model conte vertexs amb informacio associada(color, posicio, normal).

<u>Per a cada model cal generar un VAO. Les dades associades es guarden als VBO.</u>

Per pintar el model geometric, caldra doncs:

1. Crear a la GPU un VAO

   GLunit VAO1;

   glGenVertexArrays(n, GLuint *array) //generar n-id's, i els deixa al array.

   glBindVertexArray(GLuint a) //activar VAO a

2. Crear VBO per la llista de vèrtexs.

   GLuint VBO1;

   **glGenBuffers(n, array)** //genera n-VBO, i els deixa al array

   **glBindBuffer(GL_ARRAY_BUFFER, GLuint a)** //Activa el VBO a

3. Guardar els vèrtexs en el VBO

   **glBufferData(GL_ARRAY_BUFFER, sizeof(%var), *data, GL_STATIC_DRAW)**

   //envia les dades *data a la GPU

   **glVertexAttribPointer(GLuint index, GLint size, GLenum type, GL_FALSE, 0, 0)**

   //Indica les caracteristiques de l'atb 

   **glEnableVertexAttribArray(index)**

4. Indicar al VAO pintar: glDrawArrays(..)

   **glBindVertexArray()** //Activar el VAO

   **glDrawArrays(GL_TRIANGLES, index, count);** //pinta a la pantalla

   ​

   ​



​	

