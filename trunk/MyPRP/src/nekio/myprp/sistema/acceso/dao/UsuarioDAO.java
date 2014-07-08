package nekio.myprp.sistema.acceso.dao;

/**
 *
 * @author Nekio
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

public class UsuarioDAO extends DAO{
    private final String TABLA = "usuario";
    private final String ID = Globales.BD_TABLA_ID + TABLA;
    private final String TODOS_CAMPOS = 
            ID + ", id_tipo_usuario, nombre, " + 
            "apellido_p, apellido_m, contacto, " + 
            "usuario, password, activo \n";
    
    private UsuarioDTO dto;
    
    @Override
    public void asignarParametros(DTO dto) {
        this.dto = (UsuarioDTO) dto;
        
        if(Globales.APP_DEBUG){
            ConsolaDebug.agregarTexto("Parametros ingresados", ConsolaDebug.PROCESO);
        }
    }
    
    @Override
    public ArrayList<DTO> leer(String where){
        return leer(TODOS_CAMPOS, where, null, null);
    }
    
    @Override
    public ArrayList<DTO> leerDesc(String where){
        return leer(TODOS_CAMPOS, where, ID + " DESC", null);
    }  
    
    public ArrayList<DTO> leer(String select, String where, String orderBy, String groupBy){
        ArrayList<DTO> lista = new ArrayList<DTO>();
        String consulta = 
                "SELECT " + select +
                "FROM " + Globales.BD_ESQUEMA + "." + TABLA + " \n" +
                "WHERE 1=1\n";
        
        if(where != null)
            consulta += "AND "+ where + "\n";
        if(orderBy != null)
            consulta += "ORDER BY "+ orderBy + "\n";
        if(groupBy != null)
            consulta += "GROUP BY "+ groupBy + "\n";
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(consulta, ConsolaDebug.SQL);
        
        try{
            UsuarioDTO dto = null;
            ResultSet resultados = BDConexion.consultar(consulta);
            
            while(resultados.next()){
                dto = new UsuarioDTO();
                
                dto.setIdUsuario(resultados.getInt("id_usuario"));
                dto.setIdTipoUsuario(resultados.getInt("id_tipo_usuario"));
                dto.setNombre(resultados.getString("nombre"));
                dto.setApellidoP(resultados.getString("apellido_p"));
                dto.setApellidoM(resultados.getString("apellido_m"));
                dto.setContacto(resultados.getString("contacto"));
                dto.setUsuario(resultados.getString("usuario"));
                dto.setPassword(resultados.getString("password"));
                dto.setActivo(resultados.getInt("activo"));
                
                lista.add(dto);
            }
            
            BDConexion.cerrar();
        }catch(Exception e){
            ConsolaDebug.agregarTexto("Error al leer registros de " + Globales.BD_ESQUEMA + "." + TABLA + ": " + e, ConsolaDebug.ERROR);
        }
        
        return lista;
    }
    
    public DTO leerUno(String where){
        return leerUno(TODOS_CAMPOS, where, null, null);
    }
    
    public DTO leerUno(String select, String where, String orderBy, String groupBy){
        UsuarioDTO dto = null;
        
        String consulta = 
                "SELECT " + select +
                "FROM " + Globales.BD_ESQUEMA + "." + TABLA + " \n" +
                "WHERE 1=1\n";
        
        if(where != null)
            consulta += "AND "+ where + "\n";
        if(orderBy != null)
            consulta += "ORDER BY "+ orderBy + "\n";
        if(groupBy != null)
            consulta += "GROUP BY "+ groupBy + "\n";
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(consulta, ConsolaDebug.SQL);
        
        try{
            ResultSet resultados = BDConexion.consultar(consulta);
            dto = new UsuarioDTO();
            
            while(resultados.next()){
                dto.setIdUsuario(resultados.getInt("id_usuario"));
                dto.setIdTipoUsuario(resultados.getInt("id_tipo_usuario"));
                dto.setNombre(resultados.getString("nombre"));
                dto.setApellidoP(resultados.getString("apellido_p"));
                dto.setApellidoM(resultados.getString("apellido_m"));
                dto.setContacto(resultados.getString("contacto"));
                dto.setUsuario(resultados.getString("usuario"));
                dto.setPassword(resultados.getString("password"));
                dto.setActivo(resultados.getInt("activo"));
            }
            
            BDConexion.cerrar();
        }catch(Exception e){
            ConsolaDebug.agregarTexto("Error al leer un registro de " + Globales.BD_ESQUEMA + "." + TABLA + ": " + e, ConsolaDebug.ERROR);
        }
        
        return dto;
    }

    @Override
    public int agregar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
