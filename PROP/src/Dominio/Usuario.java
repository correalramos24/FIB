package Dominio;

import DriversDominio.inout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Clase que representa un usuario
 * @author Fernando Marimon
 */
//todo: comentarios javadoc funciones y atributos
public class Usuario extends Jugador implements Serializable, Cloneable{
    private String nombre;
    private String apodo;
    private String password;
    private List<String> misProblemas; //problemas dados de alta por el usuario
    List<Resultado> miHistorial;
    private int moveFila = -1;
    private int moveCol = -1;
    /**
     * main para debugar
     * @param args
     */
    public static void main(String[] args) {
        Usuario u = new Usuario("bic", "vict", "334");
        System.out.println(u);
    }
    
    /**
     * Añade un problema a la lista de usuairo
     * @param id Tema del problema añadido
     */
    public void addProblema(String id){
        misProblemas.add(id);
    }
    
    /**
     * Devuelve el nombre de los problemas dados de alta por el usuario
     * @return Nombre de los problemas
     */
    public List<String> getProblemasUsuario(){
        return misProblemas;
    }
    //control errores aki!
    /**
     * Dada la Id de un problema lo elimina
     * @param id Id del problema a eliminar
     */
    public void deleteProblema(String id){
        if(misProblemas.contains(id))
            misProblemas.remove(id);
    }
    /**
     * Creadora rapida para debugar
     * @param nombre Nombre del usuario
     */
    @Deprecated
    public Usuario(String nombre) {
        super(nombre);
        this.apodo = nombre+"_123";
        this.misProblemas = new ArrayList<>();
        this.miHistorial = new ArrayList<>();
    }

    /**
     * Creadora de usuario dado su nombre, el apodo por el que se identificara al
     * jugar y su contraseña
     * @param nombre Nombre del usuario
     * @param apodo Apodo que aparecera en las partidas al jugarlas
     * @param password Contraseña del usuario
     */
    public Usuario(String nombre, String apodo, String password) {
        super(nombre);
        this.nombre = nombre;
        this.apodo = apodo;
        this.password = password;
        this.misProblemas = new ArrayList<>();
        this.miHistorial = new ArrayList<>();
    }
    
    private Usuario(String n, String a, String p, List<String> mp, List<Resultado> mh) {
        super(n);
        this.nombre = n;
        this.apodo = a;
        this.password = p;
        this.misProblemas = mp;
        this.miHistorial = mh;
    }
    public Usuario(){
        super();
    }
    //=============================DEFAULT GETTERS && SETTERS POR DEFECTOR =============================

    /**
     * Devuelve el nombre de usuario
     * @return Nombre de usuario
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Establece o modifica el nombre de usuario
     * @param nombre Nuevo nombre de usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el apodo de usuario
     * @return Apodo de usuario
     */
    public String getApodo() {
        return this.apodo;
    }

    /**
     * Establece o modicia el apodo de usuario
     * @param apodo Nuevo apodo de usuario
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    /**
     * Devuelve la constraseña de usuario
     * @return Contraseña de usuario
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Establece o modifica la constraseña de usuario
     * @param password Nueva contraseña de usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }
    //=============================DEFAULT GETTERS && SETTERS POR DEFECTOR=============================
    /**
     * Devuelve cierto si el usuario dado es igual a este
     * @param o Usuario a comparar
     * @return Si son iguales ambos usuarios
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre) && Objects.equals(apodo, usuario.apodo) && Objects.equals(password, usuario.password);
    }

    /**
     * Devuelve los atributos de usuario en formato de string
     * @return Atributos de usuario
     */
    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", apodo='" + getApodo() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

    /**
     * Dado un caracter alfabetico retorna su valor numerico siendo 'a' = 0, 'b' = 1,...
     * @param c Caracter a convertir
     * @return Conversion del caracter a entero
     */
    public int conversorCharInt(char c) {
        char aux = Character.toLowerCase(c);
        return aux - 'a';
    }
    /**
     * Dado un entero retorna el caracter alfabetico correspondiente siendo 0 = 'a', 1 = 'b',...
     * @param i Entero a convertir
     * @return Conversion del entero a caracter
     */
    public char conversorIntChar(int i ) {
        return (char) ('a' + i);
    }

