# TEMA 2 SEGMENTACIÓ I REPLICACIÓ

Primerament segmentarem un dispositiu (unitat funcional) sèrie, sumador de vectors de bits. Segmentar es dividir en etapes un cert treball per poder augmentar la productivitat. 

No tots els dispositius poden ser segmentats, els riscos estructurals es donen en el moment que al mateix temps es vol accedir al mateix recurs de la unitat

### Segmentar un dispositiu sèrie

![1551194113575](/home/vic/.config/Typora/typora-user-images/1551194113575.png)

1ª millora, afegir Biestables-Registres D al vector de bits de la entrada, per tal de poder fer sumes 

2ª  millora, afegir mes registres, a la sortida de Ci i fent encavir amb mes registres d'acoplament pel mitj.

El temps de cicle augmenta en la 2ª millora, pero la productivitat(op's/temps) augmenta bastant.

Per fer un estudi d'un dispositiu segmentat s'utilitzen les taules de reserva(tantes files com etapes(eix vertical) i els cicles(eix horitzontal)) i les mètriques de latència de repetició(entre inicis de diferents operacions)  i la latència de l'operacio(en una operació).

![1551193832010](/home/vic/.config/Typora/typora-user-images/1551193832010.png)

![1551194245170](/home/vic/.config/Typora/typora-user-images/1551194245170.png)

Prenent un <u>dispositiu ideal</u> que volem segmentar, veurem que en fer una unica operacio obtindrem un temps mes alt en la versio sèrie, pero en fer N operacions triga menys la versio segmentada. En general, esta acotat el guany possible i es proporcional al número d'etapes.

La repliació també es un altre manera de guanyar productivitat, treballant en paral·lel amb Nd sistemes. El temps en fer N operacions sera N / Nd * (Tp+Tl). El guany possible es Nd

Es molt mès economic segmentar que replicar un circuit sencer.

### Tipus de dispositius segmentats

- Funcion -> Dispositius Unifunció/Multifunció.
- Dispositius -> Lineals (taula reserva es una linea) / Multicicles-Pseudolineals (una etapa pot trigar + 1 cicle) / No lineals (no hi ha unics predecesors o succesors).

En els dispositius lineals no te gaire sentit estudiar-los, no tenen cap risc estructural a priori i la seva latència de repeticio es d'1. Cal estudiar els altres tipus per evitar els riscos estructurals.

### Riscs estructurals (Conflictes)

Sobre aquesta taula de reserva, podem veure en un cronograma que hi ha etapes que es solaparien en un cert temps.

| A    | X    |      |      | X    |      |
| ---- | ---- | ---- | ---- | ---- | ---- |
| B    |      | X    |      |      |      |
| C    |      |      | X    |      | X    |

Per solucionar els riscos, caldrà afegir una unitat de control a l'entrada del dispositiu que impedira que s'inicin operacions en les que es poden donar conflictes(ho sabem perque coneixem la taula de reserva). La seqüència de latencia d'inici(1,4,1,4,1,4,...).

Els cicles perduts es la diferència entre la mitjana de les latències d'inici i l'ideal(1). Per analitzar parelles d'operacions hem d'estudiar l'operació prèvia i la posterior, utilitzant la taula de latència d'inici prohibides.

Aquesta taula només ens ajuda a disenyar una unitat funcional, pero també es pot redisenyar tot.

### Segmentació del procès d'interpretació d'instruccions

L'objectiu es segmentar el procès d'interpretació d'un joc d'instruccions:PAGINA -31tema2

- Instruccions de classe entera: EN
  - RR (registre a registre)
- Instruccions de memoria (moviment dades): MEM
  - LOAD
  - STORE
- Instruccions de seqüenciament: IS

PAGINA 34-tema2

### Control dispositius

[libreta]


