package Dominio;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa una Torre
 * @author marc.garcia.ribes
 */
class Rook extends Pieza {
    
    /**
     * Creadora de torre vacia
     */
    public Rook() {   }

    /**
     * Creadora de torre con color y coordenadas, variante int-char
     * @param color Color de la torre
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public Rook(String color, int x, char y) { super(color, 'r', x, y); }

    /**
     * Creadora de torre con color y coordenadas, varainte int-int
     * @param color Color de la torre
     * @param x Coordenadas de las columnas
     * @param y Coordenadas de las filas
     */
    public Rook(String color, int x, int y) { super(color, 'r', x, y); }
    
    /**
     * Dado un tablero devuelve los posibles movimientos de la torre
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
        
        int iaux = i - 1;
        int jaux = j;
        boolean blocked = false;

        while (!Tablero.FueraLimites(iaux, jaux) && !blocked){
            if (!t.getTablero()[iaux][jaux].IsEmpty()){
                blocked = true;
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Rook(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Rook(getColor(),iaux, jaux)));
                t2 = null;
            }
            --iaux;
        }
        
        iaux = i;
        jaux = j + 1;
        blocked = false;

        while (!Tablero.FueraLimites(iaux, jaux) && !blocked){
            if (!t.getTablero()[iaux][jaux].IsEmpty()){
                blocked = true;
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Rook(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Rook(getColor(),iaux, jaux)));
                t2 = null;
            }
            ++jaux;
        }

        iaux = i + 1;
        jaux = j;
        blocked = false;
        while (!Tablero.FueraLimites(iaux, jaux) && !blocked){
            if (!t.getTablero()[iaux][jaux].IsEmpty()){
                blocked = true;
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Rook(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Rook(getColor(),iaux, jaux)));
                t2 = null;
            }
            ++iaux;
        }

        iaux = i;
        jaux = j - 1;
        blocked = false;
        while (!Tablero.FueraLimites(iaux, jaux) && !blocked){
            if (!t.getTablero()[iaux][jaux].IsEmpty()){
                blocked = true;
                if (t.getTablero()[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    m = new Movimiento(i,j,iaux,jaux);
                    t2 = new Tablero(t);
                    t2.mover(m);
                    if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Rook(getColor(),iaux, jaux)));
                    t2 = null;
                }
            }
            else{
                m = new Movimiento(i,j,iaux,jaux);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(iaux,jaux, new Rook(getColor(),iaux, jaux)));
                t2 = null;
            }
            --jaux;
        }
        return ret;
    }

    /**
     * Clonadora de la clase torre
     * @return Torre identica a esta
     */
    @Override
    protected Object miClone() {
        return new Rook(this.getColor(), this.getFila(), this.getColumna());
    }
      
}