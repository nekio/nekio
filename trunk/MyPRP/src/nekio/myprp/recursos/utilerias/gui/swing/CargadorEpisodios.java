
package nekio.myprp.recursos.utilerias.gui.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Nekio
 * NOTA: Clase desacoplada de la BD para efectos de portabilidad
 */

public class CargadorEpisodios extends JFrame{
    private static final long serialVersionUID = 1L;
    private final Dimension DIMENSION = new Dimension(720, 600);
    
    private Container contenedor;
    private JTextField txtSerie;
    private JTextArea txtNombreOriginalEpis;
    private JTextArea txtNombreHispanoEpis;
    
    public CargadorEpisodios(){
        this.setTitle("Cargador de Episodios");
        
        this.contenedor = this.getContentPane();
        this.setSize(DIMENSION);
        this.setMinimumSize(DIMENSION);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        agregarComponentes(); 
        agregarEscuchadores();
        
        this.setVisible(true);
    }
    
    private void agregarComponentes() {
        JPanel pnlContenido = new JPanel(new BorderLayout());
    }

    private void agregarEscuchadores() {
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    public void salir(){
        this.dispose();
    } 
}
