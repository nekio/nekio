package nekio.tools.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Sistema.findAll", query = "SELECT s FROM Sistema s"),
    @NamedQuery(name = "Sistema.findByIdSistema", query = "SELECT s FROM Sistema s WHERE s.idSistema = :idSistema"),
    @NamedQuery(name = "Sistema.findByDescripcion", query = "SELECT s FROM Sistema s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "Sistema.findBySiglas", query = "SELECT s FROM Sistema s WHERE s.siglas = :siglas"),
    @NamedQuery(name = "Sistema.findByTitulo", query = "SELECT s FROM Sistema s WHERE s.titulo = :titulo"),
    @NamedQuery(name = "Sistema.findByDesarrollador", query = "SELECT s FROM Sistema s WHERE s.desarrollador = :desarrollador"),
    @NamedQuery(name = "Sistema.findByContacto", query = "SELECT s FROM Sistema s WHERE s.contacto = :contacto"),
    @NamedQuery(name = "Sistema.findByVersion", query = "SELECT s FROM Sistema s WHERE s.version = :version"),
    @NamedQuery(name = "Sistema.findByActualizacion", query = "SELECT s FROM Sistema s WHERE s.actualizacion = :actualizacion"),
    @NamedQuery(name = "Sistema.findByFechaLiberacion", query = "SELECT s FROM Sistema s WHERE s.fechaLiberacion = :fechaLiberacion"),
    @NamedQuery(name = "Sistema.findByMsjMantenimiento", query = "SELECT s FROM Sistema s WHERE s.msjMantenimiento = :msjMantenimiento"),
    @NamedQuery(name = "Sistema.findByBdEsquema", query = "SELECT s FROM Sistema s WHERE s.bdEsquema = :bdEsquema"),
    @NamedQuery(name = "Sistema.findByRutaRecursos", query = "SELECT s FROM Sistema s WHERE s.rutaRecursos = :rutaRecursos")})
public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sistema")
    private Integer idSistema;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String siglas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    private String desarrollador;
    @Size(max = 50)
    private String contacto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String version;
    @Size(max = 10)
    private String actualizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_liberacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLiberacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "msj_mantenimiento")
    private String msjMantenimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "bd_esquema")
    private String bdEsquema;
    @Size(max = 255)
    @Column(name = "ruta_recursos")
    private String rutaRecursos;
    @ManyToMany(mappedBy = "sistemaCollection")
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "idSistema")
    private Collection<Bitacora> bitacoraCollection;
    @OneToMany(mappedBy = "idSistema")
    private Collection<Imagen> imagenCollection;

    public Sistema() {
    }

    public Sistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public Sistema(Integer idSistema, String descripcion, String siglas, String titulo, String desarrollador, String version, Date fechaLiberacion, String msjMantenimiento, String bdEsquema) {
        this.idSistema = idSistema;
        this.descripcion = descripcion;
        this.siglas = siglas;
        this.titulo = titulo;
        this.desarrollador = desarrollador;
        this.version = version;
        this.fechaLiberacion = fechaLiberacion;
        this.msjMantenimiento = msjMantenimiento;
        this.bdEsquema = bdEsquema;
    }

    public Integer getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(String actualizacion) {
        this.actualizacion = actualizacion;
    }

    public Date getFechaLiberacion() {
        return fechaLiberacion;
    }

    public void setFechaLiberacion(Date fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }

    public String getMsjMantenimiento() {
        return msjMantenimiento;
    }

    public void setMsjMantenimiento(String msjMantenimiento) {
        this.msjMantenimiento = msjMantenimiento;
    }

    public String getBdEsquema() {
        return bdEsquema;
    }

    public void setBdEsquema(String bdEsquema) {
        this.bdEsquema = bdEsquema;
    }

    public String getRutaRecursos() {
        return rutaRecursos;
    }

    public void setRutaRecursos(String rutaRecursos) {
        this.rutaRecursos = rutaRecursos;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Collection<Bitacora> getBitacoraCollection() {
        return bitacoraCollection;
    }

    public void setBitacoraCollection(Collection<Bitacora> bitacoraCollection) {
        this.bitacoraCollection = bitacoraCollection;
    }

    public Collection<Imagen> getImagenCollection() {
        return imagenCollection;
    }

    public void setImagenCollection(Collection<Imagen> imagenCollection) {
        this.imagenCollection = imagenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSistema != null ? idSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sistema)) {
            return false;
        }
        Sistema other = (Sistema) object;
        if ((this.idSistema == null && other.idSistema != null) || (this.idSistema != null && !this.idSistema.equals(other.idSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.tools.dto.Sistema[ idSistema=" + idSistema + " ]";
    }
    
}
