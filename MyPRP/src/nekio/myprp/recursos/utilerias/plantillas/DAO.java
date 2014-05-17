package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;

public abstract class DAO {
    private final String SEPARADOR = "_";
    protected final String INSERTAR = "insertar" + SEPARADOR;
    protected final String ACTUALIZAR = "actualizar" + SEPARADOR;
    protected final String ELIMINAR = "eliminar" + SEPARADOR;
    
    public String obtenerProcedimiento(String esquema, String dml, String tabla, int numParametros){
        String parametros = "?";
        for(int i=1; i<numParametros; i++)
            parametros += ", ?";
        
        String procedimiento = "{ call " + esquema + "." + dml + tabla + "(" + parametros + ") }";
        
        return procedimiento;
    }
    
    public abstract void asignarParametros(ArrayList parametros);
    public abstract ArrayList<DTO> leer(String where);   
    public abstract int agregar();
    public abstract int eliminar();
    public abstract int modificar();
}
