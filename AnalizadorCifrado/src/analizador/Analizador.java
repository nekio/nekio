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
import java.util.ArrayList;
import java.util.List;

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
        this.textoCifrado = new Cesar().cifrar(textoCifrado,3);
        
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
        
        // Reemplazar todos los simbolos del alfabeto por comodines
        for(int i=0; i<alfabeto.getTotalSimbolos(); i++){
            try{
                simboloOriginal = alfabeto.getLetra(i);
                comodin = comodines.get(i);

                textoAproximado = textoAproximado.replace(simboloOriginal, comodin);
            }catch(Exception e){}
        }
        
        // Reemplazar comodines con simbolos probables
        for(int i=0; i<alfabeto.getTotalSimbolos(); i++){
            try{
                comodin = comodines.get(simbolosProbables.get(i).ordinal());
                simboloProbable = simbolosProbables.get(i).name().charAt(0);

                System.out.println(comodin+"="+simboloProbable);
                textoAproximado = textoAproximado.replace(comodin, simboloProbable);
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
        List<Espanol> aproximados = new ArrayList<Espanol>();
        Espanol simbolo = null;

        simbolo = obtenerMasCercano(Alfabeto.Espanol.E.ordinal(), aproximados);
        aproximados.add(simbolo);
          
        simbolo = obtenerMasCercano(Alfabeto.Espanol.A.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.O.ordinal(), aproximados);
        aproximados.add(simbolo);
          
        simbolo = obtenerMasCercano(Alfabeto.Espanol.S.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.R.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.N.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.I.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.D.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.L.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.C.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.T.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.U.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.M.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.P.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.B.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.G.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.V.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.Y.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.Q.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.H.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.F.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.Z.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.J.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.Ñ.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.X.ordinal(), aproximados);
        aproximados.add(simbolo);
        
        simbolo = obtenerMasCercano(Alfabeto.Espanol.W.ordinal(), aproximados);
        aproximados.add(simbolo);
                
        simbolo = obtenerMasCercano(Alfabeto.Espanol.K.ordinal(), aproximados);
        aproximados.add(simbolo);
                 
        return aproximados;
    }
    
    private Espanol obtenerMasCercano(int indice, List<Espanol> contenidos){
        Espanol aproximado = null;
        
        double menor = 100.00;
        int masCercano = 0;
        
        float frecuencia = 0.0f;
        Espanol simboloCifrado = null;
        Espanol simboloProbable = null;
        
        System.out.println("\nProbabilidades de "+alfabeto.getLetra(indice)+":");
        for(int j=0; j<frecuenciasProbables.get(indice).size(); j++){
            frecuencia = (float)frecuenciasProbables.get(indice).get(j);
            simboloCifrado = (Espanol)simbolosProbables.get(indice).get(j);
            simboloProbable = (Espanol)simbolosProbables.get(indice).get(j);

            if(frecuencia<menor && !contenidos.contains((Espanol)simbolosProbables.get(indice).get(j))){
                System.out.println(simboloCifrado+" "+frecuencia);
                
                menor=frecuencia;
                masCercano=j;
            }
        }
        
        aproximado = (Espanol)simbolosProbables.get(indice).get(masCercano);
        System.out.println("[Escogido por frecuencia mas cercana: " + aproximado + "]");
        
        return aproximado;
    }
    
    public String descifrarCesar(int i){
        String texto = "";
        
        Cesar cesar = new Cesar();
        texto = cesar.descifrar(texto, i);
        
        return texto;
    }
    
    public static List<Character> getCaracteresCifrados() {
        return caracteresCifrados;
    }

    public static List<Character> getComodines() {
        return comodines;
    }

    public static List<Integer> getValores() {
        return valores;
    }

    public static List<Float> getPorcentajes() {
        return porcentajes;
    }
    
    public double getPorcentajeTotal() {
        return porcentajeTotal;
    }

    public List<List> getFrecuenciasProbables() {
        return frecuenciasProbables;
    }

    public List<List> getSimbolosProbables() {
        return simbolosProbables;
    }
}
