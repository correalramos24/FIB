transcript on
if {[file exists rtl_work]} {
	vdel -lib rtl_work -all
}
vlib rtl_work
vmap work rtl_work

vcom -93 -work work {/home2/users/alumnes/1203200/dades/ac2LAB/LAB1/FUNCIONAL/CODIGO/s1bit.vhd}

