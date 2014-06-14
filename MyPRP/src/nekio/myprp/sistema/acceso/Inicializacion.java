package nekio.myprp.sistema.acceso;

import java.util.ArrayList;
import nekio.myprp.recursos.img.obj.GestorImagen;
import nekio.myprp.recursos.utilerias.Globales;
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
                Globales.BD_ESQUEMA,
                Globales.BD_MAX_ACTIVOS,
                Globales.BD_MAX_IDLE
        );
    }
    
    public String loggear(){        
        GestorAcceso gestor = new GestorAcceso();
        String mensaje = null;
        
        if(usuario == null){
            System.out.println("--- LOGGEANDO CON CREDENCIALES ANONIMAS ---");
            setUsuario(Idioma.obtenerTexto(Idioma.PROP_ACC_USR_ANONIMO, "usuario"));
            setPassword(Idioma.obtenerTexto(Idioma.PROP_ACC_USR_ANONIMO, "password"));
        }
        
        Login login = new Login(usuario,password);
        if(login.validar()){            
            gestor.setDTO(login.getDTO());
            gestor.ejecutarControladorNegocio(Globales.BD.LEER.getValor(), entidad);
            
            mensaje = "Bienvenido "+login.getUsuarioIngresado();
        }else{
            mensaje = "\nEl login fue rechazado por el siguiente motivo:\n";
            
            if(!login.isUsuarioValido())
                mensaje += "   Usuario No valido\n";
            else if(!login.isPasswordValido())
                mensaje += "   Password incorrecto\n";
            else if(!login.isAccesoValido())
                mensaje += "   Usuario Inactivo\n";
            
            gestor.ejecutarControladorNegocio("login",entidad);
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
