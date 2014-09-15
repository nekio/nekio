package nekio.series.dao;

import nekio.series.dto.TipoSerie;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoSerieController")
@ViewScoped
public class TipoSerieController extends AbstractController<TipoSerie> {

    @Inject
    private XRangoController idRangoController;
    @Inject
    private SerieController serieCollectionController;

    public TipoSerieController() {
        // Inform the Abstract parent controller of the concrete TipoSerie?cap_first Entity
        super(TipoSerie.class);
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
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from TipoSerie?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Serie page
     */
    public String navigateSerieCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Serie_items", this.getSelected().getSerieCollection());
        }
        return "/view/serie/index";
    }

}
