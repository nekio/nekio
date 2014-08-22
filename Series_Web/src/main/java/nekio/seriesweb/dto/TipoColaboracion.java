
package nekio.seriesweb.dto;

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
 * @author Nekio
 */
@Entity
@Table(name = "tipo_colaboracion")
@NamedQueries({
    @NamedQuery(name = "TipoColaboracion.findAll", query = "SELECT t FROM TipoColaboracion t"),
    @NamedQuery(name = "TipoColaboracion.findByIdTipoColaboracion", query = "SELECT t FROM TipoColaboracion t WHERE t.idTipoColaboracion = :idTipoColaboracion"),
    @NamedQuery(name = "TipoColaboracion.findByDescripcion", query = "SELECT t FROM TipoColaboracion t WHERE t.descripcion = :descripcion")})
public class TipoColaboracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_colaboracion")
    private Integer idTipoColaboracion;
    @Size(max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoColaboracion")
    private Collection<ProyectoDet> proyectoDetCollection;

    public TipoColaboracion() {
    }

    public TipoColaboracion(Integer idTipoColaboracion) {
        this.idTipoColaboracion = idTipoColaboracion;
    }

    public Integer getIdTipoColaboracion() {
        return idTipoColaboracion;
    }

    public void setIdTipoColaboracion(Integer idTipoColaboracion) {
        this.idTipoColaboracion = idTipoColaboracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<ProyectoDet> getProyectoDetCollection() {
        return proyectoDetCollection;
    }

    public void setProyectoDetCollection(Collection<ProyectoDet> proyectoDetCollection) {
        this.proyectoDetCollection = proyectoDetCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoColaboracion != null ? idTipoColaboracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoColaboracion)) {
            return false;
        }
        TipoColaboracion other = (TipoColaboracion) object;
        if ((this.idTipoColaboracion == null && other.idTipoColaboracion != null) || (this.idTipoColaboracion != null && !this.idTipoColaboracion.equals(other.idTipoColaboracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.seriesweb.dto.TipoColaboracion[ idTipoColaboracion=" + idTipoColaboracion + " ]";
    }
    
}
