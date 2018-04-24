#include "CacheSim.h"

/* Posa aqui les teves estructures de dades globals
 * per mantenir la informacio necesaria de la cache
 * */

int cache_v0[64];
int valid_v0[64];
int cache_v1[64];
int valid_v1[64];
int ultimo[64];
int acierto = 0;
int fallos = 0;
/* La rutina init_cache es cridada pel programa principal per
 * inicialitzar la cache.
 * La cache es inicialitzada al començar cada un dels tests.
 * */
void init_cache ()
{
    totaltime=0.0;
	/* Escriu aqui el teu codi */
	int i = 0;
	for(i = 0; i<64; i++){
		valid_v0[i]=0;
		valid_v1[i]=0;
		ultimo[i]=0;
	}


}

/* La rutina reference es cridada per cada referencia a simular */ 
void reference (unsigned int address)
{	
	unsigned int byte;
	unsigned int bloque_m; 
	unsigned int conj_mc;
	unsigned int via_mc;
	unsigned int tag;

	unsigned int miss;	   // boolea que ens indica si es miss
	unsigned int replacement;  // boolea que indica si es reemplaça una linia valida
	unsigned int tag_out;	   // TAG de la linia reemplaçada
	float t1,t2;		// Variables per mesurar el temps (NO modificar)
	
	t1=GetTime();
	/* Escriu aqui el teu codi */
	byte = address & 31;
	bloque_m = address / 32;
	conj_mc = bloque_m & 63;
	tag = bloque_m / 64;
	
	miss = ((valid_v0[conj_mc] == 0 || cache_v0[conj_mc] != tag) && valid_v1[conj_mc] == 0) || (cache_v0[conj_mc] != tag && cache_v1[conj_mc] != tag);
	if (miss) fallos++;
	else acierto++;
	replacement = !((valid_v0[conj_mc] == 0 || cache_v0[conj_mc] != tag) && valid_v1[conj_mc] == 0) && (cache_v0[conj_mc] != tag && cache_v1[conj_mc] != tag);

	if (valid_v0[conj_mc] == 0 && valid_v1[conj_mc] == 0){
		valid_v0[conj_mc] = 1;
		tag_out = 0;
		via_mc = 0;
		ultimo[conj_mc]=1;
		cache_v0[conj_mc] = tag;
	} else if (cache_v0[conj_mc] != tag && valid_v1[conj_mc] == 0){
		valid_v1[conj_mc] = 1;
		tag_out = 0;
		ultimo[conj_mc]=0;
		via_mc = 1;
		cache_v1[conj_mc] = tag;
	} else if (cache_v0[conj_mc] != tag && cache_v1[conj_mc] != tag){
		if (ultimo[conj_mc] == 0){
			via_mc = 0;
			ultimo[conj_mc] = 1;
			tag_out = cache_v0[conj_mc];
			cache_v0[conj_mc] = tag;	
		} else if (ultimo[conj_mc] == 1){
			via_mc = 1;
			ultimo[conj_mc] = 0;
			tag_out = cache_v1[conj_mc];
			cache_v1[conj_mc] = tag;	
		}
	} else if(cache_v0[conj_mc] == tag || cache_v1[conj_mc] == tag){
		tag_out = 0;
		if (cache_v0[conj_mc] == tag){
			via_mc = 0;
			ultimo[conj_mc] = 1;	
		} else if (cache_v1[conj_mc] == tag){
			via_mc = 1;
			ultimo[conj_mc] = 0;	
		}
	}

	/* La funcio test_and_print escriu el resultat de la teva simulacio
	 * per pantalla (si s'escau) i comproba si hi ha algun error
	 * per la referencia actual. També mesurem el temps d'execució
	 * */
	t2=GetTime();
	totaltime+=t2-t1;
	test_and_print2 (address, byte, bloque_m, conj_mc, via_mc, tag,
			miss, replacement, tag_out);
}

/* La rutina final es cridada al final de la simulacio */ 
void final ()
{
 	/* Escriu aqui el teu codi */ 
  	printf("fallos: %d\n",fallos);
	printf("aciertos: %d\n",acierto);
  
}
