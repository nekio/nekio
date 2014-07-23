package nekio.myprp.recursos.herramientas;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
// </editor-fold>

public class ConsolaDebug extends JFrame{
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private static final long serialVersionUID = 1L;
    
    private JMenuBar mnuBarra;
    private static JMenu mnSistema;
    private JMenuItem mnItCerrarSistema;
    
    private static JTextPane txtConsola;
    private static JCheckBox chkFrente;
    private static boolean activa;
    
    public static Color SQL = Color.WHITE;
    public static Color PROCESO = Color.CYAN;
    public static Color MAPEO = Color.BLUE;  
    public static Color VISTA = Color.YELLOW;
    public static Color COMODIN = Color.GREEN;
    public static Color BITACORA = Color.MAGENTA;
    
    public static Color OCULTO = Color.BLACK;
    public static Color ERROR = Color.RED;
    public static Color ADVERTENCIA = Color.ORANGE;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    static{
        activa = false;
    }
    
    public ConsolaDebug(){
        if(!activa){
            activa = true;
            
            agregarMenu();
            agregarComponentes();
            agregarEscuchadores();
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Agregar Menu">
    private void agregarMenu(){
        mnuBarra=new JMenuBar();
        mnSistema=new JMenu(Idioma.obtenerTexto(Idioma.PROP_MENU, "opciones"));
        mnSistema.setMnemonic('x');
            mnItCerrarSistema=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "cerrarSistema"));
            mnSistema.add(mnItCerrarSistema);
            
        mnuBarra.add(mnSistema);
        this.setJMenuBar(mnuBarra);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Agregar Componentes">
    private void agregarComponentes(){
        this.setTitle(Globales.NOMBRE_APP);
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(700,500));
        this.setAlwaysOnTop(true);
                
        txtConsola = new JTextPane();
        txtConsola.setEditable(false);
        txtConsola.setBackground(Color.BLACK);
        
        JScrollPane scrollCaja = new JScrollPane(txtConsola);
        scrollCaja.setOpaque(false);
        
        chkFrente = new JCheckBox(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "frente"), true);
        
        ConsolaDebug.agregarTexto(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "consola") + "\n", Color.LIGHT_GRAY);
        this.add(scrollCaja, "Center");
        this.add(chkFrente, "South");
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Agregar Escuchadores">
    private void agregarEscuchadores(){
        mnItCerrarSistema.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){       
                System.exit(0);
            }
        });
        
        chkFrente.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                siempreAlFrente(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
                
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                setVisible(false);
            }
        });
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos">
    
    // <editor-fold defaultstate="collapsed" desc="Agregar Texto">
    public static void agregarTexto(){
        agregarTexto("\n", Color.BLACK, false);
    }
    
    public static void agregarTexto(String texto){
        agregarTexto(texto, COMODIN);
    }
    
    public static void agregarTexto(String texto, Color color){
        agregarTexto(texto, color, true);
    }
    
    public static void agregarTexto(String texto, Color color, boolean momento){
        txtConsola.setEditable(true);  
        
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet as = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        as = sc.addAttribute(as, StyleConstants.FontFamily, "Courier New");
        as = sc.addAttribute(as, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        as = sc.addAttribute(as, StyleConstants.Bold, Boolean.TRUE);
        
        String fecha = "";
        String salto = "";
        if(momento){
            String inicialies = Globales.NOMBRE_APP + "> ";
            fecha = inicialies + "[" + Fecha.obtenerFechaFormateada(new Date(), Fecha.FORMATO_COMPLETO + "]\n");
            salto = "\n\n";
        }

        int longitud = txtConsola.getDocument().getLength();
        txtConsola.setCaretPosition(longitud);
        txtConsola.setCharacterAttributes(as, false);
        txtConsola.replaceSelection(fecha + texto + salto);
        txtConsola.setEditable(false);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Siempre al frente">
    private void siempreAlFrente(boolean siempre){
        this.setAlwaysOnTop(siempre);
        
        if(!siempre)
            this.toBack();
    }
    // </editor-fold>
    
    // </editor-fold>
}
