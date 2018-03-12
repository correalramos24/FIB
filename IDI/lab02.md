### vUniforms als shaders

Son com variables globals que son nomes de lectura. S'han de declarar a la classe i fer una associacio per nom. Algunes de les funcionalitats mes utilitzades son:

```c
uniform float val; //declara al hader un uniform de nom val

varLoc = GLint glGetUniformLocation (GLuint program, const GLchar *name);
//Obte la posició d'un uniform declarat al shader amb nom name, en retorna un identificador.
//program es el programa de shaders actual, name identifica l'uniform.

void glUniform {1|2|3|4}{f|i|ui}(GLint location, GLfloat value);
//Especifica el valor value al uniform identificar amb l'identificador.
//1f = 1 float, 2f = vec2 float, etc...
void glUniform{1|2|3|4}{f|i|ui|}v (GLint loc, GLsizei count, const Type *value);
//Especificia el valor si es tracta de vectors, on loc es refereix al id, count al nombre d'elements i *value al element [0] del vector.
void glUniformMatrix{2|3|4|2x3|3x2|2x4|4x2|3x4|4x3}fv (GLint loc, GLsizei count, GLboolean transpose, const GLfloat *value);
//Idem pero amb matrius, transpose indica si la matriu s'ha de transposar.

```

### Interacció directa amb Qt

Per tractar esdeveniments, cal fer override d'algun dels metodes virtuals seguents a la classe MyGLWidget:

virtual void mousePressEvent ( QMouseEvent * e )
virtual void mouseReleaseEvent ( QMouseEvent * e )
virtual void mouseMoveEvent ( QMouseEvent * e )
virtual void keyPressEvent ( QKeyEvent * e )

```c
void MyGLWidget::keyPressEvent (QKeyEvent *e) {
	makeCurrent();		//fa actiu el contexte
    switch(e->key())
    {
        case Qt::Key_S :
            scl += 0.1;
            glUniform1f (varLoc, scl);
            break;
        case Qt::Key_D :
            scl -= 0.1;
            glUniform1f (varLoc, scl);
            break;
        default: e->ignore (); // propagar al pare
    }
    update() //repinta l'escena
}
```



### Matrius de Transformació

Cal passar la matriu TG al Vertex Shader com un uniform, i lligar l'uniform al nostre programa:

```c
#version 330 core
in vec3 vertex;
uniform mat4 TG;
void main()
{
    gj_Position = TG * vec4(vertex, 1.0);
}
============================================
GLuint transLoc = glGetUniformLocation(program->programId(), "TG");
```

Alhora de construir matrius de transformació usarem glm:

```c
//Caldrà afegir la crida al metode desde el paintGL()
void MyGLWidget::modelTransform () {
	glm::mat4 TG (1.0); // Matriu de transformació, inicialment identitat
	TG = glm::translate (TG, glm::vec3 (-0.5, 0.5, 0.0));
	glUniformMatrix4fv (transLoc, 1, GL_FALSE, &TG[0][0]);
 }
```

Per aplicar transformacions:

```c
#include "glm/gtc/matrix_transform.hpp"
#define GLM_FORCE_RADIANS
translate (glm::mat4 m_ant, glm::vec3 vec_trans);
// acumula a la matriu anterior m_ant la matriu resultant de fer una
// translació pel vector vec_trans
scale (glm::mat4 m_ant, glm::vec3 vec_scale);
// acumula a la matriu anterior m_ant la matriu resultant de fer un 
//escalat en cada direcció segons els factors vec_scale
rotate (glm::mat4 m_ant, float angle, glm::vec3 vec_axe);
// acumula a la matriu anterior m_ant la matriu resultant de fer una
// rotació de angle radians al voltant de l’eix vec_axe

```

