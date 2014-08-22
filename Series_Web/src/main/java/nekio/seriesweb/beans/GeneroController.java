package nekio.seriesweb.beans;

import nekio.seriesweb.dto.Genero;
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

    public GeneroController() {
        // Inform the Abstract parent controller of the concrete Genero?cap_first Entity
        super(Genero.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
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

}
