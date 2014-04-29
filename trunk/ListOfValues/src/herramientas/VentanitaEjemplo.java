package herramientas;

/**
 * Clase para probar una Lista de Valores
 *
 * @author Nekio
 * @version 27/04/2014
 *
 */

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import listadevalores.gui.Swing;
import listadevalores.dto.Elementos;

public class VentanitaEjemplo extends JFrame{
    /*Valores de ejemplo para la obtencion de la lista de valores*/
    private String llave = "id_usuario";
    private String valor = "usuario";
    private ArrayList<String> camposExtras = new ArrayList<String>(){{
            add("nombre");
            add("apellido_p");
            add("apellido_m");
            add("activo");
    }};
    private String tabla = "usuario";
    private boolean ordenadoPorLlave = true;
    
    /*Atributos del JFrame*/
    private JTextField txtCampoId;
    private JTextField txtCampoDesc;
    private JButton btnListaValores;
    
    public VentanitaEjemplo(){
        super("Ejemplo de Lista De Valores");
        this.setSize(250,130);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        
        agregarComponentes();
        agregarEscuchadores();

        setVisible(true);
    }
    
    private void agregarComponentes(){        
        //ID
        JPanel pnlId = new JPanel();
        pnlId.setLayout(null);
        pnlId.setBounds(5,5,250,50);
        
        JLabel lblCampoId = new JLabel("ID Usuario:");
        lblCampoId.setBounds(5,5,80,24);
        pnlId.add(lblCampoId);
        
        txtCampoId = new JTextField("");
        txtCampoId.setBounds(100,5,80,24);
        pnlId.add(txtCampoId);
        
        btnListaValores = new JButton("...");
        btnListaValores.setBounds(190,5,24,22);
        pnlId.add(btnListaValores);
        
        //Descripcion
        JPanel pnlDesc = new JPanel();
        pnlDesc.setLayout(null);
        pnlDesc.setBounds(5,50,250,50);
        
        JLabel lblCampoDesc = new JLabel("Description:");
        lblCampoDesc.setBounds(5,5,80,24);
        pnlDesc.add(lblCampoDesc);
        
        txtCampoDesc = new JTextField("");
        txtCampoDesc.setBounds(100,5,80,24);
        txtCampoDesc.setEnabled(false);
        pnlDesc.add(txtCampoDesc);
                
        //Agregar paneles        
        this.add(pnlId);
        this.add(pnlDesc);
    }
    
    private void agregarEscuchadores(){
        txtCampoId.addKeyListener(new KeyAdapter(){ 
            @Override
            public void keyTyped(KeyEvent k){ 
                String iniciar=txtCampoId.getText();                
                char caracter = k.getKeyChar(); 
               
                //Longitud Maxima del ID
                if(iniciar.length()>5)
                    k.consume();
                
                //Aceptar solo numeros
                if(((caracter < '0') ||(caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)) 
                    k.consume();
                
            } 
        });
        
        btnListaValores.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mouse){
                int x=mouse.getXOnScreen();
                int y=mouse.getYOnScreen();

                Point punto = new Point(x,y);

                llamarListaDeValores(punto);
            }
        });
        
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                System.exit(0);
            }
        });
    }
    
    private void llamarListaDeValores(Point punto){
        try{
            Elementos elementos = new Elementos();

            elementos.setConexion(Conexion.getConexion());
            elementos.setLlave(llave);
            elementos.setValor(valor);
            elementos.setCamposExtras(camposExtras);
            elementos.setTabla(tabla);
            elementos.setOrdendoPorLlave(ordenadoPorLlave);
            
            ArrayList<JTextComponent> componentesTxt = new ArrayList<JTextComponent>();
            componentesTxt.add(txtCampoId);
            componentesTxt.add(txtCampoDesc);
            
            String titulo = tabla;

            new Swing(elementos, componentesTxt, titulo, punto);
        }catch(Exception e){
            System.out.println("Fallo en los valores de conexion a la BD");
        }
    }
}