    /**
     * Imprime por pantalla las coordenadas de las celdas de la lista
     * @param l Lista de celdas
     */
    public void PrintarJugadasPosiblesV3(List<Celda> l) {
        for (Celda c : l) {
            
            System.out.print(c.getColumna()+"-"+(8-c.getFila())+" ");
        }
        System.out.println();
    }

    /**
     * Dados un par de coordenadas, la primera de la pieza a mover y la segunda de
     * la celda de destino, creamos el mivimiento o nos indica porque no podemas
     * realizarlo
     * @param numeroJugadas Numero de jugadas restantes para finalizar la partida
     * @param t Estado actual del tablero de la partida
     * @return Movimiento a realizar
     * @throws java.lang.Exception
     */
    @Override
    public Movimiento mover(int numeroJugadas,Tablero t) throws Exception {
        
        //Inicializacion de todos los atributos que se usaran
        System.out.println("Quedan "+ (numeroJugadas+1)/2 +" jugadas, aprisa!!");
        inout std1 = inout.getInstance();
        List<Celda> jugadasPosibles;
        Celda[][] tablero = t.getTablero();
        int[] lectura = new int[2];
        char columnaIni = 'z', columnaFinal = 'z';
        int filaIni = -1, filaFinal = -1;
        boolean celdaIncorrecta = false, movimientoIlegal = false, primeraVez = true;
        while(celdaIncorrecta || movimientoIlegal || primeraVez) {
            
            //Frases de error y demas
            
            if(celdaIncorrecta) System.out.println("PIEZA MAL SELECCIONADA\n");
            else if(movimientoIlegal) System.out.println("MOVIMIENTO ILEGAL\n");
            if(!primeraVez) t.printarTablero();
            primeraVez = false;
            
            //Empezamos a pasar datos
            std1.writeln("Formato coordenadas: Letra Numero\n");
            std1.write("INTRODUZCA UNA PIEZA A MOVER: ");
            while(!mouseEventHecho()){
                Thread.sleep(5);
            }
            columnaIni = (char)moveCol;
            filaIni = moveFila;
            resetAuxiliaresMove();
            celdaIncorrecta = !t.esMiCelda(8-filaIni,columnaIni, getColor());
            //Hemos seleccionado una Pieza que es nuestra, vamos bien ^^
            if(!celdaIncorrecta) {
                jugadasPosibles = tablero[8-filaIni][conversorCharInt(columnaIni)].getPieza().JugadasPosibles(t);
                if(jugadasPosibles.size() > 0) {
                    std1.writeln("Tus posibles movimientos:");
                    PrintarJugadasPosiblesV3(jugadasPosibles);
                    std1.write("INTRODUZA UNA COORDENADA DE DESTINO: ");
                    while(!mouseEventHecho()){
                        Thread.sleep(5);
                    }
                    columnaFinal = (char)moveCol;
                    filaFinal = moveFila;
                    resetAuxiliaresMove();
                    if(!Tablero.FueraLimites(8-filaFinal,conversorCharInt(columnaFinal))) {
                        Pieza paux = (Pieza) tablero[8-filaIni][conversorCharInt(columnaIni)].getPieza().miClone();
                        paux.setColumna(columnaFinal);
                        paux.setFila(8-filaFinal);
                        Celda celdita = new Celda(8-filaFinal,conversorCharInt(columnaFinal),paux);
                        movimientoIlegal = !jugadasPosibles.contains(celdita);
                    }
                    else movimientoIlegal = true; //El destino estaba fuera del tablero
                }
                else {
                    System.out.println("Esta pieza no tiene movimientos posibles");
                    celdaIncorrecta = true;
                }
            }
        }
        return new Movimiento(8-filaIni,conversorCharInt(columnaIni),8-filaFinal,conversorCharInt(columnaFinal));
    }


    
    /**
     * Leemos las coordenadas de partida o destino de nuestro movimiento
     * @param c Contenedor de las coordenadas
     * @throws Exception 
     */
    private void leerValores(int[] c) throws Exception {
        inout std1 = inout.getInstance();
        c[0] = Character.toLowerCase(std1.read());
        while(c[0] <= ' ') c[0] = Character.toLowerCase(std1.read());
        c[1] = std1.read();
        while(c[1] <= ' ' || c[1] == '-') c[1] = std1.read();
        c[1] -= '0';
        std1.readline(); //Limpiar el buffer de entrada
    }
    private void resetAuxiliaresMove() {
        moveCol = moveFila = -1;
    }

