package nekio.myprp.sistema.acceso;

import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Autogenerador;
import nekio.myprp.recursos.utilerias.Globales;
import static nekio.myprp.recursos.utilerias.Globales.BD_TOOLS;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.Idioma;

/**
 *
 * @author Nekio
 */
public class Inicializacion {
    private final String entidad = "Usuario";
    
    private String usuario;
    private String password;
    
    public Inicializacion(){
        this(null, null);
    }
    
    public Inicializacion(String usuario, String password){
        this(1, usuario, password);
    }
    
    public Inicializacion(int idioma, String usuario, String password){
        this.usuario = usuario;
        this.password = password;
        
        Idioma.IDIOMA_DEFINIDO = idioma;
        
        conectarBD();
    }
    
    private void conectarBD(){
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
    
    public String loggear(){        
        AccesoGestor gestor = new AccesoGestor();
        String mensaje = null;
        
        if(usuario == null){
            ConsolaDebug.agregarTexto("--- LOGGEANDO CON CREDENCIALES ANONIMAS ---", ConsolaDebug.MAPEO);
            setUsuario(Idioma.obtenerTexto(Idioma.PROP_ACC_USR_ANONIMO, "usuario"));
            setPassword(Idioma.obtenerTexto(Idioma.PROP_ACC_USR_ANONIMO, "password"));
        }
        
        String acceso = null;
        try{
            acceso = Autogenerador.crearAcceso(password);
        }catch(Exception e){}
        
        Login login = new Login(usuario, acceso);
        if(login.validar()){            
            gestor.setEsquemaBD(BD_TOOLS);
            gestor.setDTO(login.getDTO());
            gestor.ejecutarControladorNegocio("login", entidad);
            
            mensaje = "Bienvenido "+login.getUsuarioIngresado();
        }else{
            mensaje = "\nEl login fue rechazado por el siguiente motivo:\n";
            
            if(!login.isUsuarioValido())
                mensaje += "   Usuario No valido\n";
            else if(!login.isPasswordValido())
                mensaje += "   Password incorrecto\n";
            else if(!login.isAccesoValido())
                mensaje += "   Usuario Inactivo\n";
            
            gestor.ejecutarControladorNegocio(Globales.BD.LEER.getValor(),entidad);
        }
        
        return mensaje;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
