package hamming.hammingcode;

import java.util.ArrayList;

public class Hamming {
    final int TOPE = Globales.MAX_BITS + Globales.MAX_BITS_PARIDAD;
   
    private byte[] bitsConvertidos;
    private byte[] bitsHamming;
    
    private int longitudBitsOriginales;
    private int numeroBitsParidad;
    private int longitudTotalDatos;
    private int posicionError;
    
    private byte[] bitsParidad;
    private byte[][] bitsNoParidad;
    
    public Hamming(){}

    public ArrayList<Boolean> procesarCodigo(String cadenaBitsOriginales){
        definirValores(cadenaBitsOriginales);

        return obtenerHamming();
    }
    
    private void definirValores(String cadenaBitsOriginales){
        longitudBitsOriginales = cadenaBitsOriginales.length();
        
        bitsConvertidos = new byte[TOPE];
        for (int i=0; i<longitudBitsOriginales; i++) 
            bitsConvertidos[i] = Byte.parseByte(cadenaBitsOriginales.substring(i, i+1));

        int datosConvertidos = Integer.parseInt(cadenaBitsOriginales, Globales.BASE_BINARIA);
        numeroBitsParidad = Globales.obtenerNumeroDeBitsDeParidad(longitudBitsOriginales);
        longitudTotalDatos = longitudBitsOriginales + numeroBitsParidad;
    }
    
    private ArrayList<Boolean> obtenerHamming(){
        ArrayList<Boolean> codigoHamming = new ArrayList<>();
        bitsHamming = new byte[longitudTotalDatos];
        
        boolean bitParidad = false;
        int indiceNoParidad = 0;
        int indiceHamming = 0;
        int indiceAuxiliar = 0;
        
        //guarda los bits que no forman parte de los de paridad
        bitsNoParidad = new byte[longitudBitsOriginales][];
        //guarda los indices de los bits que no forman parte de los de paridad
        int[] indiceNumeroNoParidad = new int[longitudBitsOriginales];
        //coloca los datos en los arreglos
        for (int i = 1; i <= longitudTotalDatos; i++) {
            int indice = 0;
            bitParidad = Globales.esPotenciaDe2(i);
            if (!bitParidad) {
                bitsHamming[i-1] = bitsConvertidos[indiceAuxiliar++]; //covertDataArray data we got
                //inicializa los tamaños de los arreglos de bits de no paridad
                bitsNoParidad[indiceNoParidad] = new byte[5];
                //asigna las posiciones al arreglo de bits de no paridad
                indiceNumeroNoParidad[indice++] = indiceNoParidad;
                //Convierte el indice en base 2
                convertirBase2(bitsNoParidad, i, indiceNoParidad);
                indiceNoParidad++;
            } else 
                indiceHamming++;
        }
        bitsParidad = new byte[numeroBitsParidad];
        definirBitsDeParidad(bitsParidad, numeroBitsParidad, bitsNoParidad, indiceNoParidad, bitsConvertidos);
        insertarBitsDeParidad(longitudTotalDatos, bitsParidad, bitsHamming);
        
        for(byte bit:bitsHamming)
            codigoHamming.add(bit==1?true:false);
        
        return codigoHamming;
    }
    
    public ArrayList<Boolean> obtenerHammingCorrupto(int posicionError, int longitudDatos){
        ArrayList<Boolean> codigoCorrupto = new ArrayList<Boolean>();

        if(bitsHamming[posicionError-1] == 1){
            bitsHamming[posicionError-1]=0;
        }else{
            bitsHamming[posicionError-1]=1;
        }
        
//        for(int i=0; i<longitudDatos; i++)
//            codigoCorrupto.add((int)bitsHamming[i]);
        
        for(byte bit:bitsHamming)
            codigoCorrupto.add(bit==1?true:false);
        
        return codigoCorrupto;
    }
    
    /*public int calcularErrorEnArray(int error, int longitudDatos){
        if (bitsHamming[error-1] == 1) {
            bitsHamming[error-1] = 0;
        } else {
            bitsHamming[error-1] = 1;
        }
        
        System.out.print("Codigo Hamming corrupto: ");
        for (int i = 0; i < longitudDatos; i++) {
            System.out.print(bitsHamming[i]);
        }
        
        int posicionError = detectarError(bitsParidad, numeroBitsParidad, bitsNoParidad, longitudDatos, bitsConvertidos, longitudBitsOriginales, bitsHamming);
        return posicionError;
    }*/
    
