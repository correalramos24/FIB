transcript on
if {[file exists rtl_work]} {
	vdel -lib rtl_work -all
}
vlib rtl_work
vmap work rtl_work

vcom -93 -work work {/dades/victor.correal/ac2LAB/LAB2/LAB2/SUMA/SUM4/CODIGO/S4bits.vhd}
vcom -93 -work work {/dades/victor.correal/ac2LAB/LAB2/LAB2/SUMA/COMPONENTES/SUM1BIT/CODIGO/S1bit.vhd}

vcom -93 -work work {/dades/victor.correal/ac2LAB/LAB2/LAB2/SUMA/SUM4/PRUEBAS/prueba_s4bits.vhd}

vsim -t 1ps -L altera -L lpm -L sgate -L altera_mf -L altera_lnsim -L cycloneiv_hssi -L cycloneiv_pcie_hip -L cycloneiv -L rtl_work -L work -voptargs="+acc"  prueba_S4bits

add wave *
view structure
view signals
run -all
