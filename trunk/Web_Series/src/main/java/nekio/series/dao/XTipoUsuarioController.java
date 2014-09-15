package nekio.series.dao;

import nekio.series.dto.XTipoUsuario;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "xTipoUsuarioController")
@ViewScoped
public class XTipoUsuarioController extends AbstractController<XTipoUsuario> {

    @Inject
    private XUsuarioController xUsuarioCollectionController;
    @Inject
    private XPrivilegioController xPrivilegioController;

    public XTipoUsuarioController() {
        // Inform the Abstract parent controller of the concrete XTipoUsuario?cap_first Entity
        super(XTipoUsuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        xPrivilegioController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of XUsuario entities that
     * are retrieved from XTipoUsuario?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for XUsuario page
     */
    public String navigateXUsuarioCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("XUsuario_items", this.getSelected().getXUsuarioCollection());
        }
        return "/view/xUsuario/index";
    }

    /**
     * Sets the "selected" attribute of the XPrivilegio controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareXPrivilegio(ActionEvent event) {
        if (this.getSelected() != null && xPrivilegioController.getSelected() == null) {
            xPrivilegioController.setSelected(this.getSelected().getXPrivilegio());
        }
    }
}
