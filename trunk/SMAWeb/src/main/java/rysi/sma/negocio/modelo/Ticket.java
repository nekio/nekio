/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rysi.sma.negocio.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ivan
 */
@Entity
@Table(name = "TICKET")
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t")})
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TICKET")
    private Integer idTicket;
    
    @Size(max = 50)
    @Column(name = "FOLIO")
    private String folio;
    
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    @Column(name = "FECHA_CIERRE")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    
    @Column(name = "CALIFICACION")
    private Short calificacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "RESUELTO")
    private short resuelto;
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    
    @JoinColumn(name = "ID_PERSONAL_ATENCION", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idPersonalAtencion;
    
    @JoinColumn(name = "ID_TOPICO_AYUDA", referencedColumnName = "ID_TOPICO_AYUDA")
    @ManyToOne(optional = false)
    private TopicoAyuda idTopicoAyuda;
    
    @JoinColumn(name = "ID_NIVEL_ATENCION", referencedColumnName = "ID_NIVEL_ATENCION")
    @ManyToOne(optional = false)
    private NivelAtencion idNivelAtencion;
    
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne(optional = false)
    private EstadoTicket idEstado;
    
    @JoinColumn(name = "ID_DEPTO", referencedColumnName = "ID_DEPTO")
    @ManyToOne(optional = false)
    private Departamento idDepto;
    
    @JoinColumn(name = "ID_CAUSA_CIERRE_TICKET", referencedColumnName = "ID_CAUSA_CIERRE")
    @ManyToOne
    private CausaCierreTicket idCausaCierreTicket;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, Date fechaCreacion, short resuelto) {
        this.idTicket = idTicket;
        this.fechaCreacion = fechaCreacion;
        this.resuelto = resuelto;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Short getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Short calificacion) {
        this.calificacion = calificacion;
    }

    public short getResuelto() {
        return resuelto;
    }

    public boolean isResuelto(){
        if(resuelto == 1)
            return true;
        else
            return false;
    }

    public void setResuelto(short resuelto) {
        this.resuelto = resuelto;
    }
    
    public void setResuelto(boolean resuelto){
        if(resuelto)
            this.resuelto = 1;
        else
            this.resuelto = 0;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getIdPersonalAtencion() {
        return idPersonalAtencion;
    }

    public void setIdPersonalAtencion(Usuario idPersonalAtencion) {
        this.idPersonalAtencion = idPersonalAtencion;
    }

    public TopicoAyuda getIdTopicoAyuda() {
        return idTopicoAyuda;
    }

    public void setIdTopicoAyuda(TopicoAyuda idTopicoAyuda) {
        this.idTopicoAyuda = idTopicoAyuda;
    }

    public NivelAtencion getIdNivelAtencion() {
        return idNivelAtencion;
    }

    public void setIdNivelAtencion(NivelAtencion idNivelAtencion) {
        this.idNivelAtencion = idNivelAtencion;
    }

    public EstadoTicket getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoTicket idEstado) {
        this.idEstado = idEstado;
    }

    public Departamento getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Departamento idDepto) {
        this.idDepto = idDepto;
    }

    public CausaCierreTicket getIdCausaCierreTicket() {
        return idCausaCierreTicket;
    }

    public void setIdCausaCierreTicket(CausaCierreTicket idCausaCierreTicket) {
        this.idCausaCierreTicket = idCausaCierreTicket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rysi.sma.negocio.modelo.Ticket[ idTicket=" + idTicket + " ]";
    }
    
}
