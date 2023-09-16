package DriversDominio;

import Dominio.Problema;
import Dominio.BdProblemas;
import Dominio.Usuario;
import Dominio.io;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * Classe Driver que nos permite comprobar el funcionamiento de las classe BDProblemas y Problema
 */

public class DriverProblema {
    private static inout std1 = inout.getInstance();
    private static int op =0;
    static String[] opciones = {"Añadir nuevo problema", "Ver Problemas del Sistema",
                                "Borrar Problema", "Editar Problema", "Ver Info Problema", "Ver Ranking Problema", "Cargar Problemas Ejemplo"};


    private static BdProblemas BD = BdProblemas.getInstance();
    private static Problema p = new Problema();

    public static void main(String[] args) throws Exception {
        Method[] m = new Method[]{  DriverProblema.class.getMethod("addNewProblem"),
                                    DriverProblema.class.getMethod("viewAllProblems"),
                                    DriverProblema.class.getMethod("deleteProblema"),
                                    DriverProblema.class.getMethod("editProblema"),
                                    DriverProblema.class.getMethod("viewProblem"),
                                    DriverProblema.class.getMethod("viewRaning"),
                                    DriverProblema.class.getMethod("loadProblemas")};
        while (op!=-1){
            std1.writeln("Driver de Problemas, introduzca un comando:");
            std1.write(opciones);
            op = std1.readint();
            if(op >= 0 && op <= m.length) m[op].invoke(null);
        }
    }

    public static void loadProblemas() throws Exception{
        int n = 0;
        for (String aux : getFENSistema()) {
            try {
                p = new Problema(aux, 2, "Problema Admin" + (++n), 5);
                if(p.isTeSolucion()) {
                    BD.addProblema(p);
                }
                else System.out.println("No existe solucion para el problema "+ aux);

            } catch (Exception e){
                System.out.println("error, no se ha leido un FEN correcto en la linea "+n);
                --n;
            }
        }
    }
    private static List<String> getFENSistema() throws Exception {
        List<String> ret = new ArrayList<>();
        File f = null;
        try{
            f = new File(DriverUsuario.ProblemasSistemaAdmin);
            Scanner sc = new Scanner(f);
            String l;
            while (sc.hasNextLine()){
                l = sc.nextLine();
                if(l != "") ret.add(l);
            }

        } catch (Exception e){
            std1.writeln("No se ha encontrado el archivo, generando uno");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Borre esta linea y coloque en cada linea un fem para que el sistema los cargue\n");
            bw.close();
            System.out.println("Cargue los nuevos fem y reinicie el Driver.");
            System.exit(1);
        }
        return ret;

    }
    public static void viewRaning(){
        return;
    }

    public static Problema addNewProblem() throws Exception {
        std1.writeln("Introduzca el tema: (Mate en 2 de Valery Shansin)");
        std1.readnext();
        String tema = std1.readline();
        std1.writeln("Introduzca el numero de jugadas en llegar al mate (>= 2)");
        int n = std1.readint();
        std1.writeln("Introduzca el FEN inicial");
        std1.readnext();
        String fen = std1.readline();
        std1.writeln("Introduzca una dificultad para el problema:");
        int d = std1.readint();
        p = new Problema(fen,n,tema,d);
        if(p.isTeSolucion()) {
            System.out.println("tiene solución, añadiendo a la base de datos");
            BD.existeProblema(p.getTema());
            BD.addProblema(p);
        }
        return p;
    }

    public static void viewProblem() throws Exception {
        std1.write(BD.getAllProblemas());
        std1.writeln("Introduzca un problema(tema) para ver sus datos:");
        std1.readnext();
        String taux = std1.readline();
        std1.writeln(BD.getProblemaInfo(taux));
        std1.write(BD.getTableroProblemaChar(taux));

    }
    public static void viewAllProblems() throws Exception {
        std1.writeln("Problemas del sistema:");
        std1.write(BD.getAllProblemas());
    }

    public static void deleteProblema() throws Exception {
        std1.write(BD.getAllProblemas());
        std1.writeln("Introduzca un problema(tema) para borrarlo o _ para no borrar ninguno");
        std1.readnext();
        String taux = std1.readline();
        if(!taux.equals("_")) BD.delProblema(taux);
    }

    public static void editProblema() throws Exception {
        if(BD.getnProblemas() == 0) {
            std1.write("No hay problemas en el sistema");
            return;
        }
        std1.write(BD.getAllProblemas());
        std1.writeln("Introduzca un problema(tema) para modificarlo");
        std1.readnext();
        String taux = std1.readline();
        std1.writeln("Introduzca el nuevo numero de jugadas o pulse intro para no modificarlo");
        int n = std1.readint();
        std1.writeln("Introduzca el FEN nuevo o pulse intro para no modificarlo");
        std1.readnext();
        String fen = std1.readline();
        std1.writeln("Introduzca una nueva dificultad para el problema, o intro para modificarla:");
        int d = std1.readint();
        
        boolean r = BD.modificarDatosProblema(fen, n, taux, d);
        if(r) std1.writeln("Cambio realizado con exito");
        else std1.writeln("No ha sido possible modificar el problema con tema " + taux);
    }
    public static Problema getProblema() throws Exception {
        std1.write(BD.getAllProblemas());
        std1.readnext();
        String taux = std1.readline();
        if(BD.existeProblema(taux))return BD.getProblema(taux);
        else {
            std1.writeln("No exite problema, intenta de nuevo");
            return getProblema();
        }
    }
}
