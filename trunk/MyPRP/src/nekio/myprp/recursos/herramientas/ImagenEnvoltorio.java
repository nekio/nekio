package nekio.myprp.recursos.herramientas;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import nekio.myprp.recursos.utilerias.Globales;
// </editor-fold>

public class ImagenEnvoltorio {
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    public static final String EXTENSION = "png";
    private static final String RUTA_TEMPORAL = Globales.RUTA_HOME;
    private static final String IMAGEN_TEMPORAL = RUTA_TEMPORAL + "tmp_img." + EXTENSION ;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Metodos">
    
    // <editor-fold defaultstate="collapsed" desc="Crear Imagen Temporal">
    public static String crearImagenTemporal(Dimension dimension, String ruta) throws Exception{
        int ancho = dimension.width;
        int alto = dimension.height;
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("Creando imagen temporal: " + IMAGEN_TEMPORAL, ConsolaDebug.PROCESO);
        
        checarRutaTemporal();
        BufferedImage imagen = obtenerImagen(dimension, ruta);
        
        ImageIO.write(imagen, EXTENSION, new File(IMAGEN_TEMPORAL));
        
        return IMAGEN_TEMPORAL;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Obtener Imagen">
    public static BufferedImage obtenerImagen(Dimension dimension, String ruta) throws Exception{        
        return redimensionarImagen(dimension, ImageIO.read(new File(ruta)));
    }
    
    public static BufferedImage obtenerImagen(Dimension dimension, Image imagen) throws Exception{        
        return redimensionarImagen(dimension, (BufferedImage) imagen);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Redimensionar Imagen">
    private static BufferedImage redimensionarImagen(Dimension dimension, BufferedImage imagenOriginal){
        int ancho = dimension.width;
        int alto = dimension.height;
        int type = imagenOriginal.getType() == 0? BufferedImage.TYPE_INT_ARGB : imagenOriginal.getType();
        
        BufferedImage imagenRedimensionada = new BufferedImage(ancho, alto, type);
        Graphics2D g = imagenRedimensionada.createGraphics();
        g.drawImage(imagenOriginal, 0, 0, ancho, alto, null);        
        g.dispose();
        
        return imagenRedimensionada;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eliminar Imagen Temporal">
    public static boolean eliminarImagenTemporal(){
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("Eliminando imagen temporal: " + IMAGEN_TEMPORAL, ConsolaDebug.PROCESO);
        
        boolean archivoBorrado;
        
        File imagenTemporal = new File(IMAGEN_TEMPORAL);
        archivoBorrado = imagenTemporal.delete();
        
        return archivoBorrado;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Checar ruta temporal">
    private static void checarRutaTemporal(){
        File rutaTemporal = new File(RUTA_TEMPORAL);
        if (!rutaTemporal.isDirectory())
            rutaTemporal.mkdir();
    }
    // </editor-fold>
    
    // </editor-fold>
}
