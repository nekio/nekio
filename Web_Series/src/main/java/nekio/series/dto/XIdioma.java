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
 * @author SITI
 */
@Entity
@Table(name = "x_idioma")
@NamedQueries({
    @NamedQuery(name = "XIdioma.findAll", query = "SELECT x FROM XIdioma x"),
    @NamedQuery(name = "XIdioma.findByIdIdioma", query = "SELECT x FROM XIdioma x WHERE x.idIdioma = :idIdioma"),
    @NamedQuery(name = "XIdioma.findByDescripcion", query = "SELECT x FROM XIdioma x WHERE x.descripcion = :descripcion")})
public class XIdioma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_idioma")
    private Integer idIdioma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private Collection<XUsuario> xUsuarioCollection;

    public XIdioma() {
    }

    public XIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public XIdioma(Integer idIdioma, String descripcion) {
        this.idIdioma = idIdioma;
        this.descripcion = descripcion;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<XUsuario> getXUsuarioCollection() {
        return xUsuarioCollection;
    }

    public void setXUsuarioCollection(Collection<XUsuario> xUsuarioCollection) {
        this.xUsuarioCollection = xUsuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XIdioma)) {
            return false;
        }
        XIdioma other = (XIdioma) object;
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XIdioma[ idIdioma=" + idIdioma + " ]";
    }
    
}
