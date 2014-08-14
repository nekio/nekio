package nekio.myprp.recursos.generador;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Globales.TipoDato;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
// </editor-fold>

public class ControladorGenerador {
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private String catalogo;
    
    private List<String> tablasBD;
    private List<List> detallesTablasBD;
    protected Generador generador;
    private boolean estandar;
    private boolean primitivos;
    
    protected String comentarios = 
            "/* Es necesario realizar un \"Fix Imports\" en el IDE para incluir las librerias necesarias, " +
            "\n * Agregarle tambien el package correspondiente " +
            "\n * y adicionalmente \"Format\" si se desajustan las identaciones" +
            "\n */\n\n";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ControladorGenerador(List<String> tablasBD, List<List> detallesTablasBD){
        this(tablasBD, detallesTablasBD, false, true);
    }
    
    public ControladorGenerador(List<String> tablasBD, List<List> detallesTablasBD, boolean estandar, boolean primitivos){
        try{
            this.catalogo = BDConexion.getConnection().getCatalog();
        }catch(Exception e){
            this.catalogo = "ERROR_EN_CATALOGO";
        }
        
        this.tablasBD = tablasBD;
        this.detallesTablasBD = detallesTablasBD;
        this.generador = new GHardcode(estandar, primitivos);
        this.estandar = estandar;
        this.primitivos = primitivos;
        
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
            List<String> tablasForaneas = (List<String>)detallesTablaBD.get(BDConexion.Detalles.LLAVES.ordinal()).get(2);
            List<String> atributos = detallesTablaBD.get(BDConexion.Detalles.NOMBRE_CAMPOS.ordinal());
            List<TipoDato> tipos = detallesTablaBD.get(BDConexion.Detalles.TIPO_DATOS.ordinal());
            List<List> llaves = detallesTablaBD.get(BDConexion.Detalles.LLAVES.ordinal());

            generador.crearDTO(tabla, tablasForaneas, atributos, tipos);
            generador.crearDAO(tabla, llaves, atributos, tipos);
            generador.crearObjetoNegocio(tabla);
            generador.crearGestor(tabla, catalogo);
        }catch(Exception e){
            ConsolaDebug.agregarTexto("\nHa ocurrido un error en la creacion de capas para la tabla " + tabla + "\n[" + e + "]\n", ConsolaDebug.ERROR, false);
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
    
    // <editor-fold defaultstate="collapsed" desc="Obtener Archivos de las Capas">
    public List<String> obtenerArchivos(int indiceTabla){
        List<String> archivos = new ArrayList<String>();
        
        String archivoDAO = getArchivo(Generador.Capas.DAO, indiceTabla);
        String archivoDTO = getArchivo(Generador.Capas.DTO, indiceTabla);
        String archivoObjetoNegocio = getArchivo(Generador.Capas.OBJETO_NEGOCIO, indiceTabla);
        String archivoGestor = getArchivo(Generador.Capas.GESTOR, indiceTabla);
        String archivoVista = getArchivo(Generador.Capas.VISTA, indiceTabla);

        archivos.add(archivoDAO);
        archivos.add(archivoDTO);
        archivos.add(archivoObjetoNegocio);
        archivos.add(archivoGestor);
        archivos.add(archivoVista);
        
        return archivos;
    }
    
    private String getArchivo(Generador.Capas capa, int indiceTabla){
        String ruta = Globales.RUTA_MODULOS + "/" + catalogo;
        String tabla = tablasBD.get(indiceTabla);
        String archivo = ruta + "/" + capa.getPaquete() + convertirPascal(tabla) + capa.getPostfijo() + Generador.EXTENSION;

        return archivo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public List<String> getTablasBD() {
        return tablasBD;
    }
    
    public List getDetallesTablaBD(int indice) {
        return detallesTablasBD.get(indice);
    }
    
    public void setTipos(int tabla, List<Globales.TipoDato> tipos){
        ConsolaDebug.agregarTexto("\n\n" + Globales.APP_SEPARADOR, ConsolaDebug.BITACORA, false);
        ConsolaDebug.agregarTexto("\n" + "Recreando capas ...\n", ConsolaDebug.BITACORA, false);
        ConsolaDebug.agregarTexto(Globales.APP_SEPARADOR + "\n\n", ConsolaDebug.BITACORA, false);

        detallesTablasBD.get(tabla).set(BDConexion.Detalles.TIPO_DATOS.ordinal(), tipos);
        
        this.generador = null;
        this.generador = new GHardcode(estandar, primitivos);
        crearCapasDesdeEsquema();
    }
    
    public Generador getGenerador() {
        return generador;
    }
    
    public String getComentarios() {
        return comentarios;
    }
    // </editor-fold>
}
