package hamming.hammingcode;

import java.util.ArrayList;

public class Hamming {
    final int TOPE = Globales.MAX_BITS + Globales.MAX_BITS_PARIDAD;
   
    private byte[] bitsConvertidos;
    private byte[] bitsHamming;
    
    private int lingitudBitsOriginales;
    private int numeroBitsParidad;
    private int longitudTotalDatos;
    
    private byte[] bitsParidad;
    private byte[][] bitsNoParidad;
    
    public Hamming(){
    }

    public ArrayList<Boolean> procesarCodigo(String cadenaBitsOriginales){
        definirValores(cadenaBitsOriginales);

        return obtenerHamming();
    }
    
    private void definirValores(String cadenaBitsOriginales){
        lingitudBitsOriginales = cadenaBitsOriginales.length();
        
        bitsConvertidos = new byte[TOPE];
        for (int i=0; i<lingitudBitsOriginales; i++) 
            bitsConvertidos[i] = Byte.parseByte(cadenaBitsOriginales.substring(i, i+1));

        int datosConvertidos = Integer.parseInt(cadenaBitsOriginales, Globales.BASE_BINARIA);
        numeroBitsParidad = Globales.obtenerNumeroDeBitsDeParidad(lingitudBitsOriginales);
        longitudTotalDatos = lingitudBitsOriginales + numeroBitsParidad;
    }
    
    private ArrayList<Boolean> obtenerHamming(){
        ArrayList<Boolean> codigoHamming = new ArrayList<>();
        bitsHamming = new byte[longitudTotalDatos];
        
        boolean bitParidad = false;
        int indiceNoParidad = 0;
        int indiceHamming = 0;
        int indiceAuxiliar = 0;
        
        //guarda los bits que no forman parte de los de paridad
        bitsNoParidad = new byte[lingitudBitsOriginales][];
        //guarda los indices de los bits que no forman parte de los de paridad
        int[] indiceNumeroNoParidad = new int[lingitudBitsOriginales];
        //coloca los datos en los arreglos
        for (int i = 1; i <= longitudTotalDatos; i++) {
            int indice = 0;
            bitParidad = esBitDeParidad(i);
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
    
    public int calcularErrorEnArray(int error, int longitudDatos){
        if (bitsHamming[error-1] == 1) {
            bitsHamming[error-1] = 0;
        } else {
            bitsHamming[error-1] = 1;
        }
        
        System.out.print("Codigo Hamming corrupto: ");
        for (int i = 0; i < longitudDatos; i++) {
            System.out.print(bitsHamming[i]);
        }
        
        int posicionError = detectarError(bitsParidad, numeroBitsParidad, bitsNoParidad, longitudDatos, bitsConvertidos, lingitudBitsOriginales, bitsHamming);
        return posicionError;
    }
     
    private boolean esBitDeParidad(int posicion) {
        boolean bitParidad = true;
        int auxiliar = 0;
        while (posicion > 1) {
            auxiliar = posicion % 2;
            if (auxiliar != 0) {
                bitParidad = false;
                break;
            } else 
                posicion = posicion / 2;
        }
        return bitParidad;
    }
    
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
             else 
                ;
            // inicializar m(check)=0;
            m = 0;
        }
    }
       
    private void insertarBitsDeParidad(int longitudDatos, byte bitsParidad[], byte arregloHamming[]) {
        boolean bitParidad = false;
        int indice = 0;
        for(int i = 1; i <= longitudDatos; i++){
            bitParidad = esBitDeParidad(i);
            if(bitParidad) {
                arregloHamming[i-1] = bitsParidad[indice];
                indice++;
            }else
                ;
            
        }
    }
    
    public int detectarError(byte[] bitsParidad, int numeroBitsParidad, byte bitsNoParidad[][], int longitudDatos, byte arregloDatosConvertidos[],
            int longitudOriginalDatos, byte arregloHamming[]) {
        int posicionError = 0;
        boolean bitParidad = false;
        int indiceParidad = 0;
        int indiceDato = 0;
        
        //Obtener el codigo hamming corrupto
        for (int i = 1; i <= longitudDatos; i++) {
            bitParidad = esBitDeParidad(i);
            if (!bitParidad) {
                arregloDatosConvertidos[indiceDato] = arregloHamming[i-1];
                if (indiceDato < longitudOriginalDatos) {
                    indiceDato++;
                } else {
                    ;
                }
            } else {
                bitsParidad[indiceParidad++] = arregloHamming[i-1];
            }
        }

        byte m = 0;
        byte[] error = new byte[numeroBitsParidad];
        int indice = 0;
        int indiceDatoOriginal = 0;

        m = bitsParidad[indice];

        for (int columna = 0; columna < 5; columna++) {
            for(int fila=0; fila<longitudOriginalDatos; fila++){
                if (bitsNoParidad[fila][columna]==1) {
                    if (arregloDatosConvertidos[indiceDatoOriginal]==1 && m==1) {
                        m = 0;
                        indiceDatoOriginal++;
                    }else if(arregloDatosConvertidos[indiceDatoOriginal]==0 && m==0){
                        m = 0;
                        indiceDatoOriginal++;
                    }else{
                        m = 1; //mistake (error) encontrado
                        indiceDatoOriginal++;
                    }
                }else{
                    indiceDatoOriginal++;
                }
            }
            indiceDatoOriginal = 0;

            //Reconocer el indice que contiene el error
            if(m==0){
                error[indice] = 0;
            }else{
                error[indice] = 1;
            }
            
            if(indice<numeroBitsParidad-1){
                indice++;
            }else{
                ;
            }

            m = bitsParidad[indice];
        }

        //Calcular la posicion del error
        for(int i=0; i<numeroBitsParidad; i++){
            if (error[i] == 1) 
                posicionError += (int) Math.pow(2,i);
        }
        
        // corrige el error
        if(arregloHamming[posicionError-1]==1)
            arregloHamming[posicionError-1]=0;
        else
            arregloHamming[posicionError-1]=1;
        
        return posicionError;
    }

}
