package nekio.series.dao;

import nekio.series.dto.TipoEpisodio;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoEpisodioController")
@ViewScoped
public class TipoEpisodioController extends AbstractController<TipoEpisodio> {

    @Inject
    private XRangoController idRangoController;
    @Inject
    private EpisodioController episodioCollectionController;

    public TipoEpisodioController() {
        // Inform the Abstract parent controller of the concrete TipoEpisodio?cap_first Entity
        super(TipoEpisodio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRangoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the XRango controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRango(ActionEvent event) {
        if (this.getSelected() != null && idRangoController.getSelected() == null) {
            idRangoController.setSelected(this.getSelected().getIdRango());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Episodio entities that
     * are retrieved from TipoEpisodio?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Episodio page
     */
    public String navigateEpisodioCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Episodio_items", this.getSelected().getEpisodioCollection());
        }
        return "/view/episodio/index";
    }

}
