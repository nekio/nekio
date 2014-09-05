package nekio.series.dao;

import nekio.series.dto.TipoMensaje;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoMensajeController")
@ViewScoped
public class TipoMensajeController extends AbstractController<TipoMensaje> {

    @Inject
    private MensajePrivadoController mensajePrivadoCollectionController;

    public TipoMensajeController() {
        // Inform the Abstract parent controller of the concrete TipoMensaje?cap_first Entity
        super(TipoMensaje.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of MensajePrivado entities
     * that are retrieved from TipoMensaje?cap_first and returns the navigation
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
