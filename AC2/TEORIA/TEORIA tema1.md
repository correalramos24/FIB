## TEMA 1 COMPUTADORS I FIGURES DE MÈRIT

### Mètriques

Per tal de poder evaluar el rendiment de diferents processadors o aparell lògics, cal definir mètriques per comparar:
$$
IPC = \frac{\text{instrucions}}{\text{cicles}}= (CPI)^{-1}  \\
MIPS = \frac{N_{instruccions}}{CPI*T_{cicle}*10^6} = IPC * F_{cicle}*10^{-6} \\
MFLOPS = \frac{N_{instruccionsFLOAT}}{\Delta * 10^6} \\
Rendiment= \frac{1}{T_{exec}} = \frac{1}{N*CPI*T_{cicle}}\\
Speed-up = \frac{\alpha^{inicial}}{\alpha^{final}} \\
$$
Podem veure a la mètrica de rendiment que per augmentar el rendiment cal reduïr N, CPI i Tcicle. El <u>nùmero d'instruccions</u> depen del compilador i del llenguatge màquina, pel que fa el <u>CPI</u> depen del LM i de la microarquitectura; per últim el <u>temps de cicle</u> depen de la tecnologia de fabricació i de la microarquitectura

### Jerarquia de memória

Sobre el model bàsic (Von Neumann) de processador que envia @ de memoria i aquesta respon amb dades podem observar diversos trets que ens permeten accelerar el cost d'acces a memoria:

* @ Repetides --> Localitat Temporal
* @ Contigües --> Localitat Espacial
* @ amb patrons regulars --> Temporal + Espacial

Cal destacar que tenim dues mètriques per valorar els aspectes de memória:

* Latència = Temps entre una petició de @ i la dada de @ (cicles o s).
* Ampla de banda = Quantitat d'informació (bytes/s).

Per explotar aquestes localitats de les dades obtenim que entre la memoria principal i el processador tenim diversos nivells intermèdis:

```mermaid
graph LR
MEMPRINCIPAL --> CACHE_L2
CACHE_L2 --> CACHE_L1
CACHE_L1 --> REG
REG --> P(PROCESAOR)
```

Per tant, l'acces a memoria depen de les fallades o encerts de les cache:
$$
\begin{align}
T_{miss} - T_{hit} &= T_{penalty} \\
\overline{T_{acces}} &= T_{hit} + (\overline{miss} * T_{penalty}) \\
N*CPI*T_{cicle} &\rightarrow N * (CPI_{proc} + CPI_{mem}) * T_c \\
CPI_{mem} &= \frac{miss}{instrucions} * T_{penalty}
\end{align}
$$

### Llei d'Amdahl

Esta llei ens diu que cualsevol millora a un sistema esta limitada pel temps que s'utilitza aquesta; per tant, podem veure que cal millorar la part que mes utilitzi el sistema per tal de obtenir la millor millora possible:
$$
\text{Imaginem que un proces es la suma de dos components: } T_0 = T_1 + T_2 \\
\text{Millorem } T_2 \text{ i obtenim } T_m = T_1 + T_3 \\
\text{Màxim guany, temps utilitzat item = a =>} \frac{T_2}{T_0} \\
\text{Guany potencial}  = g= \frac{T_2}{T_3} \\
Speed-up = \frac{T_0}{T_m} = \frac{T_0}{t_1+t_3} = \frac{1}{1-a+\frac{a}{g}}  \\
$$
![1551193460695](/home/vic/.config/Typora/typora-user-images/1551193460695.png)

### Concurrència

#### Tipus de concurrència

El mès barat per obtenir concurrència a nivell d'instruccions de LM (ILP), totalment invisible als programadors, s'utilitzen <u>procesadors segmentats</u>. En aquests processadors es busca dividir una instrucció en etapes, per executarles solapades. Les etapes en la que s'interpreta una instrucció ens determinara el guany.

Els <u>processadors superescalars</u> es una combinació de processadors segmentats i replicacio d'unitats funcionals. El guany es el número d'etapes de la segmentació multiplicalt pel grau de superescalabilitat.

El guany teoric es veu limitat per la semàntica dels programadors, les condicions de carrera i la falta de recursos(mateixa unitat funcional).

També es pot buscar el paral·lelisme a nivell de thread, serien com els processadors superescalars pero amb registres i flags replicats per guardar l'estat de l'execucció, s'anomenen <u>multithread</u>. Existeixen diversos tipus d'entrellaçar els threads (nomes hi ha una unitat de càlcul) segons la granularitat(fina o gruixuda). El canvi de context ve donat per un accès a memoria o algun element que bloqueja l'execucció normal. L'altra opcio es utilitzar <u>multicores</u> on es replica tota l'unitat.

El paral·lelisme a nivell de dades es basat en fer càlculs d'una tira de bits mes ample del normal (instruccions multimedia, registres especials de 128 bits).

#### Accés a Memória

Sobre el model bàsic de temps:


$$
T_{exec} = N * [CPI_P+CPI_{MEM}]*t_c \\
CPI_m = m_i * T_{penalty} \\
$$
Tenint clar que si volem optimitzar el CPImem tenim:

* Disminuint tassa d'errades: Augmentant la Cache L1 :arrow_right: Augmenta el CPIp o Tc
* Disminuir temps penalització: Reduir latència accés
* Disminuir tassa d'errades: PreFetch/Anticipació o Load's no bloquejant

#### Tecnologia de fabricació

L'element bàsic es el transistor (C-MOS) en la fabricació dels processadors, la longitud de la porta del transistor es el factor limitant, ha anat reduintse (orde de micrometres).
$$
Potencia_{procesador} = P_{com} + P_{fuga} + P_{CurtCircuit} \\
P_{com} = freq_{com} * C * V² \\
P_{curtCircuit} = V*I_{curt} \\
P_{fuga} = V*I_{fuga} \rightarrow \text{consum estàtic}\\
Potencia_{procesador} \approx P_{com}
$$

#### Escalat Ideal

...

#### Mètriques eficiència energètica

* Energia x Instrucció(EPI) = E/N = POTÈNCIA/PRODUCTIVITAT = W/MIPS
* EPI per TempsPerInstrucció(TPI) = POTÈNCIA/PRODUCTIVITAT^2 = W/MIPS^2
* Tensio alimentació - Freqüència => La Potencia es multiple del V^3 o f^3(i aquets dos tenen una relacio lineal f = k * v), per tant no es pot reduïr un terme sense afectar al altre; pero es pot reduïr el consum:
  * Disseny circuit: Treball en paral·lel, tot i que la densitat de potència es dispararà.

La tensio-freqüència es pot modificar dinàmicament.

### PROBLEMAS

