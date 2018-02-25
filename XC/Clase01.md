### Clase 01

#### The Internet (estandard)

Sobre el nivel de equipos de red(ordenador-host) tiene los primeros 4 niveles y los 3 niveles OSI referentes a comunicacion de red, en los router, solo se implementan los protocolos de red.		

​			**Implementar == PROTOCOLO == BITS ESTANDARIZADOS**

La comunicacion entre cliente y servidor, se hace virtual a traves de las subredes y estan totalmente independizados los niveles OSI.

### Modelo OSI: Comunicacion entre Niveles

Es necesaria una comunicacion entre Aplicacion, Transporte y la red.

Un SAP(Service Acces Point) es un ejemplo de servicio, o servicio primitivo.
Existe un Transport Service Provision, como un servicio entre la capa App y Transporte.
Existe un Network Service Provision, como un servicio entre la capa Transporte y Red.
Estos servicios primitivos son bidirecionales.

#### Comunicacion

Tomando el ejemplo de OSI, podemos ver como cada nivel OSI se comunica punto a punto (de endpoint a endpoint)
a nivel de protocolo de App i transporte; pero realmente el unico intercambio fisico se hace a traves de un nodo de Red, que tambien
tiene los enlaces de protocolo como las capas anteriores pero tiene un intercambio de bits.
Con los servicios primitivos, se hace el movimiento de los bits.

#### Protocol Data Units

| Protocol Data units                 | "Resultado"     |
| ----------------------------------- | --------------- |
| App-H + App Data                    | Transport Data  |
| Trans-H + Transport Data            | IP Data         |
| IP-h + IP Data                      | Frame Data      |
| DataLink-H + Frame Data + DataLink- | Data Link Frame |

Todos los Protocols Data Units estan hecho con el fin de ==independizar== de los otros niveles.

### Componentes Internet

* Servicio : Comunicacion Vertical entre capas del mismo dispositivo a traves de primitivas.
* Protocolo: Com. Horizontal entre capas de diferentes Endpoints, a traves de dialogo/Interacion.
* Nombres de las Data Unit's: TCP(Segment) , IP(DataGrama), DataLink(Frame/Trama)

### Modelo Internet
* Basado en IP --> Unidad de datos es el datagrama.
* Ip es connection-less(No pre-defined path) & Best Effort(No controla Errores)
* Tcp es connection-oriented(3 phases(Connect, Transfer, Release)) & Reliable(Fiable)

### Estandarizacion
Para los modelos de comunicacion es importante que se sepa de antemano una manera (protocolo) de intercambiar informacion.
De ahi salen las organizaciones de estandarizacion, para otras cosas aparte de que las conexiones de red.





