package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class IdiomaDTO extends DTO {

    private Integer idIdioma;
    private String descripcion;

    @Override
    public void confirmarDTO() {
        campos = new ArrayList<String>();
        tablasForaneas = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        valoresLOV = new ArrayList<String>();
        camposExtrasLOV = new ArrayList<List>();

        super.campos.add("id_idioma");
        super.valores.add(idIdioma);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);

        super.campos.add("descripcion");
        super.valores.add(descripcion);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
