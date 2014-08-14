package nekio.myprp.sistema.acceso;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.DetalleUsuario;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.sistema.acceso.dao.PrivilegioDAO;
import nekio.myprp.sistema.acceso.dao.RangoDAO;
import nekio.myprp.sistema.acceso.dao.TipoUsuarioDAO;
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.dao.UsuarioSistemaDAO;
import nekio.myprp.sistema.acceso.dto.PrivilegioDTO;
import nekio.myprp.sistema.acceso.dto.RangoDTO;
import nekio.myprp.sistema.acceso.dto.TipoUsuarioDTO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;
import nekio.myprp.sistema.acceso.dto.UsuarioSistemaDTO;

public class AccesoGestor extends Gestor{
    private final UsuarioDAO DAO = new UsuarioDAO();
    private final int MODULO = Globales.MOD_ACCESO;
    
    public AccesoGestor(){
        super.objetoNegocio = null;
        super.dao = DAO;
        super.modulo = MODULO;
    }
    
    @Override
    public void ejecutarControladorNegocio(String accion, String entidad){
        definirUsuario();
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
    
    private void definirUsuario(){
        UsuarioDTO usuario = (UsuarioDTO) super.dto;
        TipoUsuarioDTO tipoUsuario = (TipoUsuarioDTO) new TipoUsuarioDAO().leerUno("id_tipo_usuario = " + usuario.getIdTipoUsuario());
        PrivilegioDTO privilegio = (PrivilegioDTO) new PrivilegioDAO().leerUno("id_tipo_usuario = " + usuario.getIdTipoUsuario());
        RangoDTO rango = (RangoDTO) new RangoDAO().leerUno("id_rango = " + usuario.getIdRango());
        
        List<UsuarioSistemaDTO> usuarioSistema = (List) new UsuarioSistemaDAO().leer("id_usuario = " + usuario.getIdUsuario());
        List<Integer> sistemas = new ArrayList<Integer>();
        for(UsuarioSistemaDTO us: usuarioSistema)
            sistemas.add(us.getIdSistema());
        
        DetalleUsuario appUsuario = new DetalleUsuario();
        appUsuario.setId(usuario.getIdUsuario());
        appUsuario.setNick(usuario.getNick());
        appUsuario.setRangoDesc(rango.getDescripcion());
        appUsuario.setRangoId(rango.getIdRango());
        appUsuario.setAccPublico(rango.isAccPublico());
        appUsuario.setAccGrupal(rango.isAccGrupal());
        appUsuario.setAccProtegido(rango.isAccProtegido());
        appUsuario.setAccPrivado(rango.isAccPrivado());
        appUsuario.setTipoDesc(tipoUsuario.getDescripcion());
        appUsuario.setTipoId(tipoUsuario.getIdTipoUsuario());
        appUsuario.setConfigurar(privilegio.isConfigurar());
        appUsuario.setBuscar(privilegio.isBuscar());
        appUsuario.setInsertar(privilegio.isInsertar());
        appUsuario.setModificar(privilegio.isModificar());
        appUsuario.setEliminar(privilegio.isEliminar());
        appUsuario.setSistemas(sistemas);
        
        Globales.APP_USUARIO = appUsuario;
        
        usuario = null;
        tipoUsuario = null;
        privilegio = null;
        rango = null;
        appUsuario = null;
        usuarioSistema = null;
        sistemas = null;
        appUsuario = null;
    }
}
