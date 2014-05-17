package nekio.myprp.recursos.herramientas;

/**
 *
 * @author Nekio
 */

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import nekio.myprp.recursos.utilerias.Globales;

public class ImagenEnvoltorio {
    private static final String EXTENSION = "png";
    private static final String RUTA_TEMPORAL = System.getProperty("user.home") + File.separator + "." + Globales.SIGLAS_APP;
    private static final String IMAGEN_TEMPORAL = RUTA_TEMPORAL + File.separator + "tmp_img." + EXTENSION ;
            
    public static String crearImagenTemporal(Dimension dimension, String ruta) throws Exception{
        if(Globales.APP_DEBUG)
            System.out.println("\nCreando imagen temporal: " + IMAGEN_TEMPORAL);
        
        checarRutaTemporal();
        
        ImageIcon imagen = new ImageIcon(ruta);
        
        JLabel lblImagen=new JLabel();
        lblImagen.setSize(dimension);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_DEFAULT));
        lblImagen.setIcon(icono);
        
        BufferedImage bufferImagen = new BufferedImage(lblImagen.getWidth(), lblImagen.getHeight(), BufferedImage.TYPE_INT_ARGB);

        ImageIO.write(bufferImagen, EXTENSION, new File(IMAGEN_TEMPORAL));
        
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
