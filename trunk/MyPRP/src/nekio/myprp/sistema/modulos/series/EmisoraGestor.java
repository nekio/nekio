package nekio.myprp.sistema.modulos.series;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.sistema.modulos.series.dao.EmisoraDAO;
import nekio.myprp.sistema.modulos.series.negocio.Emisora;

public class EmisoraGestor extends Gestor {

    private final Gestor GESTOR = this;
    private final Emisora OBJETO_NEGOCIO = new Emisora();
    private final EmisoraDAO DAO = new EmisoraDAO();
    private final int MODULO = Globales.MOD_SERIES;

    public EmisoraGestor() {
        super.gestor = GESTOR;
        super.objetoNegocio = OBJETO_NEGOCIO;
        super.dao = DAO;
        super.modulo = MODULO;
    }
}
