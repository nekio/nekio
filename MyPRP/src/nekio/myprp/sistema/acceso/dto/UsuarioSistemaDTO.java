package nekio.myprp.sistema.acceso.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class UsuarioSistemaDTO extends DTO {

    private Integer idUsuario;
    private Integer idSistema;

    @Override
    public void confirmarDTO() {
        campos = new ArrayList<String>();
        tablasForaneas = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        valoresLOV = new ArrayList<String>();
        camposExtrasLOV = new ArrayList<List>();

        super.campos.add("id_usuario");
        super.valores.add(idUsuario);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);

        super.campos.add("id_sistema");
        super.valores.add(idSistema);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
		//super.valoresLOV.add(descripcion);
        //super.camposExtrasLOV.add(new ArrayList<String>() {{add(""); add("");}});
        super.tablasForaneas.add("null");
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public Integer getIdSistema() {
        return idSistema;
    }
}
