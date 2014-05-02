package nekio.myprp.recursos.utilerias;

import nekio.myprp.recursos.utilerias.bd.Gestor;

/**
 *
 * @author Nekio
 */

public class Globales {
    /* CONSTANTES */
    
    // <editor-fold defaultstate="collapsed" desc="Datos del Software">  
    public static final String NOMBRE_APP       = "MyPRP";
    public static final String TITULO_APP       = "My Personal Resource Planning";
    public static final String DEVELOPER        = "Nekio";
    public static final String CONTACTO         = "nekio@outlook.com";
    public static final String VERSION          = "BETA";
    public static final String ACTUALIZACION    = "0";
    public static final String FECHA_LIBERACION = "30/04/2014";
    public static final String MSJ_MANTTO       = "Espera la proxima actualizacion.\nProceso en construccion";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Valores de App">  
    public static final boolean APP_DEBUG = true;
    public static final boolean APP_DESIGN = true;
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc="Rutas de archivos">
    public static final String RUTA_BASE    = "/nekio/myprp";
    public static final String RECURSOS     = RUTA_BASE+"/recursos";
    public static final String IMG          = RECURSOS+"/img/";
    public static final String VALORES      = RECURSOS+"/valores";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Conexion a la BD">  
    public static final Gestor BD_GESTOR    = Gestor.JAVA_DERBY;
    public static final String BD_USUARIO   = "sma";
    public static final String BD_PASSWORD  = "admin";
    public static final String BD_HOST      = "localhost";
    public static final String BD_PUERTO    = "1527";
    public static final String BD_ESQUEMA   = "lania";
    public static final int BD_MAX_ACTIVOS  = 20;
    public static final int BD_MAX_IDLE     = 2;
    // </editor-fold>
    
    /* VARIABLES */
}
