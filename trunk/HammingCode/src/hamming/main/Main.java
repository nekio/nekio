package hamming.main;

/**
 *
 * @author LI. Emiliano Anastasio Landa
 * @author ISC. Sinesio Ivan Carrillo Heredia
 */

import hamming.gui.GUI;
import hamming.hammingcode.HammingDTO;

public class Main {
    public static void main(String[] args) {
        new GUI(new HammingDTO());
    }
}
