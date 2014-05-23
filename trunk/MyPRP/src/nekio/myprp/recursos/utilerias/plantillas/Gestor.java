package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Mapeador;

public abstract class Gestor {
    protected ObjetoNegocio objetoNegocio;
    protected DTO dto;
    protected int modulo;
    protected String pagina;
    
    private List<DTO> listaDTO;
    
    public void ejecutarControladorNegocio(ObjetoNegocio objetoNegocio, String accion, String entidad){
        this.objetoNegocio = objetoNegocio;
        String negocio = accion + entidad;
        
        if(Globales.APP_DEBUG)
            System.out.println("\n"+
                    Globales.OBJ_NEGOCIO_SEPARADOR +  
                    "\n|     Ejecutando negocio: " + negocio +
                    Globales.OBJ_NEGOCIO_SEPARADOR);
        
        String resultado = null;
        
        /* ACCIONES DE ENTIDAD */
        if(negocio.equals(Globales.BD.LEER.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.LEER.getLlave());
            pagina = Globales.BD.LEER.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.LEER_UNO.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.LEER_UNO.getLlave());
            pagina = Globales.BD.LEER_UNO.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.BUSCAR.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.BUSCAR.getLlave());
            pagina = Globales.BD.BUSCAR.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.INSERTAR.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.INSERTAR.getLlave());
            pagina = Globales.BD.INSERTAR.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.MODIFICAR.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.MODIFICAR.getLlave());
            pagina = Globales.BD.MODIFICAR.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.ELIMINAR.getValor() + entidad)){
            resultado = obtenerResultado(objetoNegocio, Globales.BD.ELIMINAR.getLlave());
            pagina = Globales.BD.ELIMINAR.getPagina() + entidad;
        }
        
        else if(negocio.equals(Globales.BD.NUEVO.getValor() + entidad)){
            resultado = Globales.RES_OK;
            pagina = Globales.BD.NUEVO.getPagina() + entidad;
        }else if(negocio.equals(Globales.BD.CANCELAR.getValor() + entidad)){
            resultado = Globales.RES_OK;
            pagina = Globales.BD.CANCELAR.getPagina() + entidad;
        }
        
        /* ERROR DE ACCION*/
        else{
            resultado = Globales.RES_ERROR;
            pagina = null;
        }
        
        if(Globales.APP_DEBUG)
            System.out.println(
                    "\nResultado de [" + negocio +"]: [" + resultado + "]"+
                    "\nDirige a: [" + pagina + "]");
        
        dirigir(resultado);
    }
        
    public abstract String obtenerResultado(ObjetoNegocio objetoNegocio,int metodo);
    
    public void dirigir(String resultado){
        if(Globales.APP_DEBUG)
            System.out.println("Pagina: "+ getPagina() + "[" + resultado + "]");
        
        /* ASIGNACION DE FLUJOS */
        if(resultado.equals(Globales.RES_OK)){
            Mapeador.abrir(this);
        }else if(resultado.equals(Globales.RES_ENTRADA)){
        }else if(resultado.equals(Globales.RES_REDIRIGE)){
        }else if(resultado.equals(Globales.RES_ERROR)){
        }
    }
    
    public ObjetoNegocio getObjetoNegocio() {
        return objetoNegocio;
    }
    
    public DTO getDTO() {
        return dto;
    }

    public void setDTO(DTO dto) {
        this.dto = dto;
    }
    
    public int getModulo() {
        return modulo;
    }

    public String getPagina() {
        return pagina;
    }
    
    public void setListaDTO(List<DTO> listaDTO) {
        this.listaDTO = listaDTO;
    }
    
    public List<DTO> getListaDTO() {
        return listaDTO;
    }
}
