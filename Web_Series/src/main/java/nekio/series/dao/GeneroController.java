package nekio.series.dao;

import nekio.series.dto.Genero;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "generoController")
@ViewScoped
public class GeneroController extends AbstractController<Genero> {

    @Inject
    private SerieController serieCollectionController;
    @Inject
    private SerieController serieCollection1Controller;
    @Inject
    private SerieController serieCollection2Controller;
    @Inject
    private SerieController serieCollection3Controller;
    @Inject
    private XRangoController idRangoController;

    public GeneroController() {
        // Inform the Abstract parent controller of the concrete Genero?cap_first Entity
        super(Genero.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRangoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Genero?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Serie page
     */
    public String navigateSerieCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Serie_items", this.getSelected().getSerieCollection());
        }
        return "/view/serie/index";
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Genero?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Serie page
     */
    public String navigateSerieCollection1() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Serie_items", this.getSelected().getSerieCollection1());
        }
        return "/view/serie/index";
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Genero?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Serie page
     */
    public String navigateSerieCollection2() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Serie_items", this.getSelected().getSerieCollection2());
        }
        return "/view/serie/index";
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Genero?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Serie page
     */
    public String navigateSerieCollection3() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Serie_items", this.getSelected().getSerieCollection3());
        }
        return "/view/serie/index";
    }

    /**
     * Sets the "selected" attribute of the XRango controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRango(ActionEvent event) {
        if (this.getSelected() != null && idRangoController.getSelected() == null) {
            idRangoController.setSelected(this.getSelected().getIdRango());
        }
    }
}
