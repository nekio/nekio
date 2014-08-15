package nekio.myprp.recursos.utilerias;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">  
import java.io.File;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.bd.BDGestor;
// </editor-fold>

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
    
    public static final int ID_SISTEMA = 1;
    public static final int ID_USUARIO_ADMIN = 1;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Conexion a la BD">  
    private static final String CONEXIONES      = "nekio.myprp.recursos.valores.conexiones.";
    private static final String HERRAMIENTAS    = CONEXIONES + "Herramientas";
    private static final String ESQUEMAS        = CONEXIONES + "_Esquemas";
    
    public static final BDGestor BD_GESTOR;
    public static final String BD_USUARIO;
    public static final String BD_PASSWORD;
    public static final String BD_HOST;
    public static final String BD_PUERTO;
    public static final String BD_TOOLS; //Base de datos fija
    public static final int BD_MAX_ACTIVOS;
    public static final int BD_MAX_IDLE;
    
    public static final String BD_ESQUEMA_SERIES;
    public static final int BD_ESQUEMA_SERIES_ID;
    
    static{
        Properties propiedades = obtenerPropiedades(HERRAMIENTAS);
        
        BD_GESTOR = BDGestor.valueOf(propiedades.getProperty("gestor"));
        BD_USUARIO = propiedades.getProperty("usuario");
        BD_PASSWORD = propiedades.getProperty("password");
        BD_HOST = propiedades.getProperty("host");
        BD_PUERTO = propiedades.getProperty("puerto");
        BD_TOOLS = propiedades.getProperty("esquema");
        BD_MAX_ACTIVOS = Integer.valueOf(propiedades.getProperty("maxActivos"));
        BD_MAX_IDLE = Integer.valueOf(propiedades.getProperty("maxIdle"));
        propiedades = null;
        
        propiedades = obtenerPropiedades(ESQUEMAS);
        
        BD_ESQUEMA_SERIES = propiedades.getProperty("series");
        BD_ESQUEMA_SERIES_ID = Integer.valueOf(propiedades.getProperty("idSeries"));
        propiedades = null;
    }
    
    public static Properties obtenerPropiedades(String recurso){
        Properties propiedades = new Properties();
        ResourceBundle paqueteHerramientas =  ResourceBundle.getBundle(recurso);
        
        Enumeration llaves = paqueteHerramientas.getKeys();
        String llave = null;
        while(llaves.hasMoreElements()){
            llave=(String)llaves.nextElement();
            propiedades.put(llave, paqueteHerramientas.getObject(llave));
        }
        
        return propiedades;
    }
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Valores de App">  
    public static final JFrame CONSOLA          = new ConsolaDebug();
    public static final String APP_SEPARADOR    = "--------------------------------------------------------------------";
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
    public static final int MOD_SERIES = 9;
    // </editor-fold>
        
    // <editor-fold defaultstate="collapsed" desc="Rutas de recursos">
    public static final String RUTA_HOME = System.getProperty("user.home")+File.separator+"."+SIGLAS_APP+File.separator;
    
    // Valores
    private static final String RAIZ        = "nekio";
    private static final String PROYECTO    = SIGLAS_APP;
    private static final String MODULOS     = "sistema/modulos";
    private static final String RECURSOS    = "recursos";
    private static final String IMAGENES    = "img";
    private static final String IMG_OBJ     = "obj";
    private static final String VALORES     = "valores";
    
    // Archivos
    public static final String RUTA_CREDENCIALES = RUTA_HOME+"id."+SIGLAS_APP;
    
    // Carpetas
    public static final String RUTA_BASE        = "/"+RAIZ+"/"+PROYECTO;
    public static final String RUTA_MODULOS     = RUTA_BASE+"/"+MODULOS;
    public static final String RUTA_RECURSOS    = RUTA_BASE+"/"+RECURSOS;
    public static final String RUTA_IMG         = RUTA_RECURSOS+"/"+IMAGENES+"/";
    public static final String RUTA_VALORES     = RUTA_RECURSOS+"/"+VALORES;
    
    // Paquetes
    public static final String PAQ_BASE     = RAIZ+"."+PROYECTO;
    public static final String PAQ_RECURSOS = PAQ_BASE+"."+RECURSOS;
    public static final String PAQ_IMG      = PAQ_RECURSOS+"."+IMAGENES;
    public static final String PAQ_IMG_OBJ  = PAQ_IMG+"."+IMG_OBJ;
    
    //Imagenes
    public static final String IMG_NO_AVATAR = RUTA_IMG+"noavatar.png";
    public static final String IMG_PROPIEDADES = RUTA_IMG+"propiedades.png";
    public static final String IMG_LLAVE = RUTA_IMG+"llave.png";
    
    public static final String RUTA_RANGO = RUTA_IMG+"rango/";
    public static final String IMG_RANGO_CRISTAL    = RUTA_RANGO+"cristal.png";
    public static final String IMG_RANGO_ESMERALDA  = RUTA_RANGO+"esmeralda.png";
    public static final String IMG_RANGO_ZAFIRO     = RUTA_RANGO+"zafiro.png";
    public static final String IMG_RANGO_RUBI       = RUTA_RANGO+"rubi.png";
    public static final String IMG_RANGO_DIAMANTE   = RUTA_RANGO+"diamante.png";
    public static final String IMG_RANGO_STAR_0     = RUTA_RANGO+"star0.png";
    public static final String IMG_RANGO_STAR_1     = RUTA_RANGO+"star1.png";
    public static final String IMG_RANGO_PREMIUM     = RUTA_RANGO+"premium.png";
    
    public static final String RUTA_TIPOUSR = RUTA_IMG+"tipousr/";
    public static final String IMG_USR_ANONIMO      = RUTA_TIPOUSR+"anonimo.png";
    public static final String IMG_USR_INVITADO     = RUTA_TIPOUSR+"invitado.png";
    public static final String IMG_USR_USUARIO      = RUTA_TIPOUSR+"usuario.png";
    public static final String IMG_USR_MODERADOR    = RUTA_TIPOUSR+"moderador.png";
    public static final String IMG_USR_ADMIN        = RUTA_TIPOUSR+"admin.png";
    
    public static final String RUTA_PRIVILEGIOS = RUTA_IMG+"privilegios/";
    public static final String IMG_PRIV_CONFIGURAR  = RUTA_PRIVILEGIOS+"config.png";
    public static final String IMG_PRIV_BUSCAR      = RUTA_PRIVILEGIOS+"search.png";
    public static final String IMG_PRIV_INSERTAR    = RUTA_PRIVILEGIOS+"insert.png";
    public static final String IMG_PRIV_MODIFICAR   = RUTA_PRIVILEGIOS+"update.png";
    public static final String IMG_PRIV_ELIMINAR    = RUTA_PRIVILEGIOS+"delete.png";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Valores de Objetos de Negocio"> 
    
    // Tipos de Datos
    public static enum TipoDato{
        CARACTER("Character", "char", "String"),
        TEXTO("String", null, "String"),
        NUMERO("Integer", "int", "Int"),
        DECIMAL("Double", "double", "Double"),
        FECHA("Date", null, "Timestamp"),
        TEXTO_LARGO("String", null, "String"),
        BOOLEANO("Boolean", "boolean", "Int"),
        BLOB("Image", null, "BinaryStream");
        
        String tipoClaseJava;
        String tipoPrimitivoJava;
        String encapsulado;
        
        private TipoDato(String tipoClaseJava, String tipoPrimitivoJava, String encapsulado){
            this.tipoClaseJava = tipoClaseJava;
            if(tipoPrimitivoJava == null)
                this.tipoPrimitivoJava = tipoClaseJava;
            else
                this.tipoPrimitivoJava = tipoPrimitivoJava;
            this.encapsulado = encapsulado;
        }
        
        public String getTipoClaseJava(){
            return tipoClaseJava;
        }
        
        public String getTipoPrimitivoJava(){
            return tipoPrimitivoJava;
        }
        
        public String getEncapsulado(){
            return encapsulado;
        }
    }
    
    // Entidades para gestores
    public static enum Entidad{
        Imagen,
        Usuario,
        
        //SERIES
        MensajePrivado;
        
        public static String obtenerNombreTabla(Entidad entidad){
            String nombre = entidad.name();
            StringBuilder tabla = new StringBuilder();
            
            for (int i=0; i<nombre.length(); i++){
                if(i != 0 && Character.isUpperCase(nombre.charAt(i)))
                   tabla.append("_");
                
                tabla.append(Character.toLowerCase(nombre.charAt(i)));
            }
            
            return tabla.toString();
        }
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
        CANCELAR(8,"cancelar","cancelarAccion");
        
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
    
    // Convencion de identificadores para Listas de Valores
    public static final String BD_TABLA_ID = "id_";
    public static final String BD_TABLA_DESC = "descripcion";
    
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
    
    // <editor-fold defaultstate="collapsed" desc="Interaccion para la BD">  
    public static boolean APP_DEBUG = true;
    public static boolean APP_DESIGN;
    public static boolean APP_BITACORA_ESTILO;
    
    public static String BD_DESC_ESQUEMA; //Variable del nombre del esquema
    public static int BD_ID_ESQUEMA; //Variable del ID del esquema
    
    public static String BD_DESC_USUARIO; //Variable del nombre del usuario
    public static int BD_ID_USUARIO; //Variable del ID del usuario
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Variables de Usuario">
    public static DetalleUsuario APP_USUARIO;  
    // </editor-fold>
}
