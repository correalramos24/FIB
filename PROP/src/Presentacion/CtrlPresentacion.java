package Presentacion;

import Dominio.xCtrlDominioProblemas;
import Dominio.xCtrlDominioUsuario;
import Dominio.xCtrlDominioJoc;
import Persistencia.CtrlPersistencia;


public class CtrlPresentacion {

    private xCtrlDominioJoc dJoc;
    private xCtrlDominioUsuario dUsr;
    private xCtrlDominioProblemas dPrb;

    public static void main(String[] args) throws Exception {
        CtrlPresentacion a = new CtrlPresentacion();
        a.initPresentacion();
    }
    public CtrlPresentacion(){
        //dJoc = xCtrlDominioJoc
        dUsr = new xCtrlDominioUsuario();
        dPrb = new xCtrlDominioProblemas();
    }

    public void initPresentacion() throws Exception {
        dUsr.iniciarControlador();
        dPrb.iniciarControlador();
        LogInScreen a = new LogInScreen(dUsr);
        a.createAndShowUI();
    }


}
