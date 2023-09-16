package Dominio;

import static Dominio.Pieza.colorBlanco;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa un Peon
 * @author marc.garcia.ribes
 */
public class Pawn extends Pieza{

    /**
     * Creador sin argumentos de peon
     */
    public Pawn() {
    }

    /**
     * Creadora de peon con color y coordenadas, variante int-char
     * @param color Color del peon
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public Pawn(String color, int x, char y) {
        super(color, 'p', x , y);
    }
    
    /**
     * Creadora de peon con color y coordenadas, variante int-int
     * @param color Color del peon
     * @param x Coordenada de las columnas
     * @param y Coordenada de las filas
     */
    public Pawn(String color, int x, int y) {
        super(color, 'p', x , y);
    }

    /**
     * Devuelve si el peon esta en su posicion inicial
     * @return Si esta en posicion inicial
     */
    private boolean EsPosicionInicial(){
        if (this.getColor().equals(colorNegro))
            return (getFila() == 1);
        else
            return (getFila() == 6);
    }
    
    private boolean EsPosicionFinal(){
        if (getColor().equals("White")){
            return (getFila() == 1);
        }
        else return (getFila() == 6);
    }

    /**
     * Dado un tablero devuelve los posibles movimientos del peon
     * @param t Tablero del juego acutal
     * @return Lista de posibles celdas destino
     */
    @Override
    public List<Celda> JugadasPosibles(Tablero t){
        List<Celda> ret = new LinkedList<>();
        int i = this.getFila();
        int j = this.getColumnaInt();
        
        Movimiento m;
        Tablero t2;
        
        if(getColor().equals(colorBlanco)){
            if(EsPosicionInicial() && t.getTablero()[i-2][j].getPieza() == null && t.getTablero()[i-1][j].getPieza() == null){
                m = new Movimiento(i,j,i-2, j);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i-2, j, new Pawn(colorBlanco, i-2, j)));
                t2 = null;               
            }
            if(!fueraLimites(i-1, j-1) && (t.getTablero()[i-1][j-1].getPieza() != null) && (t.getTablero()[i-1][j-1].getPieza().getColor().equals(colorNegro))){
                m = new Movimiento(i,j,i-1, j-1);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())){
//                    if (EsPosicionFinal()){
//                      ret.add(new Celda(i-1, j-1, new Knight(colorBlanco, i-1, j-1)));
//                      ret.add(new Celda(i-1, j-1, new Bishop(colorBlanco, i-1, j-1)));
//                      ret.add(new Celda(i-1, j-1, new Rook(colorBlanco, i-1, j-1)));
//                      ret.add(new Celda(i-1, j-1, new Queen(colorBlanco, i-1, j-1)));
//                    }
//                    else{
                        ret.add(new Celda(i-1, j-1, new Pawn(colorBlanco, i-1, j-1)));
                    //}
                }
                t2 = null;                 
            }
            if(!fueraLimites(i-1, j+1) && (t.getTablero()[i-1][j+1].getPieza() != null) && (t.getTablero()[i-1][j+1].getPieza().getColor().equals(colorNegro))){
                m = new Movimiento(i,j,i-1, j+1);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())){
//                    if (EsPosicionFinal()){
//                      ret.add(new Celda(i-1, j+1, new Knight(colorBlanco, i-1, j+1)));
//                      ret.add(new Celda(i-1, j+1, new Bishop(colorBlanco, i-1, j+1)));
//                      ret.add(new Celda(i-1, j+1, new Rook(colorBlanco, i-1, j+1)));
//                      ret.add(new Celda(i-1, j+1, new Queen(colorBlanco, i-1, j+1)));
//                    }
                    //else{
                        ret.add(new Celda(i-1, j+1, new Pawn(colorBlanco, i-1, j+1)));
                    //}
                }
                t2 = null;               
            }
            if (!fueraLimites(i-1, j) && (t.getTablero()[i-1][j].getPieza() == null)){
                m = new Movimiento(i,j,i-1, j);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())){
//                    if (EsPosicionFinal()){
//                      ret.add(new Celda(i-1, j, new Knight(colorBlanco, i-1, j)));
//                      ret.add(new Celda(i-1, j, new Bishop(colorBlanco, i-1, j)));
//                      ret.add(new Celda(i-1, j, new Rook(colorBlanco, i-1, j)));
//                      ret.add(new Celda(i-1, j, new Queen(colorBlanco, i-1, j)));
//                    }
                    //else{
                        ret.add(new Celda(i-1, j, new Pawn(colorBlanco, i-1, j)));
                    //}
                }
                t2 = null;                
            }
        }
        else{
            if(EsPosicionInicial() && t.getTablero()[i+2][j].getPieza() == null && t.getTablero()[i+1][j].getPieza() == null){
                m = new Movimiento(i,j,i+2, j);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())) ret.add(new Celda(i+2, j, new Pawn(colorNegro, i+2, j)));
                t2 = null;              
            }
            if(!fueraLimites(i+1, j+1) && (t.getTablero()[i+1][j+1].getPieza() != null) && (t.getTablero()[i+1][j+1].getPieza().getColor().equals(colorBlanco))){
                m = new Movimiento(i,j,i+1, j+1);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())){
//                    if (EsPosicionFinal()){
//                      ret.add(new Celda(i+1, j+1, new Knight(colorNegro, i+1, j+1)));
//                      ret.add(new Celda(i+1, j+1, new Bishop(colorNegro, i+1, j+1)));
//                      ret.add(new Celda(i+1, j+1, new Rook(colorNegro, i+1, j+1)));
//                      ret.add(new Celda(i+1, j+1, new Queen(colorNegro, i+1, j+1)));
//                    }
//                    else{
                        ret.add(new Celda(i+1, j+1, new Pawn(colorNegro, i+1, j+1)));
                    //}
                }
                t2 = null;               
            }
            if(!fueraLimites(i+1, j-1) && (t.getTablero()[i+1][j-1].getPieza() != null) && (t.getTablero()[i+1][j-1].getPieza().getColor().equals(colorBlanco))){
                m = new Movimiento(i,j,i+1, j-1);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())){
//                    if (EsPosicionFinal()){
//                      ret.add(new Celda(i+1, j-1, new Knight(colorNegro, i+1, j-1)));
//                      ret.add(new Celda(i+1, j-1, new Bishop(colorNegro, i+1, j-1)));
//                      ret.add(new Celda(i+1, j-1, new Rook(colorNegro, i+1, j-1)));
//                      ret.add(new Celda(i+1, j-1, new Queen(colorNegro, i+1, j-1)));
//                    }
//                    else{
                        ret.add(new Celda(i+1, j-1, new Pawn(colorNegro, i+1, j-1)));
                    //}
                    
                }
                t2 = null; 
            }
            if (!fueraLimites(i+1, j) && (t.getTablero()[i+1][j].getPieza() == null)){
                m = new Movimiento(i,j,i+1, j);
                t2 = new Tablero(t);
                t2.mover(m);
                if (!t2.colorEstaEnJaque(getColor())){
//                    if (EsPosicionFinal()){
//                      ret.add(new Celda(i+1, j, new Knight(colorNegro, i+1, j)));
//                      ret.add(new Celda(i+1, j, new Bishop(colorNegro, i+1, j)));
//                      ret.add(new Celda(i+1, j, new Rook(colorNegro, i+1, j)));
//                      ret.add(new Celda(i+1, j, new Queen(colorNegro, i+1, j)));
//                    }
//                    else{
                        ret.add(new Celda(i+1, j, new Pawn(colorNegro, i+1, j)));
                   // }
                }
                t2 = null;                
            }
        }
        return ret;
    }
    
    /**
     * Clonadora de la clase peon
     * @return Peon identico a este
     */
    @Override
    public Object miClone(){
        return new Pawn(this.getColor(), this.getFila(), this.getColumna());
    }
}
