
package nekio.seriesweb.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "episodio")
@NamedQueries({
    @NamedQuery(name = "Episodio.findAll", query = "SELECT e FROM Episodio e"),
    @NamedQuery(name = "Episodio.findByIdEpisodio", query = "SELECT e FROM Episodio e WHERE e.idEpisodio = :idEpisodio"),
    @NamedQuery(name = "Episodio.findByTemporada", query = "SELECT e FROM Episodio e WHERE e.temporada = :temporada"),
    @NamedQuery(name = "Episodio.findByNumEpisodioSerie", query = "SELECT e FROM Episodio e WHERE e.numEpisodioSerie = :numEpisodioSerie"),
    @NamedQuery(name = "Episodio.findByNumEpisodioTemp", query = "SELECT e FROM Episodio e WHERE e.numEpisodioTemp = :numEpisodioTemp"),
    @NamedQuery(name = "Episodio.findByDescripcion", query = "SELECT e FROM Episodio e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Episodio.findByTituloAlternativo", query = "SELECT e FROM Episodio e WHERE e.tituloAlternativo = :tituloAlternativo")})
public class Episodio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_episodio")
    private Integer idEpisodio;
    @Column(name = "temporada")
    private Integer temporada;
    @Column(name = "num_episodio_serie")
    private Integer numEpisodioSerie;
    @Column(name = "num_episodio_temp")
    private Integer numEpisodioTemp;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "titulo_alternativo")
    private String tituloAlternativo;
    @OneToMany(mappedBy = "idEpisodio")
    private Collection<Password> passwordCollection;
    @OneToMany(mappedBy = "idEpisodio")
    private Collection<Pendiente> pendienteCollection;
    @OneToMany(mappedBy = "idEpisodio")
    private Collection<Enlace> enlaceCollection;
    @OneToMany(mappedBy = "idEpisodio")
    private Collection<Obtenido> obtenidoCollection;
    @JoinColumn(name = "id_serie", referencedColumnName = "id_serie")
    @ManyToOne(optional = false)
    private Serie idSerie;
    @JoinColumn(name = "id_tipo_episodio", referencedColumnName = "id_tipo_episodio")
    @ManyToOne
    private TipoEpisodio idTipoEpisodio;

    public Episodio() {
    }

    public Episodio(Integer idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    public Integer getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(Integer idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getNumEpisodioSerie() {
        return numEpisodioSerie;
    }

    public void setNumEpisodioSerie(Integer numEpisodioSerie) {
        this.numEpisodioSerie = numEpisodioSerie;
    }

    public Integer getNumEpisodioTemp() {
        return numEpisodioTemp;
    }

    public void setNumEpisodioTemp(Integer numEpisodioTemp) {
        this.numEpisodioTemp = numEpisodioTemp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTituloAlternativo() {
        return tituloAlternativo;
    }

    public void setTituloAlternativo(String tituloAlternativo) {
        this.tituloAlternativo = tituloAlternativo;
    }

    public Collection<Password> getPasswordCollection() {
        return passwordCollection;
    }

    public void setPasswordCollection(Collection<Password> passwordCollection) {
        this.passwordCollection = passwordCollection;
    }

    public Collection<Pendiente> getPendienteCollection() {
        return pendienteCollection;
    }

    public void setPendienteCollection(Collection<Pendiente> pendienteCollection) {
        this.pendienteCollection = pendienteCollection;
    }

    public Collection<Enlace> getEnlaceCollection() {
        return enlaceCollection;
    }

    public void setEnlaceCollection(Collection<Enlace> enlaceCollection) {
        this.enlaceCollection = enlaceCollection;
    }

    public Collection<Obtenido> getObtenidoCollection() {
        return obtenidoCollection;
    }

    public void setObtenidoCollection(Collection<Obtenido> obtenidoCollection) {
        this.obtenidoCollection = obtenidoCollection;
    }

    public Serie getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
    }

    public TipoEpisodio getIdTipoEpisodio() {
        return idTipoEpisodio;
    }

    public void setIdTipoEpisodio(TipoEpisodio idTipoEpisodio) {
        this.idTipoEpisodio = idTipoEpisodio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEpisodio != null ? idEpisodio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Episodio)) {
            return false;
        }
        Episodio other = (Episodio) object;
        if ((this.idEpisodio == null && other.idEpisodio != null) || (this.idEpisodio != null && !this.idEpisodio.equals(other.idEpisodio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.seriesweb.dto.Episodio[ idEpisodio=" + idEpisodio + " ]";
    }
    
}
