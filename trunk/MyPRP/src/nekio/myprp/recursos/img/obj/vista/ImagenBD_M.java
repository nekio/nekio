package nekio.myprp.recursos.img.obj.vista;

/**
 *
 * @author Nekio
 */

import javax.swing.JFrame;
import nekio.myprp.recursos.img.obj.GestorImagen;
import nekio.myprp.recursos.img.obj.ImagenDTO;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.gui.swing.BD_Manipulador;
import nekio.myprp.recursos.utilerias.gui.swing.CatalogoImagenes;

public class ImagenBD_M extends BD_Manipulador{
    private static final long serialVersionUID = 1L;
    private final String ENTIDAD = Globales.Entidad.Imagen.name();
    
    private GestorImagen gestor;
    private CatalogoImagenes gui;
    
    public ImagenBD_M(JFrame gui){
        this.gui = (CatalogoImagenes)gui;
    }
    
    @Override
    public void insertarRegistro() {
        gestor = new GestorImagen();
        gestor.ejecutarControladorNegocio(Globales.BD.NUEVO.getValor(), ENTIDAD);
        gestor = null;
    }

    @Override
    public void editarRegistro(){
        ImagenDTO parametros = gui.getParametros();
        
        if(parametros != null){
            gestor = new GestorImagen();
            gestor.setDTO(parametros);
            gestor.ejecutarControladorNegocio(Globales.BD.LEER_UNO.getValor(), ENTIDAD);
            gestor = null;
        }else{
            if(Globales.APP_DEBUG)
                System.out.println("\nNo se pudieron leer los parametros para editar " + ENTIDAD);
        }
    }

    @Override
    public void borrarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void guardarEdicion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelarEdicion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
