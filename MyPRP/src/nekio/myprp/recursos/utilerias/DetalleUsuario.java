package nekio.myprp.recursos.utilerias;

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.util.List;
// </editor-fold>

/**
 *
 * @author Nekio
 */
public class DetalleUsuario {
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private int id;
    private String nick;
    
    private String rangoDesc;
    private int rangoId;
    private boolean accPublico;
    private boolean accGrupal;
    private boolean accProtegido;
    private boolean accPrivado;
    
    private String tipoDesc;
    private int tipoId;
    private boolean configurar;
    private boolean buscar;
    private boolean insertar;
    private boolean modificar;
    private boolean eliminar;
    
    private List<Integer> sistemas;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Setters/Getters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRangoDesc() {
        return rangoDesc;
    }

    public void setRangoDesc(String rangoDesc) {
        this.rangoDesc = rangoDesc;
    }

    public int getRangoId() {
        return rangoId;
    }

    public void setRangoId(int rangoId) {
        this.rangoId = rangoId;
    }

    public boolean isAccPublico() {
        return accPublico;
    }

    public void setAccPublico(boolean accPublico) {
        this.accPublico = accPublico;
    }

    public boolean isAccGrupal() {
        return accGrupal;
    }

    public void setAccGrupal(boolean accGrupal) {
        this.accGrupal = accGrupal;
    }

    public boolean isAccProtegido() {
        return accProtegido;
    }

    public void setAccProtegido(boolean accProtegido) {
        this.accProtegido = accProtegido;
    }

    public boolean isAccPrivado() {
        return accPrivado;
    }

    public void setAccPrivado(boolean accPrivado) {
        this.accPrivado = accPrivado;
    }

    public String getTipoDesc() {
        return tipoDesc;
    }

    public void setTipoDesc(String tipoDesc) {
        this.tipoDesc = tipoDesc;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public boolean isConfigurar() {
        return configurar;
    }

    public void setConfigurar(boolean configurar) {
        this.configurar = configurar;
    }

    public boolean isBuscar() {
        return buscar;
    }

    public void setBuscar(boolean buscar) {
        this.buscar = buscar;
    }

    public boolean isInsertar() {
        return insertar;
    }

    public void setInsertar(boolean insertar) {
        this.insertar = insertar;
    }

    public boolean isModificar() {
        return modificar;
    }

    public void setModificar(boolean modificar) {
        this.modificar = modificar;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public List<Integer> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<Integer> sistemas) {
        this.sistemas = sistemas;
    }
    // </editor-fold>
}
