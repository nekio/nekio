package hamming.hammingcode;

public class Hamming {
    private HammingDTO dto;

    public Hamming(HammingDTO dto) {
        this.dto = dto;
    }

    //Inicializa los valores para los procesos del vector recibido
    public void definirValores(int[] vector) {
        int longitudVector = vector.length;
        int filasCheckmatrix = 0;
        int filas = 0;
        int columnas = 0;

        switch (longitudVector) {
            case (4):
                filasCheckmatrix = 3;
                columnas = 7;
                filas = 4;

                dto.setMatrizGeneradora(Globales.MATRIZ_GENERADORA_7X4);
                dto.setCheckmatrix(Globales.CHECKMATRIX_7X4);
                break;
            case (11):
                filasCheckmatrix = 4;
                columnas = 15;
                filas = 11;

                dto.setMatrizGeneradora(Globales.MATRIZ_GENERADORA_15X11);
                dto.setCheckmatrix(Globales.CHECKMATRIX_15X11);
                break;
        }

        dto.setFilasCheckmatrix(filasCheckmatrix);
        dto.setColumnasCodeword(columnas);
        dto.setFilasCodeword(filas);

        dto.setVector(vector);
        dto.setCodeword(new int[columnas]);
        dto.setVectorXgenerador(new int[columnas][filas]);
        dto.setCheckmatrixXcodeword(new int[filasCheckmatrix][columnas]);
        dto.setSyndrome(new int[filasCheckmatrix]);
        
        obtenerVectorXgeneradora();
    }

    //Obtiene el resultado del vector original por la matriz generadora
    //para posteriormente hacer el calculo del codeword
    private void obtenerVectorXgeneradora() {
        String operaciones = "";
        String resultado = "";
        String proceso = "";

        int bitVector = 0;
        int bitGeneradora = 0;
        int bitResultante = 0;

        for (int columna = 0; columna < dto.getColumnasCodeword(); columna++) {
            resultado = "";
            for (int fila = 0; fila < dto.getFilasCodeword(); fila++) {
                bitVector = dto.getVector()[fila];
                bitGeneradora = dto.getMatrizGeneradora()[fila][columna];
                bitResultante = bitVector * bitGeneradora;

                dto.getVectorXgenerador()[columna][fila] = bitResultante;

                if (fila == 0) {
                    resultado += bitResultante;
                    operaciones = "(" + bitVector + "x" + bitGeneradora + ") ";
                } else {
                    resultado += "+" + bitResultante;
                    operaciones += "+ (" + bitVector + "x" + bitGeneradora + ") ";
                }
            }
            proceso += operaciones + "= " + resultado + "\n";
        }

        dto.setVectorXgeneradorProceso(proceso);
    }

    //Obtiene el codeword
    public void obtenerCodeword() {
        String proceso = "";
        int codeword[] = new int[dto.getColumnasCodeword()];
        int auxiliar = 0;

        for (int columna = 0; columna < dto.getColumnasCodeword(); columna++) {
            for (int fila = 0; fila < dto.getFilasCodeword(); fila++) {
                if (fila == 0)
                    proceso += String.valueOf(dto.getVectorXgenerador()[columna][fila]);
                else
                    proceso += " + " + String.valueOf(dto.getVectorXgenerador()[columna][fila]);

                if(fila < dto.getFilasCodeword()-1){
                    if(fila == 0){
                        if(dto.getVectorXgenerador()[columna][fila] == dto.getVectorXgenerador()[columna][fila+1])
                            auxiliar = 0;
                        else
                            auxiliar = 1;
                    }else{
                        if (auxiliar == dto.getVectorXgenerador()[columna][fila+1])
                            auxiliar = 0;
                        else
                            auxiliar = 1;
                    }
                }
            }
            proceso += " = " + auxiliar + "\n";
            codeword[columna] = auxiliar;
        }

        dto.setCodewordProceso(proceso);
        dto.setCodeword(codeword);
    }

