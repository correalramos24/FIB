package Dominio;

//import Dominio.CtrlDominioProblemas;


public class CtrlDominioJoc {


    //private Problema p;
    private Joc partida;
    private Tablero t;
    private Jugador actual;
    private Jugador atacante;
    private Jugador defensor;
    private int jugadasRestantes;
    private String colorAtacante;
    

    public CtrlDominioJoc(String idProblema, String atacante, String defensor){
        /*CtrlDominioProblemas CDP = new CtrlDominioProblemas();
        this.problemas = CtrlDominioProblemas.getProblema(idProblema);*/
        /*try{
        Problema p = getProblema(idProblema);
        this.t = new Tablero(p.getFEN(), p.getnJugadas());
        this.jugadasRestantes = t.getnJugadas();
        
        switch (atacante){
            case "Persona":
                this.actual = this.atacante = new Usuario();
                break;
            case "IASimple":
                this.actual = this.atacante = new IASimple();
                break;
            case "IASuperior":
                this.actual = this.atacante = new IASuperior();
                break;
            default:
        }
        
        switch (defensor){
            case "Persona":
                this.defensor = new Usuario();
                break;
            case "IASimple":
                this.defensor = new IASimple();
                break;
            case "IASuperior":
                this.defensor = new IASuperior();
                break;
            default:
        }
        
        if (atacante.equals("Persona") && defensor.startsWith("IA")){ partida = JocFactory.getInstace().getJocHumaMaquina(p, colorAtacante, defensor, new Usuario()); }
        else if (atacante.equals("Persona") && defensor.startsWith("P")){ partida = JocFactory.getInstace().getJocHumaHuma(p, colorAtacante, new Usuario(), new Usuario()); }
        
        }
        catch (Exception ex){
            ex.printStackTrace();
        }*/
    }
    
    /**
     * Inicializa el controlador del dominio
     * @param p Problema de la partida que se va a jugar
     * @param atacante Jugador atacante
     * @param defensor Jugador defensor
     */
    public void initControlador(String p, String atacante, String defensor){
      /*this.nJugadas = p.getnJugadas();
      this.t = new Tablero(p.getFEN(), p.getnJugadas());
      this.actual = atacante;
      this.atacante = atacante;
      this.defensor = defensor;
      this.colorAtacante = p.getColor();
      
      if (atacante instanceof )
      this.partida = JocFactory.getInstace().*/
    }
    
    public void calcularJugadasPossibles(int x, int y){
      return;
    }
    
    public Boolean mover(int x, int y){
      return true;
    }
    
    public String[] getJugadoresProblema(){
      return null;
    }
    
    public String getFENProblema(){
      return null;
    }
    
    public Boolean esFinalPartida(){
      return false;
    }
    
    public int getNActual(){
      return 100;
    }
    
    public int getNTotal(){
      return 100;
    }
    
    public String jugadorActual(){
      return null;
    }
    
    public Double getTimerActual(){
      return null;
    }
    
    public boolean getResultadoFinal(){
        return true;
    }
}
