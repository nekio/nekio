
package nekio.seriesweb.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "pendiente")
@NamedQueries({
    @NamedQuery(name = "Pendiente.findAll", query = "SELECT p FROM Pendiente p"),
    @NamedQuery(name = "Pendiente.findByIdPendiente", query = "SELECT p FROM Pendiente p WHERE p.idPendiente = :idPendiente"),
    @NamedQuery(name = "Pendiente.findByIdRango", query = "SELECT p FROM Pendiente p WHERE p.idRango = :idRango"),
    @NamedQuery(name = "Pendiente.findByPendiente", query = "SELECT p FROM Pendiente p WHERE p.pendiente = :pendiente"),
    @NamedQuery(name = "Pendiente.findByDetalle", query = "SELECT p FROM Pendiente p WHERE p.detalle = :detalle"),
    @NamedQuery(name = "Pendiente.findByFechaRevision", query = "SELECT p FROM Pendiente p WHERE p.fechaRevision = :fechaRevision"),
    @NamedQuery(name = "Pendiente.findByComentarios", query = "SELECT p FROM Pendiente p WHERE p.comentarios = :comentarios")})
public class Pendiente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pendiente")
    private Integer idPendiente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rango")
    private int idRango;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "pendiente")
    private String pendiente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_revision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRevision;
    @Lob
    @Size(max = 65535)
    @Column(name = "enlace")
    private String enlace;
    @Size(max = 255)
    @Column(name = "comentarios")
    private String comentarios;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne
    private Proyecto idProyecto;
    @JoinColumn(name = "id_episodio", referencedColumnName = "id_episodio")
    @ManyToOne
    private Episodio idEpisodio;

    public Pendiente() {
    }

    public Pendiente(Integer idPendiente) {
        this.idPendiente = idPendiente;
    }

    public Pendiente(Integer idPendiente, int idRango, String pendiente, String detalle, Date fechaRevision) {
        this.idPendiente = idPendiente;
        this.idRango = idRango;
        this.pendiente = pendiente;
        this.detalle = detalle;
        this.fechaRevision = fechaRevision;
    }

    public Integer getIdPendiente() {
        return idPendiente;
    }

    public void setIdPendiente(Integer idPendiente) {
        this.idPendiente = idPendiente;
    }

    public int getIdRango() {
        return idRango;
    }

    public void setIdRango(int idRango) {
        this.idRango = idRango;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
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

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Episodio getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(Episodio idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPendiente != null ? idPendiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pendiente)) {
            return false;
        }
        Pendiente other = (Pendiente) object;
        if ((this.idPendiente == null && other.idPendiente != null) || (this.idPendiente != null && !this.idPendiente.equals(other.idPendiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.seriesweb.dto.Pendiente[ idPendiente=" + idPendiente + " ]";
    }
    
}
