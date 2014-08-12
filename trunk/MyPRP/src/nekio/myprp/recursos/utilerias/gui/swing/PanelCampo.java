package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import nekio.herramientas.listadevalores.dao.Listado;
import nekio.herramientas.listadevalores.dto.Elementos;
import nekio.herramientas.listadevalores.gui.SwingLOV;
import nekio.myprp.recursos.herramientas.Calendario;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.bd.BDConexion;

public class PanelCampo{
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private final int TEXTO_ID = 4;
    private final int TEXTO_CAMPO = 15;
    
    private String esquemaBD;
    private String tablaForanea;
    private String campo;
    private String valorLOV;
    private List<String> camposExtrasLOV;
    private boolean nuevo;

    private JTextField txtCampo;
    private JTextField txtCampoExtra;
    private JTextArea txtCaja;
    private JScrollPane scrollCaja;
    private JLabel lblImagen;
    private JButton btnLOV;
    private JButton btnFecha;
    private JCheckBox chkValor;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Crear">
    public JPanel crear(String campo, Object valor, boolean llave){
        return crear(campo, valor, Globales.TipoDato.TEXTO, llave);
    }

    public JPanel crear(String campo, Object valor, Globales.TipoDato tipoCampo, boolean llave){
        return crear(campo, valor, tipoCampo, llave, null);
    }
    
    public JPanel crear(String campo, Object valor, Globales.TipoDato tipoCampo, boolean llave, String valorLOV){
        return crear(campo, valor, tipoCampo, llave, valorLOV, false);
    }
    
    public JPanel crear(String campo, Object valor, Globales.TipoDato tipoCampo, boolean llave, String valorLOV, boolean nuevo){
        return crear(campo, valor, tipoCampo, llave, valorLOV, null, nuevo);
    }
    
    public JPanel crear(String campo, Object valor, Globales.TipoDato tipoCampo, boolean llave, String valorLOV, String esquemaBD, boolean nuevo){
        return crear(null, campo, valor, tipoCampo, llave, valorLOV, esquemaBD, null, nuevo);
    }
    
