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

public class GestorAcceso extends Gestor{
    private final Usuario OBJETO_NEGOCIO = new Usuario();
    private final UsuarioDAO DAO = new UsuarioDAO();
    private final int MODULO = Globales.MOD_ACCESO;
    
    public GestorAcceso(){
        super.objetoNegocio = OBJETO_NEGOCIO;
        super.dao = DAO;
        super.modulo = MODULO;
    }
    
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
