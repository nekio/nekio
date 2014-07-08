package nekio.myprp.recursos.herramientas;

/**
 *
 * @author Nekio
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import nekio.myprp.recursos.utilerias.Globales;

public class ImagenEnvoltorio {
    public static final String EXTENSION = "png";
    private static final String RUTA_TEMPORAL = System.getProperty("user.home") + File.separator + "." + Globales.SIGLAS_APP;
    private static final String IMAGEN_TEMPORAL = RUTA_TEMPORAL + File.separator + "tmp_img." + EXTENSION ;

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
    
    public static BufferedImage obtenerImagen(Dimension dimension, String ruta) throws Exception{        
        return redimensionarImagen(dimension, ImageIO.read(new File(ruta)));
    }
    
    public static BufferedImage obtenerImagen(Dimension dimension, Image imagen) throws Exception{        
        return redimensionarImagen(dimension, (BufferedImage) imagen);
    }
    
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
    
    public static boolean eliminarImagenTemporal(){
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("Eliminando imagen temporal: " + IMAGEN_TEMPORAL, ConsolaDebug.PROCESO);
        
        boolean archivoBorrado;
        
        File imagenTemporal = new File(IMAGEN_TEMPORAL);
        archivoBorrado = imagenTemporal.delete();
        
        return archivoBorrado;
    }
    
    private static void checarRutaTemporal(){
        File rutaTemporal = new File(RUTA_TEMPORAL);
        if (!rutaTemporal.isDirectory())
            rutaTemporal.mkdir();
    }
}
