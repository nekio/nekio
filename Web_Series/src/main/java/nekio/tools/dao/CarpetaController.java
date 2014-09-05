package nekio.tools.dao;

import nekio.tools.dto.Carpeta;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "carpetaController")
@ViewScoped
public class CarpetaController extends AbstractController<Carpeta> {

    @Inject
    private ImagenController imagenCollectionController;

    public CarpetaController() {
        // Inform the Abstract parent controller of the concrete Carpeta?cap_first Entity
        super(Carpeta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Imagen entities that are
     * retrieved from Carpeta?cap_first and returns the navigation outcome.
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
