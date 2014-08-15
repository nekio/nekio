package nekio.myprp.sistema.acceso;

/**
 *
 * @author Nekio
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

public class Login {
    private String usuarioIngresado;
    private String passwordIngresado;
    private String password;
    private boolean recordar;
    
    private UsuarioDTO dto;
    private boolean usuarioValido;
    private boolean passwordValido;
    private boolean accesoValido;
    
    public Login(){
        this(null, null, null, false);
    }
    
    public Login(String usuarioIngresado, String passwordIngresado, String password, boolean recordar){
        this.usuarioIngresado = usuarioIngresado;
        this.passwordIngresado = passwordIngresado;
        this.password = password;
        this.recordar = recordar;
        
        this.usuarioValido = false;
        this.passwordValido = false;
        this.accesoValido = false;
    }
    
    public boolean validar(){
        boolean valido = false;
        UsuarioDAO dao = new UsuarioDAO();
        ArrayList<DTO> usuarios = dao.leer(null); 
         
        UsuarioDTO usuario;
         for(DTO dto:usuarios){
              usuario = (UsuarioDTO) dto;
             usuarioValido = validarUsuario(usuario.getNick());
            if(isUsuarioValido()){
                passwordValido = validarPassword(usuario.getAcceso());
                if(isPasswordValido()){
                    accesoValido = usuario.isActivo();  
                    if(accesoValido)
                        valido = true;
                    
                    this.dto = (UsuarioDTO) dto;
                    break;
                }else
                    break;
            }
         }
         
         recordarCredenciales();
         
         return valido;
    }
    
    private boolean validarUsuario(String usuario){
        boolean valido = false;
        if(usuarioIngresado.equals(usuario))
            return true;
        
        return valido;
    }
    
    private boolean validarPassword(String password){
        boolean valido = false;
        if(passwordIngresado.equals(password))
            return true;
        
        return valido;
    }
    
    private void recordarCredenciales(){
        // Verificar que exista la ruta Home
        File rutaHome = new File(Globales.RUTA_HOME);
        if(!rutaHome.isDirectory())
            rutaHome.mkdir();

        // Borrar el archivo si es que existe
        File rutaCredenciales=new File(Globales.RUTA_CREDENCIALES);
        rutaCredenciales.delete();
                
        if(recordar){
            try{                
                // Escribir el contenido
                BufferedWriter bw = new BufferedWriter(new FileWriter(Globales.RUTA_CREDENCIALES));

                bw.write(usuarioIngresado);
                bw.newLine();
                bw.write(password);

                bw.close();
                bw = null;
            }catch(Exception e){}
        }
    }

    public String getUsuarioIngresado() {
        return usuarioIngresado;
    }

    public String getPasswordIngresado() {
        return passwordIngresado;
    }
    
    public UsuarioDTO getDTO() {
        return dto;
    }
    
    public boolean isUsuarioValido() {
        return usuarioValido;
    }

    public boolean isPasswordValido() {
        return passwordValido;
    }

    public boolean isAccesoValido() {
        return accesoValido;
    }
}
