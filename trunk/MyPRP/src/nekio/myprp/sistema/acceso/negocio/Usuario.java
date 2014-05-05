package nekio.myprp.sistema.acceso.negocio;

/**
 *
 * @author Nekio
 */

import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.ObjetoNegocio;
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

public class Usuario extends ObjetoNegocio{
    private List<DTO> usuario;
    private String busqueda;
    
    @Override
    public String ejecutar(int metodo, Gestor gestor){
        String resultado = super.consultarAccion(metodo, new UsuarioDAO());
        
        return resultado;
    }
    
    @Override
    public String consultarSeleccion(Gestor gestor) {
        UsuarioDAO dao = new UsuarioDAO();

        String idUsuario = gestor.getParametros().get(0);
        UsuarioDTO usuario = dao.leerUno("id_usuario = " + idUsuario);
        
        //Operaciones con el DTO obtenido
        //....
        
        return Globales.RES_OK;
    }

    @Override
    public String consultarBusqueda(Gestor gestor) {
        String where =" WHERE 1=1 ";

        if(!busqueda.equals("") && busqueda != null)
                where += "AND (protocol + systemName + ecuHardware + " +
                                          "ecuType + engineProject + CONVERT(VARCHAR(30),displacement) + " +
                                          "emissionConcept + platform + power + " +
                                          "gearbox + vehicle + manufacturer) " +
                                 "LIKE '%" +   busqueda + "%'";

        System.out.println("Campo búsqueda:" + busqueda);

        DAO dao = new UsuarioDAO();
        //conexion

        usuario = dao.leer(/*conexion,*/where);

        return Globales.RES_OK; 
    }
    
    @Override
    public UsuarioDTO obtenerModelo(Gestor gestor){
        UsuarioDTO dto = new UsuarioDTO();
        
        return dto;
    }
}