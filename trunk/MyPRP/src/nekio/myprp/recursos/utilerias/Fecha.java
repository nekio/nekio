package nekio.myprp.recursos.utilerias;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nekio
 */
public class Fecha {   
    public static String FORMATO = "dd-MM-yyyy";
    
    public static String obtenerFechaFormateada(Date fecha, String formato){
        SimpleDateFormat formateador = new SimpleDateFormat(formato);        
        String fechaFormateada = formateador.format(fecha);
        
        return fechaFormateada;
    }
}
