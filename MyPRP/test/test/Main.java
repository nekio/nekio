package test;

import nekio.myprp.sistema.acceso.Inicializacion;

/**
 *
 * @author Nekio
 */
public class Main {
    public static void main(String[] args) {
        Inicializacion inicio = new Inicializacion("usuario_02","pssword_02");
        System.out.println(inicio.probarImagen());
        //System.out.println(inicio.loggear());
//        try{
//            new GeneradorPseudotablas(BDConexion.getConnection(), Globales.BD_ESQUEMA, false, "C:\\Users\\SITI\\Documents\\NetBeansProjects\\otros\\luis\\OBD_VW\\Tablas").generarTablas();
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
}
