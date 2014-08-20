package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJPanel;

public class PanelFormulario extends SwingJPanel{
    private static final long serialVersionUID = 1L;

    private String esquemaBD;
    private List<String> tablasForaneas;
    private List<String> camposBD;
    private List valoresBD;
    private List<Globales.TipoDato> tiposDatoBD;
    private List<String> valoresLOV;
    private List<List> camposExtrasLOV;
    private boolean nuevo;
    
    private List registrosLlave;
    private List registrosNoLlave;
    private List registrosLlaveValor;
    private List registrosNoLlaveValor;
    private List objetosCampos;
    
    public PanelFormulario(){
        this(null, null, null, null);
    }
    
    public PanelFormulario(List<String> camposBD, List valoresBD, List<Globales.TipoDato> tiposDatoBD, List<String> valoresLOV){
        this(camposBD, valoresBD, tiposDatoBD, valoresLOV, null);
    }
    
    public PanelFormulario(List<String> camposBD, List valoresBD, List<Globales.TipoDato> tiposDatoBD, List<String> valoresLOV, String esquemaBD){
        this(null, camposBD, valoresBD, tiposDatoBD, valoresLOV, null, esquemaBD);
    }
    
    public PanelFormulario(List<String> tablasForaneas, List<String> camposBD, List valoresBD, List<Globales.TipoDato> tiposDatoBD, List<String> valoresLOV, List<List> camposExtrasLOV, String esquemaBD){
        this(tablasForaneas, camposBD, valoresBD, tiposDatoBD, valoresLOV, camposExtrasLOV, esquemaBD, false);
    }
    
    public PanelFormulario(List<String> tablasForaneas, List<String> camposBD, List valoresBD, List<Globales.TipoDato> tiposDatoBD, List<String> valoresLOV, List<List> camposExtrasLOV, String esquemaBD, boolean nuevo){
        this.tablasForaneas = tablasForaneas;
        this.camposBD = camposBD;
        this.valoresBD = valoresBD;
        this.tiposDatoBD = tiposDatoBD;
        this.valoresLOV = valoresLOV;
        this.camposExtrasLOV = camposExtrasLOV;
        this.esquemaBD = esquemaBD;
        this.nuevo = nuevo;
        
        inicializarPanel();
    }
    
    private void inicializarPanel(){
        this.setLayout(new BorderLayout());
        
        if(camposBD != null){
            identificarRegistros();
            agregarComponentes();
            agregarEscuchadores();
        }
        
        this.setVisible(true);
    }
    
    @Override
    public void agregarComponentes(){
        JPanel pnlTabla = new JPanel(new BorderLayout());
        
        /* PANEL DE LLAVES*/
        JPanel pnlLlaves = new JPanel(new BorderLayout());
        pnlLlaves.add(new JLabel("  Llaves:"),"North");
        pnlLlaves.add(agregarLlaves(),"Center");
        pnlTabla.add(pnlLlaves,"North");
        
        JPanel pnlCampos = new JPanel(new BorderLayout());
        pnlCampos.add(new JLabel("  Campos:"),"North");
        pnlCampos.add(agregarCampos());
        pnlTabla.add(pnlCampos, "Center");
        
        this.add(pnlTabla,"Center");
    }
    
    @Override
    public void agregarEscuchadores() {
        
    }
    
    private Box agregarLlaves(){       
        String campo = null;
        String tablaForanea = null;
        Object valor = null;
        Globales.TipoDato tipoDato = null;
        String valorLOV = null;
        List<String> camposExtraLOV = null;
        
        int campos = registrosLlave.size();
        
        JPanel pnlLlaves = new JPanel(); 
        pnlLlaves.setLayout(new BoxLayout(pnlLlaves, BoxLayout.Y_AXIS));
        Box caja=Box.createVerticalBox();
        caja.add(new JScrollPane(pnlLlaves));

        PanelCampo pnlLlave = null;
        for(int i=0; i<campos; i++){
            campo = String.valueOf(registrosLlave.get(i));
            valor = registrosLlaveValor.get(i);
            tipoDato = tiposDatoBD.get(i);
            
            // El indice de los valores LOV esta desfazado en una posicion
            // respecto del indice en los registros de Llaves
            if(i!=0){
                try{
                    valorLOV = valoresLOV.get(i-1); 
                }catch(Exception e){}
                
                try{
                    camposExtraLOV = camposExtrasLOV.get(i-1);
                }catch(Exception e){}
                
                try{
                    tablaForanea = tablasForaneas.get(i-1);
                }catch(Exception e){}
            }
            
            pnlLlave = new PanelCampo(tablaForanea, campo, valor, tipoDato, true, valorLOV, esquemaBD, camposExtraLOV, nuevo);
            pnlLlaves.add(pnlLlave, Component.CENTER_ALIGNMENT);
            
            objetosCampos.add(pnlLlave.obtenerObjeto());
        }        
        
        return caja;
    }  
    
    private Box agregarCampos(){
        String campo = null;
        Object valor = null;
        Globales.TipoDato tipoDato = null;

        int llaves = this.registrosLlave.size();
        int campos = this.registrosNoLlave.size();
        
        JPanel pnlCampos = new JPanel();
        pnlCampos.setLayout(new BoxLayout(pnlCampos, BoxLayout.Y_AXIS));
        Box caja=Box.createVerticalBox();
        caja.add(new JScrollPane(pnlCampos));

        PanelCampo pnlCampo = null;
        for(int i=0; i<campos; i++){
            campo = String.valueOf(registrosNoLlave.get(i));
            valor = registrosNoLlaveValor.get(i);
            tipoDato = tiposDatoBD.get(llaves+i);
            
            pnlCampo = new PanelCampo(campo, valor, tipoDato, false, esquemaBD, nuevo);
            pnlCampos.add(pnlCampo, Component.CENTER_ALIGNMENT);
            
            objetosCampos.add(pnlCampo.obtenerObjeto());
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
        
        objetosCampos = new ArrayList();
        
        for(int i=0; i<camposBD.size(); i++){
            campo = camposBD.get(i);
            
            try{
                valor = valoresBD.get(i);
            }catch(Exception e){}
            
            if(campo.toUpperCase().startsWith(Globales.BD_TABLA_ID.toUpperCase())){
                registrosLlave.add(campo);
                registrosLlaveValor.add(valor);
            }else{
                registrosNoLlave.add(campo);
                registrosNoLlaveValor.add(valor);
            }
        }  
    }

    public List getObjetosCampos() {
        return objetosCampos;
    }
}