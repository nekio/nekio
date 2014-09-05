package nekio.series.dao;

import nekio.series.dto.TipoDistintivo;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoDistintivoController")
@ViewScoped
public class TipoDistintivoController extends AbstractController<TipoDistintivo> {

    @Inject
    private DistintivoController distintivoCollectionController;

    public TipoDistintivoController() {
        // Inform the Abstract parent controller of the concrete TipoDistintivo?cap_first Entity
        super(TipoDistintivo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Distintivo entities that
     * are retrieved from TipoDistintivo?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Distintivo page
     */
    public String navigateDistintivoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Distintivo_items", this.getSelected().getDistintivoCollection());
        }
        return "/view/distintivo/index";
    }

}
