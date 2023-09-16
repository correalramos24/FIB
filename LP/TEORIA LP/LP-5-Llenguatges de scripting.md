# Llenguatges de scripting - LS

> “Un llenguatge de *scripting* (de guions) és un llenguatge de programació destinat a escriure programes que s'integrin i es comuniquin amb altres programes.”

## Característiques

* Solen ser feblement tipats, de caràcter interpretat (o amb compilació *just-in-time*).
* Permeten l’ús interactiu com en batch
* Regles simples d’establiment d’àmbit, a molts LS no hi han declaracions de tipus (l’assignació dóna el tipus); inclús permeten tipat dinàmic flexible.
* Tipus de dades d’alt nivell predefinits (sets, bags, maps, lists,...) formant part del llenguatge, no com llibreries.
* S’utilitzen *garbagge collectors* per gestionar l’espai.

#### **A favor** 

* Desenvolupar codi per LP es ~5-10 vegades més ràpid.
* Economia d’expressions, s’afavoreix el desenvolupament ràpid i l’ús interactiu.
* Es facilita la comunicació amb altres programes, moltes opcions predefinides per crides al SO.
* Pattern matching: manipulació strings sofisticada.

#### En contra

* S’executen ~10-20 més lents.

## Dominis d’aplicació

Tot i que molt llenguatges d’scripting permeten funcionalitats d’altres llenguatges, en general tenim:

* Comandes de shell (bash) : Us interactiu, manipulació de fitxers, arguments, comandes.
* Processament de textos/generació informes (sed, awk, Perl): Fortament orientats al tractament de strings

* Matemàtiques/Estadístiques (Maple, Matlab)

* Scripting general (Per, Tcl, Python)
* Llenguatges d’extensió : Permeten fer scripts per aplicacions.

* Web scripting: Permeten que les pagines web tinguin “moviment”.