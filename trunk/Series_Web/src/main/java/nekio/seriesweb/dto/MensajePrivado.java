
package nekio.seriesweb.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "mensaje_privado")
@NamedQueries({
    @NamedQuery(name = "MensajePrivado.findAll", query = "SELECT m FROM MensajePrivado m"),
    @NamedQuery(name = "MensajePrivado.findByIdMensajePrivado", query = "SELECT m FROM MensajePrivado m WHERE m.mensajePrivadoPK.idMensajePrivado = :idMensajePrivado"),
    @NamedQuery(name = "MensajePrivado.findByIdUsuario", query = "SELECT m FROM MensajePrivado m WHERE m.mensajePrivadoPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "MensajePrivado.findByIdMpRelacionado", query = "SELECT m FROM MensajePrivado m WHERE m.idMpRelacionado = :idMpRelacionado"),
    @NamedQuery(name = "MensajePrivado.findByFecha", query = "SELECT m FROM MensajePrivado m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "MensajePrivado.findByRecibidoEnviado", query = "SELECT m FROM MensajePrivado m WHERE m.recibidoEnviado = :recibidoEnviado"),
    @NamedQuery(name = "MensajePrivado.findByAtendido", query = "SELECT m FROM MensajePrivado m WHERE m.atendido = :atendido")})
public class MensajePrivado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MensajePrivadoPK mensajePrivadoPK;
    @Column(name = "id_mp_relacionado")
    private Integer idMpRelacionado;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "mensaje")
    private String mensaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recibido_enviado")
    private int recibidoEnviado;
    @Column(name = "atendido")
    private Integer atendido;
    @JoinColumn(name = "id_web", referencedColumnName = "id_web")
    @ManyToOne
    private Web idWeb;
    @JoinColumn(name = "id_tipo_mensaje", referencedColumnName = "id_tipo_mensaje")
    @ManyToOne(optional = false)
    private TipoMensaje idTipoMensaje;
    @JoinColumn(name = "id_colaborador", referencedColumnName = "id_colaborador")
    @ManyToOne
    private Colaborador idColaborador;

    public MensajePrivado() {
    }

    public MensajePrivado(MensajePrivadoPK mensajePrivadoPK) {
        this.mensajePrivadoPK = mensajePrivadoPK;
    }

    public MensajePrivado(MensajePrivadoPK mensajePrivadoPK, String mensaje, Date fecha, int recibidoEnviado) {
        this.mensajePrivadoPK = mensajePrivadoPK;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.recibidoEnviado = recibidoEnviado;
    }

    public MensajePrivado(int idMensajePrivado, int idUsuario) {
        this.mensajePrivadoPK = new MensajePrivadoPK(idMensajePrivado, idUsuario);
    }

    public MensajePrivadoPK getMensajePrivadoPK() {
        return mensajePrivadoPK;
    }

    public void setMensajePrivadoPK(MensajePrivadoPK mensajePrivadoPK) {
        this.mensajePrivadoPK = mensajePrivadoPK;
    }

    public Integer getIdMpRelacionado() {
        return idMpRelacionado;
    }

    public void setIdMpRelacionado(Integer idMpRelacionado) {
        this.idMpRelacionado = idMpRelacionado;
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

    public int getRecibidoEnviado() {
        return recibidoEnviado;
    }

    public void setRecibidoEnviado(int recibidoEnviado) {
        this.recibidoEnviado = recibidoEnviado;
    }

    public Integer getAtendido() {
        return atendido;
    }

    public void setAtendido(Integer atendido) {
        this.atendido = atendido;
    }

    public Web getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(Web idWeb) {
        this.idWeb = idWeb;
    }

    public TipoMensaje getIdTipoMensaje() {
        return idTipoMensaje;
    }

    public void setIdTipoMensaje(TipoMensaje idTipoMensaje) {
        this.idTipoMensaje = idTipoMensaje;
    }

    public Colaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Colaborador idColaborador) {
        this.idColaborador = idColaborador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mensajePrivadoPK != null ? mensajePrivadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajePrivado)) {
            return false;
        }
        MensajePrivado other = (MensajePrivado) object;
        if ((this.mensajePrivadoPK == null && other.mensajePrivadoPK != null) || (this.mensajePrivadoPK != null && !this.mensajePrivadoPK.equals(other.mensajePrivadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.seriesweb.dto.MensajePrivado[ mensajePrivadoPK=" + mensajePrivadoPK + " ]";
    }
    
}
