package nekio.myprp.recursos.herramientas;

/**
 *
 * @author Nekio
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import nekio.myprp.recursos.utilerias.Globales;

public class ImagenEnvoltorio {
    private static final String EXTENSION = "png";
    private static final String RUTA_TEMPORAL = System.getProperty("user.home") + File.separator + "." + Globales.SIGLAS_APP;
    private static final String IMAGEN_TEMPORAL = RUTA_TEMPORAL + File.separator + "tmp_img." + EXTENSION ;

    public static String crearImagenTemporal(int ancho, int alto, String ruta) throws Exception{
        if(Globales.APP_DEBUG)
            System.out.println("\nCreando imagen temporal: " + IMAGEN_TEMPORAL);
        
        checarRutaTemporal();
        
        BufferedImage imagenOriginal = ImageIO.read(new File(ruta));
        int type = imagenOriginal.getType() == 0? BufferedImage.TYPE_INT_ARGB : imagenOriginal.getType();
        
        BufferedImage imagenRedimensionada = new BufferedImage(ancho, alto, type);
        Graphics2D g = imagenRedimensionada.createGraphics();
        g.drawImage(imagenOriginal, 0, 0, ancho, alto, null);        
        g.dispose();
        
        ImageIO.write(imagenRedimensionada, EXTENSION, new File(IMAGEN_TEMPORAL));
        
        return IMAGEN_TEMPORAL;
    }
    
    public static boolean eliminarImagenTemporal(){
        if(Globales.APP_DEBUG)
            System.out.println("\nEliminando imagen temporal: " + IMAGEN_TEMPORAL);
        
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
