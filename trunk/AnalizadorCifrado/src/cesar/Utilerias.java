package cesar;

/**
 * 07/Jun/2014
 * LANIA - MRYSI
 * Seguridad en Redes
 * 
 * @author LCI. Emiliano Anastasio Landa
 *         eanastasio@veracruz.gob.mx
 * 
 * @author ISC. Sinesio Ivan Carrillo Heredia 
 *         ivan.carrillo@si-ti.com.mx
 * 
 */

import java.util.*;

public class Utilerias {
    private static ArrayList<Integer> valores;
    private static ArrayList<Character> listaCaracteres;
    
  public static String filtrar(String texto, Alfabeto alfa) {
        char arregloFiltrado [] = texto.toUpperCase().toCharArray();
        String textoFiltrado = "";
        
        for(int i = 0; i < arregloFiltrado.length; i++) {
            if( alfa.getIndice(arregloFiltrado[i]) >= 0) {
                textoFiltrado = textoFiltrado + arregloFiltrado[i];  
            }
        }
        return textoFiltrado;
    }
    
    public static void cuentaSimbolos(String txt, Alfabeto a) {
        valores = new ArrayList<Integer>();
        String filtrado = filtrar(txt, a);
        
        listaCaracteres = new ArrayList<Character>();
        for(char c : filtrado.toCharArray())
            listaCaracteres.add(c);
   
        int [] repeticiones = new int[a.getTotalSimbolos()];
        int indice = 0;
        for(int i = 0; i < listaCaracteres.size(); i++) {
            indice = a.getIndice(listaCaracteres.get(i));
            repeticiones[indice]++;
        }
        
        for(int i : repeticiones)
            valores.add(i);
        
        for(int n = 0; n < valores.size(); n++) {
            System.out.println(a.getLetra(n) + "\t" + valores.get(n));
        }
    }
    
    public static char masRepetida(String txt, Alfabeto a) {
        int [] valores = new int[a.getTotalSimbolos()];
        String filtrado = filtrar(txt, a);
        char [] listaCaracteres = filtrado.toCharArray();
        int indice;
        for(int i = 0; i < listaCaracteres.length; i++) {
            indice = a.getIndice(listaCaracteres[i]);
            valores[indice]++;
        }
        
        int mayor = valores[0];
        int indicemayor = 0;
        for(int n = 0; n < valores.length; n++) {
            if(mayor < valores[n]) {
                mayor = valores[n];
                indicemayor = n;
            }
        }
        
        return a.getLetra(indicemayor);
    }
}
