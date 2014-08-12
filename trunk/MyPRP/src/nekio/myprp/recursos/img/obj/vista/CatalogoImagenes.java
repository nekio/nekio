package nekio.myprp.recursos.img.obj.vista;

/**
 *
 * @author Nekio
 */

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import nekio.myprp.recursos.herramientas.ConsolaDebug;
import nekio.myprp.recursos.herramientas.Mensaje;
import nekio.myprp.recursos.img.obj.ImagenDTO;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.gui.swing.BD_Navegador;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingMaestro;

public class CatalogoImagenes extends SwingMaestro{
    private static final long serialVersionUID = 1L;
    
    private final ImagenBD_M BDManipulador = new ImagenBD_M(this);
    private final BD_Navegador BDNavegador = new BD_Navegador(this);
    
    private final Dimension DIMENSION = new Dimension(720, 550);
    private final int ALTO_FILA = 100;
    private final int INDICE_CAMPO_IMG = 1;
    
    private Container contenedor;
    private JPanel pnlContenido;
    private JTable tabla;
    private DefaultTableModel modelo;
    private List<ImagenDTO> listaDTO;
    
    public CatalogoImagenes(List<ImagenDTO> listaDTO){
        this.setTitle(Globales.NOMBRE_APP + " - " + Idioma.obtenerTexto(Idioma.PROP_RECOGEDOR_IMAGEN, "titulo"));
        
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
    
    @Override
    public void agregarComponentes(){
        pnlContenido = new JPanel(new BorderLayout());
        
        // Tabla de registros
        pnlContenido.add(generarTabla(), "Center");
        
        // Manipulador de la Base de Datos
        pnlContenido.add(BDManipulador, "South");
        
        // Navegador de la Base de Datos
        pnlContenido.add(BDNavegador, "North");
        
        contenedor.add(pnlContenido);
    }
    
    @Override
    public void agregarEscuchadores(){
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private JScrollPane generarTabla(){
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
            matriz[i][INDICE_CAMPO_IMG] = listaDTO.get(i).getImagen();
            matriz[i][2] = listaDTO.get(i).getNombre();
            matriz[i][3] = String.valueOf(listaDTO.get(i).getTipo());
            matriz[i][4] = Fecha.obtenerFechaFormateada(listaDTO.get(i).getFechaSubida(), Fecha.FORMATO_COMPLETO);
            matriz[i][5] = listaDTO.get(i).getDescripcion();
            matriz[i][6] = String.valueOf(listaDTO.get(i).getIdSistema());
        }
        
        modelo = new DefaultTableModel(matriz,cabeceras){            
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false;
            }
            
            @Override
            public Object getValueAt(int fila, int columna){                
                if(columna != INDICE_CAMPO_IMG)
                    return matriz[fila][columna];
                else
                    return new ImageIcon((BufferedImage)matriz[fila][INDICE_CAMPO_IMG]);
            }
        };
        
        tabla = new JTable(modelo);
        tabla.setRowHeight(ALTO_FILA);
        tabla.setDefaultRenderer(Object.class, new RenderCeldas());
        
        //tabla.setFillsViewportHeight(true);
        tabla.setShowGrid(false);
        tabla.setOpaque(false);
        
        // Definir columna de imagen
        tabla.getColumnModel().getColumn(INDICE_CAMPO_IMG).setCellRenderer(tabla.getDefaultRenderer(ImageIcon.class));
        
        /*Agregar Scroll a la tabla*/
        TableRowSorter ordenador = new TableRowSorter<TableModel>(modelo);
        tabla.setRowSorter(ordenador);
        modelo.fireTableDataChanged(); // Para reconocimiento de actualizaciones en la tabla
        JScrollPane scrollPane = new JScrollPane(tabla);
        
        return scrollPane;
    }
    
