#### Dynamic Host Configuration Protocol (DHCP)

DHCP configura automaticamente los aspectos para entrar en una red. Existe en una red un servidor DHCP (y existe un cliente DHCP) que es que le da los parametros, que a traves de un protocolo a traves de la subred.

* @ IP(fija o dinamica).
* Máscara de Subred.
* Nombre (associado a un dominio).
* Gateway/Router por defecto.
* @ servidor DNS **local**

Para que el servidor de DHCP pueda establecer el protocolo, se hace un broadcast a toda la subred, haciendo que si hay mas de uno, solo uno pueda responder. El router de casa suele ser tambien servidor DHCP.

El protocolo se establece en dos fases, una de busqueda de DHCP i una posterior configuracion. Se puede hacer solo la segunda fase directamente si ya se sabe la @ del servidor DHCP. 

Todos los mensajes de este protocolo, viaja sobre UDP(transporte).

**Cliente** -- **Servidor**

0.0.0.0 DHCP discover --> 255.255.255.255(broadcast generico)

<-- DHCP offer

DHCP request --> @IP servidor DHCP

<-- DHCPack

#### Network Addres Translation (NAT)

Es un servicio que a traves de una direccion privada de una subred, para conectarse a una maquina que esta en "internet". En la cabecera ip, los campos direcion de origen y destino se colocan las direcciones privada y publica respectivamente. La direccion privada debe ser "convertida", solo se puede saltar por los routers con direcciones publicas, el NAT se encarga de esta conversion(se encarga el router).

Existira una table NAT que como columnas tiene una relacion entre direcciones publicas possibles y una direccion privada, variando asi la direccion de la cabecera IP.

Si existe una respuesta por parte del servidor, habra una nueva conversion de NAT.

El transporte tambien tiene un numero de puerto(port) de 0 a 1024 estan reservados.

#### Domain Name System (DNS)



es aplicacion, en el esquema OSI.

