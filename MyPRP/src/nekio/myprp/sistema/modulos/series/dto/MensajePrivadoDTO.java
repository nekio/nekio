package nekio.myprp.sistema.modulos.series.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.Date;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class MensajePrivadoDTO extends DTO{
    private int idMensajePrivado;
    private int idTipoMensaje;
    private int idMpRelacionado;
    private int idWeb;
    private int idColaborador;
    private String mensaje;
    private Date fecha;
    private boolean recibidoEnviado;
    private boolean atendido;
    
    @Override
    public void confirmarDTO(){        
        campos = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        LOVValores = new ArrayList<String>();
        
        super.campos.add("id_mensaje_privado");
        super.valores.add(idMensajePrivado);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("id_tipo_mensaje");
        super.valores.add(idTipoMensaje);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        super.LOVValores.add(Globales.BD_TABLA_DESC);
        
        super.campos.add("id_mp_relacionado");
        super.valores.add(idMpRelacionado);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        super.LOVValores.add("mensaje");
        
        super.campos.add("id_web");
        super.valores.add(idWeb);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        super.LOVValores.add("titulo");
        
        super.campos.add("id_colaborador");
        super.valores.add(idColaborador);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        super.LOVValores.add(Globales.BD_TABLA_DESC);
        
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

    public int getIdMensajePrivado() {
        return idMensajePrivado;
    }

    public void setIdMensajePrivado(int idMensajePrivado) {
        this.idMensajePrivado = idMensajePrivado;
    }

    public int getIdTipoMensaje() {
        return idTipoMensaje;
    }

    public void setIdTipoMensaje(int idTipoMensaje) {
        this.idTipoMensaje = idTipoMensaje;
    }

    public int getIdMpRelacionado() {
        return idMpRelacionado;
    }

    public void setIdMpRelacionado(int idMpRelacionado) {
        this.idMpRelacionado = idMpRelacionado;
    }

    public int getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(int idWeb) {
        this.idWeb = idWeb;
    }

    public int getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(int idColaborador) {
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

    public boolean isRecibidoEnviado() {
        return recibidoEnviado;
    }

    public void setRecibidoEnviado(boolean recibidoEnviado) {
        this.recibidoEnviado = recibidoEnviado;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }
}
