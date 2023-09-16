--
-- Copyright (c) 2018, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;

use work.param_disenyo_pkg.all;
use work.cte_tipos_deco_camino_pkg.all;
use work.componentes_control_seg_pkg.all;
use work.retardos_cntl_seg_pkg.all;
--LDD: LOGICA DETECCIÓN DEPENDENCIA DE DATOS->
---> LA SALIDA IDL{1|2}x INDICA SI EN LA ETAPA x SE ESTA CALCULANDO EL REGISTRO FUENTE
-- DE LA ETAPA D/L

entity LDD is
	port(IDL1, IDL2 : in dir_reg; --ID REG FUENTE EN D/L
		valIDL1, valIDL2 : in std_logic; --VALIDEZ IDENTIFICADORES REGS
		rdA, rdM, rdF, rdE : in dir_reg; --ID REG DESTINO ETAPAS...
		PBRA, PBRM, PBRF, PBRE : in st_PBR; --PERMISO ESCR ETAPAS...
		IDL1A, IDL1M, IDL1F, IDL1E: out std_logic; 
		IDL2A, IDL2M, IDL2F, IDL2E: out std_logic);
end LDD;


architecture estructural of LDD is


signal cmp0REG1, cmp0REG2: std_logic;

--generate
type arrayRDx is array (0 to 3) of dir_reg; --entradas rdX
type arrayPBRx is array (0 to 3) of st_PBR; --entradas PRBx

signal RDx : arrayRDx;
signal PBRx : arrayPBRx;

signal resCMP, resAND: std_logic_vector(7 downto 0);
signal RES : std_logic_vector(7 downto 0); -- lo que devolveremos en orden


begin
	RDx <= (rdA, rdM, rdF, rdE);
	PBRx <= (PBRA, PBRM, PBRF, PBRE);
	cmpr0IDL1: cmp port map (a => IDL1, b => (others => '0'), ig => cmp0REG1);
	cmpr0IDL2: cmp port map (a => IDL2, b => (others => '0'), ig => cmp0REG2);
	
	ldd: for i in 0 to 7 generate
		ldd1:if(i <= 3) generate
			CMP1i: cmp port map (a => RDx(i), b => IDL1, ig => resCMP(i));
			and1i: andv3 port map(a => resCMP(i), b => valIDL1, c => PBRx(i), s => resAND(i));
			ann1i: andv1n port map (a => resAND(i), b => cmp0REG1, s => RES(i));
		end generate ldd1;
		ldd2:if(i > 3) generate
			CMP2i: cmp port map (a => RDx(i-4), b => IDL2, ig => resCMP(i));
			and2i: andv3 port map(a => resCMP(i), b => valIDL2, c => PBRx(i-4), s => resAND(i));
			ann2i: andv1n port map (a => resAND(i), b => cmp0REG2, s => RES(i));
		end generate ldd2;
	end generate ldd;

	
	IDL1A <= RES(0) after retLDD;
	IDL1M <= RES(1) after retLDD;
	IDL1F <= RES(2) after retLDD;
	IDL1E <= RES(3) after retLDD;
	
	IDL2A <= RES(4) after retLDD;
	IDL2M <= RES(5) after retLDD;
	IDL2F <= RES(6) after retLDD;
	IDL2E <= RES(7) after retLDD;
	
end estructural;
