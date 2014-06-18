package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJPanel;

public class PanelGUI extends SwingJPanel{
    private static final long serialVersionUID = 1L;

    private List<String> camposBD;
    private List valoresBD;
    private List<Globales.TipoDato> tiposDatoBD;
    private List<String> valoresLOV;
    
    private List registrosLlave;
    private List registrosNoLlave;
    private List registrosLlaveValor;
    private List registrosNoLlaveValor;
    
    public PanelGUI(){
        this(null, null, null, null);
    }
    
    public PanelGUI(List<String> camposBD, List valoresBD, List<Globales.TipoDato> tiposDatoBD, List<String> valoresLOV){
        this.camposBD = camposBD;
        this.valoresBD = valoresBD;
        this.tiposDatoBD = tiposDatoBD;
        this.valoresLOV = valoresLOV;
        
        inicializarPanel();
    }
    
    private void inicializarPanel(){
        this.setLayout(null);
        
        if(camposBD != null){
            identificarRegistros();
            agregarComponentes();
            agregarEscuchadores();
        }
        
        this.setVisible(true);
    }
    
    @Override
    public void agregarComponentes(){
        /* PANEL DE LLAVES*/
        JPanel pnlLlaves = new JPanel(new BorderLayout());
        pnlLlaves.setBounds(10, 10, 760, 190);
        pnlLlaves.add(new JLabel("  Llaves:"),"North");
        pnlLlaves.add(agregarLlaves(),"Center");
        this.add(pnlLlaves);

        JPanel pnlCampos = new JPanel(new BorderLayout());
        pnlCampos.setBounds(10, 210, 760, 330);
        pnlCampos.add(new JLabel("  Campos:"),"North");
        pnlCampos.add(agregarCampos());
        this.add(pnlCampos);
    }
    
    @Override
    public void agregarEscuchadores() {
        
    }
    
    private Box agregarLlaves(){       
        String campo = null;
        Object valor = null;
        Globales.TipoDato tipoDato = null;
        String valorLOV = null;
        
        int campos = registrosLlave.size();
        
        JPanel pnlLlaves = new JPanel(new GridLayout(campos+1,1)); 
        Box caja=Box.createVerticalBox();
        caja.add(new JScrollPane(pnlLlaves));

        JPanel pnlLlave = null;
        for(int i=0; i<campos; i++){
            campo = String.valueOf(registrosLlave.get(i));
            valor = registrosLlaveValor.get(i);
            tipoDato = tiposDatoBD.get(i);
            
            // El indice de los valores LOV esta desfazado en una posicion
            // respecto del indice en los registros de Llaves
            if(i!=0)
                valorLOV = valoresLOV.get(i-1); 
            
            pnlLlave = new PanelCampo().crear(campo, valor, tipoDato, true, valorLOV);

            pnlLlaves.add(pnlLlave);
        }        
        
        return caja;
    }  
    
    private Box agregarCampos(){
        String campo = null;
        Object valor = null;
        Globales.TipoDato tipoDato = null;

        int llaves = this.registrosLlave.size();
        int campos = this.registrosNoLlave.size();
        
        JPanel pnlCampos = new JPanel(new GridLayout(campos,1)); 
        Box caja=Box.createVerticalBox();
        caja.add(new JScrollPane(pnlCampos));

        JPanel pnlCampo = null;
        for(int i=0; i<campos; i++){
            campo = String.valueOf(registrosNoLlave.get(i));
            valor = registrosNoLlaveValor.get(i);
            tipoDato = tiposDatoBD.get(llaves+i);
            
            pnlCampo = new PanelCampo().crear(campo, valor, tipoDato, false);

            pnlCampos.add(pnlCampo);
        }        
        
        return caja;
    }
    
    private void identificarRegistros(){        
        String campo = null;
        Object valor = null;
        
        registrosLlave = new ArrayList();
        registrosLlaveValor = new ArrayList();
        registrosNoLlave = new ArrayList();
        registrosNoLlaveValor = new ArrayList();
        
        for(int i=0; i<camposBD.size(); i++){
            campo = camposBD.get(i);
            valor = valoresBD.get(i);
            
            if(campo.toUpperCase().startsWith(Globales.BD_TABLA_ID.toUpperCase())){
                registrosLlave.add(campo);
                registrosLlaveValor.add(valor);
            }else{
                registrosNoLlave.add(campo);
                registrosNoLlaveValor.add(valor);
            }
        }  
    }
}