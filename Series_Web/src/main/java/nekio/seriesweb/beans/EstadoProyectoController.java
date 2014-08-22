package nekio.seriesweb.beans;

import nekio.seriesweb.dto.EstadoProyecto;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estadoProyectoController")
@ViewScoped
public class EstadoProyectoController extends AbstractController<EstadoProyecto> {

    @Inject
    private ProyectoController proyectoCollectionController;

    public EstadoProyectoController() {
        // Inform the Abstract parent controller of the concrete EstadoProyecto?cap_first Entity
        super(EstadoProyecto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Proyecto entities that
     * are retrieved from EstadoProyecto?cap_first and returns the navigation
     * outcome.
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
