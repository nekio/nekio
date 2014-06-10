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
            getPorcentajes().add(porcentaje);
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
            simbolo = Alfabeto.Espanol.getOrdenFrecuencia().get(indice);//alfabeto.getInstanciaLetra(indice);
            frecuenciaSimbolo = simbolo.getFrecuencia();
            
            letrasCifradas = new ArrayList<Espanol>();
            frecuencias = new ArrayList<Float>();
            
            for(int j=0; j<getPorcentajes().size(); j++){
                simboloAuxiliar = alfabeto.getInstanciaLetra(j);
                frecuenciaCifrado = getPorcentajes().get(j);
                diferencia = Math.abs(frecuenciaSimbolo - frecuenciaCifrado);
                
                if(diferencia < 5){
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
        System.out.println("---DESCIFRADO POR SUSTITUCION---");
        String textoAproximado = textoCifrado;
        String auxiliar = "";
        List<Espanol> simbolosProbables = obtenerAproximado();

        crearComodines();
        
        int indiceProbable = 0;
        char simboloOriginal = '-';
        char simboloProbable = '-';
        char comodin = '-';
        
        // Reemplazar todos los simbolos del alfabeto por comodines
        System.out.println("\nEQUIVALENCIAS DEL ALFABETO A COMODINES");
        int indice = 0;
        for(Espanol letra:Alfabeto.Espanol.getOrdenFrecuencia()){
            try{
                simboloOriginal = letra.name().charAt(0);
                comodin = comodines.get(indice);
                
                System.out.println(simboloOriginal+" = "+comodin);

                textoAproximado = textoAproximado.replace(simboloOriginal, comodin);
            }catch(Exception e){}
            indice++;
        }
        
        // Reemplazar comodines con simbolos probables
        System.out.println("\nREEMPLAZO DE COMODINES");
        for(int i=0; i<alfabeto.getTotalSimbolos(); i++){
            Espanol ordinalLetraProbable = new Alfabeto().getInstanciaLetra(simbolosProbables.get(i).ordinal());
            indiceProbable = Alfabeto.Espanol.getOrdenFrecuencia().indexOf(ordinalLetraProbable);
            comodin = comodines.get(indiceProbable);
            simboloProbable = Alfabeto.Espanol.getOrdenFrecuencia().get(i).name().charAt(0);
            
            System.out.println(comodin + " = " +simboloProbable);
            
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
        obtenerEstadisticas();
        ajustarPromedios();
        
        aproximadosPorFrecuencia = new ArrayList<Espanol>();
        Espanol simbolo = null;

        int indice = 0;
        List<Espanol> aproximadosTemp = null;
        for(Espanol letra:Alfabeto.Espanol.getOrdenFrecuencia()){
            aproximadosTemp = new ArrayList<Espanol>();
            for(Object simboloProbable:simbolosProbables.get(indice))
                aproximadosTemp.add((Espanol)simboloProbable);
            
            simbolo = obtenerMasCercano(indice, aproximadosTemp);
            getAproximadosPorFrecuencia().add(simbolo);
            indice++;
        }
                 
        return getAproximadosPorFrecuencia();
    }
    
    private Espanol obtenerMasCercano(int indice, List<Espanol> contenidos){
        List<Espanol> listaProbables = simbolosProbables.get(indice);
        Espanol aproximado = null;
        
        double menor = 100.00;
        int masCercano = 0;
        
        float diferencia = 0.0f;
        
        Espanol simboloCifrado = null;
        Espanol simboloFrecuencia = frecuenciasEspanol.get(indice);
        
        System.out.println("\nProbabilidades de " + simboloFrecuencia.name() + " (" + simboloFrecuencia.getFrecuencia() + "):");
        boolean agregado=false;
        for(int i=0; i<frecuenciasProbables.get(indice).size(); i++){
            diferencia = (float)frecuenciasProbables.get(indice).get(i);
            simboloCifrado = (Espanol)listaProbables.get(i);

            System.out.println(simboloCifrado+" "+diferencia);
            
            if(diferencia < menor && !aproximadosPorFrecuencia.contains(simboloCifrado)){
                agregado=true;
                menor=diferencia;
                masCercano=i;
            }
        }
        
        //Si no se agrego con los simbolos probables
        if(!agregado){
            //recorrer todo el alfabeto
            for(int i=0; i<alfabeto.getTotalSimbolos();i++){
                simboloCifrado = alfabeto.getInstanciaLetra(i);
                
                //asignarle el primer simbolo que no haya sido grabado antes
                if(!aproximadosPorFrecuencia.contains(simboloCifrado)){
                    aproximado = simboloCifrado;
                    break;
                }
            }
        }else
            aproximado = (Espanol)listaProbables.get(masCercano);
        
        System.out.println("[Escogido por frecuencia mas cercana: " + aproximado + "]");
        
        return aproximado;
    }
    
    public String descifrarCesar(int desplazamiento){
        if(desplazamiento < 0)
            desplazamiento = 0;
        
        Cifrados cesar = new Cifrados(alfabeto);
        obtenerEstadisticas();
        
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

    public List<Espanol> getAproximadosPorFrecuencia() {
        return aproximadosPorFrecuencia;
    }
}
