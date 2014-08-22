package nekio.seriesweb.beans;

import nekio.seriesweb.dto.Proyecto;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "proyectoController")
@ViewScoped
public class ProyectoController extends AbstractController<Proyecto> {

    @Inject
    private PesoController idPesoController;
    @Inject
    private CalidadController idCalidadController;
    @Inject
    private FormatoController idFormatoController;
    @Inject
    private IdiomaController idIdiomaController;
    @Inject
    private WebController idWebController;
    @Inject
    private SerieController idSerieController;
    @Inject
    private EstadoProyectoController idEstadoProyectoController;
    @Inject
    private ProyectoDetController proyectoDetCollectionController;
    @Inject
    private PasswordController passwordCollectionController;
    @Inject
    private PendienteController pendienteCollectionController;
    @Inject
    private EnlaceController enlaceCollectionController;
    @Inject
    private ObtenidoController obtenidoCollectionController;

    public ProyectoController() {
        // Inform the Abstract parent controller of the concrete Proyecto?cap_first Entity
        super(Proyecto.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idPesoController.setSelected(null);
        idCalidadController.setSelected(null);
        idFormatoController.setSelected(null);
        idIdiomaController.setSelected(null);
        idWebController.setSelected(null);
        idSerieController.setSelected(null);
        idEstadoProyectoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Peso controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPeso(ActionEvent event) {
        if (this.getSelected() != null && idPesoController.getSelected() == null) {
            idPesoController.setSelected(this.getSelected().getIdPeso());
        }
    }

    /**
     * Sets the "selected" attribute of the Calidad controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCalidad(ActionEvent event) {
        if (this.getSelected() != null && idCalidadController.getSelected() == null) {
            idCalidadController.setSelected(this.getSelected().getIdCalidad());
        }
    }

    /**
     * Sets the "selected" attribute of the Formato controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdFormato(ActionEvent event) {
        if (this.getSelected() != null && idFormatoController.getSelected() == null) {
            idFormatoController.setSelected(this.getSelected().getIdFormato());
        }
    }

    /**
     * Sets the "selected" attribute of the Idioma controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdIdioma(ActionEvent event) {
        if (this.getSelected() != null && idIdiomaController.getSelected() == null) {
            idIdiomaController.setSelected(this.getSelected().getIdIdioma());
        }
    }

    /**
     * Sets the "selected" attribute of the Web controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdWeb(ActionEvent event) {
        if (this.getSelected() != null && idWebController.getSelected() == null) {
            idWebController.setSelected(this.getSelected().getIdWeb());
        }
    }

    /**
     * Sets the "selected" attribute of the Serie controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSerie(ActionEvent event) {
        if (this.getSelected() != null && idSerieController.getSelected() == null) {
            idSerieController.setSelected(this.getSelected().getIdSerie());
        }
    }

    /**
     * Sets the "selected" attribute of the EstadoProyecto controller in order
     * to display its data in a dialog. This is reusing existing the existing
     * View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEstadoProyecto(ActionEvent event) {
        if (this.getSelected() != null && idEstadoProyectoController.getSelected() == null) {
            idEstadoProyectoController.setSelected(this.getSelected().getIdEstadoProyecto());
        }
    }

    /**
     * Sets the "items" attribute with a collection of ProyectoDet entities that
     * are retrieved from Proyecto?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for ProyectoDet page
     */
    public String navigateProyectoDetCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ProyectoDet_items", this.getSelected().getProyectoDetCollection());
        }
        return "/view/proyectoDet/index";
    }

    /**
     * Sets the "items" attribute with a collection of Password entities that
     * are retrieved from Proyecto?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Password page
     */
    public String navigatePasswordCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Password_items", this.getSelected().getPasswordCollection());
        }
        return "/view/password/index";
    }

    /**
     * Sets the "items" attribute with a collection of Pendiente entities that
     * are retrieved from Proyecto?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Pendiente page
     */
    public String navigatePendienteCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Pendiente_items", this.getSelected().getPendienteCollection());
        }
        return "/view/pendiente/index";
    }

    /**
     * Sets the "items" attribute with a collection of Enlace entities that are
     * retrieved from Proyecto?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Enlace page
     */
    public String navigateEnlaceCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Enlace_items", this.getSelected().getEnlaceCollection());
        }
        return "/view/enlace/index";
    }

    /**
     * Sets the "items" attribute with a collection of Obtenido entities that
     * are retrieved from Proyecto?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Obtenido page
     */
    public String navigateObtenidoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Obtenido_items", this.getSelected().getObtenidoCollection());
        }
        return "/view/obtenido/index";
    }

}
