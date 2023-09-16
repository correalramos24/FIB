package Dominio;

import Persistencia.BD;
import Persistencia.CtrlPersistencia;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * Controlador de las instancias usuarios de dominio.
 * @author victor.correal
 */
public class xCtrlDominioUsuario {

    private TreeMap<String, Usuario> instanciasUsuario;
    private BD BDusuarios;
    private Usuario u;
    private Usuario usuarioActual;

    public static void main(String[] args) throws Exception {
        xCtrlDominioUsuario cd;
        cd = new xCtrlDominioUsuario();
        cd.iniciarControlador();
        try { cd.altaUsuario("admin", "admin", "1234"); } catch (Exception e){}
        try { cd.altaUsuario("usr", "bic", "1234"); } catch (Exception e){}
        System.out.println(cd.getIdUsuarios());
    }

    public xCtrlDominioUsuario() {
        instanciasUsuario = new TreeMap();
        BDusuarios = CtrlPersistencia.getInstance().getCpUsuario();
        this.u = new Usuario();
    }

    public void iniciarControlador(){
        Set<String> allIdEntidades = BDusuarios.getAllEntidades();
        System.out.println("CAPA DOMINIO: iniciando controlador Usuarios...");
        System.out.println(allIdEntidades);
        for(String id : allIdEntidades){
            System.out.println("CAPA DOMINIO: cargando " + id);
            final byte[] obj = BDusuarios.getObjeto(id);
            u = construir(obj);
            instanciasUsuario.put(u.getNombre(), u.miClone());
        }
        usuarioActual = null;
    }

    //FUNCIONES DEL CONTROLADOR
    public void altaUsuario(String usr, String apodo, String psw) throws Exception{
        u = new Usuario(usr, apodo, psw);
        ByteArrayOutputStream dec = deconstruir(u);
        BDusuarios.guardarNuevoRecord(u.getNombre(),dec.toByteArray());
        instanciasUsuario.put(u.getNombre(), u.miClone());
        return;
    }
    
    public void editUsuario(String usr, String newApodo, String newPassword) throws Exception{
        if(!instanciasUsuario.containsKey(usr)) throw new Exception("No existe usuario" + usr);
        else{
            u = instanciasUsuario.get(usr);
            if(newApodo != null) u.setApodo(newApodo);
            if(newPassword != null) u.setPassword(newPassword);
        }
    }
    
    public void delUsuario(String usr) throws Exception{
        comprobarIntegridad(usr);
        u = null;
        instanciasUsuario.remove(usr);
    }

    public boolean logInUsuario(String usr, char[] psw)  {
        try{ comprobarIntegridad(usr); }catch (Exception e){return false;}
        boolean b = Arrays.equals(u.getPassword().toCharArray(), psw);
        if(b) usuarioActual = u;
        return b;
    }
    public void entrarConUsuario(){
        u = usuarioActual = instanciasUsuario.get("admin");
    }
    public List <String> getProblemasUsuario(String usr) throws Exception {
        if(!instanciasUsuario.containsKey(usr)) throw new Exception("No existe usuario" + usr);
        else return u.getProblemasUsuario();
    }

    public Set <String> getIdUsuarios() throws Exception{
        return instanciasUsuario.keySet();
    }
    
    //FUNCIONES INTERNAS

    private void comprobarIntegridad(String usr) throws Exception {
        if(!instanciasUsuario.containsKey(usr)) throw new Exception("No existe usuario" + usr);
        u = instanciasUsuario.get(usr);
    }

    private Usuario construir(byte[] bytes){
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Usuario) ois.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private ByteArrayOutputStream deconstruir(Usuario u){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedOutputStream bs2 = new BufferedOutputStream(baos);
            ObjectOutputStream oos = new ObjectOutputStream(bs2);
            oos.writeObject(u);
            oos.close();
            baos.close();
            return baos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Jugador getUsuario(String jugador1) throws Exception {
        comprobarIntegridad(jugador1);
        return u;
    }

    public String getUsuarioActual() {
        return usuarioActual.getApodo();
    }

    public Jugador getInstanciaUsuarioActual() {

        return usuarioActual;
    }
}
