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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s"),
    @NamedQuery(name = "Serie.findByIdSerie", query = "SELECT s FROM Serie s WHERE s.idSerie = :idSerie"),
    @NamedQuery(name = "Serie.findByTitulo", query = "SELECT s FROM Serie s WHERE s.titulo = :titulo"),
    @NamedQuery(name = "Serie.findByTituloAlternativo", query = "SELECT s FROM Serie s WHERE s.tituloAlternativo = :tituloAlternativo"),
    @NamedQuery(name = "Serie.findByTituloAlternativo2", query = "SELECT s FROM Serie s WHERE s.tituloAlternativo2 = :tituloAlternativo2"),
    @NamedQuery(name = "Serie.findByAnioInicio", query = "SELECT s FROM Serie s WHERE s.anioInicio = :anioInicio"),
    @NamedQuery(name = "Serie.findByAnioFin", query = "SELECT s FROM Serie s WHERE s.anioFin = :anioFin"),
    @NamedQuery(name = "Serie.findByTemporadas", query = "SELECT s FROM Serie s WHERE s.temporadas = :temporadas"),
    @NamedQuery(name = "Serie.findByEpisodios", query = "SELECT s FROM Serie s WHERE s.episodios = :episodios")})
public class Serie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_serie")
    private Integer idSerie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String titulo;
    @Size(max = 80)
    @Column(name = "titulo_alternativo")
    private String tituloAlternativo;
    @Size(max = 80)
    @Column(name = "titulo_alternativo2")
    private String tituloAlternativo2;
    @Column(name = "anio_inicio")
    private Integer anioInicio;
    @Column(name = "anio_fin")
    private Integer anioFin;
    private Integer temporadas;
    private Integer episodios;
    @ManyToMany(mappedBy = "serieCollection")
    private Collection<Televisora> televisoraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSerie")
    private Collection<Proyecto> proyectoCollection;
    @OneToMany(mappedBy = "idSerie")
    private Collection<Password> passwordCollection;
    @OneToMany(mappedBy = "idSerie")
    private Collection<Enlace> enlaceCollection;
    @JoinColumn(name = "id_genero_2", referencedColumnName = "id_genero")
    @ManyToOne
    private Genero idGenero2;
    @JoinColumn(name = "id_genero_1", referencedColumnName = "id_genero")
    @ManyToOne(optional = false)
    private Genero idGenero1;
    @JoinColumn(name = "id_clasificacion", referencedColumnName = "id_clasificacion")
    @ManyToOne(optional = false)
    private Clasificacion idClasificacion;
    @JoinColumn(name = "id_duracion", referencedColumnName = "id_duracion")
    @ManyToOne(optional = false)
    private Duracion idDuracion;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne(optional = false)
    private Pais idPais;
    @OneToMany(mappedBy = "idSerieOrigen")
    private Collection<Serie> serieCollection;
    @JoinColumn(name = "id_serie_origen", referencedColumnName = "id_serie")
    @ManyToOne
    private Serie idSerieOrigen;
    @JoinColumn(name = "id_genero_3", referencedColumnName = "id_genero")
    @ManyToOne
    private Genero idGenero3;
    @JoinColumn(name = "id_genero_4", referencedColumnName = "id_genero")
    @ManyToOne
    private Genero idGenero4;
    @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen")
    @ManyToOne
    private XImagen idImagen;
    @JoinColumn(name = "id_captura_1", referencedColumnName = "id_imagen")
    @ManyToOne
    private XImagen idCaptura1;
    @JoinColumn(name = "id_captura_2", referencedColumnName = "id_imagen")
    @ManyToOne
    private XImagen idCaptura2;
    @JoinColumn(name = "id_captura_3", referencedColumnName = "id_imagen")
    @ManyToOne
    private XImagen idCaptura3;
    @JoinColumn(name = "id_distintivo", referencedColumnName = "id_distintivo")
    @ManyToOne
    private Distintivo idDistintivo;
    @JoinColumn(name = "id_rango", referencedColumnName = "id_rango")
    @ManyToOne(optional = false)
    private XRango idRango;
    @JoinColumn(name = "id_emisora", referencedColumnName = "id_emisora")
    @ManyToOne(optional = false)
    private Emisora idEmisora;
    @JoinColumn(name = "id_tipo_serie", referencedColumnName = "id_tipo_serie")
    @ManyToOne(optional = false)
    private TipoSerie idTipoSerie;
    @JoinColumn(name = "id_idioma_origen", referencedColumnName = "id_idioma")
    @ManyToOne(optional = false)
    private Idioma idIdiomaOrigen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSerie")
    private Collection<Episodio> episodioCollection;

    public Serie() {
    }

    public Serie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Serie(Integer idSerie, String titulo) {
        this.idSerie = idSerie;
        this.titulo = titulo;
    }

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloAlternativo() {
        return tituloAlternativo;
    }

    public void setTituloAlternativo(String tituloAlternativo) {
        this.tituloAlternativo = tituloAlternativo;
    }

    public String getTituloAlternativo2() {
        return tituloAlternativo2;
    }

    public void setTituloAlternativo2(String tituloAlternativo2) {
        this.tituloAlternativo2 = tituloAlternativo2;
    }

    public Integer getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(Integer anioInicio) {
        this.anioInicio = anioInicio;
    }

    public Integer getAnioFin() {
        return anioFin;
    }

    public void setAnioFin(Integer anioFin) {
        this.anioFin = anioFin;
    }

    public Integer getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(Integer temporadas) {
        this.temporadas = temporadas;
    }

    public Integer getEpisodios() {
        return episodios;
    }

    public void setEpisodios(Integer episodios) {
        this.episodios = episodios;
    }

    public Collection<Televisora> getTelevisoraCollection() {
        return televisoraCollection;
    }

    public void setTelevisoraCollection(Collection<Televisora> televisoraCollection) {
        this.televisoraCollection = televisoraCollection;
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

    public Collection<Enlace> getEnlaceCollection() {
        return enlaceCollection;
    }

    public void setEnlaceCollection(Collection<Enlace> enlaceCollection) {
        this.enlaceCollection = enlaceCollection;
    }

    public Genero getIdGenero2() {
        return idGenero2;
    }

    public void setIdGenero2(Genero idGenero2) {
        this.idGenero2 = idGenero2;
    }

    public Genero getIdGenero1() {
        return idGenero1;
    }

    public void setIdGenero1(Genero idGenero1) {
        this.idGenero1 = idGenero1;
    }

    public Clasificacion getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Clasificacion idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    public Duracion getIdDuracion() {
        return idDuracion;
    }

    public void setIdDuracion(Duracion idDuracion) {
        this.idDuracion = idDuracion;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public Collection<Serie> getSerieCollection() {
        return serieCollection;
    }

    public void setSerieCollection(Collection<Serie> serieCollection) {
        this.serieCollection = serieCollection;
    }

    public Serie getIdSerieOrigen() {
        return idSerieOrigen;
    }

    public void setIdSerieOrigen(Serie idSerieOrigen) {
        this.idSerieOrigen = idSerieOrigen;
    }

    public Genero getIdGenero3() {
        return idGenero3;
    }

    public void setIdGenero3(Genero idGenero3) {
        this.idGenero3 = idGenero3;
    }

    public Genero getIdGenero4() {
        return idGenero4;
    }

    public void setIdGenero4(Genero idGenero4) {
        this.idGenero4 = idGenero4;
    }

    public XImagen getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(XImagen idImagen) {
        this.idImagen = idImagen;
    }

    public XImagen getIdCaptura1() {
        return idCaptura1;
    }

    public void setIdCaptura1(XImagen idCaptura1) {
        this.idCaptura1 = idCaptura1;
    }

    public XImagen getIdCaptura2() {
        return idCaptura2;
    }

    public void setIdCaptura2(XImagen idCaptura2) {
        this.idCaptura2 = idCaptura2;
    }

    public XImagen getIdCaptura3() {
        return idCaptura3;
    }

    public void setIdCaptura3(XImagen idCaptura3) {
        this.idCaptura3 = idCaptura3;
    }

    public Distintivo getIdDistintivo() {
        return idDistintivo;
    }

    public void setIdDistintivo(Distintivo idDistintivo) {
        this.idDistintivo = idDistintivo;
    }

    public XRango getIdRango() {
        return idRango;
    }

    public void setIdRango(XRango idRango) {
        this.idRango = idRango;
    }

    public Emisora getIdEmisora() {
        return idEmisora;
    }

    public void setIdEmisora(Emisora idEmisora) {
        this.idEmisora = idEmisora;
    }

    public TipoSerie getIdTipoSerie() {
        return idTipoSerie;
    }

    public void setIdTipoSerie(TipoSerie idTipoSerie) {
        this.idTipoSerie = idTipoSerie;
    }

    public Idioma getIdIdiomaOrigen() {
        return idIdiomaOrigen;
    }

    public void setIdIdiomaOrigen(Idioma idIdiomaOrigen) {
        this.idIdiomaOrigen = idIdiomaOrigen;
    }

    public Collection<Episodio> getEpisodioCollection() {
        return episodioCollection;
    }

    public void setEpisodioCollection(Collection<Episodio> episodioCollection) {
        this.episodioCollection = episodioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSerie != null ? idSerie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Serie)) {
            return false;
        }
        Serie other = (Serie) object;
        if ((this.idSerie == null && other.idSerie != null) || (this.idSerie != null && !this.idSerie.equals(other.idSerie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idSerie + ": " + getAnioInicio() + " - " + this.getTitulo();
    }
    
}
