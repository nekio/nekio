package nekio.seriesweb.beans;

import nekio.seriesweb.dto.TipoSerie;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "tipoSerieController")
@ViewScoped
public class TipoSerieController extends AbstractController<TipoSerie> {

    @Inject
    private SerieController serieCollectionController;

    public TipoSerieController() {
        // Inform the Abstract parent controller of the concrete TipoSerie?cap_first Entity
        super(TipoSerie.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from TipoSerie?cap_first and returns the navigation outcome.
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
