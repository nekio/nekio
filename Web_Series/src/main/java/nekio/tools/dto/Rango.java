package nekio.tools.dto;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Rango.findAll", query = "SELECT r FROM Rango r"),
    @NamedQuery(name = "Rango.findByIdRango", query = "SELECT r FROM Rango r WHERE r.idRango = :idRango"),
    @NamedQuery(name = "Rango.findByDescripcion", query = "SELECT r FROM Rango r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Rango.findByDetalle", query = "SELECT r FROM Rango r WHERE r.detalle = :detalle"),
    @NamedQuery(name = "Rango.findByAccPublico", query = "SELECT r FROM Rango r WHERE r.accPublico = :accPublico"),
    @NamedQuery(name = "Rango.findByAccGrupal", query = "SELECT r FROM Rango r WHERE r.accGrupal = :accGrupal"),
    @NamedQuery(name = "Rango.findByAccProtegido", query = "SELECT r FROM Rango r WHERE r.accProtegido = :accProtegido"),
    @NamedQuery(name = "Rango.findByAccPrivado", query = "SELECT r FROM Rango r WHERE r.accPrivado = :accPrivado"),
    @NamedQuery(name = "Rango.findByColor", query = "SELECT r FROM Rango r WHERE r.color = :color")})
public class Rango implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rango")
    private Integer idRango;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acc_publico")
    private int accPublico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acc_grupal")
    private int accGrupal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acc_protegido")
    private int accProtegido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acc_privado")
    private int accPrivado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Usuario> usuarioCollection;

    public Rango() {
    }

    public Rango(Integer idRango) {
        this.idRango = idRango;
    }

    public Rango(Integer idRango, String descripcion, String detalle, int accPublico, int accGrupal, int accProtegido, int accPrivado, String color) {
        this.idRango = idRango;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.accPublico = accPublico;
        this.accGrupal = accGrupal;
        this.accProtegido = accProtegido;
        this.accPrivado = accPrivado;
        this.color = color;
    }

    public Integer getIdRango() {
        return idRango;
    }

    public void setIdRango(Integer idRango) {
        this.idRango = idRango;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getAccPublico() {
        return accPublico;
    }

    public void setAccPublico(int accPublico) {
        this.accPublico = accPublico;
    }

    public int getAccGrupal() {
        return accGrupal;
    }

    public void setAccGrupal(int accGrupal) {
        this.accGrupal = accGrupal;
    }

    public int getAccProtegido() {
        return accProtegido;
    }

    public void setAccProtegido(int accProtegido) {
        this.accProtegido = accProtegido;
    }

    public int getAccPrivado() {
        return accPrivado;
    }

    public void setAccPrivado(int accPrivado) {
        this.accPrivado = accPrivado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRango != null ? idRango.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rango)) {
            return false;
        }
        Rango other = (Rango) object;
        if ((this.idRango == null && other.idRango != null) || (this.idRango != null && !this.idRango.equals(other.idRango))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.tools.dto.Rango[ idRango=" + idRango + " ]";
    }
    
}
