package nekio.myprp.sistema.acceso;

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.Idioma;

/**
 *
 * @author Nekio
 */
public class Inicializacion {
    
    public Inicializacion(){
        this(1, null);
    }
    
    public Inicializacion(int idioma, String usuario){
        Idioma.IDIOMA_DEFINIDO = idioma;
        conectarBD();
        loggear("usuario_02", "password_02");
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
    
    private void loggear(String usuario, String password){        
        if(usuario == null){
            usuario = Idioma.obtenerTexto(Idioma.PROP_ACC_USR_ANONIMO, "usuario");
            password = Idioma.obtenerTexto(Idioma.PROP_ACC_USR_ANONIMO, "password");
        }
        
        Login login = new Login(usuario,password);
        if(login.validar()){
            GestorAcceso gestor = new GestorAcceso();
            
            gestor.setParametros(login.getIdUsuario());
            gestor.ejecutarControladorNegocio("leerUsuario");
        }else{
            String mensaje = "\nEl login fue rechazado por el siguiente motivo:\n";
            
            if(!login.isUsuarioValido())
                mensaje += "   Usuario No valido\n";
            else if(!login.isPasswordValido())
                mensaje += "   Password incorrecto\n";
            else if(!login.isAccesoValido())
                mensaje += "   Usuario Inactivo\n";
            
            if(Globales.APP_DEBUG)
                System.out.println(mensaje);
        }
    }
}
