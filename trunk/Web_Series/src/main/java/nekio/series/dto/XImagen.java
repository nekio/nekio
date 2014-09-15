package nekio.series.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "x_imagen")
@NamedQueries({
    @NamedQuery(name = "XImagen.findAll", query = "SELECT x FROM XImagen x"),
    @NamedQuery(name = "XImagen.findByIdImagen", query = "SELECT x FROM XImagen x WHERE x.idImagen = :idImagen"),
    @NamedQuery(name = "XImagen.findByNombre", query = "SELECT x FROM XImagen x WHERE x.nombre = :nombre"),
    @NamedQuery(name = "XImagen.findByTipo", query = "SELECT x FROM XImagen x WHERE x.tipo = :tipo"),
    @NamedQuery(name = "XImagen.findByFechaSubida", query = "SELECT x FROM XImagen x WHERE x.fechaSubida = :fechaSubida"),
    @NamedQuery(name = "XImagen.findByDescripcion", query = "SELECT x FROM XImagen x WHERE x.descripcion = :descripcion")})
public class XImagen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_imagen")
    private Integer idImagen;
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
    @OneToMany(mappedBy = "idImagen")
    private Collection<Colaborador> colaboradorCollection;
    @OneToMany(mappedBy = "idImagen")
    private Collection<Emisora> emisoraCollection;
    @JoinColumn(name = "id_carpeta", referencedColumnName = "id_carpeta")
    @ManyToOne
    private XCarpeta idCarpeta;
    @OneToMany(mappedBy = "idImagen")
    private Collection<Web> webCollection;
    @OneToMany(mappedBy = "idImagen")
    private Collection<Serie> serieCollection;
    @OneToMany(mappedBy = "idCaptura1")
    private Collection<Serie> serieCollection1;
    @OneToMany(mappedBy = "idCaptura2")
    private Collection<Serie> serieCollection2;
    @OneToMany(mappedBy = "idCaptura3")
    private Collection<Serie> serieCollection3;
    @OneToMany(mappedBy = "idImagen")
    private Collection<Televisora> televisoraCollection;

    public XImagen() {
    }

    public XImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public XImagen(Integer idImagen, String tipo, Date fechaSubida) {
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

    public Collection<Colaborador> getColaboradorCollection() {
        return colaboradorCollection;
    }

    public void setColaboradorCollection(Collection<Colaborador> colaboradorCollection) {
        this.colaboradorCollection = colaboradorCollection;
    }

    public Collection<Emisora> getEmisoraCollection() {
        return emisoraCollection;
    }

    public void setEmisoraCollection(Collection<Emisora> emisoraCollection) {
        this.emisoraCollection = emisoraCollection;
    }

    public XCarpeta getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(XCarpeta idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public Collection<Web> getWebCollection() {
        return webCollection;
    }

    public void setWebCollection(Collection<Web> webCollection) {
        this.webCollection = webCollection;
    }

    public Collection<Serie> getSerieCollection() {
        return serieCollection;
    }

    public void setSerieCollection(Collection<Serie> serieCollection) {
        this.serieCollection = serieCollection;
    }

    public Collection<Serie> getSerieCollection1() {
        return serieCollection1;
    }

    public void setSerieCollection1(Collection<Serie> serieCollection1) {
        this.serieCollection1 = serieCollection1;
    }

    public Collection<Serie> getSerieCollection2() {
        return serieCollection2;
    }

    public void setSerieCollection2(Collection<Serie> serieCollection2) {
        this.serieCollection2 = serieCollection2;
    }

    public Collection<Serie> getSerieCollection3() {
        return serieCollection3;
    }

    public void setSerieCollection3(Collection<Serie> serieCollection3) {
        this.serieCollection3 = serieCollection3;
    }

    public Collection<Televisora> getTelevisoraCollection() {
        return televisoraCollection;
    }

    public void setTelevisoraCollection(Collection<Televisora> televisoraCollection) {
        this.televisoraCollection = televisoraCollection;
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
        if (!(object instanceof XImagen)) {
            return false;
        }
        XImagen other = (XImagen) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XImagen[ idImagen=" + idImagen + " ]";
    }
    
}
