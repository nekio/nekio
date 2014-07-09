package nekio.myprp.recursos.utilerias.bd;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class BitacoraDTO extends DTO {
    private int idBitacora;
    private int idSistema;
    private int idUsuario;
    private String tabla;
    private char accion;
    private String dml;
    private String fecha;
    
    @Override
    public void confirmarDTO(){        
        campos = new ArrayList<String>();
        valores = new ArrayList();
        tipoDatos = new ArrayList<Globales.TipoDato>();
        
        super.campos.add("id_bitacora");
        super.valores.add(idBitacora);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("id_sistema");
        super.valores.add(idSistema);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
        
        super.campos.add("id_usuario");
        super.valores.add(idUsuario);
        super.tipoDatos.add(Globales.TipoDato.NUMERO);
                
        super.campos.add("tabla");
        super.valores.add(tabla);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("accion");
        super.valores.add(accion);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("dml");
        super.valores.add(dml);
        super.tipoDatos.add(Globales.TipoDato.TEXTO);
        
        super.campos.add("fecha");
        super.valores.add(fecha);
        super.tipoDatos.add(Globales.TipoDato.FECHA);
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public int getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(int idSistema) {
        this.idSistema = idSistema;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public char getAccion() {
        return accion;
    }

    public void setAccion(char accion) {
        this.accion = accion;
    }

    public String getDml() {
        return dml;
    }

    public String getFecha() {
        return fecha;
    }
    
     /* Atributos artificiales */

    private String pCampos;
    private String pValores;

    public String getpCampos() {
        return pCampos;
    }

    public void setpCampos(String pCampos) {
        this.pCampos = pCampos;
    }

    public String getpValores() {
        return pValores;
    }

    public void setpValores(String pValores) {
        this.pValores = pValores;
    }
}
