package listadevalores;

/**
 * Clase para obtener una Lista de Valores
 *
 * @author Nekio
 * @version 27/04/2014
 *
 */

import java.util.ArrayList;
import listadevalores.dto.Elementos;
import listadevalores.dao.Listado;
import listadevalores.dto.LlaveValor;

public class ListaDeValores{
    public static final int LLAVE = 0;
    public static final int VALOR = 1;
    public static final int EXTRAS = 2;
    
    private ArrayList<String> nombresColumnas;
    private ArrayList<LlaveValor> resultado;
    private Elementos elementos;
    
    public ListaDeValores(){}
    
    public ListaDeValores(Elementos elementos){
        this.elementos = elementos;
        
        obtenValores();
    }
    
    private void obtenValores(){
        Listado listado = new Listado();
        try{
            resultado = listado.getLista(elementos);
            nombresColumnas = listado.getNombresColumnas();
        }catch(Exception e){
            resultado = new ArrayList<LlaveValor>();
        }
    }

    public ArrayList<String> getNombresColumnas() {
        return nombresColumnas;
    }
    
    public ArrayList<LlaveValor> getResultado() {
        return resultado;
    }
}
