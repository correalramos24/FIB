package Dominio;

/**
 * Clase que representa un moviemiento de una pieza
 * @author Fernando Marimon 
 */
public class Movimiento {
    Celda Celdainicial;
    Celda Celdafinal;
    private int xi,yi,xf,yf;

    /**
     * Creadora de movimiento a partir de dos celdas
     * @param celdainicial Celda de partida
     * @param celdafinal Celda de destino
     */
    public Movimiento(Celda celdainicial, Celda celdafinal) {
        Celdainicial = celdainicial;
        Celdafinal = celdafinal;
        xi = celdainicial.getFila();
        yi = celdainicial.getColumnaInt();
        xf = celdafinal.getFila();
        yf = celdafinal.getColumnaInt();
    }
	//TODO: int --> char

    /**
     * Creadora de movimiento a partir de un par de coordenadas de celdas
     * @param xinicial Coordenada de partida de la fila
     * @param yinicial Coordenada de partida de la columna
     * @param xfinal Coordenada de destino de la fila
     * @param yfinal Coordenada de destino de la columna
     */
        public Movimiento (int xinicial, int yinicial, int xfinal, int yfinal){
        xi = xinicial;
        yi = yinicial;
        xf = xfinal;
        yf = yfinal;
    }

    //======================GETTERS && SETTERS DEFECTO======================

    /**
     * Devuelve la celda de partida
     * @return Celda de partida
     */
    protected Celda getCeldainicial() {
        return Celdainicial;
    }

    /**
     * Establece o modifica la celda de partida
     * @param celdainicial Nueva celda de partida
     */
    protected void setCeldainicial(Celda celdainicial) {
        this.Celdainicial = celdainicial;
    }

    /**
     * Devuelve la celda de destino
     * @return Celda de destino
     */
    protected Celda getCeldafinal() {
        return Celdafinal;
    }

    /**
     * Establece o modifica la celda de destino
     * @param celdafinal Nueva celda de destino
     */
    public void setCeldafinal(Celda celdafinal) {
        this.Celdafinal = celdafinal;
    }

    /**
     * Devuelve la coordenada de la fila partida
     * @return Fila de partida
     */
    public int getxi() {
        return xi;
    }

    /**
     * Devuelve la coordenada de la columna de partida
     * @return Columna de partida
     */
    public int getyi() {
        return yi;
    }

    /**
     * Devuelve la coordenada de la fila destino
     * @return Fila de destino
     */
    public int getxf() {
        return xf;
    }

    /**
     * Devuelve la coordenada de la columna de destino
     * @return Columna de destino
     */
    public int getyf() {
        return yf;
    }
    
    /**
     * Deveulve los atributos de movimiento en formato de string
     * @return Atributos de movimiento
     */
    @Override
    public String toString(){
        return "Inicio: " + (char)(getyi()+'a') + "-" + (8-getxi()) + " Destino: " + (char)(getyf()+'a') + "-" + (8-getxf());
        //return "X inicial: " + getxi() + "; Y inicial: " + getyi() + "; X final: " + getxf() + "; Y final: " + getyf();
    }
}
