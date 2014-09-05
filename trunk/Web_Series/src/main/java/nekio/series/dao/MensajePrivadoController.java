package nekio.series.dao;

import nekio.series.dto.MensajePrivado;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "mensajePrivadoController")
@ViewScoped
public class MensajePrivadoController extends AbstractController<MensajePrivado> {

    @Inject
    private ColaboradorController idColaboradorController;
    @Inject
    private WebController idWebController;
    @Inject
    private TipoMensajeController idTipoMensajeController;

    public MensajePrivadoController() {
        // Inform the Abstract parent controller of the concrete MensajePrivado?cap_first Entity
        super(MensajePrivado.class);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setMensajePrivadoPK(new nekio.series.dto.MensajePrivadoPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idColaboradorController.setSelected(null);
        idWebController.setSelected(null);
        idTipoMensajeController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Colaborador controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdColaborador(ActionEvent event) {
        if (this.getSelected() != null && idColaboradorController.getSelected() == null) {
            idColaboradorController.setSelected(this.getSelected().getIdColaborador());
        }
    }

    /**
     * Sets the "selected" attribute of the Web controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdWeb(ActionEvent event) {
        if (this.getSelected() != null && idWebController.getSelected() == null) {
            idWebController.setSelected(this.getSelected().getIdWeb());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoMensaje controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoMensaje(ActionEvent event) {
        if (this.getSelected() != null && idTipoMensajeController.getSelected() == null) {
            idTipoMensajeController.setSelected(this.getSelected().getIdTipoMensaje());
        }
    }
}
