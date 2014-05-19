package test;

import nekio.myprp.sistema.acceso.Inicializacion;

/**
 *
 * @author Nekio
 */
public class Main {
    public static void main(String[] args) {
        Inicializacion inicio = new Inicializacion("usuario_02","pssword_02");
        System.out.println(inicio.probarImagen());
        
        /*ArrayList campos = new ArrayList();
        campos.add("idcampo1");
        campos.add("iDcampo2");
        campos.add("IDcampo3");
        campos.add("id_campo4");
        campos.add("campo5");
        campos.add("campo6");
        campos.add("campo7");
        campos.add("campo8");
        campos.add("campo9");
        campos.add("campo10");
        
        ArrayList valores = new ArrayList();
        valores.add("valorfrefre1");
        valores.add(new BufferedImage(1,2,3));
        valores.add("valor3");
        valores.add("valor4");
        valores.add("valor5");
        valores.add("valor6");
        valores.add("valor7");
        valores.add("valor8");
        valores.add("valor9");
        valores.add("valor10");
        
        ArrayList tipos = new ArrayList();
        tipos.add(0);
        tipos.add(4);
        tipos.add(0);
        tipos.add(0);
        tipos.add(0);
        tipos.add(0);
        tipos.add(0);
        tipos.add(0);
        tipos.add(0);
        tipos.add(0);
        
        new GUI("prueba",campos,valores,tipos);
        */
        
        //System.out.println(inicio.loggear());
//        try{
//            new GeneradorPseudotablas(BDConexion.getConnection(), Globales.BD_ESQUEMA, false, "C:\\Users\\SITI\\Documents\\NetBeansProjects\\otros\\luis\\OBD_VW\\Tablas").generarTablas();
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
}