    //Invierte el valor de una posicion especificada del codeword
    public void corromperBit(int posicion) {
        dto.setCodewordCorrupto(dto.getCodeword());

        if (dto.getCodeword()[posicion - 1] == 0) {
            dto.getCodewordCorrupto()[posicion - 1] = 1;
        } else {
            dto.getCodewordCorrupto()[posicion - 1] = 0;
        }
    }

    //Detecta el error  en el Codeword Corrompido, por medio de multiplicacion
    //de la checkmatrix (matriz de verificacion) por el codeword corrupto
    public void detectarError() {
        String operaciones = "";
        String resultado = "";
        String proceso = "";

        int bitCodeword = 0;
        int bitCheckmatrix = 0;
        int bitResultante = 0;

        for(int fila=0; fila<dto.getFilasCheckmatrix(); fila++){
            resultado = "";
            for(int columna=0; columna<dto.getColumnasCodeword(); columna++){
                bitCodeword = dto.getCodeword()[columna];
                bitCheckmatrix = dto.getCheckmatrix()[fila][columna];
                bitResultante = bitCheckmatrix * bitCodeword;

                dto.getCheckmatrixXcodeword()[fila][columna] = bitResultante;

                if (columna == 0) {
                    resultado += bitResultante;
                    operaciones = "(" + bitCheckmatrix + "x" + bitCodeword + ") ";
                } else {
                    resultado += "+" + bitResultante;
                    operaciones += "+ (" + bitCheckmatrix + "x" + bitCodeword + ") ";
                }
            }
            proceso += operaciones + "= " + resultado + "\n";
        }

        dto.setDeteccionErrorProceso(proceso);
        
        obtenerSyndrome();
    }

    //Obtiene el sindrome, por medio de la suma modulo2-addition
    //al resultado de multiplicar la checkmatrix (matriz de verificacion) por el codeword corrupto
    private void obtenerSyndrome() {
        String proceso = "";
        int syndrome[] = new int[dto.getFilasCheckmatrix()];
        int auxiliar = 0;
       
        for(int fila = 0; fila<dto.getFilasCheckmatrix(); fila++){
            for(int columna = 0; columna<dto.getColumnasCodeword(); columna++){
                if(columna == 0)
                    proceso += String.valueOf(dto.getCheckmatrixXcodeword()[fila][columna]);
                else
                    proceso += " + " + String.valueOf(dto.getCheckmatrixXcodeword()[fila][columna]);

                if (columna < dto.getColumnasCodeword()-1) {
                    if (columna == 0) {
                        if (dto.getCheckmatrixXcodeword()[fila][columna] == dto.getCheckmatrixXcodeword()[fila][columna+1])
                            auxiliar = 0;
                        else
                            auxiliar = 1;
                    } else {
                        if(auxiliar == dto.getCheckmatrixXcodeword()[fila][columna+1])
                            auxiliar = 0;
                        else
                            auxiliar = 1;
                    }
                }
            }
            proceso += " = " + auxiliar + "\n";
            syndrome[fila] = auxiliar;
        }
        
        dto.setSyndromeProceso(proceso);
        dto.setSyndrome(syndrome);
    }
    
    //detecta si existe o no sindrome 
    public boolean existeSyndrome(){
        boolean syndrome = false; 
        for(int i=0; i<dto.getFilasCheckmatrix(); i++){
            if (dto.getSyndrome()[i] == 1)
                syndrome = true;
        }
        
        return syndrome;
    }
    
    //Regresa el numero de la posicion donde se encuentra el error en el codeword corrupto
    public int obtenerPosicionError(){
        int posicionError = 0;
        int contador = 0;
        int columnaCheckmatrix = 0;
        int columnas = dto.getColumnasCodeword();
        int filas = dto.getFilasCheckmatrix();
        
        for(int columna=0; columna<columnas; columna++) {
            contador = 0;
            for(int fila=0; fila<filas; fila++){
                if (dto.getCheckmatrix()[fila][columna] == dto.getSyndrome()[fila])
                    contador++;
                else
                    fila = filas;
            }
            if (contador == filas){
                posicionError = columna+1;
                columnaCheckmatrix = columna+1;
                columna = columnas;
            }
        }
        
        dto.setColumnaCheckmatrix(columnaCheckmatrix);
        
        return posicionError;
    }
}
