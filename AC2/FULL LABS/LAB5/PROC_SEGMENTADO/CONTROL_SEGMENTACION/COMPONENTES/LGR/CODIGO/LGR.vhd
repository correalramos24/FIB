--
-- Copyright (c) 2018, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;

use work.cte_tipos_deco_camino_pkg.all;
use work.componentes_control_seg_pkg.all;
use work.retardos_cntl_seg_pkg.all;
--ACTIVA LAS SEÑALES NECESARRIAS, SEGUN:
-- SEÑAL DEL LDD+LDRD -> EMULA SERIE ->
-- SEÑAL DEL LRDS -> DESCARTA LAS INSTRUCCIONES, HASTA CP NUEVO
-- RID + RS -> 'PRIMERO RID'
entity LGR is
	port(RID, RS : in std_logic;
		bloqCP, bloqBDL, inyecBDL, inyecDLA: out std_logic);
end LGR;


architecture comportamiento of LGR is

signal retORn : std_logic;

begin
	
	RSandnotRID: andv1n port map(a=>RS, b=>RID, s=>retORn);
	
	bloqCP <= RID after retLGR; --mantiene reg CP
	bloqBDL <= RID after retLGR; --mantiene reg BUS-D/L

	inyecDLA <= RID after retLGR; --nop D/L y ALU
	inyecBDL <= retORn after retLGR; --nop B y D/L -> D/L llega nop
	
end comportamiento;
