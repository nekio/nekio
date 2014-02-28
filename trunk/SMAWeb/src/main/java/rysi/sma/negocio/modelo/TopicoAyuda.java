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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TOPICO_AYUDA")
@NamedQueries({
    @NamedQuery(name = "TopicoAyuda.findAll", query = "SELECT t FROM TopicoAyuda t")})
public class TopicoAyuda implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TOPICO_AYUDA")
    private Integer idTopicoAyuda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTopicoAyuda")
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "ID_DEPTO", referencedColumnName = "ID_DEPTO")
    @ManyToOne(optional = false)
    private Departamento idDepto;

    public TopicoAyuda() {
    }

    public TopicoAyuda(Integer idTopicoAyuda) {
        this.idTopicoAyuda = idTopicoAyuda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdTopicoAyuda() {
        return idTopicoAyuda;
    }

    public void setIdTopicoAyuda(Integer idTopicoAyuda) {
        this.idTopicoAyuda = idTopicoAyuda;
    }

    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public Departamento getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Departamento idDepto) {
        this.idDepto = idDepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTopicoAyuda != null ? idTopicoAyuda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TopicoAyuda)) {
            return false;
        }
        TopicoAyuda other = (TopicoAyuda) object;
        if ((this.idTopicoAyuda == null && other.idTopicoAyuda != null) || (this.idTopicoAyuda != null && !this.idTopicoAyuda.equals(other.idTopicoAyuda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rysi.sma.negocio.modelo.TopicoAyuda[ idTopicoAyuda=" + idTopicoAyuda + " ]";
    }
    
}
