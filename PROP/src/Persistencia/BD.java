package Persistencia;

import java.io.*;
import java.nio.file.Files;
import java.util.Set;
import java.util.TreeMap;

/**
 * Es toda la informacion de la base de datos de un mismo tipo en formato de bytes
 *
 * @author Victor Correal
 */
public class BD {

    
    /** Conjunto de objetos en formato de bytes del mismo tipo */
    private TreeMap<String, File> Identificadores;
    /** Fichero donde esta la informacion */
    File ids;
    /** Ruta donde almacenar los datos y de donde obtenerlos */
    private String path = "";
    /** Numero total de objetos en Identificadores */
    private int incremental;
    /**
     * 
     */
    
    /**
     * Constructora de una base de datos
     * @param S Ruta donde crear el directorio de la base de datos
     * @param name Nombre del directorio a crear
     */
    public BD(String S, String name) throws IOException {
        System.out.println("CAPA PERSISTENCIA: cargando los datos internos de "+ name + " del sistema...");
        this.path = S+File.separator+name;
        Identificadores = new TreeMap<>();
        File f = new File(this.path);
        f.mkdirs();
        ids = new File(this.path+File.separator+"metadata.bin");
        if(!ids.exists()){
            System.out.println("CAPA PERSISTENCIA: creando nuevos datos internos...");
            ids.createNewFile();
            guardarMap();
            incremental = Identificadores.size();
        }
        else getMap();
    }

    /**
     * Guarda un nuevo objeto en el directorio de la base de datos
     * @param id Id del objeto a guardar
     * @param bytes Informacion a guardar en formato de bytes
     */
    public void guardarNuevoRecord(String id, byte[] bytes) throws Exception {
        if(existe(id)) throw new Exception("Ya existe la entidad");
        try {
            File f = new File(path+File.separator+id+".bin");
            f.createNewFile();
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bytes);
            fos.close();
            Identificadores.put(id, f);
            guardarMap();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Consultora de objetos
     * @param id Id del objeto a consultar
     * @return Objeto en formato de bytes
     */
    public byte[] getObjeto(String id){
        byte[] ret = null;
        try{
            final File obj = Identificadores.get(id);
            //FileInputStream fis = new FileInputStream(obj);
            ret = Files.readAllBytes(obj.toPath());
        } catch (Exception e){

        }
        return ret;
    }

    /**
     * Destructora de objetos
     * @param id Id del objeto a eliminar
     */
    public void eliminarObjeto(String id) throws Exception {
        if(!existe(id)) throw new Exception("No existe el objeto");
        final File f = Identificadores.get(id);
        f.delete();
        Identificadores.remove(id);
        guardarMap();
    }

    /**
     * Modificador de objetos
     * @param id Id del objeto a modificar
     * @param bytes Nueva informacion a guardar en formato de bytes
     */
    public void modificarRecord(String id, byte[] bytes){
        try {
            eliminarObjeto(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            guardarNuevoRecord(id, bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Consultora de existencia de un objeto
     * @param id Id del objeto a buscar
     * @return Booleano que indica la existencia del objeto
     */
    public boolean existe(String id) {
        return Identificadores.containsKey(id);
    }

    /**
     * Consultora del numero de objetos del mismo tipo
     * @return Cantidad de objetos
     */
    public int getIncremental(){return incremental;}

    /**
     * 
     */
    private void guardarMap(){
        try {
            FileOutputStream fos = new FileOutputStream(ids);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Identificadores);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga Identificadores con la informacion del fichero correspondiente
     */
    private void getMap(){
        try{
            FileInputStream fis = new FileInputStream(ids);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Identificadores =  (TreeMap<String, File>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getAllEntidades() {
        return Identificadores.keySet();
    }

}
