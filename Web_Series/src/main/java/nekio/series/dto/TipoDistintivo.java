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
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "tipo_distintivo")
@NamedQueries({
    @NamedQuery(name = "TipoDistintivo.findAll", query = "SELECT t FROM TipoDistintivo t"),
    @NamedQuery(name = "TipoDistintivo.findByIdTipoDistintivo", query = "SELECT t FROM TipoDistintivo t WHERE t.idTipoDistintivo = :idTipoDistintivo"),
    @NamedQuery(name = "TipoDistintivo.findByDescripcion", query = "SELECT t FROM TipoDistintivo t WHERE t.descripcion = :descripcion")})
public class TipoDistintivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_distintivo")
    private Integer idTipoDistintivo;
    @Size(max = 25)
    private String descripcion;
    @OneToMany(mappedBy = "idTipoDistintivo")
    private Collection<Distintivo> distintivoCollection;

    public TipoDistintivo() {
    }

    public TipoDistintivo(Integer idTipoDistintivo) {
        this.idTipoDistintivo = idTipoDistintivo;
    }

    public Integer getIdTipoDistintivo() {
        return idTipoDistintivo;
    }

    public void setIdTipoDistintivo(Integer idTipoDistintivo) {
        this.idTipoDistintivo = idTipoDistintivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Distintivo> getDistintivoCollection() {
        return distintivoCollection;
    }

    public void setDistintivoCollection(Collection<Distintivo> distintivoCollection) {
        this.distintivoCollection = distintivoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDistintivo != null ? idTipoDistintivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDistintivo)) {
            return false;
        }
        TipoDistintivo other = (TipoDistintivo) object;
        if ((this.idTipoDistintivo == null && other.idTipoDistintivo != null) || (this.idTipoDistintivo != null && !this.idTipoDistintivo.equals(other.idTipoDistintivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.TipoDistintivo[ idTipoDistintivo=" + idTipoDistintivo + " ]";
    }
    
}
