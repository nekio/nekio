package nekio.tools.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Modalidad.findAll", query = "SELECT m FROM Modalidad m"),
    @NamedQuery(name = "Modalidad.findByAppDebug", query = "SELECT m FROM Modalidad m WHERE m.modalidadPK.appDebug = :appDebug"),
    @NamedQuery(name = "Modalidad.findByAppDesign", query = "SELECT m FROM Modalidad m WHERE m.modalidadPK.appDesign = :appDesign"),
    @NamedQuery(name = "Modalidad.findByBitacoraEstilo", query = "SELECT m FROM Modalidad m WHERE m.modalidadPK.bitacoraEstilo = :bitacoraEstilo"),
    @NamedQuery(name = "Modalidad.findByImgChica", query = "SELECT m FROM Modalidad m WHERE m.imgChica = :imgChica"),
    @NamedQuery(name = "Modalidad.findByImgMediana", query = "SELECT m FROM Modalidad m WHERE m.imgMediana = :imgMediana")})
public class Modalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModalidadPK modalidadPK;
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

    public Modalidad() {
    }

    public Modalidad(ModalidadPK modalidadPK) {
        this.modalidadPK = modalidadPK;
    }

    public Modalidad(ModalidadPK modalidadPK, String imgChica, String imgMediana) {
        this.modalidadPK = modalidadPK;
        this.imgChica = imgChica;
        this.imgMediana = imgMediana;
    }

    public Modalidad(int appDebug, int appDesign, int bitacoraEstilo) {
        this.modalidadPK = new ModalidadPK(appDebug, appDesign, bitacoraEstilo);
    }

    public ModalidadPK getModalidadPK() {
        return modalidadPK;
    }

    public void setModalidadPK(ModalidadPK modalidadPK) {
        this.modalidadPK = modalidadPK;
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
        hash += (modalidadPK != null ? modalidadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modalidad)) {
            return false;
        }
        Modalidad other = (Modalidad) object;
        if ((this.modalidadPK == null && other.modalidadPK != null) || (this.modalidadPK != null && !this.modalidadPK.equals(other.modalidadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.tools.dto.Modalidad[ modalidadPK=" + modalidadPK + " ]";
    }
    
}
