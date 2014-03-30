package hamming.hammingcode;

public class HammingDTO {
    private String vectorXgeneradorProceso;
    private String codewordProceso;
    private String deteccionErrorProceso;
    private String syndromeProceso;
    private int[][] vectorXgenerador;
    private int[][] matrizGeneradora;
    private int[][] checkmatrix;
    private int[][] checkmatrixXcodeword;
    private int[] vector;
    private int[] codeword;
    private int[] codewordCorrupto;
    private int[] syndrome;
    private int filasVector;
    private int columnasVector;
    private int filasCodeword;
    private int columnasCodeword;
    private int filasCheckmatrix;
    private int columnaCheckmatrix;

    public String getVectorXgeneradorProceso() {
        return vectorXgeneradorProceso;
    }

    public void setVectorXgeneradorProceso(String vectorXgeneradorProceso) {
        this.vectorXgeneradorProceso = vectorXgeneradorProceso;
    }

    public String getCodewordProceso() {
        return codewordProceso;
    }

    public void setCodewordProceso(String codewordProceso) {
        this.codewordProceso = codewordProceso;
    }
    
    public String getDeteccionErrorProceso() {
        return deteccionErrorProceso;
    }

    public void setDeteccionErrorProceso(String deteccionErrorProceso) {
        this.deteccionErrorProceso = deteccionErrorProceso;
    }
    
    public String getSyndromeProceso() {
        return syndromeProceso;
    }

    public void setSyndromeProceso(String syndromeProceso) {
        this.syndromeProceso = syndromeProceso;
    }

    public int[][] getVectorXgenerador() {
        return vectorXgenerador;
    }

    public void setVectorXgenerador(int[][] vectorXgenerador) {
        this.vectorXgenerador = vectorXgenerador;
    }

    public int[][] getMatrizGeneradora() {
        return matrizGeneradora;
    }

    public void setMatrizGeneradora(int[][] matrizGeneradora) {
        this.matrizGeneradora = matrizGeneradora;
    }

    public int[][] getCheckmatrix() {
        return checkmatrix;
    }

    public void setCheckmatrix(int[][] checkmatrix) {
        this.checkmatrix = checkmatrix;
    }

    public int[][] getCheckmatrixXcodeword() {
        return checkmatrixXcodeword;
    }

    public void setCheckmatrixXcodeword(int[][] checkmatrixXcodeword) {
        this.checkmatrixXcodeword = checkmatrixXcodeword;
    }

    public int[] getVector() {
        return vector;
    }

    public void setVector(int[] vector) {
        this.vector = vector;
    }

    public int[] getCodeword() {
        return codeword;
    }

    public void setCodeword(int[] codeword) {
        this.codeword = codeword;
    }
    
    public int[] getCodewordCorrupto() {
        return codewordCorrupto;
    }

    public void setCodewordCorrupto(int[] codewordCorrupto) {
        this.codewordCorrupto = codewordCorrupto;
    }

    public int[] getSyndrome() {
        return syndrome;
    }

    public void setSyndrome(int[] syndrome) {
        this.syndrome = syndrome;
    }

    public int getFilasVector() {
        return filasVector;
    }

    public void setFilasVector(int filasVector) {
        this.filasVector = filasVector;
    }

    public int getColumnasVector() {
        return columnasVector;
    }

    public void setColumnasVector(int columnasVector) {
        this.columnasVector = columnasVector;
    }

    public int getFilasCodeword() {
        return filasCodeword;
    }

    public void setFilasCodeword(int filasCodeword) {
        this.filasCodeword = filasCodeword;
    }

    public int getColumnasCodeword() {
        return columnasCodeword;
    }

    public void setColumnasCodeword(int columnasCodeword) {
        this.columnasCodeword = columnasCodeword;
    }

    public int getFilasCheckmatrix() {
        return filasCheckmatrix;
    }

    public void setFilasCheckmatrix(int filasCheckmatrix) {
        this.filasCheckmatrix = filasCheckmatrix;
    }
    
    public int getColumnaCheckmatrix() {
        return columnaCheckmatrix;
    }

    public void setColumnaCheckmatrix(int columnaCheckmatrix) {
        this.columnaCheckmatrix = columnaCheckmatrix;
    }
}
