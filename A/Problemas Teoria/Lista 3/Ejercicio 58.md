### Ejercicio 58

La gerenta de la UPC vol donar una festa als PAS de la universitat. Aquest personal té una estructura jeràrquica, en forma d’arbre on la gerenta és l’arrel. L’oficina de personal ha assignat a cada PAS un nombre real que representa el seu grau de simpatia. En vista que la festa sigui distesa, la gerenta no vol que cap superior immediat d’una persona convidada, també sigui convidada. Descriviu un algorisme per confeccionar la llista de convidats de manera que es maximitze la suma dels graus de simpatia. Quina es la complexitat del vostre algorisme?. Què hauríeu de fer per assegurar que la gerenta està invitada a la seva pròpia festa?

>El algoritmo deberá calcular todas las possibles permutaciones de suma de simpatias, dejando fuera para cada caso un estrato de empleados del mismo puesto jerarquico.
>
>Sera de interes tener una tabla con la suma de todos los grados de simpatia, por nivel jerarquico.
>
>Con esta tabla, podemos hallar, los resultados dejando fuera un estrato de empleados; para acelerar el càlculo podemos tener calculado el máximo  (que nunca podemos alcanzar por las restriciones) y restar los grados de simpatia del estrato que estamos tratando.
>
>Por ultimo, para confeccionar la lista, deberemos buscar el indice maximo y retirarlo de la lista.

````pseudocode
gestionaFiesta(tablaEmpleados)
	t[] := calcularEstratosSimpatia()
	max_simpatia := suma de todos los grados de todos los empleados
	resultado[] := tabla con tantas entradas como estratos jerarquicos
	for cada estrato jerarquico (iterador p):
    	resultado[p] = max_simpatia - t[p]
	listaInvitados := lista con todos los empleados
	estratoMaximo := max resultado[] 
	listaInvitado.borra_empleados(estratoMaximo)
	return listaInvitado
````

* Coste

  Linia 2 : Coste $\theta(n)$

  Linia 3 : El coste se puede hacer a la vez con la linia 2.

  Linia 5 : El  bucle tiene coste $\theta(p)$, siendo p el numero de puestos

  Linia 9 : Si hemos utilizado una estructura indexada por el puesto de los empleados, tendremos coste constante.

  Por tanto, el coste total sera:
  $$
  T(n) =\theta(n) + \theta(p) \rightarrow O(n)
  $$
   

  En el caso peor, tendremos tantos puestos jerarquicos como empleados.

En el caso de que queramos asegurar que la gerente participe, podemos dara a sus immediatos inferiores un 0 de grado de simpatia.