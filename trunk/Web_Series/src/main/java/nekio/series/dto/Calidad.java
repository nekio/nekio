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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "Calidad.findAll", query = "SELECT c FROM Calidad c"),
    @NamedQuery(name = "Calidad.findByIdCalidad", query = "SELECT c FROM Calidad c WHERE c.idCalidad = :idCalidad"),
    @NamedQuery(name = "Calidad.findByDescripcion", query = "SELECT c FROM Calidad c WHERE c.descripcion = :descripcion")})
public class Calidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calidad")
    private Integer idCalidad;
    @Size(max = 20)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCalidad")
    private Collection<Proyecto> proyectoCollection;
    @JoinColumn(name = "id_rango", referencedColumnName = "id_rango")
    @ManyToOne(optional = false)
    private XRango idRango;

    public Calidad() {
    }

    public Calidad(Integer idCalidad) {
        this.idCalidad = idCalidad;
    }

    public Integer getIdCalidad() {
        return idCalidad;
    }

    public void setIdCalidad(Integer idCalidad) {
        this.idCalidad = idCalidad;
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

    public XRango getIdRango() {
        return idRango;
    }

    public void setIdRango(XRango idRango) {
        this.idRango = idRango;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalidad != null ? idCalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calidad)) {
            return false;
        }
        Calidad other = (Calidad) object;
        if ((this.idCalidad == null && other.idCalidad != null) || (this.idCalidad != null && !this.idCalidad.equals(other.idCalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.Calidad[ idCalidad=" + idCalidad + " ]";
    }
    
}
