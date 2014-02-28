/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rysi.sma.negocio.modelo;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "V_TIEMPO_PROMEDIO_RESOLUCION_TICKETS")
@NamedQueries({
    @NamedQuery(name = "VTiempoPromedioResolucionTickets.findAll", query = "SELECT v FROM VTiempoPromedioResolucionTickets v")})
public class VTiempoPromedioResolucionTickets implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name = "DIAS_PROMEDIO")
    private BigInteger diasPromedio;
    
    @Id
    @EmbeddedId
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public VTiempoPromedioResolucionTickets() {
    }

    public BigInteger getDiasPromedio() {
        return diasPromedio;
    }

    public void setDiasPromedio(BigInteger diasPromedio) {
        this.diasPromedio = diasPromedio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
