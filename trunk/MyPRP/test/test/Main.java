package test;

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.sistema.acceso.Inicializacion;

/**
 *
 * @author Nekio
 */
public class Main {
    public static void main(String[] args) {     
        Globales.CONSOLA.setVisible(true);
        Inicializacion inicio = new Inicializacion(0, "admin","admin");
        inicio.loggear();
        
        /*String entidad = Globales.Entidad.Imagen.name();
        
        GestorImagen gestor = new GestorImagen();
        gestor.ejecutarControladorNegocio(Globales.BD.LEER_DESC.getValor(), entidad);
        gestor = null;*/
        
        //System.out.println(inicio.loggear());
//        try{
//            new GeneradorPseudotablas(BDConexion.getConnection(), Globales.BD_DESC_ESQUEMA, false, "C:\\Users\\SITI\\Documents\\NetBeansProjects\\otros\\luis\\OBD_VW\\Tablas").generarTablas();
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
}
