package nekio.series.dao;

import nekio.series.dto.Web;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "webController")
@ViewScoped
public class WebController extends AbstractController<Web> {

    @Inject
    private ProyectoController proyectoCollectionController;
    @Inject
    private MensajePrivadoController mensajePrivadoCollectionController;

    public WebController() {
        // Inform the Abstract parent controller of the concrete Web?cap_first Entity
        super(Web.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Proyecto entities that
     * are retrieved from Web?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of MensajePrivado entities
     * that are retrieved from Web?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for MensajePrivado page
     */
    public String navigateMensajePrivadoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("MensajePrivado_items", this.getSelected().getMensajePrivadoCollection());
        }
        return "/view/mensajePrivado/index";
    }

}
