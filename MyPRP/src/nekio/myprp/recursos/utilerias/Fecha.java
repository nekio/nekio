package nekio.myprp.recursos.utilerias;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nekio
 */
public class Fecha {   
    public static String FORMATO_COMPLETO = "dd-MM-yyyy HH:mm:ss";
    public static String FORMATO_COMPLETO_2 = "dd/MM/yyyy HH:mm:ss";
    public static String FORMATO_CORTO = "dd-MM-yyyy";
    public static String FORMATO_CORTO_2 = "dd/MM/yyyy";
    
    public static String obtenerFechaFormateada(Date fecha, String formato){
        SimpleDateFormat formateador = new SimpleDateFormat(formato);        
        String fechaFormateada = formateador.format(fecha);
        
        return fechaFormateada;
    }
    
    public static Date obtenerFecha(String cadena, String formato){
        DateFormat formateador = new SimpleDateFormat(formato);
        
        Date fecha = null;
        try{
            fecha = formateador.parse(cadena);
        }catch(Exception e){}
   
        return fecha;
    }
}
