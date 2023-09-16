package Persistencia;

import java.io.IOException;
import java.util.Set;

/**
 * @author Victor Correal
 */
public class CtrlPersistencia {
   
    public static final String directorio = "bdv2";
   
    private static CtrlPersistencia instance = null;
    
    private BD cpUsuario;
   
    private BD cpProblemas;

   
    public static CtrlPersistencia getInstance() {
        if (instance == null) {
            try {
                instance = new CtrlPersistencia();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

   
    public CtrlPersistencia() throws IOException {
        cpUsuario = new BD(directorio, "Usuario");
        cpProblemas = new BD(directorio, "Problemas");
    }

    public BD getCpProblemas() {
        return cpProblemas;
    }

    public BD getCpUsuario() {
        return cpUsuario;
    }
    
    
}
