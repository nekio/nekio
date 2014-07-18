
package lania.ado.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "itinerarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Itinerarios.findAll", query = "SELECT i FROM Itinerarios i"),
    @NamedQuery(name = "Itinerarios.findById", query = "SELECT i FROM Itinerarios i WHERE i.id = :id"),
    @NamedQuery(name = "Itinerarios.findByHorasalida", query = "SELECT i FROM Itinerarios i WHERE i.horasalida = :horasalida"),
    @NamedQuery(name = "Itinerarios.findByEstimadohrs", query = "SELECT i FROM Itinerarios i WHERE i.estimadohrs = :estimadohrs"),
    @NamedQuery(name = "Itinerarios.findByEstimadomins", query = "SELECT i FROM Itinerarios i WHERE i.estimadomins = :estimadomins")})
public class Itinerarios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "horasalida")
    @Temporal(TemporalType.TIME)
    private Date horasalida;
    
    @Column(name = "estimadohrs")
    private Integer estimadohrs;
    
    @Column(name = "estimadomins")
    private Integer estimadomins;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itinerario")
    private Collection<Corridas> corridasCollection;
    
    @JoinColumn(name = "origen", referencedColumnName = "idterminal")
    @ManyToOne(optional = false)
    private Terminales origen;
    
    @JoinColumn(name = "destino", referencedColumnName = "idterminal")
    @ManyToOne(optional = false)
    private Terminales destino;

    public Itinerarios() {
    }

    public Itinerarios(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(Date horasalida) {
        this.horasalida = horasalida;
    }

    public Integer getEstimadohrs() {
        return estimadohrs;
    }

    public void setEstimadohrs(Integer estimadohrs) {
        this.estimadohrs = estimadohrs;
    }

    public Integer getEstimadomins() {
        return estimadomins;
    }

    public void setEstimadomins(Integer estimadomins) {
        this.estimadomins = estimadomins;
    }

    @XmlTransient
    public Collection<Corridas> getCorridasCollection() {
        return corridasCollection;
    }

    public void setCorridasCollection(Collection<Corridas> corridasCollection) {
        this.corridasCollection = corridasCollection;
    }

    public Terminales getOrigen() {
        return origen;
    }

    public void setOrigen(Terminales origen) {
        this.origen = origen;
    }

    public Terminales getDestino() {
        return destino;
    }

    public void setDestino(Terminales destino) {
        this.destino = destino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Itinerarios)) {
            return false;
        }
        Itinerarios other = (Itinerarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lania.ado.entidades.Itinerarios[ id=" + id + " ]";
    }
    
}
