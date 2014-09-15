package nekio.series.dao;

import nekio.series.dto.XImagen;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "xImagenController")
@ViewScoped
public class XImagenController extends AbstractController<XImagen> {

    @Inject
    private ColaboradorController colaboradorCollectionController;
    @Inject
    private EmisoraController emisoraCollectionController;
    @Inject
    private XCarpetaController idCarpetaController;
    @Inject
    private WebController webCollectionController;
    @Inject
    private SerieController serieCollectionController;
    @Inject
    private SerieController serieCollection1Controller;
    @Inject
    private SerieController serieCollection2Controller;
    @Inject
    private SerieController serieCollection3Controller;
    @Inject
    private TelevisoraController televisoraCollectionController;

    public XImagenController() {
        // Inform the Abstract parent controller of the concrete XImagen?cap_first Entity
        super(XImagen.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idCarpetaController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Colaborador entities that
     * are retrieved from XImagen?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of Emisora entities that are
     * retrieved from XImagen?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Emisora page
     */
    public String navigateEmisoraCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Emisora_items", this.getSelected().getEmisoraCollection());
        }
        return "/view/emisora/index";
    }

    /**
     * Sets the "selected" attribute of the XCarpeta controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCarpeta(ActionEvent event) {
        if (this.getSelected() != null && idCarpetaController.getSelected() == null) {
            idCarpetaController.setSelected(this.getSelected().getIdCarpeta());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Web entities that are
     * retrieved from XImagen?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Web page
     */
    public String navigateWebCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Web_items", this.getSelected().getWebCollection());
        }
        return "/view/web/index";
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from XImagen?cap_first and returns the navigation outcome.
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
     * retrieved from XImagen?cap_first and returns the navigation outcome.
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
     * retrieved from XImagen?cap_first and returns the navigation outcome.
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
     * retrieved from XImagen?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of Televisora entities that
     * are retrieved from XImagen?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Televisora page
     */
    public String navigateTelevisoraCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Televisora_items", this.getSelected().getTelevisoraCollection());
        }
        return "/view/televisora/index";
    }

}
