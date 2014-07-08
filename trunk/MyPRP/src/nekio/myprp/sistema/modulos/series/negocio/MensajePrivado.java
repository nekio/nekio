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
import nekio.myprp.sistema.modulos.series.dao.MensajePrivadoDAO;
import nekio.myprp.sistema.modulos.series.dto.MensajePrivadoDTO;

public class MensajePrivado extends ObjetoNegocio{
    @Override
    public String ejecutar(int metodo, Gestor gestor, DTO dto){
        String resultado = super.consultarAccion(metodo, new MensajePrivadoDAO(), dto);
        
        return resultado;
    }
    
    @Override
    public String consultarId(Gestor gestor) {
        MensajePrivadoDAO dao = new MensajePrivadoDAO();

        MensajePrivadoDTO dto = (MensajePrivadoDTO)gestor.getDTO();
        String id = String.valueOf(dto.getIdMensajePrivado());
        
        MensajePrivadoDTO filtroDTO = (MensajePrivadoDTO) dao.leerUno("id_mensaje_privado = " + id);
        
        gestor.setListaDTO(new ArrayList<DTO>());
        gestor.getListaDTO().add(filtroDTO);
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("DTOs obtenidos al consultar ID: " + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);
        
        return Globales.RES_OK;
    }
    
    @Override
    public String consultarSeleccion(Gestor gestor) {
        MensajePrivadoDAO dao = new MensajePrivadoDAO();
        gestor.setListaDTO(dao.leer(null));
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("DTOs obtenidos al consultar seleccion: " + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);
        
        return Globales.RES_OK;
    }
    
    @Override
    public String consultarSeleccionDesc(Gestor gestor) {
        MensajePrivadoDAO dao = new MensajePrivadoDAO();
        gestor.setListaDTO(dao.leerDesc(null));
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("DTOs obtenidos al consultar seleccion: " + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);
        
        return Globales.RES_OK;
    }

    @Override
    public String consultarBusqueda(Gestor gestor) {
        String where =" WHERE 1=1 ";

        if(!super.busqueda.equals("") && super.busqueda != null)
                where += "AND (mensaje) " +
                          "LIKE '%" +   super.busqueda + "%'";

        DAO dao = new MensajePrivadoDAO();
        gestor.setListaDTO(dao.leer(where));
        
        if(Globales.APP_DEBUG){
            ConsolaDebug.agregarTexto("Consulta de busqueda:" + where, ConsolaDebug.SQL);
            ConsolaDebug.agregarTexto("DTOs obtenidos al consultar Busqueda: " + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);
        }

        return Globales.RES_OK; 
    }
    
    @Override
    public MensajePrivadoDTO obtenerModelo(Gestor gestor){
        MensajePrivadoDTO dto = new MensajePrivadoDTO();
        
        return dto;
    }
}
