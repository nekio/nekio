package nekio.myprp.sistema.modulos.series.dao;

/**
 *
 * @author Nekio
 */

import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.sistema.modulos.series.dto.MensajePrivadoDTO;

public class MensajePrivadoDAO extends DAO{
    private final String TABLA = "mensaje_privado";
    private final String ID = Globales.BD_TABLA_ID + TABLA;
    private final String TODOS_CAMPOS = 
            ID + ", id_tipo_mensaje, id_mp_relacionado, id_web"
            + ", id_colaborador, mensaje, fecha, recibido_enviado, atendido ";
    
    private MensajePrivadoDTO dto;
    
    //@Override
    @Override
    public void asignarParametros(DTO dto){
        this.dto = (MensajePrivadoDTO) dto;
        
        if(Globales.APP_DEBUG){
            ConsolaDebug.agregarTexto("DAO " + TABLA + ": Parametros ingresados", ConsolaDebug.PROCESO);
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
            MensajePrivadoDTO dto = null;
            ResultSet resultados = BDConexion.consultar(consulta);
            
            while(resultados.next()){
                dto = new MensajePrivadoDTO();

                dto.setIdMensajePrivado(resultados.getInt("id_mensaje_privado"));
                dto.setIdTipoMensaje(resultados.getInt("id_tipo_mensaje"));
                dto.setIdMpRelacionado(resultados.getInt("id_mp_relacionado"));
                dto.setIdWeb(resultados.getInt("id_web"));
                dto.setIdColaborador(resultados.getInt("id_colaborador"));
                dto.setMensaje(resultados.getString("mensaje"));
                dto.setFecha(resultados.getTimestamp("fecha"));
                dto.setRecibidoEnviado(resultados.getInt("recibido_enviado")==1?true:false);
                dto.setAtendido(resultados.getInt("atendido")==1?true:false);

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
        MensajePrivadoDTO dto = null;
        
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
            dto = new MensajePrivadoDTO();
            
            while(resultados.next()){
                dto.setIdMensajePrivado(resultados.getInt("id_mensaje_privado"));
                dto.setIdTipoMensaje(resultados.getInt("id_tipo_mensaje"));
                dto.setIdMpRelacionado(resultados.getInt("id_mp_relacionado"));
                dto.setIdWeb(resultados.getInt("id_web"));
                dto.setIdColaborador(resultados.getInt("id_colaborador"));
                dto.setMensaje(resultados.getString("mensaje"));
                dto.setFecha(resultados.getTimestamp("fecha"));
                dto.setRecibidoEnviado(resultados.getInt("recibido_enviado")==1?true:false);
                dto.setAtendido(resultados.getInt("atendido")==1?true:false);
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
        int parametros = 8;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_ESQUEMA, accion, TABLA, parametros);
        
        Dimension dimension = null;
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.PROCESO);

        try{
            Connection conexion = BDConexion.getConnection();
                
            CallableStatement procInsertar = conexion.prepareCall(procedimiento);
            procInsertar.setInt(1, dto.getIdTipoMensaje());
            procInsertar.setInt(2, dto.getIdMpRelacionado());
            procInsertar.setInt(3, dto.getIdWeb());
            procInsertar.setInt(4, dto.getIdColaborador());
            procInsertar.setString(5, dto.getMensaje());
            procInsertar.setTimestamp(6, new java.sql.Timestamp(dto.getFecha().getTime()));
            procInsertar.setInt(7, dto.isRecibidoEnviado()==true?1:0);
            procInsertar.setInt(8, dto.isAtendido()==true?1:0);
            procInsertar.execute();

            conexion.commit();
            BDConexion.cerrar();
            
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
        int parametros = 9;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_ESQUEMA, accion, TABLA, parametros);
        
        Dimension dimension = null;
               
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.PROCESO);

        try{
            Connection conexion = BDConexion.getConnection();
            
            CallableStatement procActualizar = conexion.prepareCall(procedimiento);
            procActualizar.setInt(1, dto.getIdMensajePrivado());
            procActualizar.setInt(2, dto.getIdTipoMensaje());
            procActualizar.setInt(3, dto.getIdMpRelacionado());
            procActualizar.setInt(4, dto.getIdWeb());
            procActualizar.setInt(5, dto.getIdColaborador());
            procActualizar.setString(6, dto.getMensaje());
            procActualizar.setTimestamp(7, new java.sql.Timestamp(dto.getFecha().getTime()));
            procActualizar.setInt(8, dto.isRecibidoEnviado()==true?1:0);
            procActualizar.setInt(9, dto.isAtendido()==true?1:0);
            procActualizar.execute();

            conexion.commit();
            BDConexion.cerrar();
            
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
            ConsolaDebug.agregarTexto("\n" + procedimiento + " : ID - " + dto.getIdMensajePrivado(), ConsolaDebug.PROCESO);

        try{
            Connection conexion = BDConexion.getConnection();
                
            CallableStatement procEliminar = conexion.prepareCall(procedimiento);
            procEliminar.setInt(1, dto.getIdMensajePrivado());
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
