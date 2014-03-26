package hamming.hammingcode;

import java.util.ArrayList;

public class HammingDTO {
    private String cadenaBitsOriginales;
    private String cadenaBitsHamming;
    private ArrayList<Boolean> bitsOriginales;
    private ArrayList<Boolean> bitsHamming;

    public String getCadenaBitsOriginales() {
        return cadenaBitsOriginales;
    }

    public void setCadenaBitsOriginales(String cadenaBitsOriginales) {
        this.cadenaBitsOriginales = cadenaBitsOriginales;
    }

    public String getCadenaBitsHamming() {
        return cadenaBitsHamming;
    }

    public void setCadenaBitsHamming(String cadenaBitsHamming) {
        this.cadenaBitsHamming = cadenaBitsHamming;
    }

    public ArrayList<Boolean> getBitsOriginales() {
        return bitsOriginales;
    }

    public void setBitsOriginales(ArrayList<Boolean> bitsOriginales) {
        this.bitsOriginales = bitsOriginales;
    }

    public ArrayList<Boolean> getBitsHamming() {
        return bitsHamming;
    }

    public void setBitsHamming(ArrayList<Boolean> bitsHamming) {
        this.bitsHamming = bitsHamming;
    }
}
