package nekio.myprp.recursos.herramientas;

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
// </editor-fold>

/**
 *
 * @author Nekio
 */
public class ImagenAjustada implements Border{
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private BufferedImage imagen = null;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public ImagenAjustada(BufferedImage imagen) {
        this.imagen = imagen;       
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos Sobreescritos">
    @Override
    public void paintBorder(Component c,Graphics g,int x,int y,int ancho,int alto) {
        if(imagen!=null)
            g.drawImage(imagen,0,0,ancho,alto,null);
    }
    
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
    
    @Override
    public boolean isBorderOpaque() {
        return true;
    }
    // </editor-fold>
}
