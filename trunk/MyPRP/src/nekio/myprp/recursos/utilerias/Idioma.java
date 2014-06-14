package nekio.myprp.recursos.utilerias;

/**
 *
 * @author Nekio
 */

import java.util.Locale;
import java.util.ResourceBundle;

public class Idioma {
    // <editor-fold defaultstate="collapsed" desc="Idiomas">  
    public static final Locale LENGUAJE_ES = new Locale("es", "MX");
    public static final int LENGUAJE_ES_VAL = 0;

    public static final Locale LENGUAJE_EN = new Locale("en", "US");
    public static final int LENGUAJE_EN_VAL = 1;
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc="Internacionalizacion">
    public static final String CADENAS          = "nekio.myprp.recursos.valores.cadenas.";
    public static String PROP_MENU              = CADENAS+"Menu";    
    public static String PROP_ACCIONES          = CADENAS+"Acciones";    
    public static String PROP_ACC_USR_ANONIMO   = CADENAS+"AccesoUsuarioAnonimo";    
    public static String PROP_RECOGEDOR_IMAGEN  = CADENAS+"RecogedorImagen";
    public static int IDIOMA_DEFINIDO;
    // </editor-fold>
    
    /* METODOS */
    
    // <editor-fold defaultstate="collapsed" desc="Obtener Texto">  
        public static String obtenerTexto(String archivoPropiedades, String etiqueta){
            Locale idioma = null;
            switch(IDIOMA_DEFINIDO){
                case LENGUAJE_ES_VAL:
                    idioma = LENGUAJE_ES;
                break;
                case LENGUAJE_EN_VAL:
                    idioma = LENGUAJE_EN;
                break;
                default:
                    idioma = LENGUAJE_ES;
            }

            String texto = ResourceBundle.getBundle(archivoPropiedades,idioma).getString(etiqueta);

            return texto;
        }
        // </editor-fold>
}
