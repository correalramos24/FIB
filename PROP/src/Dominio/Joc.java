package Dominio;

import java.util.Date;
import java.util.LinkedList;

/**
 * Classe que representa una partida entre un atacante y un defensor. Ademas de proporcionar un marco para el juego, tambien
 * se encarga de mantener un temporizador de partida y el historico de movimientos del tablero.
 * @author victor
 *
 */
public class Joc{


    public static final String JaqueDefensa = "Defensa Gana: Jaque Mate";
    public static final String JaqueAtaque = "Ataque Gana: Jaque Mate";
    public static final String FinalEne = "Se han acabado los moviminetos, gana defensa";
    public static final String NoFinalPartida = "Sigue";
    public static final String PartidaTablas = "Tablas";
    private String resultadoFinal;

    private Jugador atacante; //j1
    private Jugador defensor; //j2
    private Jugador jugadorActual;
    private Problema pSeleccionado; /**Pretoblema seleccionado para jugar*/
    private int nJugadas;
    private int nJugadasActuales;
    private cronoPartida timer;
    private LinkedList<Tablero> historicoPartida;
    private Tablero actual;
    private Resultado r = null;


    /**
     * Main para debugar la parte de la jugabilidad
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Usuario P1 = new Usuario("Alpha");
        Usuario P2 = new Usuario("Omega");
        P1.setRol(Jugador.atacante);
        P2.setRol(Jugador.defensor);
        P1.setColor(Pieza.colorBlanco);
        P2.setColor(Pieza.colorNegro);
        //Joc j = JocFactory.getInstace().getJocHumaMaquina(new Problema("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", 100), "iaSIMPLE", P2);
        Joc j = JocFactory.getInstace().getJocMaquinaHuma(new Problema("rr4k1/5ppp/8/8/8/2Q5/2R5/2R4K w - - 0 1", 3, "Mate en 5 jugadas", 3), "iaSIMPLE", P2);
	    //Joc j = new Joc(P1, P2, new Problema("7R/7R/8/8/8/8/8/k6K w - - 0 1",2));
	    System.out.println("Empieza la partida");

	    try {
    	    j.iniciar();
    	}
	    catch(Exception e){
    	    e.printStackTrace();
    	}
    }

    /**
     * Creadora de Joc estableciendo quien ataca, defende y el problema a jugar
     * @param atacante Jugador atacante
     * @param defensor Jugador defensor
     * @param pSeleccionado Problema a jugar
     */
    public Joc(Jugador atacante, Jugador defensor, Problema pSeleccionado) {
	this.atacante = atacante;
        this.defensor = defensor;
        this.pSeleccionado = pSeleccionado;
        this.nJugadas = pSeleccionado.getnJugadas();
        this.historicoPartida = new LinkedList<>();
        this.historicoPartida.add(pSeleccionado.getSituacioInicial());
        this.actual = pSeleccionado.getSituacioInicial();
        this.timer = new cronoPartida();
    }

    /**
     * Inicia una partida e inicia el cronometro de la partida y lo imprime por
     * pantalla una vez finalizada
     * @return  El resultado de la partida jugada
     * @throws Exception
     */
    public void iniciar() throws Exception {
    	timer.start();
    	boolean resBol = jocVisual();
    	System.out.print(timer.toString());
        Date d = new Date();
        Resultado res = new Resultado(timer.getTiempo1(), d.getTime(), atacante.getApodo(), defensor.getApodo(),resBol);
        pSeleccionado.addResultadoRanking(res);                
    }

