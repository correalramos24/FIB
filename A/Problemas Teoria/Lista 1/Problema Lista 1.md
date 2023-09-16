[TOC]

## Problema 5

El problema 2SAT té com a entrada un conjunt de clàusules, on cada clàusula és la disjunció (OR) de dos literals (un literal és una variable booleana o la negació d'una variable booleana). Volem trobar una manera d'assignar un valor cert o fals a cadascuna de les variables perquè totes les clàusules es satisfaguin - és a dir, hi hagi al menys almenys un literal cert a cada clàusula. Per exemple, aquí teniu una instància de 2SAT:
$$
(x_1 \or \neg x_2) \and (\neg x_1 \or \neg x_3) \and (x_1 \or x_2)  \and (\neg x_3 \or x_4)  \and (\neg x_1 \or x_4)
$$


Aquesta instáncia és satisfactible: fent x1; x2; x3; x4 cert, fals, fals i cert, respectivament.

El propòsit d'aquest problema és conduir-vos a una manera de resoldre 2SAT de manera eficient reduint-ho al problema de trobar les components connexes fortes d'un graf dirigit. Donada una instància F de 2SAT amb n variables i m clàusules, construïm un graf dirigit GF = (V;E) de la següent manera.

* Gf té 2n nodes, un per a cada variable i un per a la seva negació
* Gf té 2m arcs, per a cada clàusula ( $ \alpha \or \beta $ ) de F (on $ \alpha , \beta $ són literals), Gf té un arc des de la negació d' $\alpha$ a $\beta$ i un de la negació de $\beta$ a $\alpha$.

Tingueu en compte que la clàusula ( $ \alpha \or \beta $ ) es equivalent a qualsevol de les implicacions $\neg \alpha \implies \beta$  o $\neg\beta\implies \alpha$. En aquest sentit, Gf representa totes les implicacions directes en F.

a) Realitzeu aquesta construcció per a la instància de 2SAT indicada amunt.

> De la fórmula anterior deduïm les seguents implicacions i el següent graf:


$$
\begin{split}
(x_1 \or \neg x_2) \equiv &\neg x_1 \implies \neg x_2 \\& x_2 \implies x_1 \\
(\neg x_1 \or \neg x_3) \equiv &x_1 \implies \neg x_3 \\& x_3 \implies \neg x_1 \\
(x_1 \or x_2)  \equiv & \neg x_1 \implies x_2 \\& \neg x_2 \implies x_1 \\
(\neg x_3 \or x_4) \equiv & x_3 \implies x_4\\& \neg x_4 \implies \neg x_3 \\
(\neg x_1 \or x_4) \equiv & x_1 \implies x_4\\& \neg x_4 \implies \neg x_1\\
\end{split}
$$
$$
G_f = < V_f, E_f> \\
V_f = \{x_1, ..., x_n, \neg x_1, ..., \neg x_n\} \\
E_f = \{(\neg l_{1,i}, l_{2,i}), (\neg l_{2,i}, l_{1,i}) | 1 \leq i \leq |V|\} \\
$$



````mermaid
graph LR;

x1 --> not_x3;
x2 --> x1;
x3 --> not_x1;
x3 --> x4;
x1 --> x4
not_x1 --> not_x2;
not_x1 --> x2;
not_x2 -->x1;
not_x4 --> not_x1;
not_x4 --> not_x3;

````

b) Demostreu que si GF té una component connexa forta que conté $x$ i $\neg x$ per a alguna variable x, llavors no és satisfactible.

> Si en l'anterior graf obtenim, a la vegada un camí que ens porta desde cualsevol variable x fins $\neg x$ i viceversa (tenint un cicle) llavors arribem a una contradicció ($\neg x \and x$)

c) Ara demostreu la inversa: és a dir, que si no hi ha cap component connexa forta que contingui tat un literal com la seva negació, llavors la instància ha de ser satisfactible.

