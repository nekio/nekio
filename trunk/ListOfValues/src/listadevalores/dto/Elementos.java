package listadevalores.dto;

/**
 * Clase para encapsular los elementos de una lista de Valores
 *
 * @author Nekio
 * @version 27/04/2014
*
 */

import java.sql.Connection;
import java.util.ArrayList;

public class Elementos {
    private Connection conexion;
    private String llave;
    private String valor;
    private ArrayList<String> camposExtras;
    private String filtros;
    private String tabla;
    private boolean ordendoPorLlave;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public ArrayList<String> getCamposExtras() {
        return camposExtras;
    }

    public void setCamposExtras(ArrayList<String> camposExtras) {
        this.camposExtras = camposExtras;
    }

    public String getFiltros() {
        return filtros;
    }

    public void setFiltros(String filtros) {
        this.filtros = filtros;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public boolean isOrdendoPorLlave() {
        return ordendoPorLlave;
    }

    public void setOrdendoPorLlave(boolean ordendoPorLlave) {
        this.ordendoPorLlave = ordendoPorLlave;
    }
}
