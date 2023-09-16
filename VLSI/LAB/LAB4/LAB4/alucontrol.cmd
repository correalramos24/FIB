| alucontrol.cmd
| Written 2/12/02 David_Harris@hmc.edu
|
| Simulate the alucontrol block.
| only consider legal instructions
| Note: this file is incomplete

vector aluop aluop[1] aluop[0]
vector funct funct[3] funct[2] funct[1] funct[0]
vector alucontrol alucontrol[2] alucontrol[1] alucontrol[0]

| test always adding
set aluop 00
s 2
assert alucontrol 010

| test always subtracting
set aluop 01
s 2
assert alucontrol 110

| test funct ADD
set aluop 10
set funct 0000
s 2
assert alucontrol 010

| add code here to test SUB, AND, OR, SLT

| test always SUB
set aluop 10
set funct 0010
s 2
assert alucontrol 110

| test always AND
set aluop 10
set funct 0100
s 2
assert alucontrol 000

| test always OR
set aluop 10
set funct 0101
s 2
assert alucontrol 001

| test always SLT
set aluop 10
set funct 1010
s 2
assert alucontrol 111



