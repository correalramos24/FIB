package Dominio;

import java.util.List;


public class xCtrlDominioJoc {

    public static final String PERSONA = "Persona";
    public static final String IASIMPLE = "IASimple";
    public static final String IASUPERIOR = "IASuperior";
    public static final String INVITADO = "Invitado";
    
    private Problema p;
    private Tablero t;
    private Jugador actual;
    private Jugador atacante;
    private Jugador defensor;
    private int jugadasRestantes;

    private xCtrlDominioProblemas CdP; //DOMINIO DE TODOS LOS PROBLEMAS
    private xCtrlDominioUsuario CdU; //DOMINIO DE TODOS LOS USUARIOS
    
    
    public xCtrlDominioJoc(String idProblema, String jugadorAtacante, String jugadorDefensor, xCtrlDominioUsuario usr) throws Exception {
         
        CdP = new xCtrlDominioProblemas();
        CdP.iniciarControlador();

        CdU = usr;

        this.p = CdP.getProblema(idProblema);
        this.t = new Tablero(p.getFEN(), p.getnJugadas());
        this.jugadasRestantes = t.getnJugadas() * 2 - 1;

        try {
            switch (jugadorAtacante) {
                case PERSONA:
                    atacante = CdU.getInstanciaUsuarioActual();
                    break;
                case IASIMPLE:
                    atacante = new IASimple();
                    break;
                case IASUPERIOR:
                    atacante = new IASuperior();
                    break;
                case INVITADO:
                    atacante = new Usuario("inivitado");
                    break;
                default:
            }
            
            switch (jugadorDefensor) {
                case PERSONA:
                    defensor = CdU.getInstanciaUsuarioActual();
                    break;
                case INVITADO:
                    defensor = new Usuario("invitado");
                    break;
                case IASIMPLE:
                    defensor = new IASimple();
                    break;
                case IASUPERIOR:
                    defensor = new IASuperior();
                    break;
                default:
            }
            
            atacante.setRol(Jugador.atacante);
            defensor.setRol(Jugador.defensor);
            
            atacante.setColor(p.getColor());
            defensor.setColor(colorContrario(p.getColor()));
            
            actual = atacante;
            
            System.out.println("Jugadas restantes: " + jugadasRestantes);
              
        } catch (Exception e){e.printStackTrace();}
    }
    
    public void initControlador() throws Exception{
        //MyThread th = new MyThread();
        //th.start();
        /*while (actual instanceof IASimple || actual instanceof IASuperior){
            moverIA();      
        }*/
    }
    
    public boolean isActualIA(){
        return actual instanceof IASimple || actual instanceof IASuperior;
    }
    
    public int[] moverIA() throws Exception{
        Movimiento m = actual.mover(jugadasRestantes, t);
        t.mover(m);
        --jugadasRestantes; System.out.println("Jugadas restantes: " + jugadasRestantes);
        if (!esFinalPartida()) actual = (actual == defensor) ? atacante : defensor;
        int[] mov = {m.getxi(), m.getyi(), m.getxf(), m.getyf() };
        return mov;
    }

    private List<Celda> calcularJugadasPossibles(int x, int y){
        List<Celda> lc = t.getTablero()[x][y].getPieza().JugadasPosibles(t);
        return lc;
    }
    
    public boolean esMovimientoLegal(int xi, int yi, int xf, int yf){
         List<Celda> lc = calcularJugadasPossibles(xi, yi);
         for (Celda c : lc){
             if (c.getFila() == xf && c.getColumnaInt() == yf) return true;
         }
         return false;
    }
    
    public boolean piezaEsDelColorDelJugador(int x, int y){
        return actual.getColor().equals(t.getCelda(x, y).getColorCelda());
    }
    
    public void mover(int xi, int yi, int xf, int yf){
        this.t.mover(new Movimiento(xi,yi,xf,yf));
        --jugadasRestantes; System.out.println("Jugadas restantes: " + jugadasRestantes);
        if (esFinalPartida()) return;
        actual = (actual == defensor) ? atacante : defensor;
    }
    
    public String getApodoAtacante(){
        return this.atacante.getApodo();
    }
    
    public String getApodoDefensor(){
        return this.defensor.getApodo();
    }
    
    public String getApodoJugadorActual(){
        return this.actual.getApodo();
    }
    
    public String getApodoJugadorBlanco(){
        if (p.getColor().equals("White")){
            return this.atacante.getApodo();
        }
        else{
            return this.defensor.getApodo();
        }
    }
     
    public String getApodoJugadorNegro(){
        if (p.getColor().equals("Black")){
            return this.atacante.getApodo();
        }
        else{
            return this.defensor.getApodo();
        }
    }
    
