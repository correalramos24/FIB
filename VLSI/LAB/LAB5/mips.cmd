| mips.cmd
| Written 2/14/02 David_Harris@hmc.edu
|
| Simulate the MIPS processor for lab 5.
| Updated 1/17/04 dh with better test program
| instructions 3 and 4 are missing

|********************************
| define vectors for convenience
|********************************
|vector op op[5] op[4] op[3] op[2] op[1] op[0]
|vector irwrite irwrite[3] irwrite[2] irwrite[1] irwrite[0]
|vector aluop aluop[1] aluop[0]
|vector alusrcb alusrcb[1] alusrcb[0]
|vector pcsource pcsource[1] pcsource[0]
|vector alucontrol alucontrol[2] alucontrol[1] alucontrol[0]
vector memdata memdata[7] memdata[6] memdata[5] memdata[4] memdata[3] memdata[2] memdata[1] memdata[0]
vector adr adr[7] adr[6] adr[5] adr[4] adr[3] adr[2] adr[1] adr[0]
vector writedata writedata[7] writedata[6] writedata[5] writedata[4] writedata[3] writedata[2] writedata[1] writedata[0]

|********************************
| define a 2-phase nonoverlapping clock
| with a time of 5 ns for each step (40 ns cycle time)
|********************************
clock ph1 0 0 0 0 0 1 1 0
clock ph2 0 1 1 0 0 0 0 0
stepsize 5

|********************************
| reset the system by setting reset high and clocking
|********************************
h reset
c 
c
l reset

|********************************
| lb $2, 68($0)			# initialize $2 = 5		40020044
|********************************

| state 0
set memdata		01000000
assert adr 		00000000
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		00000010
assert adr 		00000001
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00000000
assert adr 		00000010
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		01000100
assert adr 		00000011
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state5
assert memread 		0
assert memwrite 	0
c

| state 6           44
set memdata		00000101
assert adr		01000100
assert memread 		1
assert memwrite 	0
c

| state7
assert memread 		0
assert memwrite 	0
c

|********************************
| lb $7, 64($0)			# initialize $7 = 3		40070040
|********************************

| state 0           4
set memdata		01000000
assert adr 		00000100
assert memread 		1
assert memwrite 	0
c

| state 1           5
set memdata		00000111
assert adr 		00000101
assert memread 		1
assert memwrite 	0
c

| state 2           6
set memdata		00000000
assert adr 		00000110
assert memread 		1
assert memwrite 	0
c

| state 3           7
set memdata		01000000
assert adr 		00000111
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state5
assert memread 		0
assert memwrite 	0
c

| state 6           64    
set memdata		00000011
assert adr		01000000
assert memread 		1
assert memwrite 	0
c

| state7
assert memread 		0
assert memwrite 	0
c

| insert test vectors below to handle 3rd and 4th instructions (lb and or)

|********************************
| lb $3, 69($7)			# initialize $3 = 12		40e30045 3+69
|********************************

|state 0 Instruction Fetch High @ 0x40
set memdata         01000000
assert adr          00001000
assert memread      1
assert memwrite     0
c

|state 1 @ 0xe3
set memdata         11100011
assert adr          00001001
assert memread      1
assert memwrite     0
c

|state 2 0x00
set memdata         00000000
assert adr          00001010
assert memread      1
assert memwrite     0
c

|state 3 0x45
set memdata         01000101
assert adr          00001011
assert memread      1
assert memwrite     0
c

|state 4 Instruction DECODE
assert memread 		0
assert memwrite 	0
c

| state 5 @ MEM COMPUTATION -> 69+$7
assert memread 		0
assert memwrite 	0
c

|state 6 MEM ACCESS -> @(3+69) -> 12
set memdata         00001100
assert adr          01001000
assert memread 		1
assert memwrite 	0
c

|state 7 Write REG $3
assert memread 		0
assert memwrite 	0
c

|********************************
| or $4, $7, $2			# $4 <= 3 or 5 = 7		00e22025
|********************************

|state 0 Instruction Fetch High @ 0x00
set memdata         00000000
assert adr          00001100
assert memread      1
assert memwrite     0
c

|state 1 @                      0xE2
set memdata         11100010
assert adr          00001101
assert memread      1
assert memwrite     0
c

|state 2                        0x20
set memdata         00100000
assert adr          00001110
assert memread      1
assert memwrite     0
c

|state 3                        0x25
set memdata         00100101
assert adr          00001111
assert memread      1
assert memwrite     0
c

