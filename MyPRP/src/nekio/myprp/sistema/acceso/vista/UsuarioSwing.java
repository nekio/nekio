package nekio.myprp.sistema.acceso.vista;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias">
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import nekio.myprp.recursos.utilerias.DetalleUsuario;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Icono;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;
import nekio.myprp.sistema.acceso.dao.RangoDAO;
import nekio.myprp.sistema.acceso.dao.SistemaDAO;
import nekio.myprp.sistema.acceso.dao.UsuarioDAO;
import nekio.myprp.sistema.acceso.dto.RangoDTO;
import nekio.myprp.sistema.acceso.dto.SistemaDTO;
import nekio.myprp.sistema.acceso.dto.UsuarioDTO;
// </editor-fold>

public class UsuarioSwing extends SwingJFrame{
    // <editor-fold defaultstate="collapsed" desc="Atributos">
    private final Dimension DIMENSION = new Dimension(600, 400);
    
    private Container contenedor;
    private DetalleUsuario usuario;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
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
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Agregar Componentes">
    @Override
    public void agregarComponentes() {
             
        // Rango
        JPanel pnlRangoProp = new JPanel(new BorderLayout());
        
        JLabel lblRangoDesc = new JLabel(Idioma.obtenerTexto(Idioma.PROP_RANGO, "rango") + ": " + usuario.getRangoDesc());
        Color colorRango = null;
        switch(usuario.getRangoId()){
            case 1:
                colorRango = Color.LIGHT_GRAY;
            break;
            case 2:
                colorRango = new Color(0, 204, 0);
            break;
            case 3:
                colorRango = new Color(0, 153, 204);
            break;
            case 4:
                colorRango = new Color(235, 0, 0);
            break;
            case 5:
                colorRango = new Color(204, 153, 255);
            break;
        }
        lblRangoDesc.setHorizontalAlignment(SwingConstants.CENTER);
        lblRangoDesc.setOpaque(true);
        lblRangoDesc.setBackground(colorRango);
        pnlRangoProp.add(lblRangoDesc, "North");
        
        JPanel pnlRango = new JPanel(new FlowLayout());
        pnlRango.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_RANGO, "acc_publico"), usuario.isAccPublico(), null));
        pnlRango.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_RANGO, "acc_grupal"), usuario.isAccGrupal(), null));
        pnlRango.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_RANGO, "acc_protegido"), usuario.isAccProtegido(), null));
        pnlRango.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_RANGO, "acc_privado"), usuario.isAccPrivado(), null));
        pnlRangoProp.add(pnlRango, "Center");
        
        pnlRangoProp.add(new JLabel(" "), "South");
        
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
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "configurar"), usuario.isConfigurar(), Globales.IMG_PRIV_CONFIGURAR));
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "buscar"), usuario.isBuscar(), Globales.IMG_PRIV_BUSCAR));
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "insertar"), usuario.isInsertar(), Globales.IMG_PRIV_INSERTAR));
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "modificar"), usuario.isModificar(), Globales.IMG_PRIV_MODIFICAR));
        pnlPrivilegio.add(agregarInsignia(Idioma.obtenerTexto(Idioma.PROP_PRIVILEGIO, "eliminar"), usuario.isEliminar(), Globales.IMG_PRIV_ELIMINAR));
        pnlPrivilegioProp.add(pnlPrivilegio, "Center");
        
        // Sistemas
        JPanel pnlSistemas = new JPanel(new FlowLayout());
        pnlSistemas.setOpaque(true);
        pnlSistemas.setBackground(colorRango);
        
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
        
        JPanel pnlAvatarNorte = new JPanel(new BorderLayout());
        pnlAvatarNorte.setOpaque(false);
        
        JLabel lblUsuario = new JLabel(usuario.getNick());
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setOpaque(true);
        lblUsuario.setBackground(Color.BLACK);
        lblUsuario.setForeground(Color.WHITE);
        if(usuario.getId() == 1)
            lblUsuario.setIcon(new ImageIcon(getClass().getResource(Globales.IMG_RANGO_PREMIUM)));
        
        pnlAvatarNorte.add(cargarTipoUsr(usuario.getTipoId()),"East");        
        pnlAvatarNorte.add(lblUsuario,"Center");        
        pnlAvatarNorte.add(cargarPiedrasPreciosas(usuario.getRangoId()),"South");
        
        pnlAvatar.add(pnlAvatarNorte,"North");
        pnlAvatar.add(this.obtenerImagen(Globales.IMG_NO_AVATAR), "Center");
        pnlAvatar.add(cargarEstrellas(usuario.getTipoId(), usuario.getRangoId()),"South");
        
        contenedor.add(pnlRangoProp, "North");
        contenedor.add(pnlUsuario, "Center");
        contenedor.add(pnlAvatar, "West");
        contenedor.add(obtenerImagen(Globales.IMG_PROPIEDADES), "East");
        contenedor.add(pnlPrivilegioProp, "South");
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos auxiliares">
    private JButton agregarInsignia(String texto, boolean permitido, String imagen){
        JButton btnAcceso = new JButton(texto);
        
        if(imagen != null)
            btnAcceso.setIcon(new ImageIcon(getClass().getResource(imagen)));
        
        if(permitido){
            btnAcceso.setBackground(new Color(153, 204, 255));
            btnAcceso.setToolTipText("Permitido");
        }else{
            btnAcceso.setBackground(Color.LIGHT_GRAY);
            btnAcceso.setToolTipText("No Permitido");
        }
        
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
    
    private JLabel cargarTipoUsr(int tipoUsuario){
        JLabel usr = null;
        
        switch(tipoUsuario){
            case 1:
                usr = obtenerImagen(Globales.IMG_USR_ADMIN);
            break;
            case 2:
                usr = obtenerImagen(Globales.IMG_USR_MODERADOR);
            break;
            case 3:
                usr = obtenerImagen(Globales.IMG_USR_USUARIO);
            break;
            case 4:
                usr = obtenerImagen(Globales.IMG_USR_INVITADO);
            break;
            case 5:
                usr = obtenerImagen(Globales.IMG_USR_ANONIMO);
            break;
        }
        
        return usr;
    }
    
    private JPanel cargarPiedrasPreciosas(int rango){
        JPanel pnlPiedras = new JPanel(new FlowLayout());
        pnlPiedras.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        RangoDTO dto = null;
        String descripcion = null;
        
        switch(rango){
            case 5:
                dto = (RangoDTO)new RangoDAO().leerUno("id_rango = " + 5);
                descripcion = dto.getDescripcion();
                
                JLabel diamante = new Icono().obtener(Globales.IMG_RANGO_DIAMANTE, descripcion);
                pnlPiedras.add(diamante);
            case 4:
                dto = (RangoDTO)new RangoDAO().leerUno("id_rango = " + 4);
                descripcion = dto.getDescripcion();
                
                JLabel rubi = new Icono().obtener(Globales.IMG_RANGO_RUBI, descripcion);
                pnlPiedras.add(rubi);
            case 3:
                dto = (RangoDTO)new RangoDAO().leerUno("id_rango = " + 3);
                descripcion = dto.getDescripcion();
                
                JLabel zafiro = new Icono().obtener(Globales.IMG_RANGO_ZAFIRO, descripcion);
                pnlPiedras.add(zafiro);
            case 2:
                dto = (RangoDTO)new RangoDAO().leerUno("id_rango = " + 2);
                descripcion = dto.getDescripcion();
                
                JLabel esmeralda = new Icono().obtener(Globales.IMG_RANGO_ESMERALDA, descripcion);
                pnlPiedras.add(esmeralda);
            case 1:
                dto = (RangoDTO)new RangoDAO().leerUno("id_rango = " + 1);
                descripcion = dto.getDescripcion();
                
                JLabel carbon = new Icono().obtener(Globales.IMG_RANGO_CRISTAL, descripcion);
                pnlPiedras.add(carbon);
        }
        
        return pnlPiedras;
    }
    
    private JPanel cargarEstrellas(int tipo, int rango){
        JPanel pnlEstrellas = new JPanel(new FlowLayout());
        pnlEstrellas.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        JLabel estrella = null;
        //La quinta estrella se da a cualquier tipo de usuario que sea diamante
        if(rango == 5)
            estrella = obtenerImagen(Globales.IMG_RANGO_STAR_1);
        else
            estrella = obtenerImagen(Globales.IMG_RANGO_STAR_0);
        pnlEstrellas.add(estrella);
        
        //Las primeras 4 estrellas son por tipo de usuario
        for(int i=1; i <= 4; i++){
            if(i > tipo-1)
                estrella = obtenerImagen(Globales.IMG_RANGO_STAR_1);
            else
                estrella = obtenerImagen(Globales.IMG_RANGO_STAR_0);
            
            pnlEstrellas.add(estrella);
        }
        
        return pnlEstrellas;
    }
    
    private JLabel obtenerImagen(String ruta){
        return new Icono().obtener(ruta, null);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Agregar Escuchadores">
    @Override
    public void agregarEscuchadores() {
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    // </editor-fold>
    
}
