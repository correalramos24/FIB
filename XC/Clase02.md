## Classe 02

### Internet Protocol (IP)

-->Problema de examen:



A) El rango IP publico lo permite, con 7 bits se puede (220.10.0.0/25 --> 25 red, 7 Subred. ==> 2^7^ maquinas - 2 - # Routers).

B)

| Ni   | @ IP                         | #BITS Host-ID |
| ---- | ---------------------------- | ------------- |
| N4   | 50+1(r5)+2(Red+Broadcast)=53 | 64 -> 6 bits  |
| N1   | 20+1(r4)+2 = 23              | 32 -> 5 bits  |
| N3   | 12+1(r4)+2 = 15              | 16 -> 4 bits  |
| N2   | 10+1(r4)+2 = 13              | 16 -> 4 bits  |

C)

| Xarxa        | @ IP              | RANG dels HOST              | Mascara | @ IP         |
| ------------ | ----------------- | --------------------------- | ------- | ------------ |
| @ Contratada | 220.10.0.0XXXXXXX | @ Publica - Internet        | /25     | 220.10.0.0   |
| N4           | 220.10.0.00XXXXXX | 220.10.0.1 a 220.10.0.63    | /26     | 220.10.0.0   |
| N1           | 220.10.0.010XXXXX | 220.10.0.64 a 220.10.0.95   | /27     | 220.10.0.64  |
| N3           | 220.10.0.0110XXXX | 220.10.0.96 a 220.10.0.111  | /28     | 220.10.0.96  |
| N2           | 220.10.0.0111XXXX | 220.10.0.112 a 220.10.0.224 | /28     | 220.10.0.112 |

D)

|       | @ IP              | Màscara | @IP         |
| ----- | ----------------- | ------- | ----------- |
| n2+n3 | 220.10.0.011XXXXX | /27     | 220.10.0.96 |

Direccion Ip maquina - Direccion Ip Red son diferentes, se utiliza una mascara para diferenciarlas, pese a que se muestran en unico numero.

Direccion IP --> 32 bits en la v4 (2^32^ possibles direccionamientos), en la v6 acaban siendo 128bits. Se separan el 8 octetos, expresandose en decimal.

### Mascara De Red

De los 32 bits, una parte identifican la red, i otros el host de la red; la mascara hace referencia a los bits de la direccion de red. Si se refencia a la sub red, los bits que no forman parte debido a la mascara, seran 0, pero si se referencia a la maquina, estos seran diferente de 0.

* 10.1.1.0 / 24 == Mascara 255.255.255.0


* **Mascara &and& Direccion IP-Maquina = Direccion IP Subred ** 
* La @ de host con 0....0 es direccion de Red, nunca podra ser de host.
* Como minimo, hay que reservar una direccion IP de maquina para el router.
* La @ de host con 1....1 es la direccion de Broadcats(a todas las maquinas de la red).
* La @ 0.0.0.0 se refiere a "si mismo".
* La @ 256.256.256.256 envia a todos los elementos de la red.
* La @ 127.X.X.X no se puede utilizar, esta pensada para uso interno de protocolos.

### Direccionamiento

El direccionamiento Publico, es unica "mundialmente" para que todo el mundo pueda acceder, por ello no pueden existir dos iguales.

El direccionamiento Privado, se da entre unos rangos conocidos(10.0.0.0 - 10.255.255.255, 172.16.0.0 - 172.31.255.255, 192.168.0.0-192.168.255.255), y se trata de que las direcciones de una red pueden ser repetidas en otra red.



### Tablas de enrutamineto
(Emjemplo ampliado en clase03)
200.200.20.0/24

Risp  <--->  R1.33 	<---> S1(200.200.200.32/27)	<-->HOST_A.57

​											<-->HOST_B.34

​			.66 	<---> S2(200.200.200.64/27) 	<-->HOST_C.67

​											<-->.65 R2 .1 <--> S3(200.200.200.0/27) HOST.2



TABLA C:

| A DONDE IR?    |         | COMO? POR DONDE? |           |
| -------------- | ------- | ---------------- | --------- |
| Red destino    | Máscara | gateway          | interface |
| 220.220.200.0  | /27     | 200.200.65(r2)   | e0        |
| .32            | /27     | 200.200.66(r1)   | e1        |
| .64            | /27     | directa          | e0        |
| 200.200.202.0  | /27     |                  |           |
| resto: 0.0.0.0 | 0.0.0.0 |                  |           |

