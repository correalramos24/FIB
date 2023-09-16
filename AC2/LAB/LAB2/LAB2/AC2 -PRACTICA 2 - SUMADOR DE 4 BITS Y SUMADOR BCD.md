## AC2 -PRACTICA 2 - SUMADOR DE 4 BITS Y SUMADOR BCD

En esta practica se sigue expandiendo el lenguaje VHDL y el modelsim a la vez que se implementa un sumador de 4 bits y un sumador BCD.

### Tipos de datos, operadores y parámetros VHDL

Los vectores o buses de un bit son de gran interes, asi se declaran en VHDL:

`````vhdl
signal bus: std_logic_vector(3 downto 0) := "0111"; 
signal bus: std_logic_vector(0 to 3) := "0111"; 
bus(2) --2º elemento del bus
bus(1) <= '0' --asignacion un bit
bus <= "0101"; --asignación unica
bus(2 downto 1)    --rango contiguo
--como el std_logic_vector es del tipo std_logic, los valores pueden ser:
--'0', '1', 'U' indefinido, 'X'desconocido, '-'no importa
`````

Si queremos dar el valor de una cadena de caràcteres en octal, hexadecimal o binario:

`````vhdl
0"135"
X"c5"    
B"101010"
bus <= (others=>'0') --idem que "0000"
`````

Para las operaciones aritmeticas tendremos los tipos de datos unsigned(naturales en binario) y signed (enteros en Ca2). Se encuentra en la libreria <u>ieee.numeric_std.all</u>.

El operador de concatenacion de bits (&-Concatenate) agrupa subconjuntos de bits

Los paràmetros en un modelo VHDL es una manera de poder modificar desde el exterior un valor especifico. Se declaran mediante la clausula generic.

`````vhdl
entity S4bits is
generic (tam: positive := 4; retardo: time := 5 ns); 
generic (id: type := static_value)
port ( 	A: in std_logic_vector (tam -1 downto 0); 
		y: in std_logic_vector (tam -1 downto 0); 
		s: out std_logic_vector (tam -1 downto 0);
		csal: out std_logic );
end S4bits;
--En este caso al declarar un S4bits el valor tam y tamaño puede variar.
`````

### Circuito del sumador de 4 bits

Para hacer modulos mas complicados es necesario estrucutar el diseño para comprenderlo mejor y poder reutilizar modulos. Para seguir el "Structural Model" en VHDL se debe especificar la <u>interfaz</u>, <u>lista de componentes</u>, <u>señales-cables que interconectan componentes</u> y <u>instancias de componentes.</u>

#### Ejemplo de componentes, instancias y cables

````vhdl
library ieee;		
use ieee.std_logic_1164.all;		
use work.all;	--importa componentes de la libreria de trabajo añadida por el wizard

entity S4bits is 		--pins del S4bits
generic(ret_xor: time := 15 ns;
	ret_and: time := 10 ns;
	ret_or: time := 15 ns);
port (A: in std_logic_vector(3 downto 0);
	B: in std_logic_vector(3 downto 0);
	cen: in	std_logic;
	SUM: out std_logic_vector(3 downto 0);
	csal: out std_logic);
end S4bits;		

architecture estructural of S4bits is		
	--definimos componente s1bit: 	--> INTERFAZ Y LISTA DE COMPONENTES
	component S1bit 
	-- redefinir su generico de retardos, si cal:
		generic(retardoxor: time:= ret_xor; 
        	    retardoand: time:= ret_and; 
            	retardoor: time := ret_or);
		port(x,y,cen: in std_logic; s, csal : out std_logic);
	end component;
	signal c1, c2, c3, c4 : std_logic;
begin
									--> INSTANCIAS E INTERCONEXIONES:
    instance_name: component_name port map (port1 => signal1, ...)
	S1bit0: S1bit port map(x=>A(0), y=>B(0), cen=>cen, csal=>c1, s=>SUM(0));
	S1bit1: S1bit port map(x=>A(1), y=>B(1), cen=>c1, csal=>c2, s=>SUM(1));
	S1bit2: S1bit port map(x=>A(2), y=>B(2), cen=>c2, csal=>c3, s=>SUM(2));
	S1bit3: S1bit port map(x=>A(3), y=>B(3), cen=>c3, csal=>c4, s=>SUM(3));
	csal <= c4;
