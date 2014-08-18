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
import nekio.myprp.recursos.img.obj.ImagenGestor;
import nekio.myprp.recursos.utilerias.Globales;
import static nekio.myprp.recursos.utilerias.Globales.BD_ESQUEMA_SERIES;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.gui.swing.MenuModulo;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;
import nekio.myprp.sistema.modulos.series.MensajePrivadoGestor;

public class Menu extends SwingJFrame{
    private static final long serialVersionUID = 1L;
    private final Dimension DIMENSION = new Dimension(720, 550);
    
    private Container contenedor;
    
    private JMenuBar mnuBarra;
    private JMenu mnuArchivo;
    private JMenuItem mnItUsuario;
    private JMenuItem mnItImagen;
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
    private JMenuItem mnItDebug;
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
            mnItUsuario=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "usuario"));
            mnuArchivo.add(mnItUsuario);
            
            mnItImagen=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "imagen"));
            mnuArchivo.add(mnItImagen);
        
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
            
            mnItDebug=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "consola"));
            if(Globales.APP_DEBUG)
                mnuAyuda.add(mnItDebug);
            
            
            mnItAcercaDe=new JMenuItem(Idioma.obtenerTexto(Idioma.PROP_MENU, "acercaDe"));
            mnuAyuda.add(mnItAcercaDe);
            
        mnuBarra.add(mnuArchivo);
        mnuBarra.add(mnuModulos);
        mnuBarra.add(mnuAyuda);
        setJMenuBar(mnuBarra);
    }

    @Override
    public void agregarEscuchadores() {
        mnItImagen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){       
                ImagenGestor gestor = new ImagenGestor();
                gestor.setEsquemaBD(Globales.BD_TOOLS);
                gestor.ejecutarControladorNegocio(Globales.BD.LEER_DESC.getValor(), Globales.Entidad.Imagen.name());
                gestor = null;
            }
        });
        
        mnItUsuario.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){       
                new UsuarioSwing();
            }
        });
                
        mnItSeries.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){       
                String modulo = Idioma.obtenerTexto(Idioma.PROP_MENU, "series");
                
                MensajePrivadoGestor gestor = new MensajePrivadoGestor();
                gestor.setEsquemaBD(BD_ESQUEMA_SERIES);
                
                List<String> entradas = new ArrayList<String>();
                List<String> acciones = new ArrayList<String>();
                List<String> entidades = new ArrayList<String>();
                
                entradas.add(Idioma.obtenerTexto(Idioma.PROP_MENU, "mensajePrivado"));                
                acciones.add(Globales.BD.LEER_DESC.getValor());
                entidades.add(Globales.Entidad.MensajePrivado.name());
                
                new MenuModulo(modulo, gestor, entradas, acciones, entidades, null);
            }
        });
        
        mnItDebug.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){                       
                Globales.CONSOLA.setVisible(true);
            }
        });
        
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                cerrarAplicacion();                
            }
        });
    }
    
    private void cerrarAplicacion(){
        Globales.CONSOLA.setVisible(false);
        Globales.CONSOLA.dispose();
        
        salir();
    }
}
