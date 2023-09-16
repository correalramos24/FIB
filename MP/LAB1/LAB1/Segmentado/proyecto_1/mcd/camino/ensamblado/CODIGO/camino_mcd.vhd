--
-- Copyright (c) 2017 XXXX, UPC
-- All rights reserved.
-- 

library ieee;		
use ieee.std_logic_1164.all;		
use ieee.numeric_std.all;
use work.param_disenyo_pkg.all;
use work.componentes_pkg.all;

--! @image html camino_1.png 

entity camino_mcd is
   port (reloj, pcero, pe_a, pe_b: std_logic;
	ini: std_logic;
	a, b: in st_dat;
	s: out st_dat;
	ig, meu: out std_logic);
end camino_mcd;

architecture estruc of camino_mcd is

	signal reg_a, reg_b, a_menos_b, swap_a_b: st_dat;
	signal muxA, muxB : st_dat;
	signal a_esMenor_b, esCero_b: std_logic;

begin
	--la condicion esta negada en el doc, invertimos las entradas del mux.
	mx_swap: mux2 port map(d1 => reg_b, d0 => a_menos_b, SEL => a_esMenor_b, s => swap_a_b);
	mx_ini_a: mux2 port map(d0 => swap_a_b, d1 => a, SEL => ini, s => muxA);
	mx_ini_b: mux2 port map(d0 => reg_a, d1 => b, SEL => ini, s => muxB);

	rega: RD_N_pe port map (reloj => reloj, pcero => pcero, pe => pe_a, e => muxA, s => reg_a);
	regb: RD_N_pe port map (reloj => reloj, pcero => pcero, pe => pe_b, e => muxB, s => reg_b);

	--salidas de los rega y regb. resultado es a_esMenor_b
	menor: menqu port map (L1 => reg_a, L2 => reg_b, meu => a_esMenor_b);
	
	--comprobar ==0 reg_b
	ig_cero: igual_cero port map (L1 => reg_b, ig => esCero_b);
	
	resta: sumador port map (a => reg_a, b => reg_b, s => a_menos_b);
	
	
	s <= reg_a;
	ig <= esCero_b;
	
end estruc;
