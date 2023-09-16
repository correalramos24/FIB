## Problema 61

Considereu el problema d'emmagatzemar n llibres als prestatges de la biblioteca. L'ordre dels llibres és xat pel sistema de catalogació i, per tant, no es pot canviar. Els llibres han d'aparèixer a les prestatgeries en l'ordre designat. 

Les prestatgeries d'aquesta biblioteca tenen amplada L i són regulables en alçada. Un llibre $b_i$, on $1 \le i \le n$, té gruix $t_i$ i alçada $h_i$.  Una vegada es decideix quins llibres es fiquen a un prestatge s'ajusta la seva alçada a la del llibre més alt que col·loquem al prestatge. 

Doneu un algorisme que ens permeti col·locar els n llibres a les prestatgeries de la biblioteca de manera que es minimitzi la suma de les alçades dels prestatges utilitzats.

> En este problema debemos mirar donde "interesa" mover un libro a la siguiente libreria para minimizar la suma de las alturas.
>
> Podemos tener una estructura de datos que represente un espacio entre cada libro; representando que tenemos que saltar de libreria. Calculando para cada espacio la suma de alturas que tendriamos.
>
> Tendremos que contemplar que hay espacios que deberemos "activar" de inicio en el caso que en el orden dado de libros la suma de los $t_i$ sea mayor que L.



