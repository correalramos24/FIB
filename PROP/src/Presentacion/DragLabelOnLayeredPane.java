package Presentacion;

import Dominio.xCtrlDominioJoc;
import PiezasBlancas.cargaPiezasBlancas;
import PiezasNegras.cargaPiezasNegras;

import static Presentacion.Taulell.Black;
import static Presentacion.Taulell.White;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import static java.lang.Character.toLowerCase;



public class DragLabelOnLayeredPane extends JLayeredPane {
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static final int GRID_ROWS = 8;
    private static final int GRID_COLS = 8;
    private static final int GAP = 3;
    private static final Dimension LAYERED_PANE_SIZE = new Dimension(WIDTH, HEIGHT);

    private GridLayout gridlayout = new GridLayout(GRID_ROWS, GRID_COLS, GAP, GAP);
    private JPanel backingPanel = new JPanel(gridlayout);
    private ChessPanel[][] panelGrid = new ChessPanel[GRID_ROWS][GRID_COLS];

    private xCtrlDominioJoc CDJ;
    
    public DragLabelOnLayeredPane(){
        
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setLocation(2 * GAP, 2 * GAP);
        backingPanel.setBackground(Color.black);
        
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                panelGrid[row][col] = new ChessPanel();
                backingPanel.add(panelGrid[row][col]);
            }
        }

        char[][] tab = new char[8][8];
        
        tab[0][0] = 'r'; tab[0][1] = 'n'; tab[0][2] = 'b'; tab[0][3] = 'q'; tab[0][4] = 'k'; tab[0][5] = 'b'; tab[0][6] = 'n'; tab[0][7] = 'r';
        tab[1][0] = 'p'; tab[1][1] = 'p'; tab[1][2] = 'p'; tab[1][3] = 'p'; tab[1][4] = 'p'; tab[1][5] = 'p'; tab[1][6] = 'p'; tab[1][7] = 'p';
         
        tab[6][0] = 'P'; tab[6][1] = 'P'; tab[6][2] = 'P'; tab[6][3] = 'P'; tab[6][4] = 'P'; tab[6][5] = 'P'; tab[6][6] = 'P'; tab[6][7] = 'P';
        tab[7][0] = 'R'; tab[7][1] = 'N'; tab[7][2] = 'B'; tab[7][3] = 'Q'; tab[7][4] = 'K'; tab[7][5] = 'B'; tab[7][6] = 'N'; tab[7][7] = 'R';
       
        pintarTablero(tab);
        
        backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        //MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        //addMouseListener(myMouseAdapter);
        //addMouseMotionListener(myMouseAdapter);               
    }

    public DragLabelOnLayeredPane(xCtrlDominioJoc CDJ){
        
        this.CDJ = CDJ;
        backingPanel.setSize(LAYERED_PANE_SIZE);
        backingPanel.setLocation(2 * GAP, 2 * GAP);
        backingPanel.setBackground(Color.black);
        
        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                panelGrid[row][col] = new ChessPanel();
                backingPanel.add(panelGrid[row][col]);
            }
        }
       
        char[][] tab = CDJ.getTablero();
        
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
                
        backingPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setPreferredSize(LAYERED_PANE_SIZE);
        add(backingPanel, JLayeredPane.DEFAULT_LAYER);
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter(CDJ);
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);  
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
                    default:
                        //throw new Exception("Pieza no reconocida");
                        break;
                }
            }
        }
        revalidate();
        repaint();
    }

    protected class MyMouseAdapter extends MouseAdapter {
        
        private JLabel dragLabel = null;
        private int dragLabelWidthDiv2;
        private int dragLabelHeightDiv2;
        private ChessPanel clickedPanel = null;
        private int xi, yi;

        private xCtrlDominioJoc mouseCDJ;
        
        public MyMouseAdapter(){
            mouseCDJ = CDJ;
        }
        
        public MyMouseAdapter(xCtrlDominioJoc CDJ){
            mouseCDJ = CDJ;
        }
        
        @Override
        public void mousePressed(MouseEvent me) {
            try{clickedPanel = (ChessPanel) backingPanel.getComponentAt(me.getPoint());}
            catch(Exception e){return;}
            Component[] components = clickedPanel.getComponents();
            if (components.length == 0) {
                return;
            }

            if (components[0] instanceof JLabel) {

                dragLabel = (JLabel) components[0];
                clickedPanel.remove(dragLabel);
                clickedPanel.revalidate();
                clickedPanel.repaint();
                
                int r = -1;
                int c = -1;
                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == clickedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                }
                else {
                    xi = r; yi = c;
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
            //if(presentacioJoc.isEsFinalPartida()) return;
            if (dragLabel == null) { return; }
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
            remove(dragLabel);
            ChessPanel droppedPanel = (ChessPanel) backingPanel.getComponentAt(me.getPoint());
            if (droppedPanel == null) {
                clickedPanel.add(dragLabel);
                clickedPanel.revalidate();
            } else {
                int r = -1;
                int c = -1;
                searchPanelGrid: for (int row = 0; row < panelGrid.length; row++) {
                    for (int col = 0; col < panelGrid[row].length; col++) {
                        if (panelGrid[row][col] == droppedPanel) {
                            r = row;
                            c = col;
                            break searchPanelGrid;
                        }
                    }
                }

                if (r == -1 || c == -1) {
                    clickedPanel.add(dragLabel);
                    clickedPanel.revalidate();
                }
                else {
                                       
                    boolean legalMove = mouseCDJ.esMovimientoLegal(xi,yi, r, c);
                    boolean esSuColor = mouseCDJ.piezaEsDelColorDelJugador(xi,yi);
                    
                    if (legalMove && esSuColor){
                        Component[] components = droppedPanel.getComponents();
                        if (components.length == 0) {
                            droppedPanel.add(dragLabel);
                            droppedPanel.revalidate();
                        }

                        else if (components[0] instanceof JLabel) {
                            JLabel jl = (JLabel) components[0];
                            droppedPanel.remove(jl);
                            droppedPanel.add(dragLabel);
                            droppedPanel.revalidate();
                        }
                        
                        mouseCDJ.mover(xi,yi,r,c);
                        
                    }
                    else{
                        clickedPanel.add(dragLabel);
                        clickedPanel.revalidate();
                    }
                }
            }
            
            repaint();
            dragLabel = null;
        
        }
        
    }


    //=============FUNCIONES PRIVADAS==================
    private void asignarPeon(ChessPanel jp, String color){
        try {
            if (color.equals("White")) {
                cargaPiezasBlancas a = new cargaPiezasBlancas();
                BufferedImage image = ImageIO.read(a.getPawn());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64, 64, 1)));
                jp.add(label);
                char c = color == White ? 'P' : 'p';
                jp.setCharPieza(c);
            } else {
                cargaPiezasNegras a = new cargaPiezasNegras();
                BufferedImage image = ImageIO.read(a.getPawn());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64, 64, 1)));
                jp.add(label);
                char c = color == White ? 'P' : 'p';
                jp.setCharPieza(c);
            }
        } catch (Exception e){
            System.out.println("error en la carga de los peones :(");
        }
    }
    
    private void asignarCaballo(ChessPanel jp, String color){
        try{
            if (color.equals("White")){
                cargaPiezasBlancas pb = new cargaPiezasBlancas();
                BufferedImage image = ImageIO.read(pb.getKnight());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'P' : 'p';
                jp.setCharPieza(c);
            }
            else{
                cargaPiezasNegras a = new cargaPiezasNegras();
                BufferedImage image = ImageIO.read(a.getKnight());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'P' : 'p';
                jp.setCharPieza(c);
            }
        }
        catch (Exception ex){
            
        }
    }
    
    private void asignarTorre(ChessPanel jp, String color){
        try{
            if (color.equals("White")){
                cargaPiezasBlancas pb = new cargaPiezasBlancas();
                BufferedImage image = ImageIO.read(pb.getRook());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'R' : 'r';
                jp.setCharPieza(c);
            }
            else{
                cargaPiezasNegras pn = new cargaPiezasNegras();
                BufferedImage image = ImageIO.read(pn.getRook());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'R' : 'r';
                jp.setCharPieza(c);
            }
        }
        catch (Exception ex){
            
        }
    }
    
    private void asignarAlfil(ChessPanel jp, String color){
        try{
            if (color.equals("White")){
                cargaPiezasBlancas pb = new cargaPiezasBlancas();
                BufferedImage image = ImageIO.read(pb.getBishop());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'R' : 'r';
                jp.setCharPieza(c);
            }
            else{
                cargaPiezasNegras pn = new cargaPiezasNegras();
                BufferedImage image = ImageIO.read(pn.getBishop());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'R' : 'r';
                jp.setCharPieza(c);
            }
        }
        catch (Exception ex){
            
        }
    }
    
    private void asignarReina(ChessPanel jp, String color){
        try{
            if (color.equals("White")){
                cargaPiezasBlancas pb = new cargaPiezasBlancas();

                BufferedImage image = ImageIO.read(pb.getQueen());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'R' : 'r';
                jp.setCharPieza(c);
            }
            else{
                cargaPiezasNegras pn = new cargaPiezasNegras();
                BufferedImage image = ImageIO.read(pn.getQueen());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'R' : 'r';
                jp.setCharPieza(c);
            }
        }
        catch (Exception ex){
            
        }
    }
    
    private void asignarRey(ChessPanel jp, String color){
        try{
            if (color.equals("White")){
                cargaPiezasBlancas pb = new cargaPiezasBlancas();

                BufferedImage image = ImageIO.read(pb.getKing());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'R' : 'r';
                jp.setCharPieza(c);
            }
            else{
                cargaPiezasNegras pn = new cargaPiezasNegras();
                BufferedImage image = ImageIO.read(pn.getKing());
                JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
                jp.add(label);
                char c = color == White ? 'R' : 'r';
                jp.setCharPieza(c);
            }
        }
        catch (Exception ex){
            
        }
    }
    
}