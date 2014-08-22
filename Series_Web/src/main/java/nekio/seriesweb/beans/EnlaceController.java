package nekio.seriesweb.beans;

import nekio.seriesweb.dto.Enlace;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "enlaceController")
@ViewScoped
public class EnlaceController extends AbstractController<Enlace> {

    @Inject
    private SerieController idSerieController;
    @Inject
    private EpisodioController idEpisodioController;
    @Inject
    private EnlaceStatusController idEnlaceStatusController;
    @Inject
    private ProyectoController idProyectoController;

    public EnlaceController() {
        // Inform the Abstract parent controller of the concrete Enlace?cap_first Entity
        super(Enlace.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idSerieController.setSelected(null);
        idEpisodioController.setSelected(null);
        idEnlaceStatusController.setSelected(null);
        idProyectoController.setSelected(null);
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

    /**
     * Sets the "selected" attribute of the EnlaceStatus controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEnlaceStatus(ActionEvent event) {
        if (this.getSelected() != null && idEnlaceStatusController.getSelected() == null) {
            idEnlaceStatusController.setSelected(this.getSelected().getIdEnlaceStatus());
        }
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
}
