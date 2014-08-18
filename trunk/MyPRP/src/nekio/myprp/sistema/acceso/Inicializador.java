package nekio.myprp.sistema.acceso;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Autogenerador;
import nekio.myprp.recursos.utilerias.Globales;
import static nekio.myprp.recursos.utilerias.Globales.BD_TOOLS;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.sistema.acceso.dao.ModalidadDAO;
import nekio.myprp.sistema.acceso.dto.ModalidadDTO;

public class Inicializador {
    private static final String ENTIDAD = "Usuario";
    
    public Inicializador(){
        this(null, null);
    }
    
    public Inicializador(String usuario, String password){
        this(1, usuario, password);
    }
    
    public Inicializador(int idioma, String usuario, String password){
        Idioma.IDIOMA_DEFINIDO = idioma;
        
        conectarBD(usuario, password);
    }
    
    private void conectarBD(String usuario, String password){
        BDConexion bd = new BDConexion(
                Globales.BD_GESTOR,
                Globales.BD_USUARIO,
                Globales.BD_PASSWORD,
                Globales.BD_HOST,
                Globales.BD_PUERTO,
                Globales.BD_TOOLS,
                Globales.BD_MAX_ACTIVOS,
                Globales.BD_MAX_IDLE
        );
        
        Globales.BD_DESC_ESQUEMA = Globales.BD_TOOLS;
    }
    
    public static boolean loggear(String usuario, String password, boolean recordar){ 
        boolean ingresado = false;
        AccesoGestor gestor = new AccesoGestor();
        String mensaje = null;
        
        if(usuario == null){
            if(Globales.APP_DEBUG)
                ConsolaDebug.agregarTexto("--- LOGGEANDO CON CREDENCIALES ANONIMAS ---", ConsolaDebug.MAPEO);
            usuario = Idioma.obtenerTexto(Idioma.PROP_ACC_USR_ANONIMO, "usuario");
            password = Idioma.obtenerTexto(Idioma.PROP_ACC_USR_ANONIMO, "password");
        }
        
        String acceso = null;
        try{
            acceso = Autogenerador.crearAcceso(password);
        }catch(Exception e){}
        
        Login login = new Login(usuario, acceso, password, recordar);
        if(login.validar()){
            ingresado = true;
            
            definirConfiguracionesApp();
            
            gestor.setEsquemaBD(BD_TOOLS);
            gestor.setDTO(login.getDTO());
            gestor.ejecutarControladorNegocio("login", ENTIDAD);
            
            mensaje = "Bienvenido "+login.getUsuarioIngresado();
        }else{
            mensaje = "\nEl login fue rechazado por el siguiente motivo:\n";
            
            if(!login.isUsuarioValido())
                mensaje += "   Usuario No valido\n";
            else if(!login.isPasswordValido())
                mensaje += "   Password incorrecto\n";
            else if(!login.isAccesoValido())
                mensaje += "   Usuario Inactivo\n";
        }
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(mensaje, ConsolaDebug.BITACORA);
        
        return ingresado;
    }
    
    private static void definirConfiguracionesApp(){
        ModalidadDTO modalidad = (ModalidadDTO) new ModalidadDAO().leerUno(null);
        Globales.APP_DEBUG = modalidad.isAppDebug();
        Globales.APP_DESIGN = modalidad.isAppDesign();
        
        Globales.CONSOLA.setVisible(Globales.APP_DEBUG);
    }
}
