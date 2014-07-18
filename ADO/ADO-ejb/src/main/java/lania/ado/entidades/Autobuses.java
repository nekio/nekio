
package lania.ado.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "autobuses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Autobuses.findAll", query = "SELECT a FROM Autobuses a"),
    @NamedQuery(name = "Autobuses.findByCodigo", query = "SELECT a FROM Autobuses a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Autobuses.findByLinea", query = "SELECT a FROM Autobuses a WHERE a.linea = :linea"),
    @NamedQuery(name = "Autobuses.findByEstatus", query = "SELECT a FROM Autobuses a WHERE a.estatus = :estatus"),
    @NamedQuery(name = "Autobuses.findByCapacidad", query = "SELECT a FROM Autobuses a WHERE a.capacidad = :capacidad")})
public class Autobuses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "linea")
    private String linea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "estatus")
    private String estatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidad")
    private int capacidad;

    public Autobuses() {
    }

    public Autobuses(String codigo) {
        this.codigo = codigo;
    }

    public Autobuses(String codigo, String linea, String estatus, int capacidad) {
        this.codigo = codigo;
        this.linea = linea;
        this.estatus = estatus;
        this.capacidad = capacidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autobuses)) {
            return false;
        }
        Autobuses other = (Autobuses) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lania.ado.entidades.Autobuses[ codigo=" + codigo + " ]";
    }
    
}
