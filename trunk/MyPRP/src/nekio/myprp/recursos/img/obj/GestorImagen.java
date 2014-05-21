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

    public void ejecutarControladorNegocio(String accion, String entidad){
        super.ejecutarControladorNegocio(OBJETO_NEGOCIO, accion, entidad);
    }
    
    @Override
    public String obtenerResultado(ObjetoNegocio objetoNegocio,int metodo){
        String resultado = null;
        
        if(objetoNegocio instanceof Imagen)
            this.objetoNegocio = (Imagen) objetoNegocio;
        
        if(metodo == Globales.BD.AGREGAR.getLlave() || metodo == Globales.BD.ELIMINAR.getLlave() || metodo == Globales.BD.MODIFICAR.getLlave())
            resultado = this.objetoNegocio.ejecutar(metodo, this, parametros);
        else if(metodo == Globales.BD.LEER.getLlave())
            resultado = this.objetoNegocio.consultarSeleccion(this);
        else if(metodo == Globales.BD.BUSCAR.getLlave())
            resultado = this.objetoNegocio.consultarBusqueda(this);
                
        return resultado;
    }
}

