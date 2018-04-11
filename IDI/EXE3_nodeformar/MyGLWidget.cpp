#include "MyGLWidget.h"

#include <iostream>

MyGLWidget::MyGLWidget (QWidget* parent) : QOpenGLWidget(parent), program(NULL)
{
  setFocusPolicy(Qt::StrongFocus);  // per rebre events de teclat
  scale = 1.0f;
  angle = 0;
}

MyGLWidget::~MyGLWidget ()
{
  if (program != NULL)
    delete program;
}

void MyGLWidget::initializeGL ()
{
  // Cal inicialitzar l'ús de les funcions d'OpenGL
  initializeOpenGLFunctions();  
  glEnable (GL_DEPTH_TEST);
  glClearColor(0.5, 0.7, 1.0, 1.0); // defineix color de fons (d'esborrat)

  angle = 0.0;
  alfa = float(M_PI/6);
  ra = 1.0;

  carregaShaders();
  createBuffers();
  
}

void MyGLWidget::carregaModel()
{
   m.load("HomerProves.obj");
}

void MyGLWidget::paintGL () 
{
  // Esborrem el frame-buffer
  glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

  // Carreguem la transformació de model
  modelTransformHomer ();
  projectTransform();
  viewTransform();

  // Activem el VAO per a pintar la caseta 
  glBindVertexArray (VAO_homer);

  // pintem
  glDrawArrays(GL_TRIANGLES, 0, m.faces().size()*3);
  
  modelTransformTerra();
  glBindVertexArray(VAO_terra);
  glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
  
  
  
  
  glBindVertexArray (0);
}

void MyGLWidget::modelTransformHomer ()
{
  // Matriu de transformació de model HOMER
  glm::mat4 transform (1.0f);
  transform = glm::scale(transform, glm::vec3(scale));
  transform = glm::rotate(transform, angle ,glm::vec3(0.,1.,0.));
  glUniformMatrix4fv(transLoc, 1, GL_FALSE, &transform[0][0]);
}
void MyGLWidget::modelTransformTerra ()
{
// Matriu de transformació de model terra
  glm::mat4 transform (1.0f);
  transform = glm::scale(transform, glm::vec3(scale));
  glUniformMatrix4fv(transLoc, 1, GL_FALSE, &transform[0][0]);
}

void MyGLWidget::projectTransform()
{
    ra = double(width()/double(height()));

    if(ra < 1)
    {
        alfa = atan(tan(double((M_PI/4.0))/ra));
    }

    glm::mat4 Proj = glm::perspective (2*alfa, ra, 1.0, 3.0);
    glUniformMatrix4fv (projLoc, 1, GL_FALSE, &Proj[0][0]);
}
void MyGLWidget::viewTransform()
{
    glm::mat4 View = glm::lookAt(glm::vec3(0,0,2), glm::vec3(0,0,0), glm::vec3(0,1,0));
    glUniformMatrix4fv (viewLoc, 1, GL_FALSE, &View[0][0]);

}
void MyGLWidget::resizeGL (int w, int h) 
{
  glViewport(0, 0, w, h);
  projectTransform();
}

void MyGLWidget::keyPressEvent(QKeyEvent* event) 
{
  makeCurrent();
  switch (event->key()) {
    case Qt::Key_S: { // escalar a més gran
      scale += 0.05;
      break;
    }
    case Qt::Key_D: { // escalar a més petit
      scale -= 0.05;
      break;
    }
    case Qt::Key_R:
    {
        angle += float(M_PI/4);
        modelTransformHomer();
        break;
    }
    default: event->ignore(); break;
  }
  update();
}

void MyGLWidget::createBuffers () 
{
    carregaModel();
  // Creació del Vertex Array Object per pintar
  glGenVertexArrays(1, &VAO_homer);
  glBindVertexArray(VAO_homer);

  GLuint VBO_Homer[2];
//primer VBO
  glGenBuffers(2, VBO_Homer);
  glBindBuffer(GL_ARRAY_BUFFER, VBO_Homer[0]);
  glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat)*m.faces().size()*3*3, m.VBO_vertices(), GL_STATIC_DRAW);

  // Activem l'atribut vertexLoc
  glVertexAttribPointer(vertexLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
  glEnableVertexAttribArray(vertexLoc);
//segon VBO
  glBindBuffer(GL_ARRAY_BUFFER, VBO_Homer[1]);
  glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat)*m.faces().size()*3*3, m.VBO_matdiff(), GL_STATIC_DRAW);

  // Activem l'atribut colorLoc
  glVertexAttribPointer(colorLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
  glEnableVertexAttribArray(colorLoc);
  
  //terra:
  GLuint VBO_terra[2];
  
  glGenBuffers(2, VBO_terra);
  
  glm::vec3 verterra[4] = {
      glm::vec3(-1.0, -1.0, -1.0),
      glm::vec3(-1.0, -1.0, 1.0),
      glm::vec3(1.0, -1.0, -1.0),
      glm::vec3(1.0, -1.0, 1.0)
  };
  glm::vec3 colorterra[4] = {
      glm::vec3(1, 0, 1),
      glm::vec3(1, 0, 1),
      glm::vec3(1, 0, 1),
      glm::vec3(1, 0, 1)
  };
  
  glGenVertexArrays(1, &VAO_terra);
  glBindVertexArray(VAO_terra);
  
  glBindBuffer(GL_ARRAY_BUFFER, VBO_terra[0]);
  glBufferData(GL_ARRAY_BUFFER, sizeof(verterra), verterra, GL_STATIC_DRAW);
  
  glVertexAttribPointer(vertexLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
  glEnableVertexAttribArray(vertexLoc);
  
  glBindBuffer(GL_ARRAY_BUFFER, VBO_terra[1]);
  glBufferData(GL_ARRAY_BUFFER, sizeof(colorterra), colorterra, GL_STATIC_DRAW);
  
  // Activem l'atribut colorLoc
  glVertexAttribPointer(colorLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
  glEnableVertexAttribArray(colorLoc);
  
  glBindVertexArray (0);
}

void MyGLWidget::carregaShaders()
{
  // Creem els shaders per al fragment shader i el vertex shader
  QOpenGLShader fs (QOpenGLShader::Fragment, this);
  QOpenGLShader vs (QOpenGLShader::Vertex, this);
  // Carreguem el codi dels fitxers i els compilem
  fs.compileSourceFile("shaders/fragshad.frag");
  vs.compileSourceFile("shaders/vertshad.vert");
  // Creem el program
  program = new QOpenGLShaderProgram(this);
  // Li afegim els shaders corresponents
  program->addShader(&fs);
  program->addShader(&vs);
  // Linkem el program
  program->link();
  // Indiquem que aquest és el program que volem usar
  program->bind();

  // Obtenim identificador per a l'atribut “vertex” del vertex shader
  vertexLoc = glGetAttribLocation (program->programId(), "vertex");
  // Obtenim identificador per a l'atribut “color” del vertex shader
  colorLoc = glGetAttribLocation (program->programId(), "color");
  // Uniform locations
  transLoc = glGetUniformLocation(program->programId(), "TG");
  //uniform Proj
  projLoc = glGetUniformLocation(program->programId(), "proj");
   // Uniform View
   viewLoc = glGetUniformLocation(program->programId(), "view");
}

