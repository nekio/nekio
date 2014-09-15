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
    @Inject
    private XRangoController idRangoController;

    public PesoController() {
        // Inform the Abstract parent controller of the concrete Peso?cap_first Entity
        super(Peso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRangoController.setSelected(null);
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
}
