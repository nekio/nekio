package hamming.hammingcode;

import java.util.ArrayList;

public class HammingDTO {
    private ArrayList<Boolean> bitsOriginales;
    private ArrayList<Boolean> bitsHamming;
    private ArrayList<Boolean> bitsHammingCorrupto;

    public String getCadenaBitsOriginales() {
        String cadenaBitsOriginales = "";
        for(boolean bit:bitsOriginales)
            cadenaBitsOriginales += bit==true?"1":"0";
                    
        return cadenaBitsOriginales;
    }

    public String getCadenaBitsHamming() {
        String cadenaBitsHamming = "";
        for(boolean bit:bitsOriginales)
            cadenaBitsHamming += bit==true?"1":"0";
        
        return cadenaBitsHamming;
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

    public ArrayList<Boolean> getBitsHammingCorrupto() {
        return bitsHammingCorrupto;
    }

    public void setBitsHammingCorrupto(ArrayList<Boolean> bitsHammingCorrupto) {
        this.bitsHammingCorrupto = bitsHammingCorrupto;
    }
}
