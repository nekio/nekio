package herramientas;

/**
 * Clase para manejar una conexion JDBC
 *
 * @author Nekio
 * @version 27/04/2014
 *
 */

import java.sql.*;

public class Conexion {
	private final static String JDBC_DRIVER="org.apache.derby.jdbc.ClientDriver";
	private final static String JDBC_URL="jdbc:derby://localhost:1527/lania";
	private final static String JDBC_USER="sma";
	private final static String JDBC_PASS="admin";
	private static Driver driver= null;
	
	public static synchronized Connection getConexion() throws SQLException{
		if(driver==null){
			try{
				Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
				driver=(Driver)jdbcDriverClass.newInstance();
				DriverManager.registerDriver(driver);
			}catch(Exception e){
				System.out.println("Fallo al cargar el driver JDBC\n"+e);
			}
		}
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
	}
	
	public static void cerrar(ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void cerrar(PreparedStatement ps){
		try {
			if(ps!=null){
				ps.close();
				ps=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void cerrar(Connection c){
		try {
			if(c!=null){
				c.close();
				c=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
