package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;

public class ImagenGestor extends Gestor{
    private final Gestor GESTOR = this;
    private final Imagen OBJETO_NEGOCIO = new Imagen();
    private final ImagenDAO DAO = new ImagenDAO();
    private final int MODULO = Globales.MOD_IMAGEN;
    
    public ImagenGestor(){
        super.gestor = GESTOR;
        super.objetoNegocio = OBJETO_NEGOCIO;
        super.dao = DAO;
        super.modulo = MODULO;
    }
}

