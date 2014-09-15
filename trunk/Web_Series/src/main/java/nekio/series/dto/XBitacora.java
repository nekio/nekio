package nekio.series.dto;

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
@Table(name = "x_bitacora")
@NamedQueries({
    @NamedQuery(name = "XBitacora.findAll", query = "SELECT x FROM XBitacora x"),
    @NamedQuery(name = "XBitacora.findByIdBitacora", query = "SELECT x FROM XBitacora x WHERE x.idBitacora = :idBitacora"),
    @NamedQuery(name = "XBitacora.findByTabla", query = "SELECT x FROM XBitacora x WHERE x.tabla = :tabla"),
    @NamedQuery(name = "XBitacora.findByAccion", query = "SELECT x FROM XBitacora x WHERE x.accion = :accion"),
    @NamedQuery(name = "XBitacora.findByFecha", query = "SELECT x FROM XBitacora x WHERE x.fecha = :fecha")})
public class XBitacora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora")
    private Integer idBitacora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String tabla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    private String accion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    private String dml;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private XUsuario idUsuario;

    public XBitacora() {
    }

    public XBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public XBitacora(Integer idBitacora, String tabla, String accion, String dml, Date fecha) {
        this.idBitacora = idBitacora;
        this.tabla = tabla;
        this.accion = accion;
        this.dml = dml;
        this.fecha = fecha;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDml() {
        return dml;
    }

    public void setDml(String dml) {
        this.dml = dml;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public XUsuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(XUsuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBitacora != null ? idBitacora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XBitacora)) {
            return false;
        }
        XBitacora other = (XBitacora) object;
        if ((this.idBitacora == null && other.idBitacora != null) || (this.idBitacora != null && !this.idBitacora.equals(other.idBitacora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XBitacora[ idBitacora=" + idBitacora + " ]";
    }
    
}
