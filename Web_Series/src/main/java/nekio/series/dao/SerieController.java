package nekio.series.dao;

import nekio.series.dto.Serie;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "serieController")
@ViewScoped
public class SerieController extends AbstractController<Serie> {

    @Inject
    private TelevisoraController televisoraCollectionController;
    @Inject
    private ProyectoController proyectoCollectionController;
    @Inject
    private PasswordController passwordCollectionController;
    @Inject
    private EnlaceController enlaceCollectionController;
    @Inject
    private GeneroController idGenero2Controller;
    @Inject
    private GeneroController idGenero1Controller;
    @Inject
    private ClasificacionController idClasificacionController;
    @Inject
    private DuracionController idDuracionController;
    @Inject
    private PaisController idPaisController;
    @Inject
    private IdiomaController idIdiomaOrigenController;
    @Inject
    private TipoSerieController idTipoSerieController;
    @Inject
    private EmisoraController idEmisoraController;
    @Inject
    private DistintivoController idDistintivoController;
    @Inject
    private GeneroController idGenero4Controller;
    @Inject
    private GeneroController idGenero3Controller;
    @Inject
    private SerieController serieCollectionController;
    @Inject
    private SerieController idSerieOrigenController;
    @Inject
    private EpisodioController episodioCollectionController;

    public SerieController() {
        // Inform the Abstract parent controller of the concrete Serie?cap_first Entity
        super(Serie.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idGenero2Controller.setSelected(null);
        idGenero1Controller.setSelected(null);
        idClasificacionController.setSelected(null);
        idDuracionController.setSelected(null);
        idPaisController.setSelected(null);
        idIdiomaOrigenController.setSelected(null);
        idTipoSerieController.setSelected(null);
        idEmisoraController.setSelected(null);
        idDistintivoController.setSelected(null);
        idGenero4Controller.setSelected(null);
        idGenero3Controller.setSelected(null);
        idSerieOrigenController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Televisora entities that
     * are retrieved from Serie?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Televisora page
     */
    public String navigateTelevisoraCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Televisora_items", this.getSelected().getTelevisoraCollection());
        }
        return "/view/televisora/index";
    }

    /**
     * Sets the "items" attribute with a collection of Proyecto entities that
     * are retrieved from Serie?cap_first and returns the navigation outcome.
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
     * are retrieved from Serie?cap_first and returns the navigation outcome.
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
     * Sets the "items" attribute with a collection of Enlace entities that are
     * retrieved from Serie?cap_first and returns the navigation outcome.
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
     * Sets the "selected" attribute of the Genero controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGenero2(ActionEvent event) {
        if (this.getSelected() != null && idGenero2Controller.getSelected() == null) {
            idGenero2Controller.setSelected(this.getSelected().getIdGenero2());
        }
    }

    /**
     * Sets the "selected" attribute of the Genero controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGenero1(ActionEvent event) {
        if (this.getSelected() != null && idGenero1Controller.getSelected() == null) {
            idGenero1Controller.setSelected(this.getSelected().getIdGenero1());
        }
    }

    /**
     * Sets the "selected" attribute of the Clasificacion controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdClasificacion(ActionEvent event) {
        if (this.getSelected() != null && idClasificacionController.getSelected() == null) {
            idClasificacionController.setSelected(this.getSelected().getIdClasificacion());
        }
    }

    /**
     * Sets the "selected" attribute of the Duracion controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdDuracion(ActionEvent event) {
        if (this.getSelected() != null && idDuracionController.getSelected() == null) {
            idDuracionController.setSelected(this.getSelected().getIdDuracion());
        }
    }

    /**
     * Sets the "selected" attribute of the Pais controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdPais(ActionEvent event) {
        if (this.getSelected() != null && idPaisController.getSelected() == null) {
            idPaisController.setSelected(this.getSelected().getIdPais());
        }
    }

    /**
     * Sets the "selected" attribute of the Idioma controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdIdiomaOrigen(ActionEvent event) {
        if (this.getSelected() != null && idIdiomaOrigenController.getSelected() == null) {
            idIdiomaOrigenController.setSelected(this.getSelected().getIdIdiomaOrigen());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoSerie controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoSerie(ActionEvent event) {
        if (this.getSelected() != null && idTipoSerieController.getSelected() == null) {
            idTipoSerieController.setSelected(this.getSelected().getIdTipoSerie());
        }
    }

    /**
     * Sets the "selected" attribute of the Emisora controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEmisora(ActionEvent event) {
        if (this.getSelected() != null && idEmisoraController.getSelected() == null) {
            idEmisoraController.setSelected(this.getSelected().getIdEmisora());
        }
    }

    /**
     * Sets the "selected" attribute of the Distintivo controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdDistintivo(ActionEvent event) {
        if (this.getSelected() != null && idDistintivoController.getSelected() == null) {
            idDistintivoController.setSelected(this.getSelected().getIdDistintivo());
        }
    }

    /**
     * Sets the "selected" attribute of the Genero controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGenero4(ActionEvent event) {
        if (this.getSelected() != null && idGenero4Controller.getSelected() == null) {
            idGenero4Controller.setSelected(this.getSelected().getIdGenero4());
        }
    }

    /**
     * Sets the "selected" attribute of the Genero controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGenero3(ActionEvent event) {
        if (this.getSelected() != null && idGenero3Controller.getSelected() == null) {
            idGenero3Controller.setSelected(this.getSelected().getIdGenero3());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Serie entities that are
     * retrieved from Serie?cap_first and returns the navigation outcome.
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
     * Sets the "selected" attribute of the Serie controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSerieOrigen(ActionEvent event) {
        if (this.getSelected() != null && idSerieOrigenController.getSelected() == null) {
            idSerieOrigenController.setSelected(this.getSelected().getIdSerieOrigen());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Episodio entities that
     * are retrieved from Serie?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Episodio page
     */
    public String navigateEpisodioCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Episodio_items", this.getSelected().getEpisodioCollection());
        }
        return "/view/episodio/index";
    }

}
