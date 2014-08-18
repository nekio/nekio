package nekio.myprp.sistema.acceso.dao;

/**
 *
 * @author Nekio
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.sistema.acceso.dto.ModalidadDTO;

public class ModalidadDAO extends DAO {

    private final String TABLA = "modalidad";
    private final String ID = "app_debug, app_design, bitacora_estilo";
    private final String TODOS_CAMPOS
            = ID + " ";

    private ModalidadDTO dto;

    public DTO leerUno(String where) {
        return leerUno(TODOS_CAMPOS, where, null, null);
    }

    public DTO leerUno(String select, String where, String orderBy, String groupBy) {
        ModalidadDTO dto = null;

        String consulta
                = "SELECT " + select + " \n"
                + "FROM " + Globales.BD_DESC_ESQUEMA + "." + TABLA + " \n"
                + "WHERE 1=1\n";

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto(consulta, ConsolaDebug.SQL);
        }

        try {
            ResultSet resultados = BDConexion.consultar(consulta);
            dto = new ModalidadDTO();

            while (resultados.next()) {
                dto.setAppDebug(resultados.getInt("app_debug") == 1 ? true : false);
                dto.setAppDesign(resultados.getInt("app_design") == 1 ? true : false);
                dto.setBitacoraEstilo(resultados.getInt("bitacora_estilo") == 1 ? true : false);
            }

            BDConexion.cerrar();
        } catch (Exception e) {
            ConsolaDebug.agregarTexto("Error al leer un registro de " + Globales.BD_DESC_ESQUEMA + "." + TABLA + ": " + e, ConsolaDebug.ERROR);
        }

        return dto;
    }

    @Override public void asignarParametros(DTO dto) {}
    @Override public ArrayList<DTO> leer(String where) { return null; }
    @Override public ArrayList<DTO> leerDesc(String where) { return null; }
    @Override public int agregar() { return 1; }
    @Override public int modificar() { return 1; }
    @Override public int eliminar() { return 1; }
}
