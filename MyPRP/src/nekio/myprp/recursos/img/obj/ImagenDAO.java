package nekio.myprp.recursos.img.obj;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

/**
 *
 * @author Nekio
 */
public class ImagenDAO extends DAO{
    private final String TODOS_CAMPOS = "id_imagen, imagen, nombre, descripcion \n";
    
    private String rutaImagen;
    private String nombre;
    private String descripcion;
    
    @Override
    public void asignarParametros(ArrayList parametros) {
        rutaImagen = String.valueOf(parametros.get(0));
        nombre = String.valueOf(parametros.get(1));
        descripcion = String.valueOf(parametros.get(2));
        
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
                "FROM imagen \n" +
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
            ImagenDTO dto = null;
            ResultSet resultados = BDConexion.consultar(consulta);
            
            while(resultados.next()){
                dto = new ImagenDTO();

                Blob blob = resultados.getBlob("imagen");
                byte[] datos = blob.getBytes(1, (int)blob.length());
                BufferedImage imagen = null; 
                try{
                    imagen = ImageIO.read(new ByteArrayInputStream(datos));
                }catch (IOException e) {
                    System.out.println("No se pudo leer el stream de la imagen: "+e);
                }

                dto.setIdImagen(resultados.getInt("id_imagen"));
                dto.setImagen(imagen);
                dto.setNombre(resultados.getString("nombre"));
                dto.setDescripcion(resultados.getString("descripcion"));

                lista.add(dto);
            }
            
            BDConexion.cerrar();
        }catch(Exception e){
            System.out.println("Error al leer Imagenes: " + e);
        }
        
        return lista;
    }
    
    public DTO leerUno(String where){
        return leerUno(TODOS_CAMPOS, where, null, null);
    }
    
    public DTO leerUno(String select, String where, String orderBy, String groupBy){
        ImagenDTO dto = null;
        
        String consulta = 
                "SELECT " + select +
                "FROM imagen \n" +
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
            dto = new ImagenDTO();
            
            while(resultados.next()){
                Blob blob = resultados.getBlob("imagen");
                byte[] datos = blob.getBytes(1, (int)blob.length());
                BufferedImage imagen = null; 
                try{
                    imagen = ImageIO.read(new ByteArrayInputStream(datos));
                }catch (IOException e) {
                    System.out.println("No se pudo leer el stream de la imagen: "+e);
                }

                dto.setIdImagen(resultados.getInt("id_imagen"));
                dto.setImagen(imagen);
                dto.setNombre(resultados.getString("nombre"));
                dto.setDescripcion(resultados.getString("descripcion"));
            }
            
            BDConexion.cerrar();
        }catch(Exception e){
            System.out.println("Error al leer una Imagene: " + e);
        }
        
        return dto;
    }
    
    @Override
    public int agregar(){
        int resultado = 0;
        
        FileInputStream imagen = null;
        String procedimiento = "{ call catalogador_series.insertar_imagen(?, ?, ?) }";
        
        if(Globales.APP_DEBUG)
            System.out.println("\n" + procedimiento + " : " + rutaImagen);

        try{
            Connection conexion = BDConexion.getConnection();
            File archivo = new File(rutaImagen);
            imagen = new FileInputStream(archivo);
                
            CallableStatement procInsertarImagen = conexion.prepareCall(procedimiento);
            procInsertarImagen.setBinaryStream(1,imagen,(int)archivo.length());
            procInsertarImagen.setString(2, nombre);
            procInsertarImagen.setString(3, descripcion);
            procInsertarImagen.execute();

            conexion.commit();
            BDConexion.cerrar();
            
            resultado = 1;
        }catch(Exception e){
            System.out.println("No se pudo insertar la imagen\n"+e);
        }

        return resultado;
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
