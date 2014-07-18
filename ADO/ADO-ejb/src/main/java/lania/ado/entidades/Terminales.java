
package lania.ado.entidades;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "terminales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Terminales.findAll", query = "SELECT t FROM Terminales t"),
    @NamedQuery(name = "Terminales.findByIdterminal", query = "SELECT t FROM Terminales t WHERE t.idterminal = :idterminal"),
    @NamedQuery(name = "Terminales.findByNombre", query = "SELECT t FROM Terminales t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Terminales.findByCodigoterminal", query = "SELECT t FROM Terminales t WHERE t.codigoterminal = :codigoterminal")})
public class Terminales implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idterminal")
    private Integer idterminal;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre")
    private String nombre;
    
    @Size(max = 50)
    @Column(name = "codigoterminal")
    private String codigoterminal;
    
    @JoinColumn(name = "idciudad", referencedColumnName = "idciudad")
    @ManyToOne(optional = false)
    private Ciudades idciudad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origen")
    private Collection<Itinerarios> itinerariosCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destino")
    private Collection<Itinerarios> itinerariosCollection1;

    public Terminales() {
    }

    public Terminales(Integer idterminal) {
        this.idterminal = idterminal;
    }

    public Terminales(Integer idterminal, String nombre) {
        this.idterminal = idterminal;
        this.nombre = nombre;
    }

    public Integer getIdterminal() {
        return idterminal;
    }

    public void setIdterminal(Integer idterminal) {
        this.idterminal = idterminal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoterminal() {
        return codigoterminal;
    }

    public void setCodigoterminal(String codigoterminal) {
        this.codigoterminal = codigoterminal;
    }

    public Ciudades getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(Ciudades idciudad) {
        this.idciudad = idciudad;
    }

    @XmlTransient
    public Collection<Itinerarios> getItinerariosCollection() {
        return itinerariosCollection;
    }

    public void setItinerariosCollection(Collection<Itinerarios> itinerariosCollection) {
        this.itinerariosCollection = itinerariosCollection;
    }

    @XmlTransient
    public Collection<Itinerarios> getItinerariosCollection1() {
        return itinerariosCollection1;
    }

    public void setItinerariosCollection1(Collection<Itinerarios> itinerariosCollection1) {
        this.itinerariosCollection1 = itinerariosCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idterminal != null ? idterminal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Terminales)) {
            return false;
        }
        Terminales other = (Terminales) object;
        if ((this.idterminal == null && other.idterminal != null) || (this.idterminal != null && !this.idterminal.equals(other.idterminal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "lania.ado.entidades.Terminales[ idterminal=" + idterminal + "("+nombre+")" + " ]";
        return idterminal + " - "+nombre;
    }
    
}
