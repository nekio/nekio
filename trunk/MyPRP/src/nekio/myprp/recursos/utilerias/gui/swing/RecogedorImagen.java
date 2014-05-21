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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
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

public class RecogedorImagen extends JFrame implements DropTargetListener{
    private static final long serialVersionUID = 1L;
    private final Dimension DIMENSION = new Dimension(360, 270);
    
    private Container contenedor;
    private JLabel lblTexto;
    private JLabel lblNombreArchivo;
    private JLabel lblImagen;
    private JTextField txtNombreImagen;
    private JComboBox cmbTipoImagen;
    private JButton btnSubir;
    private JButton btnAceptar;
    private JButton btnCancelar;
    
    private BufferedImage imagen;
    private File archivo;
    private String nombreArchivo;
    private String rutaArchivo;
    private char tipoImagen;
    private Dimension dimension;
    private DropTarget dragNDrop;
    
    public RecogedorImagen(){
        super(Globales.NOMBRE_APP + " - " + Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "titulo"));
        this.contenedor = this.getContentPane();
        this.setSize(DIMENSION);
        this.setMinimumSize(DIMENSION);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
    }
    
    private void agregarComponentes(){
        // Texto default del Titulo
        JPanel pnlSuperior = new JPanel(new BorderLayout());
        
        lblTexto = new JLabel(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "textoDefault"));
        lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
        pnlSuperior.add(lblTexto, "North");
        
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
        
        txtNombreImagen = new JTextField(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "nombreImagen"));
        txtNombreImagen.setEnabled(false);
        pnlImagen.add(txtNombreImagen, "South");
        
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
        
        dragNDrop=new DropTarget(this,this);
    }
    
    private void agregarEscuchadores(){
        cmbTipoImagen.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){
                habilitarComponentes(leerImagen(archivo));
             }
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
            new Mensaje("No fue seleccionado ningun archivo",3);
        else
            imagenOK = leerImagen(archivo);
        
        return imagenOK;
    }
    
    private void habilitarComponentes(boolean habilitar){
        btnAceptar.setEnabled(habilitar);
        txtNombreImagen.setEnabled(habilitar);
        
        if(habilitar){
            txtNombreImagen.setText("");
            lblNombreArchivo.setText(nombreArchivo);
            
            Icon icono = new ImageIcon(imagen);
            lblImagen.setIcon(icono);
        }else{
            txtNombreImagen.setText(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "nombreImagen"));
            lblNombreArchivo.setText(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "nombreArchivo"));
            lblImagen.setIcon(null);
        }
    }
    
    private boolean aceptar(){
        boolean cerrarVentana = true;
        
        if(txtNombreImagen.getText().length() == 0){
            int resultado = Mensaje.decidir(Idioma.obtenerTexto(
                    Idioma.PROP_RECOGEDOR_IMAGEN, "noNombre"),
                    Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "denegar"),
                    Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "confirmar"));
            
            if(resultado == Mensaje.CANCELAR || resultado == Mensaje.CERRAR)
                cerrarVentana = false;
        }
        
        return cerrarVentana;
    }
    
    private boolean leerImagen(File archivo){
        if(archivo == null)
            return false;
        else{
            leerDimension();

            String ruta = archivo.toString();
            try{
                imagen = ImagenEnvoltorio.obtenerImagen(dimension, ruta);
                nombreArchivo = archivo.getName();
                rutaArchivo = ruta;
                this.archivo = archivo;
                
                return true;
            }catch(Exception e){
                new Mensaje(Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "noImagen"),3);
            }

            return false;
        }
    }
    
    private void leerDimension(){
        String tipoImg = cmbTipoImagen.getSelectedItem().toString();
        
        if(tipoImg.equals(ImagenDTO.TipoImagen.VERTICAL.name())){
            dimension = ImagenDTO.TipoImagen.VERTICAL.getDimension();
            tipoImagen = ImagenDTO.TipoImagen.VERTICAL.getTipoImagen();
        }else if(tipoImg.equals(ImagenDTO.TipoImagen.HORIZONTAL.name())){
            dimension = ImagenDTO.TipoImagen.HORIZONTAL.getDimension();
            tipoImagen = ImagenDTO.TipoImagen.HORIZONTAL.getTipoImagen();
        }else if(tipoImg.equals(ImagenDTO.TipoImagen.AJUSTADO_CUADRADO.name())){
            dimension = ImagenDTO.TipoImagen.AJUSTADO_CUADRADO.getDimension();
            tipoImagen = ImagenDTO.TipoImagen.AJUSTADO_CUADRADO.getTipoImagen();
        }
    }
    
    private void guardar(){
        String entidad = Globales.Entidad.Imagen.name();
        GestorImagen gestor = new GestorImagen();
                
        ArrayList parametros = new ArrayList();
        parametros.add(rutaArchivo);
        parametros.add(nombreArchivo);
        parametros.add(tipoImagen);
        parametros.add(new Date());
        parametros.add(""/*descripcion*/);
        gestor.setParametros(parametros);
        
        gestor.ejecutarControladorNegocio(Globales.BD.AGREGAR.getValor(), entidad);
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
