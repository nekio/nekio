package nekio.myprp.recursos.utilerias.plantillas.swing;

/**
 *
 * @author Nekio
 */

import javax.swing.JFrame;

public abstract class SwingJFrame extends JFrame{
    private static final long serialVersionUID = 1L;
    
    public void salir(){
        this.dispose();
    } 
    
    // Metodos de inicializacion
    public abstract void agregarComponentes();
    public abstract void agregarEscuchadores();
}
