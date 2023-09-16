--
-- Copyright (c) 2018, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;

use work.param_disenyo_pkg.all;
use work.cte_tipos_deco_camino_pkg.all;

package Rcomponentes_decodificador_ModSecu_pkg is

component Rdecodificador_ModSecu is 
  port (reloj, pcero, B: in std_logic;
		instruc: in tipo_inst_busq; 
--      Pcero: in std_logic;
		fmtS : out st_fmtS;
		MSI : out st_mSI;

		PBR: out st_PBR;
		fmtD: out st_fmtD;
		mL1: out st_mL1;
		mL2: out st_mL2 ;
            
		mES: out st_mES;

		opALU: out st_opALU;
		opMD: out st_opMD;
		opSEC: out st_opSEC;
		CoErr: out std_logic);
end component;

end package Rcomponentes_decodificador_ModSecu_pkg;
