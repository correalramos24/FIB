package DriversDominio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;
import java.lang.reflect.Method;

import Dominio.*;

/**
 * Driver para ver el funcionamiento del usuario y el juego de resolucion de problemas de ajedrez.
 */

public class DriverUsuario{

    public static final String ProblemasSistemaAdmin = "problemasFEN.txt";
    private static io std = io.getInstance();
    private static inout std1 = inout.getInstance();
    private static int aux;
    private static Map<String, Usuario> instanciasUsuario;
    private static Usuario u;

    static String[] opciones = {"Añadir problema", "Ver Mis Problemas", "Jugar Nueva Partida",
            "Ver Historial de Partidas"};
    
    public static void main(String[] args) throws Exception {
        instanciasUsuario = new TreeMap<>();
        u = new Usuario("admin", "admin", "1234");
        instanciasUsuario.put("admin", u);
        Method[] m = {  DriverUsuario.class.getMethod("addProblema"),
                        DriverUsuario.class.getMethod("getProblemas"),
                        DriverUsuario.class.getMethod("newGame")};
        try {
            System.out.println("Intentando cargar los problemas del sistema");
            DriverProblema.loadProblemas();
        } catch (Exception e){
            System.out.println("No se ha logrado cargar los problemas, por favor introduzca los fem en el fichero");
            File f = new File(ProblemasSistemaAdmin);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("Borre esta linea y coloque en cada linea un fem para que el sistema los cargue\n");
            bw.close();
            System.out.println("Cargue los nuevos fem y reinicie el Driver.");
            System.exit(1);
        }
        while(true){
            boolean b = false;
            std1.writeln("Driver de Usuario, introduzca un numero:");
            std1.writeln("0. Login Usuario");
            std1.writeln("1. Nuevo Usuario");
            aux = std1.readint();
            if(aux == 0) b = loginUsuario();
            else if(aux == 1) nuevoUsuario();
            while(aux >= 0 && b){
                aux = getOperacion();
                if(aux == -1) logOut();
                else if(aux >= 0 && aux < m.length) m[aux].invoke(null);
            }
        }
    }
    public static void getProblemas() throws Exception {
        for (String s : u.getProblemasUsuario()) {
            std1.write(s);
        }
    }
    public static void addProblema() throws Exception {
        Problema prob = DriverProblema.addNewProblem();
        u.addProblema(prob.getTema());
    }
    public static void newGame() throws Exception {
        std1.writeln("Atacante vs Defensor");
        std1.writeln("0.Humano vs Humano");
        std1.writeln("1.Humano vs Maquina Simple");
        std1.writeln("2.Maquina Simple vs Humano");
        std1.writeln("3.Maquina Simple vs Maquina Simple");
        int n = std1.readint();
        Problema p = DriverProblema.getProblema();
        std1.writeln("Introduza un problema(tema) para jugar:");
        /*switch (n){
            case 0:
                Usuario u2 = new Usuario("Invitado");
                //JocFactory.getInstace().getJocHumaHuma(p,u,u2).iniciar();
                break;
            case 1:
               // JocFactory.getInstace().getJocHumaMaquina(p, JocFactory.iaSimple, u).iniciar();
                break;
            case 2:
                //JocFactory.getInstace().getJocMaquinaHuma(p, JocFactory.iaSimple, u).iniciar();
                break;
            case 3:
                //Joc j = JocFactory.getInstace().getJocMaquinaMaquina(p, JocFactory.iaSimple, JocFactory.iaSimple);
                //j.iniciar();
                break;
        }*/
    }

    private static boolean loginUsuario() throws Exception {
        String[] aux = leerUserPassword();
        if(instanciasUsuario.containsKey(aux[0])){
            u = instanciasUsuario.get(aux[0]);
            if(u.getPassword().equals(aux[1])) return true;
            else {
                std1.writeln("Mala contraseña");
                return false;
            }
        }
        else {
            std1.writeln("No existe usuario.");
            return false;
        }
    }
    private static void nuevoUsuario() throws Exception {
        String[] aux = leerUserPassword();
        if(instanciasUsuario.containsKey(aux[0])){
            std1.writeln("Ya existe el usuario");
        }
        else{
            u = new Usuario(aux[0], std.writeStringRead("Introduzca su apodo:"), aux[1]);
            instanciasUsuario.put(aux[0], u);
        }
    }
    private static void logOut() throws Exception {
        std1.writeln("Saliendo del usuario");
    }
    private static int getOperacion() throws Exception {
        std1.writeln(u.toString());
        std1.writeln("Opciones posibles:");
        std.printStringArray(opciones);
        std1.writeln("-1. logOut");
        std1.writeln("Introduzca un numero");
        return std1.readint();
    }

    private static String[] leerUserPassword() {
        String[] ret = new String[2];
        ret[0] = std.writeStringRead("Introduzca su usuario:");
        ret[1] = std.writeStringRead("Introduzca su contraseña:");
        return ret;
    }
}



