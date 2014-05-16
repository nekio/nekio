
package nekio.myprp.recursos.utilerias;

import nekio.myprp.sistema.acceso.vista.LoginGUI;

/**
 *
 * @author Nekio
 */
public class Mapeador {
    public static void abrir(int modulo, String pagina){
        if(Globales.APP_DEBUG)
            System.out.println("\nMapeador abriendo la vista: " + pagina);
        
        if(pagina.equals("login"))
            new LoginGUI();
        
        switch(modulo){
            /*********************************************************/
            /* MODULO DE ACCESO */
            /*********************************************************/
            case Globales.MOD_ACCESO:
                if(pagina.equals("bienvenida")){
                    //new Pagina();
                }else if(pagina.equals("")){
                
                }
            break;
                
            /*********************************************************/
            /* MODULO DE IMAGEN*/
            /*********************************************************/
            case Globales.MOD_IMAGEN:
                if(pagina.equals("mostrarImagen")){
                    //new Pagina();
                }else if(pagina.equals("")){
                
                }
            break;
        }
    }
}
