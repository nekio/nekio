
package nekio.seriesweb.dto;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "enlace")
@NamedQueries({
    @NamedQuery(name = "Enlace.findAll", query = "SELECT e FROM Enlace e"),
    @NamedQuery(name = "Enlace.findByIdEnlace", query = "SELECT e FROM Enlace e WHERE e.idEnlace = :idEnlace"),
    @NamedQuery(name = "Enlace.findByEnlace", query = "SELECT e FROM Enlace e WHERE e.enlace = :enlace"),
    @NamedQuery(name = "Enlace.findByComentarios", query = "SELECT e FROM Enlace e WHERE e.comentarios = :comentarios")})
public class Enlace implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_enlace")
    private Integer idEnlace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "enlace")
    private String enlace;
    @Size(max = 20)
    @Column(name = "comentarios")
    private String comentarios;
    @JoinColumn(name = "id_serie", referencedColumnName = "id_serie")
    @ManyToOne
    private Serie idSerie;
    @JoinColumn(name = "id_episodio", referencedColumnName = "id_episodio")
    @ManyToOne
    private Episodio idEpisodio;
    @JoinColumn(name = "id_enlace_status", referencedColumnName = "id_enlace_status")
    @ManyToOne
    private EnlaceStatus idEnlaceStatus;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne
    private Proyecto idProyecto;

    public Enlace() {
    }

    public Enlace(Integer idEnlace) {
        this.idEnlace = idEnlace;
    }

    public Enlace(Integer idEnlace, String enlace) {
        this.idEnlace = idEnlace;
        this.enlace = enlace;
    }

    public Integer getIdEnlace() {
        return idEnlace;
    }

    public void setIdEnlace(Integer idEnlace) {
        this.idEnlace = idEnlace;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Serie getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
    }

    public Episodio getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(Episodio idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    public EnlaceStatus getIdEnlaceStatus() {
        return idEnlaceStatus;
    }

    public void setIdEnlaceStatus(EnlaceStatus idEnlaceStatus) {
        this.idEnlaceStatus = idEnlaceStatus;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnlace != null ? idEnlace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enlace)) {
            return false;
        }
        Enlace other = (Enlace) object;
        if ((this.idEnlace == null && other.idEnlace != null) || (this.idEnlace != null && !this.idEnlace.equals(other.idEnlace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.seriesweb.dto.Enlace[ idEnlace=" + idEnlace + " ]";
    }
    
}
