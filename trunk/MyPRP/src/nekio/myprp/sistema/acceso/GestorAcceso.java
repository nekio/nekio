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
    
    @Override
    public void ejecutarControladorNegocio(String negocio){
        if(Globales.APP_DEBUG)
            System.out.println("\nEjecutando negocio: " + negocio);
        
        String resultado = null;
        String pagina = null;
        int modulo = 0;
        
        /* ACCIONES BASICAS */
        switch (negocio) {
            case Globales.ACC_LOGIN:
                resultado = Globales.RES_OK;
            break;
            case Globales.ACC_SALIR:
                resultado = Globales.RES_OK;
            break;
        }
        
        /* ACCIONES DE USUARIO */
        modulo = Globales.MOD_ACCESO;
        
        if(negocio.equals("agregarUsuario")){
            resultado = obtenerResultado(OBJ_USUARIO, Globales.BD.AGREGAR.getLlave());
        }else if(negocio.equals("buscarUsuario")){
            resultado = obtenerResultado(OBJ_USUARIO, Globales.BD.BUSCAR.getLlave());
        }else if(negocio.equals("eliminarUsuario")){
            resultado = obtenerResultado(OBJ_USUARIO, Globales.BD.ELIMINAR.getLlave());
        }else if(negocio.equals("leerUsuario")){
            resultado = obtenerResultado(OBJ_USUARIO, Globales.BD.LEER.getLlave());
            pagina = "bienvenida";
        }else if(negocio.equals("modificarUsuario")){
            resultado = obtenerResultado(OBJ_USUARIO, Globales.BD.MODIFICAR.getLlave());
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
        
        if(objetoNegocio instanceof Usuario)
            this.objetoNegocio = (Usuario) objetoNegocio;
        
        if(metodo == Globales.BD.AGREGAR.getLlave() || metodo == Globales.BD.ELIMINAR.getLlave() || metodo == Globales.BD.MODIFICAR.getLlave())
            resultado = this.objetoNegocio.ejecutar(metodo, this);
        else if(metodo == Globales.BD.LEER.getLlave())
            resultado = this.objetoNegocio.consultarSeleccion(this);
        else if(metodo == Globales.BD.BUSCAR.getLlave())
            resultado = this.objetoNegocio.consultarBusqueda(this);
                
        return resultado;
    }
}
