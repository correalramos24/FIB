--
-- Copyright (c) 2018, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;

use work.param_disenyo_pkg.all;
use work.componentes_control_seg_pkg.all;
use work.retardos_cntl_seg_pkg.all;


--EN FUNCION DE LDD, GENERA LAS SEÑALES DE RIESGO:
--> OR DE TODO
entity LDRD is
	port(IDL1A, IDL1M, IDL1F, IDL1E: in std_logic;
		IDL2A, IDL2M, IDL2F, IDL2E: in std_logic;
		RD : out std_logic);
end LDRD;


architecture comportamiento of LDRD is

signal or1AMres, or1FEres, or2AMres, or2FEres: std_logic;
signal or1AMFE, or2AMFE : std_logic;
signal ret : std_logic;
begin
	or1AM: orv2 port map(a => IDL1A, b=> IDL1M, s=> or1AMres);
	or1FE: orv2 port map(a => IDL1F, b=> IDL1E, s=> or1FEres);
	or1: orv2 port map(a=>or1AMres, b=> or1FEres, s=> or1AMFE);
	
	or2AM: orv2 port map(a => IDL2A, b=> IDL2M, s=> or2AMres);
	or2FE: orv2 port map(a => IDL2F, b=> IDL2E, s=> or2FEres);
	or2: orv2 port map(a=>or2AMres, b=> or2FEres, s=> or2AMFE);
	
	orRET: orv2 port map(a=>or1AMFE, b=> or2AMFE, s=> ret);
	
	RD <= ret after retLDRD;
	
end comportamiento;
