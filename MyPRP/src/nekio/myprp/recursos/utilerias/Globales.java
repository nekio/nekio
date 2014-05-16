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
    
    // <editor-fold defaultstate="collapsed" desc="Valores de Modulos">  
    public static final int MOD_ACCESO = 0;
    public static final int MOD_IMAGEN = 1;
    public static final int MOD_ACADEMICO = 2;
    public static final int MOD_DEPORTES = 3;
    public static final int MOD_ESPIRITUAL = 4;
    public static final int MOD_FINANCIERO = 5;
    public static final int MOD_LABORAL = 6;
    public static final int MOD_SALUD = 7;
    public static final int MOD_SOCIAL = 8;
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc="Rutas de archivos">
    public static final String RUTA_BASE    = "/nekio/myprp";
    public static final String RECURSOS     = RUTA_BASE+"/recursos";
    public static final String IMG          = RECURSOS+"/img/";
    public static final String VALORES      = RECURSOS+"/valores";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Conexion a la BD">  
    public static final Gestor BD_GESTOR    = Gestor.MY_SQL;
    public static final String BD_USUARIO   = "lania";
    public static final String BD_PASSWORD  = "lania";
    public static final String BD_HOST      = "localhost";
    public static final String BD_PUERTO    = "3306";
    public static final String BD_ESQUEMA   = "catalogador_series"; //Database
    public static final int BD_MAX_ACTIVOS  = 20;
    public static final int BD_MAX_IDLE     = 2;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Valores de Objetos de Negocio">  
    
    // Acciones de la Base de Datos
    public static enum BD{
        AGREGAR(1,"agregar"),
        BUSCAR(2,"buscar"),
        ELIMINAR(3,"eliminar"),
        LEER(4,"leer"),
        MODIFICAR(5,"modificar");
        
        private int llave;
        private String valor;

        private BD(int llave, String valor){
            this.llave = llave;
            this.valor = valor;
        }

        public int getLlave() {
            return llave;
        }

        public String getValor() {
            return valor;
        }
    };
    
    // Resultados
    public static final String RES_OK       = "ok";
    public static final String RES_ENTRADA  = "entrada";
    public static final String RES_REDIRIGE = "redirige";
    public static final String RES_ERROR    = "error";
    
    // Acciones de Negocio
    public static final String ACC_LOGIN    = "login";
    public static final String ACC_SALIR    = "salir";
    // </editor-fold>
    
    /* VARIABLES */
}
