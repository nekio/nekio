package nekio.myprp.sistema.acceso.dao;

/**
 *
 * @author Nekio
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

public class UsuarioDAO extends DAO{
    private final String TODOS_CAMPOS = 
            "id_usuario, id_tipo_usuario, nombre, " + 
            "apellido_p, apellido_m, contacto, " + 
            "usuario, password, activo \n";
    
    private int idTipoUsuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String contacto;
    private String usuario;
    private String password;
    private int activo;
    
    @Override
    public void asignarParametros(ArrayList parametros) {
        idTipoUsuario = Integer.valueOf(String.valueOf(parametros.get(0)));
        nombre = String.valueOf(parametros.get(1));
        apellidoP = String.valueOf(parametros.get(2));
        apellidoM = String.valueOf(parametros.get(3));
        contacto = String.valueOf(parametros.get(4));
        usuario = String.valueOf(parametros.get(5));
        password = String.valueOf(parametros.get(6));
        activo = Integer.valueOf(String.valueOf(parametros.get(7)));
        
        if(Globales.APP_DEBUG){
            System.out.println("\nParametros ingresados");
            for(Object parametro:parametros)
                System.out.println(parametro);
        }
    }
    
    @Override
    public ArrayList<DTO> leer(String where){
        return leer(TODOS_CAMPOS, where, null, null);
    }
    
    public ArrayList<DTO> leer(String select, String where, String orderBy, String groupBy){
        ArrayList<DTO> lista = new ArrayList<DTO>();
        String consulta = 
                "SELECT " + select +
                "FROM usuario \n" +
                "WHERE 1=1\n";
        
        if(where != null)
            consulta += "AND "+ where + "\n";
        if(orderBy != null)
            consulta += "ORDER BY "+ orderBy + "\n";
        if(groupBy != null)
            consulta += "GROUP BY "+ groupBy + "\n";
        
        if(Globales.APP_DEBUG)
            System.out.println("\n"+consulta);
        
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
            System.out.println("Error en Leer Usuarios: " + e);
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
                "FROM usuario \n" +
                "WHERE 1=1\n";
        
        if(where != null)
            consulta += "AND "+ where + "\n";
        if(orderBy != null)
            consulta += "ORDER BY "+ orderBy + "\n";
        if(groupBy != null)
            consulta += "GROUP BY "+ groupBy + "\n";
        
        if(Globales.APP_DEBUG)
            System.out.println("\n"+consulta);
        
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
            System.out.println("Error en Leer un Usuario: " + e);
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
