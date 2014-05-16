package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.ObjetoNegocio;

public class GestorImagen extends Gestor{
private final Imagen OBJ_IMAGEN = new Imagen();
    
    @Override
    public void ejecutarControladorNegocio(String negocio){
        if(Globales.APP_DEBUG)
            System.out.println("\nEjecutando negocio: " + negocio);
        
        String resultado = null;
        String pagina = null;
        int modulo = Globales.MOD_IMAGEN;
        
        /* ACCIONES DE IMAGEN */
        if(negocio.equals("agregarImagen")){
            resultado = obtenerResultado(OBJ_IMAGEN, Globales.BD.AGREGAR.getLlave());
        }else if(negocio.equals("buscarImagen")){
            resultado = obtenerResultado(OBJ_IMAGEN, Globales.BD.BUSCAR.getLlave());
        }else if(negocio.equals("eliminarImagen")){
            resultado = obtenerResultado(OBJ_IMAGEN, Globales.BD.ELIMINAR.getLlave());
        }else if(negocio.equals("leerImagen")){
            resultado = obtenerResultado(OBJ_IMAGEN, Globales.BD.LEER.getLlave());
            pagina = "mostrarImagen";
        }else if(negocio.equals("modificarImagen")){
            resultado = obtenerResultado(OBJ_IMAGEN, Globales.BD.MODIFICAR.getLlave());
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

