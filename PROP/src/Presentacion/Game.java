/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Dominio.xCtrlDominioJoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Marc
 */
public class Game extends javax.swing.JFrame {

    private Timer whiteTimer;
    private Timer blackTimer;

    private xCtrlDominioJoc controladorJuego;

    public xCtrlDominioJoc getControladorJuego() {
        return controladorJuego;
    }

    //private boolean esFinalPartida = false;
    
    private xCtrlDominioJoc CDJ;
    private String turno;
    public JFrame frameGame = this;

    public xCtrlDominioJoc getCDJ(){
        return this.controladorJuego;
    }

    /*public Game() throws Exception {
        initComponents();
        controladorJuego = new xCtrlDominioJoc("jeje", xCtrlDominioJoc.INVITADO, xCtrlDominioJoc.INVITADO, null);
        tableroArrastable = new DragLabelOnLayeredPane(this);

        lblWhitePlayer.setText(controladorJuego.getApodoJugadorBlanco());
        lblBlackPlayer.setText(controladorJuego.getApodoJugadorNegro());
        whiteTimer = new Timer(1000, new WhiteClockListner());
        blackTimer = new Timer(1000, new BlackClockListner());
        whiteTimer.start();
        blackTimer.start();
    }*/
    
    public Game(xCtrlDominioJoc CDJ) {
        //this.controladorJuego = CPJ;
        initComponents(CDJ);
        this.CDJ = CDJ;
        turno = CDJ.getApodoJugadorActual();
        //tableroArrastable = new DragLabelOnLayeredPane(CDJ);
        lblWhitePlayer.setText(CDJ.getApodoJugadorBlanco());
        lblBlackPlayer.setText(CDJ.getApodoJugadorNegro());
        whiteTimer = new Timer(1000, new WhiteClockListner());
        blackTimer = new Timer(1000, new BlackClockListner());
        whiteTimer.start();
        blackTimer.start();
        
        MyThread th = new MyThread();
        th.start();
    }

    /*
    public void empezarJuego(){
        controladorJuego.iniciarJuego();
        tableroArrastable.pintarTablero(controladorJuego.getTablero());
        //MyThread t = new MyThread();
        //t.run();
    }
    
    public boolean mover(int xi, int yi, int r, int c){
        System.out.println("GAME: MOVIENDO USUARIO");
        try {
            if(controladorJuego.juegaIA()) return false;
            controladorJuego.mover(xi, yi, r,c);
            tableroArrastable.pintarTablero(controladorJuego.getTablero());
        } catch (Exception e) {
            System.out.println("Error en el movimiento");
            return false;
        }
        return true;
    }*/
    
