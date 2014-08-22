package nekio.seriesweb.beans;

import nekio.seriesweb.dto.MensajePrivado;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "mensajePrivadoController")
@ViewScoped
public class MensajePrivadoController extends AbstractController<MensajePrivado> {

    @Inject
    private WebController idWebController;
    @Inject
    private TipoMensajeController idTipoMensajeController;
    @Inject
    private ColaboradorController idColaboradorController;

    public MensajePrivadoController() {
        // Inform the Abstract parent controller of the concrete MensajePrivado?cap_first Entity
        super(MensajePrivado.class);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setMensajePrivadoPK(new nekio.seriesweb.dto.MensajePrivadoPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idWebController.setSelected(null);
        idTipoMensajeController.setSelected(null);
        idColaboradorController.setSelected(null);
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
}
