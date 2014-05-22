
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
import java.awt.GridLayout;
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
import nekio.myprp.recursos.img.obj.GestorImagen;
import nekio.myprp.recursos.img.obj.ImagenDTO;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class CatalogoImagenes extends JFrame{
    private static final long serialVersionUID = 1L;
    private final Dimension DIMENSION = new Dimension(580, 490);
    private final int altoFila = 100;
    
    private Container contenedor;
    private JTable tabla;
    private DefaultTableModel modelo;
    private List<ImagenDTO> listaDTO;
    
    public CatalogoImagenes(){
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
        obtenerRegistros();
        generarTabla();
        
        int filas = listaDTO.size();
        
        JPanel pnlGrid = new JPanel(new GridLayout(filas,5));
    }
    
    private void agregarEscuchadores(){
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private void obtenerRegistros(){
        String entidad = Globales.Entidad.Imagen.name();
        
        GestorImagen gestor = new GestorImagen();
        gestor.ejecutarControladorNegocio(Globales.BD.LEER.getValor(), entidad);
        
        listaDTO= new ArrayList<ImagenDTO>();
        List<DTO> lista = gestor.getListaDTO();
        for(DTO dto:lista)
            listaDTO.add((ImagenDTO)dto);
    }
    
    private void generarTabla(){
        final int indiceCampoImg = 1;
        
        ArrayList<String> listaCabeceras = obtenerCabeceras();
        String[] cabeceras = listaCabeceras.toArray(new String[listaCabeceras.size()]);
        int campos = cabeceras.length;
        int registros = listaDTO.size();
        
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
        tabla.setRowHeight(altoFila);
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
        
        this.add(scrollPane,"Center");
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
    
    private void salir(){
        this.dispose();
    } 
}
