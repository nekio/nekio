package nekio.myprp.sistema.modulos.series;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.sistema.modulos.series.dao.MensajePrivadoDAO;
import nekio.myprp.sistema.modulos.series.negocio.MensajePrivado;

public class MensajePrivadoGestor extends Gestor{
    private final Gestor GESTOR = this;
    private final MensajePrivado OBJETO_NEGOCIO = new MensajePrivado();
    private final MensajePrivadoDAO DAO = new MensajePrivadoDAO();
    private final int MODULO = Globales.MOD_SERIES;
    
    public MensajePrivadoGestor(){
        super.gestor = GESTOR;
        super.objetoNegocio = OBJETO_NEGOCIO;
        super.dao = DAO;
        super.modulo = MODULO;
    }
}
