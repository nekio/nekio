
package nekio.myprp.sistema.acceso.vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import nekio.myprp.recursos.herramientas.Mensaje;
import nekio.myprp.recursos.utilerias.Autogenerador;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;
import nekio.myprp.sistema.acceso.dao.IdiomaDAO;
import nekio.myprp.sistema.acceso.dao.RangoDAO;
import nekio.myprp.sistema.acceso.dao.TipoUsuarioDAO;
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.dto.IdiomaDTO;
import nekio.myprp.sistema.acceso.dto.RangoDTO;
import nekio.myprp.sistema.acceso.dto.TipoUsuarioDTO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;

/**
 *
 * @author Nekio
 */
public class NuevoUsuarioSwing extends SwingJFrame{
    private final Dimension DIMENSION = new Dimension(300, 300);
    
    private JComboBox cmbTipoUsuario;
    private JComboBox cmbRangoUsuario;
    private JComboBox cmbIdioma;
    private JTextField txtNombre;
    private JTextField txtNick;
    private JPasswordField txtPassword;
    private JPasswordField txtPasswordConfirm;
    private JTextField txtContacto;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private Container contenedor;
    
    public NuevoUsuarioSwing(){
        this.setTitle(Globales.NOMBRE_APP + " - " + Idioma.obtenerTexto(Idioma.PROP_LOGIN, "ventanaNuevo"));
        
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
        JPanel pnlUsuario = new JPanel(new BorderLayout());
        
        JPanel pnlCampos = new JPanel(new GridLayout(8,2));
        
        // Tipo
        pnlCampos.add(crearCampo(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "tipoUsuario")));
        List<TipoUsuarioDTO> tiposUsuario = (List)new TipoUsuarioDAO().leer("id_tipo_usuario > 2");
        
        int cantidadRegistros = tiposUsuario.size();
        
        String tipos[] = new String[cantidadRegistros];
        for(int i=0; i<cantidadRegistros; i++){
            tipos[i] = tiposUsuario.get(i).getIdTipoUsuario() + " - "+ tiposUsuario.get(i).getDescripcion();
        }
        
        cmbTipoUsuario = new JComboBox(tipos);
        pnlCampos.add(cmbTipoUsuario);
        
