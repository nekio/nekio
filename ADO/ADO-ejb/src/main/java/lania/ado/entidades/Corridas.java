
package lania.ado.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "corridas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Corridas.findAll", query = "SELECT c FROM Corridas c"),
    @NamedQuery(name = "Corridas.findByFolio", query = "SELECT c FROM Corridas c WHERE c.folio = :folio"),
    @NamedQuery(name = "Corridas.findByFecha", query = "SELECT c FROM Corridas c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Corridas.findByServicio", query = "SELECT c FROM Corridas c WHERE c.servicio = :servicio"),
    @NamedQuery(name = "Corridas.findByEstatus", query = "SELECT c FROM Corridas c WHERE c.estatus = :estatus"),
    @NamedQuery(name = "Corridas.findByPrecio", query = "SELECT c FROM Corridas c WHERE c.precio = :precio"),
    @NamedQuery(name = "Corridas.findByIva", query = "SELECT c FROM Corridas c WHERE c.iva = :iva"),
    @NamedQuery(name = "Corridas.findByTotal", query = "SELECT c FROM Corridas c WHERE c.total = :total"),
    @NamedQuery(name = "Corridas.findByDisponibles", query = "SELECT c FROM Corridas c WHERE c.disponibles = :disponibles")})
public class Corridas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "folio")
    private String folio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "servicio")
    private String servicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "estatus")
    private String estatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private BigDecimal iva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disponibles")
    private int disponibles;
    @OneToMany(mappedBy = "corrida")
    private Collection<Boletos> boletosCollection;
    @JoinColumn(name = "itinerario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Itinerarios itinerario;

    public Corridas() {
    }

    public Corridas(String folio) {
        this.folio = folio;
    }

    public Corridas(String folio, Date fecha, String servicio, String estatus, BigDecimal precio, BigDecimal iva, BigDecimal total, int disponibles) {
        this.folio = folio;
        this.fecha = fecha;
        this.servicio = servicio;
        this.estatus = estatus;
        this.precio = precio;
        this.iva = iva;
        this.total = total;
        this.disponibles = disponibles;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    @XmlTransient
    public Collection<Boletos> getBoletosCollection() {
        return boletosCollection;
    }

    public void setBoletosCollection(Collection<Boletos> boletosCollection) {
        this.boletosCollection = boletosCollection;
    }

    public Itinerarios getItinerario() {
        return itinerario;
    }

    public void setItinerario(Itinerarios itinerario) {
        this.itinerario = itinerario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (folio != null ? folio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Corridas)) {
            return false;
        }
        Corridas other = (Corridas) object;
        if ((this.folio == null && other.folio != null) || (this.folio != null && !this.folio.equals(other.folio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lania.ado.entidades.Corridas[ folio=" + folio + " ]";
    }
    
}
