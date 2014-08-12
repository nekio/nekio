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
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJPanel;

public abstract class BD_Manipulador extends SwingJPanel{
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
    
    @Override
    public void agregarComponentes(){
        JPanel pnlContenedor = new JPanel();
        
        btnNuevo = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "nuevo"));
        btnEditar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "actualizar"));
        btnBorrar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "eliminar"));
        btnGuardar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "guardar"));
        btnCancelar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "cancelar"));
        
        pnlContenedor.add(getBtnNuevo());
        pnlContenedor.add(getBtnEditar());
        pnlContenedor.add(getBtnBorrar());
        pnlContenedor.add(getBtnGuardar());
        pnlContenedor.add(getBtnCancelar());
        
        this.add(pnlContenedor,"Center");
    }
    
    @Override
    public void agregarEscuchadores(){
        btnNuevo.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                habilitarEdicion(false);
                insertarRegistro();
            }
        });
        
        btnEditar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                habilitarEdicion(false);
                editarRegistro();
            }
        });
        
        btnBorrar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                habilitarEdicion(true);
                borrarRegistro();
            }
        });
        
        btnGuardar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                habilitarEdicion(true);
                guardarEdicion();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                habilitarEdicion(true);
                cancelarEdicion();
            }
        });
    }
    
    public void habilitarEdicion(boolean edicion){
        if(edicion){
            btnNuevo.setEnabled(true);
            btnEditar.setEnabled(true);
            btnBorrar.setEnabled(true);
            btnGuardar.setEnabled(false);
            btnCancelar.setEnabled(false);
        }else{
            btnNuevo.setEnabled(false);
            btnEditar.setEnabled(false);
            btnBorrar.setEnabled(false);
            btnGuardar.setEnabled(true);
            btnCancelar.setEnabled(true);
        }
        
    }
    
    public abstract void insertarRegistro();
    public abstract void editarRegistro();
    public abstract void borrarRegistro();
    public abstract void guardarEdicion();
    public abstract void cancelarEdicion();    

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }
}
