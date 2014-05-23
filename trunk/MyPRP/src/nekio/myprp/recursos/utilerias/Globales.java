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
    public static final String SIGLAS_APP       = "myprp";
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
    public static final String OBJ_NEGOCIO_SEPARADOR = "\n--------------------------------------------------------------------";
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
        
    // <editor-fold defaultstate="collapsed" desc="Rutas de recursos">
    // Valores
    private static final String RAIZ        = "nekio";
    private static final String PROYECTO    = SIGLAS_APP;
    private static final String RECURSOS    = "recursos";
    private static final String IMAGENES    = "img";
    private static final String IMG_OBJ     = "obj";
    private static final String VALORES     = "valores";
    
    // Carpetas
    public static final String RUTA_BASE        = "/"+RAIZ+"/"+PROYECTO;
    public static final String RUTA_RECURSOS    = RUTA_BASE+"/"+RECURSOS;
    public static final String RUTA_IMG         = RUTA_RECURSOS+"/"+IMAGENES+"/";
    public static final String RUTA_VALORES     = RUTA_RECURSOS+"/"+VALORES;
    
    // Paquetes
    public static final String PAQ_BASE     = RAIZ+"."+PROYECTO;
    public static final String PAQ_RECURSOS = PAQ_BASE+"."+RECURSOS;
    public static final String PAQ_IMG      = PAQ_RECURSOS+"."+IMAGENES;
    public static final String PAQ_IMG_OBJ  = PAQ_IMG+"."+IMG_OBJ;
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
    
    // Entidades para gestores
    public static enum Entidad{
        Imagen,
        Usuario
    }
    
    // Acciones de la Base de Datos
    public static enum BD{
        NUEVO(0,"agregar","agregarNuevo"),
        INSERTAR(1,"insertar","mostrarInsertado"),
        BUSCAR(2,"buscar","mostrarSeleccionFiltrada"),
        ELIMINAR(3,"eliminar","mostrarSinEliminado"),
        LEER(4,"leer","mostrarSeleccion"),
        LEER_DESC(5,"leerDesc","mostrarSeleccionDesc"),
        LEER_UNO(6,"leerId","mostrar"),
        MODIFICAR(7,"modificar","abrirEdicion"),
        CANCELAR(8,"cancelar","cancelarAccion");;
        
        private int llave;
        private String valor;
        private String pagina;

        private BD(int llave, String valor, String pagina){
            this.llave = llave;
            this.valor = valor;
            this.pagina = pagina;
        }

        public int getLlave() {
            return llave;
        }

        public String getValor() {
            return valor;
        }
        
        public String getPagina() {
            return pagina;
        }
    };
    
    // Nombres de DAO y DTO
    public static final String DAO = "DAO";
    public static final String DTO = "DTO";
    
    // Resultados
    public static final String RES_OK       = "ok";
    public static final String RES_ENTRADA  = "entrada";
    public static final String RES_REDIRIGE = "redirige";
    public static final String RES_ERROR    = "error";
    
    // Acciones de Negocio
    public static final String ACC_LOGIN    = "loginUsuario";
    public static final String ACC_SALIR    = "salir";
    // </editor-fold>
    
    /* VARIABLES */
}