> Si no hi ha cap component connexa forta (entre un literal i el seu negat) llavorns no existeix cap cicle que pugui fer arribar a contradicció, donat que el literal i el seu negat estaran en diferents components connexes.
>
> Arribem a aquets possibles casos:
>
> 1. X implica no X
> 2. no X implica X
> 3. Y i no Y implica X
> 4. Y i no Y implica no X

d) A la vista del resultat previ, hi ha un algorisme de temps lineal per resoldre 2SAT?

> Recorrent el graf SCC (strongly connected component) en ordre topologic invers i asignant a la primera visita a cada literal el valor V si es tracata de $\neg x$ i F altrament.

## Problema 8

Digueu si cadascuna de les armacions següents són certes o falses (i per què).

(a)  Asimptoticament $(1 + o(1))^{(\omega(1))} = 1$

> Cert, son tot valors constants.

(b) Si $f(n) = (n + 2)n/2$ aleshores $f(n) \in \Theta (n^2)$

> Desenvolupant l'expressió obtenim : $f(n) = (n^2 +2n)*1/2 \equiv f(n) \in \Theta (n^2)$
>
> Per tant, l'afirmació es certa

(c) Si $f(n) = (n + 2)n/2$ aleshores $f(n) \in \Theta (n^3)$

> L'afirmació es falsa, a l'apartat b s'ha resolt que el creixement asimptòtic es $n^2$

(d) $n^{1.1} \in O(n (lg n))^2$

>Fals, calculant $\lim_{n\to\infty} = \frac{f(n)}{g(n)}=\frac{n^{1.1}}{n(lg n)^2} = \infty$, per tant l'expressió $n^{1.1}$ creix asimptòticament més ràpid que g(n) i mai podrà ser O gran.

(e)$n^{0.01} \in \omega (n (lg n))^2$

>Fals, calculant $\lim_{n\to\infty} \frac{f(n)}{g(n)}= \frac{n^{0.01}}{n(lg n)^2} = 0$ veiem que g(n) creix asimptòticament més ràpid que f(n).

## Problema 15

Un graf dirigit és fortament connex quan, per cada parell de vèrtexs u; v, hi ha un camí de u a v.
Doneu un algorisme per determinar si un graf dirigit és fortament connex. Quin és l'algorisme més
ràpid que coneixeu per obtenir les components connexes fortes d'un graf?

> Una possible solució serà fent un recorregut pel tot el graf en profunditat desde cada vertex; si per aquest vèrtex no existeix un camí cap a tots els vèrtex del graf, ja podem asegurar que el graf no es fortament connex. 
>
> El cost d'aquest algorisme en el cas pitjor, que requerreix tants DFS com vèrtexs, tindrem: $T(n) = |V| * |V|^2 = n^3$

````pseudocode

function (graf G)
	for each vertex v from vertex G
		for each vertex u from vertex G
			if ! existeix_cami_DFS(G, v, u) return false
        end for
	end for
    return true
````

> L'algorisme més ràpid es l'algorisme de Kosaraju.

## Problema 19

Resoleu les següents recurrències:

(a)  $T(n) = 16T(\frac n 2)+ {n\choose 3} lg^4 n$
$$
T(n) = 16 T(n/2) + \frac{n!}{(n-k)!k!}*lg^4n
$$


(b)  $T(n) = 5T(n/2)+\sqrt{n}$
$$
T(n) = 5T(n/2)+n^{1/2} \\
\alpha = log_{2}5 = 2.321; k = 0.5\\
\alpha > k \rightarrow T(n) =  \Theta (n)^{\alpha}
$$


(c)  $T(n) = 2T(n/4) + 6.064 \sqrt{n}$
$$
T(n) = 2T(n/4) + 6.064 n^{1/2} \\
\alpha = \log_4 2 = 0.5;k = 0.5 \\
\alpha = k \rightarrow T(n)= \Theta (n^{0.5}\log n)
$$


(d) $T(n) = 2T(n/2) + \frac{n}{lg n}$
$$
T(n) - 2T(n/2) = \frac n {lgn}
$$


