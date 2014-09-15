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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@Table(name = "x_rango")
@NamedQueries({
    @NamedQuery(name = "XRango.findAll", query = "SELECT x FROM XRango x"),
    @NamedQuery(name = "XRango.findByIdRango", query = "SELECT x FROM XRango x WHERE x.idRango = :idRango"),
    @NamedQuery(name = "XRango.findByDescripcion", query = "SELECT x FROM XRango x WHERE x.descripcion = :descripcion"),
    @NamedQuery(name = "XRango.findByDetalle", query = "SELECT x FROM XRango x WHERE x.detalle = :detalle"),
    @NamedQuery(name = "XRango.findByAccPublico", query = "SELECT x FROM XRango x WHERE x.accPublico = :accPublico"),
    @NamedQuery(name = "XRango.findByAccGrupal", query = "SELECT x FROM XRango x WHERE x.accGrupal = :accGrupal"),
    @NamedQuery(name = "XRango.findByAccProtegido", query = "SELECT x FROM XRango x WHERE x.accProtegido = :accProtegido"),
    @NamedQuery(name = "XRango.findByAccPrivado", query = "SELECT x FROM XRango x WHERE x.accPrivado = :accPrivado"),
    @NamedQuery(name = "XRango.findByColor", query = "SELECT x FROM XRango x WHERE x.color = :color")})
public class XRango implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rango")
    private Integer idRango;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acc_publico")
    private int accPublico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acc_grupal")
    private int accGrupal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acc_protegido")
    private int accProtegido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acc_privado")
    private int accPrivado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Formato> formatoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Proyecto> proyectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Password> passwordCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Colaborador> colaboradorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<TipoSerie> tipoSerieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Calidad> calidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Pendiente> pendienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<TipoEpisodio> tipoEpisodioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Web> webCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<XUsuario> xUsuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Serie> serieCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Peso> pesoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRango")
    private Collection<Genero> generoCollection;

    public XRango() {
    }

    public XRango(Integer idRango) {
        this.idRango = idRango;
    }

    public XRango(Integer idRango, String descripcion, String detalle, int accPublico, int accGrupal, int accProtegido, int accPrivado, String color) {
        this.idRango = idRango;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.accPublico = accPublico;
        this.accGrupal = accGrupal;
        this.accProtegido = accProtegido;
        this.accPrivado = accPrivado;
        this.color = color;
    }

    public Integer getIdRango() {
        return idRango;
    }

    public void setIdRango(Integer idRango) {
        this.idRango = idRango;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getAccPublico() {
        return accPublico;
    }

    public void setAccPublico(int accPublico) {
        this.accPublico = accPublico;
    }

    public int getAccGrupal() {
        return accGrupal;
    }

    public void setAccGrupal(int accGrupal) {
        this.accGrupal = accGrupal;
    }

    public int getAccProtegido() {
        return accProtegido;
    }

    public void setAccProtegido(int accProtegido) {
        this.accProtegido = accProtegido;
    }

    public int getAccPrivado() {
        return accPrivado;
    }

    public void setAccPrivado(int accPrivado) {
        this.accPrivado = accPrivado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Collection<Formato> getFormatoCollection() {
        return formatoCollection;
    }

    public void setFormatoCollection(Collection<Formato> formatoCollection) {
        this.formatoCollection = formatoCollection;
    }

    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    public Collection<Password> getPasswordCollection() {
        return passwordCollection;
    }

    public void setPasswordCollection(Collection<Password> passwordCollection) {
        this.passwordCollection = passwordCollection;
    }

    public Collection<Colaborador> getColaboradorCollection() {
        return colaboradorCollection;
    }

    public void setColaboradorCollection(Collection<Colaborador> colaboradorCollection) {
        this.colaboradorCollection = colaboradorCollection;
    }

    public Collection<TipoSerie> getTipoSerieCollection() {
        return tipoSerieCollection;
    }

    public void setTipoSerieCollection(Collection<TipoSerie> tipoSerieCollection) {
        this.tipoSerieCollection = tipoSerieCollection;
    }

    public Collection<Calidad> getCalidadCollection() {
        return calidadCollection;
    }

    public void setCalidadCollection(Collection<Calidad> calidadCollection) {
        this.calidadCollection = calidadCollection;
    }

    public Collection<Pendiente> getPendienteCollection() {
        return pendienteCollection;
    }

    public void setPendienteCollection(Collection<Pendiente> pendienteCollection) {
        this.pendienteCollection = pendienteCollection;
    }

    public Collection<TipoEpisodio> getTipoEpisodioCollection() {
        return tipoEpisodioCollection;
    }

    public void setTipoEpisodioCollection(Collection<TipoEpisodio> tipoEpisodioCollection) {
        this.tipoEpisodioCollection = tipoEpisodioCollection;
    }

    public Collection<Web> getWebCollection() {
        return webCollection;
    }

    public void setWebCollection(Collection<Web> webCollection) {
        this.webCollection = webCollection;
    }

    public Collection<XUsuario> getXUsuarioCollection() {
        return xUsuarioCollection;
    }

    public void setXUsuarioCollection(Collection<XUsuario> xUsuarioCollection) {
        this.xUsuarioCollection = xUsuarioCollection;
    }

    public Collection<Serie> getSerieCollection() {
        return serieCollection;
    }

    public void setSerieCollection(Collection<Serie> serieCollection) {
        this.serieCollection = serieCollection;
    }

    public Collection<Peso> getPesoCollection() {
        return pesoCollection;
    }

    public void setPesoCollection(Collection<Peso> pesoCollection) {
        this.pesoCollection = pesoCollection;
    }

    public Collection<Genero> getGeneroCollection() {
        return generoCollection;
    }

    public void setGeneroCollection(Collection<Genero> generoCollection) {
        this.generoCollection = generoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRango != null ? idRango.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof XRango)) {
            return false;
        }
        XRango other = (XRango) object;
        if ((this.idRango == null && other.idRango != null) || (this.idRango != null && !this.idRango.equals(other.idRango))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.XRango[ idRango=" + idRango + " ]";
    }
    
}
