package nekio.seriesweb.beans;

import nekio.seriesweb.dto.Almacen;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "almacenController")
@ViewScoped
public class AlmacenController extends AbstractController<Almacen> {

    @Inject
    private ObtenidoController obtenidoCollectionController;

    public AlmacenController() {
        // Inform the Abstract parent controller of the concrete Almacen?cap_first Entity
        super(Almacen.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Obtenido entities that
     * are retrieved from Almacen?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Obtenido page
     */
    public String navigateObtenidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Obtenido_items", this.getSelected().getObtenidoCollection());
        }
        return "/view/obtenido/index";
    }

}
