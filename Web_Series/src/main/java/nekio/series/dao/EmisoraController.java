package nekio.series.dao;

import nekio.series.dto.Emisora;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "emisoraController")
@ViewScoped
public class EmisoraController extends AbstractController<Emisora> {

    @Inject
    private SerieController serieCollectionController;

    public EmisoraController() {
        // Inform the Abstract parent controller of the concrete Emisora?cap_first Entity
        super(Emisora.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Emisora?cap_first and returns the navigation outcome.
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
