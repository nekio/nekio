package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.Date;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BitacoraDAO;
import nekio.myprp.recursos.utilerias.bd.BitacoraDTO;

public abstract class DAO {
    private final String SEPARADOR = "_";
    
    protected final String INSERTAR = "insertar" + SEPARADOR;
    protected final String ACTUALIZAR = "actualizar" + SEPARADOR;
    protected final String ELIMINAR = "eliminar" + SEPARADOR;
    
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
    
    public void insertarBitacora(String tabla, String campos, String valores){
        char accionInsertar = 'I';
        
        BitacoraDTO dtoBitacora = new BitacoraDTO();
        dtoBitacora.setIdSistema(Globales.ID_SISTEMA);
        dtoBitacora.setIdUsuario(Globales.ID_USUARIO);
        dtoBitacora.setTabla(tabla);
        dtoBitacora.setAccion(accionInsertar);
        dtoBitacora.setpCampos(campos);
        dtoBitacora.setpValores(valores);

        BitacoraDAO daoBitacora = new BitacoraDAO();
        daoBitacora.asignarParametros(dtoBitacora);
        if(daoBitacora.agregar() == 1)
            ConsolaDebug.agregarTexto("Ocurrio un error al insertar en la bitacora", ConsolaDebug.ERROR);
    }
    
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
    
    public abstract void asignarParametros(DTO dto);
    public abstract List<DTO> leer(String where);
    public abstract List<DTO> leerDesc(String where);
    public abstract int agregar();
    public abstract int modificar();
    public abstract int eliminar();
}
