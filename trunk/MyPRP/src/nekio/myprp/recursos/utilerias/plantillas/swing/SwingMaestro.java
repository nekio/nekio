package nekio.myprp.recursos.utilerias.plantillas.swing;

/**
 *
 * @author Nekio
 */

import java.util.List;
import nekio.myprp.recursos.utilerias.gui.swing.BD_Navegador;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public abstract class SwingMaestro extends SwingJFrame{
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
