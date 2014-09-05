package nekio.series.dao;

import nekio.series.dto.Distintivo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "distintivoController")
@ViewScoped
public class DistintivoController extends AbstractController<Distintivo> {

    @Inject
    private TipoDistintivoController idTipoDistintivoController;
    @Inject
    private SerieController serieCollectionController;

    public DistintivoController() {
        // Inform the Abstract parent controller of the concrete Distintivo?cap_first Entity
        super(Distintivo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idTipoDistintivoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoDistintivo controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoDistintivo(ActionEvent event) {
        if (this.getSelected() != null && idTipoDistintivoController.getSelected() == null) {
            idTipoDistintivoController.setSelected(this.getSelected().getIdTipoDistintivo());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Distintivo?cap_first and returns the navigation outcome.
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
