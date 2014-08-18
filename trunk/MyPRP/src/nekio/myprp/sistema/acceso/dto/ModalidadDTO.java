package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class ModalidadDTO extends DTO {
    private Boolean appDebug;
    private Boolean appDesign;
    private Boolean bitacoraEstilo;

    @Override
    public void confirmarDTO() {
        campos = new ArrayList<String>();
        tablasForaneas = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        valoresLOV = new ArrayList<String>();
        camposExtrasLOV = new ArrayList<List>();

        super.campos.add("app_debug");
        super.valores.add(appDebug);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("app_design");
        super.valores.add(appDesign);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("bitacora_estilo");
        super.valores.add(bitacoraEstilo);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);
    }

    public void setAppDebug(Boolean appDebug) {
        this.appDebug = appDebug;
    }

    public void setAppDebug(int appDebug) {
        if (appDebug == 1) {
            this.appDebug = true;
        } else {
            this.appDebug = false;
        }
    }

    public Boolean isAppDebug() {
        return appDebug;
    }

    public void setAppDesign(Boolean appDesign) {
        this.appDesign = appDesign;
    }

    public void setAppDesign(int appDesign) {
        if (appDesign == 1) {
            this.appDesign = true;
        } else {
            this.appDesign = false;
        }
    }

    public Boolean isAppDesign() {
        return appDesign;
    }

    public void setBitacoraEstilo(Boolean bitacoraEstilo) {
        this.bitacoraEstilo = bitacoraEstilo;
    }

    public void setBitacoraEstilo(int bitacoraEstilo) {
        if (bitacoraEstilo == 1) {
            this.bitacoraEstilo = true;
        } else {
            this.bitacoraEstilo = false;
        }
    }

    public Boolean isBitacoraEstilo() {
        return bitacoraEstilo;
    }
}
