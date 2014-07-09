package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.herramientas.ImagenEnvoltorio;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class ImagenDAO extends DAO{
    private final String TABLA = "imagen";
    private final String ID = Globales.BD_TABLA_ID + TABLA;
    private final String TODOS_CAMPOS = 
            ID + ", imagen, nombre, tipo, fecha_subida, descripcion ";
    
    private ImagenDTO dto;
    
    @Override
    public void asignarParametros(DTO dto){
        this.dto = (ImagenDTO) dto;
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("DAO " + TABLA + ": Parametros ingresados", ConsolaDebug.PROCESO);
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
                "SELECT " + select + " \n" +
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
            ImagenDTO dto = null;
            ResultSet resultados = BDConexion.consultar(consulta);
            
            while(resultados.next()){
                dto = new ImagenDTO();

                Blob blob = resultados.getBlob("imagen");
                byte[] datos = blob.getBytes(1, (int)blob.length());
                Image imagen = null; 
                try{
                    imagen = ImageIO.read(new ByteArrayInputStream(datos));
                }catch (IOException e) {
                    ConsolaDebug.agregarTexto("No se pudo leer el stream de la imagen: "+e, ConsolaDebug.ERROR);
                }

                dto.setIdImagen(resultados.getInt("id_imagen"));
                dto.setImagen(imagen);
                dto.setNombre(resultados.getString("nombre"));
                dto.setTipo(resultados.getString("tipo").charAt(0));
                dto.setFechaSubida(resultados.getTimestamp("fecha_subida"));
                dto.setDescripcion(resultados.getString("descripcion"));

                lista.add(dto);
            }
            
            BDConexion.cerrar();
        }catch(Exception e){
            ConsolaDebug.agregarTexto("DAO: Error al leer registros de " + Globales.BD_ESQUEMA + "." + TABLA + ": " + e, ConsolaDebug.ERROR);
        }
        
        return lista;
    }
    
    public DTO leerUno(String where){
        return leerUno(TODOS_CAMPOS, where, null, null);
    }
    
    public DTO leerUno(String select, String where, String orderBy, String groupBy){
        ImagenDTO dto = null;
        
        String consulta = 
                "SELECT " + select + " \n" +
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
            dto = new ImagenDTO();
            
            while(resultados.next()){
                Blob blob = resultados.getBlob("imagen");
                byte[] datos = blob.getBytes(1, (int)blob.length());
                Image imagen = null; 
                try{
                    imagen = ImageIO.read(new ByteArrayInputStream(datos));
                }catch (IOException e) {
                    ConsolaDebug.agregarTexto("No se pudo leer el stream de la imagen: "+e, ConsolaDebug.ERROR);
                }

                dto.setIdImagen(resultados.getInt("id_imagen"));
                dto.setImagen(imagen);
                dto.setNombre(resultados.getString("nombre"));
                dto.setTipo(resultados.getString("tipo").charAt(0));
                dto.setFechaSubida(resultados.getTimestamp("fecha_subida"));
                dto.setDescripcion(resultados.getString("descripcion"));
            }
            
            BDConexion.cerrar();
        }catch(Exception e){
            ConsolaDebug.agregarTexto("Error al leer un registro de " + Globales.BD_ESQUEMA + "." + TABLA + ": " + e, ConsolaDebug.ERROR);
        }
        
        return dto;
    }
    
    @Override
    public int agregar(){
        int resultado = 1;
        
        String accion = super.INSERTAR;
        int parametros = 5;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_ESQUEMA, accion, TABLA, parametros);
        
        Dimension dimension = null;
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(procedimiento + " : " + dto.getRutaImagen(), ConsolaDebug.PROCESO);

        try{
//            Connection conexion = BDConexion.getConnection();
//            
//            dimension = ImagenDTO.TipoImagen.TipoImagen(dto.getTipo()).getDimension();
//            
//            InputStream imagen = null;
//            int longitud = 0;
//            
//            String rutaTemporal = ImagenEnvoltorio.crearImagenTemporal(dimension, dto.getRutaImagen());
//            File archivo = new File(rutaTemporal);
//            imagen = new FileInputStream(archivo);
//            longitud = (int) archivo.length();
//                
//            CallableStatement procInsertar = conexion.prepareCall(procedimiento);
//            procInsertar.setBinaryStream(1,imagen, longitud);
//            procInsertar.setString(2, dto.getNombre());
//            procInsertar.setString(3, String.valueOf(dto.getTipo()));
//            procInsertar.setTimestamp(4, new java.sql.Timestamp(dto.getFechaSubida().getTime()));
//            procInsertar.setString(5, dto.getDescripcion());
//            procInsertar.execute();
//
//            conexion.commit();
//            BDConexion.cerrar();
            
            super.insertarBitacora(
                    TABLA,
                    TODOS_CAMPOS.replace(ID + ", imagen, ",""),
                        formatearValor(dto.getNombre()) + ", " +
                        formatearValor(dto.getTipo()) +", " +
                        formatearValor(new java.sql.Timestamp(dto.getFechaSubida().getTime())) + ", " +
                        formatearValor(dto.getDescripcion())
            );
            
            if(Globales.APP_DEBUG){
                if(ImagenEnvoltorio.eliminarImagenTemporal())
                    ConsolaDebug.agregarTexto("Imagen temporal eliminada satisfactoriamente", ConsolaDebug.PROCESO);
                else
                    ConsolaDebug.agregarTexto("No fue eliminada la imagen temporal", ConsolaDebug.ERROR);
            }
            
            resultado = 0;
        }catch(Exception e){
            ConsolaDebug.agregarTexto("No se pudo " + accion + " en la tabla " + Globales.BD_ESQUEMA + "." + TABLA + "\n"+e, ConsolaDebug.ERROR);
        }

        return resultado;
    }
    
    @Override
    public int modificar() {
        int resultado = 1;
        
        String accion = super.ACTUALIZAR;
        int parametros = 6;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_ESQUEMA, accion, TABLA, parametros);
        
        Dimension dimension = null;
               
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(procedimiento + " : " + dto.getRutaImagen(), ConsolaDebug.PROCESO);

        try{
            Connection conexion = BDConexion.getConnection();
            
            dimension = ImagenDTO.TipoImagen.TipoImagen(dto.getTipo()).getDimension();
            
            InputStream imagen = null;
            int longitud = 0;
            try{
                String rutaTemporal = ImagenEnvoltorio.crearImagenTemporal(dimension, dto.getRutaImagen());
                File archivo = new File(rutaTemporal);
                imagen = new FileInputStream(archivo);
                longitud = (int) archivo.length();
            }catch(Exception e){
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ImageIO .write((BufferedImage) dto.getImagen(), ImagenEnvoltorio.EXTENSION, byteStream);
                imagen = new ByteArrayInputStream(byteStream .toByteArray());
                longitud = imagen.available();
            }
                
            CallableStatement procActualizar = conexion.prepareCall(procedimiento);
            procActualizar.setInt(1, dto.getIdImagen());
            procActualizar.setBinaryStream(2,imagen, longitud);
            procActualizar.setString(3, dto.getNombre());
            procActualizar.setString(4, String.valueOf(dto.getTipo()));
            procActualizar.setTimestamp(5, new java.sql.Timestamp(dto.getFechaSubida().getTime()));
            procActualizar.setString(6, dto.getDescripcion());
            procActualizar.execute();

            conexion.commit();
            BDConexion.cerrar();
            
            if(Globales.APP_DEBUG){
                if(ImagenEnvoltorio.eliminarImagenTemporal())
                    ConsolaDebug.agregarTexto("Imagen temporal eliminada satisfactoriamente", ConsolaDebug.PROCESO);
                else
                    ConsolaDebug.agregarTexto("No fue eliminada la imagen temporal", ConsolaDebug.ERROR);
            }
            
            resultado = 0;
        }catch(Exception e){
            ConsolaDebug.agregarTexto("No se pudo " + accion + " en la tabla " + Globales.BD_ESQUEMA + "." + TABLA + "\n"+e, ConsolaDebug.ERROR);
        }

        return resultado;
    }

    @Override
    public int eliminar() {
        int resultado = 1;
        
        String accion = super.ELIMINAR;
        int parametros = 1;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_ESQUEMA, accion, TABLA, parametros);
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(procedimiento + " : ID - " + dto.getIdImagen(), ConsolaDebug.PROCESO);

        try{
            Connection conexion = BDConexion.getConnection();
                
            CallableStatement procEliminar = conexion.prepareCall(procedimiento);
            procEliminar.setInt(1, dto.getIdImagen());
            procEliminar.execute();

            conexion.commit();
            BDConexion.cerrar();
            
            resultado = 0;
        }catch(Exception e){
            ConsolaDebug.agregarTexto("No se pudo " + accion + " en la tabla " + Globales.BD_ESQUEMA + "." + TABLA + "\n"+e, ConsolaDebug.ERROR);
        }

        return resultado;
    }
}
