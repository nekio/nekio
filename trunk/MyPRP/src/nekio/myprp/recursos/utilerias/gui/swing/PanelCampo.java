package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nekio.myprp.recursos.utilerias.Globales;

// <editor-fold defaultstate="collapsed" desc="Panel Campo">
class PanelCampo{
    private static final String TEXTO_VACIO = "                                            ";

    private JTextField txtCampo;
    private JTextField txtCampoExtra;
    private JTextArea txtCaja;
    private JScrollPane scrollCaja;
    private JLabel lblImagen;
    private JButton btnLOV;
    private JButton btnFecha;
    private JCheckBox chkValor;

    public JPanel crear(String campo, Object valor, boolean llave){
        return crear(campo, valor, Globales.TipoDato.TEXTO, llave);
    }

    public JPanel crear(String campo, Object valor, Globales.TipoDato tipoCampo, boolean llave){
        JPanel pnlCampo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblCampo = new JLabel(campo + ":");
        pnlCampo.add(lblCampo);

        // Aunque no todos sean JTextField, se necesita instanciar siempre
        // para obtener el valor del campo
        txtCampo = new JTextField(String.valueOf(valor));
        pnlCampo.add(txtCampo);

        if(llave){
            txtCampoExtra = new JTextField(TEXTO_VACIO);
            txtCampoExtra.setEditable(false);
            pnlCampo.add(txtCampoExtra);

            btnLOV = new JButton("...");
            pnlCampo.add(btnLOV);
        }

        if(!llave && tipoCampo == Globales.TipoDato.FECHA){
            txtCampo.setEnabled(false);
            
            btnFecha = new JButton("...");
            pnlCampo.add(btnFecha);
        }else if(!llave && tipoCampo == Globales.TipoDato.BOOLEANO){
            txtCampo.setVisible(false);
            
            chkValor = new JCheckBox();
            chkValor.setSelected((boolean)valor);
            pnlCampo.add(chkValor);
        }else if(!llave && tipoCampo == Globales.TipoDato.BLOB){
            txtCampo.setVisible(false);

            ImageIcon icon = new ImageIcon(BufferedImage.class.cast(valor)); 
            lblImagen = new JLabel();
            lblImagen.setIcon(icon);
            pnlCampo.add(lblImagen);
        }else if(!llave && tipoCampo == Globales.TipoDato.TEXTO_LARGO){
            txtCampo.setVisible(false);
            
            txtCaja = new JTextArea(String.valueOf(valor),5,58);
            txtCaja.setWrapStyleWord(true);
            txtCaja.setLineWrap(true);
            
            scrollCaja = new JScrollPane(txtCaja);
            scrollCaja.setOpaque(false);
            pnlCampo.add(scrollCaja);
        }

        escuchadores();

        return pnlCampo;
    }

    private void escuchadores(){
        if(btnLOV != null){
            btnLOV.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent evt){
                    abrirListaDeValores();
                }
            });
        }

        if(btnFecha != null){
            btnFecha.addActionListener(new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent evt){
                    manejadorDeFechas();
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
    
    private void abrirListaDeValores(){
        String campo = txtCampo.getText();
        String campoExtra = txtCampoExtra.getText();

        System.out.println(campo + ": " + campoExtra);
    }
    
    private void manejadorDeFechas(){}
    private void seleccionarImagen(){}
}
// </editor-fold>