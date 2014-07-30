package nekio.myprp.recursos.utilerias.bd;

/**
 *
 * @author Nekio
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Globales.TipoDato;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.derby.client.am.Types;
 
public class BDConexion {    
    private static final String SANGRIA = "    ";
            
    private static Connection conexion;
    private static PreparedStatement clausula;
    private static ResultSet resultado;
    
    private static String driver;
    private static String usuario;
    private static String password;
    private static String url;
    private static int maxActivos;
    private static int maxIdle;
 
    public BDConexion() {
        this(null, null, null, null, null, null);
    }
 
    public BDConexion(BDGestor gestor, String usuario, String password, String host, String puerto, String db) {
        this(gestor, usuario, password, host, puerto, db, 20, 2);
    }
 
    public BDConexion(BDGestor gestor, String usuario, String password, String host, String puerto, String db, int maxActivos, int maxIdle) {
        String driver = null;
        String url = null;
 
        switch (gestor) {
            case ACCESS:
                driver = "sun.jdbc.odbc.JdbcOdbcDriver";
                url = "jdbc:odbc:" + db;
            break;
            case SQL_SERVER_SPRINTA:
                if (puerto == null || puerto.isEmpty() || puerto.equals(""))
                    puerto = "1433";
                driver = "com.inet.tds.TdsDriver";
                url = "jdbc:inetdae:" + host + ":" + puerto + "?database=" + db;
            break;
            case SQL_SERVER_2005:
                if (puerto == null || puerto.isEmpty() || puerto.equals(""))
                    puerto = "1433";//1434 tambien puede ser
                driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                url = "jdbc:microsoft:sqlserver://" + host + ":" + puerto + "[;DatabaseName=" + db + "]";
            break;
            case MY_SQL:
                String relax = "?autoReconnect=true&relaxAutoCommit=true";
                if (puerto == null || puerto.isEmpty() || puerto.equals(""))
                    puerto = "3306";
                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://" + host + ":" + puerto + "/" + db + relax;
                //url = "jdbc:mysql://" + host + "/" + db;
            break;
            case POSTGRE_SQL:
                if (puerto == null || puerto.isEmpty() || puerto.equals(""))
                    puerto = "5432";
                driver = "org.postgresql.Driver";
                url = "jdbc:postgresql://" + host + ":" + puerto + "/" + db;
            break;
            case JAVA_DERBY:
                if (puerto == null || puerto.isEmpty() || puerto.equals(""))
                    puerto = "1527";
                driver = "org.apache.derby.jdbc.ClientDriver";
                url = "jdbc:derby://" + host + ":" + puerto + "/" + db;
            break;
            case ORACLE_8I:
                driver = "oracle.jdbc.driver.OracleDriver";
                url = "jdbc:oracle:oci8:@" + db;
            break;
            case ORACLE_9I:
                if (puerto == null || puerto.isEmpty() || puerto.equals(""))
                    puerto = "1521";
                driver = "jdbc:oracle:oci:@" + db;
                url = "jdbc:oracle:thin:@" + host + ":" + puerto + ":" + db;
            break;
            case ORACLE_THIN:
                if (puerto == null || puerto.isEmpty() || puerto.equals("")) 
                    puerto = "1521";
                driver = "oracle.jdbc.driver.OracleDriver";
                url = "jdbc:oracle:thin:@" + host + ":" + puerto + ":" + db;
            break;
        }
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto(url, ConsolaDebug.SQL);
 
        BDConexion.driver = driver;
        BDConexion.usuario = usuario;
        BDConexion.password = password;
        BDConexion.url = url;
 
        BDConexion.maxActivos = maxActivos;
        BDConexion.maxIdle = maxIdle;
    }
 
    private static DataSource getDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(driver);
        bds.setUsername(usuario);
        bds.setPassword(password);
        bds.setUrl(url);
        bds.setMaxActive(maxActivos);
        bds.setMaxIdle(maxIdle);
 
        return bds;
    }
 
    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            ConsolaDebug.agregarTexto("Ha ocurrido un error en la conexion, verifique sus credenciales\n"+e, ConsolaDebug.ERROR);
            return null;
        }
    }
 
    public static ResultSet consultar(String consulta) throws SQLException{
        conexion = BDConexion.getConnection();
        clausula = conexion.prepareStatement(consulta);
        resultado = clausula.executeQuery();
        
        if(Globales.APP_DEBUG)
            monitorearRS(consulta);
        
        return resultado;
    }
    
    private static ArrayList<String> obtenerNombresColumnas()throws Exception{           
        ArrayList<String> nombresColumnas = new ArrayList<String>();
        ResultSetMetaData mdata = resultado.getMetaData();
        int columnas = mdata.getColumnCount();
        
        for (int i = 0; i < columnas; i++) {
            nombresColumnas.add(mdata.getColumnLabel(i + 1));
        }
        
        return nombresColumnas;
    }
    
    private static void monitorearRS(String consulta){
        String registro = null;
        int cadenaMaxima = 28;
        try{
            ResultSetMetaData mdata = resultado.getMetaData();
            int campos = mdata.getColumnCount();
        
            ConsolaDebug.agregarTexto("CABECERAS DE TABLA:", ConsolaDebug.SQL, false);
            for(String cabecera:obtenerNombresColumnas()){
                ConsolaDebug.agregarTexto("   " + cabecera, ConsolaDebug.SQL, false);
            }
            
            ConsolaDebug.agregarTexto("\n\nREGISTROS DE TABLA:\n", ConsolaDebug.SQL, false);
            
            Statement instruccion = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = instruccion.executeQuery(consulta);
            int contador = 0;
            while (rs.next()) {
                contador++;
                ConsolaDebug.agregarTexto("[" + contador + "]   ", ConsolaDebug.SQL, false);
                for (int i = 1; i <= campos; i++) {
                    registro = String.valueOf(rs.getObject(i));

                    if (registro.equals("null") || registro.equals("") || registro == null || registro.isEmpty()) {
                        registro = "-";
                    }else if(registro.length() > cadenaMaxima)
                        registro = registro.substring(0, cadenaMaxima-1)+"...";

                    ConsolaDebug.agregarTexto(registro.replace("\n", " ") + "\t", ConsolaDebug.SQL, false);
                }
                ConsolaDebug.agregarTexto("\n", ConsolaDebug.OCULTO, false);
            }
            ConsolaDebug.agregarTexto("\n", ConsolaDebug.OCULTO, false);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static List<String> obtenerTablas(String usuario, String esquema){
        List<String> tablas;
        
        try {
            String table = "%";
            String[] types = {"TABLE", "VIEW"};

            conexion = BDConexion.getConnection();
            DatabaseMetaData dbm = conexion.getMetaData();
            ResultSet rsT = dbm.getTables(usuario, esquema, table, types);
                    
            if (rsT.next() == false) {
                rsT = dbm.getTables(usuario, esquema.toUpperCase(), table, types);
                if (rsT.next() == false) {
                    ConsolaDebug.agregarTexto("NO SE HA ENCONTRADO EL ESQUEMA EN LA BASE DE DATOS", ConsolaDebug.ERROR);
                    return null;
                }
            }

            tablas = new ArrayList<String>();

            ConsolaDebug.agregarTexto("Tablas detectadas:", ConsolaDebug.PROCESO, false);
            tablas.add(rsT.getString("TABLE_NAME"));
            String tabla = "";
            ConsolaDebug.agregarTexto("\n" + SANGRIA + rsT.getString("TABLE_NAME"), ConsolaDebug.COMODIN, false);
            while (rsT.next()) {
                tabla = rsT.getString("TABLE_NAME");
                tablas.add(tabla);
                ConsolaDebug.agregarTexto("\n" + SANGRIA + tabla, ConsolaDebug.COMODIN, false);
            }
            ConsolaDebug.agregarTexto("\n" + tablas.size() + " Tablas\n", ConsolaDebug.PROCESO, false);

            rsT.close();
            conexion.close();

            rsT=null;
            conexion=null;
        } catch (Exception ex) {
            tablas = null;
        }
        
        return tablas;
    }
    
    /* Estructura de contenidos de Lista
     *
     * 0 - Llaves
     *   0 - PKs
     *   1 - FKs
     *   2 - Tablas relacionadas
     * 1 - Nombres de campos
     * 2 - Tamanos de campos
     * 3 - Tipos de datos de los campos
     * 4 - Campos Opcionales
     */
    public static enum Detalles{
        LLAVES,
        NOMBRE_CAMPOS,
        TAMANO_CAMPOS,
        TIPO_DATOS,
        OPCIONALES
    }
    
    public static List<List> obtenerDetalles(String usuario, String esquema, String tabla) {
        List<List> detalles = new ArrayList<List>();
        
        List<List> llaves;
        List<String> nombres;
        List<Integer> tamanos;
        List<TipoDato> tipos;
        List<Boolean> opcionales;
        
        try {            
            conexion = BDConexion.getConnection();
            
            String catalogo = conexion.getCatalog();
            String consulta = "SELECT * FROM " + catalogo + "." + tabla;
            
            Statement instruccion = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = instruccion.executeQuery(consulta);
            
            ConsolaDebug.agregarTexto(
                    "\n\n" + Globales.APP_SEPARADOR + 
                    "\n" + catalogo + "." + tabla +
                    "\n"+Globales.APP_SEPARADOR + "\n\n",
                    ConsolaDebug.MAPEO, false
            );
            ConsolaDebug.agregarTexto(consulta, ConsolaDebug.SQL);
            
            // Inicializaciones
            llaves = new ArrayList<List>();
            nombres = new ArrayList<String>();
            tamanos = new ArrayList<Integer>();
            tipos = new ArrayList<TipoDato>();
            opcionales = new ArrayList<Boolean>();
                        
            DatabaseMetaData dbm = conexion.getMetaData();
            
            // Obtencion de llaves primarias
            ResultSet rsPk = dbm.getPrimaryKeys(usuario, esquema, tabla);
            
            List<String> pks = new ArrayList<String>();
            String pk = null;
            ConsolaDebug.agregarTexto("Obteniendo Llaves Primarias\n", ConsolaDebug.PROCESO, false);
            while(rsPk.next()){
                pk = rsPk.getString("COLUMN_NAME");
                if(!pks.contains(pk))
                    pks.add(pk);
                ConsolaDebug.agregarTexto(SANGRIA + pk + "\n", ConsolaDebug.COMODIN, false);
            }
            
            llaves.add(pks);
            
            //Obtencion de llaves foraneas
            ResultSet rsFk = dbm.getImportedKeys(usuario, esquema, tabla);
            
            List<String> fks = new ArrayList<String>();
            List<String> fTablas = new ArrayList<String>();
            String fk = null;
            String fTabla = null;
            ConsolaDebug.agregarTexto("\nObteniendo Llaves Foraneas\n", ConsolaDebug.PROCESO, false);
            while(rsFk.next()){                
                fk = rsFk.getString("PKCOLUMN_NAME");
                if(!fks.contains(fk))
                    fks.add(fk);
              
                fTabla = rsFk.getString("PKTABLE_NAME");
                if(!fTablas.contains(fTabla))
                    fTablas.add(fTabla);
                
                ConsolaDebug.agregarTexto(SANGRIA + fk + "(" + rsFk.getString("PKTABLE_CAT") + "." + fTabla + ")\n", ConsolaDebug.COMODIN, false);
            }

            llaves.add(fks);
            llaves.add(fTablas);
            
            // Obtencion de detalles de campos
            ResultSetMetaData mdata = rs.getMetaData();
            
            String nombre = null;
            int precision = 0;
            TipoDato tipo = null;
            boolean opcional = false;
            
            ConsolaDebug.agregarTexto("\nObteniendo Detalles de campos\n", ConsolaDebug.PROCESO, false);
            String campo = null;
            for (int i = 1; i <= mdata.getColumnCount(); i++) {
                try{
                    nombre = mdata.getColumnLabel(i);
                    precision = mdata.getPrecision(i);
                    
                    tipo = identificarTipoColumna(mdata.getColumnType(i));
                    if(tipo == TipoDato.TEXTO && precision == 1)
                        tipo = TipoDato.CARACTER;
                    else if(tipo == TipoDato.NUMERO && precision == 1)
                        tipo = TipoDato.BOOLEANO;
                    
                    opcional = mdata.isNullable(i)==1?true:false;

                    nombres.add(nombre);
                    tamanos.add(precision);
                    tipos.add(tipo);
                    opcionales.add(opcional);
                    
                    campo = SANGRIA + nombre + " " + tipo.name();
                    if(!(tipo == TipoDato.BLOB || tipo == TipoDato.FECHA))
                        campo += "(" + precision + ")";
                    campo += " Opcional=" + opcional;
                    
                    ConsolaDebug.agregarTexto(campo + "\n", ConsolaDebug.COMODIN, false);
                }catch(Exception e){
                    //System.out.println(e);
                }
            }            

            // Limpieza de objetos
            rs.close();
            instruccion.close();
            conexion.close();

            rs=null;
            instruccion=null;
            conexion=null;
            
            // Asignacion de los detalles
            detalles.add(Detalles.LLAVES.ordinal(), llaves);
            detalles.add(Detalles.NOMBRE_CAMPOS.ordinal(), nombres);
            detalles.add(Detalles.TAMANO_CAMPOS.ordinal(), tamanos);
            detalles.add(Detalles.TIPO_DATOS.ordinal(), tipos);
            detalles.add(Detalles.OPCIONALES.ordinal(), opcionales);
            
            ConsolaDebug.agregarTexto("\n" + catalogo + "." + tabla + ": [OK]\n", ConsolaDebug.BITACORA, false);
        } catch (Exception ex) {
            ConsolaDebug.agregarTexto("\nERROR AL OBTENER LOS DATOS DE : " + tabla + "\n", ConsolaDebug.ERROR, false);
            detalles = null;
        }
        
        return detalles;
    }
    
    private static TipoDato identificarTipoColumna(int identificador){
        TipoDato tipo = null;
        
        switch(identificador){
            case Types.BLOB:
            case Types.LONGVARBINARY:
                tipo = Globales.TipoDato.BLOB;
            break;
            case Types.VARCHAR:
            case Types.CHAR:
                tipo = Globales.TipoDato.TEXTO;
            break;
            case Types.INTEGER:
                tipo = Globales.TipoDato.NUMERO;
            break;
            case Types.LONGVARCHAR:
                tipo = Globales.TipoDato.TEXTO_LARGO;
            break;
            case Types.BOOLEAN:
                tipo = Globales.TipoDato.BOOLEANO;
            break;
            case Types.DATE:
            case Types.TIMESTAMP:
                tipo = Globales.TipoDato.FECHA;
            break;
        }
        
        return tipo;
    }
    
    public static void cerrar(){
        cerrar(resultado, clausula, conexion);
    }
 
    public static void cerrar(ResultSet rs,PreparedStatement ps,Connection c){
        cerrar(rs);
        cerrar(ps);
        cerrar(c);
    }
     
    public static void cerrar(ResultSet rs){
        try{
            if(rs!=null) {
                rs.close();
                rs = null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
 
    public static void cerrar(PreparedStatement ps){
        try{
            if(ps!=null){
                ps.close();
                ps = null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
 
    public static void cerrar(Connection c){
        try{
            if(c!= null){
                c.close();
                c=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
