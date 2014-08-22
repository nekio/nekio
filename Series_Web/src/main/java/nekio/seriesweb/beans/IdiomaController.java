package nekio.seriesweb.beans;

import nekio.seriesweb.dto.Idioma;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "idiomaController")
@ViewScoped
public class IdiomaController extends AbstractController<Idioma> {

    @Inject
    private ProyectoController proyectoCollectionController;
    @Inject
    private ObtenidoController obtenidoCollectionController;
    @Inject
    private ObtenidoController obtenidoCollection1Controller;
    @Inject
    private SerieController serieCollectionController;

    public IdiomaController() {
        // Inform the Abstract parent controller of the concrete Idioma?cap_first Entity
        super(Idioma.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Proyecto entities that
     * are retrieved from Idioma?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Proyecto page
     */
    public String navigateProyectoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Proyecto_items", this.getSelected().getProyectoCollection());
        }
        return "/view/proyecto/index";
    }

    /**
     * Sets the "items" attribute with a collection of Obtenido entities that
     * are retrieved from Idioma?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Obtenido page
     */
    public String navigateObtenidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Obtenido_items", this.getSelected().getObtenidoCollection());
        }
        return "/view/obtenido/index";
    }

    /**
     * Sets the "items" attribute with a collection of Obtenido entities that
     * are retrieved from Idioma?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Obtenido page
     */
    public String navigateObtenidoCollection1() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Obtenido_items", this.getSelected().getObtenidoCollection1());
        }
        return "/view/obtenido/index";
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Idioma?cap_first and returns the navigation outcome.
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
