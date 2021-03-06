package nekio.myprp.recursos.generador;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;

/**
 *
 * @author Nekio
 */
public class Main {
    private final String esquemaBD = Globales.BD_TOOLS;
    //private final String esquemaBD = Globales.BD_ESQUEMA_SERIES;
    
    public static void main(String[] args) {
        // Iniciar la consola
        Globales.CONSOLA.setVisible(true);
        
        // Hacer conexion a la BD
        Main m = new Main();
        m.conexion();
    }
    
    private void conexion(){
        BDConexion bd = new BDConexion(
                Globales.BD_GESTOR,
                Globales.BD_USUARIO,
                Globales.BD_PASSWORD,
                Globales.BD_HOST,
                Globales.BD_PUERTO,
                esquemaBD,
                Globales.BD_MAX_ACTIVOS,
                Globales.BD_MAX_IDLE
        );
        
        // Obtener todos los detalles de todas las tablas de la BD
        List<String> tablasBD = BDConexion.obtenerTablas(esquemaBD, Globales.BD_USUARIO);
        
        List<List> detallesTablasBD = new ArrayList<List>();
        List<List> detallesTablaBD = null;
        for(String tabla : tablasBD){
            detallesTablaBD = BDConexion.obtenerDetalles(esquemaBD, Globales.BD_USUARIO, tabla);
            detallesTablasBD.add(detallesTablaBD);
        }
        
        ConsolaDebug.agregarTexto();
        ConsolaDebug.agregarTexto(
                Globales.APP_SEPARADOR + "\n" +
                Globales.APP_SEPARADOR + "\n" +
                "Lectura de metadatos terminada", Color.LIGHT_GRAY
        );  
        
        // Realizar las generaciones de codigos
        ConsolaDebug.agregarTexto();
        new GeneradorGUI(new ControladorGenerador(tablasBD, detallesTablasBD, false, false));
    }
}