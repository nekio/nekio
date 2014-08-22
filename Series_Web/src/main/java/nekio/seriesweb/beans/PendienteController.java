package nekio.seriesweb.beans;

import nekio.seriesweb.dto.Pendiente;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pendienteController")
@ViewScoped
public class PendienteController extends AbstractController<Pendiente> {

    @Inject
    private ProyectoController idProyectoController;
    @Inject
    private EpisodioController idEpisodioController;

    public PendienteController() {
        // Inform the Abstract parent controller of the concrete Pendiente?cap_first Entity
        super(Pendiente.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idProyectoController.setSelected(null);
        idEpisodioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Proyecto controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdProyecto(ActionEvent event) {
        if (this.getSelected() != null && idProyectoController.getSelected() == null) {
            idProyectoController.setSelected(this.getSelected().getIdProyecto());
        }
    }

    /**
     * Sets the "selected" attribute of the Episodio controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEpisodio(ActionEvent event) {
        if (this.getSelected() != null && idEpisodioController.getSelected() == null) {
            idEpisodioController.setSelected(this.getSelected().getIdEpisodio());
        }
    }
}
