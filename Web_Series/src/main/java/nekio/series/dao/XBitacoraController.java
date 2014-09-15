package nekio.series.dao;

import nekio.series.dto.XBitacora;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "xBitacoraController")
@ViewScoped
public class XBitacoraController extends AbstractController<XBitacora> {

    @Inject
    private XUsuarioController idUsuarioController;

    public XBitacoraController() {
        // Inform the Abstract parent controller of the concrete XBitacora?cap_first Entity
        super(XBitacora.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idUsuarioController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the XUsuario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUsuario(ActionEvent event) {
        if (this.getSelected() != null && idUsuarioController.getSelected() == null) {
            idUsuarioController.setSelected(this.getSelected().getIdUsuario());
        }
    }
}
