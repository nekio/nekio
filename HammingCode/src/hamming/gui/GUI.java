package hamming.gui;

import hamming.hammingcode.Globales;
import hamming.hammingcode.Hamming;
import hamming.hammingcode.HammingDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class GUI extends JFrame{
    private Hamming hamming;
    private HammingDTO dto;
    private Container contenedor;
    private JPanel pnlBitsExterno;
    private JPanel pnlBitsExternoNorte;
    private JPanel pnlBitsExternoSur;
    private JPanel pnlBitsBotones;
    private JPanel pnlCentral;
    private JPanel pnlBotones;
    
    private JMenuBar barraMenu;
    private JMenu mnArchivo;
    private JMenu mnAyuda;
    private JMenuItem mnItemReiniciar;
    private JMenuItem mnItemSalir;
    private JMenuItem mnItemAcercaDe;
    
    private JButton btnAgregarBits;
    private JButton btnEliminarBits;
    private JButton btnGenerarHamming;
    private JButton btnVerHammingCorrupto;
    private JButton btnDetectarError;
    
    private JTextPane txtCodigoOriginal;
    private JTextPane txtHamming;
    private JComboBox cmbPosicion;
    private JTextPane txtHammingCorrupto;
    private JTextField txtBitsOriginales;
    private JTextField txtBitsParidad;
    private JTextField txtLongitud;
    
    public GUI(){
        this(null);
    }
    
    public GUI(HammingDTO dto){
        super(Globales.NOMBRE_APLICACION);
        
        this.dto = dto;
        
        contenedor = this.getContentPane();
        Dimension dimensionesMinimas=new Dimension(850,370);

        this.setSize(dimensionesMinimas);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(dimensionesMinimas);
        
        
        agregarMenu();
        agregarComponentes();
        agregarEscuchadores();

        this.setVisible(true);
    }
    
    private void agregarMenu(){
        barraMenu=new JMenuBar();

        /*Archivo*/
        mnArchivo=new JMenu("Archivo");
            mnItemReiniciar=new JMenuItem("Reiniciar");
            mnArchivo.add(mnItemReiniciar);
            
            mnItemSalir=new JMenuItem("Salir");
            mnArchivo.add(mnItemSalir);
        
        /*Ayuda*/
        mnAyuda=new JMenu("Ayuda");
            mnItemAcercaDe=new JMenuItem("Acerca de...");
            mnAyuda.add(mnItemAcercaDe);
        
        barraMenu.add(mnArchivo);
        barraMenu.add(mnAyuda);
        setJMenuBar(barraMenu);
    }
    
    private void agregarComponentes(){
        reiniciar();
        
        /*Panel Central*/
        pnlCentral = new JPanel(null);
        
        JLabel lblCodOriginal = new JLabel("Codigo Original:");
        lblCodOriginal.setBounds(10,10,130,24);
        pnlCentral.add(lblCodOriginal);
        
        txtCodigoOriginal = new JTextPane();
        txtCodigoOriginal.setBounds(150,10,300,24);
        pnlCentral.add(txtCodigoOriginal);
        
        JLabel lblCodHamming = new JLabel("Codigo Hamming:");
        lblCodHamming.setBounds(10,50,130,24);
        pnlCentral.add(lblCodHamming);
        
        txtHamming = new JTextPane();
        txtHamming.setBounds(150,50,300,24);
        pnlCentral.add(txtHamming);
        
        JLabel lblPosicionError = new JLabel("Posicion de error:");
        lblPosicionError.setBounds(10,90,130,24);
        pnlCentral.add(lblPosicionError);
        
        JLabel lblCodCorrupto = new JLabel("Código Corrupto:");
        lblCodCorrupto.setBounds(10,130,130,24);
        pnlCentral.add(lblCodCorrupto);
        
        txtHammingCorrupto = new JTextPane();
        txtHammingCorrupto.setBounds(150,130,300,24);
        pnlCentral.add(txtHammingCorrupto);
        
        JLabel lblBitsOriginales = new JLabel("Bits Originales:");
        lblBitsOriginales.setBounds(550,10,100,24);
        pnlCentral.add(lblBitsOriginales);
                
        txtBitsOriginales = new JTextField();
        txtBitsOriginales.setBounds(670,10,50,24);
        txtBitsOriginales.setEditable(false);
        pnlCentral.add(txtBitsOriginales);
        
        JLabel lblBitsParidad = new JLabel("Bits de Paridad:");
        lblBitsParidad.setBounds(550,50,100,24);
        pnlCentral.add(lblBitsParidad);
                
        txtBitsParidad = new JTextField();
        txtBitsParidad.setBounds(670,50,50,24);
        txtBitsParidad.setEditable(false);
        pnlCentral.add(txtBitsParidad);
                
        JLabel lblLongitud = new JLabel("Longitud Total:");
        lblLongitud.setBounds(550,90,100,24);
        pnlCentral.add(lblLongitud);
        
        txtLongitud = new JTextField();
        txtLongitud.setBounds(670,90,50,24);
        txtLongitud.setEditable(false);
        pnlCentral.add(txtLongitud);
        
        /*Panel Botones*/
        pnlBotones = new JPanel();
        
        btnGenerarHamming = new JButton("Generar Hamming");
        pnlBotones.add(btnGenerarHamming);
        
        btnVerHammingCorrupto = new JButton("Ver Hamming Corrupto");
        btnVerHammingCorrupto.setEnabled(false);
        pnlBotones.add(btnVerHammingCorrupto);
        
        btnDetectarError = new JButton("Detectar Error");
        btnDetectarError.setEnabled(false);     
        pnlBotones.add(btnDetectarError);
        
        pnlCentral.setBackground(Color.LIGHT_GRAY);
        
        /*Panel Bits*/
        pnlBitsExterno = new JPanel(new BorderLayout());
        
        pnlBitsExternoNorte = new JPanel();
        btnEliminarBits = new JButton("- Bits");
        btnAgregarBits = new JButton("+ Bits");
        pnlBitsExternoNorte.add(btnEliminarBits,"East");
        pnlBitsExternoNorte.add(btnAgregarBits,"West");
        
        pnlBitsExternoSur = new JPanel(new BorderLayout());
        definirBitsOriginales(Globales.MIN_BITS);
        
        JLabel lblCodeword = new JLabel(" "/*"Codigo original"*/);
        lblCodeword.setHorizontalAlignment(SwingConstants.CENTER);
        pnlBitsExternoSur.add(lblCodeword,"South");
        
        pnlBitsExterno.add(pnlBitsExternoNorte,"North");
        pnlBitsExterno.add(pnlBitsExternoSur,"South");
        
        /*Localizacion de paneles*/
        contenedor.add(pnlBitsExterno,"North");
        contenedor.add(pnlCentral,"Center");
        contenedor.add(pnlBotones,"South");
    }
    
    private void agregarEscuchadores(){
        mnItemReiniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){
                reiniciar();
                definirBitsOriginales(Globales.MIN_BITS);
                btnGenerarHamming.setEnabled(true);
            }
        });
        
        mnItemSalir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){
                salir();
            }
        });
        
        mnItemAcercaDe.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){
                mostrarMsj("Desarrolladores:\n"+
                           "\n      LI. Emiliano Anastasio Landa"+
                           "\n      ISC. Sinesio Ivan Carrillo Heredia");
            }
        });
        
        btnEliminarBits.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                eliminarBit();
            }
        });
        
        btnAgregarBits.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                agregarBit();
            }
        });
        
        btnGenerarHamming.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                generarHamming();
            }
        });
        
        btnVerHammingCorrupto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                verHammingCorrupto();                
            }
        });
        
        btnDetectarError.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                detectarError();                
            }
        });
        
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private void generarHamming(){
        hamming = new Hamming();
        dto.setBitsHamming(hamming.procesarCodigo(dto.getCadenaBitsOriginales()));
        descubrirHamming();
    }
    
    private void verHammingCorrupto(){
        int posicionError = (int)cmbPosicion.getSelectedItem();
        int longitudDatos = dto.getBitsHamming().size();
        
        dto.setBitsHammingCorrupto(hamming.obtenerHammingCorrupto(posicionError,longitudDatos));
        escribirHammingCorrupto();
        
        cmbPosicion.setEnabled(false);
        btnVerHammingCorrupto.setEnabled(false);
        btnDetectarError.setEnabled(true);
    }
    
    private void detectarError(){
        int longitudTotal = dto.getBitsHamming().size();
        int indiceError = cmbPosicion.getSelectedIndex()+1;
        
        int indiceCalculado = hamming.detectarError();
        String mensaje = "El algoritmo ha calculado el error en la el indice: "+indiceCalculado;
        
        mostrarMsj(mensaje);
    }
    
    private void agregarTextoPanel(JTextPane tp, String texto, boolean bitParidad){
        agregarTextoPanel(tp,texto,bitParidad,Color.LIGHT_GRAY);
    }
    private void agregarTextoPanel(JTextPane tp, String texto, boolean bitEspecial,Color colorSecundario){
        activarPanelesTexto(true);
        
        Color color = Color.BLACK;
        if(bitEspecial)
            color = colorSecundario;
        
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet as = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        as = sc.addAttribute(as, StyleConstants.FontFamily, "Courier New");
        as = sc.addAttribute(as, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        as = sc.addAttribute(as, StyleConstants.Bold, Boolean.TRUE);

        int longitud = tp.getDocument().getLength();
        tp.setCaretPosition(longitud);
        tp.setCharacterAttributes(as, false);
        tp.replaceSelection(texto);
        
        activarPanelesTexto(false);
    }
    
    private void activarPanelesTexto(boolean activar){
        txtCodigoOriginal.setEditable(activar);
        txtHamming.setEditable(activar);
        txtHammingCorrupto.setEditable(activar);
    }
    
    private void eliminarBit(){
        definirBitsOriginales(dto.getBitsOriginales().size()-1);
    }
    
    private void agregarBit(){
        definirBitsOriginales(dto.getBitsOriginales().size()+1);
    }

    private DefaultComboBoxModel obtenerModeloCombo(){
        DefaultComboBoxModel mdlCombo = new DefaultComboBoxModel();
        int longitudOriginal = dto.getBitsOriginales().size();
        int cantidadBitsParidad = Globales.obtenerNumeroDeBitsDeParidad(longitudOriginal);
        int longitudTotal = longitudOriginal + cantidadBitsParidad;
                
        for(int i=0; i<longitudTotal; i++)
            mdlCombo.addElement(i+1);
        
        return mdlCombo;
    }
    
    private void definirBitsOriginales(int botones){
        reiniciar();
        
        if(pnlBitsBotones != null){
            pnlBitsBotones.setVisible(false);
            pnlBitsBotones = null;
        }
        
        pnlBitsBotones = new JPanel();
        
        btnEliminarBits.setEnabled(true);
        btnAgregarBits.setEnabled(true);
        
        pnlBitsBotones = new JPanel();
        for(int i=0;i<botones;i++){
            JPanel btnBit = new BotonBit().crear(this, i+1);
            pnlBitsBotones.add(btnBit,"Center");
            dto.getBitsOriginales().add(false);
        }
        
        if(dto.getBitsOriginales().size() == Globales.MIN_BITS)
            btnEliminarBits.setEnabled(false);
        if(dto.getBitsOriginales().size() == Globales.MAX_BITS)
            btnAgregarBits.setEnabled(false);
        
        if(cmbPosicion != null){
            cmbPosicion.setVisible(false);
            cmbPosicion = null;
        }
        
        cmbPosicion = new JComboBox();
        cmbPosicion.setModel(obtenerModeloCombo());
        cmbPosicion.setBounds(150,90,50,24);
        pnlCentral.add(cmbPosicion);
        
        actualizarValores();
                
        pnlBitsExternoSur.add(pnlBitsBotones,"Center");
    }
    
    public void actualizarValores(){
        escribirEstadisticas();
        
        escribirCodigoOriginal();
        escribirHamming();
        dto.getBitsHammingCorrupto().clear();
        escribirHammingCorrupto();
        
        btnGenerarHamming.setEnabled(true);
        btnVerHammingCorrupto.setEnabled(false);
        btnDetectarError.setEnabled(false);
        cmbPosicion.setEnabled(false);
    }
    
    private void escribirEstadisticas(){
        int longitudOriginal = dto.getBitsOriginales().size();
        int cantidadBitsParidad = Globales.obtenerNumeroDeBitsDeParidad(longitudOriginal);
        int longitudTotal = longitudOriginal + cantidadBitsParidad;
        
        txtBitsOriginales.setText(String.valueOf(longitudOriginal));
        txtBitsParidad.setText(String.valueOf(cantidadBitsParidad));
        txtLongitud.setText(String.valueOf(longitudTotal));
    }
    
    private void escribirCodigoOriginal(){
        txtCodigoOriginal.setText("");

        for(int i=0; i<dto.getBitsOriginales().size(); i++)
                agregarTextoPanel(txtCodigoOriginal,dto.getBitsOriginales().get(i)==true?"1":"0", false);
    }
    
    private void escribirHamming(){
        txtHamming.setText("");
        dto.getBitsHamming().clear();
        
        ArrayList<Boolean> copiaBitsOriginales = new ArrayList<>();
        copiaBitsOriginales.addAll(dto.getBitsOriginales());
        
        dto.setBitsHamming(copiaBitsOriginales);
        for(int i=0; i<dto.getBitsHamming().size(); i++){
            if(Globales.esPotenciaDe2(i+1)){
                dto.getBitsHamming().add(i,true);
                agregarTextoPanel(txtHamming,"_", true);
            }else
                agregarTextoPanel(txtHamming,dto.getBitsHamming().get(i)==true?"1":"0", false);
        }
    }
    
    private void escribirHammingCorrupto(){
        txtHammingCorrupto.setText("");
        
        for(int i=0; i<dto.getBitsHammingCorrupto().size(); i++){
            String bit=dto.getBitsHammingCorrupto().get(i)==true?"1":"0";
            if(i == cmbPosicion.getSelectedIndex()){
                dto.getBitsHammingCorrupto().set(i,true);
                agregarTextoPanel(txtHammingCorrupto,bit, true,Color.RED);
            }else
                agregarTextoPanel(txtHammingCorrupto,bit, false);
        }
        
    }
    
    private void descubrirHamming(){
        txtHamming.setText("");
        
        for(int i=0; i<dto.getBitsHamming().size(); i++)
            agregarTextoPanel(txtHamming,dto.getBitsHamming().get(i)==true?"1":"0", Globales.esPotenciaDe2(i+1));
        
        btnGenerarHamming.setEnabled(false);
        btnVerHammingCorrupto.setEnabled(true);
        
        obtenerModeloCombo();
        cmbPosicion.setEnabled(true);
    }

    private void reiniciar(){
        dto.setBitsOriginales(new ArrayList<Boolean>());
        dto.setBitsHamming(new ArrayList<Boolean>());
        dto.setBitsHammingCorrupto(new ArrayList<Boolean>());
    }
    
    private void mostrarMsj(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje,Globales.NOMBRE_APLICACION,JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void salir(){
        System.exit(0);
    }
    
    public HammingDTO getDto() {
        return dto;
    }
}