    private void initComponents(xCtrlDominioJoc CDJ) {

        lblBlackPlayer = new javax.swing.JLabel();
        lblBlackClock = new javax.swing.JLabel();
        lblWhitePlayer = new javax.swing.JLabel();
        lblWhiteClock = new javax.swing.JLabel();
        tableroArrastable = new DragLabelOnLayeredPane(CDJ);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblBlackPlayer.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblBlackPlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlackPlayer.setText("Black Player");

        lblBlackClock.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblBlackClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlackClock.setText("00:00");

        lblWhitePlayer.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblWhitePlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWhitePlayer.setText("White Player");

        lblWhiteClock.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblWhiteClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWhiteClock.setText("00:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(tableroArrastable, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lblWhitePlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(lblWhiteClock, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(33, 33, 33)))
                                                .addGap(97, 97, 97))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblBlackPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(96, 96, 96))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblBlackClock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(122, 122, 122))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(lblBlackPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBlackClock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblWhitePlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblWhiteClock, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(tableroArrastable, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    protected class WhiteClockListner implements ActionListener{
        
        private int whiteCounter = 0;
       
        @Override
        public void actionPerformed(ActionEvent e) {

            String minutes, seconds;
            String whiteClock = lblWhiteClock.getText();
            
            int minute, second;

            String[] time = whiteClock.split(":");
            second = Integer.parseInt(time[1]);
            minute = Integer.parseInt(time[0]);
            
            ++second;
            if (second >= 60){ ++minute; second = 0; }
            
            if (second < 10){ seconds = "0" + second; }
            else { seconds = String.valueOf(second); }
            
            if (minute < 10){ minutes = "0" + minute; }
            else { minutes = String.valueOf(minute); }
            
            lblWhiteClock.setText(minutes + ":" + seconds);
        }   
    }
    
    protected class BlackClockListner implements ActionListener{
      
        @Override
        public void actionPerformed(ActionEvent e) {

            String minutes, seconds;
            String blackClock = lblBlackClock.getText();
            
            int minute, second;
            
            String[] time = blackClock.split(":");
            second = Integer.parseInt(time[1]);
            minute = Integer.parseInt(time[0]);
            
            ++second;
            if (second >= 60){ ++minute; second = 0; }
            
            if (second < 10){ seconds = "0" + second; }
            else { seconds = String.valueOf(second); }
            
            if (minute < 10){ minutes = "0" + minute; }
            else { minutes = String.valueOf(minute); }
            
            lblBlackClock.setText(minutes + ":" + seconds);
        }   
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Game g = new Game(new xCtrlDominioJoc("mate en 0",
                            xCtrlDominioJoc.INVITADO,
                            xCtrlDominioJoc.IASIMPLE,
                            null));
                    g.setVisible(true);
                    g.empezarJuego();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class MyThread extends Thread{
        public void run() {
            System.out.println("Iniciando el thread de control del juego");
            while(true){

                esFinalPartida = controladorJuego.esFinalPartida();
                if(esFinalPartida){
                    System.out.println("final de partida");
                    return;
                }
                if(controladorJuego.juegaIA()){
                    System.out.println("mueve IA");
                    controladorJuego.moverIA();
                    try {Thread.sleep(100);} catch (InterruptedException e) {}
                    tableroArrastable.pintarTablero(controladorJuego.getTablero());
                }
                try { Thread.sleep(100); } catch (InterruptedException e) { }
            }
        }
    }*/
    
     private class MyThread extends Thread{
        public void run() {
            //System.out.println("Iniciando el thread de control del juego");
            while(true){

                if(CDJ.esFinalPartida()){
                    
                    whiteTimer.stop();
                    blackTimer.stop();
                    
                    System.out.println("Final de partida");
                    
                    double segundosAtacante;
                    String clockAtacante;
                    if (CDJ.getApodoAtacante().equals(CDJ.getApodoJugadorBlanco())){ clockAtacante = lblWhiteClock.getText(); }
                    else { clockAtacante = lblWhiteClock.getText(); }
                               
                    int minutes, seconds;
                    String[] time = clockAtacante.split(":");
                    seconds = Integer.parseInt(time[1]);
                    minutes = Integer.parseInt(time[0]);
                    segundosAtacante = minutes*60 + seconds;

                    boolean resultado = CDJ.haGanadoAtacante();                  
                    CDJ.finalizar(segundosAtacante, System.currentTimeMillis(), resultado);
                    
                    if (resultado){
                        JOptionPane.showMessageDialog(frameGame, CDJ.getApodoJugadorActual() + " ha hecho jaque mate, ¡Enhorabuena: " + CDJ.getApodoJugadorActual() + "!");  
                    }
                    else{
                        JOptionPane.showMessageDialog(frameGame, CDJ.getApodoDefensor() + " ha conseguido defenderse, ¡Enhorabuena: " + CDJ.getApodoDefensor() + "!");
                    }
                    frameGame.dispose();
                    /*CustomGameScreen CGS = new CustomGameScreen(null, null);
                    CGS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    CGS.pack();
                    CGS.setLocationRelativeTo(null);
                    CGS.setVisible(true);*/
                    return;
                }
                
                turno = CDJ.getApodoJugadorActual();
                
                if (turno.equals(CDJ.getApodoJugadorBlanco())){
                    whiteTimer.start();
                    blackTimer.stop();
                }
                
                turno = CDJ.getApodoJugadorActual();
                
                if (turno.equals(CDJ.getApodoJugadorNegro())){
                    blackTimer.start();
                    whiteTimer.stop();
                }
                
                if(CDJ.isActualIA()){
                    System.out.println("mueve IA");
                    try {
                        CDJ.moverIA();
                        Thread.sleep(100);
                    } 
                    catch (Exception ex) { ex.printStackTrace(); }
                    tableroArrastable.pintarTablero(CDJ.getTablero());
                }
                try { Thread.sleep(100); } catch (InterruptedException e) { }
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBlackClock;
    private javax.swing.JLabel lblBlackPlayer;
    private javax.swing.JLabel lblWhiteClock;
    private javax.swing.JLabel lblWhitePlayer;
    private DragLabelOnLayeredPane tableroArrastable;
    // End of variables declaration//GEN-END:variables
}
