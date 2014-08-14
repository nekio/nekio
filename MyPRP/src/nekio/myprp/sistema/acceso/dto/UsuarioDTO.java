package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class UsuarioDTO extends DTO {

    private Integer idUsuario;
    private Integer idTipoUsuario;
    private Integer idRango;
    private String nombre;
    private String nick;
    private String autogenerado;
    private String acceso;
    private String contacto;
    private Date fechaRegistro;
    private Date ultimoAcceso;
    private Boolean activo;

    @Override
    public void confirmarDTO() {
        campos = new ArrayList<String>();
        tablasForaneas = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        valoresLOV = new ArrayList<String>();
        camposExtrasLOV = new ArrayList<List>();

        super.campos.add("id_usuario");
        super.valores.add(idUsuario);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);

        super.campos.add("id_tipo_usuario");
        super.valores.add(idTipoUsuario);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
		//super.valoresLOV.add(descripcion);
        //super.camposExtrasLOV.add(new ArrayList<String>() {{add(""); add("");}});
        super.tablasForaneas.add("null");

        super.campos.add("id_rango");
        super.valores.add(idRango);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
		//super.valoresLOV.add(descripcion);
        //super.camposExtrasLOV.add(new ArrayList<String>() {{add(""); add("");}});
        super.tablasForaneas.add("null");

        super.campos.add("nombre");
        super.valores.add(nombre);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);

        super.campos.add("nick");
        super.valores.add(nick);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);

        super.campos.add("autogenerado");
        super.valores.add(autogenerado);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);

        super.campos.add("acceso");
        super.valores.add(acceso);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);

        super.campos.add("contacto");
        super.valores.add(contacto);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);

        super.campos.add("fecha_registro");
        super.valores.add(fechaRegistro);
        super.tipoDatos.add(Globales.TipoDato.FECHA);

        super.campos.add("ultimo_acceso");
        super.valores.add(ultimoAcceso);
        super.tipoDatos.add(Globales.TipoDato.FECHA);

        super.campos.add("activo");
        super.valores.add(activo);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdRango(Integer idRango) {
        this.idRango = idRango;
    }

    public Integer getIdRango() {
        return idRango;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setAutogenerado(String autogenerado) {
        this.autogenerado = autogenerado;
    }

    public String getAutogenerado() {
        return autogenerado;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void setActivo(int activo) {
        if (activo == 1) {
            this.activo = true;
        } else {
            this.activo = false;
        }
    }

    public Boolean isActivo() {
        return activo;
    }
}
