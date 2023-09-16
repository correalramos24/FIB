package Dominio;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un Alfil
 * @author Marc Garcia
 */

class Bishop extends Pieza {

    /**
     * Creadora sin argumentos de alfil
     */
    public Bishop() {
    }

    /**
     * Creadora de alfil con color y coordenadas, variante int-char
     * @param color Color del alfil
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public Bishop(String color, int x, char y){
        super(color, 'b', x, y);
    }
    
    /**
     * Creadora de alfil con color y coordenadas, variante int-int
     * @param color Color del alfil
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public Bishop(String color, int x, int y){
        super(color, 'b', x, y);
    }
    
    /**
     * Dado un tablero devuelve los posibles movimientos del alfil
     * @param t Tablero del juego acutal
     * @return Lista de posibles celdas destino
     */
    @Override
    public List<Celda> JugadasPosibles(Tablero t) {
        List<Celda> ret = new LinkedList<>();
        int i = this.getFila();
        int j = this.getColumnaInt();
        String colorContrario = colorContrario();

        Movimiento m;
        Tablero t2;
        
        int iaux = i+1;
        int jaux = j+1;
        boolean blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){

            if (!t.getTablero()[iaux][jaux].IsEmpty()){
                blocked = true;
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Bishop(getColor(),iaux, jaux)));
                    t2 = null;                    
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Bishop(getColor(),iaux, jaux)));
                t2 = null;
            }
            ++iaux; ++jaux;
        }
        iaux = i - 1;
        jaux = j - 1;
        blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (!t.getTablero()[iaux][jaux].IsEmpty()){
                blocked = true;
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Bishop(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Bishop(getColor(),iaux, jaux)));
                t2 = null;
            }
            --iaux; --jaux;
        }
        iaux = i - 1;
        jaux = j + 1;
        blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (!t.getTablero()[iaux][jaux].IsEmpty()){
                blocked = true;
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Bishop(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Bishop(getColor(),iaux, jaux)));
                t2 = null;
            }
            --iaux; ++jaux;
        }
        iaux = i + 1;
        jaux = j - 1;
        blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (!t.getTablero()[iaux][jaux].IsEmpty()){
                blocked = true;
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Bishop(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Bishop(getColor(),iaux, jaux)));
                t2 = null;
            }
            ++iaux; --jaux;
        }
        return ret;
    }
    
    /**
     * Clonadora de la clase alfil
     * @return Alfil identico a este
     */
    @Override
    protected Object miClone() {
        return new Bishop(this.getColor(), this.getFila(), this.getColumna());
    }
}