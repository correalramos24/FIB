### T1: Métricas

#### Sostenibilidad:

- Economia:   Reducion de costes => deslocalizacion empresas,

- Ambiental:  Construccion de productos y destrucion, etc...

- Social: Enfermedades en las personas, perdida del contacto.

- Coste Humano: Problematica del Coltan -->Problemas humanos.

  **Economia, Social y Ambiental => Solucion Sostenible.**

#### Evaluación del Coste


$$
Coste Total = \frac{(Coste Die + Coste Test + Coste Empaquetado)}{Yield final(test)}
$$

$$
Coste Die = \frac{Coste Waffer} {DiesPerWaffer * Die Yield}
$$

$$
DiesPerWaffer =\frac{ \pi*(diametro/2)^2}{Die Area}- \frac{\pi*Diameter}{\sqrt(2*DieArea)}
$$

$$
DieYield = Waffer Yield * (1+ \frac{DefectosPerArea * Die Area}{\alpha})^-a
$$

#### Ley De Moore

* Observacion Economica, no Tecnologica.
* El numero de transistores (que de forma economica) se pueden integrar en un circuito se duplicara cada X tiempo.

#### Otras medidas de Coste

​	Latencia: Tiempo que transcurre entre la solicitud de un dato y la disponibilidad del mismo

​	Ancho de banda: Numero de Bytes transmitidos por unidad de tiempo.

​	Productividad: Es el trabajo realizado por unidad de tiempo.

​	Tiempo Respuesta: Es el tiempo necesario para procesar una solicitud.

#### Rendimiento

$$
\frac{1}{Rendimiento} = Tiempo Ejec = Instruciones * CPI * TiempoCiclo
$$

Los factores que afectan al tiempo de ejecucion NO son independientes.
$$
\begin{split}
& Speeedup = \frac{Ta}{Tb} \\
& Tant PerCent = (\frac{Ta}{Tb}-1)*100 
\end {split}
$$
​						Si > 1 --> B es mas rapido que A

​						Si < 1 --> A es mas rapido que B

Existen otras metricas para comparar:

​	MIPS: Millones ( 10^6 Instruciones ) por segundo.

​	MFLOPS: Millones de Floating Point Inst por segundo.

#### Consumo

$$
\begin{split}
& Potencia = Conmutacion + Corriente Fugas +  Corriente Cortocircuito\\
& Potencia = I * V = Watios  \\
& Energia = P * T  = Julios\\ 
& Potencia Conmutacion = C * V^2 * freq \\
& Energia Conmutacion  = C * V^2  \\
& PotenciaFugas = I_{Defuga} * V \\
& Eficiencia Energetica = \frac{rendiminento}{watio} = \frac{1}{Energia Consumida} \\

\end {split}
$$



#### Fiabilidad

$$
\begin{align*}
ProbFallo &= 1 - e^{-\lambda * \Delta t}\\
\lambda &= \frac{1}{MeanTimeToFail} \\
Disponibilidad &= \frac{MTTF}{MTTF + MeanTimeToRepair} = \frac{MTTF}{Mean Time Between Fails}\\ 
\frac{1}{MTTF_{sistema}} &= \frac{1}{MTTF_1} + \frac{1}{MTTF_2} ...
\end{align*}
$$

### T2: 