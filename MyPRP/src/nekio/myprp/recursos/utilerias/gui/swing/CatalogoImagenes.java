
package nekio.myprp.recursos.utilerias.gui.swing;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import nekio.myprp.recursos.herramientas.Mensaje;
import nekio.myprp.recursos.img.obj.ImagenDTO;
import nekio.myprp.recursos.img.obj.vista.ImagenBD_M;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;

public class CatalogoImagenes extends JFrame{
    private static final long serialVersionUID = 1L;
    private final Dimension DIMENSION = new Dimension(650, 500);
    private final ImagenBD_M BDM = new ImagenBD_M(this);
    private final int ALTO_FILA = 100;
    
    private Container contenedor;
    private JTable tabla;
    private DefaultTableModel modelo;
    private List<ImagenDTO> listaDTO;
    
    public CatalogoImagenes(List<ImagenDTO> listaDTO){
        super(Globales.NOMBRE_APP + " - " + Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "titulo"));
        this.contenedor = this.getContentPane();
        this.setSize(DIMENSION);
        this.setMinimumSize(DIMENSION);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        this.listaDTO = listaDTO;
        
        agregarComponentes();
        agregarEscuchadores();
        
        this.setVisible(true);
    }
    
    private void agregarComponentes(){
        JPanel pnlContenedor = new JPanel(new BorderLayout());
        
        // Tabla de registros
        pnlContenedor.add(generarTabla(), "Center");
        
        // Manipulador de la Base de Datos
        pnlContenedor.add(BDM, "South");
        
        contenedor.add(pnlContenedor);
    }
    
    private void agregarEscuchadores(){
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private JScrollPane generarTabla(){
        final int indiceCampoImg = 1;
        
        ArrayList<String> listaCabeceras = obtenerCabeceras();
        String[] cabeceras = listaCabeceras.toArray(new String[listaCabeceras.size()]);
        int campos = cabeceras.length;
        
        int registros = 0;
        try{
            registros = listaDTO.size();
        }catch(Exception e){}
        
        final Object matriz[][]=new Object[registros][campos];
        for(int i=0;i<registros;i++){
            matriz[i][0] = String.valueOf(listaDTO.get(i).getIdImagen());
            matriz[i][indiceCampoImg] = listaDTO.get(i).getImagen();
            matriz[i][2] = listaDTO.get(i).getNombre();
            matriz[i][3] = String.valueOf(listaDTO.get(i).getTipo());
            matriz[i][4] = Fecha.obtenerFechaFormateada(listaDTO.get(i).getFechaSubida(), Fecha.FORMATO);
            matriz[i][5] = listaDTO.get(i).getDescripcion();
        }
        
        modelo = new DefaultTableModel(matriz,cabeceras){            
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false;
            }
            
            @Override
            public Object getValueAt(int fila, int columna){                
                if(columna != indiceCampoImg)
                    return matriz[fila][columna];
                else
                    return new ImageIcon((BufferedImage)matriz[fila][indiceCampoImg]);
            }
        };
        
        tabla = new JTable(modelo);
        tabla.setRowHeight(ALTO_FILA);
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            private static final long serialVersionUID = 1L;
            
            @Override
            public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccionado, boolean tieneFocus, int fila, int columna){
                super.getTableCellRendererComponent (tabla, valor, seleccionado, tieneFocus, fila, columna);
                if(columna != indiceCampoImg)
                   this.setBackground(Color.LIGHT_GRAY);
                else
                    this.setBackground(Color.WHITE);

                return this;
             }
        });
        
        //tabla.setFillsViewportHeight(true);
        tabla.setShowGrid(false);
        tabla.setOpaque(false);
        
        // Definir columna de imagen
        tabla.getColumnModel().getColumn(indiceCampoImg).setCellRenderer(tabla.getDefaultRenderer(ImageIcon.class));
        
        /*Agregar Scroll a la tabla*/
        TableRowSorter ordenador = new TableRowSorter<TableModel>(modelo);
        tabla.setRowSorter(ordenador);
        JScrollPane scrollPane = new JScrollPane(tabla);
        
        return scrollPane;
    }
    
    private ArrayList<String> obtenerCabeceras(){
        ArrayList<String> valores = null;
        
        String ubicacionClase = Globales.PAQ_IMG_OBJ+"."+Globales.Entidad.Imagen.name()+Globales.DTO;
        if(Globales.APP_DEBUG)
            System.out.println("\nObteniendo atributos de la clase: "+ubicacionClase);
                
        try {
            Class<?> clase = Class.forName(ubicacionClase);

            Field properties[] = clase.getDeclaredFields();
            valores = new ArrayList<String>();
            String cabecera = null;
            
            for (int i = 0; i < properties.length; i++) {
                Field field = properties[i];
                if(!field.getType().isEnum()){
                    cabecera = field.getName();
                    valores.add(cabecera);
                }
            }
        } catch (ClassNotFoundException e){
            if(Globales.APP_DEBUG)
                System.out.println("\nNo fue posible cargar los atributos de la clase\n"+e);
        }
        
        return valores;    
    }
    
    public ImagenDTO getParametros(){
        ImagenDTO parametros = null;
        int indice = tabla.getSelectedRow();
        
        try{
            parametros = listaDTO.get(indice);
        }catch(Exception e){
            new Mensaje(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "noFilaSeleccionada"), Mensaje.MSJ_ADVERTENCIA);
        }
        
        return parametros;
    }
    
    private void salir(){
        this.dispose();
    } 
}
