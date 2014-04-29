package listadevalores.dao;

/**
 * Clase para formar Listas Clave-Valor
 *
 * @author Nekio
 * @version 27/04/2014
*
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import listadevalores.dto.Elementos;
import listadevalores.dto.LlaveValor;

public class Listado {
    private final int NO_LLAVE = -1;
    private final String NO_VALOR = "- Tabla Vacia -";
    
    private ArrayList<String> nombresColumnas;

    public ArrayList<LlaveValor> getLista(Elementos elementos) throws Exception {
        Connection conexion = elementos.getConexion();
        boolean ordenadoPorLlave = elementos.isOrdendoPorLlave();
        String llave = elementos.getLlave();
        String valor = elementos.getValor();
        ArrayList<String> camposExtras = elementos.getCamposExtras();
        if(camposExtras == null)
            camposExtras = new ArrayList<String>();
        String tabla = elementos.getTabla();
        String filtros = elementos.getFiltros();
        
        String campo = "1";
        if (!ordenadoPorLlave)
            campo = "2";
        
        String camposAdicionales = "";
        int campos = camposExtras.size();
        for(int i=0; i<campos; i++){
            if(i!=campos)
                camposAdicionales += ",";
            camposAdicionales += " " + camposExtras.get(i);
        }
        
        if(filtros == null)
            filtros = "1=1";

        String query =
                "SELECT " + llave + ", " + valor + camposAdicionales + " " +
                "FROM   " + tabla + " " +
                "WHERE  " + filtros + " " +
                "ORDER BY " + campo;
        
        System.out.println(query);

        ArrayList<LlaveValor> lista;
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            obtenerMetadatos(rs);

            lista = new ArrayList<LlaveValor>();
            ArrayList<String> registroExtra;
            while (rs.next()) {
                LlaveValor elemento = new LlaveValor();

                elemento.setLlave(rs.getInt(llave));
                elemento.setValor(rs.getString(valor));
                
                registroExtra = new ArrayList<String>();
                for(int i=0; i<camposExtras.size(); i++)
                    registroExtra.add(rs.getString(camposExtras.get(i)));
                elemento.setRegistroExtra(registroExtra);

                lista.add(elemento);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            lista = new ArrayList<LlaveValor>();
            lista.add(new LlaveValor(NO_LLAVE, NO_VALOR));
        }
        return lista;
    }
    
     private void obtenerMetadatos(ResultSet rs) throws Exception{            
        ResultSetMetaData mdata = rs.getMetaData();
        int columnas = mdata.getColumnCount();
        nombresColumnas = new ArrayList<String>();
        
        for (int i = 0; i < columnas; i++) {
            nombresColumnas.add(mdata.getColumnLabel(i + 1));
        }
    }

    public ArrayList<String> getNombresColumnas() {
        return nombresColumnas;
    }
}
