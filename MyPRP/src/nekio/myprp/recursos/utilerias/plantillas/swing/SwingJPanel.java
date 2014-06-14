package nekio.myprp.recursos.utilerias.plantillas.swing;

/**
 *
 * @author Nekio
 */

import javax.swing.JPanel;

public abstract class SwingJPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    // Metodos de inicializacion
    public abstract void agregarComponentes();
    public abstract void agregarEscuchadores();
}
