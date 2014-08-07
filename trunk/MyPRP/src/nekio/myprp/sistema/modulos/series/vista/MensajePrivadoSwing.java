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
import nekio.myprp.recursos.utilerias.gui.swing.BD_Navegador;
import nekio.myprp.recursos.utilerias.gui.swing.PanelGUI;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingMaestro;
import nekio.myprp.sistema.modulos.series.dto.MensajePrivadoDTO;

public class MensajePrivadoSwing extends SwingMaestro{
    private static final long serialVersionUID = 1L;
    
    private final BD_Navegador BDNavegador = new BD_Navegador(this);
    
    private final Dimension DIMENSION = new Dimension(500, 600);
    
    private Container contenedor;
    private JPanel pnlContenido;
    private List<MensajePrivadoDTO> listaDTO;
    private MensajePrivadoDTO dto;
    private int indiceDTO;
    
    public MensajePrivadoSwing(List<MensajePrivadoDTO> listaDTO){
        this.setTitle(Globales.NOMBRE_APP + " - " + Idioma.obtenerTexto(Idioma.PROP_MENU, "mensajePrivado"));
        
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
        this.indiceDTO = 0; // Para cargar el primer registro del DTO
        cargarDTO();
        
        contenedor.add(BDNavegador, "North");
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
        int DTOsTotales=listaDTO.size();
        
        // Tener en cuenta que los DTOs se obtienen con orden DESC
        // Por lo que el primer DTO sera el ultimo registro
        // Y el ultimo DTO sera un registro mas antiguo
        // por tanto, la navegacion entre los DTOs, sera en sentido invertido
        switch(accion){
            case BD_Navegador.PRIMERO:
                indiceDTO = DTOsTotales-1; //el registro mas antiguo
                cargarDTO();
            break;
            case BD_Navegador.ANTERIOR:
                indiceDTO++;
                
                if(indiceDTO < DTOsTotales)
                    cargarDTO();
                else
                    indiceDTO--;
            break;
            case BD_Navegador.SIGUIENTE:
                indiceDTO--;
                
                if(indiceDTO > 0)
                    cargarDTO();
                else
                    indiceDTO++;
            break;
            case BD_Navegador.ULTIMO:    
                indiceDTO = 0;
                cargarDTO();
            break;
        }
        identificarDeshabilitados();
    }
    
    private void identificarDeshabilitados(){
        int DTOsTotales=listaDTO.size();
        
        if(indiceDTO >= DTOsTotales-1){
            super.getNavegadorBD().deshabilitarPrimero();
            super.getNavegadorBD().deshabilitarAnterior();
        }else if(indiceDTO <= 0){
            super.getNavegadorBD().deshabilitarUltimo();
            super.getNavegadorBD().deshabilitarSiguiente();
        }
    }
    
    private void cargarDTO(){
        if(pnlContenido != null){
            pnlContenido.setVisible(false);
            pnlContenido = null;
        }
        pnlContenido = new JPanel(new BorderLayout());
                
        List<String> camposBD = null;
        List valoresBD = null;
        List<String> tablasForaneas = null;
        List<Globales.TipoDato> tiposDatoBD = null;
        List<String> valorLOV = null;
        List<List> camposExtrasLOV = null;
        if(listaDTO != null){
            if(listaDTO.size() != 0){
                dto = listaDTO.get(indiceDTO);
                dto.confirmarDTO();

                camposBD = dto.getCampos();
                valoresBD = dto.getValores();
                tablasForaneas = dto.getTablasForaneas();
                tiposDatoBD = dto.getTipoDatos();
                valorLOV = dto.getLOVValores();
                camposExtrasLOV = dto.getCamposExtrasLOV();
            }else{
                BDNavegador.habilitarTodo(false);
            }
        }
        pnlContenido.add(new PanelGUI(tablasForaneas, camposBD, valoresBD, tiposDatoBD, valorLOV, camposExtrasLOV, Globales.BD_DESC_ESQUEMA), "Center");
        contenedor.add(pnlContenido, "Center");
    }

    @Override
    public void buscar(String filtro) {
        
    }
}
