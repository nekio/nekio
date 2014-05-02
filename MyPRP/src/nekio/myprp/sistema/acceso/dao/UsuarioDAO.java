package nekio.myprp.sistema.acceso.dao;

/**
 *
 * @author Nekio
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

public class UsuarioDAO {
    public ArrayList<UsuarioDTO> leerUsuarios(){
        return leerUsuarios("");
    }
    public ArrayList<UsuarioDTO> leerUsuarios(String where){
        ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
        String consulta = "SELECT id_usuario, usuario, password, activo FROM usuario ";
        
        if(Globales.APP_DEBUG)
            System.out.println("\n"+consulta);
        
        try{
            UsuarioDTO dto = new UsuarioDTO();
            ResultSet resultados = BDConexion.consultar(consulta);
            
            while(resultados.next()){
                dto.setIdUsuario(resultados.getInt("id_usuario"));
                dto.setUsuario(resultados.getString("usuario"));
                dto.setPassword(resultados.getString("password"));
                dto.setActivo(resultados.getInt("activo"));
                
                lista.add(dto);
            }
            
            BDConexion.cerrar();
        }catch(Exception e){
            System.out.println("Error en Leer Usuarios: "+e);
        }
        
        return lista;
    }
}
