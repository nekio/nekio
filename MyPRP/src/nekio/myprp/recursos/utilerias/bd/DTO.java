package nekio.myprp.recursos.utilerias.bd;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nekio
 */
public class DTO {
    private List<Object> campo = new ArrayList<Object>();

    public List<Object> getCampo() {
        return campo;
    }

    public void setCampo(List<Object> campo) {
        this.campo = campo;
    }
}
