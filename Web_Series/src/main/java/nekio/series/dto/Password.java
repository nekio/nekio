package nekio.series.dto;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author SITI
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Password.findAll", query = "SELECT p FROM Password p"),
    @NamedQuery(name = "Password.findByIdPassword", query = "SELECT p FROM Password p WHERE p.idPassword = :idPassword"),
    @NamedQuery(name = "Password.findByPassword", query = "SELECT p FROM Password p WHERE p.password = :password"),
    @NamedQuery(name = "Password.findByComentarios", query = "SELECT p FROM Password p WHERE p.comentarios = :comentarios")})
public class Password implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_password")
    private Integer idPassword;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    private String password;
    @Size(max = 255)
    private String comentarios;
    @JoinColumn(name = "id_rango", referencedColumnName = "id_rango")
    @ManyToOne(optional = false)
    private XRango idRango;
    @JoinColumn(name = "id_proyecto", referencedColumnName = "id_proyecto")
    @ManyToOne
    private Proyecto idProyecto;
    @JoinColumn(name = "id_serie", referencedColumnName = "id_serie")
    @ManyToOne
    private Serie idSerie;
    @JoinColumn(name = "id_episodio", referencedColumnName = "id_episodio")
    @ManyToOne
    private Episodio idEpisodio;

    public Password() {
    }

    public Password(Integer idPassword) {
        this.idPassword = idPassword;
    }

    public Password(Integer idPassword, String password) {
        this.idPassword = idPassword;
        this.password = password;
    }

    public Integer getIdPassword() {
        return idPassword;
    }

    public void setIdPassword(Integer idPassword) {
        this.idPassword = idPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public XRango getIdRango() {
        return idRango;
    }

    public void setIdRango(XRango idRango) {
        this.idRango = idRango;
    }

    public Proyecto getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Proyecto idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Serie getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Serie idSerie) {
        this.idSerie = idSerie;
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
        hash += (idPassword != null ? idPassword.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Password)) {
            return false;
        }
        Password other = (Password) object;
        if ((this.idPassword == null && other.idPassword != null) || (this.idPassword != null && !this.idPassword.equals(other.idPassword))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nekio.series.dto.Password[ idPassword=" + idPassword + " ]";
    }
    
}
