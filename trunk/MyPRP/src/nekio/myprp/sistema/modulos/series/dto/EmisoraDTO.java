package nekio.myprp.sistema.modulos.series.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class EmisoraDTO extends DTO {

    private Integer idEmisora;
    private Integer idImagen;
    private String siglasEmisora;
    private String nombreEmisora;

    @Override
    public void confirmarDTO() {
        campos = new ArrayList<String>();
        tablasForaneas = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        valoresLOV = new ArrayList<String>();
        camposExtrasLOV = new ArrayList<List>();

        super.campos.add("id_emisora");
        super.valores.add(idEmisora);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);

        super.campos.add("id_imagen");
        super.valores.add(idImagen);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
		//super.valoresLOV.add(descripcion);
        //super.camposExtrasLOV.add(new ArrayList<String>() {{add(""); add("");}});
        super.tablasForaneas.add("null");

        super.campos.add("siglas_emisora");
        super.valores.add(siglasEmisora);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);

        super.campos.add("nombre_emisora");
        super.valores.add(nombreEmisora);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
    }

    public void setIdEmisora(Integer idEmisora) {
        this.idEmisora = idEmisora;
    }

    public Integer getIdEmisora() {
        return idEmisora;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setSiglasEmisora(String siglasEmisora) {
        this.siglasEmisora = siglasEmisora;
    }

    public String getSiglasEmisora() {
        return siglasEmisora;
    }

    public void setNombreEmisora(String nombreEmisora) {
        this.nombreEmisora = nombreEmisora;
    }

    public String getNombreEmisora() {
        return nombreEmisora;
    }
}
