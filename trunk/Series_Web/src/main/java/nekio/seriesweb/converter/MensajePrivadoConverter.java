package nekio.seriesweb.converter;

import nekio.seriesweb.dto.MensajePrivado;
import nekio.seriesweb.controllers.MensajePrivadoFacade;
import nekio.seriesweb.beans.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "mensajePrivadoConverter")
public class MensajePrivadoConverter implements Converter {

    @Inject
    private MensajePrivadoFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    nekio.seriesweb.dto.MensajePrivadoPK getKey(String value) {
        nekio.seriesweb.dto.MensajePrivadoPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new nekio.seriesweb.dto.MensajePrivadoPK();
        key.setIdMensajePrivado(Integer.parseInt(values[0]));
        key.setIdUsuario(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(nekio.seriesweb.dto.MensajePrivadoPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getIdMensajePrivado());
        sb.append(SEPARATOR);
        sb.append(value.getIdUsuario());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof MensajePrivado) {
            MensajePrivado o = (MensajePrivado) object;
            return getStringKey(o.getMensajePrivadoPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MensajePrivado.class.getName()});
            return null;
        }
    }

}
