package nekio.series.dao;

import nekio.series.dto.XPrivilegio;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "xPrivilegioController")
@ViewScoped
public class XPrivilegioController extends AbstractController<XPrivilegio> {

    @Inject
    private XTipoUsuarioController xTipoUsuarioController;

    public XPrivilegioController() {
        // Inform the Abstract parent controller of the concrete XPrivilegio?cap_first Entity
        super(XPrivilegio.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        xTipoUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the XTipoUsuario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareXTipoUsuario(ActionEvent event) {
        if (this.getSelected() != null && xTipoUsuarioController.getSelected() == null) {
            xTipoUsuarioController.setSelected(this.getSelected().getXTipoUsuario());
        }
    }
}
