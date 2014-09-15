package nekio.series.dao;

import nekio.series.dto.XModalidad;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

@Named(value = "xModalidadController")
@ViewScoped
public class XModalidadController extends AbstractController<XModalidad> {

    public XModalidadController() {
        // Inform the Abstract parent controller of the concrete XModalidad?cap_first Entity
        super(XModalidad.class);
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setXModalidadPK(new nekio.series.dto.XModalidadPK());
    }

}