    private ArrayList<String> obtenerCabeceras(){
        ArrayList<String> valores = null;
        
        String ubicacionClase = Globales.PAQ_IMG_OBJ+"."+Globales.Entidad.Imagen.name()+Globales.DTO;
        if(Globales.APP_DEBUG)
            ConsolaDebug.agregarTexto("Obteniendo atributos de la clase: " + ubicacionClase, ConsolaDebug.PROCESO);
                
        try {
            Class<?> clase = Class.forName(ubicacionClase);

            Field properties[] = clase.getDeclaredFields();
            valores = new ArrayList<String>();
            String cabecera = null;
            
            for (int i = 0; i < properties.length; i++) {
                Field field = properties[i];
                if(!field.getType().isEnum()){
                    cabecera = field.getName();
                    if(cabecera != "rutaImagen") //Este campo es artificial, por eso no se considera
                        valores.add(cabecera);
                }
            }
        } catch (ClassNotFoundException e){
            if(Globales.APP_DEBUG)
                ConsolaDebug.agregarTexto("No fue posible cargar los atributos de la clase\n" + e, ConsolaDebug.ERROR);
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
    
    @Override
    public void navegar(int accion) {
        try{
            int filaSeleccionada = tabla.getSelectedRow();
            if(filaSeleccionada < 0)
                filaSeleccionada = 0;

            int filaFinal = tabla.getRowCount()-1;

            switch(accion){
                case BD_Navegador.PRIMERO:
                    tabla.setRowSelectionInterval(0, 0);
                break;
                case BD_Navegador.ANTERIOR:
                    int filaAnterior = filaSeleccionada-1;
                    if(filaAnterior == 0){
                        super.getNavegadorBD().deshabilitarPrimero();
                        super.getNavegadorBD().deshabilitarAnterior();
                    }

                    tabla.setRowSelectionInterval(filaAnterior, filaAnterior);
                break;
                case BD_Navegador.SIGUIENTE:
                    int filaSiguiente = filaSeleccionada+1;
                    if(filaSiguiente == filaFinal){
                        super.getNavegadorBD().deshabilitarUltimo();
                        super.getNavegadorBD().deshabilitarSiguiente();
                    }

                    tabla.setRowSelectionInterval(filaSiguiente, filaSiguiente);
                break;
                case BD_Navegador.ULTIMO:                
                    tabla.setRowSelectionInterval(filaFinal, filaFinal);
                break;
            }
        }catch(Exception e){
            BDNavegador.habilitarTodo(false);
        }
    }

    @Override
    public void buscar(String filtro){ 
        String registro = null;
        filtro = filtro.toUpperCase();
        
        for(int fila=0; fila<=tabla.getRowCount()-1; fila++) {
            for(int columna=0; columna<=tabla.getColumnCount()-1; columna++) {
                if(columna != INDICE_CAMPO_IMG){
                    registro = String.valueOf(tabla.getValueAt(fila, columna)).toUpperCase();
                    if(registro.contains(filtro)) {
                        ConsolaDebug.agregarTexto(filtro+" : "+registro, ConsolaDebug.COMODIN);
                        // Localiza automaticamente la vista del scroll en la ubicacion del valor
                        tabla.scrollRectToVisible(tabla.getCellRect(fila, 0, true));

                        // Agrega el foco a la fila seleccionada
                        tabla.setRowSelectionInterval(fila, fila);

                        for(int i=0; i<=tabla.getColumnCount()-1; i++) {
                            if( i != INDICE_CAMPO_IMG)
                                tabla.getColumnModel().getColumn(i).setCellRenderer(new RenderCeldas());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void nuevo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class RenderCeldas extends DefaultTableCellRenderer{
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccionado, boolean tieneFocus, int fila, int columna){
            super.getTableCellRendererComponent (tabla, valor, seleccionado, tieneFocus, fila, columna);
            if(columna != INDICE_CAMPO_IMG)
               this.setBackground(Color.LIGHT_GRAY);
            else
                this.setBackground(Color.WHITE);
            
            if(fila == tabla.getSelectedRow())
                setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.WHITE));

            return this;
         }
    }

    @Override
    public void recargar(List<DTO> listaDTO){
        this.listaDTO = new ArrayList<ImagenDTO>();
        for(DTO dto:listaDTO)
            this.listaDTO.add((ImagenDTO) dto);
        
        pnlContenido.setVisible(false);
        pnlContenido= null;
        
        agregarComponentes();
    }

    public List<ImagenDTO> getListaDTO() {
        return listaDTO;
    }

    public void setListaDTO(List<ImagenDTO> listaDTO) {
        this.listaDTO = listaDTO;
    }
}
