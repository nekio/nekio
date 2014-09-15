package nekio.series.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "x_modalidad")
@NamedQueries({
    @NamedQuery(name = "XModalidad.findAll", query = "SELECT x FROM XModalidad x"),
    @NamedQuery(name = "XModalidad.findByAppDebug", query = "SELECT x FROM XModalidad x WHERE x.xModalidadPK.appDebug = :appDebug"),
    @NamedQuery(name = "XModalidad.findByAppDesign", query = "SELECT x FROM XModalidad x WHERE x.xModalidadPK.appDesign = :appDesign"),
    @NamedQuery(name = "XModalidad.findByBitacoraEstilo", query = "SELECT x FROM XModalidad x WHERE x.xModalidadPK.bitacoraEstilo = :bitacoraEstilo"),
    @NamedQuery(name = "XModalidad.findByImgChica", query = "SELECT x FROM XModalidad x WHERE x.imgChica = :imgChica"),
    @NamedQuery(name = "XModalidad.findByImgMediana", query = "SELECT x FROM XModalidad x WHERE x.imgMediana = :imgMediana")})
public class XModalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected XModalidadPK xModalidadPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "img_chica")
    private String imgChica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "img_mediana")
    private String imgMediana;

    public XModalidad() {
    }

    public XModalidad(XModalidadPK xModalidadPK) {
        this.xModalidadPK = xModalidadPK;
    }

    public XModalidad(XModalidadPK xModalidadPK, String imgChica, String imgMediana) {
        this.xModalidadPK = xModalidadPK;
        this.imgChica = imgChica;
        this.imgMediana = imgMediana;
    }

    public XModalidad(int appDebug, int appDesign, int bitacoraEstilo) {
        this.xModalidadPK = new XModalidadPK(appDebug, appDesign, bitacoraEstilo);
    }

    public XModalidadPK getXModalidadPK() {
        return xModalidadPK;
    }

    public void setXModalidadPK(XModalidadPK xModalidadPK) {
        this.xModalidadPK = xModalidadPK;
    }

    public String getImgChica() {
        return imgChica;
    }

    public void setImgChica(String imgChica) {
        this.imgChica = imgChica;
    }

    public String getImgMediana() {
        return imgMediana;
    }

    public void setImgMediana(String imgMediana) {
        this.imgMediana = imgMediana;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (xModalidadPK != null ? xModalidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XModalidad)) {
            return false;
        }
        XModalidad other = (XModalidad) object;
        if ((this.xModalidadPK == null && other.xModalidadPK != null) || (this.xModalidadPK != null && !this.xModalidadPK.equals(other.xModalidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XModalidad[ xModalidadPK=" + xModalidadPK + " ]";
    }
    
}
