package herramientas;

/**
 * 07/Jun/2014
 * LANIA - MRYSI
 * Seguridad en Redes
 * 
 * @author LI. Emiliano Anastasio Landa
 *         eanastasio@veracruz.gob.mx
 * 
 * @author ISC. Sinesio Ivan Carrillo Heredia 
 *         ivan.carrillo@si-ti.com.mx
 * 
 */

public class Cifrados {
    private Alfabeto alfabeto;
    
    public Cifrados(Alfabeto alfabeto) {
        this.alfabeto = alfabeto;
    }
    
    
    /*
     * METODOS GENERALES
     */
    
    public boolean esCaracterValido(char letra) {
        boolean ok = false;
        char simbolos[] = alfabeto.getSimbolos().toCharArray();
        
        for(char simbolo : simbolos) {
            if(simbolo == letra) {
                ok = true;
                break;
            }
        }
        return ok;
    }
    
    private int obtenerResiduo(int indice) {
        int totalSimbolos = alfabeto.getTotalSimbolos();
        
        int residuo = indice % totalSimbolos;
        if(residuo < 0)
            residuo = residuo + totalSimbolos;
        
        return residuo;
    }
    
    public int encontrarMasRepetido(String text){
        char masRepetido = Utilerias.masRepetida(text, alfabeto);
        int posicion = alfabeto.getIndice(masRepetido);
        
        return posicion;
    }
    
    
    /*
     * METODOS DE CIFRADO CESAR
     */
    
    public char cifrarCesar(char caracterIngresado, int llave) {
        char caracter = ' ';
        String simbolos= alfabeto.getSimbolos();
        
        int indice = simbolos.indexOf(caracterIngresado);
        int indiceModulo = obtenerResiduo(indice+llave);
        caracter = simbolos.charAt(indiceModulo);
        
        return caracter;
    }
    
    public String cifrarCesar(String mensaje, int llave) {
        char [] caracteres = mensaje.toUpperCase().toCharArray();
        String textoCifrado = "";
        for(char caracter : caracteres) {
            if(this.esCaracterValido(caracter)) {
                textoCifrado = textoCifrado + cifrarCesar(caracter, llave);
            }
        }
        return textoCifrado;
    }
    
    public char descifrarCesar(char caracterIngresado, int llave) {
        char caracter = ' ';
        String simbolos= alfabeto.getSimbolos();
        
        int indice = simbolos.indexOf(caracterIngresado);
        int indiceModulo = obtenerResiduo(indice-llave);
        
        caracter = simbolos.charAt(indiceModulo);
        
        return caracter;
    }
    
    public String descifrarCesar(String mensajeCifrado, int llave) {
        char [] caracteres = mensajeCifrado.toUpperCase().toCharArray();
        String textoDescifrado = "";
        for(char caracter : caracteres) {
            if(this.esCaracterValido(caracter))
                textoDescifrado = textoDescifrado + descifrarCesar(caracter, llave);
        }
        return textoDescifrado;
    }
    
    public int encontrarLlaveCesar(int masRepetida_o, int masRepetida_e){
        int llave = -1;
        if (masRepetida_e < masRepetida_o){
            int totalSimbolos = alfabeto.getTotalSimbolos();
            int posicion = totalSimbolos - masRepetida_o;
            llave = posicion + masRepetida_e;
        }else
            llave = masRepetida_o - masRepetida_e;
        
        return Math.abs(llave);
    } 
}
