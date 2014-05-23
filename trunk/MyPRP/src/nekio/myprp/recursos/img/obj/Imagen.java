package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.ObjetoNegocio;

public class Imagen extends ObjetoNegocio{    
    @Override
    public String ejecutar(int metodo, Gestor gestor, DTO dto){
        String resultado = super.consultarAccion(metodo, new ImagenDAO(), dto);
        
        return resultado;
    }
    
    @Override
    public String consultarId(Gestor gestor) {
        ImagenDAO dao = new ImagenDAO();

        ImagenDTO dto = (ImagenDTO)gestor.getDTO();
        String id = String.valueOf(dto.getIdImagen());
        
        ImagenDTO filtroDTO = (ImagenDTO) dao.leerUno("id_imagen = " + id);
        
        gestor.setListaDTO(new ArrayList<DTO>());
        gestor.getListaDTO().add(filtroDTO);
        
        if(Globales.APP_DEBUG)
            System.out.println("\nDTOs obtenidos al consultar ID: " + gestor.getListaDTO().size());
        
        return Globales.RES_OK;
    }
    
    @Override
    public String consultarSeleccion(Gestor gestor) {
        ImagenDAO dao = new ImagenDAO();
        gestor.setListaDTO(dao.leer(null));
        
        if(Globales.APP_DEBUG)
            System.out.println("\nDTOs obtenidos al consultar seleccion: " + gestor.getListaDTO().size());
        
        return Globales.RES_OK;
    }
    
    @Override
    public String consultarSeleccionDesc(Gestor gestor) {
        ImagenDAO dao = new ImagenDAO();
        gestor.setListaDTO(dao.leerDesc(null));
        
        if(Globales.APP_DEBUG)
            System.out.println("\nDTOs obtenidos al consultar seleccion: " + gestor.getListaDTO().size());
        
        return Globales.RES_OK;
    }

    @Override
    public String consultarBusqueda(Gestor gestor) {
        String where =" WHERE 1=1 ";

        if(!super.busqueda.equals("") && super.busqueda != null)
                where += "AND (nombre + descripcion) " +
                          "LIKE '%" +   super.busqueda + "%'";

        DAO dao = new ImagenDAO();
        gestor.setListaDTO(dao.leer(where));
        
        if(Globales.APP_DEBUG){
            System.out.println("Consulta de busqueda:" + where);
            System.out.println("DTOs obtenidos al consultar Busqueda: " + gestor.getListaDTO().size());
        }

        return Globales.RES_OK; 
    }
    
    @Override
    public ImagenDTO obtenerModelo(Gestor gestor){
        ImagenDTO dto = new ImagenDTO();
        
        return dto;
    }
}
