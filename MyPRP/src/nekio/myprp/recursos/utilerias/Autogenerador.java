package nekio.myprp.recursos.utilerias;

/**
 *
 * @author Nekio
 */

import java.math.BigInteger;
import java.security.MessageDigest;

public class Autogenerador {    
    public static void main(String[] args) {
        String texto = "myprp";
        
        Autogenerador a = new Autogenerador();
        try{
            System.out.println(a.obtenerCadena(texto));
            System.out.println(a.crearAcceso(texto));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public String crearAcceso(String password) {
        String texto = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] pass = password.toCharArray();
            byte[] buffer = new byte[pass.length];
            for (int i = 0; i < buffer.length; ++i) {
                buffer[i] = (byte) pass[i];
            }

            md5.update(buffer);
            byte[] salida = md5.digest();

            BigInteger bi = new BigInteger(salida);
            texto = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }
    
    public String obtenerCadena(String entrada) throws Exception{
        StringBuilder cadena = new StringBuilder();
        int longitudCadena = entrada.length();
        
        if((longitudCadena < 5) || (longitudCadena > 15))
            throw new Exception();
        
        char letra = ' ';
        char basuraMay = ' ';
        char basuraMin = ' ';
        int basuraNum = 0;
        int dado = 0;
        for(int i=0; i<longitudCadena; i++){
            letra = (char)(entrada.charAt(i)+8);
            
            basuraMay = (char)((int)((Math.random()*26)+1)+64);
            basuraMin = (char)((int)((Math.random()*26)+1)+96);
            basuraNum = (int)(Math.random()*10);
            
            dado = (int)(Math.random()*4);
            
            switch(dado){
                case 0:
                    cadena.append(basuraMay).append(letra).append(basuraMin);
                break;
                case 1:
                    cadena.append(basuraMin).append(letra).append(basuraMay);
                break;
                case 2:
                    cadena.append(basuraNum).append(letra).append(basuraMin);
                break;
                case 3:
                    cadena.append(basuraMay).append(letra).append(basuraNum);
                break;
            }
        }
        
        return cadena.toString();
    }
    
    public String formarEntrada(String cadena){
        StringBuilder entrada = new StringBuilder();
        int longitudCadena = cadena.length();
        
        char letra = ' ';
        for(int i=0; i<longitudCadena; i++){
            if(i%3 == 0){
                letra = (char)(cadena.charAt(i+1)-8);
                entrada.append(letra);
            }
        }
        
        return entrada.toString();
    }
}

