package gui;

/**
 * 07/Jun/2014
 * LANIA - MRYSI
 * Seguridad en Redes
 * 
 * @author LI. Emiliano Anastasio Landa
 *         eanastasio@veracruz.gob.mx
 * 
 * @author ISC. Sinesio Ivan Carrillo Heredia 
 *         ivan.carrillo@si-ti.com.mx
 * 
 */

import analizador.Analizador;
import herramientas.Alfabeto;
import herramientas.Alfabeto.Espanol;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ventana extends JFrame{
    private static final long serialVersionUID = 1L;
    
    private Container contenedor;
    private Alfabeto alfabeto;
    
    private JTextField txtRuta;
    private JButton btnRuta;
    private JComboBox cmbCifrado;
    private JButton btnAnalizar;
    private JButton btnSiguiente;
    private JButton btnGraficar;
    private JTextArea txtCifrado;
    private JTextArea txtDescifrado;
    private JScrollPane scrollCifrado;
    private JScrollPane scrollDescifrado;
    private JPanel pnlInferior;
    
    private List<Espanol> simbolos;
    private List<Float> porcentajes;
    
    private int indiceLlave;
    private List<List> simbolosProbables; 
    
    public Ventana(){
        this.contenedor = this.getContentPane();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
                
        alfabeto = new Alfabeto();
        indiceLlave = 0;
        
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
        this.setResizable(false);
    }
    
    private void agregarComponentes(){
        // PANEL SUPERIOR
        JPanel pnlSuperior = new JPanel(new BorderLayout());
        
        JLabel lblRuta = new JLabel("  Ruta de archivo:  ");
        lblRuta.setHorizontalAlignment(SwingConstants.CENTER);
        pnlSuperior.add(lblRuta, "West");
        
        txtRuta = new JTextField();
        txtRuta.setEnabled(false);
        pnlSuperior.add(txtRuta, "Center");
        
        btnRuta = new JButton("...");
        pnlSuperior.add(btnRuta, "East");
        
        JPanel pnlSuperiorAux = new JPanel();
        JLabel lblCifrado = new JLabel("  Cifrado:  ");
        lblCifrado.setHorizontalAlignment(SwingConstants.CENTER);
        pnlSuperiorAux.add(lblCifrado);
        
        String cifrados[] = {"Cifrado Cesar","Cifrado por Sustitucion"};
        cmbCifrado = new JComboBox(cifrados);
        pnlSuperiorAux.add(cmbCifrado);
        
        JLabel lblEspacioBlanco = new JLabel("     ");
        lblEspacioBlanco.setHorizontalAlignment(SwingConstants.CENTER);
        pnlSuperiorAux.add(lblEspacioBlanco);
        
        btnAnalizar = new JButton("Analizar");
        btnAnalizar.setEnabled(false);
        pnlSuperiorAux.add(btnAnalizar);
        
        btnGraficar = new JButton("Graficar");
        btnGraficar.setEnabled(false);
        pnlSuperiorAux.add(btnGraficar);
        
        pnlSuperior.add(pnlSuperiorAux, "South");
        contenedor.add(pnlSuperior, "North");
        
        // PANEL CENTRAL
        JPanel pnlCentral = new JPanel(new GridLayout(1,2));
        
        // Cifrado
        JPanel pnlCentralCifrado = new JPanel(new BorderLayout());
        
        JLabel lblCifradoLeido = new JLabel("Texto Cifrado Leido:");
        lblCifradoLeido.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCentralCifrado.add(lblCifradoLeido,"North");
        
        txtCifrado = new JTextArea();
        txtCifrado.setEditable(false);
        txtCifrado.setWrapStyleWord(true);
        txtCifrado.setLineWrap(true);
        
        scrollCifrado = new JScrollPane(txtCifrado);
        scrollCifrado.setOpaque(false);
        pnlCentralCifrado.add(scrollCifrado, "Center");
        
        // Descifrado
        JPanel pnlCentralDescifrado = new JPanel(new BorderLayout());
        
        JLabel lblDescifradoLeido = new JLabel("Texto Descifrado calculado:");
        lblDescifradoLeido.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCentralDescifrado.add(lblDescifradoLeido,"North");
        
        txtDescifrado = new JTextArea();
        txtDescifrado.setEditable(false); 
        txtDescifrado.setWrapStyleWord(true);
        txtDescifrado.setLineWrap(true);

        scrollDescifrado = new JScrollPane(txtDescifrado);
        scrollDescifrado.setOpaque(false);               
        pnlCentralDescifrado.add(scrollDescifrado, "Center");
        
        pnlCentral.add(pnlCentralCifrado);
        pnlCentral.add(pnlCentralDescifrado);
        
        contenedor.add(pnlCentral, "Center");
        
        // PANEL INFERIOR
        definirPanelInferior();
    }
    
    private void agregarEscuchadores(){
        btnRuta.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                leerArchivo();
            }
        });
        
        btnSiguiente.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                siguienteLlave();
            }
        });
        
        btnGraficar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                graficar();
            }
        });
        
        btnAnalizar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                if(btnSiguiente != null)
                    btnSiguiente.setEnabled(true);
                analizar();
                btnGraficar.setEnabled(true);
                btnAnalizar.setEnabled(false);
            }
        });
        
        cmbCifrado.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                escucharCombo();
            }
        });
                
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    public void leerArchivo(){
        String textoLeido = "";
        
        JFileChooser fc = new JFileChooser(getClass().getResource("/recursos").getPath());
        int resultado = fc.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            try {
                Scanner scanner = new Scanner(archivo);
                while (scanner.hasNext()) {
                    textoLeido = textoLeido + scanner.nextLine();
                }
                
                this.txtRuta.setText(archivo.getPath());
                this.txtCifrado.setText(textoLeido);
                btnAnalizar.setEnabled(true);
            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "No se reconoce el archivo proporcionado", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE); 
            }
        }
    }
    
    private void escucharCombo(){
        if(this.txtDescifrado.getText().length() != 0){
            txtDescifrado.setText("");
            btnAnalizar.setEnabled(true);
            btnGraficar.setEnabled(false);
        }
        
        if(this.cmbCifrado.getSelectedIndex() != 0)
            definirPanelInferior();
    }
    
    private void definirPanelInferior(){
        indiceLlave = 0;
        
        if(pnlInferior != null){
            pnlInferior.setVisible(false);
            pnlInferior = null;
        }
        
        pnlInferior = new JPanel();
        if(this.cmbCifrado.getSelectedIndex() == 0){
            btnSiguiente = new JButton();
            btnSiguiente.setEnabled(false);
            mostrarBtnCesar();
            
            pnlInferior.add(btnSiguiente);
            siguienteLlave();
        }
        
        contenedor.add(pnlInferior, "South");
    }
    
    private void siguienteLlave(){
        if(indiceLlave < alfabeto.getTotalSimbolos())
            indiceLlave++;
        else
            indiceLlave = 1;
        
        mostrarBtnCesar();
        analizar();
    }
    
    private void mostrarBtnCesar(){
        btnSiguiente.setText("Siguiente desplazamiento (" + (indiceLlave) + " de "+alfabeto.getTotalSimbolos()+")");
    }
    
    private void analizar(){
        txtDescifrado.setText("");
        int tipoDescifrado = cmbCifrado.getSelectedIndex();
        String textoCifrado = txtCifrado.getText();
        
        Analizador analizador = new Analizador(alfabeto, textoCifrado);
        
        String resultado = null;
        if(tipoDescifrado == 0)
            resultado = analizador.descifrarCesar(indiceLlave-1);
        else
            resultado = descifarSustitucion(analizador);
        
        porcentajes = analizador.getPorcentajes();
        
        txtDescifrado.setText(resultado);
    }
    
    public String descifarSustitucion(Analizador analizador){
        simbolosProbables = analizador.getSimbolosProbables();
        
        if(pnlInferior != null){
            pnlInferior.setVisible(false);
            pnlInferior = null;
        }
        
        pnlInferior = new JPanel();
        List<Espanol> masProbable = analizador.obtenerAproximado();
        
        int indice = 0;
        for(Espanol letra:Alfabeto.Espanol.getOrdenFrecuencia()){
            JPanel btnBit = new BotonSimbolo().crear(this, simbolosProbables.get(indice), masProbable.get(indice), letra.ordinal());
            pnlInferior.add(btnBit,"Center");
            
            indice++;
        }
        contenedor.add(pnlInferior, "South");
        
        return analizador.sustituirAproximado();
    }
    
    private void graficar(){
        List<Float> porcentajesPromedio = new ArrayList<Float>();
        Point punto = null;
        
        simbolos = new ArrayList<Espanol>();
        for(Espanol simbolo:Alfabeto.Espanol.values()){
            simbolos.add(simbolo);
            porcentajesPromedio.add(simbolo.getFrecuencia());
        }        
        
        punto = new Point(250,80);
        new Graficador(punto, "Frecuencias promedio del Alfabeto Espanol", simbolos, porcentajesPromedio);
        punto = new Point(250,400);
        new Graficador(punto, "Analisis de Frecuencia del Texto Cifrado", simbolos, porcentajes);
    }
    
    public void salir(){
        this.dispose();
        System.exit(0);
    } 
}
