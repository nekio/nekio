/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rysi.sma.negocio.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "V_SOLICITUDES_TICKETS_DEPTO")
@NamedQueries({
    @NamedQuery(name = "VSolicitudesTicketsDepto.findAll", query = "SELECT v FROM VSolicitudesTicketsDepto v")})
public class VSolicitudesTicketsDepto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_TICKETS")
    private int numTickets;

    @Id
    @EmbeddedId
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "MES_DEPTO")
    private String mesDepto;

    public VSolicitudesTicketsDepto() {
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public String getMesDepto() {
        return mesDepto;
    }

    public void setMesDepto(String mesDepto) {
        this.mesDepto = mesDepto;
    }
}
