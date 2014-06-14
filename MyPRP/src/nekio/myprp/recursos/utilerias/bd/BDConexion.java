package nekio.myprp.recursos.utilerias.bd;

/**
 *
 * @author Nekio
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;
import nekio.myprp.recursos.utilerias.Globales;
import org.apache.commons.dbcp.BasicDataSource;
 
public class BDConexion {    
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
            System.out.println(url);
 
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
            System.out.println("Ha ocurrido un error en la conexion, verifique sus credenciales\n"+e);
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
    
    public static ArrayList<String> obtenerNombresColumnas()throws Exception{           
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
        try{
            ResultSetMetaData mdata = resultado.getMetaData();
            int campos = mdata.getColumnCount();
        
            System.out.println("\nCABECERAS DE TABLA:");
            for(String cabecera:obtenerNombresColumnas()){
                System.out.println("   " + cabecera);
            }
            
            System.out.println("\nREGISTROS DE TABLA:");
            
            Statement instruccion = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = instruccion.executeQuery(consulta);
            while (rs.next()) {
                for (int i = 1; i <= campos; i++) {
                    registro = String.valueOf(rs.getObject(i));

                    if (registro.equals("null") || registro.equals("") || registro == null || registro.isEmpty()) {
                        registro = "-";
                    }

                    System.out.print(registro + "\t");
                }
                System.out.println();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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
