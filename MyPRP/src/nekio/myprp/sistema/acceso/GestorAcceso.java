package nekio.myprp.sistema.acceso;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.ObjetoNegocio;
import nekio.myprp.sistema.acceso.negocio.Usuario;

public class GestorAcceso extends Gestor{
    private final Usuario OBJ_USUARIO = new Usuario();
    
    public void ejecutarControladorNegocio(String accion, String entidad){
        String negocio = accion + entidad;
        
        if(Globales.APP_DEBUG)
            System.out.println("\nEjecutando negocio de Acceso: " + negocio);
        
        String resultado = null;
        String pagina = null;
        int modulo = 0;
        
        /* ACCIONES ESPECIALES DE LOGIN */
        modulo = Globales.MOD_ACCESO;
        if(negocio.equals(Globales.ACC_LOGIN)){
            resultado = Globales.RES_OK;
            pagina = "login";
        }else if(negocio.equals(Globales.RES_OK)){
            resultado = Globales.RES_OK;
            pagina = "blank";
        }else{
            super.ejecutarControladorNegocio(OBJ_USUARIO, accion, entidad);
            return; //Si accede al metodo de la clase padre, no debe continuar el flujo de este metodo hijo
        }
        
        if(Globales.APP_DEBUG)
            System.out.println("\nResultado de [" + negocio +"]: [" + resultado + "] Dirige a: [" + pagina + "]");
        
        dirigir(modulo, pagina, resultado);
    }
    
    @Override
    public String obtenerResultado(ObjetoNegocio objetoNegocio,int metodo){
        String resultado = null;
        
        if(objetoNegocio instanceof Usuario)
            this.objetoNegocio = (Usuario) objetoNegocio;
        
        if(metodo == Globales.BD.AGREGAR.getLlave() || metodo == Globales.BD.ELIMINAR.getLlave() || metodo == Globales.BD.MODIFICAR.getLlave())
            resultado = this.objetoNegocio.ejecutar(metodo, this, parametros);
        else if(metodo == Globales.BD.LEER.getLlave())
            resultado = this.objetoNegocio.consultarSeleccion(this);
        else if(metodo == Globales.BD.BUSCAR.getLlave())
            resultado = this.objetoNegocio.consultarBusqueda(this);
                
        return resultado;
    }
}
