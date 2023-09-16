--
-- Copyright (c) 2018, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;

use work.param_disenyo_pkg.all;
use work.retardos_cntl_seg_C_pkg.all;

entity LDRD_C is
	port(IDL1A, IDL1M, IDL1F, IDL1E: in std_logic;
		IDL2A, IDL2M, IDL2F, IDL2E: in std_logic;
		latphA: in std_logic;
		latphM: in std_logic;
		RD : out std_logic);
end LDRD_C;


architecture comportamiento of LDRD_C is
signal noIguales, RDA, RDM: std_logic;
begin
	noIguales <= (not IDL1A and IDL1M) or (not IDL2A and IDL2M);
	RDA <= latphA and (IDL1A or IDL2A);
	RDM <= latphM and noIguales;
	RD <= RDA or RDM after retLDRD_C;
	
end comportamiento;
