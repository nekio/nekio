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
    private String detalle;
    private Boolean accPublico;
    private Boolean accGrupal;
    private Boolean accProtegido;
    private Boolean accPrivado;
    private String color;

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

        super.campos.add("detalle");
        super.valores.add(detalle);
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

        super.campos.add("color");
        super.valores.add(color);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
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

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setAccPublico(Boolean accPublico) {
        this.accPublico = accPublico;
    }

    public void setAccPublico(int accPublico) {
        if (accPublico == 1) {
            this.accPublico = true;
        } else {
            this.accPublico = false;
        }
    }

    public Boolean isAccPublico() {
        return accPublico;
    }

    public void setAccGrupal(Boolean accGrupal) {
        this.accGrupal = accGrupal;
    }

    public void setAccGrupal(int accGrupal) {
        if (accGrupal == 1) {
            this.accGrupal = true;
        } else {
            this.accGrupal = false;
        }
    }

    public Boolean isAccGrupal() {
        return accGrupal;
    }

    public void setAccProtegido(Boolean accProtegido) {
        this.accProtegido = accProtegido;
    }

    public void setAccProtegido(int accProtegido) {
        if (accProtegido == 1) {
            this.accProtegido = true;
        } else {
            this.accProtegido = false;
        }
    }

    public Boolean isAccProtegido() {
        return accProtegido;
    }

    public void setAccPrivado(Boolean accPrivado) {
        this.accPrivado = accPrivado;
    }

    public void setAccPrivado(int accPrivado) {
        if (accPrivado == 1) {
            this.accPrivado = true;
        } else {
            this.accPrivado = false;
        }
    }

    public Boolean isAccPrivado() {
        return accPrivado;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
