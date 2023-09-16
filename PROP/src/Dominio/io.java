package Dominio;

import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author victor.correal
 */
public class io {

    private static io instance;
    private Scanner keyboard;
    //==========SINGELTONE==========
    private io() {
        keyboard = new Scanner(System.in);
    }

    /**
     *
     * @return
     */
    public static io getInstance() {
        if(instance == null){
            instance = new io();
        }
        return instance;
    }
    //======PUBLIC METHODS READ======

    /**
     *
     * @return
     */
        public String readLine(){
        String ret;
        ret = keyboard.next();
        //ret = keyboard.nextLine();
        return ret;
    }

    /**
     *
     * @return
     */
    public int readInteger(){
        int ret = keyboard.nextInt();
        return ret;
    }

    /**
     *
     * @return
     */
    public char readChar(){
        char ret = (char) keyboard.next().charAt(0);
        return ret;
    }
    //======PUBLIC METHODS WRITE=====

    /**
     *
     * @param l
     */
        public void writeString(String l){
        System.out.println(l);
    }

    /**
     *
     * @param l
     * @return
     */
    public String writeStringRead(String l){
        System.out.println(l);
        return readLine();
    }

    /**
     *
     * @param s
     */
    public void printSet(Set<?> s){
        System.out.println("=======");
        for (Object o : s) System.out.println(o);
        System.out.println("=======");
    }

    /**
     *
     * @param c
     */
    public void printCharMatrix(char[][] c){
        for (char[] chars : c) {
            for (char aChar : chars) {
                System.out.println(aChar + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     *
     * @param opciones
     */
    public void printStringArray(String[] opciones) {
        for(int i = 0; i < opciones.length; ++i) {
            System.out.println(i +": "+ opciones[i]);
        }
	}

    /**
     *
     * @param opciones
     * @return
     */
    public int printStringArrayRead(String[] opciones){
        printStringArray(opciones);
        return readInteger();
    }
}
