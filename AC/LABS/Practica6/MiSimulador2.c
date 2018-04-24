#include "CacheSim.h"

int cache[128];
int valid[128];
int dirty[128];
int aciertos;
int fallos;
//COPY BACK - WRITE ALLOCATE
//dirty bit - migracion
void init_cache ()
{
	/* Escriu aqui el teu codi */
	fallos = 0;
	aciertos = 0;
	for(int i = 0; i< 128; ++i) valid[i] = 0;
	for(int i = 0; i< 128; ++i) dirty[i] = 0;
}

/* La rutina reference es cridada per cada referencia a simular */ 
void reference (unsigned int address, unsigned int LE)
{
	unsigned int byte;
	unsigned int bloque_m; 
	unsigned int linea_mc;
	unsigned int tag;
	unsigned int miss;
	unsigned int lec_mp;
	unsigned int mida_lec_mp;
	unsigned int esc_mp;
	unsigned int mida_esc_mp;
	unsigned int replacement;
	unsigned int tag_out;

	byte = address & 31;
	bloque_m = address / 32;
	linea_mc = bloque_m & 127;
	tag = bloque_m / 128;

	miss = (valid[linea_mc] == 0) || (cache[linea_mc] != tag);

	if(miss) fallos++;
	else aciertos++;

	replacement = (cache[linea_mc] != tag) && (valid[linea_mc] == 1);
	
	if(valid[linea_mc] == 0){
		valid[linea_mc]	= 1;
		tag_out = 0;
		cache[linea_mc] = tag;
	}	
	else if(cache[linea_mc] != tag){
		tag_out = cache[linea_mc];
		cache[linea_mc] = tag;
	}
	else if(cache[linea_mc] == tag) tag_out = 0;

	if(LE == lectura)
	{
		
		if(miss && replacement)
		{
			lec_mp = true;
			if(dirty[linea_mc] == 1){
				esc_mp = 1;
				mida_esc_mp = 32;
				dirty[linea_mc] = 0;
			}
			else{
				esc_mp = mida_esc_mp = 0;
			}
			mida_lec_mp = 32;
		}
		else if(miss)
		{
			lec_mp = true;
			mida_lec_mp = 32;
			esc_mp = mida_esc_mp = 0;
		}
		else{//HIT:
			lec_mp = mida_lec_mp = 0;
			esc_mp = mida_esc_mp = 0;
		}
	}
	if(LE == escriptura)
	{
		if(miss && replacement)
		{
			if(dirty[linea_mc] == 1){
				esc_mp = 1;
				mida_esc_mp = 32;
				dirty[linea_mc] = 0;
			}
			else{
				esc_mp = 0;
				mida_esc_mp = 0;
			}
			lec_mp = 1;
			mida_lec_mp = 32;
			dirty[linea_mc] = 1;
		}
		else{ //HIT:
			lec_mp = mida_lec_mp = 0;
			esc_mp = mida_esc_mp = 0;
			dirty[linea_mc] = 1;
		}

	}
	/* La funcio test_and_print escriu el resultat de la teva simulacio
	 * per pantalla (si s'escau) i comproba si hi ha algun error
	 * per la referencia actual
	 * */
	test_and_print (address, LE, byte, bloque_m, linea_mc, tag,
			miss, lec_mp, mida_lec_mp, esc_mp, mida_esc_mp,
			replacement, tag_out);
}

/* La rutina final es cridada al final de la simulacio */ 
void final ()
{
 	printf("fallos: %d \n", fallos); 
  	printf("hits: %d \n", aciertos);
}