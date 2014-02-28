/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rysi.sma.negocio.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "CAUSA_CIERRE_TICKET")
@NamedQueries({
    @NamedQuery(name = "CausaCierreTicket.findAll", query = "SELECT c FROM CausaCierreTicket c")})
public class CausaCierreTicket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CAUSA_CIERRE")
    private Integer idCausaCierre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idCausaCierreTicket")
    private Collection<Ticket> ticketCollection;

    public CausaCierreTicket() {
    }

    public CausaCierreTicket(Integer idCausaCierre) {
        this.idCausaCierre = idCausaCierre;
    }

    public CausaCierreTicket(Integer idCausaCierre, String descripcion) {
        this.idCausaCierre = idCausaCierre;
        this.descripcion = descripcion;
    }

    public Integer getIdCausaCierre() {
        return idCausaCierre;
    }

    public void setIdCausaCierre(Integer idCausaCierre) {
        this.idCausaCierre = idCausaCierre;
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
        hash += (idCausaCierre != null ? idCausaCierre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CausaCierreTicket)) {
            return false;
        }
        CausaCierreTicket other = (CausaCierreTicket) object;
        if ((this.idCausaCierre == null && other.idCausaCierre != null) || (this.idCausaCierre != null && !this.idCausaCierre.equals(other.idCausaCierre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rysi.sma.negocio.modelo.CausaCierreTicket[ idCausaCierre=" + idCausaCierre + " ]";
    }
    
}
