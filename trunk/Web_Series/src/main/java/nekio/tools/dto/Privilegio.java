package nekio.tools.dto;

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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nekio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Privilegio.findAll", query = "SELECT p FROM Privilegio p"),
    @NamedQuery(name = "Privilegio.findByIdPrivilegio", query = "SELECT p FROM Privilegio p WHERE p.idPrivilegio = :idPrivilegio"),
    @NamedQuery(name = "Privilegio.findByConfigurar", query = "SELECT p FROM Privilegio p WHERE p.configurar = :configurar"),
    @NamedQuery(name = "Privilegio.findByBuscar", query = "SELECT p FROM Privilegio p WHERE p.buscar = :buscar"),
    @NamedQuery(name = "Privilegio.findByInsertar", query = "SELECT p FROM Privilegio p WHERE p.insertar = :insertar"),
    @NamedQuery(name = "Privilegio.findByModificar", query = "SELECT p FROM Privilegio p WHERE p.modificar = :modificar"),
    @NamedQuery(name = "Privilegio.findByEliminar", query = "SELECT p FROM Privilegio p WHERE p.eliminar = :eliminar")})
public class Privilegio implements Serializable {
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
    private TipoUsuario tipoUsuario;

    public Privilegio() {
    }

    public Privilegio(Integer idPrivilegio) {
        this.idPrivilegio = idPrivilegio;
    }

    public Privilegio(Integer idPrivilegio, int configurar, int buscar, int insertar, int modificar, int eliminar) {
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

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
        if (!(object instanceof Privilegio)) {
            return false;
        }
        Privilegio other = (Privilegio) object;
        if ((this.idPrivilegio == null && other.idPrivilegio != null) || (this.idPrivilegio != null && !this.idPrivilegio.equals(other.idPrivilegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.tools.dto.Privilegio[ idPrivilegio=" + idPrivilegio + " ]";
    }
    
}
