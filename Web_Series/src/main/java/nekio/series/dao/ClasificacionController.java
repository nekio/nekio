package nekio.series.dao;

import nekio.series.dto.Clasificacion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "clasificacionController")
@ViewScoped
public class ClasificacionController extends AbstractController<Clasificacion> {

    @Inject
    private SerieController serieCollectionController;

    public ClasificacionController() {
        // Inform the Abstract parent controller of the concrete Clasificacion?cap_first Entity
        super(Clasificacion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Clasificacion?cap_first and returns the navigation
     * outcome.
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
