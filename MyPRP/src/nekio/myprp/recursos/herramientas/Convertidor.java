package nekio.myprp.recursos.herramientas;

/**
 *
 * @author Nekio
 */
public class Convertidor {
    public static Integer aEntero(String cadena){
        Integer entero = null;
        
        try{
            entero = Integer.parseInt(cadena);
        }catch(Exception e){}
        
        return entero;
    }
    
    public static Boolean aBooleano(String cadena){
        Boolean booleano = null;
        
        try{
            booleano = Boolean.parseBoolean(cadena);
        }catch(Exception e){}
        
        return booleano;
    }
}
