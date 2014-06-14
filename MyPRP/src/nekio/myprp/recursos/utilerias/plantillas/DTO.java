package nekio.myprp.recursos.utilerias.plantillas;

import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;

/**
 *
 * @author Nekio
 */

public abstract class DTO {
    protected List<String> campos;
    protected List valores;
    protected List<Globales.TipoDato> tipoDatos;
    
    public List<String> getCampos() {        
        return campos;
    }
    
    public List getValores(){
        return valores;
    }
    
    public List<Globales.TipoDato> getTipoDatos(){
        return tipoDatos;
    }
    
    public abstract void confirmarDTO(); //Metodo para inicializar las listas de clase
}
