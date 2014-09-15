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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "x_tipo_usuario")
@NamedQueries({
    @NamedQuery(name = "XTipoUsuario.findAll", query = "SELECT x FROM XTipoUsuario x"),
    @NamedQuery(name = "XTipoUsuario.findByIdTipoUsuario", query = "SELECT x FROM XTipoUsuario x WHERE x.idTipoUsuario = :idTipoUsuario"),
    @NamedQuery(name = "XTipoUsuario.findByDescripcion", query = "SELECT x FROM XTipoUsuario x WHERE x.descripcion = :descripcion")})
public class XTipoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_usuario")
    private Integer idTipoUsuario;
    @Size(max = 50)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoUsuario")
    private Collection<XUsuario> xUsuarioCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "xTipoUsuario")
    private XPrivilegio xPrivilegio;

    public XTipoUsuario() {
    }

    public XTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
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

    public XPrivilegio getXPrivilegio() {
        return xPrivilegio;
    }

    public void setXPrivilegio(XPrivilegio xPrivilegio) {
        this.xPrivilegio = xPrivilegio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUsuario != null ? idTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XTipoUsuario)) {
            return false;
        }
        XTipoUsuario other = (XTipoUsuario) object;
        if ((this.idTipoUsuario == null && other.idTipoUsuario != null) || (this.idTipoUsuario != null && !this.idTipoUsuario.equals(other.idTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XTipoUsuario[ idTipoUsuario=" + idTipoUsuario + " ]";
    }
    
}
