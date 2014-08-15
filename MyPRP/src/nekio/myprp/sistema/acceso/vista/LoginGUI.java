package nekio.myprp.sistema.acceso.vista;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.sistema.acceso.Inicializador;
// </editor-fold>

public class LoginGUI{    
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private String usuario;
    private String password;
    private boolean recordar;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructores">
    public LoginGUI(){
        this(null, null, false);
    }
    
    public LoginGUI(String usuario, String password, boolean recordar){  
        if( (usuario == null) || (password == null) )
            leerCredenciales();
        else{
            this.usuario = usuario;
            this.password = password;
        }
        
        new Inicializador();
        
        if(usuario!= null && password!=null){
            if(!Inicializador.loggear(this.usuario, this.password, recordar))
                abrirVentanita(this.usuario, null);
        }else
            abrirVentanita(this.usuario, this.password);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Abrir Ventanita">
    private void abrirVentanita(String usuario, String password){
        if(!identificar(usuario, password))
            abrirVentanita(usuario, password);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Identificar">
    private boolean identificar(String usuario, String password){
        boolean ingresado = false;
        
        JPanel pnlLogin=new JPanel(new BorderLayout());
        
        // Norte
        JPanel pnlUsr=new JPanel(new BorderLayout());
        pnlUsr.add(new JLabel("Usuario:     "),"West");
        JTextField txtUser=new JTextField(this.usuario);
        txtUser.setCaretPosition(txtUser.getText().length());
        pnlUsr.add(txtUser,"Center");
        pnlLogin.add(pnlUsr,"North");
        
        // Centro
        JPanel pnlPass=new JPanel(new BorderLayout());
        pnlPass.add(new JLabel("Password: "),"West");
        JPasswordField txtPass = new JPasswordField(this.password);
        txtPass.setCaretPosition(txtPass.getText().length());
        pnlPass.add(txtPass,"Center");
        pnlLogin.add(pnlPass,"Center");
        
        //Sur
        JPanel pnlRecordar=new JPanel(new BorderLayout());
        JCheckBox chkRecordar = new JCheckBox("Recordar credenciales");
        chkRecordar.setSelected(recordar);
        pnlRecordar.add(new JLabel(" "),"South");
        pnlRecordar.add(chkRecordar,"West");
        pnlLogin.add(pnlRecordar,"South");
        
        Object[]opciones = {"Aceptar","Cancelar"};
        
        int entrada=JOptionPane.showOptionDialog(null, pnlLogin,Globales.NOMBRE_APP + " Login",
                 JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,
                 new ImageIcon(getClass().getResource(Globales.IMG_LLAVE)),opciones,txtPass); 
        
        String user=txtUser.getText();
        String pass=txtPass.getText();
        
        this.usuario = user;
        this.password = pass;
        
        if(entrada == 0)
            ingresado = Inicializador.loggear(user, pass, chkRecordar.isSelected());
        else
            salir();

        return ingresado;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Leer Credenciales">
    private void leerCredenciales(){
        try{
            FileReader leerArchivo = new FileReader(Globales.RUTA_CREDENCIALES);
            BufferedReader buffer = new BufferedReader(leerArchivo);

            this.usuario = buffer.readLine();
            this.password = buffer.readLine();
            
            buffer.close();   
            buffer = null;
            
            recordar = true;
        }catch(Exception e){}
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Salir">
    private void salir(){
        System.exit(0);
    }
    // </editor-fold>
}
