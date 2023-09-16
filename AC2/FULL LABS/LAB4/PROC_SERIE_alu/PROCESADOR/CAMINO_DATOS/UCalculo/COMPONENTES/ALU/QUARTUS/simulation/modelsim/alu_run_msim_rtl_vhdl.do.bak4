transcript on
if {[file exists rtl_work]} {
	vdel -lib rtl_work -all
}
vlib rtl_work
vmap work rtl_work

vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE/tipos_constantes_pkg/riscv32_coop_funct_pkg.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE/tipos_constantes_pkg/retardos_pkg.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE/tipos_constantes_pkg/param_disenyo_pkg.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE/tipos_constantes_pkg/cte_tipos_deco_camino_pkg.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE/tipos_constantes_pkg/cte_tipos_UF_pkg.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/CODIGO/componentes_ALU_pkg.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/CODIGO/mx2.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/CODIGO/alu.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/CODIGO/despla.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/CODIGO/logica.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/CODIGO/sumalg.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/CODIGO/slt.vhd}

vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/QUARTUS/../PRUEBAS/alu_ref.vhd}
vcom -93 -work work {D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/QUARTUS/../PRUEBAS/prueba_alu.vhd}

vsim -t 1ps -L altera -L lpm -L sgate -L altera_mf -L altera_lnsim -L cycloneiv_hssi -L cycloneiv_pcie_hip -L cycloneiv -L rtl_work -L work -voptargs="+acc"  prueba_alu

do D:/FIBQ7/AC2/LAB4 AC2/PROC_SERIE_alu/PROCESADOR/CAMINO_DATOS/UCalculo/COMPONENTES/ALU/QUARTUS/../PRUEBAS/formato_ventanas.do
