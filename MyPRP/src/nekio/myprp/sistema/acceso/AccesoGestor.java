package nekio.myprp.sistema.acceso;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.negocio.Usuario;

public class AccesoGestor extends Gestor{
    private final Usuario OBJETO_NEGOCIO = new Usuario();
    private final UsuarioDAO DAO = new UsuarioDAO();
    private final int MODULO = Globales.MOD_ACCESO;
    
    public AccesoGestor(){
        super.objetoNegocio = OBJETO_NEGOCIO;
        super.dao = DAO;
        super.modulo = MODULO;
    }
    
    @Override
    public void ejecutarControladorNegocio(String accion, String entidad){
        String negocio = accion + entidad;
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("Ejecutando negocio de Acceso: " + negocio, ConsolaDebug.MAPEO);
        
        String resultado = null;
        
        /* ACCIONES ESPECIALES DE LOGIN */
        if(negocio.equals(Globales.ACC_LOGIN)){
            resultado = Globales.RES_OK;
            super.pagina = "login";
        }else if(negocio.equals(Globales.RES_OK)){
            resultado = Globales.RES_OK;
            super.pagina = "blank";
        }else{
            super.ejecutarControladorNegocio(accion, entidad);
            return; //Si accede al metodo de la clase padre, no debe continuar el flujo de este metodo hijo
        }
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("Resultado de [" + negocio +"]: [" + resultado + "] Dirige a: [" + super.pagina + "]", ConsolaDebug.MAPEO);
        
        dirigir(resultado);
    }
}
