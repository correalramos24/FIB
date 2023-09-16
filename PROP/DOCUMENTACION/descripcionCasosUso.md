# DESCRIPCIÓN CASOS DE USO

Enumeraremos caso por caso los usos principales que tendra nuestro sistema:

[TOC]



### Registrar usuario

El usuario podra darse de alta en el sistema aportando su nombre de usuario, apodo(debera de ser unico en el sistema) y una contraseña.

### Entrada de un usuario (Login)

En este caso el usuario entrara con la contraseña que se registro y podra acceder a modificar sus datos personales(contraseña, nombre y apodo), ver sus historial de partidas jugadas, ver su posición en los rankings de los diferentes problemas y eliminar su cuenta.

### Gestión de problemas

Aqui el usuario podra dar de alta nuevos problemas en formato FEM, siempre y cuando el FEM sea correcto y el problema tenga solución en el numero de jugadas establecidas. También podrá eliminar problemas (solo los que ha creado él) y ver los rankings de todos los problemas.

### Jugar

Este caso de uso se refiere a la funcionalidad principal, el juego entre un atacante (que debera resolver el problema) y un defensor (que debe evitarlo). Antes de empezar a jugar se debe configurar la partida:

#### Configurar partida

Aqui el usuario debera elegir el problema sobre el que se jugará, quien jugara a defender (Humano o Máquina) y quien jugara a atacar (Humano o Máquina).

#### Jugar partida

En este escenario ya se inicia la partida con los datos de configuración descritos. La partida acabará cuando se llegue a una situación de mate o se supere el numero de movimientos que el problema estipula. Si el atacante es humano, su tiempo en resolver el problema se introducira en el ranking con su apodo solamente si es el primer intento de este.

### Gestion Rankings

Aqui el usuario podra ver los rankings de todos los problemas y consultar su estado y ademas ver un ranking de jugadores donde estaran los apodos y su media de tiempo en resolver problemas de dificultad igual.





Fernando Marimon, Victor Correal