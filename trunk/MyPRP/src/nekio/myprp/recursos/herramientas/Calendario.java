package nekio.myprp.recursos.herramientas;

/**
 *
 * @author Nekio
 */

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.text.JTextComponent;
import jcalendar.DateListener;
import jcalendar.JCalendar;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingJFrame;

public class Calendario extends SwingJFrame{
    private static final long serialVersionUID = 1L;
    
    private JCalendar calendario;
    private JButton btnAceptar;
    private JTextComponent componente;
    private Date fecha;

    public Calendario(String titulo, JTextComponent componente, Point ubicacion){       
        this.componente = componente;
        
        this.setTitle(titulo);
        this.setSize(320,230);
        
        if(ubicacion == null)
            this.setLocationRelativeTo(null);
        else
            this.setLocation(ubicacion);

        this.setResizable(false);

        agregarComponentes();
        agregarEscuchadores();

        setVisible(true);
    }

    @Override
    public void agregarComponentes(){
        fecha=null;
        
        calendario = new JCalendar();
        getContentPane().add(calendario,BorderLayout.CENTER);
        
        btnAceptar = new JButton(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "aceptar"));
        getContentPane().add(btnAceptar,BorderLayout.SOUTH);
    }

    @Override
    public void agregarEscuchadores(){
        calendario.addDateListener(new DateListener(){
            @Override
            public void dateChanged(Calendar calendario) {
                long segundosDiferencia = Math.abs((calendario.getTime().getTime() - new Date().getTime())/1000);
                
                if(segundosDiferencia > 60){
                    calendario.set(Calendar.HOUR, -12);
                    calendario.set(Calendar.MINUTE, 0);
                    calendario.set(Calendar.SECOND, 0);
                }
                
                fecha = calendario.getTime();
            }
        });
        
        btnAceptar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt){
                if(fecha != null){
                    componente.setText(Fecha.obtenerFechaFormateada(fecha, Fecha.FORMATO_COMPLETO));
                    salir();
                }else
                    new Mensaje("No se ha seleccionado ninguna fecha", Mensaje.MSJ_ERROR);
            }
        });

        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
}