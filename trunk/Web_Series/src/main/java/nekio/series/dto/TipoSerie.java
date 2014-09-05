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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "tipo_serie")
@NamedQueries({
    @NamedQuery(name = "TipoSerie.findAll", query = "SELECT t FROM TipoSerie t"),
    @NamedQuery(name = "TipoSerie.findByIdTipoSerie", query = "SELECT t FROM TipoSerie t WHERE t.idTipoSerie = :idTipoSerie"),
    @NamedQuery(name = "TipoSerie.findByIdRango", query = "SELECT t FROM TipoSerie t WHERE t.idRango = :idRango"),
    @NamedQuery(name = "TipoSerie.findByDescripcion", query = "SELECT t FROM TipoSerie t WHERE t.descripcion = :descripcion")})
public class TipoSerie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_serie")
    private Integer idTipoSerie;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rango")
    private int idRango;
    @Size(max = 20)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoSerie")
    private Collection<Serie> serieCollection;

    public TipoSerie() {
    }

    public TipoSerie(Integer idTipoSerie) {
        this.idTipoSerie = idTipoSerie;
    }

    public TipoSerie(Integer idTipoSerie, int idRango) {
        this.idTipoSerie = idTipoSerie;
        this.idRango = idRango;
    }

    public Integer getIdTipoSerie() {
        return idTipoSerie;
    }

    public void setIdTipoSerie(Integer idTipoSerie) {
        this.idTipoSerie = idTipoSerie;
    }

    public int getIdRango() {
        return idRango;
    }

    public void setIdRango(int idRango) {
        this.idRango = idRango;
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
        hash += (idTipoSerie != null ? idTipoSerie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSerie)) {
            return false;
        }
        TipoSerie other = (TipoSerie) object;
        if ((this.idTipoSerie == null && other.idTipoSerie != null) || (this.idTipoSerie != null && !this.idTipoSerie.equals(other.idTipoSerie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.TipoSerie[ idTipoSerie=" + idTipoSerie + " ]";
    }
    
}