end estructural;		
````

Para añadir una libreria en Quartus, Assignments -> Settings y alli se añade el directorio de CODIGO del componente (SUMA/COMPONENTES/SUM1BIT/CODIGO en esta practica).

### Programas de prueba en VHDL - testbench

Los testbench se basan en diseñar frentes de onda para comprobar el funcionamiento de un circuito. Un frente de onda es igual que un concurrent statement.

````vhdl
s <= a and b after 2 ns; --una unico transacion
a <= ‘0’, ‘1’ after 5 ns, .. , ‘0’ after 140 ns; --mas de una transacion
````

**El testbench no pertenece al diseño**, por lo tant se debe declarar una nueva entidad y añadir el componente como hemos visto antes.

![1552411593994](C:\Users\victor\AppData\Roaming\Typora\typora-user-images\1552411593994.png)

*En esta practica en SUMA/SUM4/PRUEBAS hay un fichero base del programa de pruebas; recordar que no se puede añadir al diseño pero se puede analizar con quartus atraves de Processing -> Analyze Current File. Tambien hace falta en Assigments->Settings->EDA Tool settings->Simulation->Test benches->new->filename ...PAGINA 30 pdf errores en el fichero de prueba y eso*

### Modelo de comportamiento VHDL - Process

Se trata de una secuencia de sentencias de asignación que se ejecutan sucesivamente. Todos los process se ejecutan una vez se inicia la simulación y se declara dentro del cuerpo de la arquitectura. El cuerpo de un process tiene variables y estructuras "it-then-else" y parecidas:

`````vhdl
[process_label:] process [(sensitivity_list)] is --señales que provocan activacion
    [process_declaration]
    variable index2: integer :=-34;
	
    begin
        --SENTENCIAS SEQUENCIALES:
        if(d = '1') then
        	--statement
        elsif condition then
             --statement
        else --statement
        end if;
        --ESTIMULACIÓN VERIFICACIÓN
        wait for 10 ns;
        wait until x = '1';
        --LOOP'S
        for param_name in 0 to 3 loop
            --statements
        end loop
    end process[process_label];
`````

### Generacion de estimulos

Mediante un process se pueden modelar señales de frente de onda, utilizando el concepto de programa de prueba.

````vhdl
architecture comp of prueba is
    begin
        prueba: process
    		begin
                a <= '0'
                b <= '0'
                cen <= '0'
                wait for 100 ns;
                ...
                wait;
         end process
      end comp;
````

Para comprobar los resultados sin ver la traza temporal se puede utilizar la sentencia assert

````vhdl
assert condition [report expr][severity expr->note, warning, error, failure]
assert csal = acarreo_salida report "string error" & CR & LF & image(to_integer(unsigner(a))) severity error;
````

### Generacion de estructuras regulares VHDL

Para generar replicas a la hora de construir un mismo elemento VHDL dispone de la sentencia generate.Un ejemplo para una version 'generate' de un sumador de n bits:

`````vhdl
architecture estructural of snbits is
	signal c : std_logic_vector (n downto 0); --senyal interconexiones
	begin
	c(0) <= cen;
	sumador: for i in 0 to n-1 
    generate
	sumi: s1bit generic map(retardoxor =>15 ns, retardoand => 10 ns, retardoor => 15 ns)    	port map (x=>a(i), y=>b(i), cen=>c(i), s=>s(i), csal=>c(i+1));
	end generate;
	csal <= c(n)
end estructural;
`````

*Trabajo: En el directorio SUMA/SUMGENERA/CODIGO se suministra un fichero (snbits.vhd) con el esqueleto para generar, utilizando la sentencia “generate”, un sumador de 4 bits a partir de un sumador de 1 bit. Edite el fichero y complete la especificación. En el Apéndice 2.4 se muestran varias posibilidades.*
*Cree un proyecto Quartus en el directorio SUMA/SUMGENERA/QUARTUS. Este proyecto debe utilizar el sumador de 1 bit (s1bit.vhd) como librería de usuario. Analice la elaboración RTL y compruebe el funcionamiento. Para esto último copie el programa de prueba, utilizado en SUM4, en el directorio PRUEBAS de este proyecto y modifíquelo de forma oportuna si es necesario.*

## SUMA EN DECIMAL EN BCD

