package nekio.series.converter;

import nekio.series.dto.XModalidad;
import nekio.series.facade.XModalidadFacade;
import nekio.series.dao.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "xModalidadConverter")
public class XModalidadConverter implements Converter {

    @Inject
    private XModalidadFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    nekio.series.dto.XModalidadPK getKey(String value) {
        nekio.series.dto.XModalidadPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new nekio.series.dto.XModalidadPK();
        key.setAppDebug(Integer.parseInt(values[0]));
        key.setAppDesign(Integer.parseInt(values[1]));
        key.setBitacoraEstilo(Integer.parseInt(values[2]));
        return key;
    }

    String getStringKey(nekio.series.dto.XModalidadPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getAppDebug());
        sb.append(SEPARATOR);
        sb.append(value.getAppDesign());
        sb.append(SEPARATOR);
        sb.append(value.getBitacoraEstilo());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof XModalidad) {
            XModalidad o = (XModalidad) object;
            return getStringKey(o.getXModalidadPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), XModalidad.class.getName()});
            return null;
        }
    }

}
