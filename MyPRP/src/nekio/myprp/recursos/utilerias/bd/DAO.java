package nekio.myprp.recursos.utilerias.bd;

/**
 *
 * @author Nekio
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;

public class DAO {
    public ArrayList<DTO> leer(){
        return leer("", "");
    }
    
    public ArrayList<DTO> leer(String select, String from){
        return leer(select, from, null, null, null);
    }
    
    public ArrayList<DTO> leer(String select, String from, String where, String orderBy, String groupBy){
        ArrayList<DTO> listaDTO = null;
        
        String consulta = 
                "SELECT " + select + "\n" +
                "FROM "+ from + "\n" +
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
            
            int columnas = resultados.getMetaData().getColumnCount();
            
            listaDTO = new ArrayList<DTO>();
            DTO dto = null;
            List<Object> campoDTO = null;
            
            while(resultados.next()){
                dto = new DTO();
                
                campoDTO = new ArrayList<Object>();
                for(int columna=0; columna < columnas; columna++)
                    campoDTO.add(resultados.getObject(columna+1));
                dto.setCampo(campoDTO);
                
                listaDTO.add(dto);
            }
            
            BDConexion.cerrar();
        }catch(Exception e){
            System.out.println("Error en DAO Generico, Leer: "+e);
        }
        
        return listaDTO;
    }
}
