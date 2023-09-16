package Dominio;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Clase que representa la IA simple 
 * @author marc.garcia.ribes
 */
public class IASimple extends Jugador{

    public static final String WHITE = "White";
    public static final String BLACK = "Black";

    /**
     * Constructora sin argumentos
     */
    public IASimple() {
        super("Dummy");
    }

    /**
     * Constructora con el nombre de la IA
     * @param nombre
     */
    public IASimple(String nombre) {
        super(nombre);
    }

    /**
     * Consultora que dado un tablero y un color da sus movimientos posibles
     * @param color Color de las piezas
     * @param t Tablero acutal
     * @return Devuelve una lista con los movimientos legales del color especificado
     */
    static List<Movimiento> ListadoJugadasColor(String color, Tablero t){
        
        List<Movimiento> sm = new LinkedList<>();
        List<Pieza> piezas;
        
        if (color.equals(WHITE)) piezas = t.getPiezasBlancas();
        else piezas = t.getPiezasNegras();

            for (Pieza pz : piezas){  
                List<Celda> JP = pz.JugadasPosibles(t);
                for (Celda c : JP){
                    sm.add(new Movimiento(t.getTablero()[pz.getFila()][pz.getColumnaInt()], c));
                }
            }
            return sm;
    }
       
    /**
     * Algoritmo de backtracking recursivo
     * @param N número de jugadas * 2 - 1 que quedan hasta el mate
     * @param color color del jugador al que le toca mover
     * @param maximizer si el jugador de el color está atacando o defendiendo
     * @param t tablero actual
     * @return Devuelve true si hay una solución para el problema con las condiciones especificadas.
     */
    static boolean minimaxcolor(int N, String color, boolean maximizer, Tablero t){
        
        if (N == 0){ return t.haGanado(colorContrario(color)); }
        else{
            if (t.haGanado(colorContrario(color))){
                return true;
            }
        }
        
        boolean valor;
           
        if (maximizer){
            valor = false;          
            List<Movimiento> sm = ListadoJugadasColor(color, t);
            for (Movimiento m : sm){
                Tablero t2 = new Tablero(t);
                t2.mover(m);
                valor = valor || minimaxcolor(N - 1, colorContrario(color), false, t2);
            }           
            return valor;
        }
        else{ 
            valor = true;
            List<Movimiento> sm = ListadoJugadasColor(color, t);
            for (Movimiento m : sm){
                Tablero t2 = new Tablero(t);
                t2.mover(m);
                valor = valor && minimaxcolor(N - 1, colorContrario(color), true, t2);
            }
            return valor;
        }
    }
    
    /**
     * Consultora que devuelve un movimiento
     * @param N número de jugadas * 2 - 1 que quedan hasta el mate
     * @param color color del jugador al que le toca mover
     * @param maximizer si el jugador de el color está atacando o defendiendo
     * @param t tablero actual
     * @return Devuelve la jugada que debe hacer dado un estado del tablero y un rol asignado
     */
    static Movimiento minimaxjugada(int N, String color, boolean maximizer, Tablero t) {
        
        boolean minimax;
        List<Movimiento> sm = ListadoJugadasColor(color,t);
        
        if (maximizer) {
            for (Movimiento m : sm) {
                Tablero t2 = new Tablero(t);
                t2.mover(m);
                minimax = minimaxcolor(N - 1, colorContrario(color), false, t2);
                if (minimax) {
                    return new Movimiento(m.Celdainicial, m.Celdafinal);
                }
            }
        }
        else{
            if (sm.isEmpty()){
                return null;
            }
            else{
                Random r = new Random();
                int ir = r.nextInt()%sm.size();
                if(ir < 0) ir *= -1;
                return sm.get(ir); 
            }           
        }
        return null;
    }
    
    /**
     * Retorna el color contrario al dado
     * @param color Color a buscar el contrario
     * @return Devuelve white si color es black y viceversa
     */
    private static String colorContrario(String color){       
        if (color.equals(WHITE)) return BLACK;
        else return WHITE;
    }

    /**
     * Función de testeo para ver los tiempos que tarda en resolver problemas
     * @param t Tablero a comprobar
     * @return Devuelve si el problema tiene solución
     */
    public static boolean comprobarSolucion(Tablero t, String c) {
        System.out.println("Comprobando solucion del problema " + t.getFEN());
        long ini = System.currentTimeMillis();
        boolean b = IASimple.minimaxcolor(t.getnJugadas()*2 - 1, c, true, t);
        long fin = System.currentTimeMillis();
        System.out.println((fin-ini)/100);
        return b;
    }

    /**
     * Función que devuelve el movimiento pertinente a la IA dado el estado de un tablero
     * @param N número de jugadas * 2 - 1 que quedan hasta el mate
     * @param tablero tablero actual
     * @return Devuelve un movimiento
     */
    @Override
    public Movimiento mover(int N, Tablero tablero) {
        Movimiento m = minimaxjugada(N, getColor(), getRol() == atacante, tablero);
        if(m != null)System.out.println(m.toString());
        return m;
    }   
}
