
package lania.ado.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "boletos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boletos.findAll", query = "SELECT b FROM Boletos b"),
    @NamedQuery(name = "Boletos.findByFolio", query = "SELECT b FROM Boletos b WHERE b.folio = :folio"),
    @NamedQuery(name = "Boletos.findByTipoboleto", query = "SELECT b FROM Boletos b WHERE b.tipoboleto = :tipoboleto"),
    @NamedQuery(name = "Boletos.findByPasajero", query = "SELECT b FROM Boletos b WHERE b.pasajero = :pasajero"),
    @NamedQuery(name = "Boletos.findByAsiento", query = "SELECT b FROM Boletos b WHERE b.asiento = :asiento"),
    @NamedQuery(name = "Boletos.findByPrecio", query = "SELECT b FROM Boletos b WHERE b.precio = :precio"),
    @NamedQuery(name = "Boletos.findByIva", query = "SELECT b FROM Boletos b WHERE b.iva = :iva"),
    @NamedQuery(name = "Boletos.findByEstatus", query = "SELECT b FROM Boletos b WHERE b.estatus = :estatus")})
public class Boletos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "folio")
    private String folio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipoboleto")
    private int tipoboleto;
    @Size(max = 100)
    @Column(name = "pasajero")
    private String pasajero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "asiento")
    private int asiento;
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
    @Size(min = 1, max = 9)
    @Column(name = "estatus")
    private String estatus;
    @JoinColumn(name = "corrida", referencedColumnName = "folio")
    @ManyToOne
    private Corridas corrida;

    public Boletos() {
    }

    public Boletos(String folio) {
        this.folio = folio;
    }

    public Boletos(String folio, int tipoboleto, int asiento, BigDecimal precio, BigDecimal iva, String estatus) {
        this.folio = folio;
        this.tipoboleto = tipoboleto;
        this.asiento = asiento;
        this.precio = precio;
        this.iva = iva;
        this.estatus = estatus;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public int getTipoboleto() {
        return tipoboleto;
    }

    public void setTipoboleto(int tipoboleto) {
        this.tipoboleto = tipoboleto;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Corridas getCorrida() {
        return corrida;
    }

    public void setCorrida(Corridas corrida) {
        this.corrida = corrida;
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
        if (!(object instanceof Boletos)) {
            return false;
        }
        Boletos other = (Boletos) object;
        if ((this.folio == null && other.folio != null) || (this.folio != null && !this.folio.equals(other.folio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lania.ado.entidades.Boletos[ folio=" + folio + " ]";
    }
    
}
