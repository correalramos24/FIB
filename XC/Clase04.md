### IP PROTOCOL - HEADER

Para averiguar el MTU, se utiliza mtu path disovery + msg control 

20 bytes(160 bits) para la cabecera que son fijos, algunos de los campos principales:

* Total lenght = 16 bits Longuitud en bytes (Tamaño max: 64Kbytes)
* Identification = 16 bits (Cada 2^16^ datagramas se repiten los id's).
* Fragment Offset + MF + DF = 13 bits + 1 + 1 + 1 reservado
* Source @ = 32 bits
* Destination @  = 32 bits
* Cal sumarle "options" y "padding" aparte de los 20 octetos

Otros campos secundarios, que tambien se incluyen son:

* **Version** IP: 4 bits que identifican la version del IP.
* **I**nternet **H**eader **L**enght: 4 bits que indican los bits de options y padding.
* **T**ype **O**f **S**ervice: 8 bits donde se codifican cosas, mas destinadas a redes privadas, no para internet.s
  * 8 niveles de prioridad(3 bits).
  * Delay: Tiene un bit que indica poco retardo o normal (1 bit).
  * Throughput: Rendiminento normal o mucho rendimiento(1 bit).
  * Reliability: 1 bit
  * Coste: 1 bit
  * 1 bit reservado
* **T**ime **T**o **L**ive: 8 bits, segundos que (0-255 segs) al llegar a 0, el datagrama es eliminado.
* **Protocol**: 8 bits para identificar quien es la entidad de transporte: UDP, TCP, etc...
* **Header Checksum**: 16 bits para controlar si hay algun error en la transmision de datos para controlar errores.
* **Options**: Se trata de transmitir informacion del tipo longuitud-valor y numero de opcion.

Despues de la cabecera, vienen los datos de transporte y aplicacion.

[Ejemplo cabezera IPv4]

### Otros Protocolos y servicios auxiliares

#### Address Resolution Protocol (ARP)

Todos los componentes necesitan una direccion de enlace(fisica) para poder referenciarse a nivel de data link. Este protocolo funciona por debajo de IP.

La manera para saber cual es la @ de enlace de una @ IP determinada, de manera que se pregunta a todos los dispositivos (broadcast) para que el que tiene la @IP que estamos buscando le pueda decir cual es su @ de enlace.

​				ARP Requests --BROADCAST--> ARP Reply

El protocolo incluye los datos que se envian, que han de contener la @ IP, @ Fisica de las dos maquinas que quieren comunicarse. Tambien existe una tabla de ARP que hace cache de @IP - @Fisica

#### Internet Control Message Protocol (ICMP)

vat19.com

Es un protocolo que viajaba sobre ip (se codifica en los 8 bits de protocol) y se utiliza para hacer notificaciones.

* Echo request y replay: Hace un "ping".
* Timestamp request y replay: Pedir marcas de tiempo.

Existen posibles errores en este protocolo:

* Ralentizacion de origen(source quench)
* Redirect
* Errores(notifiacion): Destino inalcanzable, tiempo excedido(TTL == 0).