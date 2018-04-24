## Object Constrain Language (OCL)

Especificacio de restricions, per fer suport als models gràfics.

Es pot utilitzar per especificar invariants(inv) i per especificar pre i postcondicions i regles de derivacio.

### Propietats dels objectes

Tota expressio OCL s'escriu en un context d'una instància i en defineix una propietat.

Es tracta de començar amb una instancia de la classe que volem crear la propietat, i accedir als seus camps:

p.e.  = **p.edad >= 0**

Podem navegar entre objectes, partint d'un en concret, a través de les associacions del Model conceptual per referir-nos a altres objectes i les seves propietats.

p.e = context Empresa inv:**self.director.nom** --nom del director de l'empresa 

### Col·leccions: Cjt, bosses i seqüencies

| Tipus            | Definicio                                         | Multiplicitat             |
| ---------------- | ------------------------------------------------- | ------------------------- |
| Conjunt          | Cada element apareix un UNIC cop a la col·lecció. | 1                         |
| Bossa(Multi cjt) | la col·lecció pot tenir repetits.                 | >1                        |
| Seqüencia        | Col·lecció amb elements ordenats.                 | +1 Associacio i alguna >1 |

p.e.= "nombre de treballadors que treballes per a una persona"

context Persona::num-treb:Int derive:

**self.empresesDirigides.**

**empleat->asSet()** El resultat es una bossa, pot tenir repetits, els eliminem amb asSet()

**->size()** 

instancia -> navega per Empreses Dirigides -> navega per empleat

### Operacions sobre Col·leccions

* Select: Especificar un subconjunt d'una col·lecció

  Persones majors de 50 que treballen a una empresa

  **self.empleat->select(edad>50)**

  **self.empleat->select(p:persona|p.edad>50)**

* Collect: especifica una col.lecció que es deriva d’una altra, però que conté objectes diferents.

  Edad dels empleats d'una empresa

  **self.empleat -> collect(dataNaixement)**

* forAll: Expressio per a tots els elements

  "tots els empleats de l'empresa es diuen Jack"

  **context Empresa inv: self.empleat->forAll(nom="Jack")**

* Exists: Condicio que satisfà almenys un element

  "Com a minim un empleat de l'empresa es diu jack"

  **context Empresa inv: self.empleat->exists(nom='Jack')**

* IsUnique: Retorna cert si l'expressió que s'avalua si cada element de la col·lecció es unique

  "la clau externa d'empresa es el seu nom"

  **context Empresa inv: Empresa.allInstances()-> isUnique(nom)**

### Definicions de ...

* Restriccions textuals d'integritat(inv)

  **context Persona inv:**

  **self.esposa -> notEmpty() implies self.esposa.edat >= 18 and**

  **self.espos -> notEmpty() implies self.espos.edat >= 18**

* Definició d'atributs derivats(derive)

  **context Empresa::nombred'Empleats : Integer**

  **derive: self.empleat->size()**

### Navegació per classes Associatives

* Per anar a una classe associativa, s'utilitza el nom de la classe associativa.

* Desde una classe associativa, s'utilitza el nom de rol de l'extrem on es vol anar o el nom de la classe en cas que no tingui nom de rol.

  "les persones que treballen no poden estar aturades"

  **context Treball inv: self.empleat.estaAturat = false;**

### Navegacio per classes Associatives Recursives

* Per anar a una classe associativa, es pot utilitzar cualsevol de les dues direccions. Entre claudators s'ha despecificar el nom de rol cap on es vol navegar.

### Navegacio per Associacions Ternaries amb Associativa

* Navegacio cap a la classe associativa: Idem que Classes Associatives.
* Navegacio d'una classe a l'altra: S'accedeix per la classe Associativa.

**context Alumne inv:**

**self** desde alumne

**.matricula **anem a matricules del alumne

**-> select(m|m.quadrimestre.quad='1q-04-05')** agafem de les matricules, les que el quatrimestre es ''

**.assignatura** assignatures matriculades per l'alumne al quatri ' '

### Navegacio per Generalizació - Especialització

* Accés directe a les propietats de la superclasse: Acces normal(.)

* Seleccio objectes que pertanyen a una subclasse

  **self.transaccio->select(oclIstypeof(ingres))**

* Accés a les propietats definides al nivell de subclase: Accés amb oclIsTypeOf(Nom)

  **context CompteCorrent inv:** 

  **self.transacció** Transaccions fetes per un Compte corrent

  **->select(oclIstypeOf(Extraccio))** Seleccionem les de tipus Extraccio

  **.oclAsType(extraccio)** diem que es de tipus Extraccio

  **.persona ->asSet()** agafem les persones 

### Variables let i def

* Let: Es fa servir per definir expressions que es volen utilitzar mes d'un cop
* Def: Es pot reutilitzar tambe, pero es pot accedir com si fos un atribut de la classe.

## Especificar en OCL

Sempre començar amb una instància contextual, es possible que una mateixa exp s'expresi de diferentes maneres.

* Si la restricció es sobre el valor d'un atribut de la classe, aqui esta la instància contextual
* Si la restricció es sobre valors dels atributs de mes d'una classe, qualsevol es candidata
* Normalment, navegar el minim sempre.



