package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class UsuarioDTO extends DTO{
    private int idUsuario;
    private int idTipoUsuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String contacto;
    private String usuario;
    private String password;
    private boolean activo;
    
    @Override
    public void confirmarDTO(){        
        campos = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        
        super.campos.add("id_usuario");
        super.valores.add(idUsuario);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("id_tipo_usuario");
        super.valores.add(idTipoUsuario);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("nombre");
        super.valores.add(nombre);
        
        super.campos.add("apellido_p");
        super.valores.add(apellidoP);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("apellido_m");
        super.valores.add(apellidoM);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("contacto");
        super.valores.add(contacto);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("usuario");
        super.valores.add(usuario);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("password");
        super.valores.add(password);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("activo");
        super.valores.add(activo);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);
    }

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
