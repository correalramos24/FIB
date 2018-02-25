### Classe 00

## App to Network Example

Se puede comprobar (app. wireshark) como la app que hace la request http hace una serie de llamadas tcp, pues <u>HTTP</u> funciona sobre <u>TCP</u>, que a su vez se implementa con un <u>protocolo IP</u>, que acaba siendo un <u>Data Link</u>.

Esto lleva a que una cadena de informacion de la app, acaba acumulando Headers de cada uno de los niveles de protocolo utilitzados (AppHeader, TCPHeader, IPHeader, Data).

Alfinal, podemo deducir que <u>"Internet es una red de redes"</u>. Para estudiar como se comportant las redes, podemos definir algunas estructuras con niveles, para un estudio independiente.

## MODELO OSI - MODELO INTERNET

Es un modelo donde las capas son:

|     OSI     |       INTERNET        |
| :---------: | :-------------------: |
|     App     |                       |
| Presentacio |      Application      |
|   Session   |                       |
|  Transport  | Transport(TCP or UDP) |
|   Network   |          IP           |
|  Data Link  |    Network Access     |
|  Physical   |       Physical        |

Son modelos arbitràrios, que permite un estudio.
