package nekio.tools.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Nekio
 */
@Embeddable
public class ModalidadPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "app_debug")
    private int appDebug;
    @Basic(optional = false)
    @NotNull
    @Column(name = "app_design")
    private int appDesign;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bitacora_estilo")
    private int bitacoraEstilo;

    public ModalidadPK() {
    }

    public ModalidadPK(int appDebug, int appDesign, int bitacoraEstilo) {
        this.appDebug = appDebug;
        this.appDesign = appDesign;
        this.bitacoraEstilo = bitacoraEstilo;
    }

    public int getAppDebug() {
        return appDebug;
    }

    public void setAppDebug(int appDebug) {
        this.appDebug = appDebug;
    }

    public int getAppDesign() {
        return appDesign;
    }

    public void setAppDesign(int appDesign) {
        this.appDesign = appDesign;
    }

    public int getBitacoraEstilo() {
        return bitacoraEstilo;
    }

    public void setBitacoraEstilo(int bitacoraEstilo) {
        this.bitacoraEstilo = bitacoraEstilo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) appDebug;
        hash += (int) appDesign;
        hash += (int) bitacoraEstilo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModalidadPK)) {
            return false;
        }
        ModalidadPK other = (ModalidadPK) object;
        if (this.appDebug != other.appDebug) {
            return false;
        }
        if (this.appDesign != other.appDesign) {
            return false;
        }
        if (this.bitacoraEstilo != other.bitacoraEstilo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.tools.dto.ModalidadPK[ appDebug=" + appDebug + ", appDesign=" + appDesign + ", bitacoraEstilo=" + bitacoraEstilo + " ]";
    }
    
}
