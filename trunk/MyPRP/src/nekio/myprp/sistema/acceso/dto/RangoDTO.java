package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class RangoDTO extends DTO {

    private Integer idRango;
    private String descripcion;
    private Boolean accPublico;
    private Boolean accGrupal;
    private Boolean accProtegido;
    private Boolean accPrivado;
    private Color colorRango;

    @Override
    public void confirmarDTO() {
        campos = new ArrayList<String>();
        tablasForaneas = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        valoresLOV = new ArrayList<String>();
        camposExtrasLOV = new ArrayList<List>();

        super.campos.add("id_rango");
        super.valores.add(idRango);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);

        super.campos.add("descripcion");
        super.valores.add(descripcion);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);

        super.campos.add("acc_publico");
        super.valores.add(accPublico);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("acc_grupal");
        super.valores.add(accGrupal);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("acc_protegido");
        super.valores.add(accProtegido);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);

        super.campos.add("acc_privado");
        super.valores.add(accPrivado);
        super.tipoDatos.add(Globales.TipoDato.BOOLEANO);
    }

    public void setIdRango(Integer idRango) {
        this.idRango = idRango;
    }

    public Integer getIdRango() {
        return idRango;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setAccPublico(Boolean accPublico) {
        this.accPublico = accPublico;
    }

    public Boolean isAccPublico() {
        return accPublico;
    }

    public void setAccGrupal(Boolean accGrupal) {
        this.accGrupal = accGrupal;
    }

    public Boolean isAccGrupal() {
        return accGrupal;
    }

    public void setAccProtegido(Boolean accProtegido) {
        this.accProtegido = accProtegido;
    }

    public Boolean isAccProtegido() {
        return accProtegido;
    }

    public void setAccPrivado(Boolean accPrivado) {
        this.accPrivado = accPrivado;
    }

    public Boolean isAccPrivado() {
        return accPrivado;
    }

    public Color getColorRango() {
        return colorRango;
    }

    public void setColorRango(Color colorRango) {
        this.colorRango = colorRango;
    }
}
