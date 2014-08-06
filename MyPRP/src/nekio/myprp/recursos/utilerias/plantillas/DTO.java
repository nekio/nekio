package nekio.myprp.recursos.utilerias.plantillas;

import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;

/**
 *
 * @author Nekio
 */

public abstract class DTO {
    protected List<String> campos;
    protected List<String> tablasForaneas;
    protected List valores;
    protected List<Globales.TipoDato> tipoDatos;
    protected List<String> valoresLOV;
    protected List<List> camposExtrasLOV;
    
    public List<String> getCampos() {        
        return campos;
    }
    
    public List<String> getTablasForaneas() {
        return tablasForaneas;
    }
    
    public List getValores(){
        return valores;
    }
    
    public List<Globales.TipoDato> getTipoDatos(){
        return tipoDatos;
    }

    public List<String> getLOVValores() {
        return valoresLOV;
    }
    
    public List<List> getCamposExtrasLOV() {
        return camposExtrasLOV;
    }
    
    public abstract void confirmarDTO(); //Metodo para inicializar las listas de clase
}
