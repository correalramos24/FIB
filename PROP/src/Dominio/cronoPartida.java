package Dominio;

/**
 * Classe que mantiene un cronometro de una partida de ajedrez, consta de dos cronometros(uno por cada jugador).
 * @author Victor Correal Ramos
 */

public class cronoPartida{

    private double tiempoJ1;
    private double tiempoJ2;
    protected int jugadorActual;

    private static final int jugador1 = 1;
    private static final int jugador2 = 0;
    public final String j1 = "Jugador1";
    public final String j2 = "Jugador2";

    private double TiempoI, TiempoF;

    public static void main(String[] args) throws InterruptedException {
        cronoPartida cp = new cronoPartida();

        while(true){
            cp.cambiarCrono();
            System.out.println(cp);
            Thread.sleep(1000);
            }
    }
    
    /**
     * Creadora de la clase cronometro con ambos tiempos a 0
     */
    public cronoPartida(){
        jugadorActual = jugador1;
        tiempoJ1 = tiempoJ2 = 0.0;
    }

    /**
     * Empiza a contar el tiempo del jugador que mueve primero
     */
    public void start(){
    TiempoI = System.currentTimeMillis();
    }
    
    /**
     * Suma el tiempo transcurrido al jugador que estaba moviendo y empieza
     * a contar el tiempo del otro jugador
     */
    public void cambiarCrono(){
      TiempoF = System.currentTimeMillis();
      if(jugadorActual == jugador1){ // jugador1
          tiempoJ1 += (TiempoF-TiempoI);
          jugadorActual  = jugador2;
      }
      else{ // jugador 2
        tiempoJ2 += (TiempoF-TiempoI);
        jugadorActual  = jugador1;
      }
      TiempoI = System.currentTimeMillis();
  }

    /**
     * Devuelve el tiempo utilizado hasta el momento
     * @return long que representa el tiempo utilizado por el jugador1
     */
    public double getTiempo1()
    {
      return tiempoJ1/1000;
    }
    
    /**
     * Devuelve el tiempo utilizado hasta el momento
     * @return long que representa el tiempo utilizado por el jugador2
     */
    public double getTiempo2()
    {
    return tiempoJ2/1000;
    }
    
    /**
     * Indica el jugador cuyo tiempo esta correndo actualmente
     * @return Jugador con el tiempo sumando
     */
    public String jugadorActual(){
    if(jugadorActual == jugador1)return "Jugador1";
    else return "Jugador2";
    }

    /**
     * Devuelve un string con los segundos del jugador1
     * @return Segundos del jugador1
     */
    public String getSegundos1() {
        int t = (int) getTiempo1()%60;
        if(t < 10) return "0"+t;
        return "" + t/10 + t%10;
    }
    
    /**
     * Devuelve un string con los segundos del jugador2
     * @return Segundos del jugador2
     */
    public String getSegundos2() {
        int t = (int) getTiempo2()%60;
        if(t < 10) return "0"+t;
        return "" + t/10 + t%10;
    }
    
    /**
     * Devuelve un string con los minutos del jugador1
     * @return Minutos del jugador1
     */
    public String getMinutos1() {
        return "" + (int) (getTiempo1()/60);
    }
    
    /**
     * Devuelve un string con los minutos del jugador2
     * @return Minutos del jugador2
     */
    public String getMinutos2() {
        return "" + (int) (getTiempo2()/60);
    }
    
    /**
     * Devuelve el string con los tiempos de ambos jugadores indicando de cual
     * es cada uno
     * @return String con los tiempos de los jugadores
     */
    @Override
    public String toString() {
        return "Tiempo total de partida por J1: " + getMinutos1() + ":"+ getSegundos1() + "\n" +
               "Tiempo total de partida por J2: " + getMinutos2() + ":"+ getSegundos2() + "\n";
    }
}
