package nekio.myprp.sistema.modulos.series.vista;

/**
 *
 * @author Nekio
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.gui.swing.PanelGUI;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingMaestro;
import nekio.myprp.sistema.modulos.series.dto.MensajePrivadoDTO;

public class MensajesPrivados extends SwingMaestro{
    private static final long serialVersionUID = 1L;
    
    private final Dimension DIMENSION = new Dimension(720, 550);
    
    private Container contenedor;
    private JPanel pnlContenido;
    private List<MensajePrivadoDTO> listaDTO;
    
    public MensajesPrivados(List<MensajePrivadoDTO> listaDTO){
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
    public void agregarComponentes() {
        pnlContenido = new JPanel(new BorderLayout());
                
        List<String> camposBD = null;
        List valoresBD = null;
        List<Globales.TipoDato> tiposDatoBD = null;
        if(listaDTO != null){
            if(listaDTO.size() != 0){
                MensajePrivadoDTO dto = listaDTO.get(0); // Tomar el primer registro
                dto.confirmarDTO();

                camposBD = dto.getCampos();
                valoresBD = dto.getValores();
                tiposDatoBD = dto.getTipoDatos();
            }
        }
        
        pnlContenido.add(new PanelGUI(camposBD, valoresBD, tiposDatoBD), "North");
        
        contenedor.add(pnlContenido);
    }

    @Override
    public void agregarEscuchadores() {
        addWindowListener( new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent evt){
                salir();
            }
        });
    }
    
    @Override
    public void recargar(List<DTO> listaDTO){
        this.listaDTO = new ArrayList<MensajePrivadoDTO>();
        for(DTO dto:listaDTO)
            this.listaDTO.add((MensajePrivadoDTO) dto);
        
        pnlContenido.setVisible(false);
        pnlContenido= null;
        
        agregarComponentes();
    }

    @Override
    public void navegar(int accion) {
        
    }

    @Override
    public void buscar(String filtro) {
        
    }
}
