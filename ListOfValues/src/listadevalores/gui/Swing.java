package listadevalores.gui;

/**
 * Clase para crear la GUI (Swing) de una lista de Valores
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import listadevalores.ListaDeValores;
import listadevalores.dto.Elementos;
import listadevalores.dto.LlaveValor;

public class Swing extends JFrame{
    private static final long serialVersionUID = 1L;
    
    private ListaDeValores listaDeValores;
    private ArrayList<LlaveValor> lista;
    private Elementos elementos;
    
    private JTextField txtBusqueda;
    private JButton btnAceptar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private ArrayList<JTextComponent> componentesTxt;
    
    public Swing(Elementos elementos, ArrayList<JTextComponent> componentesTxt){
        this(elementos, componentesTxt, null, null);
    }
    
    public Swing(Elementos elementos, ArrayList<JTextComponent> componentesTxt, String titulo, Point ubicacion){
        super("Lista De Valores "+titulo);
        
        this.elementos = elementos;
        this.componentesTxt = componentesTxt;
        
        this.setSize(500,270);
        if(ubicacion == null)
            this.setLocationRelativeTo(null);
        else
            this.setLocation(ubicacion);
        
        agregarComponentes();
        agregarEscuchadores();

        setVisible(true);
    }
    
    private void agregarComponentes(){
        lista = new ArrayList<LlaveValor>();
        
        JLabel lblBusqueda = new JLabel("Buscar: ");
        
        btnAceptar = new JButton("Aceptar");
        getRootPane().setDefaultButton(btnAceptar);
        this.add(btnAceptar,"South");
        
        ejecutar();
    }
    
    private void agregarEscuchadores(){
        btnAceptar.addActionListener(new ActionListener(){
            public void actionPerformed( ActionEvent evt){
                aceptar();
            }
        });
        
        tabla.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent key){                   
                if(key.getKeyCode()==KeyEvent.VK_ENTER)
                    aceptar();
            }
        });
        
        tabla.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mouse){
                if(mouse.getClickCount()==2)
                        aceptar();
            }
        });
        
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    private void generarTabla(){
        ArrayList<String> listaCabeceras = obtenerCabeceras();
        String[] cabeceras = listaCabeceras.toArray(new String[listaCabeceras.size()]);
        
        int campos = cabeceras.length;
        int registros = lista.size();
        String matriz[][]=new String[registros][campos];
        
        for(int i=0;i<registros;i++){
            matriz[i][ListaDeValores.LLAVE] = String.valueOf(lista.get(i).getLlave());
            matriz[i][ListaDeValores.VALOR] = lista.get(i).getValor();
            for(int campoExtra=2; campoExtra<campos; campoExtra++)
                matriz[i][campoExtra] = lista.get(i).getRegistroExtra().get(campoExtra-2);
        }
        
        modelo = new DefaultTableModel(matriz,cabeceras){
            @Override
            public boolean isCellEditable(int fila, int columna) {
                    return false;
            }
        };
        
        tabla = new JTable(modelo){            
            private static final long serialVersionUID = 1L;          
            private int anchoTabla;
            
//            @Override
//            public void paint(Graphics graficos) {                
//                /*Redimensionar las columnas*/
//                anchoTabla = getWidth();
//
//                int[] anchos = {porcentuar(15),porcentuar(85)};
//                for(int i=0;i<tabla.getColumnCount();i++)
//                    tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
//                
//                super.paint(graficos);
//            }
            
            private int porcentuar(int numero){
                return (numero*anchoTabla)/100;
            }
        };
        
        //tabla.setFillsViewportHeight(true);
        tabla.setShowGrid(false);
        tabla.setOpaque(false);
        
        /*Agregar Scroll a la tabla*/
        TableRowSorter ordenador = new TableRowSorter<TableModel>(modelo);
        tabla.setRowSorter(ordenador);
        JScrollPane scrollPane = new JScrollPane(tabla);
        
        this.add(scrollPane,"Center");
    }
    
    private ArrayList<String> obtenerCabeceras(){
        ArrayList<String> cabeceras = listaDeValores.getNombresColumnas();
        int columnas = cabeceras.size();
                
        ArrayList<String> valores = new ArrayList<String>();
        valores.add(cabeceras.get(ListaDeValores.LLAVE));
        valores.add(cabeceras.get(ListaDeValores.VALOR));
        
        for(int campoExtra=2; campoExtra<columnas; campoExtra++)
            valores.add(cabeceras.get(campoExtra));
        
        return valores;    
    }
    
    private void ejecutar(){
        listaDeValores = new ListaDeValores(elementos);
        lista = listaDeValores.getResultado();
        generarTabla();
    }
    
    private void aceptar(){
        int fila = tabla.getSelectedRow();
        
        componentesTxt.get(ListaDeValores.LLAVE).setText(obtenRegistro(fila,ListaDeValores.LLAVE));
        componentesTxt.get(ListaDeValores.VALOR).setText(obtenRegistro(fila,ListaDeValores.VALOR));
        
        salir();
    }
    
    private String obtenRegistro(int fila, int columna){
        String registro = String.valueOf(tabla.getValueAt(fila, columna));
        
        return registro;
    }
    
    private void salir(){
        this.dispose();
    }
}
