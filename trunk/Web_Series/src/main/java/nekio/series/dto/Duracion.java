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
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Duracion.findAll", query = "SELECT d FROM Duracion d"),
    @NamedQuery(name = "Duracion.findByIdDuracion", query = "SELECT d FROM Duracion d WHERE d.idDuracion = :idDuracion"),
    @NamedQuery(name = "Duracion.findByDescripcion", query = "SELECT d FROM Duracion d WHERE d.descripcion = :descripcion")})
public class Duracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_duracion")
    private Integer idDuracion;
    @Size(max = 20)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDuracion")
    private Collection<Serie> serieCollection;

    public Duracion() {
    }

    public Duracion(Integer idDuracion) {
        this.idDuracion = idDuracion;
    }

    public Integer getIdDuracion() {
        return idDuracion;
    }

    public void setIdDuracion(Integer idDuracion) {
        this.idDuracion = idDuracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Serie> getSerieCollection() {
        return serieCollection;
    }

    public void setSerieCollection(Collection<Serie> serieCollection) {
        this.serieCollection = serieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDuracion != null ? idDuracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Duracion)) {
            return false;
        }
        Duracion other = (Duracion) object;
        if ((this.idDuracion == null && other.idDuracion != null) || (this.idDuracion != null && !this.idDuracion.equals(other.idDuracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.Duracion[ idDuracion=" + idDuracion + " ]";
    }
    
}
