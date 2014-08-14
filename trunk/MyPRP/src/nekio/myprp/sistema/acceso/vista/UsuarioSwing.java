package nekio.myprp.sistema.acceso.vista;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import nekio.myprp.recursos.utilerias.DetalleUsuario;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;
import nekio.myprp.sistema.acceso.dao.SistemaDAO;
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.dto.SistemaDTO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;


public class UsuarioSwing extends SwingJFrame{
    private final Dimension DIMENSION = new Dimension(450, 350);
    
    private Container contenedor;
    private DetalleUsuario usuario;
    
    public UsuarioSwing(){
        this.setTitle(Globales.NOMBRE_APP + " - " + Idioma.obtenerTexto(Idioma.PROP_MENU, "usuario"));
        
        this.contenedor = this.getContentPane();
        this.setSize(DIMENSION);
        this.setMinimumSize(DIMENSION);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        usuario = Globales.APP_USUARIO;
        
        agregarComponentes(); 
        agregarEscuchadores();
        
        this.setVisible(true);
    }

    @Override
    public void agregarComponentes() {
        // Rango
        JPanel pnlRangoProp = new JPanel(new BorderLayout());
        
        JLabel lblRangoDesc = new JLabel(Idioma.obtenerTexto(Idioma.PROP_RANGO, "rango") + ": " + usuario.getRangoDesc());
        Color colorRango = null;
        switch(usuario.getRangoId()){
            case 1:
                colorRango = Color.DARK_GRAY;
            break;
            case 2:
                colorRango = Color.GREEN;
            break;
            case 3:
                colorRango = Color.BLUE;
            break;
            case 4:
                colorRango = Color.RED;
            break;
            case 5:
                colorRango = Color.WHITE;
            break;
        }
        lblRangoDesc.setHorizontalAlignment(SwingConstants.CENTER);
        lblRangoDesc.setOpaque(true);
        lblRangoDesc.setBackground(colorRango);
        pnlRangoProp.add(lblRangoDesc, "North");
        
        JPanel pnlRango = new JPanel(new FlowLayout());
        pnlRango.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_RANGO, "acc_publico"), usuario.isAccPublico()));
        pnlRango.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_RANGO, "acc_grupal"), usuario.isAccGrupal()));
        pnlRango.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_RANGO, "acc_protegido"), usuario.isAccProtegido()));
        pnlRango.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_RANGO, "acc_privado"), usuario.isAccPrivado()));
        pnlRangoProp.add(pnlRango, "Center");
        
        // Usuario
        JPanel pnlUsuario = new JPanel();
        UsuarioDTO dto = (UsuarioDTO) new UsuarioDAO().leerUno("id_usuario = " + usuario.getId());
        pnlUsuario.add(agregarCampo(Idioma.obtenerTexto(Idioma.PROP_USUARIO, "idUsuario"), String.valueOf(usuario.getId()), colorRango));
        pnlUsuario.add(agregarCampo(Idioma.obtenerTexto(Idioma.PROP_USUARIO, "nombre"), dto.getNombre(), colorRango));
        pnlUsuario.add(agregarCampo(Idioma.obtenerTexto(Idioma.PROP_USUARIO, "nick"), usuario.getNick(), colorRango));
        pnlUsuario.add(agregarCampo(Idioma.obtenerTexto(Idioma.PROP_USUARIO, "fechaRegistro"), Fecha.obtenerFechaFormateada(dto.getFechaRegistro(), Fecha.FORMATO_COMPLETO_2), colorRango));
        pnlUsuario.add(agregarCampo(Idioma.obtenerTexto(Idioma.PROP_USUARIO, "ultimoAcceso"), Fecha.obtenerFechaFormateada(dto.getUltimoAcceso(), Fecha.FORMATO_COMPLETO_2), colorRango));
        dto = null;
        
        // Privilegio
        JPanel pnlPrivilegioProp = new JPanel(new BorderLayout());
        
        JLabel lblPrivilegioDesc = new JLabel(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "privilegio") + ": " + usuario.getTipoDesc());
        lblPrivilegioDesc.setHorizontalAlignment(SwingConstants.CENTER);
        lblPrivilegioDesc.setForeground(colorRango);
        pnlPrivilegioProp.add(lblPrivilegioDesc, "North");
        
        JPanel pnlPrivilegio = new JPanel(new FlowLayout());
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "configurar"), usuario.isConfigurar()));
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "buscar"), usuario.isBuscar()));
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "insertar"), usuario.isInsertar()));
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "modificar"), usuario.isModificar()));
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "eliminar"), usuario.isEliminar()));
        pnlPrivilegioProp.add(pnlPrivilegio, "Center");
        
        // Sistemas
        JPanel pnlSistemas = new JPanel(new FlowLayout());
        SistemaDTO sistema = null;
        JButton btnSistema = null;
        for(int idSistema:usuario.getSistemas()){
            sistema = (SistemaDTO) new SistemaDAO().leerUno("id_sistema = " + idSistema);
            btnSistema = new JButton(sistema.getDescripcion());
            pnlSistemas.add(btnSistema);
        }
        pnlPrivilegioProp.add(pnlSistemas, "South");
        
        // Avatar
        JPanel pnlAvatar = new JPanel(new BorderLayout());
        pnlAvatar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlAvatar.setBackground(colorRango);
        
        JLabel lblUsuario = new JLabel(usuario.getNick());
        pnlAvatar.add(lblUsuario,"North");
        
        contenedor.add(pnlRangoProp, "North");
        contenedor.add(pnlUsuario, "Center");
        contenedor.add(pnlAvatar, "West");
        contenedor.add(pnlPrivilegioProp, "South");
    }
    
    private JButton agregarInsignia(String acceso, boolean permitido){
        JButton btnAcceso = new JButton(acceso);
        btnAcceso.setEnabled(false);
        if(permitido)
            btnAcceso.setBackground(Color.WHITE);
        else
            btnAcceso.setBackground(Color.LIGHT_GRAY);
        
        return btnAcceso;
    }
    
    private JPanel agregarCampo(String nombre, String valor, Color color){
        JPanel pnlCampo = new JPanel(new FlowLayout());
        
        JLabel lblCampo = new JLabel(nombre + ":    ");
        lblCampo.setForeground(color);
        JTextField txtValor = new JTextField(valor);
        txtValor.setEditable(false);
        
        pnlCampo.add(lblCampo);
        pnlCampo.add(txtValor);
        
        return pnlCampo;
    }

    @Override
    public void agregarEscuchadores() {
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
}
