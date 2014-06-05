package analizador;

/**
 * 07/Jun/2014
 * LANIA - MRYSI
 * Seguridad en Redes
 * 
 * @author LI. Emiliano Anastasio Landa
 *         eanastasio@veracruz.gob.mx
 * 
 * @author ISC. Sinesio Ivan Carrillo Heredia 
 *         ivan.carrillo@si-ti.com.mx
 * 
 */

import herramientas.Alfabeto;
import herramientas.Alfabeto.Espanol;
import herramientas.Cifrados;
import static herramientas.Utilerias.filtrar;
import java.util.ArrayList;
import java.util.List;

public class Analizador {
    private Alfabeto alfabeto;
    private List<Espanol> frecuenciasEspanol;
    private List<Espanol> aproximadosPorFrecuencia;
            
    private String textoCifrado;
    private List<Character> caracteresCifrados;
    private List<Character> comodines;
    private List<Integer> valores;
    private List<Float> porcentajes;
    
    private double porcentajeTotal;
    
    private List<List> frecuenciasProbables;
    private List<List> simbolosProbables;    
    
    public Analizador(Alfabeto alfabeto, String textoCifrado){
        this.alfabeto = alfabeto;        
        this.textoCifrado = textoCifrado;
        
        this.frecuenciasEspanol = Alfabeto.Espanol.getOrdenFrecuencia();
        
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
        
        int indice = 0;
        for(Espanol letra:Alfabeto.Espanol.getOrdenFrecuencia()){
            simbolo = alfabeto.getInstanciaLetra(indice);
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
            
            indice++;
        }
    }
    
    public String sustituirAproximado(){
        System.out.println("-------------------------");
        String textoAproximado = textoCifrado;
        String auxiliar = "";
        List<Espanol> simbolosProbables = obtenerAproximado();

        crearComodines();
        
        char simboloOriginal = '-';
        char simboloProbable = '-';
        char comodin = '-';
        
        // Reemplazar todos los simbolos del alfabeto por comodines
        System.out.println("\nEQUIVALENCIAS DEL ALFABETO A COMODINES");
        for(int i=0; i<alfabeto.getTotalSimbolos(); i++){
            try{
                simboloOriginal = alfabeto.getLetra(i);
                comodin = comodines.get(i);
                
                System.out.println(simboloOriginal+" = "+comodin);

                textoAproximado = textoAproximado.replace(simboloOriginal, comodin);
            }catch(Exception e){}
        }
        
        // Reemplazar comodines con simbolos probables
        System.out.println("\nREEMPLAZO DE COMODINES");
        for(int i=0; i<alfabeto.getTotalSimbolos(); i++){
            comodin = comodines.get(i);
            simboloProbable = simbolosProbables.get(i).name().charAt(0);

            ////////////////////////////////////////////////
            // AQUI EN ESTA PARTE ES DONDE DEBE DE VERSE LO DEL REEMPLAZO DE LOS COMODINES POR LOS SIMBOLOS PROBABLES
            ////////////////////////////////////////////////
            System.out.println(simboloProbable+" = "+comodin);
            textoAproximado = textoAproximado.replace(comodin, simboloProbable);
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
        aproximadosPorFrecuencia = new ArrayList<Espanol>();
        Espanol simbolo = null;

        int indice = 0;
        List<Espanol> aproximadosTemp = null;
        for(Espanol letra:Alfabeto.Espanol.getOrdenFrecuencia()){
            aproximadosTemp = new ArrayList<Espanol>();
            for(Object simboloProbable:simbolosProbables.get(indice))
                aproximadosTemp.add((Espanol)simboloProbable);
            
            simbolo = obtenerMasCercano(indice, aproximadosTemp);
            aproximadosPorFrecuencia.add(simbolo);
            indice++;
        }
                 
        return aproximadosPorFrecuencia;
    }
    
    private Espanol obtenerMasCercano(int indice, List<Espanol> contenidos){
        Espanol aproximado = null;
        
        double menor = 100.00;
        int masCercano = 0;
        
        float frecuencia = 0.0f;
        Espanol simboloCifrado = null;
        
        System.out.println("\nProbabilidades de " + frecuenciasEspanol.get(indice).name() + ":");
        for(int j=0; j<frecuenciasProbables.get(indice).size(); j++){
            frecuencia = (float)frecuenciasProbables.get(indice).get(j);
            simboloCifrado = (Espanol)simbolosProbables.get(indice).get(j);

            System.out.println(simboloCifrado+" "+frecuencia);

            ////////////////////////////////////////////////
            // AQUI EN ESTA PARTE ES DONDE DEBE DE VERSE LO DE NO REPETIR NINGUN SIMBOLO EN LA LISTA DE SIMBOLOS PROBABLES
            ////////////////////////////////////////////////
            if(frecuencia < menor && !aproximadosPorFrecuencia.contains(simboloCifrado)){
                menor=frecuencia;
                masCercano=j;
            }
        }
        
        aproximado = (Espanol)simbolosProbables.get(indice).get(masCercano);
        System.out.println("[Escogido por frecuencia mas cercana: " + aproximado + "]");
        
        return aproximado;
    }
    
    public String descifrarCesar(int desplazamiento){
        Cifrados cesar = new Cifrados(alfabeto);
        
        int distanciaPicos= 4; //posiciones distantes entre A y E (Los picos de la estadistica)
        
        int masRepetida = cesar.encontrarMasRepetido(textoCifrado); //generalmente va a ser la que equivale a E
        int probableA = masRepetida - distanciaPicos; //ajustamos las posiciones para estar en el indice probable de A
        
        //int llave = cesar.encontrarLlaveCesar(masRepetidaOriginal, masRepetidaCifrada);
        String contenidoDescifrado = cesar.descifrarCesar(textoCifrado, probableA + desplazamiento);
        
        return contenidoDescifrado;
    }
    
    public List<Character> getCaracteresCifrados() {
        return caracteresCifrados;
    }

    public List<Character> getComodines() {
        return comodines;
    }

    public List<Integer> getValores() {
        return valores;
    }

    public List<Float> getPorcentajes() {
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
