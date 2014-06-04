package herramientas;

import java.util.Arrays;

public class Alfabeto {
    public static enum Espanol{
        A(12.43f),
        B(1.42f),
        C(4.68f),
        D(5.86f),
        E(13.58f),
        F(0.69f),
        G(1.01f),
        H(0.7f),
        I(6.22f),
        J(0.44f),
        K(0.01f),
        L(4.97f),
        M(3.15f),
        N(6.7f),
        Ñ(0.31f),
        O(8.58f),
        P(2.51f),
        Q(0.88f),
        R(6.86f),
        S(7.88f),
        T(4.63f),
        U(3.93f),
        V(0.9f),
        W(0.02f),
        X(0.22f),
        Y(0.9f),
        Z(0.52f);
        
        private float frecuencia;

        private Espanol(float frecuencia){
            this.frecuencia = frecuencia;
        }

        public float getFrecuencia() {
            return frecuencia;
        }
    };
    
    private int totalSimbolos;
    
    public Alfabeto() {
        totalSimbolos = Espanol.values().length;
    }
    
    public String getSimbolos() {
        String[] arregloSimbolos = obtenerValoresEnum(Espanol.class);
        String simbolos = "";
        for(String simbolo : arregloSimbolos)
            simbolos += simbolo;

        return simbolos;
    }
    
    public char getLetra(int indice) {
        indice = indice % totalSimbolos;
        if(indice < 0)
            indice = indice + totalSimbolos;
        
        char letra = String.valueOf(Espanol.values()[indice]).charAt(0);

        return letra;
    }
    
    public Espanol getInstanciaLetra(int indice) {
        indice = indice % totalSimbolos;
        if(indice < 0)
            indice = indice + totalSimbolos;
        
        String letra = String.valueOf(Espanol.values()[indice]);
        Espanol instanciaLetra = Espanol.valueOf(letra);

        return instanciaLetra;
    }
    
    public int getIndice(char letra) {
        int indice = 0;
        char simbolo = '-';
        for(; indice < totalSimbolos; indice++) {
            try{
                simbolo = String.valueOf(Espanol.values()[indice]).charAt(0);
                if(simbolo == letra)
                    break;
            }catch(Exception e){ //Cacha cualquier simbolo que no se encuentre en el enum
                indice = -1;
                break;
            }
        }
        
        return indice;
    }
    
    public int getTotalSimbolos() {
        return totalSimbolos;
    }
    
    private static String[] obtenerValoresEnum(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }
}