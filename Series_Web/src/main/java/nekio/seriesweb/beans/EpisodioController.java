package nekio.seriesweb.beans;

import nekio.seriesweb.dto.Episodio;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "episodioController")
@ViewScoped
public class EpisodioController extends AbstractController<Episodio> {

    @Inject
    private PasswordController passwordCollectionController;
    @Inject
    private PendienteController pendienteCollectionController;
    @Inject
    private EnlaceController enlaceCollectionController;
    @Inject
    private ObtenidoController obtenidoCollectionController;
    @Inject
    private SerieController idSerieController;
    @Inject
    private TipoEpisodioController idTipoEpisodioController;

    public EpisodioController() {
        // Inform the Abstract parent controller of the concrete Episodio?cap_first Entity
        super(Episodio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idSerieController.setSelected(null);
        idTipoEpisodioController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Password entities that
     * are retrieved from Episodio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Password page
     */
    public String navigatePasswordCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Password_items", this.getSelected().getPasswordCollection());
        }
        return "/view/password/index";
    }

    /**
     * Sets the "items" attribute with a collection of Pendiente entities that
     * are retrieved from Episodio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Pendiente page
     */
    public String navigatePendienteCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pendiente_items", this.getSelected().getPendienteCollection());
        }
        return "/view/pendiente/index";
    }

    /**
     * Sets the "items" attribute with a collection of Enlace entities that are
     * retrieved from Episodio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Enlace page
     */
    public String navigateEnlaceCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Enlace_items", this.getSelected().getEnlaceCollection());
        }
        return "/view/enlace/index";
    }

    /**
     * Sets the "items" attribute with a collection of Obtenido entities that
     * are retrieved from Episodio?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Obtenido page
     */
    public String navigateObtenidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Obtenido_items", this.getSelected().getObtenidoCollection());
        }
        return "/view/obtenido/index";
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
     * Sets the "selected" attribute of the TipoEpisodio controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoEpisodio(ActionEvent event) {
        if (this.getSelected() != null && idTipoEpisodioController.getSelected() == null) {
            idTipoEpisodioController.setSelected(this.getSelected().getIdTipoEpisodio());
        }
    }
}
