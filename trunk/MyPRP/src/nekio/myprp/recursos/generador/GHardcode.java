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
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Globales.TipoDato;
// </editor-fold>

public class GHardcode extends Generador{
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private String tablaPascal;
    private String tablaCamel;
    private String setDTOs;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructores">
    public GHardcode(){
        this(true, true);
    }
    
    public GHardcode(boolean estandar, boolean primitivos){
        super.primitivos = primitivos;
        super.estandar = estandar;
        
        super.codigoProcBD = new ArrayList<String>();
        super.codigoDTO = new ArrayList<String>();
        super.codigoDAO = new ArrayList<String>();
        super.codigoObjetoNegocio = new ArrayList<String>();
        super.codigoGestor = new ArrayList<String>();
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Capas del Sistema">
    
    // <editor-fold defaultstate="collapsed" desc="Procedimientos BD">
    @Override
    protected void crearProcedimientosBD(String catalogo, String tabla, List<String> atributos, List<TipoDato> tipos, List<Integer> precisiones) {
        StringBuilder codigoBD = new StringBuilder();
        
        String comentarios = 
            "--" +
            "\n-- PROCEDIMIENTOS DE " +tabla.toUpperCase() +
            "\n--";
        
        codigoBD.append(comentarios);
        codigoBD.append(procInsertar(catalogo, tabla, atributos, tipos, precisiones));
        codigoBD.append(procActualizar(catalogo, tabla, atributos, tipos, precisiones));
        codigoBD.append(procEliminar(catalogo, tabla));
        
        super.codigoProcBD.add(codigoBD.toString());
    }
    
    private String procInsertar(String catalogo, String tabla, List<String> atributos, List<TipoDato> tipos, List<Integer> precisiones){
        StringBuilder codigo = new StringBuilder();
        String tablaPascal = convertirPascal(tabla);
        
        int cantidadCampos = atributos.size();
        
        String parametros = "";
        TipoDato tipo = null;
        for(int i=1; i < cantidadCampos; i++){ //Comienza del indice 1, para no considerar la PK
            tipo = tipos.get(i);
            
            if(tipo == TipoDato.FECHA || tipo == TipoDato.BLOB || tipo == TipoDato.TEXTO_LARGO)
                parametros += "\n\tIN p" + convertirPascal(atributos.get(i)) + " " + tipo.getTipoSQL() + ",";
            else
                parametros += "\n\tIN p" + convertirPascal(atributos.get(i)) + " " + tipo.getTipoSQL() + "(" + precisiones.get(i) + "),";
        }
        parametros = parametros.substring(0, parametros.lastIndexOf(','));
        
        String campos = "";
        String camposPascal = "";
        for(int i=0; i < cantidadCampos; i++){ 
            campos += atributos.get(i) + ", ";
            camposPascal += "p" + convertirPascal(atributos.get(i)) + ", ";
            
            if((i+1)%3 == 0){
                campos += "\n\t\t";
                camposPascal += "\n\t\t";
            }
        }
        campos = campos.substring(0, campos.lastIndexOf(','));
        camposPascal = camposPascal.substring(0, camposPascal.lastIndexOf(','));
        
        String camposBitacora = "";
        for(int i=0; i < cantidadCampos; i++){
            tipo = tipos.get(i);
            
            if(tipo == TipoDato.FECHA)
                camposBitacora += "\n\t\t\t\"DATE_FORMAT('\",CAST(IFNULL(p" + convertirPascal(atributos.get(i)) + ",'NULL') AS CHAR),\"', '%Y-%m-%d %H:%i:%s')\", ', ',";
            else if (tipo == TipoDato.BLOB)
                continue;
            else
                camposBitacora += "\n\t\t\tIFNULL(CONCAT(\"'\",p" + convertirPascal(atributos.get(i)) + ",\"'\"),'NULL'), ', ',";
        }
        camposBitacora = camposBitacora.substring(0, camposBitacora.lastIndexOf(','));
        
        String listaCampos = "";
        for(int i=0; i < cantidadCampos; i++){ 
            if(!(tipos.get(i) == TipoDato.BLOB))
                listaCampos += atributos.get(i) + ", ";
        }
        listaCampos = listaCampos.substring(0, listaCampos.lastIndexOf(','));
        
        String bitacora = 
            "\n\n\tCALL nekio_herramientas.insertar_bitacora(" +
            "\n\t\tvIdSistema," +
            "\n\t\tNULL," +
            "\n\t\t'" + tabla + "'," +
            "\n\t\t'I'," +
            "\n\t\t'" + listaCampos +  "'," +
            "\n\t\tCONCAT(" +
            camposBitacora +
            "\n\t\t)" +
            "\n\t);";
        
        codigo.append(
                "\n\nDROP PROCEDURE IF EXISTS " + catalogo + ".insertar_" + tabla + ";" +
                "\nDELIMITER //" +
                "\nCREATE PROCEDURE " + catalogo + ".insertar_" + tabla + "(" +
                parametros +
                "\n)BEGIN"+
                "\n\tDECLARE pId" + tablaPascal + " INT(11);" +
                procIdSistema(catalogo) +
                "\n\n\tSELECT IFNULL(MAX(id_" + tabla + "),0) + 1" +
                "\n\t\tINTO pId" + tablaPascal + "" +
                "\n\tFROM " + catalogo + "." + tabla + ";" +
                "\n\t-- WHERE id_ = pId_;" +
                "\n\n\tINSERT INTO " + catalogo + "." + tabla + "\n\t\t(" + campos + ")" + 
                "\n\tVALUES\n\t\t(" + camposPascal + ");" +
                bitacora +
                "\nEND" +
                "\n// DELIMITER ;"
        );
        
        return codigo.toString();
    }
    
    private String procActualizar (String catalogo, String tabla, List<String> atributos, List<TipoDato> tipos, List<Integer> precisiones){
        StringBuilder codigo = new StringBuilder();
        String tablaPascal = convertirPascal(tabla);
        
        int cantidadCampos = atributos.size();
        
        String parametros = "";
        TipoDato tipo = null;
        for(int i=0; i < cantidadCampos; i++){ //Comienza del indice 0, para considerar la PK
            tipo = tipos.get(i);

            if(tipo == TipoDato.FECHA || tipo == TipoDato.BLOB || tipo == TipoDato.TEXTO_LARGO)
                parametros += "\n\tIN p" + convertirPascal(atributos.get(i)) + " " + tipo.getTipoSQL() + ",";
            else
                parametros += "\n\tIN p" + convertirPascal(atributos.get(i)) + " " + tipo.getTipoSQL() + "(" + precisiones.get(i) + "),";
        }
        parametros = parametros.substring(0, parametros.lastIndexOf(','));
        
        String pk = atributos.get(0) + " = " + "p" + convertirPascal(atributos.get(0));
        String campos = "";
        for(int i=1; i < cantidadCampos; i++) //Comienza del indice 0, para considerar la PK
            campos += "\n\t\t" + atributos.get(i) + " = " + "p" + convertirPascal(atributos.get(i)) + ",";
        campos = campos.substring(0, campos.lastIndexOf(','));
        
        String camposBitacora = "";
        for(int i=0; i < cantidadCampos; i++){
            tipo = tipos.get(i);
            
            if(tipo == TipoDato.FECHA)
                camposBitacora += "\n\t\t\t\"" + atributos.get(i) + " = \", \"DATE_FORMAT('\",CAST(IFNULL(p" + convertirPascal(atributos.get(i)) + ",'NULL') AS CHAR),\"', '%Y-%m-%d %H:%i:%s')\", \",\\n\",";
            else if (tipo == TipoDato.BLOB)
                continue;
            else
                camposBitacora += "\n\t\t\t\"" + atributos.get(i) + " = \", IFNULL(CONCAT(\"'\",p" + convertirPascal(atributos.get(i)) + ",\"'\"),'NULL'), \",\\n\",";
        }
        camposBitacora = camposBitacora.substring(0, camposBitacora.lastIndexOf(','));
        
        String bitacora = 
            "\n\n\tCALL nekio_herramientas.insertar_bitacora(" +
            "\n\t\tvIdSistema," +
            "\n\t\tNULL," +
            "\n\t\t'" + tabla + "'," +
            "\n\t\t'U'," +
            "\n\t\tCONCAT(" +
            camposBitacora +
            "\n\t\t)," +
            "\n\t\tCONCAT(\"id_" + tabla + " = \", IFNULL(CONCAT(\"'\",pId" + tablaPascal + ",\"'\"),'NULL'))" +
            "\n\t);";
        
        codigo.append(
                "\n\nDROP PROCEDURE IF EXISTS " + catalogo + ".actualizar_" + tabla + ";" +
                "\nDELIMITER //" +
                "\nCREATE PROCEDURE " + catalogo + ".actualizar_" + tabla + "(" +
                parametros +
                "\n)BEGIN"+
                procIdSistema(catalogo) +
                "\n\n\tUPDATE " + catalogo + "." + tabla +
                "\n\tSET" + campos +
                "\n\tWHERE " + pk + ";" +
                "\n\t-- AND id_ = pId;" + 
                bitacora +
                "\nEND" +
                "\n// DELIMITER ;"
        );
        
        return codigo.toString();
    }
    
    private String procEliminar(String catalogo, String tabla){
        StringBuilder codigo = new StringBuilder();
        String tablaPascal = convertirPascal(tabla);
        
        String pk = "id_" + tabla + " = " + "pId" + convertirPascal(tabla);
        
        String bitacora = 
            "\n\n\tCALL nekio_herramientas.insertar_bitacora(" +
            "\n\t\tvIdSistema," +
            "\n\t\tNULL," +
            "\n\t\t'" + tabla + "'," +
            "\n\t\t'D'," +
            "\n\t\tNULL," +
            "\n\t\tCONCAT(\"id_" + tabla + " = \", pId" + tablaPascal + ")" +
            "\n\t);";
        
        codigo.append(
                "\n\nDROP PROCEDURE IF EXISTS " + catalogo + ".eliminar_" + tabla + ";" +
                "\nDELIMITER //" +
                "\nCREATE PROCEDURE " + catalogo + ".eliminar_" + tabla + "(" +
                "\n\tp" + "id" + tablaPascal + " INT(11)" +
                "\n)BEGIN" +
                procIdSistema(catalogo) +
                "\n\n\tDELETE FROM " + catalogo + "." + tabla +
                "\n\tWHERE " + pk + ";" +
                "\n\t-- AND id_ = pId;" + 
                bitacora +
                "\nEND" +
                "\n// DELIMITER ;"
        );
        
        return codigo.toString();
    }
    
    private String procIdSistema(String catalogo){
        String codigo = 
            "\n\tDECLARE vIdSistema INT(2);" +
            "\n\n\tSELECT nekio_herramientas.id_sistema(" + catalogo + ".esquema())" +
            "\n\tINTO vIdSistema;";
        
        return codigo;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="DTO">
    @Override
    public void crearDTO(String tabla, List<String> tablasForaneas, List<String> atributos, List<TipoDato> tipos){
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
            tipoDato = null;
            if(super.primitivos)
                tipoDato = tipos.get(i).getTipoPrimitivoJava();
            else
                tipoDato = tipos.get(i).getTipoClaseJava();
            
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
                    "\n\t\ttablasForaneas = new ArrayList<String>();" +
                    "\n\t\tvalores = new ArrayList();" +
                    "\n\t\ttipoDatos = new ArrayList<Globales.TipoDato>();" +
                    "\n\t\tvaloresLOV = new ArrayList<String>();" +
                    "\n\t\tcamposExtrasLOV = new ArrayList<List>();";

            codigoDTO.append("\n" + confirmarDTO);
            
            for(int i=0; i<atributos.size(); i++){
                String tablaForanea = null;
                try{
                    tablaForanea = tablasForaneas.get(i);
                }catch(Exception e){}
                
                String bloque = formatearConfirmarDTO(tipos.get(i), atributos.get(i), i, tablaForanea);

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
    
    private String formatearConfirmarDTO(TipoDato tipo, String campo, int indice, String tablaForanea){
        String codigo = null;
        
        codigo = 
                "\n\t\tsuper.campos.add(\"" + campo + "\");" +
                "\n\t\tsuper.valores.add(" + convertirCamel(campo) + ");" +
                "\n\t\tsuper.tipoDatos.add(Globales.TipoDato."+ tipo.name() +");";
        
        if( (indice != 0) && (campo.startsWith(Globales.BD_TABLA_ID)) ){
            codigo += 
                "\n\t\t//super.valoresLOV.add(" + Globales.BD_TABLA_DESC + ");" +
                "\n\t\t//super.camposExtrasLOV.add(new ArrayList<String>() {{add(\"\"); add(\"\");}});" + 
                //"\n\t\tsuper.tablasForaneas.add(\"" + tablaForanea + "\");";
                    "\n\t\tsuper.tablasForaneas.add(\"null\");";
        }
        
        return codigo;
    }
    
    private String encapsular(TipoDato tipo, String campo){
        String codigo = null;
        
        String tipoDato = null;
        if(super.primitivos)
            tipoDato = tipo.getTipoPrimitivoJava();
        else
            tipoDato = tipo.getTipoClaseJava();
            
        String pascal = convertirPascal(campo);
        String camel = convertirCamel(campo);
        boolean booleano = tipo==tipo.BOOLEANO;
        
        String setter = 
                "\n\tpublic void set" + pascal + "(" + tipoDato + " " + camel + ") {" + 
                "\n\t\tthis." + camel + " = " + camel + ";" +
                "\n\t}";
        
        if(booleano){
            setter += 
                "\n\n\tpublic void set" + pascal + "(int " + camel + ") {" +
                "\n\t\tif(" + camel + " == 1)" +
                "\n\t\t\tthis." + camel + " = true;" +
                "\n\t\telse" +
                "\n\t\t\tthis." + camel + " = false;" +
                "\n\t}";
        }
        
        String prefijoGetter = booleano ? " is" : " get";
        String getter = 
                "\n\tpublic " + tipoDato + prefijoGetter + pascal + "() {" +
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
        
         tablaPascal = convertirPascal(tabla);
         tablaCamel = convertirCamel(tabla);
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
        
        setDTOs = "";
        String campo = null;
        TipoDato tipo = null;
        for(int i=0; i<indiceFinal; i++){
            campo = campos.get(i);
            tipo = tipos.get(i);
            
            setDTOs += "\n\t\t\t\t" + formatearSetDTO(campo, tipo);
         }
        
        codigoDAO.append(leerDAO(campos, tipos));
        codigoDAO.append(leerUnoDAO(campos, tipos));
        codigoDAO.append(agregarDAO(campos, tipos));
        codigoDAO.append(modificarDAO(campos, tipos));
        codigoDAO.append(eliminarDAO(campos, tipos));
        codigoDAO.append("\n}");
        
        super.codigoDAO.add(codigoDAO.toString());
    }
    
    private String formatearSetDTO(String campo, TipoDato tipo){
        String codigo = null;
        String campoPascal = convertirPascal(campo);
        
        switch(tipo){
            case TEXTO_LARGO:
            case TEXTO:
            case NUMERO:
            case DECIMAL:
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
    
    private String leerDAO(List<String> campos, List<TipoDato> tipos){
        String codigo = null;
        
        codigo =
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
            "\n\t\t\t\"FROM \" + Globales.BD_DESC_ESQUEMA + \".\" + TABLA + \" \\n\" +" +
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
            "\n\t\t\tConsolaDebug.agregarTexto(\"DAO: Error al leer registros de \" + Globales.BD_DESC_ESQUEMA + \".\" + TABLA + \": \" + e, ConsolaDebug.ERROR);" +
            "\n\t\t}" +
            "\n\n\t\treturn lista;" +
            "\n\t}";
        
        return codigo;
    }
    
    private String leerUnoDAO(List<String> campos, List<TipoDato> tipos){
        String codigo = null;
        
        codigo = 
            "\n\n\tpublic DTO leerUno(String where){" +
            "\n\t\treturn leerUno(TODOS_CAMPOS, where, null, null);" +
            "\n\t}" +
            "\n\n\tpublic DTO leerUno(String select, String where, String orderBy, String groupBy){" +
            "\n\t\t" + tablaPascal + "DTO dto = null;" +
            "\n\n\t\tString consulta = " +
            "\n\t\t\t\"SELECT \" + select + \" \\n\" +" +
            "\n\t\t\t\"FROM \" + Globales.BD_DESC_ESQUEMA + \".\" + TABLA + \" \\n\" +" +
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
            "\n\t\t\tConsolaDebug.agregarTexto(\"Error al leer un registro de \" + Globales.BD_DESC_ESQUEMA + \".\" + TABLA + \": \" + e, ConsolaDebug.ERROR);" +
            "\n\t\t}" +
            "\n\n\t\treturn dto;" +
            "\n\t}";
        
        return codigo;
    }
    
    private String agregarDAO(List<String> campos, List<TipoDato> tipos){
        String codigo = null;
        
        String inserciones = "";
        String campo = null;
        TipoDato tipo = null;
        for(int i=0; i<campos.size(); i++){
            campo = campos.get(i);
            tipo = tipos.get(i);
            
            if(i!=0) //No insertar el ID
                inserciones += "\n\t\t\t" + formatearInsertar(i, campo, tipo, true);
        }
        
        codigo =
            "\n\n\t@Override" +
            "\n\tpublic int agregar(){" +
            "\n\t\tint resultado = 1;" +
            "\n\n\t\tString accion = super.INSERTAR;" +
            "\n\t\tint parametros = " + (campos.size()-1) + ";" + //No considera el ID, por eso se resta 1
            "\n\t\tString procedimiento = super.obtenerProcedimiento(Globales.BD_DESC_ESQUEMA, accion, TABLA, parametros);" +
            "\n\n\t\tif(Globales.APP_DEBUG)" +
            "\n\t\t\tConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.PROCESO);" +
            "\n\n\t\ttry{" +
            "\n\t\t\tConnection conexion = BDConexion.getConnection();" +
            "\n\n\t\t\tCallableStatement procInsertar = conexion.prepareCall(procedimiento);" +
            inserciones +
            "\n\t\t\tprocInsertar.execute();" +
            "\n\n\t\t\tconexion.commit();" +
            "\n\t\t\tBDConexion.cerrar();" +
            "\n\n\t\t\tresultado = 0;" +
            "\n\t\t}catch(Exception e){" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"No se pudo \" + accion + \" en la tabla \" + Globales.BD_DESC_ESQUEMA + \".\" + TABLA + \"\\n\"+e, ConsolaDebug.ERROR);" +
            "\n\t\t}" +
            "\n\n\t\treturn resultado;" +
            "\n\t}";
        
        return codigo;
    }
    
    private String formatearInsertar(int indice, String campo, TipoDato tipo, boolean insertar){
        String codigo = null;
        String campoPascal = convertirPascal(campo);
        
        String accion = null;
        if(insertar)
            accion = "Insertar";
        else
            accion = "Actualizar";
        
        String instruccion = "proc" + accion + ".set" + tipo.getEncapsulado() +"(" + indice + ", ";
        
        switch(tipo){
            case TEXTO_LARGO:
            case TEXTO:
            case NUMERO:
            case DECIMAL:
                codigo = instruccion  + "dto.get" + campoPascal + "());";
            break;
            case FECHA:
                codigo = instruccion + "new java.sql.Timestamp(dto.get" + campoPascal + "().getTime()));";
            break;
            case BOOLEANO:
                codigo = instruccion + "dto.is" + campoPascal + "()==true?1:0);";
            break;
            case CARACTER:
                codigo = instruccion + "String.valueOf(dto.get" + campoPascal + "()));";
            break;
            case BLOB:
                codigo = "/* INSERCION DE IMAGEN EN DESARROLLO */";
                    /*"\n\t\t\t\tdimension = ImagenDTO.TipoImagen.TipoImagen(dto.getTipo()).getDimension();" +
                    "\n\n\t\t\t\tInputStream imagen = null;" +
                    "\n\t\t\t\tint longitud = 0;" +
                    "\n\n\t\t\t\tString rutaTemporal = ImagenEnvoltorio.crearImagenTemporal(dimension, dto.getRutaImagen());" +
                    "\n\t\t\t\tFile archivo = new File(rutaTemporal);" +
                    "\n\t\t\t\timagen = new FileInputStream(archivo);" +
                    "\n\t\t\t\tlongitud = (int) archivo.length();" +
                    "\n\n\t\t\t\tprocInsertar.setBinaryStream(" + indice + ", imagen, longitud);\n";*/
            break;
        }
        
        return codigo;
    }
    
    private String modificarDAO(List<String> campos, List<TipoDato> tipos){
        String codigo = null;
        
        String actualizaciones = "";
        String campo = null;
        TipoDato tipo = null;
        for(int i=0; i<campos.size(); i++){
            campo = campos.get(i);
            tipo = tipos.get(i);
            
            actualizaciones += "\n\t\t\t" + formatearInsertar(i+1, campo, tipo, false);
        }
        
        codigo =
            "\n\n\t@Override" +
            "\n\tpublic int modificar() {" +
            "\n\t\tint resultado = 1;" +
            "\n\t\tString accion = super.ACTUALIZAR;" +
            "\n\t\tint parametros = " + campos.size() + ";" +
            "\n\t\tString procedimiento = super.obtenerProcedimiento(Globales.BD_DESC_ESQUEMA, accion, TABLA, parametros);" +
            "\n\n\t\tif(Globales.APP_DEBUG)" +
            "\n\t\t\tConsolaDebug.agregarTexto(procedimiento, ConsolaDebug.PROCESO);" +
            "\n\n\t\ttry{" +
            "\n\t\t\tConnection conexion = BDConexion.getConnection();" +
            "\n\n\t\t\tCallableStatement procActualizar = conexion.prepareCall(procedimiento);" +
            actualizaciones +
            "\n\t\t\tprocActualizar.execute();" +
            "\n\n\t\t\tconexion.commit();" +
            "\n\t\t\tBDConexion.cerrar();" +
            "\n\n\t\t\tresultado = 0;" +
            "\n\t\t}catch(Exception e){" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"No se pudo \" + accion + \" en la tabla \" + Globales.BD_DESC_ESQUEMA + \".\" + TABLA + \"\\n\"+e, ConsolaDebug.ERROR);" +
            "\n\t\t}" +
            "\n\n\t\treturn resultado;" +
            "\n\t}";
        
        return codigo;
    }
    
    private String eliminarDAO(List<String> campos, List<TipoDato> tipos){
        String codigo = null;
        
        codigo =
            "\n\n\t@Override" +
            "\n\tpublic int eliminar() {" +
            "\n\t\tint resultado = 1;" +
            "\n\n\t\tString accion = super.ELIMINAR;" +
            "\n\t\tint parametros = 1;" +
            "\n\t\tString procedimiento = super.obtenerProcedimiento(Globales.BD_DESC_ESQUEMA, accion, TABLA, parametros);" +
            "\n\n\t\tif(Globales.APP_DEBUG)" +
            "\n\t\t\tConsolaDebug.agregarTexto(procedimiento + \" : ID - \" + dto.getId" + tablaPascal + "(), ConsolaDebug.PROCESO);" +
            "\n\n\t\ttry{" +
            "\n\t\t\tConnection conexion = BDConexion.getConnection();" +
            "\n\n\t\t\tCallableStatement procEliminar = conexion.prepareCall(procedimiento);" +
            "\n\t\t\tprocEliminar.setInt(1, dto.getId" + tablaPascal + "());" +
            "\n\t\t\tprocEliminar.execute();" +
            "\n\n\t\t\tconexion.commit();" +
            "\n\t\t\tBDConexion.cerrar();" +
            "\n\n\t\t\tresultado = 0;" +
            "\n\t\t}catch(Exception e){" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"No se pudo \" + accion + \" en la tabla \" + Globales.BD_DESC_ESQUEMA + \".\" + TABLA + \"\\n\"+e, ConsolaDebug.ERROR);" +
            "\n\t\t}" +
            "\n\t\treturn resultado;\n" +
            "\n\t}";
        
        return codigo;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Objeto de Negocio">
    @Override
    public void crearObjetoNegocio(String tabla){
        StringBuilder codigoObjetoNegocio = new StringBuilder();
        
        tablaPascal = convertirPascal(tabla);
        tablaCamel = convertirCamel(tabla);
         
        String abrirClase = "public class " + tablaPascal + " extends ObjetoNegocio{";
        codigoObjetoNegocio.append(abrirClase);
        
        String ejecutar = 
            "\n\t@Override" +
            "\n\tpublic String ejecutar(int metodo, Gestor gestor, DTO dto){" +
            "\n\t\tString resultado = super.consultarAccion(metodo, new " + tablaPascal + "DAO(), dto);" +
            "\n\n\t\treturn resultado;" +
            "\n\t}";
        
        String consultarId =
            "\n\n\t@Override" +
            "\n\tpublic String consultarId(Gestor gestor) {" +
            "\n\t\t" + tablaPascal + "DAO dao = new " + tablaPascal + "DAO();" +
            "\n\t\t" + tablaPascal + "DTO dto = (" + tablaPascal + "DTO)gestor.getDTO();" +
            "\n\t\tString id = String.valueOf(dto.getId" + tablaPascal + "());" +
            "\n\n\t\t" + tablaPascal + "DTO filtroDTO = (" + tablaPascal + "DTO) dao.leerUno(\"id_" + tabla + " = \" + id);" +
            "\n\n\t\tgestor.setListaDTO(new ArrayList<DTO>());" +
            "\n\t\tgestor.getListaDTO().add(filtroDTO);" +
            "\n\n\t\tif(Globales.APP_DEBUG)" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"DTOs obtenidos al consultar ID: \" + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);" +
            "\n\n\t\treturn Globales.RES_OK;" +
            "\n\t}";
        
        String consultarSeleccion = 
            "\n\n\t@Override" +
            "\n\tpublic String consultarSeleccion(Gestor gestor) {" +
            "\n\t\t" + tablaPascal + "DAO dao = new " + tablaPascal + "DAO();" +
            "\n\t\tgestor.setListaDTO(dao.leer(null));" +
            "\n\n\t\tif(Globales.APP_DEBUG)" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"DTOs obtenidos al consultar seleccion: \" + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);" +
            "\n\n\t\treturn Globales.RES_OK;" +
            "\n\t}";
        
        String consultarSeleccionDesc = 
            "\n\n\t@Override" +
            "\n\tpublic String consultarSeleccionDesc(Gestor gestor) {" +
            "\n\t\t" + tablaPascal + "DAO dao = new " + tablaPascal + "DAO();" +
            "\n\t\tgestor.setListaDTO(dao.leerDesc(null));" +
            "\n\n\t\tif(Globales.APP_DEBUG)" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"DTOs obtenidos al consultar seleccion: \" + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);" +
            "\n\n\t\treturn Globales.RES_OK;" +
            "\n\t}";
        
        String consultarBusqueda = 
            "\n\n\t@Override" +
            "\n\tpublic String consultarBusqueda(Gestor gestor) {" +
            "\n\t\tString where =\" WHERE 1=1 \";" +
            "\n\n\t\tif(!super.busqueda.equals(\"\") && super.busqueda != null)" +
            "\n\t\t\twhere += \"AND (descripcion) \" +" +
            "\n\t\t\t\t\"LIKE '%\" +   super.busqueda + \"%'\";" +
            "\n\n\t\tDAO dao = new " + tablaPascal + "DAO();" +
            "\n\t\tgestor.setListaDTO(dao.leer(where));" +
            "\n\n\t\tif(Globales.APP_DEBUG){" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"Consulta de busqueda:\" + where, ConsolaDebug.SQL);" +
            "\n\t\t\tConsolaDebug.agregarTexto(\"DTOs obtenidos al consultar Busqueda: \" + gestor.getListaDTO().size(), ConsolaDebug.PROCESO);" +
            "\n\t\t}" +
            "\n\n\t\treturn Globales.RES_OK;" +
            "\n\t}";
        
        String obtenerModelo = 
            "\n\n\t@Override" +
            "\n\tpublic " + tablaPascal + "DTO obtenerModelo(Gestor gestor){" +
            "\n\t\t" + tablaPascal + "DTO dto = new " + tablaPascal + "DTO();" +
            "\n\n\t\treturn dto;" +
            "\n\t}";
        
        codigoObjetoNegocio.append(ejecutar);
        codigoObjetoNegocio.append(consultarId);
        codigoObjetoNegocio.append(consultarSeleccion);
        codigoObjetoNegocio.append(consultarSeleccionDesc);
        codigoObjetoNegocio.append(consultarBusqueda);
        codigoObjetoNegocio.append(obtenerModelo);
        codigoObjetoNegocio.append("\n}");
        
        super.codigoObjetoNegocio.add(codigoObjetoNegocio.toString());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Gestor">
    @Override
    public void crearGestor(String tabla, String catalogo){
        StringBuilder codigoGestor = new StringBuilder();
        
        tablaPascal = convertirPascal(tabla);
        tablaCamel = convertirCamel(tabla);
        
        String abrirClase = "public class " + tablaPascal + "Gestor extends Gestor{";
        codigoGestor.append(abrirClase);
        
        String atributos = 
            "\n\tprivate final Gestor GESTOR = this;" +
            "\n\tprivate final " + tablaPascal + " OBJETO_NEGOCIO = new " + tablaPascal + "();" +
            "\n\tprivate final " + tablaPascal + "DAO DAO = new " + tablaPascal + "DAO();" +
            "\n\tprivate final int MODULO = Globales.MOD_" + catalogo.toUpperCase() + ";";
        
        String constructor = 
            "\n\n\tpublic " + tablaPascal + "Gestor(){" +
            "\n\t\tsuper.gestor = GESTOR;" +
            "\n\t\tsuper.objetoNegocio = OBJETO_NEGOCIO;" +
            "\n\t\tsuper.dao = DAO;" +
            "\n\t\tsuper.modulo = MODULO;" +
            "\n\t}";
        
        
        codigoGestor.append(atributos);
        codigoGestor.append(constructor);
        codigoGestor.append("\n}");
        
        super.codigoGestor.add(codigoGestor.toString());
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Vista">
    @Override
    public void crearVista(){}
    // </editor-fold>
    
    // </editor-fold>
}
