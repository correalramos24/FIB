package Dominio;

/**
 * Classe abstracta que identifica a un jugador de un juego
 * @author Nando
 */
public abstract class Jugador {

    private String apodo;
    public static final int atacante = 1;
    public static final int defensor = 0;
    private int rol;
    private String color;
    
    public Jugador(){
        
    }
    
    /**
     * Creadora de jugador con apodo
     * @param apodo Apodo del jugador
     */
    public Jugador(String apodo){
        this.apodo = apodo;
    }

    /**
     * Devuelve el color del jugador
     * @return Color del jugador
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Establece o modifica el color del jugador
     * @param color Nuevo color del jugador
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /**
     * Devuelve el rol del jugador
     * @return Rol del jugador
     */
    public int getRol() {
        return rol;
    }
    
    /**
     * Establece o modifica el rol del jugador
     * @param r Nuevo rol del jugador
     * @throws Exception 
     */
    public void setRol(int r) throws Exception {
        if(r!= atacante && r != defensor) throw new Exception("Mal rol");
        rol = r;
    }
    /**
     * Devuelve el apodo del jugador
     * @return Apodo del jugador
     */
    public String getApodo() {
        return this.apodo;
    }
    //todo: mirar como hacemos esto

    /**
     * Devuelve el siguiente movimiento del jugador
     * @param N Numero de jugadas restantes, solo tiene uso "real" en la IA
     * @param tablero 
     * @return Movimiento siguiente
     */
    public abstract Movimiento mover(int N, Tablero tablero) throws Exception;
}
