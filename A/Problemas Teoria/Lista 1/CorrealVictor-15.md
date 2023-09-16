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