| state 4   Instruction DECODE
assert memread 		0
assert memwrite 	0
c

| state 9   Execution
assert memread 		0
assert memwrite 	0
c

| state 10  R-typecompletion
assert memread 		0
assert memwrite 	0
c

|********************************
| and $5, $3, $4		# $5 <= 12 and 7 = 4		00642824
|********************************

| state 0
set memdata		00000000
assert adr 		00010000
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		01100100
assert adr 		00010001
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00101000
assert adr 		00010010
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00100100
assert adr 		00010011
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 9
assert memread 		0
assert memwrite 	0
c

| state 10
assert memread 		0
assert memwrite 	0
c

|********************************
| add $5, $5, $4		# $5 <= 4 + 7 = 11		00a42820
|********************************

| state 0
set memdata		00000000
assert adr 		00010100
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		10100100
assert adr 		00010101
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00101000
assert adr 		00010110
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00100000
assert adr 		00010111
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 9
assert memread 		0
assert memwrite 	0
c

| state 10
assert memread 		0
assert memwrite 	0
c

|********************************
| beq $5, $7, end		# shouldn't be taken		10a70008
|********************************

| state 0
set memdata		00010000
assert adr 		00011000
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		10100111
assert adr 		00011001
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00000000
assert adr 		00011010
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00001000
assert adr 		00011011
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 11
assert memread 		0
assert memwrite 	0
c

|********************************
| slt $6, $3, $4		# $6 <= 12 < 7 = 0		0064302a
|********************************

| state 0
set memdata		00000000
assert adr 		00011100
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		01100100
assert adr 		00011101
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00110000
assert adr 		00011110
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00101010
assert adr 		00011111
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 9
assert memread 		0
assert memwrite 	0
c

| state 10
assert memread 		0
assert memwrite 	0
c

|********************************
| beq $6, $0, around		# should be taken		10c00001
|********************************

| state 0
set memdata		00010000
assert adr 		00100000
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		11000000
assert adr 		00100001
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00000000
assert adr 		00100010
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00000001
assert adr 		00100011
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 11
assert memread 		0
assert memwrite 	0
c

|********************************
| slt $6, $7, $2		# $6 <= 3 < 5 = 1		00e2302a
|********************************

| state 0
set memdata		00000000
assert adr 		00101000
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		11100010
assert adr 		00101001
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00110000
assert adr 		00101010
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00101010
assert adr 		00101011
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 9
assert memread 		0
assert memwrite 	0
c

| state 10
assert memread 		0
assert memwrite 	0
c

|********************************
| add $7, $6, $5		# $7 <= 1 + 11 = 12		00c53820
|********************************

| state 0
set memdata		00000000
assert adr 		00101100
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		11000101
assert adr 		00101101
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00111000
assert adr 		00101110
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00100000
assert adr 		00101111
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 9
assert memread 		0
assert memwrite 	0
c

| state 10
assert memread 		0
assert memwrite 	0
c

|********************************
| sub $7, $7, $2		# $7 <= 12 - 5 = 7		00e23822
|********************************

| state 0
set memdata		00000000
assert adr 		00110000
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		11100010
assert adr 		00110001
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00111000
assert adr 		00110010
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00100010
assert adr 		00110011
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 9
assert memread 		0
assert memwrite 	0
c

| state 10
assert memread 		0
assert memwrite 	0
c

|********************************
| j end				# should be taken		0800000f
|********************************

| state 0
set memdata		00001000
assert adr 		00110100
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		00000000
assert adr 		00110101
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00000000
assert adr 		00110110
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00001111
assert adr 		00110111
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 12
assert memread 		0
assert memwrite 	0
c

|********************************
| sb $7, 0($2)			# write adr 5 <= 7		60470000
|********************************

| state 0
set memdata		01100000
assert adr 		00111100
assert memread 		1
assert memwrite 	0
c

| state 1
set memdata		01000111
assert adr 		00111101
assert memread 		1
assert memwrite 	0
c

| state 2
set memdata		00000000
assert adr 		00111110
assert memread 		1
assert memwrite 	0
c

| state 3
set memdata		00000000
assert adr 		00111111
assert memread 		1
assert memwrite 	0
c

| state 4
assert memread 		0
assert memwrite 	0
c

| state 5
assert memread 		0
assert memwrite 	0
c

| state 8
assert memread 		0
assert memwrite 	1
assert writedata	00000111
assert adr		00000101
c