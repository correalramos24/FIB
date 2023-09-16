package Presentacion;

import Dominio.Usuario;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.Character.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Taulell extends JFrame implements MouseListener{

    public static String Black = "Black";
    public static String White = "White";
    private Usuario u;

    //Si hay problemas con las imagenes el main este te dice donde te encuentras
    //para saber que error hay en la direccion relativa ^^
    public static void main(String args[]) {
        System.out.println(new File("").getAbsolutePath());

        Taulell t = new Taulell();
        t.display();
        for(int i = 0; i < 64; i++)
            t.getPanel()[i].addMouseListener(t.getPanel()[i]);

        while(true) {

        }
    }
    
    void asignarPeon(JPanel jp, String color){
        try{
            File f = new File("../../Desktop/prop/src/Presentacion/Recursos/Iconos/Piezas/" + color + "/Pawn.png");

            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label); 
        }
        catch (Exception ex){
            
        }
    }
    
    void asignarCaballo(JPanel jp, String color){
        try{
            File f = new File("../../Desktop/prop/src/Presentacion/Recursos/Iconos/Piezas/" + color + "/Knight.png");

            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label); 
        }
        catch (Exception ex){
            
        }
    }
    
    void asignarTorre(JPanel jp, String color){
        try{
            File f = new File("../../Desktop/prop/src/Presentacion/Recursos/Iconos/Piezas/" + color + "/Rook.png");

            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label); 
        }
        catch (Exception ex){
            
        }
    }
    
    void asignarAlfil(JPanel jp, String color){
        try{
            File f = new File("../../Desktop/prop/src/Presentacion/Recursos/Iconos/Piezas/" + color + "/Bishop.png");

            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label); 
        }
        catch (Exception ex){
            
        }
    }
    
    void asignarReina(JPanel jp, String color){
        try{
            File f = new File("../../Desktop/prop/src/Presentacion/Recursos/Iconos/Piezas/" + color + "/Queen.png");

            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label); 
        }
        catch (Exception ex){
            
        }
    }
    
    void asignarRey(JPanel jp, String color){
        try{
            File f = new File("../../Desktop/prop/src/Presentacion/Recursos/Iconos/Piezas/" + color + "/King.png");

            BufferedImage image = ImageIO.read(f);
            JLabel label = new JLabel(new ImageIcon(image.getScaledInstance(64,64,1)));
            jp.add(label); 
        }
        catch (Exception ex){
            
        }
    }

   //Initialise arrays to hold panels and images of the board
    public ChessPanel[] getPanel() {
        return panels;
    }

    private ChessPanel[] panels = new ChessPanel[] {

    // white
    new ChessPanel(), new ChessPanel(), new ChessPanel(),
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), 
    // empty
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), 
    // black
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(), 
    new ChessPanel(), new ChessPanel(), new ChessPanel(),  
    new ChessPanel()
    };

    public Taulell() 
    {

    } // Board()

    public void display()
    {
        setTitle("Chess board with unicode images");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(8, 8);

        contentPane.setLayout(gridLayout);

        asignarTorre(panels[0], Black);
        asignarTorre(panels[7], Black);
        
        asignarCaballo(panels[1], Black);
        asignarCaballo(panels[6], Black);
        
        asignarAlfil(panels[2], Black);
        asignarAlfil(panels[5], Black);
        
        asignarReina(panels[3], Black);
        
        asignarRey(panels[4], Black);
        
        
        asignarTorre(panels[56], White);
        asignarTorre(panels[63], White);
        
        asignarCaballo(panels[57], White);
        asignarCaballo(panels[62], White);
        
        asignarAlfil(panels[58], White);
        asignarAlfil(panels[61], White);
        
        asignarReina(panels[59], White);
        
        asignarRey(panels[60], White);
        
        int row = -1;
        for (int i = 0; i < panels.length; i++) 
        {
            if(i % 8 == 0) row ++; // increment row number
            panels[i].setColumna(i%8);
            panels[i].setFila(i/8);
            panels[i].set(i, row);
            if (i > 7 && i < 16) asignarPeon(panels[i], Black);
            if (i > 47 && i < 56) asignarPeon(panels[i], White);
            //panels[i].addMouseListener(new mouseListener());
            contentPane.add(panels[i]);
        } // i

        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
     } // display()
    
    private void asignarPiezaStd(JPanel jp,char c) {
        String color;
        if(c < 'a') color = White;
        else color = Black;
        c = toLowerCase(c);
        switch(c) {
            case('p'): 
                asignarPeon(jp,color);
                break;
            case('b'):
                asignarAlfil(jp,color);
                break;
            case('r'):
                asignarTorre(jp,color);
                break;
            case('n'):
                asignarCaballo(jp,color);
                break;
            case('q'):
                asignarReina(jp,color);
                break;
            case('k'):
                asignarRey(jp,color);
                break;
        }
    }

    
    public void displayMod(char[][] tablero){
        //setTitle("Chess board with unicode images");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container contentPane = getContentPane();
        GridLayout gridLayout = new GridLayout(8, 8);
        contentPane.setLayout(gridLayout);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                int k = i*8+j;
                panels[k].removeAll();
                panels[k].setUsuario(u);
                panels[k].addMouseListener(panels[k]);
                panels[k].set(k, i);
                panels[k].setFila(i);
                panels[k].setColumna(j);
                asignarPiezaStd(panels[k],tablero[i][j]);
                contentPane.add(panels[k]);
            }
        }
        revalidate();
        repaint();
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        //actualizarPantalla();
    } // display()

    public void actualizarPantalla() {
        setVisible(false);
        setVisible(true);
    }
    
    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println(me.getID());

    }

    public void setUsuario(Usuario u) {
        this.u = u;
    }
} // class Board