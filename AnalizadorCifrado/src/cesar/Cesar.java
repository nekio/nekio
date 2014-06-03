package cesar;

/**
 * 07/Jun/2014
 * LANIA - MRYSI
 * Seguridad en Redes
 * 
 * @author LCI. Emiliano Anastasio Landa
 *         eanastasio@veracruz.gob.mx
 * 
 * @author ISC. Sinesio Ivan Carrillo Heredia 
 *         ivan.carrillo@si-ti.com.mx
 * 
 */

public class Cesar {
    private final String alfa = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private char [] alfabeto;
    
    public Cesar() {
        alfabeto = alfa.toCharArray();
    }
    
    public boolean esCaracterValido(char letra) {
        boolean ok = false;
        for(char c : alfabeto) {
            if(c == letra) {
                ok = true;
                break;
            }
        }
        return ok;
    }
    
    private int modulo(int p, int q) {
        int r = p%q;
        
        if(r < 0) {
            r = r + q;
        }
        return r;
    }
    
    // Se asume que m es un caracter en el alfabeto
    public char cifrar(char m, int k) {
        char c = ' ';
        int indice = alfa.indexOf(m);
        int nIndice = modulo(indice + k,alfa.length());
        c = alfa.charAt(nIndice);
        return c;
    }
    
    public String cifrar(String M, int k) {
        char [] caracteres = M.toUpperCase().toCharArray();
        String C = "";
        for(char m : caracteres) {
            if(this.esCaracterValido(m)) {
                C = C + cifrar(m, k);
            }
        }
        return C;
    }
    
        public char descifrar(char m, int k) {
        char c = ' ';
        int indice = alfa.indexOf(m);
        int nIndice = modulo(indice - k,alfa.length());
        c = alfa.charAt(nIndice);
        return c;
    }
    
    public String descifrar(String M, int k) {
        char [] caracteres = M.toUpperCase().toCharArray();
        String C = "";
        for(char m : caracteres) {
            if(this.esCaracterValido(m)) {
                C = C + descifrar(m, k);
            }
        }
        return C;
    }
    
    
}
