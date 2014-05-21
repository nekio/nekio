package nekio.myprp.recursos.utilerias;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Nekio
 */
public class Fecha {   
    public static Date obtener(String cadenaFormateada, String formato){
        String mensaje = null;
        Date fecha = null;
        SimpleDateFormat formateador = new SimpleDateFormat(formato);
 
	try{
            fecha = formateador.parse(cadenaFormateada);
            mensaje = "Cadena de fecha obtenida correctamente";
	}catch(Exception e) {
            mensaje = e.getMessage();
	}
        
        if(Globales.APP_DEBUG){
            System.out.println("\n" + mensaje);
            System.out.println(fecha + "del formato: "+ formateador.format(fecha));
        }
        
        return fecha;
    }
}
