### Pipeline Programable OpenGL 3.3

OpenGL te dues parts programables, el vertex shader, que fa referencia a les possibles transormacions geometriques i el fragment shader, que fa referencia a càlculs de colors i altres efectes.

A cada model VAO, per cada vertex s'ejecuta el vertex shader, seguit d'aixo es rasteriza i es produeixen fragments, que pasen al fragment shader.

### Lenguatge GLSL 3.3(OpenGL Shading Language)

Es el llenguatge que serveix per programar shaders, la sintaxis es de C. 

Els tipus de dades bàsiques del GLSL son:

* **Escalars**: void, int, uint, float, bool.
* **Vectors**: vec2, vec3, vec4, mat2, mat3, mat2x3, ivec3, bvec4
* Tambe es poden definir **Structs** com a C, on implicitament es defineixen constructors.
* Les funcions que es poden crear son amb sintaxis C, pero afegint tipus de parametres(in, out i inout). Els parametres es passen per copia sempre!
* Al vertex shader i al fragment shader existeixen variables globals predefinides que no s'han de declara:
  * out vec4 gl_Position; (VS)
  * in vec4 gl_FragCoord;(FS)
  * out float gl_FragDepth;(FS, no s'utilitza a IDI)
* Als FS, l'instruccio **discard**  fa que no es procesi el fragment, fent que aquet no acabi existint al final.

### MyGLWidget + Fragments

Els shaders s'han d'indicar a la clase MyGLWidget, els codis en GLSL es compilen en temps d'execuccio del qt.

Per pasar-li informacio als shaders cal definir un lligam entre els dos, que es fa pel nom i a traves de la crida **GLint glGetAttribLocation (program, "name")** ens permet referenciar amb l'integer que retorna.