        // Rango
        pnlCampos.add(crearCampo(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "rangoUsuario")));
        List<RangoDTO> rangosUsuario = (List)new RangoDAO().leer("id_rango < 3");
        
        cantidadRegistros = rangosUsuario.size();
        
        String rangos[] = new String[cantidadRegistros];
        for(int i=0; i<cantidadRegistros; i++){
            rangos[i] = rangosUsuario.get(i).getIdRango() + " - " + rangosUsuario.get(i).getDescripcion() + " (" + rangosUsuario.get(i).getDetalle() + ")";
        }
        
        cmbRangoUsuario = new JComboBox(rangos);
        pnlCampos.add(cmbRangoUsuario);
        
        
        // Idioma
        pnlCampos.add(crearCampo(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "idioma")));
        List<IdiomaDTO> idiomasUsuario = (List)new IdiomaDAO().leer(null);
        
        cantidadRegistros = idiomasUsuario.size();
        
        String idiomas[] = new String[cantidadRegistros];
        for(int i=0; i<cantidadRegistros; i++){
            idiomas[i] = idiomasUsuario.get(i).getIdIdioma() + " - " + idiomasUsuario.get(i).getDescripcion();
        }
        
        cmbIdioma = new JComboBox(idiomas);
        pnlCampos.add(cmbIdioma);
        
        // Nombre
        pnlCampos.add(crearCampo(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "nombre")));
        txtNombre = new JTextField();
        pnlCampos.add(txtNombre);
        
        // Nick
        pnlCampos.add(crearCampo(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "nick")));
        txtNick = new JTextField();
        pnlCampos.add(txtNick);
        
        // Password
        pnlCampos.add(crearCampo(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "darPassword")));
        txtPassword = new JPasswordField();
        pnlCampos.add(txtPassword);
        
        pnlCampos.add(crearCampo(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "confirmPassword")));
        txtPasswordConfirm = new JPasswordField();
        pnlCampos.add(txtPasswordConfirm);
        
        // Contacto
        pnlCampos.add(crearCampo(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "contacto")));
        txtContacto = new JTextField();
        pnlCampos.add(txtContacto);
        
        pnlUsuario.add(pnlCampos);
        
        /* BOTONES */
        JPanel pnlBotones = new JPanel(new FlowLayout());
        
        btnAceptar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "aceptar"));
        pnlBotones.add(btnAceptar);
        
        btnCancelar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "cancelar"));
        pnlBotones.add(btnCancelar);
        
        contenedor.add(pnlUsuario,"Center");
        contenedor.add(pnlBotones,"South");
    }
    
    @Override
    public void agregarEscuchadores() {
        btnAceptar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                if(validarEntradas())
                    crearUsuario();
            }
        });
        
        btnCancelar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                salir();
            }
        });
                
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private JLabel crearCampo(String texto){
        JLabel lblCampo = new JLabel(texto + ":   ");
        lblCampo.setHorizontalAlignment(SwingConstants.RIGHT);
        
        return lblCampo;
    }
    
    private boolean validarEntradas(){
        boolean ok = false;
        
        StringBuilder mensaje = new StringBuilder();
        
        if(txtNombre==null || txtNombre.getText().length() < 10){
            mensaje.append("\n\t- " + Idioma.obtenerTexto(Idioma.PROP_LOGIN, "Xnombre"));
        }
        
        if(txtNick==null || txtNick.getText().length() < 5){
            mensaje.append("\n\t- " + Idioma.obtenerTexto(Idioma.PROP_LOGIN, "Xnick"));
        }
        
        if(txtPassword==null || txtPassword.getText().length() < 5){
            mensaje.append("\n\t- " + Idioma.obtenerTexto(Idioma.PROP_LOGIN, "XdarPassword"));
        }
        
        if(txtPasswordConfirm==null || txtPasswordConfirm.getText().length() < 5){
            mensaje.append("\n\t- " + Idioma.obtenerTexto(Idioma.PROP_LOGIN, "XconfirmPassword"));
        }
        
        if(!txtPasswordConfirm.getText().equals(txtPassword.getText())){
            mensaje.append("\n\t- " + Idioma.obtenerTexto(Idioma.PROP_LOGIN, "Xcoincidir"));
        }
        
        if(txtContacto==null || txtContacto.getText().length() < 8){
            mensaje.append("\n\t- " + Idioma.obtenerTexto(Idioma.PROP_LOGIN, "Xcontacto"));
        }
        
        if(mensaje.length() > 0){
            mensaje.insert(0, Idioma.obtenerTexto(Idioma.PROP_LOGIN, "Xmensaje") + "\n");
            new Mensaje(mensaje.toString(), Mensaje.MSJ_ADVERTENCIA);
        }else{
            ok = true;
        }
        
        return ok;
    }
    
    private void crearUsuario(){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdTipoUsuario(obtenerID(cmbTipoUsuario.getSelectedItem().toString()));
        dto.setIdRango(obtenerID(cmbRangoUsuario.getSelectedItem().toString()));
        dto.setIdIdioma(obtenerID(cmbIdioma.getSelectedItem().toString()));
        dto.setNombre(txtNombre.getText());
        dto.setNick(txtNick.getText());
        try{
            dto.setAutogenerado(Autogenerador.obtenerCadena(txtPassword.getText()));
        }catch(Exception e){
            new Mensaje(Idioma.obtenerTexto(Idioma.PROP_LOGIN, "XdarPassword"), Mensaje.MSJ_ERROR);
        }
        dto.setAcceso(Autogenerador.crearAcceso(txtPassword.getText()));
        dto.setContacto(txtContacto.getText());
        
        UsuarioDAO dao = new UsuarioDAO();
        dao.asignarParametros(dto);
        dao.agregar();
    }
    
    private int obtenerID(String texto){
        int id = 0;
        
        id = Integer.valueOf(texto.substring(0, texto.indexOf("-")).trim());
        
        return id;
    }
    
    @Override
    public void salir(){
        setVisible(false);
        dispose();

        new LoginGUI();
    }
}
