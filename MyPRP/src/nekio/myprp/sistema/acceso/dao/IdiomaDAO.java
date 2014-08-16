package nekio.myprp.sistema.acceso.dao;

/**
 *
 * @author Nekio
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.sistema.acceso.dto.IdiomaDTO;

public class IdiomaDAO extends DAO {

    private final String TABLA = "idioma";
    private final String ID = "id_idioma";
    private final String TODOS_CAMPOS
            = ID + ", descripcion ";

    private IdiomaDTO dto;

    @Override
    public void asignarParametros(DTO dto) {
        this.dto = (IdiomaDTO) dto;

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto("DAO " + TABLA + ": Parametros ingresados", ConsolaDebug.PROCESO);
        }
    }

    @Override
    public ArrayList<DTO> leer(String where) {
        return leer(TODOS_CAMPOS, where, null, null);
    }

    @Override
    public ArrayList<DTO> leerDesc(String where) {
        return leer(TODOS_CAMPOS, where, ID + " DESC", null);
    }

    public ArrayList<DTO> leer(String select, String where, String orderBy, String groupBy) {
        ArrayList<DTO> lista = new ArrayList<DTO>();
        String consulta
                = "SELECT " + select + " \n"
                + "FROM " + Globales.BD_DESC_ESQUEMA + "." + TABLA + " \n"
                + "WHERE 1=1\n";

        if (where != null) {
            consulta += "AND " + where + "\n";
        }
        if (orderBy != null) {
            consulta += "ORDER BY " + orderBy + "\n";
        }
        if (groupBy != null) {
            consulta += "GROUP BY " + groupBy + "\n";
        }

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto(consulta, ConsolaDebug.SQL);
        }

        try {
            IdiomaDTO dto = null;
            ResultSet resultados = BDConexion.consultar(consulta);

            while (resultados.next()) {
                dto = new IdiomaDTO();

                dto.setIdIdioma(resultados.getInt("id_idioma"));
                dto.setDescripcion(resultados.getString("descripcion"));

                lista.add(dto);
            }
            BDConexion.cerrar();
        } catch (Exception e) {
            ConsolaDebug.agregarTexto("DAO: Error al leer registros de " + Globales.BD_DESC_ESQUEMA + "." + TABLA + ": " + e, ConsolaDebug.ERROR);
        }

        return lista;
    }

    public DTO leerUno(String where) {
        return leerUno(TODOS_CAMPOS, where, null, null);
    }

    public DTO leerUno(String select, String where, String orderBy, String groupBy) {
        IdiomaDTO dto = null;

        String consulta
                = "SELECT " + select + " \n"
                + "FROM " + Globales.BD_DESC_ESQUEMA + "." + TABLA + " \n"
                + "WHERE 1=1\n";

        if (where != null) {
            consulta += "AND " + where + "\n";
        }
        if (orderBy != null) {
            consulta += "ORDER BY " + orderBy + "\n";
        }
        if (groupBy != null) {
            consulta += "GROUP BY " + groupBy + "\n";
        }

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto(consulta, ConsolaDebug.SQL);
        }

        try {
            ResultSet resultados = BDConexion.consultar(consulta);
            dto = new IdiomaDTO();

            while (resultados.next()) {
                dto.setIdIdioma(resultados.getInt("id_idioma"));
                dto.setDescripcion(resultados.getString("descripcion"));
            }

            BDConexion.cerrar();
        } catch (Exception e) {
            ConsolaDebug.agregarTexto("Error al leer un registro de " + Globales.BD_DESC_ESQUEMA + "." + TABLA + ": " + e, ConsolaDebug.ERROR);
        }

        return dto;
    }

    @Override
    public int agregar() {
        int resultado = 1;

        String accion = super.INSERTAR;
        int parametros = 1;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_DESC_ESQUEMA, accion, TABLA, parametros);

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.PROCESO);
        }

        try {
            Connection conexion = BDConexion.getConnection();

            CallableStatement procInsertar = conexion.prepareCall(procedimiento);
            procInsertar.setString(1, dto.getDescripcion());
            procInsertar.execute();

            conexion.commit();
            BDConexion.cerrar();

            resultado = 0;
        } catch (Exception e) {
            ConsolaDebug.agregarTexto("No se pudo " + accion + " en la tabla " + Globales.BD_DESC_ESQUEMA + "." + TABLA + "\n" + e, ConsolaDebug.ERROR);
        }

        return resultado;
    }

    @Override
    public int modificar() {
        int resultado = 1;
        String accion = super.ACTUALIZAR;
        int parametros = 2;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_DESC_ESQUEMA, accion, TABLA, parametros);

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.PROCESO);
        }

        try {
            Connection conexion = BDConexion.getConnection();

            CallableStatement procActualizar = conexion.prepareCall(procedimiento);
            procActualizar.setInt(1, dto.getIdIdioma());
            procActualizar.setString(2, dto.getDescripcion());
            procActualizar.execute();

            conexion.commit();
            BDConexion.cerrar();

            resultado = 0;
        } catch (Exception e) {
            ConsolaDebug.agregarTexto("No se pudo " + accion + " en la tabla " + Globales.BD_DESC_ESQUEMA + "." + TABLA + "\n" + e, ConsolaDebug.ERROR);
        }

        return resultado;
    }

    @Override
    public int eliminar() {
        int resultado = 1;

        String accion = super.ELIMINAR;
        int parametros = 1;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_DESC_ESQUEMA, accion, TABLA, parametros);

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto(procedimiento + " : ID - " + dto.getIdIdioma(), ConsolaDebug.PROCESO);
        }

        try {
            Connection conexion = BDConexion.getConnection();

            CallableStatement procEliminar = conexion.prepareCall(procedimiento);
            procEliminar.setInt(1, dto.getIdIdioma());
            procEliminar.execute();

            conexion.commit();
            BDConexion.cerrar();

            resultado = 0;
        } catch (Exception e) {
            ConsolaDebug.agregarTexto("No se pudo " + accion + " en la tabla " + Globales.BD_DESC_ESQUEMA + "." + TABLA + "\n" + e, ConsolaDebug.ERROR);
        }
        return resultado;

    }
}
