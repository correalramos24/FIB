package Dominio;

import Exepciones.BadFEN;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;



/**
=====================================      
 * 8    r   n   b   q   k   b   n   r
 * 7    p   p   p   p   p   p   p   p 
 * 6
 * 5
 * 4
 * 3
 * 2    P   P   P   P   P   P   P   P
 * 1    R   N   B   Q   K   B   N   R
 *      a   b   c   d   e   f   g   h
======================================
 */

/**
 * Classe de datos que representa un tablero, como un conjunto de Celdas.
 * @author marc.garcia.ribes
 * @version 1.0
 **/

public class Tablero implements Serializable {
    
    /**
     * Conjunto de todas las celdas de un tablero.
     */
    public Celda[][] tablero;
    /**
     * FEN que representa el tablero en un instante.
     */
    private String tableroFEN;
    /**
     * Estatico que referencia el color de las pieza
     */
    private static String White = Pieza.colorBlanco;
    private static String Black = Pieza.colorNegro;
    private int nJugadas;

    /**
     * Constructora de un tablero vacio
     */
    public Tablero(){
    
    }
    /**
     * Constructora de un tablero, sobre un FEN.
     * @param fen FEN del tablero que queremos instanciar.
     * @param nJugadas número de jugadas para el mate
     */
    public Tablero(String fen, int nJugadas) throws Exception{
        //try{
            this.nJugadas = nJugadas;
            this.tablero = parseTablero(fen);
            tableroFEN = fen;
        //}
        //catch (Exception ex){
        //    System.out.println("Error creando tablero: " + ex.getMessage());
        //}
    }
    /**
     * Constructora por copia de un tablero.
     * @param t Tablero que queremos copiar.
     */
    public Tablero(Tablero t){
        //TODO: dar coordenadas int a las celdas
        tablero = new Celda[8][8];
        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                tablero[i][j] = new Celda(t.getTablero()[i][j]);
            }            
        }
        tableroFEN = t.tableroFEN;
    }
    /**
     * Comprueba que dada una coordenada este dentro de los limites del tablero.
     * @param y coordenada vertical del tablero.
     * @param x coordenada horizontal del tablero.
     * @return indica si esta dentro de los limites del tablero la coordenada x,y.
     */
    public static boolean FueraLimites(int x, int y)
    {
        return (x < 0 || x > 7 || y < 0 || y > 7);
    }
    
    /**
     * Devuelve el numero de jugadas restantes
     * @return Jugadas restantes
     */
    public int getnJugadas() {
        return nJugadas;
    }
    
  
    /**
     * Devuleve el color contrario al que le damos
     * @param color Color actual
     * @return Color contrario al dado
     */
    public String colorContrario(String color){
        if(color.equals(White)) return Black;
        else return White;
    }
    
    /**
     * Devuelve la celda del tablero de posicion i-j
     * @param i Coordenada de las filas
     * @param j Coordenada de las columnas
     * @return Celda i-j
     */
    public Celda getCelda(int i, int j) {
        return tablero[i][j];
    }
    
    /**
     * Devuelve el conjunto de celdas que conforman el tablero
     * @return Matriz de celdas
     */
    public Celda[][] getTablero() {
        return tablero;
    }
    
    public void setTablero(char[][] tablero){
        
        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                switch (tablero[i][j]){
                    case 'p':
                        getTablero()[i][j].setPieza(new Pawn(Pieza.colorNegro,i,j));
                        break;
                    case 'P':
                        getTablero()[i][j].setPieza(new Pawn(Pieza.colorBlanco,i,j));
                        break;
                    case 'n':
                        getTablero()[i][j].setPieza(new Knight(Pieza.colorNegro,i,j));
                        break;
                    case 'N':
                        getTablero()[i][j].setPieza(new Knight(Pieza.colorBlanco,i,j));
                        break;
                    case 'b':
                        getTablero()[i][j].setPieza(new Bishop(Pieza.colorNegro,i,j));
                        break;
                    case 'B':
                        getTablero()[i][j].setPieza(new Bishop(Pieza.colorBlanco,i,j));
                        break;
                    case 'r':
                        getTablero()[i][j].setPieza(new Rook(Pieza.colorNegro,i,j));
                        break;
                    case 'R':
                        getTablero()[i][j].setPieza(new Rook(Pieza.colorBlanco,i,j));
                        break;
                    case 'k':
                        getTablero()[i][j].setPieza(new King(Pieza.colorNegro,i,j));
                        break;
                    case 'K':
                        getTablero()[i][j].setPieza(new King(Pieza.colorBlanco,i,j));
                        break;
                    case 'q':
                        getTablero()[i][j].setPieza(new Queen(Pieza.colorNegro,i,j));                            
                        break;
                    case 'Q':
                        getTablero()[i][j].setPieza(new Queen(Pieza.colorBlanco,i,j));
                        break;
                    default:
                        break;
                            //throw new Exception("Bad FEN format! Caracter incorrecto: " + aux);
                }
            }
        }
    }
    /**
     * Devuelve el color de la pieza de la celda i-j, si no hay pieza deuvuelve
     * el string "Vacio"
     * @param i Coordenada de las filas
     * @param j Coordenada de las columnas
     * @return Color de la pieza de la celda i-j
     */
    public String getColorCelda(int i, int j) {
        if(FueraLimites(i,j)) return "Vacio";
        if(!tablero[i][j].IsEmpty()) {
            return tablero[i][j].getPieza().getColor();
        }
        else return "Vacio";
    }
    
    /**
     * Consultora de las piezas blancas del tablero
     * @return Lista de piezas blancas en el tablero, cada pieza con su celda actual.
     */
    public List <Pieza> getPiezasBlancas()
    {
        LinkedList<Pieza> piezasW = new LinkedList<>();

        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                if (!tablero[i][j].IsEmpty() && tablero[i][j].getPieza().getColor().equals(White)){
                    piezasW.add(tablero[i][j].getPieza());
                }
            }
        }

        return piezasW;
    }
    /**
     * Consultora de las piezas negras del tablero
     * @return Lista de piezas negras en el tablero, cada pieza con su celda actual.
     */
    public List <Pieza> getPiezasNegras()
    {
        LinkedList<Pieza> piezasB = new LinkedList<>();

        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                if (!tablero[i][j].IsEmpty() && tablero[i][j].getPieza().getColor().equals(Black)){
                    piezasB.add(tablero[i][j].getPieza());
                }
            }
        }

        return piezasB;
    }
    
    /**
     * Devuelve una lista con las celdas ocupadas por la piezas de un color
     * @param c Color de las piezas a buscar
     * @return Lista de celdas con piezas de color c
     */
    public List<Celda> getCeldasColor(String c) {
        List<Celda> piezas = new LinkedList<>();
        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                if (!tablero[i][j].IsEmpty() && tablero[i][j].getPieza().getColor().equals(c)){
                    piezas.add(tablero[i][j]);
                }
            }
        }
        return piezas;
    }
    
    /** 
     * Devuelve la celda que contiene el Rey
     * @param l Lista de celdas ocupadas por piezas de cierto color
     * @return Celda del rey
     */
    public Celda getCeldaRey(List<Celda> l) {
        for (Celda c : l) {
            if(c.getPieza() instanceof King) return c;
        }
        return null;
    }
    
    /**
     * Devuelve el FEN del tablero
     * @return FEN de tablero
     */
    public String getFEN() {
        return tableroFEN;
    }
    
    /**
     * Dado un FEN, crea la estructura de datos correspondiente al tablero
     * @param tableroFEN FEN del tablero
     * @return Matriz de celdas que representan un tablero
     * @throws java.lang.Exception
     */
    public static Celda[][] parseTablero(String tableroFEN) throws Exception
    {
        int whiteKing = 0;
        int blackKing = 0;

        String[] filasTablero = tableroFEN.split("/");

        if (filasTablero.length != 8) throw new Exception("Bad FEN format! El número de filas es distinto a ocho.");
        Celda[][] tablero = new Celda[8][8];
        
        for (int i = 0; i < 8; ++i){
            for (int j = 0; j < 8; ++j){
                tablero[i][j] = new Celda(i,j,null);
            }       
        } 
        
        for (int i = 0; i < 8; ++i)
        {
            String filaFEN = filasTablero[i];
            int k = 0;
            for (int j = 0; j < filaFEN.length() && filaFEN.charAt(j) != ' '; ++j)
            {
                char aux = filaFEN.charAt(j);

                if (Character.isDigit(aux))
                {
                    int tope = k + Character.getNumericValue(aux);
                    while (k < tope)++k;
                }
                else
                {
                    if (k > 7) { throw new Exception("Bad FEN format! El número de columnas es distinto a ocho en la fila: " + i); }
                    switch (aux) {
                        case 'p':
                            tablero[i][k].setPieza(new Pawn(Pieza.colorNegro,i,k));
                            break;
                        case 'P':
                            tablero[i][k].setPieza(new Pawn(Pieza.colorBlanco,i,k));
                            break;
                        case 'n':
                            tablero[i][k].setPieza(new Knight(Pieza.colorNegro,i,k));
                            break;
                        case 'N':
                            tablero[i][k].setPieza(new Knight(Pieza.colorBlanco,i,k));
                            break;
                        case 'b':
                            tablero[i][k].setPieza(new Bishop(Pieza.colorNegro,i,k));
                            break;
                        case 'B':
                            tablero[i][k].setPieza(new Bishop(Pieza.colorBlanco,i,k));
                            break;
                        case 'r':
                            tablero[i][k].setPieza(new Rook(Pieza.colorNegro,i,k));
                            break;
                        case 'R':
                            tablero[i][k].setPieza(new Rook(Pieza.colorBlanco,i,k));
                            break;
                        case 'k':
                            tablero[i][k].setPieza(new King(Pieza.colorNegro,i,k));
                            blackKing++;
                            break;
                        case 'K':
                            tablero[i][k].setPieza(new King(Pieza.colorBlanco,i,k));
                            whiteKing++;
                            break;
                        case 'q':
                            tablero[i][k].setPieza(new Queen(Pieza.colorNegro,i,k));                            
                            break;
                        case 'Q':
                            tablero[i][k].setPieza(new Queen(Pieza.colorBlanco,i,k));
                            break;
                        default:
                            throw new Exception("Bad FEN format! Caracter incorrecto: " + aux);
                    }
                    ++k;
                }
            }
        }

        if (whiteKing == 0) throw new Exception("Bad FEN format! No se ha encontrado el rey blanco.");
        else if (blackKing == 0) throw new Exception("Bad FEN format! No se ha encontrado el rey negro.");
        else if (whiteKing != 1 || blackKing != 1) throw new Exception("Bad FEN format! Solamente se necesita un rey, que ya trabajan mucho.");

        return tablero;
    }
    
    /**
     * Convierte el tablero actual a un string en formato FEN
     * @return FEN del tablero
     */
    public String tableroToFEN() {
        
        String FEN = "";
        
//        for (int i = 0; i < 8; ++i){
//            int count = 0;
//            for (int j = 0; j < 8; ++j){
//                if (getTablero()[i][j].IsEmpty()){
//                    ++count;
//                }
//                else{
//                    char c = getTablero()[i][j].getPieza().getRepresentacion();
//                    if (count != 0){
////                        FEN.concat((char)count + c);
////                        FEN.concat();
//                        count = 0;
//                    }
//                    else{
//                        FEN += c;
//                    }
//                }
//            }
//            if (i != 7) FEN += '/';
//        }
        return FEN;
    }

    /**
     * Devuelve si un jugador ha ganado la partida
     * @param color
     * @return true si colorContrario(color) está en jaque mate, false si no es así.
     */
    public boolean haGanado(String color){
        boolean sinMovimientos = true;
        String colorContrario = colorContrario(color);
        List<Celda> lc = getCeldasColor(colorContrario);
        for (Celda c : lc){
            if (!c.getPieza().JugadasPosibles(this).isEmpty()) sinMovimientos = false;
        }
        return colorEstaEnJaque(colorContrario) && sinMovimientos;
    }

    /**
     * Printa el tablero por consola
     */
    public void printarTablero(){
        char c;
        for (int i = 0; i < 8; ++i)
        {
            System.out.print((8-i)+" ");
            for (int j = 0; j < 8; ++j)
            {
                if (tablero[i][j].getPieza() instanceof Pawn){
                    if (tablero[i][j].getPieza().getColor().equals(Pieza.colorBlanco)){
                        c = 'P';
                    }
                    else { c = 'p'; }
                }
                else if (tablero[i][j].getPieza() instanceof Knight){
                    if (tablero[i][j].getPieza().getColor().equals(Pieza.colorBlanco)){
                        c = 'N';
                    }
                    else { c = 'n'; }
                }
                else if (tablero[i][j].getPieza() instanceof Bishop){
                    if (tablero[i][j].getPieza().getColor().equals(Pieza.colorBlanco)){
                        c = 'B';
                    }
                    else { c = 'b'; }
                }
                else if (tablero[i][j].getPieza() instanceof Rook){
                    if (tablero[i][j].getPieza().getColor().equals(Pieza.colorBlanco)){
                        c = 'R';
                    }
                    else { c = 'r'; }
                }
                else if (tablero[i][j].getPieza() instanceof King){
                    if (tablero[i][j].getPieza().getColor().equals(Pieza.colorBlanco)){
                        c = 'K';
                    }
                    else { c = 'k'; }
                }
                else if (tablero[i][j].getPieza() instanceof Queen){
                    if (tablero[i][j].getPieza().getColor().equals(Pieza.colorBlanco)){
                        c = 'Q';
                    }
                    else { c = 'q'; }
                }
                else{
                    c = '-';
                }
                System.out.print(c);
                System.out.print(' ');
            }
            System.out.print('\n');
        }
        System.out.println("  A B C D E F G H");
    }

    /**
     *
     * @return
     */
    public char[][] getMatrixCharTablero(){
        char[][] ret = new char[8][8];
        char aux;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(tablero[i][j].IsEmpty())
                    ret[i][j] = '-';
                else {
                    aux = tablero[i][j].getPieza().getRepresentacion();
                    if(tablero[i][j].getPieza().getColor().equals(Pieza.colorBlanco)) aux = Character.toUpperCase(aux);
                    else aux = Character.toLowerCase(aux);
                    ret[i][j] = aux;
                }
            }
        }
        return ret;
    }

    /**
     * Dado un movimiento(posicion incial y posicion final de una pieza), mueve dicha pieza.
     * @param m Par de coordenadas inicio-destino
     */
    public void mover(Movimiento m){
        Pieza pAux = this.tablero[m.getxi()][m.getyi()].getPieza();
        this.tablero[m.getxi()][m.getyi()].eliminarPieza();
        this.tablero[m.getxf()][m.getyf()].setPieza(pAux);
    }
    
    /**
     * Devuelve cierto si las coordenadas dadas corresponden a una celda con pieza
     * del color pasado
     * @param f Coordenada de la fila
     * @param c Coordenada de la columna
     * @param color Color de la pieza
     * @return Si pertenece la celda al color pasado
     */
    public boolean esMiCelda(int f, char c, String color) {
        int j = c-'a';
        //System.out.println((8-f) + " " +c+" -> Tu color: "+color+ " Color de la celda: "+getColorCelda(f,j));
        return this.getColorCelda(f,j).equals(color);
    }

    /**
     * Deveulve cierto si el jugador del color dado esta en Rey Ahogado
     * @param color Color del jugador
     * @return Si se esta en Rey Ahogado
     */
    public boolean colorEstaEnTablas(String color) {
        boolean res = true;
        List<Celda> misCeldas = this.getCeldasColor(color);
        for(Celda c : misCeldas) {
            List<Celda> misMoves = c.getPieza().JugadasPosibles(this);
            res = res && misMoves.isEmpty();
        }
        return res;
    }
    
    /**
     * Devuelve cierto si el rey de dicho color esta en Jaque
     * @param color Color del jugador
     * @return Si se esta en Jaque
     */
    public boolean colorEstaEnJaque(String color) {
        
        String colorContrario;
        if(color.equals(White)) colorContrario = Black;
        else colorContrario = White;
        
        Pieza p = getCeldaRey(getCeldasColor(color)).getPieza();
        int i = p.getFila();
        int j = p.getColumnaInt();
        
        // PEONES
        
        if (color.equals(Black)){
            if (!FueraLimites(i+1,j+1) && tablero[i+1][j+1].getPieza() != null && tablero[i+1][j+1].getPieza() instanceof Pawn && tablero[i+1][j+1].getPieza().getColor().equals(White)) return true;
            else if (!FueraLimites(i+1,j-1) && tablero[i+1][j-1].getPieza() != null && tablero[i+1][j-1].getPieza() instanceof Pawn && tablero[i+1][j-1].getPieza().getColor().equals(White)) return true;
        }
        else{
            if (!FueraLimites(i-1,j+1) && tablero[i-1][j+1].getPieza() != null && tablero[i-1][j+1].getPieza() instanceof Pawn && tablero[i-1][j+1].getPieza().getColor().equals(Black)) return true;
            else if (!FueraLimites(i-1,j-1) && tablero[i-1][j-1].getPieza() != null && tablero[i-1][j-1].getPieza() instanceof Pawn && tablero[i-1][j-1].getPieza().getColor().equals(Black)) return true;
        }
        
        // REY CONTARIO
        
        if (!FueraLimites(i - 1, j) && !tablero[i - 1][j].IsEmpty() && tablero[i - 1][j].getPieza() instanceof King) {
            return true;
        } // UP
        if (!FueraLimites(i - 1, j + 1) && !tablero[i - 1][j + 1].IsEmpty() && tablero[i - 1][j + 1].getPieza() instanceof King) {
            return true;          
        } // UP-RIGHT
        if (!FueraLimites(i, j + 1) && !tablero[i][j + 1].IsEmpty() && tablero[i][j + 1].getPieza() instanceof King) {
            return true;
        } // RIGHT
        if (!FueraLimites(i + 1, j + 1) && !tablero[i + 1][j + 1].IsEmpty() && tablero[i + 1][j + 1].getPieza() instanceof King) { 
            return true;
        } // DOWN-RIGHT
        if (!FueraLimites(i + 1, j) && !tablero[i + 1][j].IsEmpty() && tablero[i + 1][j].getPieza() instanceof King) {
            return true;
        } // DOWN
        if (!FueraLimites(i + 1, j - 1) && !tablero[i + 1][j - 1].IsEmpty() && tablero[i + 1][j - 1].getPieza() instanceof King) {
            return true;
        } // DOWN-LEFT
        if (!FueraLimites(i, j - 1) && !tablero[i][j - 1].IsEmpty() && tablero[i][j - 1].getPieza() instanceof King) {
            return true;
        } // LEFT
        if (!FueraLimites(i - 1, j - 1) && !tablero[i - 1][j - 1].IsEmpty() && tablero[i - 1][j - 1].getPieza() instanceof King) {
            return true;
        } // LEFT-UP
        
        // DIAGONALES
                    
        int iaux = i+1;
        int jaux = j+1;
        boolean blocked = false;
        while (!FueraLimites(iaux, jaux) && !blocked){

            if (!tablero[iaux][jaux].IsEmpty()){
                blocked = true;
                if (tablero[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    Pieza pz = tablero[iaux][jaux].getPieza();
                    if (pz instanceof Bishop || pz instanceof Queen) return true;
                   
                }
            }
            ++iaux; ++jaux;
        }
        
        iaux = i - 1;
        jaux = j - 1;
        blocked = false;
        while (!FueraLimites(iaux, jaux) && !blocked){
            if (!tablero[iaux][jaux].IsEmpty()){
                blocked = true;
                if (tablero[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    Pieza pz = tablero[iaux][jaux].getPieza();
                    if (pz instanceof Bishop || pz instanceof Queen) return true;
                }
            }
            --iaux; --jaux;
        }
        iaux = i - 1;
        jaux = j + 1;
        blocked = false;
        while (!FueraLimites(iaux, jaux) && !blocked){
            if (!tablero[iaux][jaux].IsEmpty()){
                blocked = true;
                if (tablero[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    Pieza pz = tablero[iaux][jaux].getPieza();
                    if (pz instanceof Bishop || pz instanceof Queen) return true;
                }
            }
            --iaux; ++jaux;
        }
        iaux = i + 1;
        jaux = j - 1;
        blocked = false;
        while (!FueraLimites(iaux, jaux) && !blocked){
            if (!tablero[iaux][jaux].IsEmpty()){
                blocked = true;
                if (tablero[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    Pieza pz = tablero[iaux][jaux].getPieza();
                    if (pz instanceof Bishop || pz instanceof Queen) return true;
                }
            }
            ++iaux; --jaux;
        }
        
        // COLUMNAS | FILAS
        
        iaux = i - 1;
        jaux = j;
        blocked = false;

        while (!FueraLimites(iaux, jaux) && !blocked){
            if (!tablero[iaux][jaux].IsEmpty()){
                blocked = true;
                if (tablero[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    Pieza pz = tablero[iaux][jaux].getPieza();
                    if (pz instanceof Rook || pz instanceof Queen) return true;
                }
            }
            --iaux;
        }
        
        iaux = i;
        jaux = j + 1;
        blocked = false;

        while (!FueraLimites(iaux, jaux) && !blocked){
            if (!tablero[iaux][jaux].IsEmpty()){
                blocked = true;
                if (tablero[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    Pieza pz = tablero[iaux][jaux].getPieza();
                    if (pz instanceof Rook || pz instanceof Queen) return true;
                }
            }
            ++jaux;
        }

        iaux = i + 1;
        jaux = j;
        blocked = false;
        while (!FueraLimites(iaux, jaux) && !blocked){
            if (!tablero[iaux][jaux].IsEmpty()){
                blocked = true;
                if (tablero[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    Pieza pz = tablero[iaux][jaux].getPieza();
                    if (pz instanceof Rook || pz instanceof Queen) return true;
                }
            }
            ++iaux;
        }

        iaux = i;
        jaux = j - 1;
        blocked = false;
        while (!FueraLimites(iaux, jaux) && !blocked){
            if (!tablero[iaux][jaux].IsEmpty()){
                blocked = true;
                if (tablero[iaux][jaux].getPieza().getColor().equals(colorContrario)){
                    Pieza pz = tablero[iaux][jaux].getPieza();
                    if (pz instanceof Rook || pz instanceof Queen) return true;
                }
            }
            --jaux;
        }
        
        // CABALLOS
        
        if (!FueraLimites(i-2, j-1) && !tablero[i-2][j-1].IsEmpty() && tablero[i-2][j-1].getPieza() instanceof Knight && tablero[i-2][j-1].getPieza().getColor().equals(colorContrario)){
            return true;
        }
        if (!FueraLimites(i-2, j+1) && !tablero[i-2][j+1].IsEmpty() && tablero[i-2][j+1].getPieza() instanceof Knight && tablero[i-2][j+1].getPieza().getColor().equals(colorContrario)){
            return true;
        }
        if (!FueraLimites(i+1, j-2) && !tablero[i+1][j-2].IsEmpty() && tablero[i+1][j-2].getPieza() instanceof Knight && tablero[i+1][j-2].getPieza().getColor().equals(colorContrario)){
            return true;
        }
        if (!FueraLimites(i-1, j-2) && !tablero[i-1][j-2].IsEmpty() && tablero[i-1][j-2].getPieza() instanceof Knight && tablero[i-1][j-2].getPieza().getColor().equals(colorContrario)){
            return true;
        }
        if (!FueraLimites(i+1, j+2) && !tablero[i+1][j+2].IsEmpty() && tablero[i+1][j+2].getPieza() instanceof Knight && tablero[i+1][j+2].getPieza().getColor().equals(colorContrario)){
            return true;
        }
        if (!FueraLimites(i-1, j+2) && !tablero[i-1][j+2].IsEmpty() && tablero[i-1][j+2].getPieza() instanceof Knight && tablero[i-1][j+2].getPieza().getColor().equals(colorContrario)){
            return true;
        }        
        if (!FueraLimites(i+2, j-1) && !tablero[i+2][j-1].IsEmpty() && tablero[i+2][j-1].getPieza() instanceof Knight && tablero[i+2][j-1].getPieza().getColor().equals(colorContrario)){
            return true;
        }
        if (!FueraLimites(i+2, j+1) && !tablero[i+2][j+1].IsEmpty() && tablero[i+2][j+1].getPieza() instanceof Knight && tablero[i+2][j+1].getPieza().getColor().equals(colorContrario)){
            return true;
        }
        
        return false;
    }

    /**
     * Devuelve cierto si el jugador de dicho colo esta en Jaque Mate
     * @param color Color del jugador
     * @return Si se esta en Jaque Mate
     */
    public boolean colorEstaEnJaqueMate(String color){
        return colorEstaEnTablas(color) && colorEstaEnJaque(color);
    }

    /**
     * Main para debuggar
     * @param args 
     */
    public static void main(String[] args){
        
        Tablero tab = new Tablero();

        try{
//            tab.tablero = parseTablero("4BN1B/6Pp/7k/8/5K2/8/8/8 w - - 0 1"); // Coronación 1 jugada mate blancas)
//             tab.tablero = parseTablero("6k1/2P2ppp/8/8/8/8/8/7K w - - 0 1"); // Coronacion 1 jugada mate blancas
//             tab.tablero = parseTablero("1N1b4/6nr/R5n1/2Ppk2r/K2p2qR/8/2N1PQ2/B6B w - - 0 1"); // CHECK (un rey desapareix [SOLUCIONADO])
//             tab.tablero = parseTablero("7R/7R/8/8/8/8/8/k6K w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("5rk1/5ppp/8/8/8/8/1Q6/B6K w - - 0 1"); // Enroc mate blanques alfil reina CHECK
//             tab.tablero = parseTablero("b6k/1q6/8/8/8/8/5PPP/5RK1 b - - 0 1"); // Enroc mate negres alfil reina CHECK
//             tab.tablero = parseTablero("r5k1/5ppp/8/8/8/8/1Q6/1R5K w - - 0 1"); //  blanques mate 2 enroc mate sacrifici dama CHECK
//             tab.tablero = parseTablero("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5 w - - 0 1"); // blanques mate 2 ( 1er llista ) CHECK -> FAIL -> CHECK
//             tab.tablero = parseTablero("2n5/5PB1/2p1NK2/5B1b/1Pp4p/P1kp1P1n/Q4p2/5N1r w - - 0 1"); // blanques mate 2 ( 2er llista ) CHECK   
//             tab.tablero = parseTablero("rr4k1/5ppp/8/8/8/2Q5/2R5/2R4K w - - 0 1"); // enroc mate sacrifici dama doble (blanques 5) CHECK
//             tab.tablero = parseTablero("2r4k/2r5/2q5/8/8/8/5PPP/RR4K1 w - - 0 1"); // enroc mate sacrifici dama doble (blanques 5) CHECK
//             tab.tablero = parseTablero("2R5/2N4K/2pn2B1/Nb6/5p2/B1k1p2Q/2pn4/3R4 w - - 0 1"); // CHECK (rey desapareix [SOLUCIONADO])
//             tab.tablero = parseTablero("5Nn1/p2p3r/Bn4rP/1PpkB1PR/2N1p1R1/2PP1pK1/8/3Q4 w - - 0 1"); // CHECK blanques mate 2 (4t llista)
//             tab.tablero = parseTablero("2QR3r/1q2Pb2/1bpBpp2/p6n/KNpkpN2/1P3P2/3PP3/n3r3 w - - 0 1"); // CHECK blanques mate 2 (5e llista)
//             tab.tablero = parseTablero("2QR3r/1q2Pb2/1bpBpp2/p6n/KNpkpN2/1P3P2/3PP3/n3r3 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("8/1P2pr1P/1P1pNp2/4kBp1/3pP1Q1/1rp3n1/b3p1N1/5nK1 w - - 0 1"); // FAIL
//             tab.tablero = parseTablero("8/2pN1Np1/rp3P1p/p1B1ppP1/2PpkP2/4P1P1/2P1KQ1b/7b w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("3r4/q3r3/1b6/2n2Rp1/2P2N2/QP2k1P1/4P3/BN2RnKB w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("2N1n1B1/2p4K/4Rp1B/2q2k1p/1p5R/3n1b1P/8/Q7 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("1K3R2/1p1B3n/8/p1B3p1/r3k3/1Q1N1N1r/3p4/2b1nq2 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("8/2p2P2/p1n3b1/3p3p/Q2pk3/rp4B1/1rnP1R1K/2NB3R w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("3Q4/r2b1KN1/2pk1n1R/P1p1N3/2Pn2p1/8/4P1r1/3R3B w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("3NK3/4Pp2/3P1Rp1/1Bpk4/n1N5/5P2/1r2b3/Q3R1B1 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("2r4R/3r4/b6B/5PPk/7p/8/3pRP2/b2B1KQ1 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("2bq4/Q7/4rN2/5n2/2BPr2R/4k3/2n3PB/1N1R2K1 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("8/1p1RPq2/5n1Q/3P4/RBNkpnN1/K2p4/B2Ppr2/8 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("B6K/B1N5/2np3p/2r2n1Q/1p2k3/1P2PNP1/4P3/8 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("5r2/1pR3Bn/1KP1k3/2P1p3/1p3pP1/5Q2/3Pp2p/1B1b4 w - - 0 1"); // CHECK
//             tab.tablero = parseTablero("4r3/2r5/Bp2R2b/1n6/2n2Q2/3kNbRq/2p1p1N1/2K1B3 w - - 0 1"); // CHECK
//            
//            tab.tablero = parseTablero("1N1b4/6nr/R5n1/2Ppk2r/r2p2qR/8/2N1PQ2/B6B w - - 0 1"); // PRUEBAS FEN


            tab.printarTablero();
            System.out.println();
            System.out.println();

            //System.out.print(tab.tableroToFEN());
            //tab.asignarAmenazas();

    //        List<Pieza> lp = tab.getPiezasBlancas();
    //        
    //        for (Pieza p : lp){
    //            if (p instanceof King){
    //                System.out.println("KING");
    //            }
    //            List<Celda> lc = p.JugadasPosibles(tab);
    //            
    //            System.out.println(p.getRepresentacion() + " " + "[" + p.getFila() + "]" + "[" + p.getColumnaInt() + "]:");
    //            for (Celda c : lc){
    //                System.out.println("[" + c.getFila() + "]" + "[" + c.getColumnaInt() + "]");
    //            }
    //        }

    //        List<Celda> lc = tab.tablero[2][7].getPieza().JugadasPosibles(tab);
    //        for (Celda c : lc){
    //            System.out.println(c.getPieza().getRepresentacion() + "[" + c.getFila() + "]" + "[" + c.getColumnaInt() + "]");
    //        }
            //TreeNode<Movimiento> root = null;
            //boolean haGanado = IASimple.minimax(3, "White", tab);
            int N = 3;
            //boolean haGanado = IASimple.minimaxcolor(N, White, true, tab);
            
            long iniA = System.currentTimeMillis();
            boolean haGanado = IASuperior.minimaxcolor(N, White, true, tab);
            long finA = System.currentTimeMillis();
            
            
            long iniS = System.currentTimeMillis();
            boolean haGanadoS = IASimple.minimaxcolor(N, White, true, tab);
            long finS = System.currentTimeMillis();
            
            
            
            
            //boolean haGanado = IASimple.minimaxarbol(3, "White", tab, root);
            //Movimiento pm = null;
            //boolean haGanado = IASimple.minimaxjugada(3, White, true, pm, tab);

    //        System.out.println("Siguiente jugada:");
    //        if (pm != null) System.out.println(pm.toString());

            //if (root != null) System.out.println(root.toString());
            if (haGanado){
                System.out.println("IASuperior: Hay jaque mate en " + (N+1)/2 + " jugadas.");
            }
            else{
                System.out.println("IASuperior:No existe solución con el número de jugadas introducido");
            }
            
            if (haGanadoS){
                System.out.println("IASimple: Hay jaque mate en " + (N+1)/2 + " jugadas.");
            }
            else{
                System.out.println("IASimple: No existe solución con el número de jugadas introducido");
            }
             
            System.out.println("IASimple: " + (finS-iniS)/100);
            System.out.println("IASuperior: " + (finA-iniA)/100);
            
        }
        catch (Exception ex){
            System.out.println("Error al ejecutar main(): " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public boolean getJugadasPossiblesCelda(Movimiento m) {
        Pieza p = this.tablero[m.getxi()][m.getyi()].getPieza();
        List<Celda> auxCeldas = p.JugadasPosibles(this);
        Pieza aux = (Pieza) p.miClone();
        aux.setColumna((char) (m.getyf()+'a'));
        aux.setFila(m.getxf());
        return auxCeldas.contains(new Celda(m.getxf(), m.getyf(), aux));
    }
}