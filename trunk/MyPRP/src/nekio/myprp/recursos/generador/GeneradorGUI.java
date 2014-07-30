package nekio.myprp.recursos.generador;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;

public class GeneradorGUI extends SwingJFrame{
    
    private enum Capas{
        DTO,
        DAO,
        NEGOCIO,
        GESTOR
    };
    
    private ControladorGenerador controlador;
    private JComboBox cmbTablas;
    private JComboBox cmbCapas;
    private JTextField txtAutor;
    private JTextArea txtContenido;
    private JCheckBox chkEditar;
    private JButton btnGenerar;
    private JButton btnEditar;
    private JButton btnExportar;
            
    public GeneradorGUI(ControladorGenerador generador){
        super("Generador de Capas del Sistema");
        this.controlador = generador;
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(400,300));
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        generador.crearCapasDesdeEsquema();
        
        agregarComponentes();
        agregarEscuchadores();
        
        
        this.setVisible(true);
    }

    @Override
    public void agregarComponentes(){
        //Norte
        JPanel pnlSuperior = new JPanel(new FlowLayout());
        
        JLabel lblAutor = new JLabel("Autor: ");
        txtAutor = new JTextField(20);
        pnlSuperior.add(lblAutor);
        pnlSuperior.add(txtAutor);
        
        JLabel lblTablas = new JLabel("         Tabla: ");
        cmbTablas = new JComboBox(controlador.getTablasBD().toArray());
        pnlSuperior.add(lblTablas);
        pnlSuperior.add(cmbTablas);
        
        JLabel lblCapas = new JLabel("         Capa: ");
        cmbCapas = new JComboBox(Capas.values());
        pnlSuperior.add(lblCapas);
        pnlSuperior.add(cmbCapas);
        
        btnGenerar = new JButton("Generar");
        pnlSuperior.add(btnGenerar);
        
        btnEditar = new JButton("Editar");
        pnlSuperior.add(btnEditar);
        
        this.add(pnlSuperior, "North");
        
        // Centro
        txtContenido = new JTextArea();
        txtContenido.setEditable(false);
        txtContenido.setFont(new Font("Courier New",java.awt.Font.PLAIN,12));
        JScrollPane scrollCaja = new JScrollPane(txtContenido);
        scrollCaja.setOpaque(false);
        
        this.add(scrollCaja, "Center");
        
        //Sur
        JPanel pnlInferior = new JPanel(new BorderLayout());
        
        chkEditar = new JCheckBox("Editar texto");
        pnlInferior.add(chkEditar, "West");
        
        btnExportar = new JButton("Exportar");
        pnlInferior.add(btnExportar, "East");
        
        this.add(pnlInferior, "South");
    }

    @Override
    public void agregarEscuchadores() {
        btnEditar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                //
            }
        });
        
        btnGenerar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                verCodigo();
            }
        });
        
        chkEditar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){
                txtContenido.setEditable(chkEditar.isSelected());
             }
        });
        
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private void verCodigo(){
        int tabla = cmbTablas.getSelectedIndex();
        Capas capa = Capas.valueOf(cmbCapas.getSelectedItem().toString());
        
        String autor = "";
        String codigo = null;
        try{
            switch(capa){
                case DTO:
                    codigo = controlador.getGenerador().getCodigoDTO().get(tabla);
                break;
                case DAO:
                    codigo = controlador.getGenerador().getCodigoDAO().get(tabla);
                break;
                case NEGOCIO:
                    codigo = controlador.getGenerador().getCodigoObjetoNegocio().get(tabla);
                break;
                case GESTOR:
                    codigo = controlador.getGenerador().getCodigoGestor().get(tabla);
                break;
            }
            
            if (txtAutor != null){
                if(txtAutor.getText().length() > 0){
                    autor = 
                        "/**" +
                        "\n *" +
                        "\n * @author " + txtAutor.getText() +
                        "\n * " + Fecha.obtenerFechaFormateada(new Date(), Fecha.FORMATO_COMPLETO_2) +
                        "\n */\n\n";
                    }
            }
        }catch(Exception e){
            codigo = "";
        }

        txtContenido.setText(controlador.getComentarios() + autor + codigo);
    }
}
