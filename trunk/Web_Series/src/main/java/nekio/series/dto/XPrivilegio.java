package nekio.series.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "x_privilegio")
@NamedQueries({
    @NamedQuery(name = "XPrivilegio.findAll", query = "SELECT x FROM XPrivilegio x"),
    @NamedQuery(name = "XPrivilegio.findByIdPrivilegio", query = "SELECT x FROM XPrivilegio x WHERE x.idPrivilegio = :idPrivilegio"),
    @NamedQuery(name = "XPrivilegio.findByConfigurar", query = "SELECT x FROM XPrivilegio x WHERE x.configurar = :configurar"),
    @NamedQuery(name = "XPrivilegio.findByBuscar", query = "SELECT x FROM XPrivilegio x WHERE x.buscar = :buscar"),
    @NamedQuery(name = "XPrivilegio.findByInsertar", query = "SELECT x FROM XPrivilegio x WHERE x.insertar = :insertar"),
    @NamedQuery(name = "XPrivilegio.findByModificar", query = "SELECT x FROM XPrivilegio x WHERE x.modificar = :modificar"),
    @NamedQuery(name = "XPrivilegio.findByEliminar", query = "SELECT x FROM XPrivilegio x WHERE x.eliminar = :eliminar")})
public class XPrivilegio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_privilegio")
    private Integer idPrivilegio;
    @Basic(optional = false)
    @NotNull
    private int configurar;
    @Basic(optional = false)
    @NotNull
    private int buscar;
    @Basic(optional = false)
    @NotNull
    private int insertar;
    @Basic(optional = false)
    @NotNull
    private int modificar;
    @Basic(optional = false)
    @NotNull
    private int eliminar;
    @JoinColumn(name = "id_privilegio", referencedColumnName = "id_tipo_usuario", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private XTipoUsuario xTipoUsuario;

    public XPrivilegio() {
    }

    public XPrivilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public XPrivilegio(Integer idPrivilegio, int configurar, int buscar, int insertar, int modificar, int eliminar) {
        this.idPrivilegio = idPrivilegio;
        this.configurar = configurar;
        this.buscar = buscar;
        this.insertar = insertar;
        this.modificar = modificar;
        this.eliminar = eliminar;
    }

    public Integer getIdPrivilegio() {
        return idPrivilegio;
    }

    public void setIdPrivilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public int getConfigurar() {
        return configurar;
    }

    public void setConfigurar(int configurar) {
        this.configurar = configurar;
    }

    public int getBuscar() {
        return buscar;
    }

    public void setBuscar(int buscar) {
        this.buscar = buscar;
    }

    public int getInsertar() {
        return insertar;
    }

    public void setInsertar(int insertar) {
        this.insertar = insertar;
    }

    public int getModificar() {
        return modificar;
    }

    public void setModificar(int modificar) {
        this.modificar = modificar;
    }

    public int getEliminar() {
        return eliminar;
    }

    public void setEliminar(int eliminar) {
        this.eliminar = eliminar;
    }

    public XTipoUsuario getXTipoUsuario() {
        return xTipoUsuario;
    }

    public void setXTipoUsuario(XTipoUsuario xTipoUsuario) {
        this.xTipoUsuario = xTipoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivilegio != null ? idPrivilegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XPrivilegio)) {
            return false;
        }
        XPrivilegio other = (XPrivilegio) object;
        if ((this.idPrivilegio == null && other.idPrivilegio != null) || (this.idPrivilegio != null && !this.idPrivilegio.equals(other.idPrivilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XPrivilegio[ idPrivilegio=" + idPrivilegio + " ]";
    }
    
}
