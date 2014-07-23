
package nekio.myprp.recursos.generador;

import java.awt.Color;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;

/**
 *
 * @author Nekio
 */
public class Generador {
    private List<String> tablasBD;
    private List<List> detallesTablasBD;
    
    public Generador(List<String> tablasBD, List<List> detallesTablasBD){
        this.tablasBD = tablasBD;
        this.detallesTablasBD = detallesTablasBD;
        
        ConsolaDebug.agregarTexto(
                Globales.APP_SEPARADOR + "\n" +
                "Generador inicializado\n" +
                Globales.APP_SEPARADOR + "\n",
                Color.YELLOW,
                false
        );
    }
    
    public void crearCapasDesdeEsquema(){
        ConsolaDebug.agregarTexto("Creando capas para todo el esquema", ConsolaDebug.SQL);
         for(int i=0; i<tablasBD.size() ;i++){
            crearCapasDesdeTabla(tablasBD.get(i), detallesTablasBD.get(i));
        }
    }
    
    public void crearCapasDesdeTabla(String tabla, List<List> detallesTablaBD){
        ConsolaDebug.agregarTexto("\nCreando capas para la tabla " + tabla, ConsolaDebug.PROCESO, false);
//        crearDTO();
//        crearDAO();
//        crearObjetoNegocio();
//        crearGestor();
    }
    
    private void crearDTO(){}
    private void crearDAO(){}
    private void crearObjetoNegocio(){}
    private void crearGestor(){}
}
