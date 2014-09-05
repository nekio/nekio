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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a"),
    @NamedQuery(name = "Almacen.findByIdAlmacen", query = "SELECT a FROM Almacen a WHERE a.idAlmacen = :idAlmacen"),
    @NamedQuery(name = "Almacen.findByDescripcion", query = "SELECT a FROM Almacen a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Almacen.findByProposito", query = "SELECT a FROM Almacen a WHERE a.proposito = :proposito"),
    @NamedQuery(name = "Almacen.findByComentarios", query = "SELECT a FROM Almacen a WHERE a.comentarios = :comentarios"),
    @NamedQuery(name = "Almacen.findByCapacidad", query = "SELECT a FROM Almacen a WHERE a.capacidad = :capacidad"),
    @NamedQuery(name = "Almacen.findByEspacioLibre", query = "SELECT a FROM Almacen a WHERE a.espacioLibre = :espacioLibre")})
public class Almacen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_almacen")
    private Integer idAlmacen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String descripcion;
    @Size(max = 100)
    private String proposito;
    @Size(max = 200)
    private String comentarios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String capacidad;
    @Size(max = 20)
    @Column(name = "espacio_libre")
    private String espacioLibre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlmacen")
    private Collection<Obtenido> obtenidoCollection;

    public Almacen() {
    }

    public Almacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Almacen(Integer idAlmacen, String descripcion, String capacidad) {
        this.idAlmacen = idAlmacen;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProposito() {
        return proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getEspacioLibre() {
        return espacioLibre;
    }

    public void setEspacioLibre(String espacioLibre) {
        this.espacioLibre = espacioLibre;
    }

    public Collection<Obtenido> getObtenidoCollection() {
        return obtenidoCollection;
    }

    public void setObtenidoCollection(Collection<Obtenido> obtenidoCollection) {
        this.obtenidoCollection = obtenidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlmacen != null ? idAlmacen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.idAlmacen == null && other.idAlmacen != null) || (this.idAlmacen != null && !this.idAlmacen.equals(other.idAlmacen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.Almacen[ idAlmacen=" + idAlmacen + " ]";
    }
    
}
