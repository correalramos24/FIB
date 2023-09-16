package Dominio;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Classe auxiliar que permite almacenar un resultado de una partida, tanto si el usuario gana como si pierde.
 * @author Fernando Marimon
 */
//todo: comentarios javadoc funciones y atributos
public class Resultado implements Serializable{

    private Double tiempo; //Segundos, hacer transformacion para minutos y eso
    private Long fechaJuego; //Fecha de juego
    private String jAtacante;
    private String jDefensor;
    private boolean resultadoFinal;
    
    /**
     * Creadora de resultado vacia
     */
    public Resultado() {
    }

    /**
     * Creadora de resultado con todos sus parametros
     * @param tiempo Segundos empleados por el atacante
     * @param fechaJuego Fecha en la que se jugo la partida
     * @param atacante Jugador atacante
     * @param defensor Jugador defensor
     * @param rf Resultado de la partida
     */
    public Resultado(Double tiempo, Long fechaJuego, String atacante, String defensor, boolean rf) {
        this.tiempo = tiempo;
        this.fechaJuego = fechaJuego;
        this.jAtacante = atacante;
        this.jDefensor = defensor;
        this.resultadoFinal = rf;
    }
//=============================DEFAULT GETTERS && SETTERS POR DEFECTOR =============================


    /**
     * Devuelve el atacante del resultado
     * @return Atacante
     */
    public String getjAtacante() {
        return jAtacante;
    }

    /**
     * Establece el atacante del resultado
     * @param jAtacante Nuevo atacante
     */
    public void setjAtacante(String jAtacante) {
        this.jAtacante = jAtacante;
    }

    /**
     * Devuelve el defensor del resultado
     * @return Defensor
     */
    public String getjDefensor() {
        return jDefensor;
    }

    /**
     * Establece el defensor del resultado
     * @param jDefensor Nuevo defensor
     */
    public void setjDefensor(String jDefensor) {
        this.jDefensor = jDefensor;
    }

    /**
     * Devuelve el tiempo empleado por el atacante
     * @return Teimpo del atacante
     */
        public Double getTiempo() {
        return this.tiempo;
    }

    /**
     * Establece el tiempo del atacante
     * @param tiempo Nuevo tiempo del atacante
     */
    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * Devuelve la fecha del resultado
     * @return Fecha del resultado
     */
    public Long getFechaJuego() {
        return this.fechaJuego;
    }

    /**
     * Establece la fecha del resultado
     * @param fechaJuego Nueva fecha del resultado
     */
    public void setFechaJuego(Long fechaJuego) {
        this.fechaJuego = fechaJuego;
    }

    public boolean getResultado() {
        return resultadoFinal;
    }


    //=============================DEFAULT GETTERS && SETTERS POR DEFECTOR =============================


    /**
     * Devuelve los atributos de resultado en formato de string
     * @return Atributos de resultado
     */
    @Override
    public String toString() {
        return "{" +
            " tiempo='" + getTiempo() + "'" +
            ", fechaJuego='" + getFechaJuego() + "'" +
            ", atacante='" + getjAtacante() + "'" +
            ", defensor='" + getjDefensor() + "'}'";
    }
    
    @Override
    public boolean equals(Object p) {
        if(p == this)
            return true;
        if(!(p instanceof Resultado))
            return false;
        Resultado comp = (Resultado) p;
        return this.jAtacante.equals(comp.jAtacante);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.tiempo);
        hash = 13 * hash + Objects.hashCode(this.fechaJuego);
        hash = 13 * hash + Objects.hashCode(this.jAtacante);
        hash = 13 * hash + Objects.hashCode(this.jDefensor);
        hash = 13 * hash + (this.resultadoFinal ? 1 : 0);
        return hash;
    }
    
    
}
