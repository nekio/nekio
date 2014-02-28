/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rysi.sma.negocio.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "NIVEL_ATENCION")
@NamedQueries({
    @NamedQuery(name = "NivelAtencion.findAll", query = "SELECT n FROM NivelAtencion n")})
public class NivelAtencion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NIVEL_ATENCION")
    private Integer idNivelAtencion;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNivelAtencion")
    private Collection<Ticket> ticketCollection;

    public NivelAtencion() {
    }

    public NivelAtencion(Integer idNivelAtencion) {
        this.idNivelAtencion = idNivelAtencion;
    }

    public Integer getIdNivelAtencion() {
        return idNivelAtencion;
    }

    public void setIdNivelAtencion(Integer idNivelAtencion) {
        this.idNivelAtencion = idNivelAtencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivelAtencion != null ? idNivelAtencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelAtencion)) {
            return false;
        }
        NivelAtencion other = (NivelAtencion) object;
        if ((this.idNivelAtencion == null && other.idNivelAtencion != null) || (this.idNivelAtencion != null && !this.idNivelAtencion.equals(other.idNivelAtencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rysi.sma.negocio.modelo.NivelAtencion[ idNivelAtencion=" + idNivelAtencion + " ]";
    }
    
}
