
package nekio.myprp.recursos.utilerias;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Nekio
 */
public class Icono {
    public JLabel obtener(String ruta, String descripcion){
        JLabel imagen = new JLabel(new ImageIcon(getClass().getResource(ruta)));
        
        if(descripcion != null)
            imagen.setToolTipText(descripcion);
        
        return imagen;
    }
    
    public JButton obtener(String ruta){
        JButton imagen = new JButton(new ImageIcon(getClass().getResource(ruta)));
        
        return imagen;
    }
}
