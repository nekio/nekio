package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BitacoraDAO;
import nekio.myprp.recursos.utilerias.bd.BitacoraDTO;
// </editor-fold>

public abstract class DAO {
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private final String SEPARADOR = "_";
    
    protected final String INSERTAR = "insertar" + SEPARADOR;
    protected final String ACTUALIZAR = "actualizar" + SEPARADOR;
    protected final String ELIMINAR = "eliminar" + SEPARADOR;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos">
    
    // <editor-fold defaultstate="collapsed" desc="Obtener Procedimiento">
    public String obtenerProcedimiento(String dml, String tabla, int numParametros){
        return obtenerProcedimiento(Globales.BD_TOOLS, dml, tabla, numParametros);
    }
    
    public String obtenerProcedimiento(String esquema, String dml, String tabla, int numParametros){
        String parametros = "?";
        for(int i=1; i<numParametros; i++)
            parametros += ", ?";
        
        String procedimiento = "{ call " + esquema + "." + dml + tabla + "(" + parametros + ") }";
        
        return procedimiento;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Insertar Bitacora">
    public void insertarBitacora(String tabla, String campos, String valores, boolean insertar){
        char accion = ' ';
        
        if(insertar)
            accion = 'I';
        else
            accion = 'U';
        
        BitacoraDTO dtoBitacora = new BitacoraDTO();
        dtoBitacora.setIdSistema(Globales.ID_SISTEMA);
        dtoBitacora.setIdUsuario(Globales.BD_ID_USUARIO);
        dtoBitacora.setTabla(tabla);
        dtoBitacora.setAccion(accion);
        dtoBitacora.setpCampos(campos);
        dtoBitacora.setpValores(valores);

        BitacoraDAO daoBitacora = new BitacoraDAO();
        daoBitacora.asignarParametros(dtoBitacora);
        
        if(insertar){
            if(daoBitacora.agregar() == 1)
                ConsolaDebug.agregarTexto("Ocurrio un error al insertar el DML INSERT en la bitacora", ConsolaDebug.ERROR);
        }else{
            if(daoBitacora.modificar() == 1)
                ConsolaDebug.agregarTexto("Ocurrio un error al insertar el DML UPDATE en la bitacora", ConsolaDebug.ERROR);
        }
    }
    
    public void insertarBitacora(String tabla, String valores){
        char accion = 'D';
        
        BitacoraDTO dtoBitacora = new BitacoraDTO();
        dtoBitacora.setIdSistema(Globales.ID_SISTEMA);
        dtoBitacora.setIdUsuario(Globales.BD_ID_USUARIO);
        dtoBitacora.setTabla(tabla);
        dtoBitacora.setAccion(accion);
        dtoBitacora.setpValores(valores);

        BitacoraDAO daoBitacora = new BitacoraDAO();
        daoBitacora.asignarParametros(dtoBitacora);
        
        if(daoBitacora.eliminar() == 1)
            ConsolaDebug.agregarTexto("Ocurrio un error al insertar el DML DELETE en la bitacora", ConsolaDebug.ERROR);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Formatear Valor">
    public String formatearValor(char valor){
        String formateado = "";
        
        formateado = "'" + valor + "'";
        
        return formateado;
    }
    
    public String formatearValor(String valor){
        String formateado = "";
        
        formateado = "'" + valor + "'";
        
        return formateado;
    }
    
    public String formatearValor(Date valor){
        String formateado = "";
        
        formateado = "DATE_FORMAT('" + valor + "', '%Y-%m-%d %H:%i:%s')";
        
        return formateado;
    }
    
    public void val(CallableStatement procedimiento, int indice, Object tipo){
        val(procedimiento, indice, tipo, 0);
    }
    
    public void val(CallableStatement procedimiento, int indice, Object tipo, int longitud){
        try{
            
            if(tipo == null)
                asignarNulo(procedimiento, indice, tipo);
            else{
                if(tipo instanceof Integer)
                    procedimiento.setInt(indice, (int)tipo);
                if(tipo instanceof String)
                    procedimiento.setString(indice, String.valueOf(tipo));
                if(tipo instanceof Boolean){
                    boolean booleano = (boolean)tipo;
                    procedimiento.setInt(indice, booleano == true ? 1 : 0);
                }if(tipo instanceof Date){
                    Date fecha = (Date)tipo;
                    procedimiento.setTimestamp(indice, new Timestamp(fecha.getTime()));
                }if(tipo instanceof Double)
                    procedimiento.setDouble(indice, (double)tipo);
                if(tipo instanceof InputStream)
                    procedimiento.setBinaryStream(indice, (InputStream)tipo, longitud);
            }
            
        }catch(Exception e){
            asignarNulo(procedimiento, indice, tipo);
        }
    }
    
    private void asignarNulo(CallableStatement procedimiento, int indice, Object tipo){
        try{
            procedimiento.setNull(indice, Types.NULL);
        }catch(Exception ex){
            if(Globales.APP_DEBUG)
                ConsolaDebug.agregarTexto("Error de procedimiento en el paso del parametro " + indice + " (" + tipo.toString() + ")", ConsolaDebug.ERROR);
        }
    }
    // </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos Abstractos">
    public abstract void asignarParametros(DTO dto);
    public abstract List<DTO> leer(String where);
    public abstract List<DTO> leerDesc(String where);
    public abstract int agregar();
    public abstract int modificar();
    public abstract int eliminar();
    // </editor-fold>
}
