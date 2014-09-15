package nekio.series.dto;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Televisora.findAll", query = "SELECT t FROM Televisora t"),
    @NamedQuery(name = "Televisora.findByIdTelevisora", query = "SELECT t FROM Televisora t WHERE t.idTelevisora = :idTelevisora"),
    @NamedQuery(name = "Televisora.findBySiglasTelevisora", query = "SELECT t FROM Televisora t WHERE t.siglasTelevisora = :siglasTelevisora"),
    @NamedQuery(name = "Televisora.findByNombreTelevisora", query = "SELECT t FROM Televisora t WHERE t.nombreTelevisora = :nombreTelevisora")})
public class Televisora implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_televisora")
    private Integer idTelevisora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "siglas_televisora")
    private String siglasTelevisora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_televisora")
    private String nombreTelevisora;
    @JoinTable(name = "televisora_serie", joinColumns = {
        @JoinColumn(name = "id_televisora", referencedColumnName = "id_televisora")}, inverseJoinColumns = {
        @JoinColumn(name = "id_serie", referencedColumnName = "id_serie")})
    @ManyToMany
    private Collection<Serie> serieCollection;
    @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen")
    @ManyToOne
    private XImagen idImagen;

    public Televisora() {
    }

    public Televisora(Integer idTelevisora) {
        this.idTelevisora = idTelevisora;
    }

    public Televisora(Integer idTelevisora, String siglasTelevisora, String nombreTelevisora) {
        this.idTelevisora = idTelevisora;
        this.siglasTelevisora = siglasTelevisora;
        this.nombreTelevisora = nombreTelevisora;
    }

    public Integer getIdTelevisora() {
        return idTelevisora;
    }

    public void setIdTelevisora(Integer idTelevisora) {
        this.idTelevisora = idTelevisora;
    }

    public String getSiglasTelevisora() {
        return siglasTelevisora;
    }

    public void setSiglasTelevisora(String siglasTelevisora) {
        this.siglasTelevisora = siglasTelevisora;
    }

    public String getNombreTelevisora() {
        return nombreTelevisora;
    }

    public void setNombreTelevisora(String nombreTelevisora) {
        this.nombreTelevisora = nombreTelevisora;
    }

    public Collection<Serie> getSerieCollection() {
        return serieCollection;
    }

    public void setSerieCollection(Collection<Serie> serieCollection) {
        this.serieCollection = serieCollection;
    }

    public XImagen getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(XImagen idImagen) {
        this.idImagen = idImagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTelevisora != null ? idTelevisora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Televisora)) {
            return false;
        }
        Televisora other = (Televisora) object;
        if ((this.idTelevisora == null && other.idTelevisora != null) || (this.idTelevisora != null && !this.idTelevisora.equals(other.idTelevisora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return siglasTelevisora + " (" + nombreTelevisora + ")";
    }
    
}
