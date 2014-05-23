package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.ObjetoNegocio;

public class GestorImagen extends Gestor{
    private final Imagen OBJETO_NEGOCIO = new Imagen();
    private final int MODULO = Globales.MOD_IMAGEN;
    
    public GestorImagen(){
        super.modulo = MODULO;
    }

    public void ejecutarControladorNegocio(String accion, String entidad){
        super.ejecutarControladorNegocio(OBJETO_NEGOCIO, accion, entidad);
    }
    
    @Override
    public String obtenerResultado(ObjetoNegocio objetoNegocio,int metodo){
        String resultado = null;
        
        if(objetoNegocio instanceof Imagen)
            super.objetoNegocio = (Imagen) objetoNegocio;
        
        if(metodo == Globales.BD.LEER.getLlave())
            resultado = super.objetoNegocio.consultarSeleccion(this);
        else if(metodo == Globales.BD.LEER_UNO.getLlave())
            resultado = super.objetoNegocio.consultarId(this);
        else if(metodo == Globales.BD.BUSCAR.getLlave())
            resultado = super.objetoNegocio.consultarBusqueda(this);
        else if(metodo == Globales.BD.INSERTAR.getLlave() || metodo == Globales.BD.MODIFICAR.getLlave() || metodo == Globales.BD.ELIMINAR.getLlave())
            resultado = super.objetoNegocio.ejecutar(metodo, this, super.dto);
                
        return resultado;
    }
}

