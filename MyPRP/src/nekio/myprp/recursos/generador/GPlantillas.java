package nekio.myprp.recursos.generador;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import static nekio.myprp.recursos.generador.ControladorGenerador.convertirCamel;
import static nekio.myprp.recursos.generador.ControladorGenerador.convertirPascal;
import nekio.myprp.recursos.utilerias.Globales;
// </editor-fold>

public class GPlantillas extends Generador{
     // <editor-fold defaultstate="collapsed" desc="Atributos">
    private final String RUTA_PLANTILLAS = "/nekio/myprp/recursos/generador/plantillas/";
    // </editor-fold>
    
     // <editor-fold defaultstate="collapsed" desc="Constructores">
    public GPlantillas(){
        this(true);
    }
    
    public GPlantillas(boolean estandar){
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
    public void crearDTO(String tabla, List<String> tablasForaneas, List<String> atributos, List<Globales.TipoDato> tipos){
        StringBuilder codigoDTO = new StringBuilder();
        
        StringBuilder contenido = new StringBuilder();
        String linea=null; 
            
        try{
            BufferedReader buffer = obtenerArchivo("DTO");
            String tipoDato = null;
            String atributo = null;
            
            int paso = -1;
            String declaracionAtributo = "";
            String aux = ""; //retiene la linea original con comodines
            while((linea=buffer.readLine()) != null){
                
                if(linea.contains("~0"))
                    paso = 0;
                else if(linea.contains("~1"))
                    paso = 1;
                else if(linea.contains("~2"))
                    paso = 2;
                else if(linea.contains("~3"))
                    paso = 3;
                else if(linea.contains("$"))
                    break;
                
                if(paso < 0){
                    contenido.append(linea.replaceAll("#0", convertirPascal(tabla)) + "\n");
                }else if(paso == 0){
                    if(!linea.contains("~0")){
                        for(int i=0; i<atributos.size(); i++){
                            aux = linea;

                            if(super.primitivos)
                                tipoDato = tipos.get(i).getTipoPrimitivoJava();
                            else
                                tipoDato = tipos.get(i).getTipoClaseJava();
                            
                            atributo = convertirCamel(atributos.get(i));
                            declaracionAtributo = tipoDato + " " + atributo;

                            aux = aux.replaceAll("#2", convertirPascal(declaracionAtributo));
                            contenido.append(aux + "\n");
                        }
                    }
                }else if(paso == 1){
                    if(!linea.contains("~1")){
                        contenido.append(linea + "\n");
                    }else
                        contenido.append("\n");
                }else if(paso == 2){
                    if(!linea.contains("~2")){
                        for(int i=0; i<atributos.size(); i++){
                            aux = linea;
                            
                            if(!aux.contains("}")){
                                if(super.primitivos)
                                    tipoDato = tipos.get(i).getTipoPrimitivoJava();
                                else
                                    tipoDato = tipos.get(i).getTipoClaseJava();
                                
                                //String bloque = formatearConfirmarDTO(tipos.get(i), atributos.get(i));
                                aux = aux.replaceAll("#1", atributos.get(i));
                                aux = aux.replaceAll("#2", convertirCamel(atributos.get(i)));
                                aux = aux.replaceAll("#5", tipoDato);
                                contenido.append(aux + "\n");
                            }else{
                                contenido.append(aux + "\n");
                                break;
                            }
                        }
                    }else
                        contenido.append("\n");
                }else if(paso == 3){
                    if(!linea.contains("~2")){
                        for(int i=0; i<atributos.size(); i++){
                            aux = linea;
                            
                            if(!aux.contains("}")){
                                if(super.primitivos)
                                    tipoDato = tipos.get(i).getTipoPrimitivoJava();
                                else
                                    tipoDato = tipos.get(i).getTipoClaseJava();
                                
                                //String bloque = formatearConfirmarDTO(tipos.get(i), atributos.get(i));
                                aux = aux.replaceAll("#1", atributos.get(i));
                                aux = aux.replaceAll("#2", convertirCamel(atributos.get(i)));
                                aux = aux.replaceAll("#5", tipoDato);
                                contenido.append(aux + "\n");
                            }else{
                                contenido.append(aux + "\n");
                                break;
                            }
                        }
                    }else
                        contenido.append("\n");
                }
            }
            buffer.close();
            buffer = null;
            
            System.out.println(contenido+"\n\n");
        }
        catch(Exception e){
           
        }
    }
    
    private BufferedReader obtenerArchivo(String plantilla){
        BufferedReader recurso = null;
        try{
            File archivo = new File(getClass().getResource(RUTA_PLANTILLAS + plantilla).getFile());
            FileReader leerArchivo = new FileReader(archivo);
            recurso = new BufferedReader(leerArchivo);
        }catch(Exception e){}
        
        return recurso;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="DAO">
    @Override
    protected void crearDAO(String tabla, List<List> llaves, List<String> campos, List<Globales.TipoDato> tipos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Objeto de Negocio">
    @Override
    protected void crearObjetoNegocio(String tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Gestor">
    @Override
    protected void crearGestor(String tabla, String catalogo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Vista">
    @Override
    public void crearVista(){}
    // </editor-fold>
    
    // </editor-fold>
}
