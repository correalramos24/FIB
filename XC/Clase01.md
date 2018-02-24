### Clase 01

#### Ejemplo de Internet

Sobre el nivel de equipos de red(ordenador-host) tiene los primeros 4 niveles y los 3 niveles OSI referentes a comunicacion de red, en los router, solo se implementan los protocolos de red.		

​			**Implementar == PROTOCOLO == BITS ESTANDARIZADOS**

La comunicacion entre cliente y servidor, se hace virtual a traves de las subredes y estan totalmente independizados los niveles OSI.

### Modelo OSI: Comunicacion entre Niveles

Es necesaria una comunicacion entre Aplicacion, Transporte y la red.

El SAP(Service Acces Point), o direcion de transporte entre Transporte y App.(Servicio Primitivo). La direccion de red es entre Transporte y Red.

@Ejemplo de Comunicacion

Data-Header + App data := Data

Al llegar al nivel de transporte:

Trans-header + Transport Data(Data)	

etc...

Todos los Protocols Data Units estan hecho con el fin de ==independizar== de los otros niveles.

### Componentes Internet

* Servicio : Comunicacion Vertical


* Protocolo: Com. Horizontal
* Nombres de las Data Unit's: **d** , IP(DataGrama), DataLink(Frame/Trama)


* Ip es connection-less(No pre-defined path) & Best Effort(No controla Errores)
* Tcp es connection-oriented(3 phases(Connect, Transfer, Release)) & Reliable(Fiable)

###Estandarizacion

"Especificacion Comun para un cierto modelo"





