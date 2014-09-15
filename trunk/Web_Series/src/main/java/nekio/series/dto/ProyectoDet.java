package nekio.series.dto;

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

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "proyecto_det")
@NamedQueries({
    @NamedQuery(name = "ProyectoDet.findAll", query = "SELECT p FROM ProyectoDet p"),
    @NamedQuery(name = "ProyectoDet.findByIdProyectoDet", query = "SELECT p FROM ProyectoDet p WHERE p.idProyectoDet = :idProyectoDet")})
public class ProyectoDet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto_det")
    private Integer idProyectoDet;
    @JoinColumn(name = "id_tipo_colaboracion", referencedColumnName = "id_tipo_colaboracion")
    @ManyToOne(optional = false)
    private TipoColaboracion idTipoColaboracion;
    @JoinColumn(name = "id_colaborador", referencedColumnName = "id_colaborador")
    @ManyToOne(optional = false)
    private Colaborador idColaborador;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne(optional = false)
    private Proyecto idProyecto;

    public ProyectoDet() {
    }

    public ProyectoDet(Integer idProyectoDet) {
        this.idProyectoDet = idProyectoDet;
    }

    public Integer getIdProyectoDet() {
        return idProyectoDet;
    }

    public void setIdProyectoDet(Integer idProyectoDet) {
        this.idProyectoDet = idProyectoDet;
    }

    public TipoColaboracion getIdTipoColaboracion() {
        return idTipoColaboracion;
    }

    public void setIdTipoColaboracion(TipoColaboracion idTipoColaboracion) {
        this.idTipoColaboracion = idTipoColaboracion;
    }

    public Colaborador getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Colaborador idColaborador) {
        this.idColaborador = idColaborador;
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
        hash += (idProyectoDet != null ? idProyectoDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyectoDet)) {
            return false;
        }
        ProyectoDet other = (ProyectoDet) object;
        if ((this.idProyectoDet == null && other.idProyectoDet != null) || (this.idProyectoDet != null && !this.idProyectoDet.equals(other.idProyectoDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.ProyectoDet[ idProyectoDet=" + idProyectoDet + " ]";
    }
    
}
