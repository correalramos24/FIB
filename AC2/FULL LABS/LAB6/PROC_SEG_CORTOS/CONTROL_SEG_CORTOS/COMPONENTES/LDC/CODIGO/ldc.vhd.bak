--
-- Copyright (c) 2018, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;

use work.param_disenyo_pkg.all;
use work.RegDes_pkg.all;
use work.retardos_cntl_seg_C_pkg.all;

entity LDC is
	port(reloj, Pcero: std_logic;
		IDL1A, IDL1M, IDL1F, IDL1E: in std_logic;
		IDL2A, IDL2M, IDL2F, IDL2E: in std_logic;
		C1L1, C2L1, C3L1, C4L1: out std_logic;
		C1L2, C2L2, C3L2, C4L2: out std_logic);
end LDC;

architecture estructural of LDC is

begin
	C1L1 <= '0' after retLDC;
	C2L1 <= '0' after retLDC;
	C3L1 <= '0' after retLDC;
	C4L1 <= '0' after retLDC;
	C1L2 <= '0' after retLDC;
	C2L2 <= '0' after retLDC;
	C3L2 <= '0' after retLDC;
	C4L2 <= '0' after retLDC;

end;

