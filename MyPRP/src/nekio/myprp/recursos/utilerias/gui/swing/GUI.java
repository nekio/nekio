package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import nekio.myprp.recursos.utilerias.Globales;

public class GUI extends JFrame{
    private static final long serialVersionUID = 1L;
    
    private Container contenedor;
    private String titulo;
    private List camposBD;
    private List valoresBD;
    private List<Integer> tiposDatoBD;
    private List registrosLlave;
    private List registrosNoLlave;
    private List registrosLlaveValor;
    private List registrosNoLlaveValor;
    private Dimension dimension;
    
    public GUI(){
        this("Swing GUI", null, null, null);
    }
    
    public GUI(String titulo, ArrayList camposBD, ArrayList valoresBD, ArrayList<Integer> tiposDatoBD){
        this(titulo, camposBD, valoresBD, tiposDatoBD, new Dimension(640,480));
    }
    
    public GUI(String titulo, List camposBD, List valoresBD, List<Integer> tiposDatoBD, Dimension dimension){
        super(Globales.NOMBRE_APP + ": " + titulo);
        
        this.titulo = titulo;
        this.camposBD = camposBD;
        this.valoresBD = valoresBD;
        this.tiposDatoBD = tiposDatoBD;
        this.dimension = dimension;
        
        inicializarGUI();
    }
    
    private void inicializarGUI(){        
        this.contenedor = this.getContentPane();
        this.setSize(dimension);
        this.setMinimumSize(dimension);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        identificarRegistros();
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
    }
    
    private void agregarComponentes(){
        /* PANEL DE LLAVES*/
        JPanel pnlLlaves = new JPanel(new BorderLayout());
        pnlLlaves.setBounds(10, 10, 350, 150);
        pnlLlaves.add(new JLabel("  Llaves:"),"North");
        pnlLlaves.add(agregarLlaves(),"Center");
        contenedor.add(pnlLlaves);

        JPanel pnlCampos = new JPanel(new BorderLayout());
        pnlCampos.setBounds(10, 180, 450, 250);
        pnlCampos.add(new JLabel("  Campos:"),"North");
        pnlCampos.add(agregarCampos());
        contenedor.add(pnlCampos);
        
        agregarDMLs();
        agregarNavegacion();
    }
    private void agregarEscuchadores(){
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private Box agregarLlaves(){       
        String campo = null;
        Object valor = null;
        int tipoDato = 0;
        int campos = registrosLlave.size();
        
        JPanel pnlLlaves = new JPanel(new GridLayout(campos+1,1)); 
        Box caja=Box.createVerticalBox();
        caja.add(new JScrollPane(pnlLlaves));

        JPanel pnlLlave = null;
        for(int i=0; i<campos; i++){
            campo = String.valueOf(registrosLlave.get(i));
            valor = registrosLlaveValor.get(i);
            tipoDato = Integer.valueOf(String.valueOf(tiposDatoBD.get(i)));
            
            pnlLlave = new PanelCampo().crear(campo, valor, tipoDato, true);

            pnlLlaves.add(pnlLlave);
        }        
        
        return caja;
    }  
    
    private Box agregarCampos(){
        String campo = null;
        Object valor = null;
        int tipoDato = 0;

        int campos = this.registrosNoLlave.size();
        
        JPanel pnlCampos = new JPanel(new GridLayout(campos,1)); 
        Box caja=Box.createVerticalBox();
        caja.add(new JScrollPane(pnlCampos));

        JPanel pnlCampo = null;
        for(int i=0; i<campos; i++){
            campo = String.valueOf(registrosNoLlave.get(i));
            valor = registrosNoLlaveValor.get(i);
            tipoDato = Integer.valueOf(String.valueOf(tiposDatoBD.get(i)));
            
            pnlCampo = new PanelCampo().crear(campo, valor, tipoDato, false);

            pnlCampos.add(pnlCampo);
        }        
        
        return caja;
    }
    
    private void agregarDMLs(){}
    private void agregarNavegacion(){}
    
    private void identificarRegistros(){        
        String campo = null;
        Object valor = null;
        
        registrosLlave = new ArrayList();
        registrosLlaveValor = new ArrayList();
        registrosNoLlave = new ArrayList();
        registrosNoLlaveValor = new ArrayList();
        
        for(int i=0; i<camposBD.size(); i++){
            campo = String.valueOf(camposBD.get(i));
            valor = valoresBD.get(i);
            
            if(campo.toUpperCase().startsWith("ID_")){
                registrosLlave.add(campo);
                registrosLlaveValor.add(valor);
            }else{
                registrosNoLlave.add(campo);
                registrosNoLlaveValor.add(valor);
            }
        }  
    }
    
    private void dibujarComponente(Component componente){}
    private void asociarLOV(){}
    
    private void salir(){
        this.dispose();
    }   
}