package Dominio;

import static Dominio.IASimple.BLACK;
import static Dominio.IASimple.WHITE;

/**
 * Factory de Joc per cadascun dels diferents jocs del que disposa el sistema.
 * @author Victor Correal
 */
public class JocFactory {

    private static JocFactory instance;
    private Jugador j1, j2;
    public static String iaSimple = "iaSIMPLE";
    public static String iaSuperior = "iaSUPERIOR";

    /**
     * Constructora del patron Singletone...
     * @return Instancia de JocFactory
     */
    public static JocFactory getInstace() {
        if (instance == null) instance = new JocFactory();
        return instance;
    }

    /**
     * Creadora privada de factory
     */
    private JocFactory(){}
       
     /**
     * Crea un Joc entre dos maquinas
     * @param p Problema que se jugara en la partida
     * @param color Color de las ppiezas del atacante
     * @param ia1 Tipo de la IA atacante
     * @param ia2 Tipo de la IA defensora
     * @return La partida a jugar
     * @throws Exception 
     */
    public Joc getJocMaquinaMaquina(Problema p, String ia1, String ia2) throws Exception {
        
        Jugador j1, j2;
        
        if(ia1.equals(iaSimple)){ j1 = new IASimple(); }
        else{ j1 = new IASuperior(); }
        
        if (ia2.equals(iaSimple)){ j2 = new IASimple(); }
        else { j2 = new IASuperior(); }
        
        j1.setRol(Jugador.atacante);
        j1.setColor(p.getColor());
        
        j2.setRol(Jugador.defensor);
        j2.setColor(colorContrario(p.getColor()));

        return new Joc(j1, j2, p);
    }
    
    /**
     * Crea un juego entre humano y maquina, atacando el humano
     * @param p Problema que se jugara en la partida
     * @param color Color de las piezas del atacante
     * @param tipoIa Tipo de la IA defensora
     * @param u El usuario que jugara de atacante
     * @return La partida a jugar
     * @throws Exception 
     */
    public Joc getJocHumaMaquina(Problema p, String tipoIa, Usuario u) throws Exception {
        Jugador ia;
        if (tipoIa.equals(iaSimple)){ ia = new IASimple(); }
        else { ia = new IASuperior(); }
        
        u.setRol(Jugador.atacante);
        u.setColor(p.getColor());
        
        ia.setRol(Jugador.defensor);
        ia.setColor(colorContrario(p.getColor()));
        
        return new Joc(u,ia, p);
    }
    
    /**
     * Crea un juego entre dos humanos, siendo el primero el atacante
     * @param p Problema que se jugara en la partida
     * @param color Color de las piezas del atacante
     * @param u1 El usuario que jugara de atacante
     * @param u2 El usuario que jugara de defensor
     * @return La partida a jugar
     * @throws Exception 
     */
    public Joc getJocHumaHuma(Problema p, Usuario u1, Usuario u2) throws Exception {
        u1.setRol(Jugador.atacante);
        u1.setColor(p.getColor());
        u2.setRol(Jugador.defensor);
        u2.setColor(colorContrario(p.getColor()));
        return new Joc(u1, u2, p);
    }

    /**
     * Crea un juego entre maquina y humano, atacando la maquina
     * @param p Problema que se jugara en la partida
     * @param color Color piezas del atacante
     * @param tipoIa Tipo de la IA atacante
     * @param u El usuario que jugara de defensor
     * @return La partida a jugar
     * @throws Exception 
     */
    public Joc getJocMaquinaHuma(Problema p, String tipoIa, Usuario u) throws Exception {
        Jugador ia; 
        if (tipoIa.equals(iaSimple)) { ia = new IASimple(); }
        else { ia = new IASuperior(); }       
        ia.setColor(p.getColor());
        ia.setRol(Jugador.atacante);
        u.setRol(Jugador.defensor);
        u.setColor(colorContrario(p.getColor()));
        return new Joc(ia, u, p);
    }
    
     /**
     * Retorna el color contrario al dado
     * @param color Color a buscar el contrario
     * @return Devuelve white si color es black y viceversa
     */
    private static String colorContrario(String color){       
        if (color.equals(WHITE)) return BLACK;
        else return WHITE;
    }
}
