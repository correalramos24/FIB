; Pad Frame arrangement file
; David_Harris@hmc.edu
; 12/8/01
; Generate a padframe for a 40-pin MOSIS tinychip schematic

; specify the cell library with the pads
celllibrary muddpads11_ami05.elib

; create a top-level facet containing the padframe and core
facet chip{sch}

; place this facet as the "core"
core mips{ic}

; set the alignment of the pads (specifying input and output port names)
align pad_corner{ic}      dvdd-1 dvdd
align pad_in{ic}          dvdd-1 dvdd
align pad_out{ic}         dvdd-1 dvdd
align pad_analog{ic}	  dvdd-1 dvdd
align pad_dvdd{ic}        dvdd-1 dvdd
align pad_dgnd{ic}        dvdd-1 dvdd

;; replace the pad_in and pad_out statements with the pad frame arrangement you want.
;; keep at least two pad_dvdd and pad_dgnd pads somewhere on the chip to supply
;; power and ground to the pads and core

; place the top edge of pads, starting with upper-right
place pad_corner{ic}
place pad_out{ic} dout=memread export pad=memread
place pad_out{ic} dout=memwrite export pad=memwrite
place pad_in{ic} din=ph1 export pad=ph1
place pad_in{ic} din=ph2 export pad=ph2
place pad_in{ic} din=memdata[7] export pad=memdata[7]
place pad_in{ic} din=memdata[6] export pad=memdata[6]
place pad_in{ic} din=memdata[5] export pad=memdata[5]
place pad_in{ic} din=memdata[4] export pad=memdata[4]
place pad_in{ic} din=memdata[3] export pad=memdata[3]
place pad_in{ic} din=memdata[2] export pad=memdata[2]

; place the left edge of pads
rotate cc
place pad_corner{ic}
place pad_in{ic} din=memdata[1] export pad=memdata[1]
place pad_in{ic} din=memdata[0] export pad=memdata[0]
place pad_out{ic} dout=writedata[7] export pad=writedata[7]
place pad_out{ic} dout=writedata[6] export pad=writedata[6]
place pad_out{ic} dout=writedata[5] export pad=writedata[5]
place pad_out{ic} dout=writedata[4] export pad=writedata[4]
place pad_out{ic} dout=writedata[3] export pad=writedata[3]
place pad_out{ic} dout=writedata[2] export pad=writedata[2]
place pad_out{ic} dout=writedata[1] export pad=writedata[1]
place pad_out{ic} dout=writedata[0] export pad=writedata[0]

; place the bottom edge of pads
rotate cc
place pad_corner{ic}
place pad_out{ic} dout=adr[7] export pad=adr[7]
place pad_out{ic} dout=adr[6] export pad=adr[6]
place pad_out{ic} dout=adr[5] export pad=adr[5]
place pad_out{ic} dout=adr[4] export pad=adr[4]
place pad_out{ic} dout=adr[3] export pad=adr[3]
place pad_out{ic} dout=adr[2] export pad=adr[2]
place pad_out{ic} dout=adr[1] export pad=adr[1]
place pad_out{ic} dout=adr[0] export pad=adr[0]
place pad_dvdd{ic} 
place pad_dgnd{ic}


; place the right edge of pads
rotate cc
place pad_corner{ic}
place pad_dgnd{ic}
place pad_dvdd{ic} 
place pad_dgnd{ic}
place pad_dvdd{ic} 
place pad_dgnd{ic}
place pad_dvdd{ic} 
place pad_dgnd{ic}
place pad_in{ic} din=reset export pad=reset
place pad_dgnd{ic} export pad = GND
place pad_dvdd{ic} export pad = VDD