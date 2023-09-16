package Presentacion;

import Dominio.Usuario;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
//import javax.swing.SwingConstants;


public class ChessPanel extends JPanel implements MouseListener {

    //Font font     = new Font("DejaVu Sans", Font.PLAIN, 24);
    Color bgLight = new Color(222, 184, 135);
    Color bgDark  = new Color(139, 69, 19);
    Color selected = new Color(255,255,0);
    
    private int fila;
    private int columna;
    private char c;
    private Usuario u;
    private Color miColor;
    
    public ChessPanel(){}

    public ChessPanel(LayoutManager layout) {
        setLayout(layout);
    }
    
    public char getCharPieza(){
        return c;
    }
    
    public void setCharPieza(char c){
        this.c = c;
    }
    
    void set(int idx, int row)
    {
        //setFont(font);
        setOpaque(true);
        setBackground((idx+row)%2 == 0 ? bgLight : bgDark);
        miColor = getBackground();
    }

    public void setUsuario(Usuario u) {
        this.u = u;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        u.asignarAuxiliaresMove(fila,columna);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getFila() {
        return fila;
    }

    public void setFila(int f) {
        this.fila = f;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int c) {
        this.columna = c;
    }
}
