package nekio.series.dao;

import nekio.series.dto.XCarpeta;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "xCarpetaController")
@ViewScoped
public class XCarpetaController extends AbstractController<XCarpeta> {

    @Inject
    private XImagenController xImagenCollectionController;

    public XCarpetaController() {
        // Inform the Abstract parent controller of the concrete XCarpeta?cap_first Entity
        super(XCarpeta.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of XImagen entities that are
     * retrieved from XCarpeta?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for XImagen page
     */
    public String navigateXImagenCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("XImagen_items", this.getSelected().getXImagenCollection());
        }
        return "/view/xImagen/index";
    }

}
