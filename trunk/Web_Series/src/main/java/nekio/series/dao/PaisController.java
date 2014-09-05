package nekio.series.dao;

import nekio.series.dto.Pais;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "paisController")
@ViewScoped
public class PaisController extends AbstractController<Pais> {

    @Inject
    private ColaboradorController colaboradorCollectionController;
    @Inject
    private SerieController serieCollectionController;

    public PaisController() {
        // Inform the Abstract parent controller of the concrete Pais?cap_first Entity
        super(Pais.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Colaborador entities that
     * are retrieved from Pais?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Colaborador page
     */
    public String navigateColaboradorCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Colaborador_items", this.getSelected().getColaboradorCollection());
        }
        return "/view/colaborador/index";
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Pais?cap_first and returns the navigation outcome.
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
