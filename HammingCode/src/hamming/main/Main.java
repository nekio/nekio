package hamming.main;

/**
 *
 * @author Emiliano Anastasio Landa
 * @author ISC. Sinesio Ivan Carrillo Heredia
 */


import hamming.gui.GUI;
import hamming.hammingcode.HammingDTO;

public class Main {
    public static void main(String[] args) {
        new GUI(new HammingDTO());
        /*Hamming hamming = new Hamming();
        entrada = new Scanner(System.in);
        
        int longitudDeDatos = hamming.definirValores(entrada);
        hamming.generarArreglos(longitudDeDatos);
        
        System.out.print("\n\nPosicion de error: ");
        int error = entrada.nextInt();
        
        int posicionError =  hamming.calcularErrorEnArray(error, longitudDeDatos);
        System.out.println("\nError detectado en la posicion: " + posicionError);
        * */
    }
    
}
