package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;

public class MenuBoton extends JButton{
    private String entrada;
    private Gestor gestor;
    private String icono;
    private String accion;
    private String entidad;
    
    public MenuBoton(String entrada, Gestor gestor, String accion, String entidad, String icono){
        this.entrada = entrada;
        this.gestor = gestor;
        this.accion = accion;
        this.entidad = entidad;
        this.icono = icono;
        
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("   MenuBoton: reconocida la entrada " + entrada, ConsolaDebug.PROCESO);
        
        agregarComponentes();
        agregarEscuchadores();
    }
    
    private void agregarComponentes(){
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        if(icono != null){
            ImageIcon imagen = new ImageIcon(getClass().getResource(icono));
            
            //imagen escalada
            //Icon iconoEscala = new ImageIcon(imagen.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
            this.setIcon(imagen);
        }else
            this.setText(entrada);
    }
    
    private void agregarEscuchadores(){
        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){      
                gestor.ejecutarControladorNegocio(accion, entidad);
            }
        });
    }
}
