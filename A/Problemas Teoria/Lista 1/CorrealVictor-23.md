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



