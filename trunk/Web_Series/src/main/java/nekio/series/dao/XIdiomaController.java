package nekio.series.dao;

import nekio.series.dto.XIdioma;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "xIdiomaController")
@ViewScoped
public class XIdiomaController extends AbstractController<XIdioma> {

    @Inject
    private XUsuarioController xUsuarioCollectionController;

    public XIdiomaController() {
        // Inform the Abstract parent controller of the concrete XIdioma?cap_first Entity
        super(XIdioma.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of XUsuario entities that
     * are retrieved from XIdioma?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for XUsuario page
     */
    public String navigateXUsuarioCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("XUsuario_items", this.getSelected().getXUsuarioCollection());
        }
        return "/view/xUsuario/index";
    }

}
