package nekio.seriesweb.beans;

import nekio.seriesweb.dto.Password;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "passwordController")
@ViewScoped
public class PasswordController extends AbstractController<Password> {

    @Inject
    private ProyectoController idProyectoController;
    @Inject
    private SerieController idSerieController;
    @Inject
    private EpisodioController idEpisodioController;

    public PasswordController() {
        // Inform the Abstract parent controller of the concrete Password?cap_first Entity
        super(Password.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idProyectoController.setSelected(null);
        idSerieController.setSelected(null);
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
     * Sets the "selected" attribute of the Serie controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSerie(ActionEvent event) {
        if (this.getSelected() != null && idSerieController.getSelected() == null) {
            idSerieController.setSelected(this.getSelected().getIdSerie());
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
