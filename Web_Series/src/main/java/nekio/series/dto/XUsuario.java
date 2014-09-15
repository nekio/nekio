package nekio.series.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "x_usuario")
@NamedQueries({
    @NamedQuery(name = "XUsuario.findAll", query = "SELECT x FROM XUsuario x"),
    @NamedQuery(name = "XUsuario.findByIdUsuario", query = "SELECT x FROM XUsuario x WHERE x.idUsuario = :idUsuario"),
    @NamedQuery(name = "XUsuario.findByNombre", query = "SELECT x FROM XUsuario x WHERE x.nombre = :nombre"),
    @NamedQuery(name = "XUsuario.findByNick", query = "SELECT x FROM XUsuario x WHERE x.nick = :nick"),
    @NamedQuery(name = "XUsuario.findByAutogenerado", query = "SELECT x FROM XUsuario x WHERE x.autogenerado = :autogenerado"),
    @NamedQuery(name = "XUsuario.findByAcceso", query = "SELECT x FROM XUsuario x WHERE x.acceso = :acceso"),
    @NamedQuery(name = "XUsuario.findByContacto", query = "SELECT x FROM XUsuario x WHERE x.contacto = :contacto"),
    @NamedQuery(name = "XUsuario.findByFechaRegistro", query = "SELECT x FROM XUsuario x WHERE x.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "XUsuario.findByUltimoAcceso", query = "SELECT x FROM XUsuario x WHERE x.ultimoAcceso = :ultimoAcceso"),
    @NamedQuery(name = "XUsuario.findByActivo", query = "SELECT x FROM XUsuario x WHERE x.activo = :activo")})
public class XUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 100)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String nick;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String autogenerado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 33)
    private String acceso;
    @Size(max = 50)
    private String contacto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ultimo_acceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;
    @Basic(optional = false)
    @NotNull
    private int activo;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<XBitacora> xBitacoraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "xUsuario")
    private Collection<MensajePrivado> mensajePrivadoCollection;
    @JoinColumn(name = "id_idioma", referencedColumnName = "id_idioma")
    @ManyToOne(optional = false)
    private XIdioma idIdioma;
    @JoinColumn(name = "id_rango", referencedColumnName = "id_rango")
    @ManyToOne(optional = false)
    private XRango idRango;
    @JoinColumn(name = "id_tipo_usuario", referencedColumnName = "id_tipo_usuario")
    @ManyToOne(optional = false)
    private XTipoUsuario idTipoUsuario;

    public XUsuario() {
    }

    public XUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public XUsuario(Integer idUsuario, String nick, String autogenerado, String acceso, Date fechaRegistro, Date ultimoAcceso, int activo) {
        this.idUsuario = idUsuario;
        this.nick = nick;
        this.autogenerado = autogenerado;
        this.acceso = acceso;
        this.fechaRegistro = fechaRegistro;
        this.ultimoAcceso = ultimoAcceso;
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAutogenerado() {
        return autogenerado;
    }

    public void setAutogenerado(String autogenerado) {
        this.autogenerado = autogenerado;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Collection<XBitacora> getXBitacoraCollection() {
        return xBitacoraCollection;
    }

    public void setXBitacoraCollection(Collection<XBitacora> xBitacoraCollection) {
        this.xBitacoraCollection = xBitacoraCollection;
    }

    public Collection<MensajePrivado> getMensajePrivadoCollection() {
        return mensajePrivadoCollection;
    }

    public void setMensajePrivadoCollection(Collection<MensajePrivado> mensajePrivadoCollection) {
        this.mensajePrivadoCollection = mensajePrivadoCollection;
    }

    public XIdioma getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(XIdioma idIdioma) {
        this.idIdioma = idIdioma;
    }

    public XRango getIdRango() {
        return idRango;
    }

    public void setIdRango(XRango idRango) {
        this.idRango = idRango;
    }

    public XTipoUsuario getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(XTipoUsuario idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
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
        if (!(object instanceof XUsuario)) {
            return false;
        }
        XUsuario other = (XUsuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XUsuario[ idUsuario=" + idUsuario + " ]";
    }
    
}
