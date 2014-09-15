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
    @Inject
    private XRangoController idRangoController;
    @Inject
    private XImagenController idImagenController;

    public WebController() {
        // Inform the Abstract parent controller of the concrete Web?cap_first Entity
        super(Web.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRangoController.setSelected(null);
        idImagenController.setSelected(null);
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

    /**
     * Sets the "selected" attribute of the XImagen controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdImagen(ActionEvent event) {
        if (this.getSelected() != null && idImagenController.getSelected() == null) {
            idImagenController.setSelected(this.getSelected().getIdImagen());
        }
    }
}
