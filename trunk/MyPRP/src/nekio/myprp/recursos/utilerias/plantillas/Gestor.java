package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Mapeador;

public abstract class Gestor {
    public ObjetoNegocio objetoNegocio;
    private ArrayList<String> parametros;
    
    public abstract void ejecutarControladorNegocio(String negocio);
    public abstract String obtenerResultado(ObjetoNegocio objetoNegocio,int metodo);
    
    public void dirigir(int modulo, String pagina, String resultado){
        /* IMPRESION PARA MONITOREO EN CONSOLA */
        if(Globales.APP_DEBUG)
            System.out.println("Pagina: "+ pagina + "[" + resultado + "]");
        
        /* ASIGNACION DE FLUJOS */
        if(resultado.equals(Globales.RES_OK)){
            Mapeador.abrir(modulo, pagina);
        }else if(resultado.equals(Globales.RES_ENTRADA)){
        }else if(resultado.equals(Globales.RES_REDIRIGE)){
        }else if(resultado.equals(Globales.RES_ERROR)){
        }
    }
    
    public ArrayList<String> getParametros() {
        return parametros;
    }
    
    public void setParametros(Object ... parametros) {
        ArrayList<String> listaParametros = new ArrayList<String>();
        
        for(Object parametro:parametros)
            listaParametros.add(String.valueOf(parametro));
        
        this.parametros = listaParametros;
    }
}
