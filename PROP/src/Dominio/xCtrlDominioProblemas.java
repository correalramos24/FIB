package Dominio;

import Persistencia.BD;
import Persistencia.CtrlPersistencia;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Victor Correal Ramos
 */
public class xCtrlDominioProblemas {

    public static final String BD_PROBLEMAS = "bdProblemas";
    private BD PersistenciaBD;
    private BdProblemas instancia;

    public xCtrlDominioProblemas(){
        PersistenciaBD = CtrlPersistencia.getInstance().getCpProblemas();
        this.instancia = BdProblemas.getInstance();
    }

    public static void main(String[] args) throws Exception {
        xCtrlDominioProblemas cdP = new xCtrlDominioProblemas();
        cdP.iniciarControlador();
        cdP.altaProblema("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5 w - - 0 1", 3, "mate en 3 por mi", 5);
        cdP.altaProblema("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5 w - - 0 1", 3, "mate en X por mi", 5);
        cdP.altaProblema("5B1b/1p2rR2/8/1B4N1/K2kP3/5n1R/1N6/2Q5 w - - 0 1", 3, "enga ahi", 5);
        System.out.println(cdP.getIdProblemas());
    }


    public void iniciarControlador() throws Exception {
        System.out.println("CAPA DOMINIO: Cargando los datos de los problemas del sistema");
        final byte[] obj = PersistenciaBD.getObjeto(BD_PROBLEMAS);
        if (obj != null) instancia = construir(obj); //restaura el ultimo estado de la bd de problemas.
        else{
            System.out.println("NO se ha podido cargar, iniciando una nueva base de datos de problemas ...");
            instancia = BdProblemas.getInstance();
            ByteArrayOutputStream dec = deconstruir(instancia);
            PersistenciaBD.guardarNuevoRecord(BD_PROBLEMAS, dec.toByteArray());
        }
    }

    //FUNCIONES DEL CONTROLADOR
    public Set <String> getIdProblemas(){
        return instancia.getAllProblemas();
    }

    public void altaProblema(String fen, int n, String tema, int dif)throws Exception{
        Problema p = new Problema(fen, n, tema, dif);
        instancia.addProblema(p);
        actualizarDatos();
    }
    public void editDataProblema(String fen, Integer n, String tema, Integer dif) throws Exception {
        if(!instancia.existeProblema(tema)) throw new Exception("No existe problema con tema " + tema);
        Problema pA = instancia.getProblema(tema);
        if(n != null) pA.setnJugadas(n);
        if(dif != null) pA.setDificultad(dif);
        actualizarDatos();
    }
    public void delProblema(String tema) throws Exception {
        if(! instancia.existeProblema(tema)) throw new Exception("No existe problema con tema "+tema);
        else instancia.delProblema(tema);
    }
    public String[] getProblemaMetaData(String id)throws Exception{
        if(! instancia.existeProblema(id)) throw new Exception("No existe problema con tema "+id);
        return instancia.getDataProblema(id);
    }
    public char[][] getProblemaSituacionInidical(String id) throws Exception {
        if(! instancia.existeProblema(id)) throw new Exception("No existe problema con tema "+id);
        return instancia.getTableroInicial(id);
    }
    public boolean comprobarFEN(String fen, int n) throws Exception {
        Problema p;
        try {
            p = new Problema(fen, n);
            return p.comprobarSolucionProblema();
        } catch (Exception e) {
            throw e;
        }
    }

    //FUNCIONES INTERNAS
    private void actualizarDatos() throws Exception {
        PersistenciaBD.eliminarObjeto(BD_PROBLEMAS);
        ByteArrayOutputStream dec = deconstruir(instancia);
        PersistenciaBD.guardarNuevoRecord(BD_PROBLEMAS, dec.toByteArray());
    }
    private BdProblemas construir(byte[] bytes){
    try{
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (BdProblemas) ois.readObject();
    } catch(IOException | ClassNotFoundException e) { e.printStackTrace(); }
        return null;
    }
    private ByteArrayOutputStream deconstruir(BdProblemas b){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bs2 = new BufferedOutputStream(baos);
            ObjectOutputStream oos = new ObjectOutputStream(bs2);
            oos.writeObject(b);
            oos.close();
            baos.close();
            return baos;
        } catch (IOException e) { e.printStackTrace();}
        return null;
    }

    public Set <String> getRanking(String idProblema) {
        return instancia.getProblema(idProblema).getRanking().getTopRanking();
    }
    public int getRankingUsuario(String idProblema, String usr){
        try {
            return instancia.getProblema(idProblema).getRanking().getPosicionRanking(usr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    protected Problema getProblema(String idProblema) throws Exception {
        if(! instancia.existeProblema(idProblema)) throw new Exception("No existe problema con tema "+idProblema);
        return instancia.getProblema(idProblema);
    }

    void addRanking(String tema, Resultado r) {
        System.out.println(r);
        instancia.getProblema(tema).addResultadoRanking(r);
        try {
            actualizarDatos();
        } catch (Exception ex) {
            Logger.getLogger(xCtrlDominioProblemas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
