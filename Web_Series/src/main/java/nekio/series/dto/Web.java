package nekio.series.dto;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Web.findAll", query = "SELECT w FROM Web w"),
    @NamedQuery(name = "Web.findByIdWeb", query = "SELECT w FROM Web w WHERE w.idWeb = :idWeb"),
    @NamedQuery(name = "Web.findByTitulo", query = "SELECT w FROM Web w WHERE w.titulo = :titulo"),
    @NamedQuery(name = "Web.findBySiglas", query = "SELECT w FROM Web w WHERE w.siglas = :siglas"),
    @NamedQuery(name = "Web.findByFacebook", query = "SELECT w FROM Web w WHERE w.facebook = :facebook"),
    @NamedQuery(name = "Web.findByPagina", query = "SELECT w FROM Web w WHERE w.pagina = :pagina"),
    @NamedQuery(name = "Web.findByDescripcion", query = "SELECT w FROM Web w WHERE w.descripcion = :descripcion")})
public class Web implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_web")
    private Integer idWeb;
    @Size(max = 50)
    private String titulo;
    @Size(max = 20)
    private String siglas;
    @Size(max = 100)
    private String facebook;
    @Size(max = 50)
    private String pagina;
    @Size(max = 100)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idWeb")
    private Collection<Proyecto> proyectoCollection;
    @OneToMany(mappedBy = "idWeb")
    private Collection<MensajePrivado> mensajePrivadoCollection;
    @JoinColumn(name = "id_rango", referencedColumnName = "id_rango")
    @ManyToOne(optional = false)
    private XRango idRango;
    @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen")
    @ManyToOne
    private XImagen idImagen;

    public Web() {
    }

    public Web(Integer idWeb) {
        this.idWeb = idWeb;
    }

    public Integer getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(Integer idWeb) {
        this.idWeb = idWeb;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    public Collection<MensajePrivado> getMensajePrivadoCollection() {
        return mensajePrivadoCollection;
    }

    public void setMensajePrivadoCollection(Collection<MensajePrivado> mensajePrivadoCollection) {
        this.mensajePrivadoCollection = mensajePrivadoCollection;
    }

    public XRango getIdRango() {
        return idRango;
    }

    public void setIdRango(XRango idRango) {
        this.idRango = idRango;
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
        hash += (idWeb != null ? idWeb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Web)) {
            return false;
        }
        Web other = (Web) object;
        if ((this.idWeb == null && other.idWeb != null) || (this.idWeb != null && !this.idWeb.equals(other.idWeb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return siglas;
    }
    
}
