package Dominio;

import java.io.Serializable;
import java.util.*;


/**
 * Ranking, que ordena a los jugadores atacantes por el tiempo de la partida sobre un problema concreto.
 * @author victor correal
 */
//todo: comentarios javadoc funciones y atributos
public class Ranking implements Serializable {

    String nombreRanking; //IDEM NOMBREPROBLEMA
    List<Resultado> resultados;
    
   
    /**
     * Creadora de un ranking sin resultados
     * @param nr Nombre del ranking
     */
    public Ranking(String nr){
        this.nombreRanking = nr;
        resultados = new ArrayList<>();
    }

    /**
     * Devuelve la posicion en el ranking del usuario
     * @param usuario Usuario a buscar en el ranking
     * @return Posicion del usuario
     * @throws Exception
     */
    public int getPosicionRanking(String usuario) throws Exception {
        int i = 1;
        for (Resultado r : resultados) {
            if(r.getjAtacante().equals(usuario)) return i;
            i++;
        }
        //throw new Exception("No existe el usuario en el ranking");
        return -1;
    }

    /**
     * Devuelve los tres primeros jugadores con mejor tiempo del ranking
     * @return Jugadores con mejor tiempo
     */
    public Set<String> getTopRanking(){
        Set <String> ret = new TreeSet <>();
        Iterator <Resultado> it = resultados.iterator();
        int i = 0;
        while(it.hasNext() && (i < 3)){
            ret.add(it.next().getjAtacante());
            i++;
        }
        return ret;
    }

    /**
     * Devuelve el numero de usuarios que han hecho el problema del ranking
     * @return Cantidad de jugadores que han jugado
     */
    public int getParticipantes(){
        return resultados.size();
    }

    /**
     * Añade un nuevo resultado al ranking
     * @param r Resultado a añadir
     */
    public void addResultado(Resultado r){
        if(resultados.contains(r)){
            for (Resultado re : resultados)
                if (re.getjAtacante().equals(r.getjAtacante())) {
                    if (r.getTiempo() < re.getTiempo()) resultados.add(r);
                }
        }
        else resultados.add(r);
        Collections.sort(this.resultados, new Comparator<Resultado>() {

            @Override
            public int compare(Resultado o1, Resultado o2) {
                return (int) (o1.getTiempo()-o2.getTiempo());
            }
        });
    }

    /**
     * Devuelve el ranking completo
     * @return Todos los que han jugado
     */
    protected List<Resultado> getAllResultados(){
        return resultados;
    }

}