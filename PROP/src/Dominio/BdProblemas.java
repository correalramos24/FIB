package Dominio;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Classe de datos que organizara todos los problemas del sistema, donde los problemas estan identificados
 * por su tema. Se trata de una classe singeltone.
 * @author victor correal
 */


public class BdProblemas implements Serializable {
    private static BdProblemas instance; //singletone
    Map<String, Problema> misProblemas;

    //======================CONSTRUCTORS======================

    /**
     * Constructora del patron Singletone...
     * @return Instancia de BdProblemas
     */
    public static BdProblemas getInstance(){
        if (instance == null) {
            instance = new BdProblemas();
        }
        return instance;
    }
        
    /**
     * Creadora privada de la base de datos
     */
    private BdProblemas (){
        misProblemas = new TreeMap <>();
    }

    //======================

    /**
     * Devuelve los atributos de un problema en formato de string
     * @param id Id del problema
     * @return
     */
    public String getProblemaInfo(String id) {
        return misProblemas.get(id).toString();
    }

    /**
     * Devuelve los temas de todos los problemas
     * @return Tema de los problemas
     */
    public Set<String> getAllProblemas(){
        return misProblemas.keySet();
    }

    /**
     * Elimina un problema de la base de datos
     * @param id Id del problema a eliminar
     */
    public void delProblema(String id){
        this.misProblemas.remove(id);
    }

    /**
     * Añade un problema a la base de datos
     * @param p Problema a añadir
     * @throws Exception
     */
    public void addProblema(Problema p) throws Exception {
        if(! p.isTeSolucion()) throw new Exception("No tiene solucion el problema con fen" + p.getFEN());
        if(existeProblema(p.getTema())) throw new Exception("Ya existe problema");
        this.misProblemas.put(p.getTema(), p);
    }

    /**
     * Devuelve si existe un problema
     * @param id Id del problema a buscar
     * @return
     */
    public Boolean existeProblema(String id){
        return this.misProblemas.containsKey(id);
    }

    /**
     * Devuelve la cantidad de problemas de la base de datos 
     * @return Cantidad de problemas
     */
    public int getnProblemas() {
        return misProblemas.size();
    }

    /**
     * Modifica los atributos de un problema ya existente
     * @param FEN Nuevo FEN del problema
     * @param nJ Nuevo numero de jugadas
     * @param tema Tema del problema a modificar
     * @param dif Nueva dificultad del problema
     * @return Devuelve si se ha modificado correctamente
     */
    public boolean modificarDatosProblema(String FEN, int nJ, String tema, int dif){
        if(!misProblemas.containsKey(tema)) return false;
        try {
            misProblemas.get(tema).setDificultad(dif);
            misProblemas.get(tema).setnJugadas(nJ);
            misProblemas.get(tema).setFEN(FEN, nJ);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * Devuelve el problema buscado
     * @param id Id del problema a buscar
     * @return Problema buscado
     */
    public Problema getProblema(String id){ return this.misProblemas.get(id); }

    /**
     * Devuelve todos los problemas de la base de datos
     * @return Problemas existentes
     */
    protected Map<String, Problema> getmisProblemas(){
        return this.misProblemas;
    }

    /**
     * Devuelve la representacion grafica del tablero utilizando las
     * representaciones de cada pieza
     * @param taux Problema a buscar
     * @return Representacion grafica de tablero
     */
    public char[][] getTableroProblemaChar(String taux) {
        if(!misProblemas.containsKey(taux)) return null;
        return misProblemas.get(taux).getSituacioInicial().getMatrixCharTablero();
    }

    public String[] getDataProblema(String id) {
        Problema pAux = misProblemas.get(id);
        String[] ret = new String[4];
        ret[0] = pAux.getFEN();
        ret[1] = String.valueOf(pAux.getnJugadas());
        ret[2] = String.valueOf(pAux.getDificultad());
        ret[3] = pAux.getUsuarioCreador();
        return ret;
    }

    public char[][] getTableroInicial(String id) {
        return misProblemas.get(id).getTableroProblema();
    }

}

