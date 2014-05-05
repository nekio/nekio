package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;

public abstract class DAO {
    public abstract ArrayList<DTO> leer(String where);   
    public abstract int agregar();
    public abstract int eliminar();
    public abstract int modificar();
}
