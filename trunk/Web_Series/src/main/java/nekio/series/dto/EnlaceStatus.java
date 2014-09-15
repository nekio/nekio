package nekio.series.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "enlace_status")
@NamedQueries({
    @NamedQuery(name = "EnlaceStatus.findAll", query = "SELECT e FROM EnlaceStatus e"),
    @NamedQuery(name = "EnlaceStatus.findByIdEnlaceStatus", query = "SELECT e FROM EnlaceStatus e WHERE e.idEnlaceStatus = :idEnlaceStatus"),
    @NamedQuery(name = "EnlaceStatus.findByDescripcion", query = "SELECT e FROM EnlaceStatus e WHERE e.descripcion = :descripcion")})
public class EnlaceStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_enlace_status")
    private Integer idEnlaceStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    private String descripcion;
    @OneToMany(mappedBy = "idEnlaceStatus")
    private Collection<Enlace> enlaceCollection;

    public EnlaceStatus() {
    }

    public EnlaceStatus(Integer idEnlaceStatus) {
        this.idEnlaceStatus = idEnlaceStatus;
    }

    public EnlaceStatus(Integer idEnlaceStatus, String descripcion) {
        this.idEnlaceStatus = idEnlaceStatus;
        this.descripcion = descripcion;
    }

    public Integer getIdEnlaceStatus() {
        return idEnlaceStatus;
    }

    public void setIdEnlaceStatus(Integer idEnlaceStatus) {
        this.idEnlaceStatus = idEnlaceStatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Enlace> getEnlaceCollection() {
        return enlaceCollection;
    }

    public void setEnlaceCollection(Collection<Enlace> enlaceCollection) {
        this.enlaceCollection = enlaceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnlaceStatus != null ? idEnlaceStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnlaceStatus)) {
            return false;
        }
        EnlaceStatus other = (EnlaceStatus) object;
        if ((this.idEnlaceStatus == null && other.idEnlaceStatus != null) || (this.idEnlaceStatus != null && !this.idEnlaceStatus.equals(other.idEnlaceStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.EnlaceStatus[ idEnlaceStatus=" + idEnlaceStatus + " ]";
    }
    
}
