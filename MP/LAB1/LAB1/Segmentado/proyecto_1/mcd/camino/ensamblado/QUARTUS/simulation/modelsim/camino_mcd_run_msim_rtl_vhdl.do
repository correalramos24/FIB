transcript on
if {[file exists rtl_work]} {
	vdel -lib rtl_work -all
}
vlib rtl_work
vmap work rtl_work

vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Secuencial/proyecto_1/tipos_constantes_pkg/retardos_componentes_pkg.vhd}
vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Secuencial/proyecto_1/tipos_constantes_pkg/param_disenyo_pkg.vhd}
vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Secuencial/proyecto_1/mcd/camino/componentes_pkg/componentes_pkg.vhd}
vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Secuencial/proyecto_1/mcd/camino/componentes/MUX/CODIGO/mux2.vhd}
vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Secuencial/proyecto_1/mcd/camino/componentes/REGISTROS/CODIGO/rd_n_pe.vhd}
vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Secuencial/proyecto_1/mcd/camino/componentes/menor/CODIGO/menqu.vhd}
vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Secuencial/proyecto_1/mcd/camino/componentes/igual_cero/CODIGO/igual_cero.vhd}
vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Secuencial/proyecto_1/mcd/camino/componentes/sumador/CODIGO/sumador.vhd}
vcom -93 -work work {C:/Users/victor/Documents/FIBq9/MP/LAB1/LAB1/Segmentado/proyecto_1/mcd/camino/ensamblado/CODIGO/camino_mcd.vhd}

