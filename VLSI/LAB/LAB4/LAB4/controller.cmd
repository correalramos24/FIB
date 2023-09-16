| controller.cmd
| Written 2/12/02 David_Harris@hmc.edu
|
| Simulate the controller for lab 4.

| define vectors for convenience
vector op op5 op4 op3 op2 op1 op0
vector irwrite irwrite3 irwrite2 irwrite1 irwrite0
vector aluop aluop1 aluop0
vector alusrcb alusrcb1 alusrcb0
vector pcsource pcsource1 pcsource0

| define a 2-phase nonoverlapping clock
| in which ph1 goes high then low
| then ph2 goes high then low
| with a time of 2 ns for each step (8 ns cycle time)
clock ph1 1 0 0 0
clock ph2 0 0 1 0
stepsize 2

| reset the system by setting reset high and clocking twice
h reset
l zero
c 
l reset

| ***** test lb
| state 0
set op 010000
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 1000
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 1
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0100
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 2
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0010
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 3
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0001
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 4
c
assert alusrca 0
assert alusrcb 11
assert aluop 00

| state 5
c
assert alusrca 1
assert alusrcb 10
assert aluop 00

| state 6
c
assert memread 1
assert iord 1

| state 7
c
assert regdst 0
assert regwrite 1
assert memtoreg 1

| ***** test sb
| state 0
set op 011000
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 1000
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 1
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0100
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 2
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0010
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 3
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0001
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 4
c
assert alusrca 0
assert alusrcb 11
assert aluop 00

| state 5
c
assert alusrca 1
assert alusrcb 10
assert aluop 00

| state 8
c
assert memwrite 1
assert iord 1

| ***** test R-type instructions
| state 0
set op 000000
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 1000
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 1
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0100
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 2
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0010
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 3
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0001
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 4
c
assert alusrca 0
assert alusrcb 11
assert aluop 00

| state 9
c
assert alusrca 1
assert alusrcb 00
assert aluop 10

| state 10
c
assert regdst 1
assert regwrite 1
assert memtoreg 0

| ***** test beq
| state 0
set op 000100
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 1000
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 1
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0100
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 2
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0010
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 3
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0001
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 4
c
assert alusrca 0
assert alusrcb 11
assert aluop 00

| state 11
c
assert alusrca 1
assert alusrcb 00
assert aluop 01
assert pcsource 01
assert pcchange 0

| check that zero works in state 11
h zero
s
assert pcchange 1

| ***** test j
| state 0
set op 000010
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 1000
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 1
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0100
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 2
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0010
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 3
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0001
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 4
c
assert alusrca 0
assert alusrcb 11
assert aluop 00

| state 12
c
assert pcsource 10
assert pcchange 1

| ***** test addi
| state 0
set op 001000
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 1000
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 1
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0100
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 2
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0010
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 3
c
assert memread 1
assert alusrca 0
assert iord 0
assert irwrite 0001
assert alusrcb 01
assert aluop 00
assert pcchange 1
assert pcsource 00

| state 4
c
assert alusrca 0
assert alusrcb 11
assert aluop 00

| state 13
c
assert alusrca 1
assert alusrcb 10
assert aluop 00

| state 14
c
assert regdst 0
assert regwrite 1
assert memtoreg 0

| *** all MIPS instructions tested