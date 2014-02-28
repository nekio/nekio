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
@Table(name = "USUARIO")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Size(max = 50)
    @Column(name = "APELLIDO_P")
    private String apellidoP;
    
    @Size(max = 50)
    @Column(name = "APELLIDO_M")
    private String apellidoM;
    
    @Size(max = 200)
    @Column(name = "CONTACTO")
    private String contacto;
    
    @Size(max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private short activo;
    
    @JoinColumn(name = "ID_TIPO_USUARIO", referencedColumnName = "ID_TIPO_USUARIO")
    @ManyToOne
    private TipoUsuario idTipoUsuario;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Ticket> ticketCollection;
    
    @OneToMany(mappedBy = "idPersonalAtencion")
    private Collection<Ticket> ticketCollection1;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, short activo) {
        this.idUsuario = idUsuario;
        this.activo = activo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getActivo() {
        return activo;
    }
    
    public boolean isActivo() {
        if(this.activo == 1)
            return true;
        else
            return false;
    }

    public void setActivo(short activo) {
        this.activo = activo;
    }
    
    public void setActivo(boolean activo) {
        if(activo)
            this.activo = 1;
        else
            this.activo = 0;
    }
    
    public TipoUsuario getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(TipoUsuario idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public Collection<Ticket> getTicketCollection1() {
        return ticketCollection1;
    }

    public void setTicketCollection1(Collection<Ticket> ticketCollection1) {
        this.ticketCollection1 = ticketCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rysi.sma.negocio.modelo.Usuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
