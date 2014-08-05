package nekio.myprp.recursos.generador;

/**
 *
 * @author Nekio
 */

 // <editor-fold defaultstate="collapsed" desc="Librerias">
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
// </editor-fold>

public abstract class Generador {
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    public static final String EXTENSION = ".java";
    
    public static enum Capas{
        DAO("DAO", "dao/"),
        DTO("DTO", "dto/"),
        OBJETO_NEGOCIO("", "negocio/"),
        GESTOR("Gestor",""),
        VISTA("Swing", "vista/");
        
        private String postfijo;
        private String paquete;
        
        private Capas(String postfijo, String paquete){
            this.postfijo = postfijo;
            this.paquete = paquete;
        }

        public String getPostfijo() {
            return postfijo;
        }

        public String getPaquete() {
            return paquete;
        }
    }
    
    protected List<String> codigoDTO;
    protected List<String> codigoDAO;
    protected List<String> codigoObjetoNegocio;
    protected List<String> codigoGestor;
    
    protected boolean estandar;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos Abstractos">
    protected abstract void crearDTO(String tabla, List<String> atributos, List<Globales.TipoDato> tipos);
    protected abstract void crearDAO(String tabla, List<List> llaves, List<String> campos, List<Globales.TipoDato> tipos);
    protected abstract void crearObjetoNegocio(String tabla);
    protected abstract void crearGestor(String tabla, String catalogo);
    protected abstract void crearVista();
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters y Getters">
    public List<String> getCodigoDTO() {
        return codigoDTO;
    }

    public void setCodigoDTO(List<String> codigoDTO) {
        this.codigoDTO = codigoDTO;
    }

    public List<String> getCodigoDAO() {
        return codigoDAO;
    }

    public void setCodigoDAO(List<String> codigoDAO) {
        this.codigoDAO = codigoDAO;
    }

    public List<String> getCodigoObjetoNegocio() {
        return codigoObjetoNegocio;
    }

    public void setCodigoObjetoNegocio(List<String> codigoObjetoNegocio) {
        this.codigoObjetoNegocio = codigoObjetoNegocio;
    }

    public List<String> getCodigoGestor() {
        return codigoGestor;
    }

    public void setCodigoGestor(List<String> codigoGestor) {
        this.codigoGestor = codigoGestor;
    }
    
    public boolean isEstandar() {
        return estandar;
    }

    public void setEstandar(boolean estandar) {
        this.estandar = estandar;
    }
    // </editor-fold>
}
