package listadevalores.gui;

/**
 * Clase para crear la GUI (Swing) de una lista de Valores
 *
 * @author Nekio
 * @version 27/04/2014
 *
 */

import herramientas.Conexion;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    
    private JTextComponent cmpLlave;
    private JTextComponent cmpDescripcion;
    
    
    public Swing(Elementos elementos, JTextComponent cmpLlave, JTextComponent cmpDescripcion){
        super("Lista De Valores");
        setSize(500,270);
        setLocationRelativeTo(null);
        
        this.elementos = elementos;
        this.cmpLlave = cmpLlave;
        this.cmpDescripcion = cmpDescripcion;
        
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
    
    private void generarTabla(int registros){
        String[] columnas = {"ID", "Descripcion",};
        String matriz[][]=new String[registros][columnas.length];
        
        for(int i=0;i<registros;i++){
            matriz[i][0]=String.valueOf(lista.get(i).getKey());
            matriz[i][1]=lista.get(i).getValue();
        }
        
        modelo = new DefaultTableModel(matriz,columnas){
            @Override
            public boolean isCellEditable(int fila, int columna) {
                    return false;
            }
        };
        
        tabla = new JTable(modelo){            
            private static final long serialVersionUID = 1L;          
            private int anchoTabla;
            
            @Override
            public void paint(Graphics graficos) {                
                /*Redimensionar las columnas*/
                anchoTabla = getWidth();

                int[] anchos = {porcentuar(15),porcentuar(85)};
                for(int i=0;i<tabla.getColumnCount();i++)
                    tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
                
                super.paint(graficos);
            }
            
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
    
    private void ejecutar(){
        try{
            Connection conexion = Conexion.getConexion();
            listaDeValores = new ListaDeValores(elementos);
            lista = listaDeValores.getResultado();
            generarTabla(lista.size());
        }catch(Exception e){}
    }
    
    private void aceptar(){
        int fila = tabla.getSelectedRow();
        LlaveValor resultado = listaDeValores.getResultado().get(fila);
        
        cmpLlave.setText(String.valueOf(resultado.getKey()));
        cmpDescripcion.setText(resultado.getValue());
        
        salir();
    }
    
    private void salir(){
        this.dispose();
    }
}
