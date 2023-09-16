--
-- Copyright (c) 2018, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;

use work.cte_tipos_deco_camino_pkg.all;
use work.param_disenyo_pkg.all;
use work.cte_tipos_UF_pkg.all;
use work.retardos_pkg.all;

entity sumalg is
    port (opALU : in  st_opALU;
          a	: in  tam_dat_camino;
          b	: in  tam_dat_camino;
			 men : out std_logic;
          s	: out tam_dat_camino);
end sumalg;

architecture compor of sumalg is

signal aa, bb, cc, ss: std_logic_vector(tam_dat downto 0);
signal an, bn: std_logic;
signal ent, resta: std_logic; -- ent=1/0 dato entero/natural; resta=1/0 resta/suma 

begin
--	exprese las senyales de control en funcion de opALU
	resta <= '0' when opALU = ALU_ADD else '1';
	ent <= '0' when opALU = ALU_ADD or opALU = ALU_SLTU else '1';

--	extension de rango y vectores a sumar
	an <= '0' when ent = '0' else '1' when a(tam_dat-1) = '1' and ent='1';
	bn <= '0' when ent = '0' else '1' when b(tam_dat-1) = '1' and ent='1';
	aa <= an & a;
	bb <= (bn & b) xor (tam_dat downto 0 => resta); --cambiar signo

--	suma aa + bb + cc
	cc <= (tam_dat downto 1 => '0', 0 => resta); --sumar 1 si hay que restar, cambiar signo()
	ss <= std_logic_vector(unsigned(aa)+unsigned(bb)+unsigned(cc));
	
--	modifique las senyales de salida
	s <= ss(tam_dat-1 downto 0);
	men <= 	'1' when ent = '0' and ss(tam_dat) = '1' else --unsigned: 100-120 = -20 
				'0' when ent = '0' and ss(tam_dat) = '0' else
				'0' when ent = '1' and a(tam_dat -1) = '0' and b(tam_dat-1) = '1' else
				'1' when ent = '1' and a(tam_dat -1) = '1' and b(tam_dat-1) = '0' else
				'0' when ent = '1' and ss(tam_dat) = '0' else 	-- -19 menor que -20 = -19 menor que 20 = -19 + 20 = 1
				'1' when ent = '1' and ss(tam_dat) = '1'; 		--	-19 menor que -15 = -19 menor que 15 = -19 + 15 = -4
end;
