package listadevalores.dto;

/**
 * Clase para encapsular los campos de Listas Clave-Valor
 *
 * @author Nekio
 * @version 27/04/2014
*
 */

public class LlaveValor {
    private int key;
    private String value;

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
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
