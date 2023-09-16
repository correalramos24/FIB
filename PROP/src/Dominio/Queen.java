package Dominio;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa una Reina
 * @author marc.garcia.ribes
 */
public class Queen extends Pieza {
    
    /**
     * Creadora de reina con color y coordenadas, variante int-char
     * @param color Color de la reina
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public  Queen(String color, int x, char y) {
        super(color,'q',x,y);
    }
    
    /**
     * Creadora de reina con color y coordenadas, variante int-int
     * @param color Color de la reina
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public  Queen(String color, int x, int y) {
        super(color,'q',x,y);
    }
    
    /**
     * Dado un tablero devuelve los posibles movimientos de la reina
     * @param t Tablero del juego acutal
     * @return Lista de posibles celdas destino
     */
    @Override
    public List<Celda> JugadasPosibles(Tablero t) {
        List<Celda> JP = new LinkedList<>();
        int i = this.getFila();
        int j = this.getColumnaInt();
        String colorContrario = colorContrario();
        
        Movimiento m;
        Tablero t2;
        
        int iaux = i + 1;
        int jaux = j + 1;
        boolean blocked = false;
        
        while (!Tablero.FueraLimites(iaux, jaux) && !blocked){
            
            if (t.getTablero()[iaux][jaux].getPieza() != null){
                blocked = true;
                
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                    t2 = null;
                }
                
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                t2 = null;
            }
            ++iaux; ++jaux;
        }          
                   
        iaux = i - 1;
        jaux = j - 1;
        blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (t.getTablero()[iaux][jaux].getPieza() != null){
                blocked = true;
                
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                t2 = null; 
            }
            --iaux; --jaux;
        }
        
        iaux = i - 1;
        jaux = j + 1;
        blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (t.getTablero()[iaux][jaux].getPieza() != null){
                blocked = true;
                
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                t2 = null; 
            }
            --iaux; ++jaux;
        }
        
        iaux = i + 1;
        jaux = j - 1;
        blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (t.getTablero()[iaux][jaux].getPieza() != null){
                blocked = true;
                
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                t2 = null;
            }
            ++iaux; --jaux;
        }
        
        iaux = i - 1;
        jaux = j;
        blocked = false;
        
        while (!fueraLimites(iaux, jaux) && !blocked){
            
            if (t.getTablero()[iaux][jaux].getPieza() != null){
                blocked = true;
                
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                t2 = null; 
            }
            --iaux;
        }          
                   
        iaux = i;
        jaux = j + 1;
        blocked = false;
        
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (t.getTablero()[iaux][jaux].getPieza() != null){
                blocked = true;
                
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                    t2 = null; 
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                t2 = null;
            }
            ++jaux;
        }
        
        iaux = i + 1;
        jaux = j;
        blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (t.getTablero()[iaux][jaux].getPieza() != null){
                blocked = true;
                
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                t2 = null;
            }
            ++iaux;
        }
        
        iaux = i;
        jaux = j - 1;
        blocked = false;
        while (!fueraLimites(iaux, jaux) && !blocked){
            if (t.getTablero()[iaux][jaux].getPieza() != null){
                blocked = true;
                
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) JP.add(new Celda(iaux,jaux, new Queen(getColor(),iaux, jaux)));
                t2 = null;
            }
            --jaux;
        }
        return JP;
    }
    

    /**
     * Clonadora de la clase reina
     * @return Reina identica a esta
     */
    @Override
    protected Object miClone(){
        return new Queen(this.getColor(), this.getFila(), this.getColumna());
    }
    
}
