package nekio.myprp.sistema.acceso.negocio;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
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
    public String consultarSeleccion(Gestor gestor) {
        UsuarioDAO dao = new UsuarioDAO();

        String idUsuario = (String) gestor.getParametros().get(0);
        UsuarioDTO usuario = (UsuarioDTO) dao.leerUno("id_usuario = " + idUsuario);
        
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

        listaDTO = dao.leer(/*conexion,*/where);

        return Globales.RES_OK; 
    }
    
    @Override
    public UsuarioDTO obtenerModelo(Gestor gestor){
        UsuarioDTO dto = new UsuarioDTO();
        
        return dto;
    }
}