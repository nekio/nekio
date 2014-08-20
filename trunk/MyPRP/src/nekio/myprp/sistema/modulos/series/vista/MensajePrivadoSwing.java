package nekio.myprp.sistema.modulos.series.vista;

/**
 *
 * @author Nekio
 */

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import nekio.myprp.recursos.herramientas.Convertidor;
import nekio.myprp.recursos.herramientas.Mensaje;
import nekio.myprp.recursos.utilerias.Fecha;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.Idioma;
import nekio.myprp.recursos.utilerias.gui.swing.BD_Navegador;
import nekio.myprp.recursos.utilerias.gui.swing.PanelFormulario;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingMaestro;
import nekio.myprp.sistema.modulos.series.dto.MensajePrivadoDTO;

public class MensajePrivadoSwing extends SwingMaestro{
    private static final long serialVersionUID = 1L;
    
    private final MensajePrivadoBD_M BDManipulador = new MensajePrivadoBD_M(this);
    private final BD_Navegador BDNavegador = new BD_Navegador(this);
    
    private final Dimension DIMENSION = new Dimension(720, 600);
    
    private Container contenedor;
    private JPanel pnlContenido;
    private PanelFormulario pnlFormulario;
    private List<MensajePrivadoDTO> listaDTO;
    private MensajePrivadoDTO ultimoDtoSeleccionado;
    private MensajePrivadoDTO dto;
    private int indiceDTO;
    private boolean nuevo;
    
    public MensajePrivadoSwing(List<MensajePrivadoDTO> listaDTO){
        this.setTitle(Globales.NOMBRE_APP + " - " + Idioma.obtenerTexto(Idioma.PROP_MENU, "mensajePrivado"));
        
        this.contenedor = this.getContentPane();
        this.setSize(DIMENSION);
        this.setMinimumSize(DIMENSION);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        
        this.listaDTO = listaDTO;
        this.indiceDTO = 0; // Para cargar el primer registro del DTO
        nuevo = false;
        
        agregarComponentes(); 
        agregarEscuchadores();
        
        this.setVisible(true);
    }

    @Override
    public void agregarComponentes() {
        pnlContenido = new JPanel(new BorderLayout());
        
        // Navegador de la Base de Datos
        pnlContenido.add(BDNavegador, "North");
        
        // Registros
        pnlContenido.add(cargarDTO(), "Center");
        
        // Manipulador de la Base de Datos
        pnlContenido.add(BDManipulador, "South");
        
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
        
        try{
            for(DTO dto:listaDTO)
                this.listaDTO.add((MensajePrivadoDTO) dto);
        }catch(Exception e){}
        
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
    
    private JPanel cargarDTO(){        
        JPanel pnlRegistro = new JPanel(new BorderLayout());
                
        List<String> camposBD = null;
        List valoresBD = null;
        List<String> tablasForaneas = null;
        List<Globales.TipoDato> tiposDatoBD = null;
        List<String> valorLOV = null;
        List<List> camposExtrasLOV = null;
        if(listaDTO != null){
            if(listaDTO.size() != 0){
                dto = listaDTO.get(indiceDTO);
                ultimoDtoSeleccionado = dto;
            }else{                
                dto = new MensajePrivadoDTO();
                BDNavegador.habilitarTodo(false);
            }
        }
        
        dto.confirmarDTO();

        camposBD = dto.getCampos();
        if(!nuevo)
            valoresBD = dto.getValores();
        tablasForaneas = dto.getTablasForaneas();
        tiposDatoBD = dto.getTipoDatos();
        valorLOV = dto.getLOVValores();
        camposExtrasLOV = dto.getCamposExtrasLOV();
        
        pnlFormulario = new PanelFormulario(tablasForaneas, camposBD, valoresBD, tiposDatoBD, valorLOV, camposExtrasLOV, Globales.BD_DESC_ESQUEMA, nuevo);
        pnlRegistro.add(pnlFormulario , "Center");
        
        nuevo = false;
        
        return pnlRegistro;
    }

    @Override
    public void buscar(String filtro) {
        
    }
    
    public MensajePrivadoDTO getParametros(){
        MensajePrivadoDTO parametros = null;
        
        List objetosCampos = pnlFormulario.getObjetosCampos();
        
        try{
            JTextField tmpIdMensajePrivado = (JTextField)objetosCampos.get(0);
            //JTextField tmpIdUsuario = (JTextField)objetosCampos.get(1); //No se considera obtener el Id Usuario desde el formulario
            JTextField tmpIdTipoMensaje = (JTextField)objetosCampos.get(1);
            JTextField tmpIdMpRelacionado = (JTextField)objetosCampos.get(2);
            JTextField tmpIdWeb = (JTextField)objetosCampos.get(3);
            JTextField tmpIdColaborador = (JTextField)objetosCampos.get(4);
            JTextArea tmpMensaje = (JTextArea)objetosCampos.get(5);
            JTextField tmpFecha = (JTextField)objetosCampos.get(6);
            JCheckBox tmpRecibidoEnviado = (JCheckBox)objetosCampos.get(7);
            JCheckBox tmpAtendido = (JCheckBox)objetosCampos.get(8);
            
            parametros = new MensajePrivadoDTO();
            parametros.setIdMensajePrivado(Convertidor.aEntero(tmpIdMensajePrivado.getText()));
            parametros.setIdUsuario(Globales.APP_USUARIO.getId());
            parametros.setIdTipoMensaje(Convertidor.aEntero(tmpIdTipoMensaje.getText()));
            parametros.setIdMpRelacionado(Convertidor.aEntero(tmpIdMpRelacionado.getText()));
            parametros.setIdWeb(Convertidor.aEntero(tmpIdWeb.getText()));
            parametros.setIdColaborador(Convertidor.aEntero(tmpIdColaborador.getText()));
            parametros.setMensaje(tmpMensaje.getText());
            parametros.setFecha(Fecha.obtenerFecha(tmpFecha.getText(), Fecha.FORMATO_COMPLETO));
            parametros.setRecibidoEnviado(tmpRecibidoEnviado.isSelected());
            parametros.setAtendido(tmpAtendido.isSelected());
        }catch(Exception e){}
        
        if(dto == null)
            new Mensaje(Idioma.obtenerTexto(Idioma.PROP_ACCIONES, "noFilaSeleccionada"), Mensaje.MSJ_ADVERTENCIA);
        
        return parametros;
    }

    @Override
    public void nuevo() {
        nuevo = true;
        recargar((List)this.listaDTO);
    }

    @Override
    public void insertar() {
        nuevo = false;
        recargar((List)this.listaDTO); 
    }

    @Override
    public void modificar() {
        
    }

    @Override
    public void eliminar() {
       nuevo = false;
       recargar((List)this.listaDTO); 
    }

    @Override
    public void cancelar() {
        nuevo = false;
        recargar((List)this.listaDTO);
    }
    
    public MensajePrivadoDTO getUltimoDtoSeleccionado(){
        return ultimoDtoSeleccionado;
    }
}
