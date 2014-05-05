
package nekio.myprp.recursos.utilerias;

/**
 *
 * @author Nekio
 */
public class Mapeador {
    public static void abrir(int modulo, String pagina){
        if(Globales.APP_DEBUG)
            System.out.println("\nMapeador abriendo la vista: " + pagina);
        switch(modulo){
            case Globales.MOD_ACCESO:
                if(pagina.equals("bienvenida")){
                    //new Pagina();
                }else if(pagina.equals("")){
                
                }
            break;
        }
    }
}
