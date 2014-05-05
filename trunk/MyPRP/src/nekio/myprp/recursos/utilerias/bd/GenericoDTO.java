package nekio.myprp.recursos.utilerias.bd;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class GenericoDTO extends DTO{
    private List<Object> campo = new ArrayList<Object>();

    public List<Object> getCampo() {
        return campo;
    }

    public void setCampo(List<Object> campo) {
        this.campo = campo;
    }
}
