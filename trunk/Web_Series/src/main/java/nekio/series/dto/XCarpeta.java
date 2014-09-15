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
@Table(name = "x_carpeta")
@NamedQueries({
    @NamedQuery(name = "XCarpeta.findAll", query = "SELECT x FROM XCarpeta x"),
    @NamedQuery(name = "XCarpeta.findByIdCarpeta", query = "SELECT x FROM XCarpeta x WHERE x.idCarpeta = :idCarpeta"),
    @NamedQuery(name = "XCarpeta.findByDescripcion", query = "SELECT x FROM XCarpeta x WHERE x.descripcion = :descripcion")})
public class XCarpeta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carpeta")
    private Integer idCarpeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    private String descripcion;
    @OneToMany(mappedBy = "idCarpeta")
    private Collection<XImagen> xImagenCollection;

    public XCarpeta() {
    }

    public XCarpeta(Integer idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public XCarpeta(Integer idCarpeta, String descripcion) {
        this.idCarpeta = idCarpeta;
        this.descripcion = descripcion;
    }

    public Integer getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(Integer idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<XImagen> getXImagenCollection() {
        return xImagenCollection;
    }

    public void setXImagenCollection(Collection<XImagen> xImagenCollection) {
        this.xImagenCollection = xImagenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarpeta != null ? idCarpeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XCarpeta)) {
            return false;
        }
        XCarpeta other = (XCarpeta) object;
        if ((this.idCarpeta == null && other.idCarpeta != null) || (this.idCarpeta != null && !this.idCarpeta.equals(other.idCarpeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XCarpeta[ idCarpeta=" + idCarpeta + " ]";
    }
    
}
