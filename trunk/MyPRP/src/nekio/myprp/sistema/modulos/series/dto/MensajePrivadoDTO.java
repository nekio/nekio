package nekio.myprp.sistema.modulos.series.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class MensajePrivadoDTO extends DTO{
    private Integer idMensajePrivado;
    private Integer idUsuario;
    private Integer idTipoMensaje;
    private Integer idMpRelacionado;
    private Integer idWeb;
    private Integer idColaborador;
    private String mensaje;
    private Date fecha;
    private Boolean recibidoEnviado;
    private Boolean atendido;
    
    @Override
    public void confirmarDTO(){        
        campos = new ArrayList<String>();
        tablasForaneas = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        valoresLOV = new ArrayList<String>();
        camposExtrasLOV = new ArrayList<List>();
        
        // Este campo no nos sirve mostrar en la vista
        //super.campos.add("id_usuario");
        //super.valores.add(idUsuario);
        //super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("id_mensaje_privado");
        super.valores.add(idMensajePrivado);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("id_tipo_mensaje");
        super.valores.add(idTipoMensaje);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        super.valoresLOV.add(Globales.BD_TABLA_DESC);
        super.camposExtrasLOV.add(null);
        super.tablasForaneas.add(null);
        
        super.campos.add("id_mp_relacionado");
        super.valores.add(idMpRelacionado);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        super.valoresLOV.add("mensaje");
        super.camposExtrasLOV.add(null);
        super.tablasForaneas.add("mensaje_privado");
        
        super.campos.add("id_web");
        super.valores.add(idWeb);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        super.valoresLOV.add("titulo");
        super.camposExtrasLOV.add(new ArrayList<String>() {{add("siglas"); add("pagina");}});
        super.tablasForaneas.add(null);
        
        super.campos.add("id_colaborador");
        super.valores.add(idColaborador);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        super.valoresLOV.add(Globales.BD_TABLA_DESC);
        super.camposExtrasLOV.add(null);
        super.tablasForaneas.add(null);
        
        super.campos.add("mensaje");
        super.valores.add(mensaje);
        super.tipoDatos.add(Globales.TipoDato.TEXTO_LARGO);
        
        super.campos.add("fecha");
        super.valores.add(fecha);
        super.tipoDatos.add(Globales.TipoDato.FECHA);
        
        super.campos.add("recibido_enviado");
        super.valores.add(recibidoEnviado);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);
        
        super.campos.add("atendido");
        super.valores.add(atendido);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);
    }

    public Integer getIdMensajePrivado() {
        return idMensajePrivado;
    }

    public void setIdMensajePrivado(Integer idMensajePrivado) {
        this.idMensajePrivado = idMensajePrivado;
    }

    public Integer getIdTipoMensaje() {
        return idTipoMensaje;
    }

    public void setIdTipoMensaje(Integer idTipoMensaje) {
        this.idTipoMensaje = idTipoMensaje;
    }
    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdMpRelacionado() {
        return idMpRelacionado;
    }

    public void setIdMpRelacionado(Integer idMpRelacionado) {
        this.idMpRelacionado = idMpRelacionado;
    }

    public Integer getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(Integer idWeb) {
        this.idWeb = idWeb;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean isRecibidoEnviado() {
        return recibidoEnviado;
    }

    public void setRecibidoEnviado(Boolean recibidoEnviado) {
        this.recibidoEnviado = recibidoEnviado;
    }

    public Boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(Boolean atendido) {
        this.atendido = atendido;
    }
}
