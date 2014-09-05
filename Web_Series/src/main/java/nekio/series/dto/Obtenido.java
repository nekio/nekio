package nekio.series.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "Obtenido.findAll", query = "SELECT o FROM Obtenido o"),
    @NamedQuery(name = "Obtenido.findByIdObtenido", query = "SELECT o FROM Obtenido o WHERE o.idObtenido = :idObtenido"),
    @NamedQuery(name = "Obtenido.findByFechaObtencion", query = "SELECT o FROM Obtenido o WHERE o.fechaObtencion = :fechaObtencion"),
    @NamedQuery(name = "Obtenido.findByNombreArchivo", query = "SELECT o FROM Obtenido o WHERE o.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "Obtenido.findByPesoArchivo", query = "SELECT o FROM Obtenido o WHERE o.pesoArchivo = :pesoArchivo"),
    @NamedQuery(name = "Obtenido.findByUnidadPeso", query = "SELECT o FROM Obtenido o WHERE o.unidadPeso = :unidadPeso"),
    @NamedQuery(name = "Obtenido.findByComentarios", query = "SELECT o FROM Obtenido o WHERE o.comentarios = :comentarios")})
public class Obtenido implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_obtenido")
    private Integer idObtenido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_obtencion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaObtencion;
    @Size(max = 255)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso_archivo")
    private BigDecimal pesoArchivo;
    @Size(max = 2)
    @Column(name = "unidad_peso")
    private String unidadPeso;
    @Size(max = 255)
    private String comentarios;
    @JoinColumn(name = "id_subtitulo", referencedColumnName = "id_idioma")
    @ManyToOne
    private Idioma idSubtitulo;
    @JoinColumn(name = "id_audio", referencedColumnName = "id_idioma")
    @ManyToOne
    private Idioma idAudio;
    @JoinColumn(name = "id_almacen", referencedColumnName = "id_almacen")
    @ManyToOne(optional = false)
    private Almacen idAlmacen;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne
    private Proyecto idProyecto;
    @JoinColumn(name = "id_episodio", referencedColumnName = "id_episodio")
    @ManyToOne
    private Episodio idEpisodio;

    public Obtenido() {
    }

    public Obtenido(Integer idObtenido) {
        this.idObtenido = idObtenido;
    }

    public Obtenido(Integer idObtenido, Date fechaObtencion) {
        this.idObtenido = idObtenido;
        this.fechaObtencion = fechaObtencion;
    }

    public Integer getIdObtenido() {
        return idObtenido;
    }

    public void setIdObtenido(Integer idObtenido) {
        this.idObtenido = idObtenido;
    }

    public Date getFechaObtencion() {
        return fechaObtencion;
    }

    public void setFechaObtencion(Date fechaObtencion) {
        this.fechaObtencion = fechaObtencion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public BigDecimal getPesoArchivo() {
        return pesoArchivo;
    }

    public void setPesoArchivo(BigDecimal pesoArchivo) {
        this.pesoArchivo = pesoArchivo;
    }

    public String getUnidadPeso() {
        return unidadPeso;
    }

    public void setUnidadPeso(String unidadPeso) {
        this.unidadPeso = unidadPeso;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Idioma getIdSubtitulo() {
        return idSubtitulo;
    }

    public void setIdSubtitulo(Idioma idSubtitulo) {
        this.idSubtitulo = idSubtitulo;
    }

    public Idioma getIdAudio() {
        return idAudio;
    }

    public void setIdAudio(Idioma idAudio) {
        this.idAudio = idAudio;
    }

    public Almacen getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Almacen idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Episodio getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(Episodio idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObtenido != null ? idObtenido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obtenido)) {
            return false;
        }
        Obtenido other = (Obtenido) object;
        if ((this.idObtenido == null && other.idObtenido != null) || (this.idObtenido != null && !this.idObtenido.equals(other.idObtenido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.Obtenido[ idObtenido=" + idObtenido + " ]";
    }
    
}