      private void convertirBase2(byte[][] bitsNoParidad, int posicion, int indiceNoParidad) {
        int contador = 0;
        byte auxiliar = 0;
        int base = 2;
        while (posicion >= base) {
            auxiliar = (byte) (posicion%base);
            bitsNoParidad[indiceNoParidad][contador] = auxiliar;
            contador++;
            posicion = posicion/base;
        }
        bitsNoParidad[indiceNoParidad][contador] = (byte) posicion;
    }
      
       private void definirBitsDeParidad(byte[] bitsParidad, int numeroBitsParidad, byte bitsNoParidad[][], int indiceNoParidad, byte arregloDatosConvertidos[]) {
        byte m = 0;
        int indice = 0;
        int indiceOriginal = 0;

        for(int columna=0; columna<5; columna++){
            for(int fila=0; fila<indiceNoParidad; fila++){
                //checa si es la posicion 2'0, 2'1, 2'2 
                if(bitsNoParidad[fila][columna]==1){
                    //si lo es, checa si es el dato original en paridad
                    if(arregloDatosConvertidos[indiceOriginal]==0 && m==0){
                        m = 0;
                        indiceOriginal++;
                    }else if(arregloDatosConvertidos[indiceOriginal] == 1 && m == 1) {
                        //OR Exclusivo
                        m = 0;
                        indiceOriginal++;
                    }else{
                        // si el datos y el m(check) no son el mismo, initializa m=1
                        m = 1;
                        indiceOriginal++;
                    }
                }else{
                    indiceOriginal++;
                }
            }
            indiceOriginal = 0;
            //inserta check bit al arraeglo
            bitsParidad[indice] = m;
            if (indice < numeroBitsParidad - 1) 
                indice++;

            // inicializar m(check)=0;
            m = 0;
        }
    }
       
    private void insertarBitsDeParidad(int longitudDatos, byte bitsParidad[], byte arregloHamming[]) {
        boolean bitParidad = false;
        int indice = 0;
        
        for(int i=1; i<=longitudDatos; i++){
            bitParidad = Globales.esPotenciaDe2(i);
            if(bitParidad){
                arregloHamming[i-1] = bitsParidad[indice];
                indice++;
            }
        }
    }
    
    public int detectarError() {        
        obtenerBitsParidad();
        obtenerPosicionErrorEnBinario();
        byte[] posicionError = obtenerPosicionErrorEnBinario();
        
        return obtenerPosicionError(posicionError);
    }
    
    private byte[] obtenerPosicionErrorEnBinario(){
        byte error = 0;
        byte[] posicionError = new byte[numeroBitsParidad];
        int indice = 0;

        error = bitsParidad[indice];

        for (int columna = 0; columna < 5; columna++) {
            for(int fila=0; fila<longitudBitsOriginales; fila++){
                if(bitsNoParidad[fila][columna]==1){
                    if (bitsConvertidos[fila]==1 && error==1)
                        error = 0;
                    else if(bitsConvertidos[fila]==0 && error==0)
                        error = 0;
                    else
                        error = 1; //error encontrado
                }
            }

            //Reconocer el indice que contiene el error
            if(error==0)
                posicionError[indice] = 0;
            else
                posicionError[indice] = 1;
            
            if(indice<numeroBitsParidad-1)
                indice++;

            error = bitsParidad[indice];
        }
        
        return posicionError;
    }
    
    private void obtenerBitsParidad(){
        boolean bitParidad = false;
        int indiceParidad = 0;
        int indiceDato = 0;
        
        for (int i = 1; i <= longitudTotalDatos; i++) {
            bitParidad = Globales.esPotenciaDe2(i);
            if(!bitParidad){
                bitsConvertidos[indiceDato] = bitsHamming[i-1];
                if(indiceDato < longitudBitsOriginales)
                    indiceDato++;
            }else
                bitsParidad[indiceParidad++] = bitsHamming[i-1];
        }
    }
    
    private int obtenerPosicionError(byte[] error){
        posicionError = 0;
        for(int i=0; i<numeroBitsParidad; i++){
            if (error[i] == 1) 
                posicionError += (int) Math.pow(2,i);
        }

        return posicionError;
    }
    
    private void corregirError(){
        if(bitsHamming[posicionError-1]==1)
            bitsHamming[posicionError-1]=0;
        else
            bitsHamming[posicionError-1]=1;
    }
}