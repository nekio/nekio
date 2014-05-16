package nekio.myprp.sistema.acceso;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

public class Login {
    private String usuarioIngresado;
    private String passwordIngresado;
    
    private int idUsuario;
    private boolean usuarioValido;
    private boolean passwordValido;
    private boolean accesoValido;
    
    public Login(){
        this(null, null);
    }
    
    public Login(String usuarioIngresado, String passwordIngresado){
        this.usuarioIngresado = usuarioIngresado;
        this.passwordIngresado = passwordIngresado;
        
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
             usuarioValido = validarUsuario(usuario.getUsuario());
            if(isUsuarioValido()){
                passwordValido = validarPassword(usuario.getPassword());
                if(isPasswordValido()){
                    accesoValido = usuario.isActivo();  
                    if(accesoValido)
                        valido = true;
                    
                    idUsuario = usuario.getIdUsuario();
                    break;
                }else
                    break;
            }
         }
         
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

    public String getUsuarioIngresado() {
        return usuarioIngresado;
    }

    public String getPasswordIngresado() {
        return passwordIngresado;
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

    public int getIdUsuario() {
        return idUsuario;
    }
}
