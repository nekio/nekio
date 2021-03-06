package nekio.myprp.recursos.img.obj.vista;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.herramientas.Mensaje;
import nekio.myprp.recursos.img.obj.ImagenGestor;
import nekio.myprp.recursos.img.obj.ImagenDTO;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.gui.swing.BD_Manipulador;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingMaestro;

public class ImagenBD_M extends BD_Manipulador{
    private static final long serialVersionUID = 1L;
    private final String ENTIDAD = Globales.Entidad.Imagen.name();
    
    private ImagenGestor gestor;
    private CatalogoImagenes gui;
    
    public ImagenBD_M(SwingMaestro gui){
        this.gui = (CatalogoImagenes)gui;
        
        super.getBtnGuardar().setVisible(false);
        super.getBtnCancelar().setVisible(false);
    }
    
    @Override
    public void insertarRegistro() {
        gestor = new ImagenGestor();
        gestor.setEsquemaBD(Globales.BD_TOOLS);
        gestor.setGui(gui);
        gestor.ejecutarControladorNegocio(Globales.BD.NUEVO.getValor(), ENTIDAD);
        gestor = null;
    }

    @Override
    public void editarRegistro(){
        ImagenDTO parametros = gui.getParametros();
        
        if(parametros != null){
            gestor = new ImagenGestor();
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
        ImagenDTO parametros = gui.getParametros();
        
        int confirmar = Mensaje.decidir(
                Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "confirmarBorrado") + " [ID : " + parametros.getIdImagen() + "]",
                Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "denegar"),
                Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "confirmar"));
        
        if(confirmar == 1){
            if(parametros != null){
                gestor = new ImagenGestor();
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

    @Override public void guardarEdicion(){}
    @Override public void cancelarEdicion(){}
}
