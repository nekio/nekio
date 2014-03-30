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
    private JButton btnGeneradora;
    private JButton btnVerificadora;
    private JButton btnGenerarCodeword;
    private JButton btnVerCodewordCorrupto;
    private JButton btnDetectarError;
    
    private JTextPane txtCodigoOriginal;
    private JTextPane txtHamming;
    private JComboBox cmbPosicion;
    private JTextPane txtHammingCorrupto;
    private JTextField txtBitsOriginales;
    private JTextField txtBitsParidad;
    private JTextField txtLongitud;
    private JTextField txtSyndrome;
    
    private int longitudOriginal;
    private int cantidadBitsParidad;
    private int longitudTotal;
    
    public GUI(){
        this(null);
    }
    
    public GUI(HammingDTO dto){
        super(Globales.NOMBRE_APLICACION);
        
        this.dto = dto;
        
        contenedor = this.getContentPane();
        Dimension dimensionesMinimas=new Dimension(750,370);

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
        
        JLabel lblCodHamming = new JLabel("Codeword:");
        lblCodHamming.setBounds(10,50,100,24);
        pnlCentral.add(lblCodHamming);
        
        txtHamming = new JTextPane();
        txtHamming.setBounds(150,50,300,24);
        pnlCentral.add(txtHamming);
        
        JLabel lblPosicionError = new JLabel("Posicion de error:");
        lblPosicionError.setBounds(10,90,130,24);
        pnlCentral.add(lblPosicionError);
        
        btnGeneradora = new JButton("Generadora");
        btnGeneradora.setBounds(210,90,115,24);
        pnlCentral.add(btnGeneradora);
        
        btnVerificadora = new JButton("Verificadora");
        btnVerificadora.setBounds(335,90,115,24);
        pnlCentral.add(btnVerificadora);
        
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
        
        JLabel lblSyndrome = new JLabel("Syndrome:");
        lblSyndrome.setBounds(550,130,100,24);
        pnlCentral.add(lblSyndrome);
        
        txtSyndrome = new JTextField();
        txtSyndrome.setBounds(670,130,50,24);
        txtSyndrome.setEditable(false);
        pnlCentral.add(txtSyndrome);
        
        /*Panel Botones*/
        pnlBotones = new JPanel();
        
        btnGenerarCodeword = new JButton("Generar Hamming");
        pnlBotones.add(btnGenerarCodeword);
        
        btnVerCodewordCorrupto = new JButton("Ver Hamming Corrupto");
        btnVerCodewordCorrupto.setEnabled(false);
        pnlBotones.add(btnVerCodewordCorrupto);
        
        btnDetectarError = new JButton("Detectar Error");
        btnDetectarError.setEnabled(false);     
        pnlBotones.add(btnDetectarError);
        
        pnlCentral.setBackground(Color.LIGHT_GRAY);
        
        /*Panel Bits*/
        pnlBitsExterno = new JPanel(new BorderLayout());
        
        pnlBitsExternoNorte = new JPanel();
        btnEliminarBits = new JButton("4 Bits");
        btnAgregarBits = new JButton("11 Bits");
        pnlBitsExternoNorte.add(btnEliminarBits,"East");
        pnlBitsExternoNorte.add(btnAgregarBits,"West");
        
        pnlBitsExternoSur = new JPanel(new BorderLayout());
        definirBitsOriginales(Globales.MIN_BITS);
        
        JLabel lblCodeword = new JLabel(" ");
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
                eliminarBits();
                reiniciar();
                btnGenerarCodeword.setEnabled(true);
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
                eliminarBits();
            }
        });
        
        btnAgregarBits.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                agregarBits();
            }
        });
        
        btnGenerarCodeword.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                generarCodeword();
            }
        });
        
        btnVerCodewordCorrupto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                generarCodewordCorrupto();                
            }
        });
        
        btnDetectarError.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                detectarError();                
            }
        });
        
        btnGeneradora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String generadora="";
                
                for(int i=0; i<longitudOriginal; i++){
                    for(int j=0; j<longitudTotal; j++)
                        generadora += dto.getMatrizGeneradora()[i][j];
                    generadora += "\n";
                }
                    
                mostrarMsj("Matriz Generadora:\n\n" + generadora);
            }
        });
        
        btnVerificadora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String verificadora="";
                
                for(int i=0; i<Globales.obtenerNumeroDeBitsDeParidad(longitudOriginal); i++){
                    for(int j=0; j<longitudTotal; j++)
                        verificadora += dto.getCheckmatrix()[i][j];
                    verificadora += "\n";
                }
                    
                mostrarMsj("Matriz Verificadora:\n\n" + verificadora);
            }
        });
        
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    /*METODOS ASOCIADOS A LOS ESCUCHADORES DE LOS BOTONES*/
    private void generarCodeword(){
        hamming = new Hamming(dto);
        
        hamming.definirValores(dto.getVector());
        hamming.obtenerCodeword();
        mostrarMsj("MULTIPLICACION DEL VECTOR ORIGINAL POR LA MATRIZ GENERADORA:\n\n"+
                dto.getVectorXgeneradorProceso()+"\n\n"+
                "CONTINUA...");
        
        mostrarMsj("OBTENCIÓN DE CODEWORD: \n\n"+
                dto.getCodewordProceso());
        
        escribirHamming();
    }
    
    private void escribirHamming(){
        txtHamming.setText("");
        
        for(int i=0; i<dto.getCodeword().length; i++){
            if(i >= dto.getColumnasVector())
                agregarTextoPanel(txtHamming,dto.getCodeword()[i]==1?"1":"0", true);
            else
                agregarTextoPanel(txtHamming,dto.getCodeword()[i]==1?"1":"0", false);
        }
        
        btnGenerarCodeword.setEnabled(false);
        btnVerCodewordCorrupto.setEnabled(true);
        
        obtenerModeloCombo();
        cmbPosicion.setEnabled(true);
    }
    
    private void generarCodewordCorrupto(){
        int posicionError = (int)cmbPosicion.getSelectedItem();

        hamming.corromperBit(posicionError);
        
        escribirHammingCorrupto();
        
        cmbPosicion.setEnabled(false);
        btnVerCodewordCorrupto.setEnabled(false);
        btnDetectarError.setEnabled(true);
    }
    
    private void escribirHammingCorrupto(){
        txtHammingCorrupto.setText("");
        
        for(int i=0; i<dto.getCodewordCorrupto().length; i++){
            String bit=dto.getCodewordCorrupto()[i]==1?"1":"0";
            if(i == cmbPosicion.getSelectedIndex()){
                agregarTextoPanel(txtHammingCorrupto,bit, true,Color.RED);
            }else
                agregarTextoPanel(txtHammingCorrupto,bit, false);
        }
    }
    
    private void detectarError(){        
        hamming.detectarError();
        
        mostrarMsj("MULTIPLICACION DE LA MATRIZ DE VERIFICACION POR EL CODEWORD CORRUPTO: \n\n"+
                dto.getDeteccionErrorProceso());
        mostrarMsj("OBTENCIÓN DE SYNDROME: \n\n"+
                dto.getSyndromeProceso());
        
        String syndrome="";
        for(int i=0; i<dto.getSyndrome().length; i++)
            syndrome += String.valueOf(dto.getSyndrome()[i]);
                    
        txtSyndrome.setText(syndrome);
        
        mostrarMsj("El algoritmo ha calculado el error en el indice: "+hamming.obtenerPosicionError());
    }
    
    /*METODOS PARA OPERACIONES DE LA GUI*/
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
    
    private void eliminarBits(){
        int bits = Globales.MIN_BITS;
        
        btnAgregarBits.setEnabled(true);
        btnEliminarBits.setEnabled(false);
        
        dto.setColumnasVector(bits);
        definirBitsOriginales(bits);
    }
    
    private void agregarBits(){
        int bits = Globales.MAX_BITS;
        
        btnAgregarBits.setEnabled(false);
        btnEliminarBits.setEnabled(true);
        
        dto.setColumnasVector(bits);
        definirBitsOriginales(bits);
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
            dto.getVector()[i]=0;
        }
        
        if(dto.getVector().length == Globales.MIN_BITS)
            btnEliminarBits.setEnabled(false);
        if(dto.getVector().length == Globales.MAX_BITS)
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

        dto.setCodewordCorrupto(new int[dto.getColumnasCodeword()]);
        
        btnGenerarCodeword.setEnabled(true);
        btnVerCodewordCorrupto.setEnabled(false);
        btnDetectarError.setEnabled(false);
        cmbPosicion.setEnabled(false);
    }
    
    private void escribirEstadisticas(){
        obtenerLongitudes();
        
        txtBitsOriginales.setText(String.valueOf(longitudOriginal));
        txtBitsParidad.setText(String.valueOf(cantidadBitsParidad));
        txtLongitud.setText(String.valueOf(longitudTotal));
        txtSyndrome.setText("");
        
        dto.setFilasVector(cantidadBitsParidad);
        if(longitudTotal==7){
            dto.setMatrizGeneradora(Globales.MATRIZ_GENERADORA_7X4);
            dto.setCheckmatrix(Globales.CHECKMATRIX_7X4);
        }else if(longitudTotal==15){
            dto.setMatrizGeneradora(Globales.MATRIZ_GENERADORA_15X11);
            dto.setCheckmatrix(Globales.CHECKMATRIX_15X11);
        }
    }
    
    private void escribirCodigoOriginal(){
        txtCodigoOriginal.setText("");
        txtHamming.setText("");
        txtHammingCorrupto.setText("");

        for(int i=0; i<dto.getVector().length; i++)
                agregarTextoPanel(txtCodigoOriginal,dto.getVector()[i]==1?"1":"0", false);
    }

    private void reiniciar(){
        obtenerLongitudes();
   
        if(longitudOriginal==0){
            longitudOriginal=Globales.MIN_BITS;
            dto.setColumnasVector(longitudOriginal);
        }
        
        dto.setVector(new int[longitudOriginal]);
        dto.setCodeword(new int[longitudTotal]);
        dto.setCodewordCorrupto(new int[longitudTotal]);
    }
    
    private void obtenerLongitudes(){
        longitudOriginal = dto.getColumnasVector();
        cantidadBitsParidad = Globales.obtenerNumeroDeBitsDeParidad(longitudOriginal);
        longitudTotal = longitudOriginal + cantidadBitsParidad;
    }
    
    private DefaultComboBoxModel obtenerModeloCombo(){
        DefaultComboBoxModel mdlCombo = new DefaultComboBoxModel();
        obtenerLongitudes();
                
        for(int i=0; i<longitudTotal; i++)
            mdlCombo.addElement(i+1);
        
        return mdlCombo;
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