    private boolean jocVisual() throws Exception{
        /*DragLabelOnLayeredPane taulellIntern = new DragLabelOnLayeredPane(atacante, defensor, new Problema("rr4k1/5ppp/8/8/8/2Q5/2R5/2R4K w - - 0 1",5));
        JFrame taulell = new JFrame();
        //taulell.add(taulellIntern);
        //taulell.setBounds(taulellIntern.getBounds());
        taulell.getContentPane().add(taulellIntern);
        taulell.setSize(625, 625);
        //taulell.setMinimumSize(taulellIntern.getSize());
        //taulell.setPreferredSize(taulellIntern.getSize());
        //taulell.setSize(taulellIntern.getSize());
        taulell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //taulell.pack();
        taulell.setLocationRelativeTo(null);
        taulell.setVisible(true);
        */
        return true;
    }
    /**
     * Desarrollo de la partida, movimientos de las piezas, evolucion del 
     * tablero, finalizaciones,...
     * @throws Exception 
     */
    private boolean joc() throws Exception {
        /*DragLabelOnLayeredPane taulellIntern = new DragLabelOnLayeredPane(atacante, defensor, new Problema("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1",100));
        JFrame taulell = new JFrame();
        taulell.add(taulellIntern);
        taulell.getContentPane().add(taulellIntern);
        taulell.setSize(650, 650);
        taulell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        taulell.pack();
        taulell.setLocationRelativeTo(null);
        taulell.setVisible(true);

        boolean resultado;
        int n = 0;
        Movimiento aux;
        actual = new Tablero(historicoPartida.get(0)); //actual = situacion inicial
        Jugador jugador = defensor;
        boolean jaqueMate = false, tablas = false;
        while(n < 2*nJugadas && !jaqueMate && !tablas) {
            jugador = cambioJugador(jugador);
            //if(jugador instanceof Usuario) taulellIntern.setUsuario((Usuario) jugador);
            //taulell.setTitle("Mueve "+jugador.getApodo()+"-"+jugador.getColor());
            //taulellIntern.displayMod(actual.getMatrixCharTablero());
            boolean reyEnJaque = true;
            jaqueMate = actual.colorEstaEnJaqueMate(jugador.getColor());
            tablas = actual.colorEstaEnTablas(jugador.getColor());
            if (!jaqueMate && !tablas) {
                while (reyEnJaque) {
                    System.out.println("Juega " + jugador.getApodo());
                    actual.printarTablero();
                    aux = jugador.mover(nJugadas * 2 - n, actual);
                    actual.mover(aux); //el tablero debe comprobar que el movimiento es correcto
                    reyEnJaque = actual.colorEstaEnJaque(jugador.getColor());
                    if (reyEnJaque) {
                        actual = new Tablero(historicoPartida.getLast());
                        System.out.println("¡Movimiento ilegal, tu REY está en JAQUE!\n");
                    }
                }
            }
            timer.cambiarCrono();
            historicoPartida.add(new Tablero(actual)); //hay que hacer un clone o algo
            ++n;
        }

        if(jaqueMate) {
            //taulell.setTitle(cambioJugador(jugador).getApodo() + " ha ganado la partida-"+(cambioJugador(jugador).getColor()));
            System.out.println("\n" + cambioJugador(jugador).getApodo() + " ha ganado la partida");
            System.out.println(jugador.getApodo() + " ha perdido la partida");
            resultado = jugador!=atacante;
        }
        else if(tablas) {
            //taulell.setTitle("EMPATE");
            System.out.println("\nLa partida ha acabado en tablas");
            resultado = false;
        } //Hay muchos mas tipos de tablas, pero bueno
        else {
            //taulell.setTitle(defensor.getApodo()+" ha ganado la partida-"+defensor.getColor());
            System.out.println("\nParece que "+atacante.getApodo()+" no ha podido con "+defensor.getApodo());
            resultado = false;
        }
        System.out.println("\nResultado final del tablero");
        actual.printarTablero();
        //taulell.displayMod(actual.getMatrixCharTablero());
        */
        return false;
    }

    public Jugador getAtacante(){
        return this.atacante;
    }
    
     public Jugador getDefensor(){
        return this.defensor;
    }

    /**
     * Cambia el rol de jugador actual de un jugador al otro
     * @param j Jugador actual
     * @return nuevo jugador actual
     */
    private void cambioJugador(Jugador j){
        if(j == atacante) jugadorActual = defensor;
        else jugadorActual = atacante;
        System.out.println("Le toca a " + jugadorActual + "ha jugado " + j);
    }

    //=======CALLBACKS CONTROLADOR=========

    public void iniciarJuegoControlado(){
        timer.start();
        actual = new Tablero(historicoPartida.get(0));
        jugadorActual = atacante;
        nJugadasActuales = 0;
        resultadoFinal = "";

    }
    public String getJugadorActual(){
        return jugadorActual.getApodo();
    }

    protected int getnJugadas(){
        return nJugadas;
    }

    //todo: retirar exc ofensivas
    protected void mover(Movimiento m) throws Exception {
        System.out.println("quedan " + nJugadas);
        Tablero aux = new Tablero(actual);
        boolean celda = aux.esMiCelda(m.getxi(), (char) (m.getyi()+'a'),
                jugadorActual.getColor()); //pieza correcta
        if(celda){
            if(aux.getJugadasPossiblesCelda(m)) {
                System.out.println("Ha movido "+this.getJugadorActual());
                aux.mover(m);
                actual = aux;
                nJugadasActuales++;
                if(nJugadasActuales%2==0) nJugadas--;
                cambioJugador(jugadorActual);
                timer.cambiarCrono();
                historicoPartida.add(aux);
            }
            else throw new Exception("Payaso, no puedes hacer esto");
        }
        else throw new Exception("No es tu celda, payaso");
    }

    protected String esFinalPartida(){
        if(actual.colorEstaEnJaqueMate(atacante.getColor())) return JaqueDefensa;
        if(actual.colorEstaEnJaqueMate(defensor.getColor())) return JaqueAtaque;
        if(nJugadas <= 0) return FinalEne;
        if(actual.colorEstaEnTablas(atacante.getColor())) return PartidaTablas;
        if(actual.colorEstaEnTablas(defensor.getColor())) return PartidaTablas;
        else return NoFinalPartida;
    }

    public char[][] getTableroActual() {
        return actual.getMatrixCharTablero();
    }

    public void moverIA() {
        try {
            if(juegaIA())mover(jugadorActual.mover(nJugadas*2, actual));
        } catch (Exception e) {
            System.out.println("error IA");
        }
    }

    public boolean juegaIA() {
        return jugadorActual instanceof IASimple || jugadorActual instanceof IASuperior;
    }
    
    public cronoPartida getTimer() {
        return timer;
    }
    public String nombreAtacante() {
        return atacante.getApodo();
    }
    public String nombreDefensor() {
        return defensor.getApodo();
    }
}