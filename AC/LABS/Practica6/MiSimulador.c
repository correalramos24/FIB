#include "CacheSim.h"

#define TAM_BYTE 4

//MAPEO DIRECTO
//4KBYTES TAMAÑO MC = 
//32BYTES POR LINEA = 
//Act simultanea + No migracion fallo escritura

int cache[128];
int valid[128];
int fallos;
int aciertos;
void init_cache ()
{
	for(int i = 0; i < 128; i++) valid[i]=0;
	fallos = 0;
	aciertos = 0;
}

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
	
	replacement = (cache[linea_mc] != tag) && (valid[linea_mc] == 1) && (LE != escriptura);

	if(valid[linea_mc] == 0 && (LE != escriptura)){
		valid[linea_mc] = 1;
		tag_out = 0;
		cache[linea_mc] = tag;
	}
	else if(cache[linea_mc] != tag && (LE != escriptura))
	{
		tag_out = cache[linea_mc];
		cache[linea_mc] = tag;
	}
	else if(cache[linea_mc] == tag) tag_out = 0;

	if(LE == lectura)
	{
		esc_mp = false;
		mida_esc_mp = 0;
		if(miss){
			lec_mp = true;
			mida_lec_mp = 32;
		}
		else{
			lec_mp = false;
			mida_lec_mp = 0;
		}
	}
	if(LE == escriptura)
	{
		lec_mp = false;
		mida_lec_mp = 0;
		esc_mp = true;
		mida_esc_mp = 1;	
	}

	test_and_print (address, LE, byte, bloque_m, linea_mc, tag,
			miss, lec_mp, mida_lec_mp, esc_mp, mida_esc_mp,
			replacement, tag_out);
}

/* La rutina final es cridada al final de la simulacio */ 
void final ()
{
 	/* Escriu aqui el teu codi */ 
	printf("fallos: %d \n", fallos); 
  	printf("hits: %d \n", aciertos);
}
