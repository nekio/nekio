package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.List;
import javax.swing.JFrame;
import nekio.myprp.recursos.utilerias.gui.swing.BD_Navegador;

public abstract class SwingMaestro extends JFrame{
    private static final long serialVersionUID = 1L;
    
    private BD_Navegador navegadorBD;
    
    // Metodo abstracto para la implementacion del manipulador de BD
    public abstract void recargar(List<DTO> listaDTO);
    
    // Metodos abstractos para la implementacion del navegador de BD
    public abstract void navegar(int accion);
    public abstract void buscar(String filtro);

    public BD_Navegador getNavegadorBD() {
        return navegadorBD;
    }

    public void setNavegadorBD(BD_Navegador navegadorBD) {
        this.navegadorBD = navegadorBD;
    }
}
