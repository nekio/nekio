
package nekio.seriesweb.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "proyecto")
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByIdProyecto", query = "SELECT p FROM Proyecto p WHERE p.idProyecto = :idProyecto"),
    @NamedQuery(name = "Proyecto.findByIdRango", query = "SELECT p FROM Proyecto p WHERE p.idRango = :idRango"),
    @NamedQuery(name = "Proyecto.findByFechaInicio", query = "SELECT p FROM Proyecto p WHERE p.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Proyecto.findByFechaFin", query = "SELECT p FROM Proyecto p WHERE p.fechaFin = :fechaFin"),
    @NamedQuery(name = "Proyecto.findByEpisodiosSubir", query = "SELECT p FROM Proyecto p WHERE p.episodiosSubir = :episodiosSubir"),
    @NamedQuery(name = "Proyecto.findByEpisodiosSubidos", query = "SELECT p FROM Proyecto p WHERE p.episodiosSubidos = :episodiosSubidos"),
    @NamedQuery(name = "Proyecto.findByEnlace", query = "SELECT p FROM Proyecto p WHERE p.enlace = :enlace"),
    @NamedQuery(name = "Proyecto.findByComentario", query = "SELECT p FROM Proyecto p WHERE p.comentario = :comentario")})
public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rango")
    private int idRango;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "episodios_subir")
    private Integer episodiosSubir;
    @Column(name = "episodios_subidos")
    private Integer episodiosSubidos;
    @Size(max = 255)
    @Column(name = "enlace")
    private String enlace;
    @Size(max = 100)
    @Column(name = "comentario")
    private String comentario;
    @Lob
    @Size(max = 65535)
    @Column(name = "enlaces")
    private String enlaces;
    @JoinColumn(name = "id_peso", referencedColumnName = "id_peso")
    @ManyToOne(optional = false)
    private Peso idPeso;
    @JoinColumn(name = "id_calidad", referencedColumnName = "id_calidad")
    @ManyToOne(optional = false)
    private Calidad idCalidad;
    @JoinColumn(name = "id_formato", referencedColumnName = "id_formato")
    @ManyToOne(optional = false)
    private Formato idFormato;
    @JoinColumn(name = "id_idioma", referencedColumnName = "id_idioma")
    @ManyToOne(optional = false)
    private Idioma idIdioma;
    @JoinColumn(name = "id_web", referencedColumnName = "id_web")
    @ManyToOne(optional = false)
    private Web idWeb;
    @JoinColumn(name = "id_serie", referencedColumnName = "id_serie")
    @ManyToOne(optional = false)
    private Serie idSerie;
    @JoinColumn(name = "id_estado_proyecto", referencedColumnName = "id_estado_proyecto")
    @ManyToOne(optional = false)
    private EstadoProyecto idEstadoProyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProyecto")
    private Collection<ProyectoDet> proyectoDetCollection;
    @OneToMany(mappedBy = "idProyecto")
    private Collection<Password> passwordCollection;
    @OneToMany(mappedBy = "idProyecto")
    private Collection<Pendiente> pendienteCollection;
    @OneToMany(mappedBy = "idProyecto")
    private Collection<Enlace> enlaceCollection;
    @OneToMany(mappedBy = "idProyecto")
    private Collection<Obtenido> obtenidoCollection;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyecto(Integer idProyecto, int idRango, Date fechaInicio) {
        this.idProyecto = idProyecto;
        this.idRango = idRango;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdRango() {
        return idRango;
    }

    public void setIdRango(int idRango) {
        this.idRango = idRango;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getEpisodiosSubir() {
        return episodiosSubir;
    }

    public void setEpisodiosSubir(Integer episodiosSubir) {
        this.episodiosSubir = episodiosSubir;
    }

    public Integer getEpisodiosSubidos() {
        return episodiosSubidos;
    }

    public void setEpisodiosSubidos(Integer episodiosSubidos) {
        this.episodiosSubidos = episodiosSubidos;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEnlaces() {
        return enlaces;
    }

    public void setEnlaces(String enlaces) {
        this.enlaces = enlaces;
    }

    public Peso getIdPeso() {
        return idPeso;
    }

    public void setIdPeso(Peso idPeso) {
        this.idPeso = idPeso;
    }

    public Calidad getIdCalidad() {
        return idCalidad;
    }

    public void setIdCalidad(Calidad idCalidad) {
        this.idCalidad = idCalidad;
    }

    public Formato getIdFormato() {
        return idFormato;
    }

    public void setIdFormato(Formato idFormato) {
        this.idFormato = idFormato;
    }

    public Idioma getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Idioma idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Web getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(Web idWeb) {
        this.idWeb = idWeb;
    }

    public Serie getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
    }

    public EstadoProyecto getIdEstadoProyecto() {
        return idEstadoProyecto;
    }

    public void setIdEstadoProyecto(EstadoProyecto idEstadoProyecto) {
        this.idEstadoProyecto = idEstadoProyecto;
    }

    public Collection<ProyectoDet> getProyectoDetCollection() {
        return proyectoDetCollection;
    }

    public void setProyectoDetCollection(Collection<ProyectoDet> proyectoDetCollection) {
        this.proyectoDetCollection = proyectoDetCollection;
    }

    public Collection<Password> getPasswordCollection() {
        return passwordCollection;
    }

    public void setPasswordCollection(Collection<Password> passwordCollection) {
        this.passwordCollection = passwordCollection;
    }

    public Collection<Pendiente> getPendienteCollection() {
        return pendienteCollection;
    }

    public void setPendienteCollection(Collection<Pendiente> pendienteCollection) {
        this.pendienteCollection = pendienteCollection;
    }

    public Collection<Enlace> getEnlaceCollection() {
        return enlaceCollection;
    }

    public void setEnlaceCollection(Collection<Enlace> enlaceCollection) {
        this.enlaceCollection = enlaceCollection;
    }

    public Collection<Obtenido> getObtenidoCollection() {
        return obtenidoCollection;
    }

    public void setObtenidoCollection(Collection<Obtenido> obtenidoCollection) {
        this.obtenidoCollection = obtenidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idProyecto + ": " + getIdSerie().getTitulo() + " [" + getIdWeb().getSiglas() + "][" + getIdIdioma().getDescripcion() + "][" + this.idEstadoProyecto.getDescripcion() + "]";
    }
    
}
