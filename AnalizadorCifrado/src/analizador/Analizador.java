package analizador;

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

import cesar.Alfabeto;
import cesar.Alfabeto.Espanol;
import cesar.Cesar;
import static cesar.Utilerias.filtrar;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Analizador {
    private Alfabeto alfabeto;
    private String textoCifrado;
    private static List<Character> caracteresCifrados;
    private static List<Character> comodines;
    private static List<Integer> valores;
    private static List<Float> porcentajes;
    private double porcentajeTotal;
    
    private List<List> frecuenciasProbables;
    private List<List> simbolosProbables;    
    
    public Analizador(Alfabeto alfabeto, String textoCifrado){
        this.alfabeto = alfabeto;        
        this.textoCifrado = textoCifrado;
        
        obtenerEstadisticas();
        ajustarPromedios();
    }
    
    private void obtenerEstadisticas(){
        cuentaSimbolos(textoCifrado, alfabeto);
        obtenerPorcentajes();
    }
    
    private void cuentaSimbolos(String txt, Alfabeto a) {
        valores = new ArrayList<Integer>();
        String filtrado = filtrar(txt, a);
        
        caracteresCifrados = new ArrayList<Character>();
        for(char c : filtrado.toCharArray())
            caracteresCifrados.add(c);
   
        int [] repeticiones = new int[a.getTotalSimbolos()];
        int indice = 0;
        for(int i = 0; i < caracteresCifrados.size(); i++) {
            indice = a.getIndice(caracteresCifrados.get(i));
            try{
                repeticiones[indice]++;
            }catch(Exception e){}
        }
        
        for(int i : repeticiones)
            valores.add(i);
    }
    
    private void obtenerPorcentajes(){
        porcentajes = new ArrayList<Float>();
        porcentajeTotal = 0.0;
        
        int totalSimbolosCifrados = 0;
        for(int valor : valores)
            totalSimbolosCifrados = totalSimbolosCifrados += valor;
        
        float porcentaje = 0.0f;
        for(float valor: valores){
            porcentaje = (valor / totalSimbolosCifrados) * 100;
            porcentajeTotal = porcentajeTotal + porcentaje;
            porcentajes.add(porcentaje);
        }
    }
    
    private void ajustarPromedios(){
        frecuenciasProbables = new ArrayList<List>();
        simbolosProbables = new ArrayList<List>();
        
        Espanol simbolo = null;
        Espanol simboloAuxiliar = null;
        float frecuenciaSimbolo = 0.0f;
        float frecuenciaCifrado = 0.0f;
        float diferencia = 0.0f;
        
        List<Espanol> letrasCifradas = null;
        List<Float> frecuencias = null;
        for(int i=0; i<alfabeto.getTotalSimbolos(); i++){
            simbolo = alfabeto.getInstanciaLetra(i);
            frecuenciaSimbolo = simbolo.getFrecuencia();
            
            letrasCifradas = new ArrayList<Espanol>();
            frecuencias = new ArrayList<Float>();
            for(int j=0; j<porcentajes.size(); j++){
                simboloAuxiliar = alfabeto.getInstanciaLetra(j);
                frecuenciaCifrado = porcentajes.get(j);
                diferencia = Math.abs(frecuenciaSimbolo - frecuenciaCifrado);
                
                if(diferencia < 4 && diferencia > -4){
                    letrasCifradas.add(simboloAuxiliar);
                    frecuencias.add(diferencia);
                }
            }
            frecuenciasProbables.add(frecuencias);
            simbolosProbables.add(letrasCifradas);
        }
    }
    
    public String sustituirAproximado(){
        String textoAproximado = textoCifrado;
        String auxiliar = "";
        List<Espanol> simbolosProbables = obtenerAproximado();
        
        crearComodines();
        
        char simboloOriginal = '-';
        char simboloProbable = '-';
        char comodin = '-';
        for(int i=0; i<alfabeto.getTotalSimbolos(); i++){
            try{
                simboloOriginal = alfabeto.getLetra(i);
                simboloProbable = simbolosProbables.get(i).name().charAt(0);
                comodin = comodines.get(i);

                textoAproximado = textoAproximado.replace(simboloOriginal, comodin);
                textoAproximado = textoAproximado.replace(simboloProbable, simboloOriginal);
                //System.out.println(simboloOriginal +"="+simboloProbable);
            }catch(Exception e){}
        }
                    
        return textoAproximado;
    }
    
    private void crearComodines(){
        int inicio = 697;
        comodines = new ArrayList<Character>();
        
        Character comodin = null;
        for(int i=inicio; i<alfabeto.getTotalSimbolos()+inicio; i++)
            comodines.add((char)i);
    }
    
    public List<Espanol> obtenerAproximado(){
        List<Espanol> aproximado = new ArrayList<Espanol>();
        
        float frecuencia = 0.0f;
        Espanol simboloCifrado = null;
        Espanol simboloProbable = null;
        for(int i=0; i<alfabeto.getTotalSimbolos(); i++){
            double menor = 100.00;
            int masCercano = 0;
            
            System.out.println("\nProbabilidades de "+alfabeto.getLetra(i)+":");
            for(int j=0; j<frecuenciasProbables.get(i).size(); j++){
                frecuencia = (float)frecuenciasProbables.get(i).get(j);
                simboloCifrado = (Espanol)simbolosProbables.get(i).get(j);
                simboloProbable = (Espanol)simbolosProbables.get(i).get(j);
                
                System.out.println(simboloCifrado+" "+frecuencia);
                if(frecuencia<menor && !aproximado.contains(simboloProbable)){
                    menor=frecuencia;
                    masCercano=j;
                }
            }
            System.out.println("[Escogido por frecuencia mas cercana: "+(Espanol)simbolosProbables.get(i).get(masCercano)+"]");
            aproximado.add((Espanol)simbolosProbables.get(i).get(masCercano));
        }
          
        return aproximado;
    }
}
