package nekio.myprp.recursos.herramientas;

/**
 *
 * @author Nekio
 */

// <editor-fold defaultstate="collapsed" desc="Librerias"> 
import javax.swing.JOptionPane;
import nekio.myprp.recursos.utilerias.Globales;
// </editor-fold>

public class Mensaje {
    // <editor-fold defaultstate="collapsed" desc="Atributos"> 
    public static final int CERRAR = -1;
    public static final int CANCELAR = 0;
    public static final int ACEPTAR = 1;
    
    public static final int MSJ_INFORMACION = 1;
    public static final int MSJ_ADVERTENCIA = 2;
    public static final int MSJ_ERROR = 3;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Metodos"> 
    
    // <editor-fold defaultstate="collapsed" desc="Constructores"> 
    public Mensaje(){
        this(null);
    }
    
    public Mensaje(String mensaje){
        this(mensaje,0);
    }
    
    public Mensaje(String mensaje,int tipoMensaje){
        mostrarMensaje(mensaje,tipoMensaje);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Mostrar Mensaje"> 
    private void mostrarMensaje(String mensaje,int tipoMensaje){
        int tipo=0;
        
        switch(tipoMensaje){
            case 1:
               tipo=JOptionPane.INFORMATION_MESSAGE;
            break;
            case 2:
               tipo=JOptionPane.WARNING_MESSAGE;
            break;
            case 3:
               tipo=JOptionPane.ERROR_MESSAGE;
            break;
            default:
                tipo=JOptionPane.PLAIN_MESSAGE;
        }
        JOptionPane.showMessageDialog(null, mensaje, Globales.NOMBRE_APP, tipo);        
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Decidir"> 
    public static int decidir(String mensaje, String cancelar, String aceptar){        
        Object[] opciones = new String[2];
        opciones[0] = cancelar;
        opciones[1] = aceptar;
        
        int respuesta = JOptionPane.showOptionDialog(
                null,
                mensaje, 
                Globales.NOMBRE_APP, 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null,
                opciones,
                opciones[1]);
        
        return respuesta;
    }
    // </editor-fold>
    
    // </editor-fold>
}
