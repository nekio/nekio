package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Mapeador;

public abstract class Gestor {
    protected ObjetoNegocio objetoNegocio;
    protected ArrayList parametros;
    private List<DTO> listaDTO;
    
    public void ejecutarControladorNegocio(ObjetoNegocio objetoNegocio, String accion, String entidad){
        this.objetoNegocio = objetoNegocio;
        String negocio = accion + entidad;
        
        if(Globales.APP_DEBUG)
            System.out.println("\nEjecutando negocio: " + negocio);
        
        String resultado = null;
        String pagina = null;
        int modulo = Globales.MOD_IMAGEN;
        
        /* ACCIONES DE ENTIDAD */
        if(negocio.equals(Globales.BD.AGREGAR.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.AGREGAR.getLlave());
            pagina = Globales.BD.AGREGAR.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.BUSCAR.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.BUSCAR.getLlave());
            pagina = Globales.BD.BUSCAR.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.ELIMINAR.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.ELIMINAR.getLlave());
            pagina = Globales.BD.ELIMINAR.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.LEER.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.LEER.getLlave());
            pagina = Globales.BD.LEER.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.MODIFICAR.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.MODIFICAR.getLlave());
            pagina = Globales.BD.MODIFICAR.getPagina() + entidad;
        }
        
        /* ERROR DE ACCION*/
        else{
            resultado = Globales.RES_ERROR;
            pagina = null;
        }
        
        if(Globales.APP_DEBUG)
            System.out.println("\nResultado de [" + negocio +"]: [" + resultado + "] Dirige a: [" + pagina + "]");
        
        dirigir(modulo, pagina, resultado);
    }
        
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
    
    public void setParametros(ArrayList parametros) {        
        this.parametros = parametros;
    }
    
    public void setParametros(Object ... parametros) {
        ArrayList listaParametros = new ArrayList();
        
        for(Object parametro:parametros)
            listaParametros.add(parametro);
        
        this.parametros = listaParametros;
    }

    public ObjetoNegocio getObjetoNegocio() {
        return objetoNegocio;
    }
    
    public void setListaDTO(List<DTO> listaDTO) {
        this.listaDTO = listaDTO;
    }
    
    public List<DTO> getListaDTO() {
        return listaDTO;
    }
}
