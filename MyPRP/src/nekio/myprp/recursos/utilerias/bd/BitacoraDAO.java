package nekio.myprp.recursos.utilerias.bd;

/**
 *
 * @author Nekio
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class BitacoraDAO extends DAO{
    private final String TABLA = "bitacora";
    private final String ID = Globales.BD_TABLA_ID + TABLA;
    private final String TODOS_CAMPOS = 
            ID + ", id_sistema, id_usuario, tabla, accion, dml, fecha ";
    
    private BitacoraDTO dto;
    
    @Override
    public void asignarParametros(DTO dto){
        this.dto = (BitacoraDTO) dto;
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("DAO " + TABLA + ": Parametros ingresados", ConsolaDebug.BITACORA);
    }
    
    @Override 
    public int agregar(){
        int resultado = 1;
        
        String accion = super.INSERTAR;
        int parametros = 6;
        String procedimiento = obtenerProcedimiento(accion, TABLA, parametros);
                
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.BITACORA);

        try{
            Connection conexion = BDConexion.getConnection();
            
            CallableStatement procInsertar = conexion.prepareCall(procedimiento);
            procInsertar.setInt(1, dto.getIdSistema());
            procInsertar.setInt(2, dto.getIdUsuario());
            procInsertar.setString(3, dto.getTabla());
            procInsertar.setString(4, String.valueOf(dto.getAccion()));
            procInsertar.setString(5, dto.getpCampos());
            procInsertar.setString(6, dto.getpValores());
            procInsertar.execute();

            conexion.commit();
            BDConexion.cerrar();
                        
            ConsolaDebug.agregarTexto("Evento registrado en la bitacora satisfactoriamente", ConsolaDebug.BITACORA);
            resultado = 0;
        }catch(Exception e){
            ConsolaDebug.agregarTexto("No se pudo " + accion + " en la tabla " + Globales.BD_DESC_ESQUEMA + "." + TABLA + "\n"+e, ConsolaDebug.BITACORA);
        }
        
        return resultado;
    }

    @Override public List<DTO> leer(String where) { return null; }
    @Override public List<DTO> leerDesc(String where) { return null; }
    @Override public int modificar() { return 0; }
    @Override public int eliminar() { return 0; }
}
