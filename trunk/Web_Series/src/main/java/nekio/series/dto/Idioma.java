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
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i"),
    @NamedQuery(name = "Idioma.findByIdIdioma", query = "SELECT i FROM Idioma i WHERE i.idIdioma = :idIdioma"),
    @NamedQuery(name = "Idioma.findByDescripcion", query = "SELECT i FROM Idioma i WHERE i.descripcion = :descripcion")})
public class Idioma implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_idioma")
    private Integer idIdioma;
    @Size(max = 20)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdioma")
    private Collection<Proyecto> proyectoCollection;
    @OneToMany(mappedBy = "idSubtitulo")
    private Collection<Obtenido> obtenidoCollection;
    @OneToMany(mappedBy = "idAudio")
    private Collection<Obtenido> obtenidoCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIdiomaOrigen")
    private Collection<Serie> serieCollection;

    public Idioma() {
    }

    public Idioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
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

    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    public Collection<Obtenido> getObtenidoCollection() {
        return obtenidoCollection;
    }

    public void setObtenidoCollection(Collection<Obtenido> obtenidoCollection) {
        this.obtenidoCollection = obtenidoCollection;
    }

    public Collection<Obtenido> getObtenidoCollection1() {
        return obtenidoCollection1;
    }

    public void setObtenidoCollection1(Collection<Obtenido> obtenidoCollection1) {
        this.obtenidoCollection1 = obtenidoCollection1;
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
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) object;
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.Idioma[ idIdioma=" + idIdioma + " ]";
    }
    
}
