package hamming.hammingcode;

public class Globales {
    /*Constantes*/    
    public static final int MAX_BITS_PARIDAD = 5;
    public static final int MAX_BITS = 16;
    public static final int MIN_BITS = 3;
    public static final int BASE_BINARIA = 2;
    public static final String CERO = "0";
    public static final String UNO = "1";
    
    public static boolean esPotenciaDe2(int numero){
        double base2=(Math.log(numero)/Math.log(BASE_BINARIA));
        return base2 % 1 == 0;
    }
}
