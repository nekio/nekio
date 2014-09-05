package nekio.series.dao;

import nekio.series.dto.Formato;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "formatoController")
@ViewScoped
public class FormatoController extends AbstractController<Formato> {

    @Inject
    private ProyectoController proyectoCollectionController;

    public FormatoController() {
        // Inform the Abstract parent controller of the concrete Formato?cap_first Entity
        super(Formato.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Proyecto entities that
     * are retrieved from Formato?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Proyecto page
     */
    public String navigateProyectoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Proyecto_items", this.getSelected().getProyectoCollection());
        }
        return "/view/proyecto/index";
    }

}
