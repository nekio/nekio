package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;

public class GestorImagen extends Gestor{
    private final Imagen OBJETO_NEGOCIO = new Imagen();
    private final ImagenDAO DAO = new ImagenDAO();
    private final int MODULO = Globales.MOD_IMAGEN;
    
    public GestorImagen(){
        super.objetoNegocio = OBJETO_NEGOCIO;
        super.dao = DAO;
        super.modulo = MODULO;
    }

    @Override
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

