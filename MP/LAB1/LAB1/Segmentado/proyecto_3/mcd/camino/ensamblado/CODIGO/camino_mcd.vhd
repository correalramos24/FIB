--
-- Copyright (c) 2017 XXXX, UPC
-- All rights reserved.
-- 

library ieee;		
use ieee.std_logic_1164.all;		
use ieee.numeric_std.all;
use work.param_disenyo_pkg.all;
use work.componentes_pkg.all;
--! @image html camino_2.png 

entity camino_mcd is
   port (reloj, pcero, pe_a, pe_b: std_logic;
	ini_a, ini_b: std_logic;
	a, b: in st_dat;
	s: out st_dat;
	ig, meu: out std_logic);
end camino_mcd;

architecture estruc of camino_mcd is
signal mx_a, mx_b, reg_a, reg_b, mx_cam, t_s: st_dat;
signal t_ig, t_meu, mayor: std_logic;

begin

	ma_ini_a: mux2 port map(d1=>a, d0=>reg_a, SEL=>ini_a, s=>mx_a);
	mx_ini_b: mux2 port map(d1=>b, d0=>reg_b, SEL=>ini_b, s=>mx_b);

	mux_swap: mux2 port map(d1=>reg_b, d0=>t_s, SEL=>t_meu, s=>mx_cam);
	
	rega: RD_N_pe port map (reloj => reloj, pcero => pcero, pe => pe_a, e => mx_cam, s => reg_a);
	regb: RD_N_pe port map (reloj => reloj, pcero => pcero, pe => pe_b, e => reg_a, s => reg_b);

	menor: menqu port map (L1 => mx_a, L2 => mx_b, meu => t_meu);
	resta: sumador port map (a => mx_a, b => mx_b, s => t_s);
	ig_cero: igual_cero port map (L1 => reg_b, ig => t_ig);

	ig <= t_ig;
	s <= ;

end estruc;