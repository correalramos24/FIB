package Presentacion;

import Dominio.xCtrlDominioProblemas;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class VistaProblema extends JFrame {

    private javax.swing.JButton jButton1; //portapapeles
    private javax.swing.JButton jButton2; //add problema
    private javax.swing.JButton jButton3; //Comprobar solucion FEN
    private javax.swing.JButton jButton4; //Ver tablero
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6; //error
    private javax.swing.JSpinner jSpinner1; //dificultad
    private javax.swing.JSpinner jSpinner2; //N
    private javax.swing.JTextField jTextField1; //tema
    private javax.swing.JTextField jTextField2; //fen

    private xCtrlDominioProblemas CtlrDomProb;

    public VistaProblema(xCtrlDominioProblemas cd) throws Exception {
        if (cd == null) {
            cd = new xCtrlDominioProblemas();
            cd.iniciarControlador();
        }
        this.CtlrDomProb = cd;
        initComponents();
    }

    //===========================HANDLERS===========================

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                String s = (String) t.getTransferData(DataFlavor.stringFlavor);
                jTextField2.setText(s);
            } catch (UnsupportedFlavorException | IOException e) {
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

        String tema = jTextField1.getText().trim();
        String FEN = jTextField2.getText();
        int dif = (int) jSpinner1.getValue();
        int n = (int) jSpinner2.getValue();

        try {
            CtlrDomProb.altaProblema(FEN, n, tema, dif);
            jTextField1.setText("");
            jTextField2.setText("");
            jSpinner1.setValue(0);
            jSpinner2.setValue(0);
            JOptionPane.showMessageDialog(null, "Problema " + tema + " creado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Comprobar solucion FEN

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        String fen = jTextField2.getText();
        int n = (int) jSpinner2.getValue();
        if (fen == "") {
            return;
        }
        if (n <= 0) {
            JOptionPane.showMessageDialog(null, "El numero de jugadas debe ser mayor que 0");
        }
        else{
            try {
                if (CtlrDomProb.comprobarFEN(fen, n)) {
                    JOptionPane.showMessageDialog(null, "Tiene solucion");
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene solucion :-(");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            String FEN = jTextField2.getText();
            char[][] cm = toCharMap(FEN);
            DragLabelOnLayeredPane DLOLP = new DragLabelOnLayeredPane();
            DLOLP.pintarTablero(cm);
            JFrame frame = new JFrame("Tablero FEN introducido");
            frame.getContentPane().add(DLOLP);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(630, 650);
            //frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    private char[][] toCharMap(String FEN) throws Exception{
        char[][] cm = new char[8][8];
        String[] filasTablero = FEN.split("/");

        if (filasTablero.length != 8) throw new Exception("Bad FEN format! El número de filas es distinto a ocho.");

        
        for (int i = 0; i < 8; ++i)
        {
            String filaFEN = filasTablero[i];
            int k = 0;
            for (int j = 0; j < filaFEN.length() && filaFEN.charAt(j) != ' '; ++j)
            {
                char aux = filaFEN.charAt(j);

                if (Character.isDigit(aux))
                {
                    int tope = k + Character.getNumericValue(aux);
                    while (k < tope)++k;
                }
                else
                {
                    if (k > 7) { throw new Exception("Bad FEN format! El número de columnas es distinto a ocho en la fila: " + i); }
                    cm[i][k] = aux;
                    ++k;
                }
            }
        }
        return cm;
    }

    //===========================HANDLERS===========================
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("VISTA PROBLEMA");

        jLabel2.setText("Dificultad");

        jLabel3.setText("Tema");

        jLabel4.setText("FEN");

        jButton1.setText("Portapapeles");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton2.setText("Añadir Problema");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jButton3.setText("Comprobar Solucion FEN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jLabel5.setText("Jugadas");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jButton3.setText("Comprobar Solucion FEN");

        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("Error Label");
        jLabel6.setVisible(false);

        jButton4.setText("Ver Tablero");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(77, 77, 77)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addGap(36, 36, 36)
                                                        .addComponent(jTextField1))
                                                .addComponent(jLabel4)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jButton2)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jButton3)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jButton4)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton1))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel2)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(18, 18, Short.MAX_VALUE)
                                                                        .addComponent(jLabel5)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(81, 81, 81)
                                        .addComponent(jLabel6)))
                        .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton1)
                                .addComponent(jButton3)
                                .addComponent(jButton4))
                        .addGap(35, 35, 35))
        );
        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VistaProblema(null).setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
