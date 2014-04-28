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
import java.sql.SQLException;
import java.util.ArrayList;
import listadevalores.dto.Elementos;
import listadevalores.dto.LlaveValor;

public class Listado {

    private final int NO_LLAVE = -1;
    private final String NO_VALOR = "- Tabla Vacia -";

    public ArrayList<LlaveValor> getLista(Elementos elementos) throws Exception {
        Connection conexion = elementos.getConexion();
        boolean ordenadoPorLlave = elementos.isOrdendoPorLlave();
        String llave = elementos.getLlave();
        String valor = elementos.getValor();
        String tabla = elementos.getTabla();
        String filtros = elementos.getFiltros();
        
        String campo = "1";
        if (!ordenadoPorLlave)
            campo = "2";
        
        if(filtros == null)
            filtros = "1=1";

        String query =
                "SELECT " + llave + ", " + valor + " " +
                "FROM   " + tabla + " " +
                "WHERE  " + filtros + " " +
                "ORDER BY " + campo;

        ArrayList<LlaveValor> lista;
        try {
            PreparedStatement ps = conexion.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<LlaveValor>();
            while (rs.next()) {
                LlaveValor elemento = new LlaveValor();

                elemento.setKey(rs.getInt(llave));
                elemento.setValue(rs.getString(valor));

                lista.add(elemento);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            lista = new ArrayList<LlaveValor>();
            lista.add(new LlaveValor(NO_LLAVE, NO_VALOR));
        }
        return lista;
    }
}
