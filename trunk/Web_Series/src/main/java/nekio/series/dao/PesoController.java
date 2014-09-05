package nekio.series.dao;

import nekio.series.dto.Peso;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "pesoController")
@ViewScoped
public class PesoController extends AbstractController<Peso> {

    @Inject
    private ProyectoController proyectoCollectionController;

    public PesoController() {
        // Inform the Abstract parent controller of the concrete Peso?cap_first Entity
        super(Peso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Proyecto entities that
     * are retrieved from Peso?cap_first and returns the navigation outcome.
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
