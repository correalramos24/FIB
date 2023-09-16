--
-- Copyright (c) 2018, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;

use work.cte_tipos_deco_camino_pkg.all;
use work.componentes_control_seg_pkg.all;
use work.retardos_cntl_seg_pkg.all;
--LOGICA DETECION RIESGO DE SECUENCIAMINETO
--el bit 3 es el que varia en el modelsim

entity LDRS is
	port(opsecDL, opsecA : in st_opSEC;
		RS: out std_logic);
end LDRS;


architecture comportamiento of LDRS is
	signal auxOR : std_logic;
begin
	orSEC: orv2 port map(a=>opsecA(3), b => opsecDL(3), s=> auxOR);
	RS <= auxOR after retLDRS;
	
end comportamiento;
