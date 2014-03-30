package hamming.hammingcode;

public class Globales {
    /*Constantes*/    
    public static final String NOMBRE_APLICACION = "RSA - Codigos Hamming";
    
    public static final int MAX_BITS_PARIDAD = 4;
    public static final int MAX_BITS = 11;
    public static final int MIN_BITS = 4;
    public static final int BASE_BINARIA = 2;
    
    public static final String CERO = "0";
    public static final String UNO = "1";
    
    public static final int[][] MATRIZ_GENERADORA_7X4 = {
        {1,0,0,0,1,1,0},
        {0,1,0,0,1,0,1},
        {0,0,1,0,0,1,1},
        {0,0,0,1,1,1,1}
    };
    
    public static final int[][] CHECKMATRIX_7X4 = {
        {1,1,0,1,1,0,0},
        {1,0,1,1,0,1,0},
        {0,1,1,1,0,0,1}
    };
    
    public static final int[][] MATRIZ_GENERADORA_15X11 = {
        {1,0,0,0,0,0,0,0,0,0,0,1,1,0,0},
        {0,1,0,0,0,0,0,0,0,0,0,0,1,1,0},
        {0,0,1,0,0,0,0,0,0,0,0,0,0,1,1},
        {0,0,0,1,0,0,0,0,0,0,0,1,1,0,1},
        {0,0,0,0,1,0,0,0,0,0,0,1,0,1,0},
        {0,0,0,0,0,1,0,0,0,0,0,0,1,0,1},
        {0,0,0,0,0,0,1,0,0,0,0,1,1,1,0},
        {0,0,0,0,0,0,0,1,0,0,0,0,1,1,1},
        {0,0,0,0,0,0,0,0,1,0,0,1,1,1,1},
        {0,0,0,0,0,0,0,0,0,1,0,1,0,1,1},
        {0,0,0,0,0,0,0,0,0,0,1,1,0,0,1}
    };
    
    public static final int [][] CHECKMATRIX_15X11 = {
        {1,0,0,1,1,0,1,0,1,1,1,1,0,0,0},
        {1,1,0,1,0,1,1,1,1,0,0,0,1,0,0},
        {0,1,1,0,1,0,1,1,1,1,0,0,0,1,0},
        {0,0,1,1,0,1,0,1,1,1,1,0,0,0,1}
    };

    /*Metodos recurrentes*/
    public static int obtenerNumeroDeBitsDeParidad(int longitudCodigoOriginal){
        int bitsParidad = 0;
        int n = longitudCodigoOriginal;
        
        if(n>=1 && n<=4){
            bitsParidad = 3;
        }else if(n>4 && n<=11) {
            bitsParidad = 4;
        }else if(n>11){
            bitsParidad = 5;
        }

        return bitsParidad;
    }
}