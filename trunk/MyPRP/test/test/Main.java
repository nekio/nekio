package test;

import nekio.myprp.sistema.acceso.Inicializacion;

/**
 *
 * @author Nekio
 */
public class Main {
    public static void main(String[] args) {
        Inicializacion inicio = new Inicializacion("usuario_02","pssword_02");
        inicio.loggear();
        /*String entidad = Globales.Entidad.Imagen.name();
        
        GestorImagen gestor = new GestorImagen();
        gestor.ejecutarControladorNegocio(Globales.BD.LEER_DESC.getValor(), entidad);
        gestor = null;*/
        
        //System.out.println(inicio.loggear());
//        try{
//            new GeneradorPseudotablas(BDConexion.getConnection(), Globales.BD_ESQUEMA, false, "C:\\Users\\SITI\\Documents\\NetBeansProjects\\otros\\luis\\OBD_VW\\Tablas").generarTablas();
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
}
