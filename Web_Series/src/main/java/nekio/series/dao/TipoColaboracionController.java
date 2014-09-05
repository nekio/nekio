package nekio.series.dao;

import nekio.series.dto.TipoColaboracion;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoColaboracionController")
@ViewScoped
public class TipoColaboracionController extends AbstractController<TipoColaboracion> {

    @Inject
    private ProyectoDetController proyectoDetCollectionController;

    public TipoColaboracionController() {
        // Inform the Abstract parent controller of the concrete TipoColaboracion?cap_first Entity
        super(TipoColaboracion.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of ProyectoDet entities that
     * are retrieved from TipoColaboracion?cap_first and returns the navigation
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

}
