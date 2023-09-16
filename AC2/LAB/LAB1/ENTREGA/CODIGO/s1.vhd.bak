library ieee;
use ieee.std_logic_1164.all;


entity s1bitRetardo is
 port (
		x: in	std_logic;
		y: in std_logic;
		cen: in std_logic;
		s: out std_logic;
		csal: out std_logic );
 end s1bitRetardo;

 architecture flujodedatos of s1bitRetardo is
	constant retardoxor: time := 15 ns;
	constant retardoand: time := 10 ns;
	constant retardoor: time := 15 ns;
	signal xorxy, andxy, andxcen, andycen : std_logic;

 begin
	xorxy <= x xor y after retardoxor;
	s <= xorxy xor cen after retardoxor;
	andxy <= x and y after retardoand;
	andxcen <= x and cen after retardoand;
	andycen <= y and cen after retardoand;
	csal <= andxy or andxcen or andycen after retardoor;

		
end flujodedatos;