    private boolean mouseEventHecho() {
        return moveCol != -1;
    }
    //Columna->char  Fila->int
    public void asignarAuxiliaresMove(int f, int c) {
        moveCol = c + 'a';
        moveFila = 8 - f;
    }
        
    public Usuario miClone() {
        return new Usuario(this.nombre, this.apodo, this.password, this.misProblemas, this.miHistorial);
    }

}

/*
    protected Movimiento mover(int numeroJugadas,Tablero t) throws Exception {

        //Inicializacion de todos los atributos que se usaran
        System.out.println("Quedan "+ (numeroJugadas+1)/2 +" jugadas, aprisa!!");
        inout std1 = inout.getInstance();
        List<Celda> jugadasPosibles;
        Celda[][] tablero = t.getTablero();
        int[] lectura = new int[2];
        char columnaIni = 'z', columnaFinal = 'z';
        int filaIni = -1, filaFinal = -1;
        boolean celdaIncorrecta = false, movimientoIlegal = false, primeraVez = true;
        while(celdaIncorrecta || movimientoIlegal || primeraVez) {

            //Frases de error y demas

            if(celdaIncorrecta) System.out.println("PIEZA MAL SELECCIONADA\n");
            else if(movimientoIlegal) System.out.println("MOVIMIENTO ILEGAL\n");
            if(!primeraVez) t.printarTablero();
            primeraVez = false;

            //Empezamos a pasar datos
            std1.writeln("Formato coordenadas: Letra Numero\n");
            std1.write("INTRODUZCA UNA PIEZA A MOVER: ");
            leerValores(lectura);
            columnaIni = (char)lectura[0];
            filaIni = lectura[1];
            celdaIncorrecta = !t.esMiCelda(8-filaIni,columnaIni, getColor());
            //Hemos seleccionado una Pieza que es nuestra, vamos bien ^^
            if(!celdaIncorrecta) {
                jugadasPosibles = tablero[8-filaIni][conversorCharInt(columnaIni)].getPieza().JugadasPosibles(t);
                if(jugadasPosibles.size() > 0) {
                    std1.writeln("Tus posibles movimientos:");
                    PrintarJugadasPosiblesV3(jugadasPosibles);
                    std1.write("INTRODUZA UNA COORDENADA DE DESTINO: ");
                    leerValores(lectura);
                    columnaFinal = (char)lectura[0];
                    filaFinal = lectura[1];
                    if(!Tablero.FueraLimites(8-filaFinal,conversorCharInt(columnaFinal))) {
                        Pieza paux = (Pieza) tablero[8-filaIni][conversorCharInt(columnaIni)].getPieza().miClone();
                        paux.setColumna(columnaFinal);
                        paux.setFila(8-filaFinal);
                        Celda celdita = new Celda(8-filaFinal,conversorCharInt(columnaFinal),paux);
                        movimientoIlegal = !jugadasPosibles.contains(celdita);
                    }
                    else movimientoIlegal = true; //El destino estaba fuera del tablero
                }
                else {
                    System.out.println("Esta pieza no tiene movimientos posibles");
                    celdaIncorrecta = true;
                }
            }
        }
        return new Movimiento(8-filaIni,conversorCharInt(columnaIni),8-filaFinal,conversorCharInt(columnaFinal));
    }
*/