    public JPanel crear(String tablaForanea, String campo, Object valor, Globales.TipoDato tipoCampo, boolean llave, String valorLOV, String esquemaBD, List<String> camposExtrasLOV, boolean nuevo){
        this.esquemaBD = esquemaBD;
        this.tablaForanea = tablaForanea;
        this.campo = campo;
        this.valorLOV = valorLOV;
        this.camposExtrasLOV = camposExtrasLOV;
        this.nuevo = nuevo;
        
        JPanel pnlCampo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblCampo = new JLabel(campo + ":");
        pnlCampo.add(lblCampo);

        // Aunque no todos sean JTextField, se necesita instanciar siempre
        // para obtener el valor del campo
        if(valor == null)
            valor = "";
        
        txtCampo = new JTextField(TEXTO_CAMPO);
        txtCampo.setText(String.valueOf(valor));
        
        if(nuevo)
            txtCampo.setEditable(true);
        else
            txtCampo.setEditable(false);
        
        pnlCampo.add(txtCampo);

        if(llave && valorLOV!=null){
            txtCampo.setColumns(TEXTO_ID);
            
            txtCampoExtra = new JTextField(TEXTO_CAMPO);
            txtCampoExtra.setEditable(false);
            
            pnlCampo.add(txtCampoExtra);

            btnLOV = new JButton("...");
            pnlCampo.add(btnLOV);
            
            if(tablaForanea == null)
                tablaForanea = campo.replace(Globales.BD_TABLA_ID, "");
            String idFK = Globales.BD_TABLA_ID + tablaForanea;
            
            try{
                Elementos elementos = new Elementos();
                elementos.setConexion(BDConexion.getConnection());
                elementos.setLlave(idFK);
                elementos.setValor(valorLOV);
                elementos.setTabla(tablaForanea);
                elementos.setFiltros(idFK + "=" + ((int)valor));
            
                txtCampoExtra.setText(Listado.getRegistro(elementos, esquemaBD).getValor());
            }catch(Exception e){
                txtCampoExtra.setText("-");
            }
        }

        if(!llave && tipoCampo == Globales.TipoDato.FECHA){     
            try{
                txtCampo.setText(formatearFecha((Date)valor));
            }catch(Exception e){}
            
            btnFecha = new JButton("...");
            pnlCampo.add(btnFecha);
        }else if(!llave && tipoCampo == Globales.TipoDato.BOOLEANO){
            txtCampo.setVisible(false);
            
            chkValor = new JCheckBox();
            try{
                chkValor.setSelected((boolean)valor);
            }catch(Exception e){}
            
            pnlCampo.add(chkValor);
        }else if(!llave && tipoCampo == Globales.TipoDato.BLOB){
            txtCampo.setVisible(false);
            lblImagen = new JLabel();
            
            try{
                ImageIcon icon = new ImageIcon(BufferedImage.class.cast(valor)); 
                lblImagen.setIcon(icon);
            }catch(Exception e){}
            
            pnlCampo.add(lblImagen);
        }else if(!llave && tipoCampo == Globales.TipoDato.TEXTO_LARGO){
            txtCampo.setVisible(false);
            
            txtCaja = new JTextArea(String.valueOf(valor),10,55);
            txtCaja.setWrapStyleWord(true);
            txtCaja.setLineWrap(true);
            
            if(nuevo)
                txtCaja.setEditable(true);
            else
                txtCaja.setEditable(false);
            
            scrollCaja = new JScrollPane(txtCaja);
            scrollCaja.setOpaque(false);
            pnlCampo.add(scrollCaja);
        }

        escuchadores();

        return pnlCampo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Escuchadores">
    private void escuchadores(){
        if(btnLOV != null){
            btnLOV.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mouse){
                    int x=mouse.getXOnScreen();
                    int y=mouse.getYOnScreen();

                    Point punto = new Point(x,y);

                    llamarListaDeValores(punto);
                }
            });
        }

        if(btnFecha != null){
            btnFecha.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent mouse){
                    int x=mouse.getXOnScreen();
                    int y=mouse.getYOnScreen();

                    Point punto = new Point(x,y);

                    new Calendario(campo, txtCampo, punto);
                }
            });
        }

        if(lblImagen != null){
            lblImagen.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    if (e.getClickCount()==1){        
                        seleccionarImagen();
                    }
                }
            });
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Llamar Lista De Valores">
    private void llamarListaDeValores(Point punto){
        try{
            String tabla = null;
            if(tablaForanea == null)
                tabla = campo.replace(Globales.BD_TABLA_ID, "");
            else
                tabla = tablaForanea;
            
            String llaveLOV = campo;
            
            Elementos elementos = new Elementos();

            elementos.setConexion(BDConexion.getConnection());
            elementos.setLlave(llaveLOV);
            elementos.setValor(valorLOV);
            elementos.setCamposExtras(camposExtrasLOV);
            elementos.setTabla(tabla);
            elementos.setOrdendoPorLlave(true);
            
            ArrayList<JTextComponent> componentesTxt = new ArrayList<JTextComponent>();
            componentesTxt.add(txtCampo);
            componentesTxt.add(txtCampoExtra);
            
            String titulo = tabla;

            try{
                new SwingLOV(elementos, componentesTxt, titulo, punto, esquemaBD);
            }catch(Exception e){
                txtCampoExtra.setVisible(false);
                btnLOV.setVisible(false);
            }
        }catch(Exception e){
            ConsolaDebug.agregarTexto("Fallo en los valores de conexion a la BD", ConsolaDebug.ERROR);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Formatear Fecha">
    private String formatearFecha(Date fecha){
        return Fecha.obtenerFechaFormateada(fecha, Fecha.FORMATO_COMPLETO);
    }
    // </editor-fold>
    
    private void seleccionarImagen(){}
}