(e) $T(n) = T(n-10)+n$
$$
a = 1 \rightarrow T(n) = \theta (n^{1+1})
$$


## Problema 20

Donat un graf no dirigit G=(V,E) i un subconjunt de vèrtex $V_1$, el graf induït per $V_1$, G[$V_1$] té com a vèrtex $V_1$ i com a arestes totes les arestes a E que connecten vèrtexs en $V_1$. Un clique és un subgraf induït per un conjunt C on tots els vèrtexs estan connectats a ells.

Considereu el següent algorisme de divir-i-vèncer per al problema de trobar un clique en un graf no dirigit G = (V,A)

````pseudocode
cliqueDV(G)
	Enumerar vèrtex de V 1,2....n, n = |V|
	Si n = 1 tornar v
	dividir V en v1 = {1,2...n/2} i v2 = {n/2 +1, ..., n}
	Sigui G1 = G[v1], G2 = G[v2]
	c1 = clique(G1) i c2 = clique(G2)
	c1+ = c1 i c2+=c2
	for u in C1 do
		if u està connectat a tots els vèrtexs de C2+ then
			c2+ = c2+ U {u}
	for u in C2 do
		if u està connectat a tots els vèrtexs de C2+ then
			c1+ = c1+ U {u}
	retornar el més grans d'entre c1+ i c2+		
````

(a) Demostreu que l’algorisme clique DV sempre retorna un subgraf de G que és un clique.

> El cas base de l'algorisme genera un clique per cada vèrtex del graf.
>
> Un cop s'ha fet tota aquesta recursivitat, es tracta d'agrupar els cliques, sempre que existeixi un graf complet entre els vèrtexs.
>
> El clique resultant de cada crida recursiva es el més gran d'entre els dos "subcliques" del graf original.

(b) Doneu una expressió asimptòtica del nombre de passos de l’algorisme clique DV.

> $T(n) = 2T(\frac n 2)+ \frac{2n^2} 2$

(c) Doneu un exemple d’un graf G on l’algorisme CliqueDV retorna un clique que no és de grandària màxima.

> 

(d) Creieu que és fàcil modificar clique DV de manera que sempre done el clique màxim, sense incrementar el temps pitjor de l’algorisme? Expliqueu la vostra resposta.

> 

## Problema 23

Donada una taula A amb n registres, on cada registre conté un enter de valor entre 0 i $2^n$, i els continguts de la taula estan desordenats, dissenyeu un algorisme lineal per a obtenir una llista ordenada dels elements a A que tenen valor més gran que els log n elements més petits a A, i al mateix temps, tenen valor més petit que els n - 3 log n elements més grans a A.

> Recorrerem tota la taula A, actualitzant tres estructures, 
>
> * llista resultat amb els registres adients
> * llista de valors més petits
> * llista de valors més grans
>
> Les tres llistes estaran ordenades, i es podra accedir directament al primer i a l'ultim element.
>
> La funció .add() en el cas de tenir plena la llista, retornara l'element que no hi cap o null

````pseudocode
function reordenaLimit(r)
	if r == NULL return
	if r < minims.first() then 
		reordenaLimit(minims.add(reg))
	if r > maxims.last() then
		reordenaLimit(maxims.add(reg))
	else resultat.add(r)
	
function ordenaTaula(A, n)
	petits = log n
	grans = n - 3*log n
	init_Taula(maxims, grans)
    init_Taula(resultat)
	init_Taula(minims, petits)
	for each reg in A
		if reg < minims.first() then 
			reordenaLimit(minims.add(reg))
		else if reg >= maxims.last() then 
			reordenaLimit(maxims.add(reg))
        else resultat.add(reg)
	end for
	return resultat
````



## Problema 26

Donat un vector A amb $n$ elements, és possible posar en ordre creixent els $\sqrt{n}$ elements més petits i fer-ho en $O(n)$ passos?