    public char[][] getTablero(){
        return t.getMatrixCharTablero();
    }
    
    public String getFENProblema(){
        return this.p.getFEN();
    }
    
    public Boolean esFinalPartida(){
        return jugadasRestantes == 0;
    }
    
    public int getJugadasRestantes(){
        return this.jugadasRestantes;
    }
    
    public void finalizar(Double segundosAtacante, Long fechaJuego, boolean resultado){
        Resultado r = new Resultado(segundosAtacante, fechaJuego, atacante.getApodo(), defensor.getApodo(), resultado);
        this.CdP.addRanking(p.getTema(),r);
    }
    
    public boolean haGanadoAtacante(){
        return t.haGanado(actual.getColor());
    }
    /**
     * Retorna el color contrario al dado
     * @param color Color a buscar el contrario
     * @return Devuelve white si color es black y viceversa
     */
    private static String colorContrario(String color){       
        if (color.equals("White")) return "Black";
        else return "White";
    }
   
}

/*
public class xCtrlDominioJoc {

    public static final String PERSONA = "Persona";
    public static final String IASIMPLE = "IASimple";
    public static final String IASUPERIOR = "IASuperior";
    public static final String INVITADO = "Invitado";

    private Problema p;
    private Tablero t;

    private int jugadasRestantes;

    private Joc instancia; //INSTANCIA DE JUEGO

    private xCtrlDominioProblemas CdP; //DOMINIO DE TODOS LOS PROBLEMAS
    private xCtrlDominioUsuario CdU; //DOMINIO DE TODOS LOS USUARIOS

    public xCtrlDominioJoc(String idProblema, String jugadorAtacante, String jugadorDefensor, xCtrlDominioUsuario usr) throws Exception {
        CdP = new xCtrlDominioProblemas();
        CdP.iniciarControlador();

        CdU = usr;

        this.p = CdP.getProblema(idProblema);
        this.t = new Tablero(p.getFEN(), p.getnJugadas());
        this.jugadasRestantes = t.getnJugadas();
        Jugador atacante = null;
        Jugador defensor = null;
        try {
            switch (jugadorAtacante) {
                case PERSONA:
                    atacante = CdU.getInstanciaUsuarioActual();
                    break;
                case IASIMPLE:
                    atacante = new IASimple();
                    break;
                case IASUPERIOR:
                    atacante = new IASuperior();
                    break;
                case INVITADO:
                    atacante = new Usuario("inivitado");
                    break;
                default:
            }
            switch (jugadorDefensor) {
                case PERSONA:
                    defensor = CdU.getInstanciaUsuarioActual();
                    break;
                case INVITADO:
                    defensor = new Usuario("invitado");
                    break;
                case IASIMPLE:
                    defensor = new IASimple();
                    break;
                case IASUPERIOR:
                    defensor = new IASuperior();
                    break;
                default:
            }
            atacante.setRol(Jugador.atacante);
            defensor.setRol(Jugador.defensor);
            atacante.setColor(Pieza.colorBlanco);
            defensor.setColor(Pieza.colorNegro);
        } catch (Exception e){e.printStackTrace();}
        instancia = new Joc(atacante, defensor, p);
    }

    public void jugar() {
        instancia.iniciarJuegoControlado();
    }

    //mover USUARIO:
    public void mover(int xi, int yi, int xf, int yf) throws Exception {
        instancia.mover(new Movimiento(xi,yi,xf,yf));
    }

    public char[][] getTablero(){
        return instancia.getTableroActual();
    }

    public Boolean esFinalPartida(){
        return !instancia.esFinalPartida().equals(Joc.NoFinalPartida);
    }


    public void iniciarJuego() {
        instancia.iniciarJuegoControlado();
    }

    public void moverIA() {
        instancia.moverIA();
    }

    public boolean juegaIA() {
        return instancia.juegaIA();
    }
    public cronoPartida getTimer() {
        return instancia.getTimer();
    }
    public String nombreAtacante() {
        return instancia.nombreAtacante();
    }
    public String nombreDefensor() {
        return instancia.nombreDefensor();
    }
    public String nombreActual() {
        return instancia.getJugadorActual();
    }
    
    public String getApodoJugadorBlanco(){
        if (p.getColor().equals("White")){
            return this.instancia.getAtacante().getApodo();
        }
        else{
            return this.instancia.getDefensor().getApodo();
        }
    }
     
    public String getApodoJugadorNegro(){
        if (p.getColor().equals("Black")){
            return this.instancia.getAtacante().getApodo();
        }
        else{
            return this.instancia.getDefensor().getApodo();
        }
    }
}*/
