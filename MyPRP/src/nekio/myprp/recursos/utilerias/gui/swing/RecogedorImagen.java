package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import nekio.myprp.recursos.herramientas.ImagenEnvoltorio;
import nekio.myprp.recursos.herramientas.Mensaje;
import nekio.myprp.recursos.img.obj.GestorImagen;
import nekio.myprp.recursos.img.obj.ImagenDTO;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.plantillas.SwingMaestro;

public class RecogedorImagen extends JFrame implements DropTargetListener{
    private static final long serialVersionUID = 1L;
    private final Dimension DIMENSION = new Dimension(380, 290);
    
    private Container contenedor;
    private SwingMaestro guiPadre;
    
    private JLabel lblTexto;
    private JLabel lblNombreArchivo;
    private JLabel lblImagen;
    private JTextField txtNombreImagen;
    private JTextField txtDescripcion;
    private JComboBox cmbTipoImagen;
    private JButton btnSubir;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private ImagenDTO dto;
    private Integer idImagen;
    private BufferedImage imagen;
    private String nombreImagen;
    private Character tipoImagen;
    private Date fechaSubida;
    private String descripcionImagen;
    
    private File archivo;
    private String nombreArchivo;
    private String rutaArchivo;
    private Dimension dimension;
    private DropTarget dragNDrop;
    
    public RecogedorImagen(SwingMaestro guiPadre){
        this(guiPadre, null);
    }
    
    public RecogedorImagen(SwingMaestro guiPadre, ImagenDTO dto){
        super(Globales.NOMBRE_APP + " - " + Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "titulo"));
        
        this.guiPadre = guiPadre;
        
