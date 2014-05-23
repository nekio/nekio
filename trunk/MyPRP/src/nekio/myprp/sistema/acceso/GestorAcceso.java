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
    private final int MODULO = Globales.MOD_ACCESO;
    
    public GestorAcceso(){
        super.modulo = MODULO;
    }
    
    public void ejecutarControladorNegocio(String accion, String entidad){
        String negocio = accion + entidad;
        
        if(Globales.APP_DEBUG)
            System.out.println("\nEjecutando negocio de Acceso: " + negocio);
        
        String resultado = null;
        
        /* ACCIONES ESPECIALES DE LOGIN */
        if(negocio.equals(Globales.ACC_LOGIN)){
            resultado = Globales.RES_OK;
            super.pagina = "login";
        }else if(negocio.equals(Globales.RES_OK)){
            resultado = Globales.RES_OK;
            super.pagina = "blank";
        }else{
            super.ejecutarControladorNegocio(OBJ_USUARIO, accion, entidad);
            return; //Si accede al metodo de la clase padre, no debe continuar el flujo de este metodo hijo
        }
        
        if(Globales.APP_DEBUG)
            System.out.println("\nResultado de [" + negocio +"]: [" + resultado + "] Dirige a: [" + super.pagina + "]");
        
        dirigir(resultado);
    }
    
    @Override
    public String obtenerResultado(ObjetoNegocio objetoNegocio,int metodo){
        String resultado = null;
        
        if(objetoNegocio instanceof Usuario)
            super.objetoNegocio = (Usuario) objetoNegocio;
        
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
