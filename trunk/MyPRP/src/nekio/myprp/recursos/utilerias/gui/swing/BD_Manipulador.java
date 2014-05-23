package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import nekio.myprp.recursos.utilerias.Idioma;

public abstract class BD_Manipulador extends JPanel{
    private static final long serialVersionUID = 1L;
    
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnBorrar;
    private JButton btnGuardar;
    private JButton btnCancelar;
    
    public BD_Manipulador(){        
        this.setLayout(new BorderLayout());
        
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
    }
    
    private void agregarComponentes(){
        JPanel pnlContenedor = new JPanel();
        
        btnNuevo = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "nuevo"));
        btnEditar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "actualizar"));
        btnBorrar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "eliminar"));
        btnGuardar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "guardar"));
        btnCancelar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "cancelar"));
        
        pnlContenedor.add(btnNuevo);
        pnlContenedor.add(btnEditar);
        pnlContenedor.add(btnBorrar);
        pnlContenedor.add(btnGuardar);
        pnlContenedor.add(btnCancelar);
        
        this.add(pnlContenedor,"Center");
    }
    
    private void agregarEscuchadores(){
        btnNuevo.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                insertarRegistro();
            }
        });
        
        btnEditar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                editarRegistro();
            }
        });
        
        btnBorrar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                borrarRegistro();
            }
        });
        
        btnGuardar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                guardarEdicion();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                cancelarEdicion();
            }
        });
    }
    
    public abstract void insertarRegistro();
    public abstract void editarRegistro();
    public abstract void borrarRegistro();
    public abstract void guardarEdicion();
    public abstract void cancelarEdicion();    
}
