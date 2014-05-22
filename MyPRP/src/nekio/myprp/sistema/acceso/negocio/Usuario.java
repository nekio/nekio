package nekio.myprp.sistema.acceso.negocio;

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
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

public class Usuario extends ObjetoNegocio{
    @Override
    public String ejecutar(int metodo, Gestor gestor, ArrayList parametros){
        String resultado = super.consultarAccion(metodo, new UsuarioDAO(), parametros);
        
        return resultado;
    }
    
    @Override
    public String consultarId(Gestor gestor) {
        UsuarioDAO dao = new UsuarioDAO();

        String id = (String) gestor.getParametros().get(0);
        UsuarioDTO dto = (UsuarioDTO) dao.leerUno("id_usuario = " + id);
        
        gestor.setListaDTO(new ArrayList<DTO>());
        gestor.getListaDTO().add(dto);
        
        if(Globales.APP_DEBUG)
            System.out.println("\nDTOs obtenidos al consultar ID: " + gestor.getListaDTO().size());
        
        return Globales.RES_OK;
    }
    
    @Override
    public String consultarSeleccion(Gestor gestor) {
        UsuarioDAO dao = new UsuarioDAO();
        gestor.setListaDTO(dao.leer(null));
        
        if(Globales.APP_DEBUG)
            System.out.println("\nDTOs obtenidos al consultar seleccion: " + gestor.getListaDTO().size());
        
        return Globales.RES_OK;
    }

    @Override
    public String consultarBusqueda(Gestor gestor) {
        String where =" WHERE 1=1 ";

        if(!super.busqueda.equals("") && super.busqueda != null)
                where += "AND (protocol + systemName + ecuHardware + " +
                                "ecuType + engineProject + CONVERT(VARCHAR(30),displacement) + " +
                                "emissionConcept + platform + power + " +
                                "gearbox + vehicle + manufacturer) " +
                           "LIKE '%" +   super.busqueda + "%'";

        DAO dao = new UsuarioDAO();
        gestor.setListaDTO(dao.leer(where));
        
        if(Globales.APP_DEBUG){
            System.out.println("Consulta de busqueda:" + where);
            System.out.println("DTOs obtenidos al consultar Busqueda: " + gestor.getListaDTO().size());
        }

        return Globales.RES_OK; 
    }
    
    @Override
    public UsuarioDTO obtenerModelo(Gestor gestor){
        UsuarioDTO dto = new UsuarioDTO();
        
        return dto;
    }
}