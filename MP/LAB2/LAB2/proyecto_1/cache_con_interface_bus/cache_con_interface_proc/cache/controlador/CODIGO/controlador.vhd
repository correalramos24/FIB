--
-- Copyright (c) 2017 XXXX, UPC
-- All rights reserved.
-- 

library ieee;
use ieee.std_logic_1164.all;

use work.param_disenyo_pkg.all;
use work.controlador_pkg.all;
use work.retardos_controlador_pkg.all;
use work.acciones_pkg.all;
use work.procedimientos_controlador_pkg.all;
--! @image html controlador.png 

entity controlador is
port (reloj, pcero: in std_logic;
		pet: in tp_contro_e;
		s_estado: in tp_contro_cam_estado;
		s_control: out tp_contro_cam_cntl;
		resp: out tp_contro_s;
		resp_m: in tp_cntl_memoria_e;
		pet_m: out tp_cntl_memoria_s);
end;
  

	
	
architecture compor of controlador is

--type tipoestado is (DES0, DES, CMPETIQ, INI, ESCINI, LEC, PML, PMEA, PMEF, ESPL, ESPEA, ESPEF, ESB, ESCP, HECHOL, HECHOE);
signal estado, prxestado: tipoestado;
signal derechos_acceso: std_logic;
	
begin
	
-- determinacion de los derechos de acceso al bloque
derechos_acceso <= '1' when (s_estado.AF and s_estado.EST) = '1' else '0';

prxestado <= 	DES0 when pcero = '1' or (estado = DES0 and not hay_peticion_procesador(pet)) else
					INI when estado = DES0 and hay_peticion_ini_procesador(pet) else
					CMPETIQ when (estado = DES0 or estado = DES) and hay_peticion_procesador(pet) else 
					ESCINI when estado = INI else
					HECHOE when estado = ESCINI else
					DES when not hay_peticion_procesador(pet) or estado = HECHOE else 
          LEC when estado = CMPETIQ and es_acierto_lectura(pet, derechos_acceso) else
          HECHOL when estado = LEC;
					
					--
estado <= prxestado after retardo_estado;

logi_sal: process(estado, pcero, pet, s_estado, resp_m)
variable v_s_control: tp_contro_cam_cntl;
variable v_pet_m: tp_cntl_memoria_s;
variable v_resp: tp_contro_s;
begin
	por_defecto(v_s_control, v_pet_m, v_resp);
	if (pcero = not puesta_cero) then
		case estado is
			when DES0 => 
				if hay_peticion_procesador(pet) then
					interfaces_DES(v_resp);
				end if;
			when INI => 
				interfaces_en_CURSO(v_resp);
			when ESCINI =>
				interfaces_en_CURSO(v_resp);
				actualizar_etiqueta(v_s_control);
				actualizar_estado(v_s_control, contenedor_valido);
				actualizar_dato(v_s_control);
			when HECHOE =>
				interfaces_HECHOE(v_resp);
			when DES => 
				if hay_peticion_procesador(pet) then
					interfaces_DES(v_resp);
					lectura_etiq_estado(v_s_control); --siguiente ciclo
				end if;
			when CMPETIQ => 
				interfaces_en_CURSO(v_resp);
			when LEC => 
				interfaces_en_CURSO(v_resp);
				lectura_datos(v_s_control);
			when HECHOL =>
				interfaces_HECHOL(v_resp);
			when PML => 
				interfaces_en_CURSO(v_resp);
				peticion_memoria_lectura(v_pet_m);
			when ESPL => 
				interfaces_en_CURSO(v_resp);
			when ESB => 
				interfaces_en_CURSO(v_resp);
				actualizar_etiqueta(v_s_control);
				actualizar_estado(v_s_control, contenedor_valido);
				actu_datos_desde_bus(v_s_control);
			when PMEA => 
				interfaces_en_CURSO(v_resp);
				peticion_memoria_escritura(v_pet_m);
			when ESPEA =>
				interfaces_en_CURSO(v_resp);
			when ESCP => 
				interfaces_en_CURSO(v_resp);
				actualizar_dato(v_s_control);
			when PMEF => 
				interfaces_en_CURSO(v_resp);
				peticion_memoria_escritura(v_pet_m);
			when ESPEF =>
				interfaces_en_CURSO(v_resp);
			when others =>
		end case;
	end if; 

s_control <= v_s_control after retardo_logica_salida;
pet_m <= v_pet_m after retardo_logica_salida;
resp <= v_resp after retardo_logica_salida;

end process;

end;

