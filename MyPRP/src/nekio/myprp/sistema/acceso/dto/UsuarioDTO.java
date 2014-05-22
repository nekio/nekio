package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class UsuarioDTO implements DTO{
    private int idUsuario;
    private int idTipoUsuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String contacto;
    private String usuario;
    private String password;
    private boolean activo;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        if(activo == 1)
            this.activo = true;
        else
            this.activo = false;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
