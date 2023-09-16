package Presentacion;

import Dominio.xCtrlDominioJoc;

import javax.swing.*;
import java.awt.*;

public class xCtrlPresentacionJoc {

    
    private xCtrlDominioJoc CDJ;

    private DragLabelOnLayeredPane pantallaJuego; //pantalla de juego

    private boolean esFinalPartida;
    private boolean mueveHumano;
    private estadisticasPartida statsP;


    public static void main(String[] args) throws Exception {
        xCtrlDominioJoc aux = new xCtrlDominioJoc("jeje", xCtrlDominioJoc.INVITADO, xCtrlDominioJoc.INVITADO, null);
        xCtrlPresentacionJoc a = new xCtrlPresentacionJoc(aux);
        JFrame j = new JFrame("a");
        //j.add(a);
        j.pack();
        j.setVisible(true);
        a.jugar();
    }

    public xCtrlPresentacionJoc(){}

    public xCtrlPresentacionJoc(xCtrlDominioJoc f) {
/*
        this.CDJ = f;
        pantallaJuego = new DragLabelOnLayeredPane(null);
        esFinalPartida = false;
        mueveHumano = false;
        MyThreadFinal t = new MyThreadFinal();
        t.start();
        JFrame frame = new JFrame("aa");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this.pantallaJuego);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/
        
    }
    
    public boolean isMueveHumano() {
        return mueveHumano;
    }
    public boolean isEsFinalPartida() {
        return esFinalPartida;
    }
    
    public void jugar() {
        //this.CDJ.iniciarJuego();
        //char[][] tablero = CDJ.getTablero();
        //pantallaJuego.pintarTablero(tablero);

    }
    //callback desde la pantalla
    public boolean moverDisplay(int xi, int yi, int r, int c){
        System.out.println("PRESENTACION: conato de movimiento usuario en el tablero");
        try {
            //if(CDJ.juegaIA()) return false;
            CDJ.mover(xi, yi, r,c);
            pantallaJuego.pintarTablero(CDJ.getTablero());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ups, mal movimiento de usuario");
        }
        return true;
    }

    public DragLabelOnLayeredPane getPantallaJuego() {
        return pantallaJuego;
    }
    
    public xCtrlDominioJoc getCDJ(){
        return this.CDJ;
    }

    /*private class MyThreadFinal extends Thread{
        public void run(){
            statsP = new estadisticasPartida();
            System.out.println("Thread propiedad de Victor, entrando...1");
            while (true) {
                statsP.getJugadorActual().setText(CDJ.nombreActual());
                statsP.getTiempoAtacante().setText("Tiempo de " + CDJ.nombreAtacante());
                statsP.getTiempoDefensor().setText("Tiempo de " + CDJ.nombreDefensor());
                statsP.getTiempoNumeroAtac().setText(CDJ.getTimer().getMinutos1()+":"+CDJ.getTimer().getSegundos1());
                statsP.getTiempoNumeroDefend().setText(CDJ.getTimer().getMinutos2()+":"+CDJ.getTimer().getSegundos2());
                statsP.setVisible(true);
                esFinalPartida = CDJ.esFinalPartida();
                if(esFinalPartida) {
                    System.out.println("Saliendo del thread, final partida");
                    return;
                }
                if(CDJ.juegaIA() && !esFinalPartida) {
                    CDJ.moverIA();
                    try {Thread.sleep(1000);} catch (InterruptedException e) {}
                    pantallaJuego.pintarTablero(CDJ.getTablero());
                }
                try { Thread.sleep(50); } catch (InterruptedException e) { }
            }
        }

    }*/

}
