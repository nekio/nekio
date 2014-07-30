package nekio.myprp.recursos.generador;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.awt.Color;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Globales.TipoDato;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
// </editor-fold>

public class ControladorGenerador {
    // <editor-fold defaultstate="collapsed" desc="Atributos">   
    private List<String> tablasBD;
    private List<List> detallesTablasBD;
    protected Generador generador;
    
    protected String comentarios = 
            "/* Es necesario realizar un \"Fix Imports\" en el IDE para incluir las librerias necesarias, " +
            "\n * Agregarle tambien el package correspondiente " +
            "\n * y adicionalmente \"Format\" si se desajustan las identaciones" +
            "\n */\n\n";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ControladorGenerador(List<String> tablasBD, List<List> detallesTablasBD){
        this(tablasBD, detallesTablasBD, false);
    }
    
    public ControladorGenerador(List<String> tablasBD, List<List> detallesTablasBD, boolean estandar){
        this.tablasBD = tablasBD;
        this.detallesTablasBD = detallesTablasBD;
        this.generador = new GHardcode(estandar);
        
        ConsolaDebug.agregarTexto(
                Globales.APP_SEPARADOR + "\n" +
                "Generador inicializado con " +
                tablasBD.size() + " tablas\n" +
                Globales.APP_SEPARADOR + "\n",
                Color.YELLOW,
                false
        );
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Crear Capas Desde.. ">
    public void crearCapasDesdeEsquema(){
        ConsolaDebug.agregarTexto("Creando capas para todo el esquema", ConsolaDebug.SQL);
         for(int i=0; i<getTablasBD().size() ;i++){
            crearCapasDesdeTabla(getTablasBD().get(i), detallesTablasBD.get(i));
        }
    }
    
    public void crearCapasDesdeTabla(String tabla, List<List> detallesTablaBD){
        ConsolaDebug.agregarTexto("\nCreando capas para la tabla " + tabla, ConsolaDebug.PROCESO, false);
        
        try{
            List<String> atributos = detallesTablaBD.get(BDConexion.Detalles.NOMBRE_CAMPOS.ordinal());
            List<TipoDato> tipos = detallesTablaBD.get(BDConexion.Detalles.TIPO_DATOS.ordinal());
            List<List> llaves = detallesTablaBD.get(BDConexion.Detalles.LLAVES.ordinal());

            generador.crearDTO(tabla, atributos, tipos);
            generador.crearDAO(tabla, llaves, atributos, tipos);
            generador.crearObjetoNegocio();
            generador.crearGestor();
        }catch(Exception e){
            ConsolaDebug.agregarTexto("\nHa ocurrido un error en la creacion de capas para la tabla " + tabla, ConsolaDebug.ERROR, false);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Funciones">    
    public static String convertirPascal(String texto){
        StringBuilder pascalCase = new StringBuilder(convertirCamel(texto));
        pascalCase = pascalCase.replace(0, 1, String.valueOf(pascalCase.charAt(0)).toUpperCase());
        
        return pascalCase.toString();
    }
    
    public static String convertirCamel(String texto){
        StringBuilder camelCase = new StringBuilder(texto);
        
        char letra = '-';
        int indiceAux = 0;
        for(int i=0; i<texto.length(); i++){
            letra = texto.charAt(i);
            
            if(letra == '_'){
                char letraSiguiente = texto.charAt(i+1);
                
                camelCase.delete(indiceAux+i, indiceAux+i+1);
                camelCase.replace(indiceAux+i+1, indiceAux+i+1, String.valueOf(letraSiguiente).toUpperCase());
                camelCase.delete(indiceAux+i, indiceAux+i+1);
                
                indiceAux--;
            }
        }
        
        return camelCase.toString();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters">
    public List<String> getTablasBD() {
        return tablasBD;
    }
    
    public List getDetallesTablaBD(int indice) {
        return detallesTablasBD.get(indice);
    }
    
    public Generador getGenerador() {
        return generador;
    }
    
    public String getComentarios() {
        return comentarios;
    }
    // </editor-fold>
}