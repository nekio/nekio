package nekio.myprp.recursos.utilerias.bd;

/**
 *
 * @author Nekio
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GeneradorPseudotablas {
    private final String EXTENSION_ARCHIVO = ".txt";
    
    private Connection conexion;
    private String esquema;
    private boolean incluirVistas;
    private String ruta;
    
    public GeneradorPseudotablas(){
        this(null, null, false, null);
    }
    
    public GeneradorPseudotablas(Connection conexion, String esquema, boolean incluirVistas, String ruta){
        this.conexion = conexion;
        this.esquema = esquema;
        this.incluirVistas = incluirVistas;
        this.ruta = ruta;
    }
    
    public void generarTablas() throws Exception{
        ArrayList<String> tablas = obtenerTablas();
        
        String estructuraPseudotabla = null;
        for(String tabla : tablas){
            estructuraPseudotabla = generarTabla(tabla);
            generarPseudotabla(tabla, estructuraPseudotabla);
        }
        
        conexion.close();
        conexion=null;
    }
    
    private String generarTabla(String tabla) throws Exception{
        String resultado = null;
        String procedimiento = "{ call prueba.obtenerDetalleTabla(?, ?, ?) }";
        //System.out.println("\n" + procedimiento + " : " + esquema + "." + tabla);

        CallableStatement procObtenerDetalleTabla = conexion.prepareCall(procedimiento);
        procObtenerDetalleTabla.setString(1, esquema);
        procObtenerDetalleTabla.setString(2, tabla);
        procObtenerDetalleTabla.registerOutParameter(3, Types.VARCHAR);
        procObtenerDetalleTabla.execute();
        
        resultado = procObtenerDetalleTabla.getString(3);

        conexion.commit();

        return resultado;
    }
    
    private ArrayList<String> obtenerTablas() throws Exception{
        String catalogo = null;
        String esquema = null;
        
        String tabla = "%";
        DatabaseMetaData dbm = conexion.getMetaData();
        
        ResultSet resultados = obtenerResultados(dbm, catalogo, esquema, tabla, incluirVistas);
        if (resultados.next() == false) {
            esquema = esquema.toUpperCase();
            resultados = obtenerResultados(dbm, catalogo, esquema, tabla, incluirVistas);
            if (resultados.next() == false)
                return null;
        }

        ArrayList<String> tablas = new ArrayList();
        tablas.add(resultados.getString("TABLE_NAME"));
        while (resultados.next())
            tablas.add(resultados.getString("TABLE_NAME"));

        resultados.close();
        resultados=null;

        return tablas;
    }
    
    private ResultSet obtenerResultados(DatabaseMetaData dbm, String catalogo, String esquema, String tabla, boolean incluirVistas) throws Exception{
        String[] soloTablas = {"TABLE"};
        String[] tablasYvistas = {"TABLE", "VIEW"};
        
        ResultSet resultados = null;
        if(incluirVistas)
            resultados = dbm.getTables(catalogo, esquema, tabla, tablasYvistas);
        else
            resultados = dbm.getTables(catalogo, esquema, tabla, soloTablas);
        
        return resultados;
    }
    
    private void generarPseudotabla(String tabla, String estructuraPseudotabla){        
        StringTokenizer tokenizer=new StringTokenizer(estructuraPseudotabla,"\n");
        int numTokens=tokenizer.countTokens();
        String tokens[]=new String[numTokens];
        String token=null;

        int indice=0;
        // Monitorear la tabla
        //System.out.println("\n"+tabla);
        while(tokenizer.hasMoreTokens()){
            token=tokenizer.nextToken();
            tokens[indice]=token;
            
            // Monitorear cada token
            //System.out.println(token);

            indice++;
        }
        
        String rutaPseudotabla = ruta + File.separator + esquema + "." + tabla + EXTENSION_ARCHIVO;
        //System.out.println(rutaPseudotabla);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaPseudotabla));
            
            for(int i=0;i<tokens.length;i++){
                bw.write(tokens[i]);
                bw.newLine();
            }
            
            bw.close();
            bw = null;
        }catch(Exception e){}
    }
}
