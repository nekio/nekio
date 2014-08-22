
package nekio.seriesweb.dto;

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
@Table(name = "emisora")
@NamedQueries({
    @NamedQuery(name = "Emisora.findAll", query = "SELECT e FROM Emisora e"),
    @NamedQuery(name = "Emisora.findByIdEmisora", query = "SELECT e FROM Emisora e WHERE e.idEmisora = :idEmisora"),
    @NamedQuery(name = "Emisora.findByIdImagen", query = "SELECT e FROM Emisora e WHERE e.idImagen = :idImagen"),
    @NamedQuery(name = "Emisora.findBySiglasEmisora", query = "SELECT e FROM Emisora e WHERE e.siglasEmisora = :siglasEmisora"),
    @NamedQuery(name = "Emisora.findByNombreEmisora", query = "SELECT e FROM Emisora e WHERE e.nombreEmisora = :nombreEmisora")})
public class Emisora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_emisora")
    private Integer idEmisora;
    @Column(name = "id_imagen")
    private Integer idImagen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "siglas_emisora")
    private String siglasEmisora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_emisora")
    private String nombreEmisora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmisora")
    private Collection<Serie> serieCollection;

    public Emisora() {
    }

    public Emisora(Integer idEmisora) {
        this.idEmisora = idEmisora;
    }

    public Emisora(Integer idEmisora, String siglasEmisora, String nombreEmisora) {
        this.idEmisora = idEmisora;
        this.siglasEmisora = siglasEmisora;
        this.nombreEmisora = nombreEmisora;
    }

    public Integer getIdEmisora() {
        return idEmisora;
    }

    public void setIdEmisora(Integer idEmisora) {
        this.idEmisora = idEmisora;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String getSiglasEmisora() {
        return siglasEmisora;
    }

    public void setSiglasEmisora(String siglasEmisora) {
        this.siglasEmisora = siglasEmisora;
    }

    public String getNombreEmisora() {
        return nombreEmisora;
    }

    public void setNombreEmisora(String nombreEmisora) {
        this.nombreEmisora = nombreEmisora;
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
        hash += (idEmisora != null ? idEmisora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emisora)) {
            return false;
        }
        Emisora other = (Emisora) object;
        if ((this.idEmisora == null && other.idEmisora != null) || (this.idEmisora != null && !this.idEmisora.equals(other.idEmisora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.seriesweb.dto.Emisora[ idEmisora=" + idEmisora + " ]";
    }
    
}
