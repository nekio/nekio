package nekio.series.dao;

import nekio.series.dto.ProyectoDet;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "proyectoDetController")
@ViewScoped
public class ProyectoDetController extends AbstractController<ProyectoDet> {

    @Inject
    private TipoColaboracionController idTipoColaboracionController;
    @Inject
    private ColaboradorController idColaboradorController;
    @Inject
    private ProyectoController idProyectoController;

    public ProyectoDetController() {
        // Inform the Abstract parent controller of the concrete ProyectoDet?cap_first Entity
        super(ProyectoDet.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idTipoColaboracionController.setSelected(null);
        idColaboradorController.setSelected(null);
        idProyectoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the TipoColaboracion controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoColaboracion(ActionEvent event) {
        if (this.getSelected() != null && idTipoColaboracionController.getSelected() == null) {
            idTipoColaboracionController.setSelected(this.getSelected().getIdTipoColaboracion());
        }
    }

    /**
     * Sets the "selected" attribute of the Colaborador controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdColaborador(ActionEvent event) {
        if (this.getSelected() != null && idColaboradorController.getSelected() == null) {
            idColaboradorController.setSelected(this.getSelected().getIdColaborador());
        }
    }

    /**
     * Sets the "selected" attribute of the Proyecto controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdProyecto(ActionEvent event) {
        if (this.getSelected() != null && idProyectoController.getSelected() == null) {
            idProyectoController.setSelected(this.getSelected().getIdProyecto());
        }
    }
}
