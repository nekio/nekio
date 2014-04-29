package listadevalores.dto;

import java.util.ArrayList;

/**
 * Clase para encapsular los campos de Listas Clave-Valor
 *
 * @author Nekio
 * @version 27/04/2014
*
 */

public class LlaveValor {
    private int llave;
    private String valor;
    private ArrayList<String> registroExtra;

    public LlaveValor() {
        this(0, null);
    }

    public LlaveValor(int key) {
        this(key, null);
    }

    public LlaveValor(String value) {
        this(0, value);
    }

    public LlaveValor(int key, String value) {
        this.llave = key;
        this.valor = value;
    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ArrayList<String> getRegistroExtra() {
        return registroExtra;
    }

    public void setRegistroExtra(ArrayList<String> registroExtra) {
        this.registroExtra = registroExtra;
    }
}
