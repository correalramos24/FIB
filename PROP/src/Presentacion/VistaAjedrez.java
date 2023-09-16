package Presentacion;

import Dominio.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static Presentacion.Taulell.Black;
import static Presentacion.Taulell.White;
import static java.lang.Character.toLowerCase;

public class VistaAjedrez extends JLayeredPane {

    public static final int HEIGHT = 590;
    public static final int WIDTH = 590;
    private static final int GRID_ROWS = 8;
    private static final int GRID_COLS = 8;
    private static final int GAP = 3;
    private static final Dimension LAYERED_PANE_SIZE = new Dimension(WIDTH, HEIGHT);

    private GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP);
    private JPanel backingPanel = new JPanel(gridlayout);
    private ChessPanel[][] panelGrid = new ChessPanel[GRID_ROWS][GRID_COLS];

    public VistaAjedrez(char[][] tablero) {
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setLocation(2 * GAP, 2 * GAP);
        backingPanel.setBackground(Color.black);
        if(tablero != null)pintarTablero(tablero);
        backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
    }

    protected void pintarTablero(char[][] tab) {
        backingPanel.removeAll();
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                panelGrid[row][col] = new ChessPanel();
                backingPanel.add(panelGrid[row][col]);
            }
        }
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                String color;
                char c = tab[i][j];
                if(c < 'a') color = White;
                else color = Black;
                c = toLowerCase(c);
                panelGrid[i][j].setColumna(8-j);
                panelGrid[i][j].setFila(i/8);
                panelGrid[i][j].set(j, i);
                switch(c) {
                    case('p'):
                        asignarPeon(panelGrid[i][j],color);
                        break;
                    case('b'):
                        asignarAlfil(panelGrid[i][j],color);
                        break;
                    case('r'):
                        asignarTorre(panelGrid[i][j],color);
                        break;
                    case('n'):
                        asignarCaballo(panelGrid[i][j],color);
                        break;
                    case('q'):
                        asignarReina(panelGrid[i][j],color);
                        break;
                    case('k'):
                        asignarRey(panelGrid[i][j],color);
                        break;
                }
            }
        }
        revalidate();
        repaint();
    }

    public static void Visual() throws Exception {
        xCtrlDominioProblemas cdp = new xCtrlDominioProblemas();
        cdp.iniciarControlador();
        VistaAjedrez taulellIntern = new VistaAjedrez(cdp.getProblemaSituacionInidical("yo"));
        JFrame taulell = new JFrame();
        taulell.getContentPane().add(taulellIntern);
        taulell.setSize(625, 625);
        taulell.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        taulell.setLocationRelativeTo(null);
        taulell.setVisible(true);
        taulell.pack();
        taulell.setSize(new Dimension(650,650));

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { Visual(); } catch (Exception e) { }
            }
        });
    }

    //==============PRIVATE METHODS==============
    protected class MyMouseAdapter extends MouseAdapter {

        private JLabel dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private JPanel clickedPanel = null;
        private Celda celdaInicial;
        private Celda celdaFinal;

        @Override
        public void mousePressed(MouseEvent me) {

            clickedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            Component[] components = clickedPanel.getComponents();
            if (components.length == 0) {
                return;
            }
            // if we click on jpanel that holds a jlabel
            if (components[0] instanceof JLabel) {
                dragLabel = (JLabel) components[0];
                clickedPanel.remove(dragLabel);
                clickedPanel.revalidate();
                clickedPanel.repaint();

                int r = -1;
                int c = -1;
                searchPanelGrid:
                for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == clickedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    // if off the grid, return label to home
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                } else {
                    //celdaInicial = t.getTablero()[r][c];
                    System.out.println(celdaInicial.toString());
                }

                dragLabelWidthDiv2 = dragLabel.getWidth() / 2;
                dragLabelHeightDiv2 = dragLabel.getHeight() / 2;

                int x = me.getPoint().x - dragLabelWidthDiv2;
                int y = me.getPoint().y - dragLabelHeightDiv2;
                dragLabel.setLocation(x, y);
                add(dragLabel, JLayeredPane.DRAG_LAYER);
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            int x = me.getPoint().x - dragLabelWidthDiv2;
            int y = me.getPoint().y - dragLabelHeightDiv2;
            dragLabel.setLocation(x, y);
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            if (dragLabel == null) {
                return;
            }
            remove(dragLabel); // remove dragLabel for drag layer of JLayeredPane
            JPanel droppedPanel = (JPanel) backingPanel.getComponentAt(me.getPoint());
            if (droppedPanel == null) {
                // if off the grid, return label to home
                clickedPanel.add(dragLabel);
                clickedPanel.revalidate();
            } else {
                int r = -1;
                int c = -1;
                searchPanelGrid:
                for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == droppedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    // if off the grid, return label to home
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                } else {

                    /*celdaFinal = t.getTablero()[r][c];
                    System.out.println(celdaFinal.toString());

                    java.util.List<Celda> lc = celdaInicial.getPieza().JugadasPosibles(t);
                    for (Celda cld : lc){
                        System.out.println("[" + cld.getFila() + "]" + "[" + cld.getColumnaInt() + "]");
                    }

                    if (moverDisplay(lc, celdaFinal) && actual.getColor().equals(celdaInicial.getColorCelda())){
                        Component[] components = droppedPanel.getComponents();
                        if (components.length == 0) {
                            droppedPanel.add(dragLabel);
                            droppedPanel.revalidate();
                        }
                        // if we click on jpanel that holds a jlabel
                        else if (components[0] instanceof JLabel) {
                            // remove label from panel
                            JLabel jl = (JLabel) components[0];
                            droppedPanel.remove(jl);
                            droppedPanel.add(dragLabel);
                            droppedPanel.revalidate();
                        }
                        t.mover(new Movimiento(celdaInicial, celdaFinal));
                        if (t.haGanado(actual.getColor())) {
                            JOptionPane.showMessageDialog(backingPanel, actual.getApodo() + " ha hecho jaque mate, ¡Enhorabuena!");

                        }
                        actual = (actual == defensor) ? atacante : defensor;
                    }
                    else{
                        clickedPanel.add(dragLabel);
                        clickedPanel.revalidate();
                    }
                }
            }
            repaint();
            dragLabel = null;

            if (actual instanceof IASimple || actual instanceof IASuperior){
                moverIA();
            }
            */
                }
            }
        }
    }

    private void asignarPeon(ChessPanel jp, String color){
        try{
            String s = File.separator;
            //File f = new File(".." + separator + ".." + separator + "Desktop" + separator + "prop" + separator + "src" + separator + "Presentacion" + separator + "Recursos" + separator + "Iconos" + separator + "Piezas" + separator + color + separator + "NandoPawn.png");
            File f = new File("." + s + "src" + s + "Presentacion" + s + "Recursos" + s + "Iconos" + s + "Piezas" + s + color + s + "Pawn.png");
            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label);
            char c = color == White ? 'P' : 'p';
            jp.setCharPieza(c);
        }
        catch (Exception ex){

        }
    }

    private void asignarCaballo(ChessPanel jp, String color){
        try{
            String s = File.separator;
            File f = new File("." + s + "src" + s + "Presentacion" + s + "Recursos" + s + "Iconos" + s + "Piezas" + s + color + s + "Knight.png");
            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label);
            char c = color == White ? 'N' : 'n';
            jp.setCharPieza(c);
        }
        catch (Exception ex){

        }
    }

    private void asignarTorre(ChessPanel jp, String color){
        try{
            String separator = File.separator;
            File f = new File("." + separator + "src" + separator + "Presentacion" + separator + "Recursos" + separator + "Iconos" + separator + "Piezas" + separator + color + separator + "Rook.png");
            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label);
            char c = color == White ? 'R' : 'r';
            jp.setCharPieza(c);
        }
        catch (Exception ex){

        }
    }

    private void asignarAlfil(ChessPanel jp, String color){
        try{
            String separator = File.separator;
            File f = new File("." + separator + "src" + separator + "Presentacion" + separator + "Recursos" + separator + "Iconos" + separator + "Piezas" + separator + color + separator + "Bishop.png");
            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label);
            char c = color == White ? 'B' : 'b';
            jp.setCharPieza(c);
        }
        catch (Exception ex){

        }
    }

    private void asignarReina(ChessPanel jp, String color){
        try{
            String separator = File.separator;
            File f = new File("." + separator + "src" + separator + "Presentacion" + separator + "Recursos" + separator + "Iconos" + separator + "Piezas" + separator + color + separator + "Queen.png");
            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label);
            char c = color == White ? 'Q' : 'q';
            jp.setCharPieza(c);
        }
        catch (Exception ex){

        }
    }

    private void asignarRey(ChessPanel jp, String color){
        try{
            String separator = File.separator;
            File f = new File("." + separator + "src" + separator + "Presentacion" + separator + "Recursos" + separator + "Iconos" + separator + "Piezas" + separator + color + separator + "King.png");
            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label);
            char c = color == White ? 'K' : 'k';
            jp.setCharPieza(c);
        }
        catch (Exception ex){

        }
    }

    private boolean esMovimientoLegal(java.util.List<Celda> lc, Celda c){
        for (Celda cld : lc){
            if (cld.getFila() == c.getFila() && cld.getColumnaInt() == c.getColumnaInt()) return true;
        }
        return false;
    }
}
