package Dominio;


import java.io.Serializable;

/**
 * Classe de datos que reprensenta un problema del sistema, identificado por su tema y caracterizado por
 * su FEN, numero de Jugadas para lograr el mate y dificultad.
 * @author Victor Correal Ramos
 */
public class Problema implements Serializable {

    private String tema;
    private int dificultad;
    private int nJugadas;
    private Tablero situacioInicial;
    private Ranking ranking;
    private boolean teSolucion;
    private String color;
    private String usuarioCreador;

    //================CONSTRUCTORS===================

    /**
     * Constructora de un Problema vacio.
     */
    public Problema() {

    }
    //TODO: mirar comprobacion de FEN
    /**
     * Constructora canonica de un problema
     * @param FEN Situacion inicial del tablero, en formato FEN.
     * @param nJugadas Numero de jugadas para alcanzar el mate.
     * @param tema Identificador del problema, normalmente suele ser del tipo: Mate en X por las blancas de .... .
     * @param dificultad Dificultad del problema
     * @throws Exception En caso de introducir un numero de jugadas erroneo(< 1) o una dificultad incorrecta(<0).
     */
    public Problema(String FEN, int nJugadas, String tema, int dificultad) throws Exception {
        if(nJugadas < 1 || dificultad < 0) throw new Exception("Mal formato de entrada");
        this.tema = tema;
        this.nJugadas = nJugadas;
        this.situacioInicial = new Tablero(FEN, nJugadas);
        this.dificultad = dificultad;
        this.ranking = new Ranking(tema);
        this.color = getColor(FEN);
        this.teSolucion =  comprobarSolucionProblema();
        if (!teSolucion) throw new Exception("El problema introducido no tiene solución en el número de jugadas indicadas.");
    }
    public Problema(String FEN, int nJugadas, String tema, int dificultad, String usr) throws Exception {
        if(nJugadas < 1 || dificultad < 0) throw new Exception("Mal formato de entrada");
        this.tema = tema;
        this.nJugadas = nJugadas;
        this.situacioInicial = new Tablero(FEN, nJugadas);
        this.dificultad = dificultad;
        this.ranking = new Ranking(tema);
        this.color = getColor(FEN);
        this.usuarioCreador = usr;
        this.teSolucion =  comprobarSolucionProblema();
        if (!teSolucion) throw new Exception("El problema introducido no tiene solución en el número de jugadas indicadas.");
    }

    /**
     * Creadora sencilla para debugar
     * @param FEN
     * @param n
     * @deprecated
     */
    public Problema(String FEN,int n) throws Exception {
        this.situacioInicial = new Tablero(FEN, n);
        this.nJugadas = n;
        this.tema = "Pruebas tontas";
        this.color = getColor(FEN);
        this.ranking = new Ranking(this.tema);
    }

    //================GETTERS && SETTER PER DEFECTE===================

    public boolean isTeSolucion() {
        return teSolucion;
    }

    private String getColor(String FEN) throws Exception {
        try {
            if (FEN.split("/")[7].split(" ")[1].equals("w")) return "White";
            else return "Black";
        } catch (Exception e){
            throw new Exception("Bad FEN format! No se ha detectado el color que del jugador al que le toca mover");
        }

    }

    /**
     * Devuelve el tema del problema
     * @return Tema del problema
     */
    public String getTema() { return tema; }
    
    public String getColor() { return color; }

    /**
     * Establece o modifica el tema del problema
     * @param tema Nuevo tema del problema
     * @throws Exception
     */
    public void setTema(String tema) throws Exception {
        if(tema == "") throw new Exception("Tema vacio");
        this.tema = tema;
    }

    /**
     * Devuelve la dificultad del problema
     * @return Dificultad del problema
     */
    public int getDificultad() { return dificultad;}

    /**
     * Establece o modifica la dificultad del problema
     * @param dificultad Nueva dificultad del problema
     * @throws Exception
     */
    public void setDificultad(int dificultad) throws Exception {
        if(dificultad < 0) throw new Exception("Dificultad menor que 0");
        this.dificultad = dificultad; }

    /**
     * Devuelve el numero de jugadas del problema por jugador
     * @return Numero de jugadas
     */
    public int getnJugadas() {return nJugadas;}

    /** 
     * Establece o modifica el numero de jugadas del problema por jugador
     * @param nJugadas Nuevo numero de jugadas
     * @throws Exception
     */
    public void setnJugadas(int nJugadas) throws Exception {
        if(nJugadas < 1) throw new Exception("Numero de jugadas incorrecto");
        int nAnterior = this.nJugadas;
        this.nJugadas = nJugadas;
        situacioInicial = new Tablero(this.getFEN(), nJugadas);
        if(!comprobarSolucionProblema()) situacioInicial = new Tablero(this.getFEN(), nAnterior);
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }
    //==================================================================

    /**
     * Devuelve el tablero inicial del problema
     * @return Tablero inicial
     */
    
    protected Tablero getSituacioInicial() {
        return situacioInicial;
    }

    /**
     * Devuelve el ranking del problema
     * @return Ranking del problema
     */
    protected Ranking getRanking(){return ranking;}

    //================CALLBACK'S========================================
    //todo: mirar callback's a ranking para ver el ranking de un problema

    /**
     * 
     * @param r
     */
    protected void addResultadoRanking(Resultado r){
        if(r.getResultado()) {
            ranking.addResultado(r);
        }
    }

    public boolean comprobarSolucionProblema(){
        return IASimple.comprobarSolucion(getSituacioInicial(), getColor());
    }

    public char[][] getTableroProblema(){
        return getSituacioInicial().getMatrixCharTablero();
    }
    //================GENERIC FUNCTIONS=================================
    /**
     * Deveulve los atributos del problema en formato de string
     * @return Atributos del problema
     */
    @Override
    public String toString() {
        return "Problema{" +
                "tema='" + tema + '\'' +
                ", dificultad=" + dificultad +
                ", nJugadas=" + nJugadas + '}';
    }

    public void setFEN(String FEN, int n) throws Exception {
        Tablero a = new Tablero(FEN, nJugadas);
        if(IASimple.comprobarSolucion(a, getColor(FEN))) situacioInicial = a;   
        else throw new Exception("No tiene solucion el FEN propuesto");
    }

    public String getFEN() {
        return this.situacioInicial.getFEN();
    }
}
