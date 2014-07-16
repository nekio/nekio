package nekio.myprp.sistema.modulos.series.dto;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.Date;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class PendienteDTO extends DTO{
    private int idPendiente;
    private String pendiente;
    private String detalle;
    private int idEpisodio;
    private int idProyecto;
    private Date fechaRevision;
    private String enlace;
    private String comentarios;
    
    @Override
    public void confirmarDTO() {
        campos = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        LOVValores = new ArrayList<String>();
        
        super.campos.add("id_pendiente");
        super.valores.add(idPendiente);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("pendiente");
        super.valores.add(pendiente);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("detalle");
        super.valores.add(detalle);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("id_episodio");
        super.valores.add(idEpisodio);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("id_proyecto");
        super.valores.add(idProyecto);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("fecha_revision");
        super.valores.add(fechaRevision);
        super.tipoDatos.add(Globales.TipoDato.FECHA);
        
        super.campos.add("enlace");
        super.valores.add(enlace);
        super.tipoDatos.add(Globales.TipoDato.TEXTO_LARGO);
        
        super.campos.add("comentarios");
        super.valores.add(comentarios);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
    }
    
}
