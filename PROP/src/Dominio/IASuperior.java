package Dominio;

import static Dominio.IASimple.BLACK;
import static Dominio.IASimple.WHITE;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Marc
 */
public class IASuperior extends Jugador {

    public IASuperior(){
        //super("SkyNet");
        super("HAL 9000");
    }

    public IASuperior(String jugador1) {
        super(jugador1);
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
    
    static List<Movimiento>[] OrdenarListaJugadas(List<Movimiento> sm, String color, Tablero t){
        
        List<Movimiento>[] mo = new LinkedList[17];
        int valorPreJugada = AsignarValor(t, color);
        for (Movimiento m : sm){
            
            Tablero t2 = new Tablero(t);
            t2.mover(m);
            int valorPostJugada = AsignarValor(t2, color);
            int valorH = valorPostJugada - valorPreJugada;
            if (mo[valorH + 8] == null) { mo[valorH + 8] = new LinkedList<>(); }
            mo[valorH + 8].add(m);         
        }

        return mo;
    }
    
    static int AsignarValor(Tablero t, String color){
        List<Celda> l = t.getCeldasColor(colorContrario(color));
        Celda c = t.getCeldaRey(l);
        return c.getPieza().JugadasPosibles(t).size();
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
        
        boolean valor;
           
        if (maximizer){
            valor = false;          
            List<Movimiento> sm = ListadoJugadasColor(color, t);
            List<Movimiento>[] mo = OrdenarListaJugadas(sm,color,t);
            for (int i = 0; i < 17; ++i){
                if (mo[i] != null){
                    while (mo[i].size() > 0){
                        Movimiento m = mo[i].remove(0);
                        //System.out.println(m.toString());
                        Tablero t2 = new Tablero(t);
                        t2.mover(m);
                        boolean b = minimaxcolor(N - 1, colorContrario(color), false, t2);
                        if (b) return true;
                        else valor = valor || b;
                    } 
                }                           
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
    
    @Override
    public Movimiento mover(int N, Tablero tablero) throws Exception {
        Movimiento m = minimaxjugada(N, getColor(), getRol() == atacante, tablero);
        System.out.println(m.toString());
        return m;
    }
    
}
