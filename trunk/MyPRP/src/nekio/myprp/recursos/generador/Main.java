package nekio.myprp.recursos.generador;

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;

/**
 *
 * @author Nekio
 */
public class Main {
    public static void main(String[] args) {
        Globales.CONSOLA.setVisible(true);
        
        Main m = new Main();
        m.conexion();
        
        //BDConexion.obtenerTablas(Globales.BD_TOOLS, Globales.BD_USUARIO);
        BDConexion.obtenerDetalles(Globales.BD_TOOLS, Globales.BD_USUARIO, "sistema");
    }
    
    private void conexion(){
        BDConexion bd = new BDConexion(
                Globales.BD_GESTOR,
                Globales.BD_USUARIO,
                Globales.BD_PASSWORD,
                Globales.BD_HOST,
                Globales.BD_PUERTO,
                Globales.BD_TOOLS,
                Globales.BD_MAX_ACTIVOS,
                Globales.BD_MAX_IDLE
        );
    }
}
