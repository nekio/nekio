
package nekio.seriesweb.dto;

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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nekio
 */
@Entity
@Table(name = "colaborador")
@NamedQueries({
    @NamedQuery(name = "Colaborador.findAll", query = "SELECT c FROM Colaborador c"),
    @NamedQuery(name = "Colaborador.findByIdColaborador", query = "SELECT c FROM Colaborador c WHERE c.idColaborador = :idColaborador"),
    @NamedQuery(name = "Colaborador.findByIdImagen", query = "SELECT c FROM Colaborador c WHERE c.idImagen = :idImagen"),
    @NamedQuery(name = "Colaborador.findByIdRango", query = "SELECT c FROM Colaborador c WHERE c.idRango = :idRango"),
    @NamedQuery(name = "Colaborador.findByDescripcion", query = "SELECT c FROM Colaborador c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Colaborador.findBySexo", query = "SELECT c FROM Colaborador c WHERE c.sexo = :sexo"),
    @NamedQuery(name = "Colaborador.findByContacto", query = "SELECT c FROM Colaborador c WHERE c.contacto = :contacto")})
public class Colaborador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_colaborador")
    private Integer idColaborador;
    @Column(name = "id_imagen")
    private Integer idImagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rango")
    private int idRango;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "sexo")
    private Integer sexo;
    @Size(max = 100)
    @Column(name = "contacto")
    private String contacto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColaborador")
    private Collection<ProyectoDet> proyectoDetCollection;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    @ManyToOne
    private Pais idPais;
    @OneToMany(mappedBy = "idColaborador")
    private Collection<MensajePrivado> mensajePrivadoCollection;

    public Colaborador() {
    }

    public Colaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Colaborador(Integer idColaborador, int idRango, String descripcion) {
        this.idColaborador = idColaborador;
        this.idRango = idRango;
        this.descripcion = descripcion;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public int getIdRango() {
        return idRango;
    }

    public void setIdRango(int idRango) {
        this.idRango = idRango;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Collection<ProyectoDet> getProyectoDetCollection() {
        return proyectoDetCollection;
    }

    public void setProyectoDetCollection(Collection<ProyectoDet> proyectoDetCollection) {
        this.proyectoDetCollection = proyectoDetCollection;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public Collection<MensajePrivado> getMensajePrivadoCollection() {
        return mensajePrivadoCollection;
    }

    public void setMensajePrivadoCollection(Collection<MensajePrivado> mensajePrivadoCollection) {
        this.mensajePrivadoCollection = mensajePrivadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColaborador != null ? idColaborador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colaborador)) {
            return false;
        }
        Colaborador other = (Colaborador) object;
        if ((this.idColaborador == null && other.idColaborador != null) || (this.idColaborador != null && !this.idColaborador.equals(other.idColaborador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.seriesweb.dto.Colaborador[ idColaborador=" + idColaborador + " ]";
    }
    
}
