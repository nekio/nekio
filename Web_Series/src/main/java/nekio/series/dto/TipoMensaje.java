package nekio.series.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "tipo_mensaje")
@NamedQueries({
    @NamedQuery(name = "TipoMensaje.findAll", query = "SELECT t FROM TipoMensaje t"),
    @NamedQuery(name = "TipoMensaje.findByIdTipoMensaje", query = "SELECT t FROM TipoMensaje t WHERE t.idTipoMensaje = :idTipoMensaje"),
    @NamedQuery(name = "TipoMensaje.findByDescripcion", query = "SELECT t FROM TipoMensaje t WHERE t.descripcion = :descripcion")})
public class TipoMensaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_mensaje")
    private Integer idTipoMensaje;
    @Size(max = 20)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoMensaje")
    private Collection<MensajePrivado> mensajePrivadoCollection;

    public TipoMensaje() {
    }

    public TipoMensaje(Integer idTipoMensaje) {
        this.idTipoMensaje = idTipoMensaje;
    }

    public Integer getIdTipoMensaje() {
        return idTipoMensaje;
    }

    public void setIdTipoMensaje(Integer idTipoMensaje) {
        this.idTipoMensaje = idTipoMensaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<MensajePrivado> getMensajePrivadoCollection() {
        return mensajePrivadoCollection;
    }

    public void setMensajePrivadoCollection(Collection<MensajePrivado> mensajePrivadoCollection) {
        this.mensajePrivadoCollection = mensajePrivadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMensaje != null ? idTipoMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMensaje)) {
            return false;
        }
        TipoMensaje other = (TipoMensaje) object;
        if ((this.idTipoMensaje == null && other.idTipoMensaje != null) || (this.idTipoMensaje != null && !this.idTipoMensaje.equals(other.idTipoMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.TipoMensaje[ idTipoMensaje=" + idTipoMensaje + " ]";
    }
    
}
