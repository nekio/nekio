package nekio.tools.dto;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Carpeta.findAll", query = "SELECT c FROM Carpeta c"),
    @NamedQuery(name = "Carpeta.findByIdCarpeta", query = "SELECT c FROM Carpeta c WHERE c.idCarpeta = :idCarpeta"),
    @NamedQuery(name = "Carpeta.findByDescripcion", query = "SELECT c FROM Carpeta c WHERE c.descripcion = :descripcion")})
public class Carpeta implements Serializable {
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
    private Collection<Imagen> imagenCollection;

    public Carpeta() {
    }

    public Carpeta(Integer idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public Carpeta(Integer idCarpeta, String descripcion) {
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

    public Collection<Imagen> getImagenCollection() {
        return imagenCollection;
    }

    public void setImagenCollection(Collection<Imagen> imagenCollection) {
        this.imagenCollection = imagenCollection;
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
        if (!(object instanceof Carpeta)) {
            return false;
        }
        Carpeta other = (Carpeta) object;
        if ((this.idCarpeta == null && other.idCarpeta != null) || (this.idCarpeta != null && !this.idCarpeta.equals(other.idCarpeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.tools.dto.Carpeta[ idCarpeta=" + idCarpeta + " ]";
    }
    
}
