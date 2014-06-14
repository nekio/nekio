package nekio.myprp.sistema.acceso.vista;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.gui.swing.MenuModulo;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;
import nekio.myprp.sistema.modulos.series.GestorMensajePrivado;

public class Menu extends SwingJFrame{
    private static final long serialVersionUID = 1L;
    private final Dimension DIMENSION = new Dimension(720, 550);
    
    private Container contenedor;
    
    private JMenuBar mnuBarra;
    private JMenu mnuArchivo;
    private JMenu mnuModulos;
    private JMenuItem mnItAcademico;
    private JMenuItem mnItDeportes;
    private JMenuItem mnItEspiritual;
    private JMenuItem mnItFinanciero;
    private JMenuItem mnItLaboral;
    private JMenuItem mnItSalud;
    private JMenuItem mnItSocial;
    private JMenuItem mnItSeries;
    private JMenu mnuAyuda;
    private JMenuItem mnItVersion;
    private JMenuItem mnItAcercaDe;
    
    
    public Menu(){
        this.setTitle(Globales.TITULO_APP);
        this.contenedor = this.getContentPane();
        this.setSize(DIMENSION);
        this.setMinimumSize(DIMENSION);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
    }

    @Override
    public void agregarComponentes() {
        agregarMenu();
    }
    
    private void agregarMenu(){
        mnuBarra=new JMenuBar();

        /*Archivo*/
        mnuArchivo=new JMenu(Idioma.obtenerTexto(Idioma.PROP_MENU, "archivo"));
        mnuArchivo.setMnemonic('a');
        
        /*Modulos*/
        mnuModulos = new JMenu(Idioma.obtenerTexto(Idioma.PROP_MENU, "modulos"));
        mnuModulos.setMnemonic('m');
            mnItAcademico=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "academico"));
            mnuModulos.add(mnItAcademico);
            
            mnItDeportes=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "deportes"));
            mnuModulos.add(mnItDeportes);
            
            mnItEspiritual=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "espiritual"));
            mnuModulos.add(mnItEspiritual);
            
            mnItFinanciero=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "financiero"));
            mnuModulos.add(mnItFinanciero);
            
            mnItLaboral=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "laboral"));
            mnuModulos.add(mnItLaboral);
            
            mnItSalud=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "salud"));
            mnuModulos.add(mnItSalud);
            
            mnItSocial=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "social"));
            mnuModulos.add(mnItSocial);
            
            mnItSeries=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "series"));                
            mnuModulos.add(mnItSeries);
            
       /*Ayuda*/
        mnuAyuda=new JMenu(Idioma.obtenerTexto(Idioma.PROP_MENU, "ayuda"));
        mnuAyuda.setMnemonic('h');
            mnItVersion=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "version"));
            mnuAyuda.add(mnItVersion);
            
            mnItAcercaDe=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "acercaDe"));
            mnuAyuda.add(mnItAcercaDe);
            
        mnuBarra.add(mnuArchivo);
        mnuBarra.add(mnuModulos);
        mnuBarra.add(mnuAyuda);
        setJMenuBar(mnuBarra);
    }

    @Override
    public void agregarEscuchadores() {
        mnItSeries.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){       
                String modulo = Idioma.obtenerTexto(Idioma.PROP_MENU, "series");
                GestorMensajePrivado gestor = new GestorMensajePrivado();
                
                List<String> entradas = new ArrayList<String>();
                List<String> acciones = new ArrayList<String>();
                List<String> entidades = new ArrayList<String>();
                
                entradas.add(Idioma.obtenerTexto(Idioma.PROP_MENU, "mensajePrivado"));                
                acciones.add(Globales.BD.LEER_DESC.getValor());
                entidades.add(Globales.Entidad.MensajePrivado.name());
                
                new MenuModulo(modulo, gestor, entradas, acciones, entidades, null);
            }
        });
        
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
}
