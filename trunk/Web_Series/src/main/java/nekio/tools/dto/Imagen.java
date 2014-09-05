package nekio.tools.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "Imagen.findAll", query = "SELECT i FROM Imagen i"),
    @NamedQuery(name = "Imagen.findByIdImagen", query = "SELECT i FROM Imagen i WHERE i.idImagen = :idImagen"),
    @NamedQuery(name = "Imagen.findByNombre", query = "SELECT i FROM Imagen i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "Imagen.findByTipo", query = "SELECT i FROM Imagen i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "Imagen.findByFechaSubida", query = "SELECT i FROM Imagen i WHERE i.fechaSubida = :fechaSubida"),
    @NamedQuery(name = "Imagen.findByDescripcion", query = "SELECT i FROM Imagen i WHERE i.descripcion = :descripcion")})
public class Imagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_imagen")
    private Integer idImagen;
    @Lob
    private byte[] imagen;
    @Size(max = 30)
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_subida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSubida;
    @Size(max = 80)
    private String descripcion;
    @JoinColumn(name = "id_sistema", referencedColumnName = "id_sistema")
    @ManyToOne
    private Sistema idSistema;
    @JoinColumn(name = "id_carpeta", referencedColumnName = "id_carpeta")
    @ManyToOne
    private Carpeta idCarpeta;

    public Imagen() {
    }

    public Imagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Imagen(Integer idImagen, String tipo, Date fechaSubida) {
        this.idImagen = idImagen;
        this.tipo = tipo;
        this.fechaSubida = fechaSubida;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sistema getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Sistema idSistema) {
        this.idSistema = idSistema;
    }

    public Carpeta getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(Carpeta idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.tools.dto.Imagen[ idImagen=" + idImagen + " ]";
    }
    
}
