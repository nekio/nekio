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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "tipo_episodio")
@NamedQueries({
    @NamedQuery(name = "TipoEpisodio.findAll", query = "SELECT t FROM TipoEpisodio t"),
    @NamedQuery(name = "TipoEpisodio.findByIdTipoEpisodio", query = "SELECT t FROM TipoEpisodio t WHERE t.idTipoEpisodio = :idTipoEpisodio"),
    @NamedQuery(name = "TipoEpisodio.findByDescripcion", query = "SELECT t FROM TipoEpisodio t WHERE t.descripcion = :descripcion")})
public class TipoEpisodio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_episodio")
    private Integer idTipoEpisodio;
    @Size(max = 20)
    private String descripcion;
    @JoinColumn(name = "id_rango", referencedColumnName = "id_rango")
    @ManyToOne(optional = false)
    private XRango idRango;
    @OneToMany(mappedBy = "idTipoEpisodio")
    private Collection<Episodio> episodioCollection;

    public TipoEpisodio() {
    }

    public TipoEpisodio(Integer idTipoEpisodio) {
        this.idTipoEpisodio = idTipoEpisodio;
    }

    public Integer getIdTipoEpisodio() {
        return idTipoEpisodio;
    }

    public void setIdTipoEpisodio(Integer idTipoEpisodio) {
        this.idTipoEpisodio = idTipoEpisodio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public XRango getIdRango() {
        return idRango;
    }

    public void setIdRango(XRango idRango) {
        this.idRango = idRango;
    }

    public Collection<Episodio> getEpisodioCollection() {
        return episodioCollection;
    }

    public void setEpisodioCollection(Collection<Episodio> episodioCollection) {
        this.episodioCollection = episodioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEpisodio != null ? idTipoEpisodio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEpisodio)) {
            return false;
        }
        TipoEpisodio other = (TipoEpisodio) object;
        if ((this.idTipoEpisodio == null && other.idTipoEpisodio != null) || (this.idTipoEpisodio != null && !this.idTipoEpisodio.equals(other.idTipoEpisodio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.TipoEpisodio[ idTipoEpisodio=" + idTipoEpisodio + " ]";
    }
    
}
