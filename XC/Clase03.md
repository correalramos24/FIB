### Tabla Enrutamiento

[Ejemplo Enrutamiento]

Las tablas pueden ser Estaticas(se programan de antemano las tablas) o pueden ser dinamicas, que a traves de algoritmos se van actualizando las tablas.

### Fragmentacion

<u>Problema</u>: Passar a traves de los SAP tamaños de informacion muy grande podria ser peligroso, a la hora de passar datos por encima de una cantidad "X" de bytes. La cantidad de bytes maximos se define en el protocolo IP, en la cabezera.

La <u>solucion</u> es enviar los datos "empaquetados".

El MTU (Maximum Transfer Unit) es el maximo que la entidad de Data Link admite. A nivel de IP, es complicado saber el orden de los paquetes, por ello, es mejor fragmentar en el nivel superior a IP, el nivel de TCP.

En el protocolo, para saber si llegan todos los trozos

+ DF(Don't Fragment):
+ MF(More Fragment): "Hay mas fragmentos detras". Vale 1 solo en el ultimo de todos los paquetes.
+ Se numera en bloques de 8 bytes, para ahorrar en bits de offset en la cabecera de IP.









