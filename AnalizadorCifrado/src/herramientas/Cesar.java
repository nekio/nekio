package herramientas;

public class Cesar {
    private Alfabeto alfabeto;
    
    public Cesar(Alfabeto alfabeto) {
        this.alfabeto = alfabeto;
    }
    
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
    
    public char cifrar(char caracterIngresado, int llave) {
        char caracter = ' ';
        String simbolos= alfabeto.getSimbolos();
        
        int indice = simbolos.indexOf(caracterIngresado);
        int indiceModulo = obtenerResiduo(indice+llave);
        caracter = simbolos.charAt(indiceModulo);
        
        return caracter;
    }
    
    public String cifrar(String mensaje, int llave) {
        char [] caracteres = mensaje.toUpperCase().toCharArray();
        String textoCifrado = "";
        for(char caracter : caracteres) {
            if(this.esCaracterValido(caracter)) {
                textoCifrado = textoCifrado + cifrar(caracter, llave);
            }
        }
        return textoCifrado;
    }
    
    public char descifrar(char caracterIngresado, int llave) {
        char caracter = ' ';
        String simbolos= alfabeto.getSimbolos();
        
        int indice = simbolos.indexOf(caracterIngresado);
        int indiceModulo = obtenerResiduo(indice-llave);
        
        caracter = simbolos.charAt(indiceModulo);
        
        return caracter;
    }
    
    public String descifrar(String mensajeCifrado, int llave) {
        char [] caracteres = mensajeCifrado.toUpperCase().toCharArray();
        String textoDescifrado = "";
        for(char caracter : caracteres) {
            if(this.esCaracterValido(caracter))
                textoDescifrado = textoDescifrado + descifrar(caracter, llave);
        }
        return textoDescifrado;
    }
    
    public int encontrarLlave(int masRepetida_o, int masRepetida_e){
        int llave = -1;
        if (masRepetida_e < masRepetida_o){
            int totalSimbolos = alfabeto.getTotalSimbolos();
            int posicion = totalSimbolos - masRepetida_o;
            llave = posicion + masRepetida_e;
        }else
            llave = masRepetida_o - masRepetida_e;
        
        return Math.abs(llave);
    } 
    
    public int encontrarMasRepetido(String text){
        char masRepetido = Utilerias.masRepetida(text, alfabeto);
        int posicion = alfabeto.getIndice(masRepetido);
        
        return posicion;
    }
}
