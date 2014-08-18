package nekio.myprp.sistema.modulos.series.negocio;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.ObjetoNegocio;
import nekio.myprp.sistema.modulos.series.dao.EmisoraDAO;
import nekio.myprp.sistema.modulos.series.dto.EmisoraDTO;

public class Emisora extends ObjetoNegocio {

    @Override
    public String ejecutar(int metodo, Gestor gestor, DTO dto) {
        String resultado = super.consultarAccion(metodo, new EmisoraDAO(), dto);

        return resultado;
    }

    @Override
    public String consultarId(Gestor gestor) {
        EmisoraDAO dao = new EmisoraDAO();
        EmisoraDTO dto = (EmisoraDTO) gestor.getDTO();
        String id = String.valueOf(dto.getIdEmisora());

        EmisoraDTO filtroDTO = (EmisoraDTO) dao.leerUno("id_emisora = " + id);

        gestor.setListaDTO(new ArrayList<DTO>());
        gestor.getListaDTO().add(filtroDTO);

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto("DTOs obtenidos al consultar ID: " + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);
        }

        return Globales.RES_OK;
    }

    @Override
    public String consultarSeleccion(Gestor gestor) {
        EmisoraDAO dao = new EmisoraDAO();
        gestor.setListaDTO(dao.leer(null));

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto("DTOs obtenidos al consultar seleccion: " + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);
        }

        return Globales.RES_OK;
    }

    @Override
    public String consultarSeleccionDesc(Gestor gestor) {
        EmisoraDAO dao = new EmisoraDAO();
        gestor.setListaDTO(dao.leerDesc(null));

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto("DTOs obtenidos al consultar seleccion: " + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);
        }

        return Globales.RES_OK;
    }

    @Override
    public String consultarBusqueda(Gestor gestor) {
        String where = " WHERE 1=1 ";

        if (!super.busqueda.equals("") && super.busqueda != null) {
            where += "AND (descripcion) "
                    + "LIKE '%" + super.busqueda + "%'";
        }

        DAO dao = new EmisoraDAO();
        gestor.setListaDTO(dao.leer(where));

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto("Consulta de busqueda:" + where, ConsolaDebug.SQL);
            ConsolaDebug.agregarTexto("DTOs obtenidos al consultar Busqueda: " + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);
        }

        return Globales.RES_OK;
    }

    @Override
    public EmisoraDTO obtenerModelo(Gestor gestor) {
        EmisoraDTO dto = new EmisoraDTO();

        return dto;
    }
}
