package nekio.series.dao;

import nekio.series.dto.Televisora;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
@Named(value="televisoraController")
@ViewScoped
public class TelevisoraController extends AbstractController<Televisora> {

    @Inject
    private SerieController serieCollectionController;
    @Inject
    private XImagenController idImagenController;

    public TelevisoraController() {
        // Inform the Abstract parent controller of the concrete Televisora?cap_first Entity
        super(Televisora.class);
    }


    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idImagenController.setSelected(null);
    }

    /**
    * Sets the "items" attribute with a collection of Serie entities that are retrieved from Televisora?cap_first
     * and returns the navigation outcome.
     *
     * @return  navigation outcome for Serie page
     */
    public String navigateSerieCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Serie_items", this.getSelected().getSerieCollection());
        }
        return "/view/serie/index";
    }

    /**
     * Sets the "selected" attribute of the XImagen controller
     * in order to display its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdImagen(ActionEvent event) {
        if (this.getSelected() != null && idImagenController.getSelected() == null) {
            idImagenController.setSelected(this.getSelected().getIdImagen());
        }
    }
}
