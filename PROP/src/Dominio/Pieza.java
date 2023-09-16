package Dominio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * Clase abstracta que representa una pieza en el sistema.
 * @author Marc Garcia Ribes
 */

//todo: comentarios javadoc funciones y atributos
public abstract class Pieza implements Serializable {

    private String color;
    private char representacio;
    private int f;
    private char c;

    public static final String colorBlanco = "White";
    public static final String colorNegro = "Black";

    /**
     * Constructor sin argumentos de Pieza
     */
    public Pieza (){
    }

    /**
     * Constructor de Pieza, variante int-int
     * @param color Color de la pieza
     * @param repr Representacion de la pieza
     * @param fila Coordenada de la fila
     * @param col Coordenada de la columna
     */
    public Pieza (String color, char repr, int fila, int col){
        this.color = color;
        this.representacio = repr;
        this.f = fila;
        this.c = (char) ('a'+col);
    }
    
    /**
     * Constructor de Pieza, variante int-char
     * @param color Color de la pieza
     * @param repr Representacion de la pieza
     * @param fila Coordenada de la fila
     * @param col Coordenada de la columna
     */
    public Pieza (String color, char repr, int fila, char col) {
        this.color = color;
        this.representacio = repr;
        this.f = fila;
        this.c = col;
    }
    /**
     * Constructora por copia de una pieza.
     * @param p Pieza que queremos copiar.
     */
    public Pieza(Pieza p){
        this.color = p.color;
        this.representacio = p.getRepresentacion();
        this.f = p.getFila();
        this.c = p.getColumna();
    }

    /**
     * Devuelve una lista con las celdas a la que se puede mover una pieza dado un tablero t
     * @param t
     * @return lista de celdas posibles
     */
    public abstract List<Celda> JugadasPosibles(Tablero t);
       
    /**
     * Devuelve la representacion de una pieza en un tablero
     * @return un caracter representado a una pieza, si es de Blancas en mayusculas, de otra manera en minusculas
     */
    public char getRepresentacion(){
        if(color.equals(colorBlanco)) return Character.toUpperCase(representacio);
        else return Character.toLowerCase(representacio);
    }

    /**
     * Devuelve el color de la pieza
     * @return Color de la pieza
     */
    public String getColor() {
        return color;
    }

    /**
     * Devuelve el color contrario de la pieza
     * @return Si la pieza es negra, devuelve blanco y viceversa
     */
    public String colorContrario(){
        if(getColor().equals("White")) return "Black";
        else return "White";
    }

    /**
     * Devuelve los distintos tipos de piezas del ajedrez
     * @return Nombres de las distintas piezas del ajedrez
     */
    public static String[] getTiposFicha(){
        return new String[]{"Pawn", "Knight", "Bishop", "Rook", "King", "Queen"};
    }

    /**
     * Devuelve si una posición en el tablero está fuera de rango
     * @return true si está fuera del tablero, false en caso contrario
     */
    static boolean fueraLimites(int x, int y)
    {
        return (x < 0 || x > 7 || y < 0 || y > 7);
    }

    /**
     * Devuelve una copia superficial de una pieza
     * @return Pieza clonada
     */
    protected abstract Object miClone();

    /**
     * Devuelve si una pieza es igual a otra pieza p
     * @param p Pieza a comparar
     * @return Si ambas piezas son iguales
     */
    @Override
    public boolean equals(Object p) {
        if(p == this)
            return true;
        if(!(p instanceof Pieza))
            return false;
        Pieza comp = (Pieza) p;
        return c == comp.getColumna() && f == comp.getFila() && representacio == Character.toLowerCase(comp.getRepresentacion()) && color.equals(comp.getColor());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.color);
        hash = 67 * hash + this.representacio;
        hash = 67 * hash + this.f;
        hash = 67 * hash + this.c;
        return hash;
    }
    
    /**
     * Devuelve la fila de la pieza
     * @return Fila de de la pieza
     */
    public int getFila() {
        return f;
    }

    /**
     * Devuelve la columna de la pieza
     * @return Columna de de la pieza
     */
    public char getColumna() {
        return c;
    }
    
    /**
     * Devulve la columna de la pieza pasada a entero
     * @return Columna de la pieza
     */
    public int getColumnaInt(){
        return c - 'a';
    }
    
    /**
     * Establece o modifica la fila de pieza
     * @param x Nueva fila de pieza
     */
    public void setFila(int x) {
        this.f = x;
    }
    
    /**
     * Establece o modifica la columna de pieza
     * @param y Nueva columna de pieza
     */
    public void setColumna(char y) {
        this.c = y;
    }
}






