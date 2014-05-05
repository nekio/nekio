package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;

public abstract class ObjetoNegocio {      
    public abstract String ejecutar(int metodo, Gestor gestor);
    public abstract DTO obtenerModelo(Gestor gestor);
    public abstract String consultarSeleccion(Gestor gestor);
    public abstract String consultarBusqueda(Gestor gestor);
    
    public String consultarAccion(int metodo, DAO dao){
        int resultado = 0;
        
        if(metodo == Globales.BD.AGREGAR.getLlave())
            resultado = dao.agregar();
        if(metodo == Globales.BD.ELIMINAR.getLlave())
            resultado = dao.eliminar();
        if(metodo == Globales.BD.MODIFICAR.getLlave())
            resultado = dao.modificar();
        
        if(resultado == 0)
            return Globales.RES_OK;
        else
            return Globales.RES_ERROR;
    }
}
