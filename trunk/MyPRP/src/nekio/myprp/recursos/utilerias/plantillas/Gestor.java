package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Mapeador;

public abstract class Gestor {
    protected ObjetoNegocio objetoNegocio;
    protected ArrayList parametros;
    
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
    
    public ArrayList<Object> getParametros() {
        return parametros;
    }
    
    public void setParametros(Object ... parametros) {
        ArrayList<Object> listaParametros = new ArrayList<Object>();
        
        for(Object parametro:parametros)
            listaParametros.add(parametro);
        
        this.parametros = listaParametros;
    }

    public ObjetoNegocio getObjetoNegocio() {
        return objetoNegocio;
    }
}
