package nekio.series.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Distintivo.findAll", query = "SELECT d FROM Distintivo d"),
    @NamedQuery(name = "Distintivo.findByIdDistintivo", query = "SELECT d FROM Distintivo d WHERE d.idDistintivo = :idDistintivo"),
    @NamedQuery(name = "Distintivo.findByDescripcion", query = "SELECT d FROM Distintivo d WHERE d.descripcion = :descripcion")})
public class Distintivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_distintivo")
    private Integer idDistintivo;
    @Size(max = 100)
    private String descripcion;
    @JoinColumn(name = "id_tipo_distintivo", referencedColumnName = "id_tipo_distintivo")
    @ManyToOne
    private TipoDistintivo idTipoDistintivo;
    @OneToMany(mappedBy = "idDistintivo")
    private Collection<Serie> serieCollection;

    public Distintivo() {
    }

    public Distintivo(Integer idDistintivo) {
        this.idDistintivo = idDistintivo;
    }

    public Integer getIdDistintivo() {
        return idDistintivo;
    }

    public void setIdDistintivo(Integer idDistintivo) {
        this.idDistintivo = idDistintivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoDistintivo getIdTipoDistintivo() {
        return idTipoDistintivo;
    }

    public void setIdTipoDistintivo(TipoDistintivo idTipoDistintivo) {
        this.idTipoDistintivo = idTipoDistintivo;
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
        hash += (idDistintivo != null ? idDistintivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distintivo)) {
            return false;
        }
        Distintivo other = (Distintivo) object;
        if ((this.idDistintivo == null && other.idDistintivo != null) || (this.idDistintivo != null && !this.idDistintivo.equals(other.idDistintivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.Distintivo[ idDistintivo=" + idDistintivo + " ]";
    }
    
}
