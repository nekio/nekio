
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
@Table(name = "genero")
@NamedQueries({
    @NamedQuery(name = "Genero.findAll", query = "SELECT g FROM Genero g"),
    @NamedQuery(name = "Genero.findByIdGenero", query = "SELECT g FROM Genero g WHERE g.idGenero = :idGenero"),
    @NamedQuery(name = "Genero.findByIdRango", query = "SELECT g FROM Genero g WHERE g.idRango = :idRango"),
    @NamedQuery(name = "Genero.findByGenero", query = "SELECT g FROM Genero g WHERE g.genero = :genero"),
    @NamedQuery(name = "Genero.findByDescripcion", query = "SELECT g FROM Genero g WHERE g.descripcion = :descripcion")})
public class Genero implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genero")
    private Integer idGenero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rango")
    private int idRango;
    @Size(max = 20)
    @Column(name = "genero")
    private String genero;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idGenero2")
    private Collection<Serie> serieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGenero1")
    private Collection<Serie> serieCollection1;
    @OneToMany(mappedBy = "idGenero4")
    private Collection<Serie> serieCollection2;
    @OneToMany(mappedBy = "idGenero3")
    private Collection<Serie> serieCollection3;

    public Genero() {
    }

    public Genero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Genero(Integer idGenero, int idRango) {
        this.idGenero = idGenero;
        this.idRango = idRango;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdRango() {
        return idRango;
    }

    public void setIdRango(int idRango) {
        this.idRango = idRango;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public Collection<Serie> getSerieCollection1() {
        return serieCollection1;
    }

    public void setSerieCollection1(Collection<Serie> serieCollection1) {
        this.serieCollection1 = serieCollection1;
    }

    public Collection<Serie> getSerieCollection2() {
        return serieCollection2;
    }

    public void setSerieCollection2(Collection<Serie> serieCollection2) {
        this.serieCollection2 = serieCollection2;
    }

    public Collection<Serie> getSerieCollection3() {
        return serieCollection3;
    }

    public void setSerieCollection3(Collection<Serie> serieCollection3) {
        this.serieCollection3 = serieCollection3;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGenero != null ? idGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Genero)) {
            return false;
        }
        Genero other = (Genero) object;
        if ((this.idGenero == null && other.idGenero != null) || (this.idGenero != null && !this.idGenero.equals(other.idGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.seriesweb.dto.Genero[ idGenero=" + idGenero + " ]";
    }
    
}
