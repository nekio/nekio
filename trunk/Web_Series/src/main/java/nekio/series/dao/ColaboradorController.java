package nekio.series.dao;

import nekio.series.dto.Colaborador;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "colaboradorController")
@ViewScoped
public class ColaboradorController extends AbstractController<Colaborador> {

    @Inject
    private ProyectoDetController proyectoDetCollectionController;
    @Inject
    private PaisController idPaisController;
    @Inject
    private MensajePrivadoController mensajePrivadoCollectionController;

    public ColaboradorController() {
        // Inform the Abstract parent controller of the concrete Colaborador?cap_first Entity
        super(Colaborador.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPaisController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of ProyectoDet entities that
     * are retrieved from Colaborador?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for ProyectoDet page
     */
    public String navigateProyectoDetCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ProyectoDet_items", this.getSelected().getProyectoDetCollection());
        }
        return "/view/proyectoDet/index";
    }

    /**
     * Sets the "selected" attribute of the Pais controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPais(ActionEvent event) {
        if (this.getSelected() != null && idPaisController.getSelected() == null) {
            idPaisController.setSelected(this.getSelected().getIdPais());
        }
    }

    /**
     * Sets the "items" attribute with a collection of MensajePrivado entities
     * that are retrieved from Colaborador?cap_first and returns the navigation
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

}
