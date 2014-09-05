package nekio.tools.dao;

import nekio.tools.dto.Sistema;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "sistemaController")
@ViewScoped
public class SistemaController extends AbstractController<Sistema> {

    @Inject
    private ImagenController imagenCollectionController;

    public SistemaController() {
        // Inform the Abstract parent controller of the concrete Sistema?cap_first Entity
        super(Sistema.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Imagen entities that are
     * retrieved from Sistema?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Imagen page
     */
    public String navigateImagenCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Imagen_items", this.getSelected().getImagenCollection());
        }
        return "/view/tools/imagen/index";
    }

}
