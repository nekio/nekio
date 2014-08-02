
package nekio.myprp.recursos.generador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;

/**
 *
 * @author Nekio
 */
public class GEditor extends SwingJFrame{
    private List<JComboBox> cmbTipos;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private ControladorGenerador controlador;
    private int indiceTabla;
    private List<String> atributos;
    private List<Globales.TipoDato> tipos;
    
    public GEditor(ControladorGenerador controlador, int indiceTabla, String tabla, List<String> atributos, List<Globales.TipoDato> tipos){
        super("Editar atributos de " + tabla);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(800,300));
        this.setMaximumSize(new Dimension(800,600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        this.controlador = controlador;
        this.indiceTabla = indiceTabla;
        this.atributos = atributos;
        this.tipos = tipos;
        
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
    }

    @Override
    public void agregarComponentes() {
        // Atributos
        cmbTipos = new ArrayList<JComboBox>();
        int cantidadAtributos = atributos.size();
        
        JPanel pnlAtributos = new JPanel(new GridLayout(5, 1));
        for(int i=0; i<cantidadAtributos; i++){
            pnlAtributos.add(agregarAtributo(atributos.get(i), tipos.get(i)));
        }

        Box caja=Box.createVerticalBox();
        caja.add(new JScrollPane(pnlAtributos));
        
        // Espacio vacio
        JPanel pnlVacio = new JPanel(new BorderLayout());
        pnlVacio.setBackground(Color.DARK_GRAY);
        
        // Botones
        JPanel pnlBotones = new JPanel(new FlowLayout());
        pnlBotones.setBackground(Color.LIGHT_GRAY);
        
        btnAceptar = new JButton("Aceptar");
        pnlBotones.add(btnAceptar);
        
        btnCancelar = new JButton("Cancelar");
        pnlBotones.add(btnCancelar);
        
        // Agregar paneles
        this.add(caja, "North");
        this.add(pnlVacio, "Center");
        this.add(pnlBotones, "South");
    }

    @Override
    public void agregarEscuchadores() {
        btnAceptar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                aceptar();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                salir();
            }
        });
        
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private JPanel agregarAtributo(String atributo, Globales.TipoDato tipo){
        JPanel pnlAtributo = new JPanel(new FlowLayout());
        
        JLabel lblAtributo = new JLabel(atributo);
        JLabel lblTipo = new JLabel("[" + tipo.name() + "]");
        JComboBox cmbTipo = new JComboBox(Globales.TipoDato.values());
        cmbTipo.setSelectedItem(Globales.TipoDato.valueOf(tipo.name()));
        
        pnlAtributo.add(lblAtributo);
        pnlAtributo.add(lblTipo);
        pnlAtributo.add(cmbTipo);
        pnlAtributo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        this.cmbTipos.add(cmbTipo);
        
        return pnlAtributo;
    }
    
    private void aceptar(){
        List<Globales.TipoDato> tipos = new ArrayList<Globales.TipoDato>();
        
        for(int i=0; i<atributos.size(); i++){
            tipos.add((Globales.TipoDato) cmbTipos.get(i).getSelectedItem());
        }
        
        controlador.setTipos(indiceTabla, tipos);
        super.salir();
    }
    
}