        if(guiPadre == null){
            if(Globales.APP_DEBUG)
                System.out.println("\nFalta asociar en el Mapeador la Vista padre que invoca a esta Vista");
            
            new Mensaje(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "noVistaPadre"));
            salir();
        }else{
            this.contenedor = this.getContentPane();
            this.dto = dto;

            this.setSize(DIMENSION);
            this.setMinimumSize(DIMENSION);
            this.setLocationRelativeTo(null);
            this.setLayout(new BorderLayout());

            leerDTO();
            agregarComponentes();
            agregarEscuchadores();

            this.setVisible(true);
        }
    }
    
    private void leerDTO(){
        if (dto == null){
            idImagen = null;
            imagen = null;
            nombreImagen = null;
            tipoImagen = null;
            fechaSubida = null;
            descripcionImagen = null;
        }else{
            idImagen = dto.getIdImagen();
            imagen = (BufferedImage)dto.getImagen();
            nombreImagen = dto.getNombre();
            tipoImagen = dto.getTipo();
            fechaSubida = dto.getFechaSubida();
            descripcionImagen = dto.getDescripcion();
        }
    }
    
    private void agregarComponentes(){
        // Texto default del Titulo
        JPanel pnlSuperior = new JPanel(new BorderLayout());
        
        lblTexto = new JLabel(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "textoDefault"));
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        pnlSuperior.add(lblTexto, "North");
        
        String id = idImagen == null ? "-" : idImagen.toString();
        JLabel lblId = new JLabel(" ID : "+id);
        lblId.setHorizontalAlignment(SwingConstants.CENTER);
        pnlSuperior.add(lblId, "West");
                
        cmbTipoImagen = new JComboBox(ImagenDTO.TipoImagen.values());
        pnlSuperior.add(cmbTipoImagen, "East");
        
        contenedor.add(pnlSuperior, "North");
                
        // Imagen
        JPanel pnlImagen = new JPanel(new BorderLayout());
        pnlImagen.setBackground(Color.BLACK);
        
        lblNombreArchivo = new JLabel(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "nombreArchivo"));
        lblNombreArchivo.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombreArchivo.setForeground(Color.WHITE);
        pnlImagen.add(lblNombreArchivo, "North");
        
        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        pnlImagen.add(lblImagen, "Center");
        
        JPanel pnlTextos = new JPanel(new BorderLayout());
        JPanel pnlTxtNmb = new JPanel(new BorderLayout());

        JLabel lblNombreImagen = new JLabel(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "nombreImagen"));
        lblNombreImagen.setHorizontalAlignment(SwingConstants.CENTER);
        pnlTxtNmb.add(lblNombreImagen, "West");
        
        txtNombreImagen = new JTextField();
        txtNombreImagen.setEnabled(false);
        pnlTxtNmb.add(txtNombreImagen, "Center");
        pnlTextos.add(pnlTxtNmb, "North");
        
        JPanel pnlTxtDsc = new JPanel(new BorderLayout());
        
        JLabel lblDescripcion = new JLabel(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "descripcion"));
        lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
        pnlTxtDsc.add(lblDescripcion, "West");
        
        txtDescripcion = new JTextField();
        txtDescripcion.setEnabled(false);
        pnlTxtDsc.add(txtDescripcion, "Center");
        pnlTextos.add(pnlTxtDsc, "South");
        
        pnlImagen.add(pnlTextos, "South");
        
        contenedor.add(pnlImagen, "Center");
        
        // Botones
        JPanel pnlBotones = new JPanel();
        btnSubir = new JButton(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "subir"));
        btnAceptar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "aceptar"));
        btnAceptar.setEnabled(false);
        btnCancelar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "cancelar"));
                
        pnlBotones.add(btnSubir);
        pnlBotones.add(btnAceptar);
        pnlBotones.add(btnCancelar);
        contenedor.add(pnlBotones, "South");
        
        definirValoresComponentes();
        dragNDrop=new DropTarget(this,this);
    }
    
    private void agregarEscuchadores(){
        cmbTipoImagen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                if(dto == null)
                    habilitarComponentes(leerImagen(archivo));
                else
                    habilitarComponentes(leerImagen());
             }
        });
        
        txtNombreImagen.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){  
                int longitudMaxima = 30;
                String texto=txtNombreImagen.getText();

                if(texto.length() >= longitudMaxima){
                    texto = texto.substring(0, longitudMaxima);
                    txtNombreImagen.setText(texto);
                    txtNombreImagen.setForeground(Color.RED);
                    e.consume();
                }else
                    txtNombreImagen.setForeground(Color.BLACK);
            }; 
        }); 
        
        txtDescripcion.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){  
                int longitudMaxima = 80;
                String texto=txtDescripcion.getText();

                if(texto.length() >= longitudMaxima){
                    texto = texto.substring(0, longitudMaxima);
                    txtDescripcion.setText(texto);
                    txtDescripcion.setForeground(Color.RED);
                    e.consume();
                }else
                    txtDescripcion.setForeground(Color.BLACK);
            }; 
        }); 
        
        btnSubir.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                if(escogerImagen())
                    habilitarComponentes(true);
                else
                    habilitarComponentes(false);
            }
        });
        
        btnAceptar.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                if(aceptar())
                    guardar();
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
    
    private boolean escogerImagen(){
        boolean imagenOK = false;

        JFileChooser selecciona = new JFileChooser(System.getProperty("user.home"));
        selecciona.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado=selecciona.showSaveDialog(null);
        if(resultado==JFileChooser.CANCEL_OPTION)
            return imagenOK;

        File archivo=selecciona.getSelectedFile();
        if(archivo==null || archivo.getName().equals(""))
            new Mensaje("No fue seleccionado ningun archivo", Mensaje.MSJ_ERROR);
        else
            imagenOK = leerImagen(archivo);
        
        return imagenOK;
    }
    
    private void habilitarComponentes(boolean habilitar){
        btnAceptar.setEnabled(habilitar);
        txtNombreImagen.setEnabled(habilitar);
        txtDescripcion.setEnabled(habilitar);
        
        txtNombreImagen.setText("");
        txtDescripcion.setText("");
        lblNombreArchivo.setText(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "nombreArchivo"));
        lblImagen.setIcon(null);
        
        definirValoresComponentes();
    }
    
    private void definirValoresComponentes(){
        // Imagen
        if(imagen != null)
            lblImagen.setIcon(new ImageIcon(imagen));
        
        // Nombre de archivo
        if(dto != null){
            nombreArchivo = "";
            lblNombreArchivo.setText(nombreArchivo);
        }
        
        // Nombre Imagen
        txtNombreImagen.setText(nombreImagen);
        
        // Tipo Imagen
        int indice = 0;
        if(tipoImagen != null)
            indice = ImagenDTO.TipoImagen.TipoImagen(tipoImagen).getIndice();
        cmbTipoImagen.setSelectedIndex(indice);
        
        // Descripcion
        txtDescripcion.setText(descripcionImagen);
    }
    
    private boolean aceptar(){
        boolean cerrarVentana = true;
        
        nombreImagen = txtNombreImagen.getText();
        descripcionImagen = txtDescripcion.getText();
        fechaSubida = new Date();
        
        if(nombreImagen.length() == 0){
            int resultado = Mensaje.decidir(Idioma.obtenerTexto(
                    Idioma.PROP_RECOGEDOR_IMAGEN, "noNombre"),
                    Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "denegar"),
                    Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "confirmar"));
            
            if(resultado == Mensaje.CANCELAR || resultado == Mensaje.CERRAR)
                cerrarVentana = false;
        }
        
        return cerrarVentana;
    }
    
    private boolean leerImagen(){
        leerDimension();

        try{
            imagen = ImagenEnvoltorio.obtenerImagen(dimension, dto.getImagen());
            nombreArchivo = "";

            return true;
        }catch(Exception e){
            new Mensaje(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "noImagen"), Mensaje.MSJ_ERROR);
        }

        return false;
    }
    
    private boolean leerImagen(File archivo){
        if(archivo == null)
            return false;
        else{
            leerDimension();

            String ruta = archivo.toString();
            try{
                imagen = ImagenEnvoltorio.obtenerImagen(dimension, ruta);
                if(dto != null)
                    dto.setImagen(imagen);
                
                nombreArchivo = archivo.getName();
                rutaArchivo = ruta;
                this.archivo = archivo;
                
                return true;
            }catch(Exception e){
                new Mensaje(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "noImagen"), Mensaje.MSJ_ERROR);
            }

            return false;
        }
    }
    
    private void leerDimension(){
        String tipoImg = cmbTipoImagen.getSelectedItem().toString();
        
        if(tipoImg.equals(ImagenDTO.TipoImagen.VERTICAL.name())){
            dimension = ImagenDTO.TipoImagen.VERTICAL.getDimension();
            tipoImagen = ImagenDTO.TipoImagen.VERTICAL.getTipo();
        }else if(tipoImg.equals(ImagenDTO.TipoImagen.HORIZONTAL.name())){
            dimension = ImagenDTO.TipoImagen.HORIZONTAL.getDimension();
            tipoImagen = ImagenDTO.TipoImagen.HORIZONTAL.getTipo();
        }else if(tipoImg.equals(ImagenDTO.TipoImagen.AJUSTADO_CUADRADO.name())){
            dimension = ImagenDTO.TipoImagen.AJUSTADO_CUADRADO.getDimension();
            tipoImagen = ImagenDTO.TipoImagen.AJUSTADO_CUADRADO.getTipo();
        }
    }
    
    private void guardar(){
        dto = new ImagenDTO();

        dto.setIdImagen(idImagen);
        dto.setImagen(imagen);
        dto.setNombre(nombreImagen);
        dto.setTipo(tipoImagen);
        dto.setFechaSubida(fechaSubida);
        dto.setDescripcion(descripcionImagen);
        dto.setRutaImagen(rutaArchivo); //Campo artificial
        
        String entidad = Globales.Entidad.Imagen.name();
        
        GestorImagen gestor = new GestorImagen();
        gestor.setDTO(dto);
        gestor.setGui(guiPadre);
 
        if(idImagen == null)
            gestor.ejecutarControladorNegocio(Globales.BD.INSERTAR.getValor(), entidad);
        else
            gestor.ejecutarControladorNegocio(Globales.BD.MODIFICAR.getValor(), entidad);
        
        gestor = null;
        
        salir();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Metodos de implementacion DragN'Drop">
    @Override public void dragEnter(DropTargetDragEvent dtde){}
    @Override public void dragOver(DropTargetDragEvent dtde){}
    @Override public void dropActionChanged(DropTargetDragEvent dtde){}
    @Override public void dragExit(DropTargetEvent dte){}
    @Override public void drop(DropTargetDropEvent evento){
        File archivo = null;
        try {
            Transferable transferencia = evento.getTransferable(); 
            DataFlavor[] metadatosTransfer = transferencia.getTransferDataFlavors();
            
            // Lectura de archivos
            ArrayList<File> archivos= new ArrayList<>();
            for(int i=0;i<metadatosTransfer.length;i++){
                if(metadatosTransfer[i].isFlavorJavaFileListType()){
                    evento.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                    
                    List lista=(List)transferencia.getTransferData(metadatosTransfer[i]);
                    for(int j=0;j<lista.size();j++)
                        archivos.add((File)lista.get(j));
                    evento.dropComplete(true);  
                    archivo = archivos.get(0);
                    
                    habilitarComponentes(leerImagen(archivo));
                    return;
                }
            }
            
            evento.rejectDrop();
        }catch (UnsupportedFlavorException | IOException e) {
            evento.rejectDrop();
        }
    }
    // </editor-fold>
    
    private void salir(){
        this.dispose();
    } 
}
