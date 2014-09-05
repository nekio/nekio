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
 * @author Nekio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Clasificacion.findAll", query = "SELECT c FROM Clasificacion c"),
    @NamedQuery(name = "Clasificacion.findByIdClasificacion", query = "SELECT c FROM Clasificacion c WHERE c.idClasificacion = :idClasificacion"),
    @NamedQuery(name = "Clasificacion.findBySiglas", query = "SELECT c FROM Clasificacion c WHERE c.siglas = :siglas"),
    @NamedQuery(name = "Clasificacion.findByDescripcion", query = "SELECT c FROM Clasificacion c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Clasificacion.findByComentarios", query = "SELECT c FROM Clasificacion c WHERE c.comentarios = :comentarios"),
    @NamedQuery(name = "Clasificacion.findByMinimoEdad", query = "SELECT c FROM Clasificacion c WHERE c.minimoEdad = :minimoEdad")})
public class Clasificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_clasificacion")
    private Integer idClasificacion;
    
    @Size(max = 10)
    private String siglas;
    
    @Size(max = 20)
    private String descripcion;
    
    @Size(max = 255)
    private String comentarios;
    
    @Column(name = "minimo_edad")
    private Integer minimoEdad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClasificacion")
    private Collection<Serie> serieCollection;

    public Clasificacion() {
    }

    public Clasificacion(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public Integer getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getMinimoEdad() {
        return minimoEdad;
    }

    public void setMinimoEdad(Integer minimoEdad) {
        this.minimoEdad = minimoEdad;
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
        hash += (idClasificacion != null ? idClasificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificacion)) {
            return false;
        }
        Clasificacion other = (Clasificacion) object;
        if ((this.idClasificacion == null && other.idClasificacion != null) || (this.idClasificacion != null && !this.idClasificacion.equals(other.idClasificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String detalle = null;
        
        if(minimoEdad == null)
            detalle = descripcion;
        else
            detalle = minimoEdad.toString() + "+";
        
        return siglas + " (" + detalle + ")";
    }
    
}
