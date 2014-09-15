package nekio.series.dao;

import nekio.series.dto.XUsuario;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "xUsuarioController")
@ViewScoped
public class XUsuarioController extends AbstractController<XUsuario> {

    @Inject
    private XBitacoraController xBitacoraCollectionController;
    @Inject
    private MensajePrivadoController mensajePrivadoCollectionController;
    @Inject
    private XIdiomaController idIdiomaController;
    @Inject
    private XRangoController idRangoController;
    @Inject
    private XTipoUsuarioController idTipoUsuarioController;

    public XUsuarioController() {
        // Inform the Abstract parent controller of the concrete XUsuario?cap_first Entity
        super(XUsuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idIdiomaController.setSelected(null);
        idRangoController.setSelected(null);
        idTipoUsuarioController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of XBitacora entities that
     * are retrieved from XUsuario?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for XBitacora page
     */
    public String navigateXBitacoraCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("XBitacora_items", this.getSelected().getXBitacoraCollection());
        }
        return "/view/xBitacora/index";
    }

    /**
     * Sets the "items" attribute with a collection of MensajePrivado entities
     * that are retrieved from XUsuario?cap_first and returns the navigation
     * outcome.
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
     * Sets the "selected" attribute of the XIdioma controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdIdioma(ActionEvent event) {
        if (this.getSelected() != null && idIdiomaController.getSelected() == null) {
            idIdiomaController.setSelected(this.getSelected().getIdIdioma());
        }
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
     * Sets the "selected" attribute of the XTipoUsuario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoUsuario(ActionEvent event) {
        if (this.getSelected() != null && idTipoUsuarioController.getSelected() == null) {
            idTipoUsuarioController.setSelected(this.getSelected().getIdTipoUsuario());
        }
    }
}
