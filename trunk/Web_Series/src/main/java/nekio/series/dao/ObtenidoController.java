package nekio.series.dao;

import nekio.series.dto.Obtenido;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "obtenidoController")
@ViewScoped
public class ObtenidoController extends AbstractController<Obtenido> {

    @Inject
    private IdiomaController idSubtituloController;
    @Inject
    private IdiomaController idAudioController;
    @Inject
    private AlmacenController idAlmacenController;
    @Inject
    private ProyectoController idProyectoController;
    @Inject
    private EpisodioController idEpisodioController;

    public ObtenidoController() {
        // Inform the Abstract parent controller of the concrete Obtenido?cap_first Entity
        super(Obtenido.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idSubtituloController.setSelected(null);
        idAudioController.setSelected(null);
        idAlmacenController.setSelected(null);
        idProyectoController.setSelected(null);
        idEpisodioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Idioma controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSubtitulo(ActionEvent event) {
        if (this.getSelected() != null && idSubtituloController.getSelected() == null) {
            idSubtituloController.setSelected(this.getSelected().getIdSubtitulo());
        }
    }

    /**
     * Sets the "selected" attribute of the Idioma controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdAudio(ActionEvent event) {
        if (this.getSelected() != null && idAudioController.getSelected() == null) {
            idAudioController.setSelected(this.getSelected().getIdAudio());
        }
    }

    /**
     * Sets the "selected" attribute of the Almacen controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdAlmacen(ActionEvent event) {
        if (this.getSelected() != null && idAlmacenController.getSelected() == null) {
            idAlmacenController.setSelected(this.getSelected().getIdAlmacen());
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
