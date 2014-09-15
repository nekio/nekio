package nekio.series.dao;

import nekio.series.dto.XRango;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "xRangoController")
@ViewScoped
public class XRangoController extends AbstractController<XRango> {

    @Inject
    private FormatoController formatoCollectionController;
    @Inject
    private ProyectoController proyectoCollectionController;
    @Inject
    private PasswordController passwordCollectionController;
    @Inject
    private ColaboradorController colaboradorCollectionController;
    @Inject
    private TipoSerieController tipoSerieCollectionController;
    @Inject
    private CalidadController calidadCollectionController;
    @Inject
    private PendienteController pendienteCollectionController;
    @Inject
    private TipoEpisodioController tipoEpisodioCollectionController;
    @Inject
    private WebController webCollectionController;
    @Inject
    private XUsuarioController xUsuarioCollectionController;
    @Inject
    private SerieController serieCollectionController;
    @Inject
    private PesoController pesoCollectionController;
    @Inject
    private GeneroController generoCollectionController;

    public XRangoController() {
        // Inform the Abstract parent controller of the concrete XRango?cap_first Entity
        super(XRango.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Formato entities that are
     * retrieved from XRango?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Formato page
     */
    public String navigateFormatoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Formato_items", this.getSelected().getFormatoCollection());
        }
        return "/view/formato/index";
    }

    /**
     * Sets the "items" attribute with a collection of Proyecto entities that
     * are retrieved from XRango?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of Password entities that
     * are retrieved from XRango?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of Colaborador entities that
     * are retrieved from XRango?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of TipoSerie entities that
     * are retrieved from XRango?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for TipoSerie page
     */
    public String navigateTipoSerieCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoSerie_items", this.getSelected().getTipoSerieCollection());
        }
        return "/view/tipoSerie/index";
    }

    /**
     * Sets the "items" attribute with a collection of Calidad entities that are
     * retrieved from XRango?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Calidad page
     */
    public String navigateCalidadCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Calidad_items", this.getSelected().getCalidadCollection());
        }
        return "/view/calidad/index";
    }

    /**
     * Sets the "items" attribute with a collection of Pendiente entities that
     * are retrieved from XRango?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of TipoEpisodio entities
     * that are retrieved from XRango?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for TipoEpisodio page
     */
    public String navigateTipoEpisodioCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("TipoEpisodio_items", this.getSelected().getTipoEpisodioCollection());
        }
        return "/view/tipoEpisodio/index";
    }

    /**
     * Sets the "items" attribute with a collection of Web entities that are
     * retrieved from XRango?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of XUsuario entities that
     * are retrieved from XRango?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for XUsuario page
     */
    public String navigateXUsuarioCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("XUsuario_items", this.getSelected().getXUsuarioCollection());
        }
        return "/view/xUsuario/index";
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from XRango?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of Peso entities that are
     * retrieved from XRango?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Peso page
     */
    public String navigatePesoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Peso_items", this.getSelected().getPesoCollection());
        }
        return "/view/peso/index";
    }

    /**
     * Sets the "items" attribute with a collection of Genero entities that are
     * retrieved from XRango?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Genero page
     */
    public String navigateGeneroCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Genero_items", this.getSelected().getGeneroCollection());
        }
        return "/view/genero/index";
    }

}
