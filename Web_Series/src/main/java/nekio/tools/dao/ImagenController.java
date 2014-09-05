package nekio.tools.dao;

import nekio.tools.dto.Imagen;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "imagenController")
@ViewScoped
public class ImagenController extends AbstractController<Imagen> {

    @Inject
    private SistemaController idSistemaController;
    @Inject
    private CarpetaController idCarpetaController;

    public ImagenController() {
        // Inform the Abstract parent controller of the concrete Imagen?cap_first Entity
        super(Imagen.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idSistemaController.setSelected(null);
        idCarpetaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Sistema controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSistema(ActionEvent event) {
        if (this.getSelected() != null && idSistemaController.getSelected() == null) {
            idSistemaController.setSelected(this.getSelected().getIdSistema());
        }
    }

    /**
     * Sets the "selected" attribute of the Carpeta controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCarpeta(ActionEvent event) {
        if (this.getSelected() != null && idCarpetaController.getSelected() == null) {
            idCarpetaController.setSelected(this.getSelected().getIdCarpeta());
        }
    }
}
