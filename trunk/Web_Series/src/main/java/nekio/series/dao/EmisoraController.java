package nekio.series.dao;

import nekio.series.dto.Emisora;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "emisoraController")
@ViewScoped
public class EmisoraController extends AbstractController<Emisora> {

    @Inject
    private XImagenController idImagenController;
    @Inject
    private SerieController serieCollectionController;

    public EmisoraController() {
        // Inform the Abstract parent controller of the concrete Emisora?cap_first Entity
        super(Emisora.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idImagenController.setSelected(null);
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

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Emisora?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Serie page
     */
    public String navigateSerieCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Serie_items", this.getSelected().getSerieCollection());
        }
        return "/view/serie/index";
    }

}
