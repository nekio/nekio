package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;

public abstract class ObjetoNegocio {    
    protected String busqueda;
    
    public abstract String ejecutar(int metodo, Gestor gestor, ArrayList parametros);
    public abstract DTO obtenerModelo(Gestor gestor);
    public abstract String consultarId(Gestor gestor);
    public abstract String consultarSeleccion(Gestor gestor);
    public abstract String consultarBusqueda(Gestor gestor);
    
    public String consultarAccion(int metodo, DAO dao, ArrayList parametros){
        int resultado = 0;
        
        if(parametros!=null)
            dao.asignarParametros(parametros);
        
        if(metodo == Globales.BD.AGREGAR.getLlave())
            resultado = dao.agregar();
        else if(metodo == Globales.BD.ELIMINAR.getLlave())
            resultado = dao.eliminar();
        else if(metodo == Globales.BD.MODIFICAR.getLlave())
            resultado = dao.modificar();
        
        if(resultado == 0)
            return Globales.RES_OK;
        else
            return Globales.RES_ERROR;
    }
    
    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
}
