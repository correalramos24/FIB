package Dominio;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un Rey
 * @author marc.garcia.ribes
 */
public class King extends Pieza{


    /**
     * Creadora de rey con color y coordenadas, variante int-char
     * @param color Color del rey
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public King(String color, int x, char y) {
        super(color, 'k', x, y);
    }
    
    /**
     * Creadora de rey con color y coordenadas, variante int-int
     * @param color Color del rey
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public King(String color, int x, int y) {
        super(color, 'k', x, y);
    }
    
    /**
     * Dado un tablero devuelve los posibles movimientos del rey
     * @param t Tablero del juego acutal
     * @return Lista de posibles celdas destino
     */
    @Override
    public List<Celda> JugadasPosibles(Tablero t) {
        
        List<Celda> JP = new LinkedList<>();
        int i, j;
        i = this.getFila();
        j = this.getColumnaInt();

        Movimiento m;
        Tablero t2;
        
        if (!fueraLimites(i - 1, j) && !t.getTablero()[i - 1][j].getColorCelda().equals(getColor())) {
            m = new Movimiento(i,j,i-1,j);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(i - 1, j, new King(getColor(), i-1,j)));
            t2 = null;
        } // UP
        if (!fueraLimites(i - 1, j + 1) && !t.getTablero()[i - 1][j + 1].getColorCelda().equals(getColor())) {
            m = new Movimiento(i,j,i-1,j+1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(i - 1, j + 1, new King(getColor(), i-1,j+1)));
            t2 = null;
            
        } // UP-RIGHT
        if (!fueraLimites(i, j + 1) && !t.getTablero()[i][j + 1].getColorCelda().equals(getColor())) {
            m = new Movimiento(i,j,i,j+1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(i, j+1, new King(getColor(), i,j+1)));
            t2 = null;
            
        } // RIGHT
        if (!fueraLimites(i + 1, j + 1) && !t.getTablero()[i + 1][j + 1].getColorCelda().equals(getColor())) {
            m = new Movimiento(i,j,i+1,j+1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(i + 1, j+1, new King(getColor(), i+1,j+1)));
            t2 = null;
            
        } // DOWN-RIGHT
        if (!fueraLimites(i + 1, j) && !t.getTablero()[i + 1][j].getColorCelda().equals(getColor())) {
            m = new Movimiento(i,j,i+1,j);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(i + 1, j, new King(getColor(), i+1,j)));
            t2 = null;
            
        } // DOWN
        if (!fueraLimites(i + 1, j - 1) && !t.getTablero()[i + 1][j - 1].getColorCelda().equals(getColor())) {
            m = new Movimiento(i,j,i+1,j-1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(i + 1, j - 1, new King(getColor(), i+1,j-1)));
            t2 = null;
            
        } // DOWN-LEFT
        if (!fueraLimites(i, j - 1) && !t.getTablero()[i][j - 1].getColorCelda().equals(getColor())) {
            m = new Movimiento(i,j,i,j-1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(i, j - 1, new King(getColor(), i,j-1)));
            t2 = null;
            
        } // LEFT
        if (!fueraLimites(i - 1, j - 1) && !t.getTablero()[i - 1][j - 1].getColorCelda().equals(getColor())) {
            m = new Movimiento(i,j,i-1,j-1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(i - 1, j - 1, new King(getColor(), i-1,j-1)));
            t2 = null;
            
        } // LEFT-UP

        return JP;
    }

    /**
     * Clonadora de la clase rey
     * @return Rey identico a este
     */
    @Override
    protected Object miClone(){
        return new King(getColor(), getFila(), getColumna());
    }
}
