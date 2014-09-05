package nekio.series.dao;

import nekio.series.dto.EnlaceStatus;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "enlaceStatusController")
@ViewScoped
public class EnlaceStatusController extends AbstractController<EnlaceStatus> {

    @Inject
    private EnlaceController enlaceCollectionController;

    public EnlaceStatusController() {
        // Inform the Abstract parent controller of the concrete EnlaceStatus?cap_first Entity
        super(EnlaceStatus.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Enlace entities that are
     * retrieved from EnlaceStatus?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Enlace page
     */
    public String navigateEnlaceCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Enlace_items", this.getSelected().getEnlaceCollection());
        }
        return "/view/enlace/index";
    }

}
