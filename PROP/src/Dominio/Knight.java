package Dominio;

import java.util.LinkedList;
import java.util.List;


/**
 * Clase que representa un Caballo
 * @author marc.garcia.ribes
 */
public class Knight extends Pieza{

    /**
     * Creador vacio de caballo
     */
    public Knight() {
    }

    /**
     * Creadora de caballo con color y coordenadas, variante int-char
     * @param color Color del caballo
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public Knight(String color, int x, char y) {
        super(color, 'n', x, y);
    }
    
    /**
     * Creadora de caballo con color y coordenadas, variante int-int
     * @param color Color del caballo
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public Knight(String color, int x, int y) {
        super(color, 'n', x, y);
    }


    /**
     * Dado un tablero devuelve los posibles movimientos del caballo
     * @param t Tablero del juego acutal
     * @return Lista de posibles celdas destino
     */
    @Override
    public List<Celda> JugadasPosibles(Tablero t) {
        
        List<Celda> ret = new LinkedList<>();
        int i = getFila();
        int j = getColumnaInt();
        String colorContrario = colorContrario();
        
        Movimiento m;
        Tablero t2;
        
        if(!fueraLimites(i-2, j-1) && ((t.getTablero()[i-2][j-1].IsEmpty()) || t.getTablero()[i-2][j-1].getPieza().getColor().equals(colorContrario))){
            m = new Movimiento(i,j,i-2,j-1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i-2, j-1, new Knight(getColor(),i-2, j-1)));
            t2 = null;          
        }
        if (!fueraLimites(i-2, j+1) && ((t.getTablero()[i-2][j+1].IsEmpty()) || t.getTablero()[i-2][j+1].getPieza().getColor().equals(colorContrario))){
            m = new Movimiento(i,j,i-2,j+1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i-2,j+1,new Knight(getColor(),i-2, j+1)));
            t2 = null;           
        }
        if (!fueraLimites(i+1, j-2) && ((t.getTablero()[i+1][j-2].IsEmpty()) || t.getTablero()[i+1][j-2].getPieza().getColor().equals(colorContrario))){
            m = new Movimiento(i,j,i+1,j-2);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i+1,j-2, new Knight(getColor(),i+1, j-2)));
            t2 = null;           
        }
        if (!fueraLimites(i-1, j-2) && ((t.getTablero()[i-1][j-2].IsEmpty()) || t.getTablero()[i-1][j-2].getPieza().getColor().equals(colorContrario))){
            m = new Movimiento(i,j,i-1,j-2);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i-1, j-2, new Knight(getColor(),i-1, j-2)));
            t2 = null;             
        }
        if (!fueraLimites(i+1, j+2) && ((t.getTablero()[i+1][j+2].IsEmpty()) || t.getTablero()[i+1][j+2].getPieza().getColor().equals(colorContrario))){
            m = new Movimiento(i,j,i+1,j+2);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i+1,j+2, new Knight(getColor(),i+1, j+2)));
            t2 = null;           
        }
        if (!fueraLimites(i-1, j+2) && ((t.getTablero()[i-1][j+2].IsEmpty()) || t.getTablero()[i-1][j+2].getPieza().getColor().equals(colorContrario))){
            m = new Movimiento(i,j,i-1,j+2);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i-1,j+2, new Knight(getColor(),i-1, j+2)));
            t2 = null;           
        }        
        if (!fueraLimites(i+2, j-1) && ((t.getTablero()[i+2][j-1].IsEmpty()) || t.getTablero()[i+2][j-1].getPieza().getColor().equals(colorContrario))){
            m = new Movimiento(i,j,i+2,j-1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i+2,j-1, new Knight(getColor(),i+2, j-1)));
            t2 = null;            
        }
        if (!fueraLimites(i+2, j+1) && ((t.getTablero()[i+2][j+1].IsEmpty()) || t.getTablero()[i+2][j+1].getPieza().getColor().equals(colorContrario))){
            m = new Movimiento(i,j,i+2,j+1);
            t2 = new Tablero(t);
            t2.mover(m);
            if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i+2,j+1, new Knight(getColor(),i+2, j+1)));
            t2 = null;          
        }
        return ret;
    }
    
    /**
     * Clonadora de la clase caballo
     * @return Caballo identico a este
     */
    @Override
    public Object miClone() {
        return new Knight(getColor(), getFila(), getColumna());
    }
}
