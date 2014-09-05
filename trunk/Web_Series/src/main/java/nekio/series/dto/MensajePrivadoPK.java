package nekio.series.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nekio
 */
@Embeddable
public class MensajePrivadoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_mensaje_privado")
    private int idMensajePrivado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;

    public MensajePrivadoPK() {
    }

    public MensajePrivadoPK(int idMensajePrivado, int idUsuario) {
        this.idMensajePrivado = idMensajePrivado;
        this.idUsuario = idUsuario;
    }

    public int getIdMensajePrivado() {
        return idMensajePrivado;
    }

    public void setIdMensajePrivado(int idMensajePrivado) {
        this.idMensajePrivado = idMensajePrivado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMensajePrivado;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajePrivadoPK)) {
            return false;
        }
        MensajePrivadoPK other = (MensajePrivadoPK) object;
        if (this.idMensajePrivado != other.idMensajePrivado) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.MensajePrivadoPK[ idMensajePrivado=" + idMensajePrivado + ", idUsuario=" + idUsuario + " ]";
    }
    
}
