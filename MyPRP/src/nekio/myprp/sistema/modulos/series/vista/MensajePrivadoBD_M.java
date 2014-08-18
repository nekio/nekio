
package nekio.myprp.sistema.modulos.series.vista;

import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.herramientas.Mensaje;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.gui.swing.BD_Manipulador;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingMaestro;
import nekio.myprp.sistema.modulos.series.MensajePrivadoGestor;
import nekio.myprp.sistema.modulos.series.dto.MensajePrivadoDTO;

/**
 *
 * @author Nekio
 */
public class MensajePrivadoBD_M extends BD_Manipulador{
    private static final long serialVersionUID = 1L;
    private final String ENTIDAD = Globales.Entidad.MensajePrivado.name();
    
    private MensajePrivadoGestor gestor;
    private MensajePrivadoSwing gui;
    
    public MensajePrivadoBD_M(SwingMaestro gui){
        super.habilitarEdicion(true);
        this.gui = (MensajePrivadoSwing)gui;
    }
    
    @Override
    public void insertarRegistro() {
        gestor = new MensajePrivadoGestor();
        gestor.setEsquemaBD(Globales.BD_ESQUEMA_SERIES);
        gestor.setGui(gui);
        gestor.ejecutarControladorNegocio(Globales.BD.NUEVO.getValor(), ENTIDAD);
        gestor = null;
    }

    @Override
    public void editarRegistro(){
        MensajePrivadoDTO parametros = gui.getParametros();
        
        if(parametros != null){
            gestor = new MensajePrivadoGestor();
            gestor.setEsquemaBD(Globales.BD_ESQUEMA_SERIES);
            gestor.setDTO(parametros);
            gestor.setGui(gui);
            gestor.ejecutarControladorNegocio(Globales.BD.LEER_UNO.getValor(), ENTIDAD);
            gestor = null;
        }else{
            if(Globales.APP_DEBUG)
                ConsolaDebug.agregarTexto("No se pudieron leer los parametros para editar " + ENTIDAD, ConsolaDebug.ERROR);
        }
    }

    @Override
    public void borrarRegistro() {
        MensajePrivadoDTO parametros = gui.getParametros();
        
        int confirmar = Mensaje.decidir(
                Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "confirmarBorrado") + " [ID : " + parametros.getIdMensajePrivado() + "]",
                Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "denegar"),
                Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "confirmar"));
        
        if(confirmar == 1){
            if(parametros != null){
                gestor = new MensajePrivadoGestor();
                gestor.setEsquemaBD(Globales.BD_ESQUEMA_SERIES);
                gestor.setDTO(parametros);
                gestor.setGui(gui);
                gestor.ejecutarControladorNegocio(Globales.BD.ELIMINAR.getValor(), ENTIDAD);
                gestor = null;
            }else{
                if(Globales.APP_DEBUG)
                    ConsolaDebug.agregarTexto("No se pudieron leer los parametros para borrar " + ENTIDAD, ConsolaDebug.ERROR);
            }
        }
    }

    @Override public void guardarEdicion(){
        MensajePrivadoDTO parametros = gui.getParametros();
        
        if(parametros != null){
            gestor = new MensajePrivadoGestor();
            gestor.setEsquemaBD(Globales.BD_ESQUEMA_SERIES);
            gestor.setDTO(parametros);
            gestor.setGui(gui);
            gestor.ejecutarControladorNegocio(Globales.BD.INSERTAR.getValor(), ENTIDAD);
            gestor = null;
        }else{
            if(Globales.APP_DEBUG)
                ConsolaDebug.agregarTexto("No se pudieron leer los parametros para insertar " + ENTIDAD, ConsolaDebug.ERROR);
        }
           
    }
    
    @Override public void cancelarEdicion(){
        MensajePrivadoDTO parametros = gui.getParametros();
        
        if(parametros != null){
            gestor = new MensajePrivadoGestor();
            gestor.setEsquemaBD(Globales.BD_ESQUEMA_SERIES);
            gestor.setDTO(parametros);
            gestor.setGui(gui);
            gestor.ejecutarControladorNegocio(Globales.BD.CANCELAR.getValor(), ENTIDAD);
            gestor = null;
        }else{
            if(Globales.APP_DEBUG)
                ConsolaDebug.agregarTexto("No se pudieron leer los parametros para cancelar la edicion de " + ENTIDAD, ConsolaDebug.ERROR);
        }
    }
}