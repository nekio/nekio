package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JPanel;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;

public class MenuModulo extends SwingJFrame{
    private static final long serialVersionUID = 1L;
    
    private final Dimension DIMENSION = new Dimension(720, 300);
    private Container contenedor;
    
    private String modulo;
    private final Gestor gestor;
    private final List<String> entradas;
    private final List<String> acciones;
    private final List<String> entidades;
    private final List<String> iconos;
    
    public MenuModulo(String modulo, Gestor gestor, List<String> entradas, List<String> acciones, List<String> entidades, List<String> iconos){
        this.modulo = modulo;
        this.gestor = gestor;
        this.entradas = entradas;
        this.acciones = acciones;
        this.entidades = entidades;
        this.iconos = iconos;
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("MenuModulo: reconocido el modulo de " + modulo, ConsolaDebug.PROCESO);
        
        inicializar();
        
    }
    
    private void inicializar(){
        this.setTitle(Globales.NOMBRE_APP + " - " + modulo);
        
        this.contenedor = this.getContentPane();
        this.setSize(DIMENSION);
        this.setMinimumSize(DIMENSION);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
    }
    
    @Override
    public void agregarComponentes() {
        JPanel pnlEntradas = new JPanel();
        
        String entrada = null;
        String accion = null;
        String entidad = null;
        String icono = null;
        
        for(int i=0; i<entradas.size(); i++){
            entrada = entradas.get(i);
            accion = acciones.get(i);
            entidad = entidades.get(i);
            if(iconos != null)
                icono = iconos.get(i);

            pnlEntradas.add(new MenuBoton(entrada, gestor, accion, entidad, icono));
        }
        
        contenedor.add(pnlEntradas,"North");
    }

    @Override
    public void agregarEscuchadores() {
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
}
