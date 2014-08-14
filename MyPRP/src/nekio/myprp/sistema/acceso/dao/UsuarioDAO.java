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
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

public class UsuarioDAO extends DAO {

    private final String TABLA = "usuario";
    private final String ID = "id_usuario";
    private final String TODOS_CAMPOS
            = ID + ", id_tipo_usuario, id_rango, nombre, nick, autogenerado, acceso, contacto, fecha_registro, ultimo_acceso, activo ";

    private UsuarioDTO dto;

    @Override
    public void asignarParametros(DTO dto) {
        this.dto = (UsuarioDTO) dto;

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
            ConsolaDebug.agregarTexto("Obteniendo lista de usuarios...\n\n", ConsolaDebug.ADVERTENCIA, false);
        }

        try {
            // Este DAO en particular no debe mostrar el resultado de sus registros
            // por motivos de seguridad
            boolean debug = Globales.APP_DEBUG;
            Globales.APP_DEBUG = false;
            
            UsuarioDTO dto = null;
            ResultSet resultados = BDConexion.consultar(consulta);

            while (resultados.next()) {
                dto = new UsuarioDTO();

                dto.setIdUsuario(resultados.getInt("id_usuario"));
                dto.setIdTipoUsuario(resultados.getInt("id_tipo_usuario"));
                dto.setIdRango(resultados.getInt("id_rango"));
                dto.setNombre(resultados.getString("nombre"));
                dto.setNick(resultados.getString("nick"));
                dto.setAutogenerado(resultados.getString("autogenerado"));
                dto.setAcceso(resultados.getString("acceso"));
                dto.setContacto(resultados.getString("contacto"));
                dto.setFechaRegistro(resultados.getTimestamp("fecha_registro"));
                dto.setUltimoAcceso(resultados.getTimestamp("ultimo_acceso"));
                dto.setActivo(resultados.getInt("activo") == 1 ? true : false);

                lista.add(dto);
            }
            BDConexion.cerrar();
            Globales.APP_DEBUG = debug;
        } catch (Exception e) {
            ConsolaDebug.agregarTexto("DAO: Error al leer registros de " + Globales.BD_DESC_ESQUEMA + "." + TABLA + ": " + e, ConsolaDebug.ERROR);
        }

        return lista;
    }

    public DTO leerUno(String where) {
        return leerUno(TODOS_CAMPOS, where, null, null);
    }

    public DTO leerUno(String select, String where, String orderBy, String groupBy) {
        UsuarioDTO dto = null;

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
            dto = new UsuarioDTO();

            while (resultados.next()) {
                dto.setIdUsuario(resultados.getInt("id_usuario"));
                dto.setIdTipoUsuario(resultados.getInt("id_tipo_usuario"));
                dto.setIdRango(resultados.getInt("id_rango"));
                dto.setNombre(resultados.getString("nombre"));
                dto.setNick(resultados.getString("nick"));
                dto.setAutogenerado(resultados.getString("autogenerado"));
                dto.setAcceso(resultados.getString("acceso"));
                dto.setContacto(resultados.getString("contacto"));
                dto.setFechaRegistro(resultados.getTimestamp("fecha_registro"));
                dto.setUltimoAcceso(resultados.getTimestamp("ultimo_acceso"));
                dto.setActivo(resultados.getInt("activo") == 1 ? true : false);
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
            procInsertar.setInt(1, dto.getIdTipoUsuario());
            procInsertar.setInt(2, dto.getIdRango());
            procInsertar.setString(3, dto.getNombre());
            procInsertar.setString(4, dto.getNick());
            procInsertar.setString(5, dto.getAutogenerado());
            procInsertar.setString(6, dto.getAcceso());
            procInsertar.setString(7, dto.getContacto());
            procInsertar.setTimestamp(8, new java.sql.Timestamp(dto.getFechaRegistro().getTime()));
            procInsertar.setTimestamp(9, new java.sql.Timestamp(dto.getUltimoAcceso().getTime()));
            procInsertar.setInt(10, dto.isActivo() == true ? 1 : 0);
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
            procActualizar.setInt(1, dto.getIdUsuario());
            procActualizar.setInt(2, dto.getIdTipoUsuario());
            procActualizar.setInt(3, dto.getIdRango());
            procActualizar.setString(4, dto.getNombre());
            procActualizar.setString(5, dto.getNick());
            procActualizar.setString(6, dto.getAutogenerado());
            procActualizar.setString(7, dto.getAcceso());
            procActualizar.setString(8, dto.getContacto());
            procActualizar.setTimestamp(9, new java.sql.Timestamp(dto.getFechaRegistro().getTime()));
            procActualizar.setTimestamp(10, new java.sql.Timestamp(dto.getUltimoAcceso().getTime()));
            procActualizar.setInt(11, dto.isActivo() == true ? 1 : 0);
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
            ConsolaDebug.agregarTexto(procedimiento + " : ID - " + dto.getIdUsuario(), ConsolaDebug.PROCESO);
        }

        try {
            Connection conexion = BDConexion.getConnection();

            CallableStatement procEliminar = conexion.prepareCall(procedimiento);
            procEliminar.setInt(1, dto.getIdUsuario());
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
