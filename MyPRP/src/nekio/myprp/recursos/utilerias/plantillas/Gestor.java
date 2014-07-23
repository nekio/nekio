package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.plantillas.swing.SwingMaestro;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Mapeador;

public abstract class Gestor {
    protected ObjetoNegocio objetoNegocio;
    protected DTO dto;
    protected DAO dao;
    protected int modulo;
    protected String pagina;
    
    private SwingMaestro gui;
    private List<DTO> listaDTO;
    
    public void ejecutarControladorNegocio(String accion, String entidad){
        if(Globales.BD_ESQUEMA == null)
            ConsolaDebug.agregarTexto("... Falta asociar un esquema de Base de Datos al Gestor" + entidad, ConsolaDebug.ERROR);
        else{
            String negocio = accion + entidad;

            if(Globales.APP_DEBUG)
                ConsolaDebug.agregarTexto(Globales.APP_SEPARADOR +  
                        "\n|     Ejecutando negocio: " + negocio + "\n" +
                        Globales.APP_SEPARADOR,
                        ConsolaDebug.MAPEO);

            String resultado = null;

            /* ACCIONES DE ENTIDAD */
            if(negocio.equals(Globales.BD.LEER.getValor() + entidad)){
                resultado = obtenerResultado(Globales.BD.LEER.getLlave());
                pagina = Globales.BD.LEER.getPagina() + entidad;
            }else if(negocio.equals(Globales.BD.LEER_DESC.getValor() + entidad)){
                resultado = obtenerResultado(Globales.BD.LEER_DESC.getLlave());
                pagina = Globales.BD.LEER_DESC.getPagina() + entidad;
            }else if(negocio.equals(Globales.BD.LEER_UNO.getValor() + entidad)){
                resultado = obtenerResultado(Globales.BD.LEER_UNO.getLlave());
                pagina = Globales.BD.LEER_UNO.getPagina() + entidad;
            }else if(negocio.equals(Globales.BD.BUSCAR.getValor() + entidad)){
                resultado = obtenerResultado(Globales.BD.BUSCAR.getLlave());
                pagina = Globales.BD.BUSCAR.getPagina() + entidad;
            }else if(negocio.equals(Globales.BD.INSERTAR.getValor() + entidad)){
                resultado = obtenerResultado(Globales.BD.INSERTAR.getLlave());
                pagina = Globales.BD.INSERTAR.getPagina() + entidad;
            }else if(negocio.equals(Globales.BD.MODIFICAR.getValor() + entidad)){
                resultado = obtenerResultado(Globales.BD.MODIFICAR.getLlave());
                pagina = Globales.BD.MODIFICAR.getPagina() + entidad;
            }else if(negocio.equals(Globales.BD.ELIMINAR.getValor() + entidad)){
                resultado = obtenerResultado(Globales.BD.ELIMINAR.getLlave());
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
                ConsolaDebug.agregarTexto(
                    "Resultado de [" + negocio +"]: [" + resultado + "]"+
                    "\nDirige a: [" + pagina + "]",
                    ConsolaDebug.MAPEO);

            dirigir(resultado);
        }
    }
        
    public abstract String obtenerResultado(int metodo);
    
    public void dirigir(String resultado){
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("Pagina: "+ getPagina() + "[" + resultado + "]", ConsolaDebug.MAPEO);
        
        /* ASIGNACION DE FLUJOS */
        if(resultado.equals(Globales.RES_OK)){
            Mapeador.abrir(this);
        }else if(resultado.equals(Globales.RES_ENTRADA)){
        }else if(resultado.equals(Globales.RES_REDIRIGE)){
        }else if(resultado.equals(Globales.RES_ERROR)){
        }
    }
    
    public void recargarGUI(){
        gui.recargar(dao.leerDesc(null));
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("Finalizada la recarga de la vista\n"+gui.getClass().getName(), ConsolaDebug.VISTA);
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
    
    public DAO getDao() {
        return dao;
    }
    
    public int getModulo() {
        return modulo;
    }

    public String getPagina() {
        return pagina;
    }
    
    public SwingMaestro getGui() {
        return gui;
    }

    public void setGui(SwingMaestro gui) {
        this.gui = gui;
    }
    
    public void setListaDTO(List<DTO> listaDTO) {
        this.listaDTO = listaDTO;
    }
    
    public List<DTO> getListaDTO() {
        return listaDTO;
    }

    public void setEsquemaBD(String esquemaBD) {
        Globales.BD_ESQUEMA = esquemaBD;
    }
}
