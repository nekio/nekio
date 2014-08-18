package nekio.myprp.recursos.generador;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;

public class GeneradorGUI extends SwingJFrame{
    
    private enum Capas{
        BD,
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
    private JCheckBox chkPrimitivos;
    private JButton btnGenerar;
    private JButton btnEditar;
    private JButton btnCopiarPortapapeles;
    private JButton btnExportarActual;
    private JButton btnExportarTodo;
            
    public GeneradorGUI(ControladorGenerador generador){
        super("Generador de Capas del Sistema");
        this.controlador = generador;
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(850,500));
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
        
        btnEditar = new JButton("Editar");
        pnlSuperior.add(btnEditar);
        
        JLabel lblCapas = new JLabel("           Capa: ");
        cmbCapas = new JComboBox(Capas.values());
        pnlSuperior.add(lblCapas);
        pnlSuperior.add(cmbCapas);
        
        btnGenerar = new JButton("Generar");
        pnlSuperior.add(btnGenerar);
        
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
        JPanel pnlChks = new JPanel(new FlowLayout());
        chkEditar = new JCheckBox("Editar texto");
        pnlChks.add(chkEditar);
        chkPrimitivos = new JCheckBox("Tipos Primitivos");
        chkPrimitivos.setSelected(controlador.getGenerador().isPrimitivos());
        pnlChks.add(chkPrimitivos);
        pnlInferior.add(pnlChks, "West");
        
        JPanel pnlBotones = new JPanel(new FlowLayout());
        
        btnCopiarPortapapeles = new JButton("Copiar en el Portapapeles");
        btnCopiarPortapapeles.setEnabled(false);
        pnlBotones.add(btnCopiarPortapapeles);
        
        btnExportarActual = new JButton("Exportar capas de la tabla seleccionada");
        pnlBotones.add(btnExportarActual);
        
        btnExportarTodo = new JButton("Exportar capas de todas las tablas");
        pnlBotones.add(btnExportarTodo);
        
        pnlInferior.add(pnlBotones, "East");
        
        this.add(pnlInferior, "South");
    }

    @Override
    public void agregarEscuchadores() {
        btnEditar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                editarEntidad();
            }
        });
        
        btnGenerar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                verCodigo();
            }
        });
        
        btnCopiarPortapapeles.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                copiarPortapapeles();
            }
        });
        
        btnExportarActual.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                exportar(false);
            }
        });
        
        btnExportarTodo.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                exportar(true);
            }
        });
        
        chkEditar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){
                txtContenido.setEditable(chkEditar.isSelected());
             }
        });
        
        chkPrimitivos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){
                controlador.getGenerador().setPrimitivos(chkPrimitivos.isSelected());
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
                case BD:
                    codigo = controlador.getGenerador().getCodigoProcBD().get(tabla);
                break;
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
        btnCopiarPortapapeles.setEnabled(true);
    }
    
    private void editarEntidad(){
        int tablaSeleccionada = this.cmbTablas.getSelectedIndex();
        
        String tabla = controlador.getTablasBD().get(tablaSeleccionada);
        List<String> atributos = (List<String>) controlador.getDetallesTablaBD(tablaSeleccionada).get(BDConexion.Detalles.NOMBRE_CAMPOS.ordinal());
        List<Globales.TipoDato> tipos = (List<Globales.TipoDato>) controlador.getDetallesTablaBD(tablaSeleccionada).get(BDConexion.Detalles.TIPO_DATOS.ordinal());
        
        new GEditor(controlador, tablaSeleccionada, tabla, atributos, tipos);
    }
    
    private void copiarPortapapeles(){        
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection data = new StringSelection (txtContenido.getText());
        clipboard.setContents(data,data);
    
        String codigoCopiado = cmbCapas.getSelectedItem().toString();
    }
    
    private void exportar(boolean todo){
        if(todo){
            List<String> archivos = null;
            for(int i=0; i<controlador.getTablasBD().size(); i++){
                archivos = controlador.obtenerArchivos(i);
                crearCapas(archivos, i);
            }
        }else{
            int tablaSeleccionada = cmbTablas.getSelectedIndex();
            List<String> archivos = controlador.obtenerArchivos(tablaSeleccionada);
            crearCapas(archivos, tablaSeleccionada);
        }
    }
    
    private void crearCapas(List<String> archivos, int tablaSeleccionada){
        ConsolaDebug.agregarTexto("\n\nGenerando capas para " + controlador.getTablasBD().get(tablaSeleccionada) + "\n", ConsolaDebug.SQL, false);
        String texto=null; 
        for(String archivo:archivos){
            ConsolaDebug.agregarTexto("\n" + archivo, ConsolaDebug.MAPEO, false);
            texto = controlador.getGenerador().codigoDAO.get(tablaSeleccionada);
            
            try{                
                FileWriter archivoNuevo = new FileWriter(archivo);
                archivoNuevo.write(texto);
                archivoNuevo.close();
                archivoNuevo = null;
            }catch(Exception e){}
        }

        ConsolaDebug.agregarTexto();
    }
}
