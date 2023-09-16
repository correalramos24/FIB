| fulladder.cmd
| Written 1/24/02 David_Harris@hmc.edu
|
| Simulate a full adder for Lab 2.
|  Note: this file does not check all input combinations.

| 000
l a b c
s 2
assert s 0
assert cout 0

| 001
h a
s 2
assert s 1
assert cout 0

| 010
l a
h b
s 2
assert s 1
assert cout 0

| 011
h a
s 2
assert s 0
assert cout 1


| 100
l b a
h c
s 2
assert s 1
assert cout 0


| 101
h a
s 2
assert s 0
assert cout 1


| 110
l a
h b
s 2
assert s 0
assert cout 1


| 111
h a
s 2
assert s 1
assert cout 1