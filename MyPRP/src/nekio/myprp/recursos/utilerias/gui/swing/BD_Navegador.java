package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.plantillas.SwingMaestro;

public class BD_Navegador extends JPanel{
    private static final long serialVersionUID = 1L;
    public static final int PRIMERO = 1;
    public static final int ANTERIOR = 2;
    public static final int SIGUIENTE = 3;
    public static final int ULTIMO = 4;
    
    protected SwingMaestro gui;
    private List<String> encontrados;
    
    private JButton btnPrimero;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnUltimo;
    private JTextField txtBuscar;
    
    public BD_Navegador(SwingMaestro gui){
        this.gui = gui;
        this.gui.setNavegadorBD(this);
        
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
    }
    
    private void agregarComponentes(){  
        this.setLayout(new BorderLayout());
        
        JPanel pnlAtras = new JPanel();
        JPanel pnlAdelante = new JPanel();
        
        btnPrimero = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "primero"));
        btnAnterior = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "anterior"));
        pnlAtras.add(btnPrimero);
        pnlAtras.add(btnAnterior);
        
        txtBuscar = new JTextField();
        
        btnSiguiente = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "siguiente"));
        btnUltimo = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "ultimo"));
        pnlAdelante.add(btnSiguiente);
        pnlAdelante.add(btnUltimo);
        
        this.add(pnlAtras, "West");
        this.add(txtBuscar, "Center");
        this.add(pnlAdelante, "East");
    }
    
    private void agregarEscuchadores(){
        btnPrimero.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                irPrimero();
            }
        });
        
        btnAnterior.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                irAnterior();
            }
        });
        
        btnSiguiente.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                irSiguiente();
            }
        });
        
        btnUltimo.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                irUltimo();
            }
        });
        
        txtBuscar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                if(encontrados == null)
                    buscarRegistro(txtBuscar.getText());
                //else
                    //buscarRegistro();
            }
        });
    }
    
    public void deshabilitarPrimero(){
        btnPrimero.setEnabled(false);
    }
    
    public void deshabilitarAnterior(){
        btnAnterior.setEnabled(false);
    }
    
    public void deshabilitarSiguiente(){
        btnSiguiente.setEnabled(false);
    }
    
    public void deshabilitarUltimo(){
        btnUltimo.setEnabled(false);
    }
    
    public void deshabilitarBuscar(){
        txtBuscar.setEnabled(false);
    }
    

    public void irPrimero() {
        btnPrimero.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnSiguiente.setEnabled(true);
        btnUltimo.setEnabled(true);
        gui.navegar(PRIMERO);
    }

    public void irAnterior() {
        btnSiguiente.setEnabled(true);
        btnUltimo.setEnabled(true);
        gui.navegar(ANTERIOR);
    }

    public void irSiguiente() {
        btnPrimero.setEnabled(true);
        btnAnterior.setEnabled(true);
        gui.navegar(SIGUIENTE);
    }

    public void irUltimo() {
        btnPrimero.setEnabled(true);
        btnAnterior.setEnabled(true);
        btnSiguiente.setEnabled(false);
        btnUltimo.setEnabled(false);
        gui.navegar(ULTIMO);
    }
    
    public void buscarRegistro(String filtro){
        gui.buscar(filtro);
    }

    public List<String> getEncontrados() {
        return encontrados;
    }

    public void setEncontrados(List<String> encontrados) {
        this.encontrados = encontrados;
    }
}
