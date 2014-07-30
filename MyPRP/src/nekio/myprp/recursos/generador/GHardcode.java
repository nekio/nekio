package nekio.myprp.recursos.generador;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.util.ArrayList;
import java.util.List;
import static nekio.myprp.recursos.generador.ControladorGenerador.convertirCamel;
import static nekio.myprp.recursos.generador.ControladorGenerador.convertirPascal;
import nekio.myprp.recursos.utilerias.Globales.TipoDato;
// </editor-fold>

public class GHardcode extends Generador{
    // <editor-fold defaultstate="collapsed" desc="Constructores">
    public GHardcode(){
        this(true);
    }
    
    public GHardcode(boolean estandar){
        super.estandar = estandar;
        
        super.codigoDTO = new ArrayList<String>();
        super.codigoDAO = new ArrayList<String>();
        super.codigoObjetoNegocio = new ArrayList<String>();
        super.codigoGestor = new ArrayList<String>();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Capas del Sistema">
    
    // <editor-fold defaultstate="collapsed" desc="DTO">
    @Override
    public void crearDTO(String tabla, List<String> atributos, List<TipoDato> tipos){
        StringBuilder codigoDTO = new StringBuilder();
        
        StringBuilder contenido = new StringBuilder();
        String linea=null; 
            
        String abrirClase = "public class " + convertirPascal(tabla) + "DTO extends DTO{";
        String modificadorAcceso = "private";
        String tipoDato = null;
        String atributo = null;
        
        String cerrarClase = "}";
        
        String setter = null;
        String getter = null;
        
        // Crear campos
        String declaracionAtributo = null;
        codigoDTO.append(abrirClase);        
        for(int i=0; i<atributos.size(); i++){
            tipoDato = tipos.get(i).getTipoJava();
            atributo = convertirCamel(atributos.get(i));
            declaracionAtributo = modificadorAcceso + " " + tipoDato + " " + atributo + ";";
            
            codigoDTO.append("\n\t" + declaracionAtributo);
        }
        
        // Crear metodo confirmarDTO
        if(!super.isEstandar()){
            String confirmarDTO = 
                    "\n\t@Override" +
                    "\n\tpublic void confirmarDTO(){ " +
                    "\n\t\tcampos = new ArrayList<String>();" +
                    "\n\t\tvalores = new ArrayList();" +
                    "\n\t\ttipoDatos = new ArrayList<Globales.TipoDato>();";

            codigoDTO.append("\n" + confirmarDTO);
            for(int i=0; i<atributos.size(); i++){
                String bloque = formatearConfirmarDTO(tipos.get(i), atributos.get(i));

                codigoDTO.append("\n" + bloque);
            }
            codigoDTO.append("\n\t}");
        }
        
        // Crear parejas de setters y getters
        for(int i=0; i<atributos.size(); i++){
            String bloques = encapsular(tipos.get(i), atributos.get(i));
            
            codigoDTO.append("\n" + bloques);
        }
        
        codigoDTO.append("\n" + cerrarClase);  
        
        this.codigoDTO.add(codigoDTO.toString());
    }
    
    private String formatearConfirmarDTO(TipoDato tipo, String campo){
        String codigo = null;
        
        codigo = 
                "\n\t\tsuper.campos.add(\"" + campo + "\");" +
                "\n\t\tsuper.valores.add(" + convertirCamel(campo) + ");" +
                "\n\t\tsuper.tipoDatos.add(Globales.TipoDato."+ tipo.name() +");";
        
        return codigo;
    }
    
    private String encapsular(TipoDato tipo, String campo){
        String codigo = null;
        
        String tipoDato = tipo.getTipoJava();
        String pascal = convertirPascal(campo);
        String camel = convertirCamel(campo);
        
        String setter = 
                "\n\tpublic void set" + pascal + "(" + tipoDato + " " + camel + ") {" + 
                "\n\t\tthis." + camel + " = " + camel +
                "\n\t}";
        
        String getter = 
                "\n\tpublic " + tipoDato + " get" + pascal + "() {" +
                "\n\t\treturn " + camel + ";" +
                "\n\t}";
        
        codigo = setter + "\n" + getter;
        
        return codigo;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="DAO">
    @Override
    public void crearDAO(String tabla, List<List> llaves, List<String> campos, List<TipoDato> tipos){
         StringBuilder codigoDAO = new StringBuilder();
        
         String tablaPascal = convertirPascal(tabla);
         String tablaCamel = convertirCamel(tabla);
         String PKs = "";
         String FKs = "";
         //String foraneas = "";
         String camposTabla = "";
         int indiceFinal = 0;
         
         List listaPKs = llaves.get(0);
         indiceFinal = listaPKs.size();
         for(int i=0; i<indiceFinal; i++){
             PKs += listaPKs.get(i).toString();
             if(indiceFinal <= 1)
                 break;
             if(i != indiceFinal-1)
                 PKs += ", ";
         }
         
         List listaFKs = llaves.get(1);
         indiceFinal = listaFKs.size();
         for(int i=0; i<indiceFinal; i++){
             FKs += listaFKs.get(i).toString();
             if(indiceFinal <= 1)
                 break;
             if(i != indiceFinal-1)
                 FKs += ", ";
         }
         
         /* TABLAS FORANEAS
         List listaRelaciones = llaves.get(2);
         indiceFinal = listaRelaciones.size();
         for(int i=0; i<indiceFinal; i++){
             foraneas += listaRelaciones.get(i).toString();
            if(indiceFinal <= 1)
                    break;
             if(i != indiceFinal-1)
                 foraneas += ", ";
         }
         */
         
         indiceFinal = campos.size();
         int primerCampo = listaPKs.size();
         for(int i=0; i<indiceFinal; i++){
             if(i >= primerCampo){
                 if(i == primerCampo)
                 camposTabla += ", ";
                 
                camposTabla += campos.get(i).toString();
             }else
                 continue;
             
             if(indiceFinal <= 1)
                 break;
             if(i != indiceFinal-1)
                 camposTabla += ", ";
         }
         
         // Crear campos
        String abrirClase = "public class " + tablaPascal + "DAO extends DAO{";
        codigoDAO.append(abrirClase);
        
        String atributosMiembro =
            "\n\tprivate final String TABLA = \"" + tabla + "\";" +
            "\n\tprivate final String ID = \"" + PKs + "\";" +
            "\n\tprivate final String TODOS_CAMPOS = " +
            "\n\t\tID + \"" + camposTabla + " \";" +
            "\n\n\tprivate " + tablaPascal + "DTO dto;";
        codigoDAO.append(atributosMiembro);
        
        // Crear metodo asignarParametros
        if(!super.isEstandar()){
            String asignarParametros = 
                "\n\n\t@Override" +
                "\n\tpublic void asignarParametros(DTO dto){" +
                "\n\t\tthis.dto = (" + tablaPascal + "DTO) dto;" +
                "\n\n\t\tif(Globales.APP_DEBUG)" +
                "\n\t\t\tConsolaDebug.agregarTexto(\"DAO \" + TABLA + \": Parametros ingresados\", ConsolaDebug.PROCESO);" +
                "\n\t}";
            codigoDAO.append(asignarParametros);
        }
        
        // Crear metodo sobrecargado leer
        
        String setDTOs = "";
        String campo = null;
        TipoDato tipo = null;
        for(int i=0; i<indiceFinal; i++){
            campo = campos.get(i);
            tipo = tipos.get(i);
            
            setDTOs += "\n\t\t\t\t" + formatearSetDTO(campo, tipo);
         }
        
        String leer =
            "\n\n\t@Override" +
            "\n\tpublic ArrayList<DTO> leer(String where){" +
            "\n\t\treturn leer(TODOS_CAMPOS, where, null, null);" +
            "\n\t}" +
            "\n\n\t@Override" +
            "\n\tpublic ArrayList<DTO> leerDesc(String where){" +
            "\n\t\treturn leer(TODOS_CAMPOS, where, ID + \" DESC\", null);" +
            "\n\t}" +
            "\n\n\tpublic ArrayList<DTO> leer(String select, String where, String orderBy, String groupBy){" +
            "\n\t\tArrayList<DTO> lista = new ArrayList<DTO>();" +
            "\n\t\tString consulta = " +
            "\n\t\t\t\"SELECT \" + select + \" \\n\" +" +
            "\n\t\t\t\"FROM \" + Globales.BD_ESQUEMA + \".\" + TABLA + \" \\n\" +" +
            "\n\t\t\t\"WHERE 1=1\\n\";" +
            "\n\n\t\tif(where != null)" +
            "\n\t\t\tconsulta += \"AND \"+ where + \"\\n\";" +
            "\n\t\tif(orderBy != null)" +
            "\n\t\t\tconsulta += \"ORDER BY \"+ orderBy + \"\\n\";" +
            "\n\t\tif(groupBy != null)" +
            "\n\t\t\tconsulta += \"GROUP BY \"+ groupBy + \"\\n\";" +
            "\n\n\t\tif(Globales.APP_DEBUG)" +
            "\n\t\t\tConsolaDebug.agregarTexto(consulta, ConsolaDebug.SQL);" +
            "\n\n\t\ttry{"+
            "\n\t\t\t" + tablaPascal + "DTO dto = null;" +
            "\n\t\t\tResultSet resultados = BDConexion.consultar(consulta);" +
            "\n\n\t\t\twhile(resultados.next()){" +
            "\n\t\t\t\tdto = new " + tablaPascal + "DTO();" +
            "\n" + setDTOs +
            "\n\n\t\t\t\tlista.add(dto);" +
            "\n\t\t\t}" +
            "\n\t\t\tBDConexion.cerrar();" +
            "\n\t\t}catch(Exception e){" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"DAO: Error al leer registros de \" + Globales.BD_ESQUEMA + \".\" + TABLA + \": \" + e, ConsolaDebug.ERROR);" +
            "\n\t\t}" +
            "\n\n\t\treturn lista;" +
            "\n\t}";
        codigoDAO.append(leer);
        
        String leerUno = 
            "\n\n\tpublic DTO leerUno(String where){" +
            "\n\t\treturn leerUno(TODOS_CAMPOS, where, null, null);" +
            "\n\t}" +
            "\n\n\tpublic DTO leerUno(String select, String where, String orderBy, String groupBy){" +
            "\n\t\t" + tablaPascal + "DTO dto = null;" +
            "\n\n\t\tString consulta = " +
            "\n\t\t\t\"SELECT \" + select + \" \\n\"" +
            "\n\t\t\t\"FROM \" + Globales.BD_ESQUEMA + \".\" + TABLA + \" \\n\" +" +
            "\n\t\t\t\"WHERE 1=1\\n\";" +
            "\n\n\t\tif(where != null)" +
            "\n\t\t\tconsulta += \"AND \"+ where + \"\\n\";" +
            "\n\t\tif(orderBy != null)" +
            "\n\t\t\tconsulta += \"ORDER BY \"+ orderBy + \"\\n\";" +
            "\n\t\tif(groupBy != null)" +
            "\n\t\t\tconsulta += \"GROUP BY \"+ groupBy + \"\\n\";" +
            "\n\n\t\tif(Globales.APP_DEBUG)" +
            "\n\t\t\tConsolaDebug.agregarTexto(consulta, ConsolaDebug.SQL);" +
            "\n\n\t\ttry{" + 
            "\n\t\t\tResultSet resultados = BDConexion.consultar(consulta);" +
            "\n\t\t\tdto = new " + tablaPascal + "DTO();" +
            "\n\n\t\t\twhile(resultados.next()){" +
            setDTOs +
            "\n\t\t\t}" +
            "\n\n\t\t\tBDConexion.cerrar();" +
            "\n\t\t}catch(Exception e){" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"Error al leer un registro de \" + Globales.BD_ESQUEMA + \".\" + TABLA + \": \" + e, ConsolaDebug.ERROR);" +
            "\n\t\t}" +
            "\n\n\t\treturn dto;" +
            "\n\t}";
        codigoDAO.append(leerUno);
        
        super.codigoDAO.add(codigoDAO.toString());
    }
    
    private String formatearSetDTO(String campo, TipoDato tipo){
        String codigo = null;
        String campoPascal = convertirPascal(campo);
        
        switch(tipo){
            case TEXTO_LARGO:
            case TEXTO:
            case NUMERO:
            case FECHA:
                codigo = "dto.set"+ campoPascal +"(resultados.get" + tipo.getEncapsulado() + "(\"" + campo + "\"));";
            break;
            case BOOLEANO:
                codigo = "dto.set"+ campoPascal +"(resultados.getInt" + "(\"" + campo + "\")==1?true:false);";
            break;
            case CARACTER:
                codigo = "dto.set"+ campoPascal +"(resultados.getString" + "(\"" + campo + "\").charAt(0));";
            break;
            case BLOB:
                codigo =
                    "\n\t\t\t\tBlob blob = resultados.getBlob(\"" + campo + "\");" +
                    "\n\t\t\t\tbyte[] datos = blob.getBytes(1, (int)blob.length());" +
                    "\n\t\t\t\tImage imagen = null;" +
                    "\n\t\t\t\ttry{" +
                    "\n\t\t\t\t\timagen = ImageIO.read(new ByteArrayInputStream(datos));" +
                    "\n\t\t\t\t}catch (IOException e) {" +
                    "\n\t\t\t\t\tConsolaDebug.agregarTexto(\"No se pudo leer el stream de la imagen: \"+e, ConsolaDebug.ERROR);" +
                    "\n\t\t\t\t}" +
                    "\n\t\t\t\tdto.set" + campoPascal + "(imagen);\n";
            break;
        }
        
        return codigo;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Objeto de Negocio">
    @Override
    public void crearObjetoNegocio(){}
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Gestor">
    @Override
    public void crearGestor(){}
    // </editor-fold>
    
    // </editor-fold>
}
