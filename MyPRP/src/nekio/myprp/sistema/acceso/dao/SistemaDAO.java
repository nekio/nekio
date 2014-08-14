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
import nekio.myprp.sistema.acceso.dto.SistemaDTO;

public class SistemaDAO extends DAO {

    private final String TABLA = "sistema";
    private final String ID = "id_sistema";
    private final String TODOS_CAMPOS
            = ID + ", descripcion, siglas, titulo, desarrollador, contacto, version, actualizacion, fecha_liberacion, msj_mantenimiento, bd_esquema ";

    private SistemaDTO dto;

    @Override
    public void asignarParametros(DTO dto) {
        this.dto = (SistemaDTO) dto;

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
            SistemaDTO dto = null;
            ResultSet resultados = BDConexion.consultar(consulta);

            while (resultados.next()) {
                dto = new SistemaDTO();

                dto.setIdSistema(resultados.getInt("id_sistema"));
                dto.setDescripcion(resultados.getString("descripcion"));
                dto.setSiglas(resultados.getString("siglas"));
                dto.setTitulo(resultados.getString("titulo"));
                dto.setDesarrollador(resultados.getString("desarrollador"));
                dto.setContacto(resultados.getString("contacto"));
                dto.setVersion(resultados.getString("version"));
                dto.setActualizacion(resultados.getString("actualizacion"));
                dto.setFechaLiberacion(resultados.getTimestamp("fecha_liberacion"));
                dto.setMsjMantenimiento(resultados.getString("msj_mantenimiento"));
                dto.setBdEsquema(resultados.getString("bd_esquema"));

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
        SistemaDTO dto = null;

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
            dto = new SistemaDTO();

            while (resultados.next()) {
                dto.setIdSistema(resultados.getInt("id_sistema"));
                dto.setDescripcion(resultados.getString("descripcion"));
                dto.setSiglas(resultados.getString("siglas"));
                dto.setTitulo(resultados.getString("titulo"));
                dto.setDesarrollador(resultados.getString("desarrollador"));
                dto.setContacto(resultados.getString("contacto"));
                dto.setVersion(resultados.getString("version"));
                dto.setActualizacion(resultados.getString("actualizacion"));
                dto.setFechaLiberacion(resultados.getTimestamp("fecha_liberacion"));
                dto.setMsjMantenimiento(resultados.getString("msj_mantenimiento"));
                dto.setBdEsquema(resultados.getString("bd_esquema"));
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
        int parametros = 10;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_DESC_ESQUEMA, accion, TABLA, parametros);

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.PROCESO);
        }

        try {
            Connection conexion = BDConexion.getConnection();

            CallableStatement procInsertar = conexion.prepareCall(procedimiento);
            procInsertar.setString(1, dto.getDescripcion());
            procInsertar.setString(2, dto.getSiglas());
            procInsertar.setString(3, dto.getTitulo());
            procInsertar.setString(4, dto.getDesarrollador());
            procInsertar.setString(5, dto.getContacto());
            procInsertar.setString(6, dto.getVersion());
            procInsertar.setString(7, dto.getActualizacion());
            procInsertar.setTimestamp(8, new java.sql.Timestamp(dto.getFechaLiberacion().getTime()));
            procInsertar.setString(9, dto.getMsjMantenimiento());
            procInsertar.setString(10, dto.getBdEsquema());
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
        int parametros = 11;
        String procedimiento = super.obtenerProcedimiento(Globales.BD_DESC_ESQUEMA, accion, TABLA, parametros);

        if (Globales.APP_DEBUG) {
            ConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.PROCESO);
        }

        try {
            Connection conexion = BDConexion.getConnection();

            CallableStatement procActualizar = conexion.prepareCall(procedimiento);
            procActualizar.setInt(1, dto.getIdSistema());
            procActualizar.setString(2, dto.getDescripcion());
            procActualizar.setString(3, dto.getSiglas());
            procActualizar.setString(4, dto.getTitulo());
            procActualizar.setString(5, dto.getDesarrollador());
            procActualizar.setString(6, dto.getContacto());
            procActualizar.setString(7, dto.getVersion());
            procActualizar.setString(8, dto.getActualizacion());
            procActualizar.setTimestamp(9, new java.sql.Timestamp(dto.getFechaLiberacion().getTime()));
            procActualizar.setString(10, dto.getMsjMantenimiento());
            procActualizar.setString(11, dto.getBdEsquema());
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
            ConsolaDebug.agregarTexto(procedimiento + " : ID - " + dto.getIdSistema(), ConsolaDebug.PROCESO);
        }

        try {
            Connection conexion = BDConexion.getConnection();

            CallableStatement procEliminar = conexion.prepareCall(procedimiento);
            procEliminar.setInt(1, dto.getIdSistema());
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
