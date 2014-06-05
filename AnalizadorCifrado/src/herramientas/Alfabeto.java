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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        
        public static List<Espanol> getOrdenFrecuencia(){
            List<Espanol> lista = new ArrayList<Espanol>();
            
            lista.add(Espanol.E);
            lista.add(Espanol.A);
            lista.add(Espanol.O);
            lista.add(Espanol.S);
            lista.add(Espanol.R);
            lista.add(Espanol.N);
            lista.add(Espanol.I);
            lista.add(Espanol.D);
            lista.add(Espanol.L);
            lista.add(Espanol.C);
            lista.add(Espanol.T);
            lista.add(Espanol.U);
            lista.add(Espanol.M);
            lista.add(Espanol.P);
            lista.add(Espanol.B);
            lista.add(Espanol.G);
            lista.add(Espanol.V);
            lista.add(Espanol.Y);
            lista.add(Espanol.Q);
            lista.add(Espanol.H);
            lista.add(Espanol.F);
            lista.add(Espanol.Z);
            lista.add(Espanol.J);
            lista.add(Espanol.Ñ);
            lista.add(Espanol.X);
            lista.add(Espanol.W);
            lista.add(Espanol.K);

        return lista;
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
