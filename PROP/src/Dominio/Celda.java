package Dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa una Celda del tablero de ajedrez
 * @author fernando.marimon
 */
public class Celda implements Serializable {
    
    /**Pieza que ocupa la celda, puede ser null TODO: pasar a pieza */    
    private Pieza p;

    /**Representa las coordenadas de la celda en el tablero */
    private int f;
    private char c;

    /**
     * Constructor sin argumentos de Celda
     **/
    public Celda(){
        f = -1;
        c = 'z';
    }
    /**
     * Constructor por copia de celda
     * @param c Celda a copiar
     * */
    public Celda(Celda c){
        
        if (c.IsEmpty()){ this.p = null; }
        else this.p = (Pieza) c.getPieza().miClone();

        this.f = c.getFila();
        this.c = c.getColumna();        
    }
    
    /**
     * Constructora por parametros de celda, variante int-char
     * @param f Coordenada de la fila
     * @param c Coordenada de la columna
     * @param p Pieza que hay en la celda
     */
    public Celda(int f, char c, Pieza p) {
        this.f = f;
        this.c = c;
        this.p = p;
    }

    /**
     * Constructora por parametros de celda, variante int-int
     * @param f Coordenada de la fila
     * @param c Coordenada de la columna
     * @param p Pieza que hay en la celda
     */
    public Celda(int f, int c, Pieza p) {
        this.f = f;
        this.c = (char) ('a'+c);
        this.p = p;
    }
    
    /**
     * Devuelve si en una celda hay una pieza
     * @return True si no hay pieza.
     */
    public Boolean IsEmpty(){
        return p == null;
    }
    
    /**
     * Devuelve la fila de la celda
     * @return  Fila de celda
     */
    public int getFila(){
        return f;
    }
    
    /**
     * Devuelve la columna de la celda
     * @return Columna de celda
     */
    public char getColumna(){
        return c;
    }
    
    /**
     * Devuelve la columna de la celda en formato de int
     * @return Columna de celda
     */
    public int getColumnaInt(){
        return c - 'a';
    }
    
   
    /**
     * Getter público de la pieza de una Celda
     * @return Devuelve la pieza que tiene la celda
     */
    public Pieza getPieza(){
        return p;
    }

    /**
     * Elimina la pieza de una celda
     */
    public void eliminarPieza() { p = null; }
    
    /**
     * Establece o modifica la pieza de celda
     * @param p Nueva pieza de celda
     */
    public void setPieza(Pieza p) {
        this.p = p;
        this.p.setFila(this.f);
        this.p.setColumna(this.c);
    }

    /**
     * Consultora del color de la pieza de una celda
     * @return White / Black / Vacio
     */
    public String getColorCelda() {
        if(!IsEmpty()) {
            return getPieza().getColor();
        }
        else return "Vacio";
    }
    
    /**
     * Definición de equals para una celda
     * @param c Celda a comparar
     * @return true si una celda es superficialmente igual a otra
     */
    @Override
    public boolean equals(Object c) {
        if(c == this)
            return true;
        if(!(c instanceof Celda))
            return false;
        Celda comp = (Celda) c;
        boolean b;
        b = this.f == comp.getFila() && this.c == comp.getColumna();
        if(p == null)
            return b && comp.IsEmpty();
        else 
            return b && !comp.IsEmpty() && p.equals(comp.getPieza());
    }
    
    @Override
    public String toString() {
        return "Fila: " + getFila() + ". Columna: " + getColumnaInt();
    }

//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 89 * hash + Objects.hashCode(this.p);
//        hash = 89 * hash + this.f;
//        hash = 89 * hash + this.c;
//        return hash;
//    }
    
}
