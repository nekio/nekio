package nekio.myprp.sistema.modulos.series;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.sistema.modulos.series.dao.MensajePrivadoDAO;
import nekio.myprp.sistema.modulos.series.negocio.MensajePrivado;

public class GestorMensajePrivado extends Gestor{
    private final MensajePrivado OBJETO_NEGOCIO = new MensajePrivado();
    private final MensajePrivadoDAO DAO = new MensajePrivadoDAO();
    private final int MODULO = Globales.MOD_SERIES;
    
    public GestorMensajePrivado(){
        super.objetoNegocio = OBJETO_NEGOCIO;
        super.dao = DAO;
        super.modulo = MODULO;
    }

    public void ejecutarControladorNegocio(String accion, String entidad){
        super.ejecutarControladorNegocio(accion, entidad);
    }
    
    @Override
    public String obtenerResultado(int metodo){
        String resultado = null;
        
        if(metodo == Globales.BD.LEER.getLlave())
            resultado = super.objetoNegocio.consultarSeleccion(this);
        else if(metodo == Globales.BD.LEER_DESC.getLlave())
            resultado = super.objetoNegocio.consultarSeleccionDesc(this);
        else if(metodo == Globales.BD.LEER_UNO.getLlave())
            resultado = super.objetoNegocio.consultarId(this);
        else if(metodo == Globales.BD.BUSCAR.getLlave())
            resultado = super.objetoNegocio.consultarBusqueda(this);
        else if(metodo == Globales.BD.INSERTAR.getLlave() || metodo == Globales.BD.MODIFICAR.getLlave() || metodo == Globales.BD.ELIMINAR.getLlave())
            resultado = super.objetoNegocio.ejecutar(metodo, this, super.dto);
                
        return resultado;
    }
}
