package nekio.myprp.recursos.utilerias.plantillas;

/**
 *
 * @author Nekio
 */

import nekio.myprp.recursos.utilerias.Globales;

public abstract class ObjetoNegocio {    
    protected String busqueda;
    
    public abstract String ejecutar(int metodo, Gestor gestor, DTO dto);
    public abstract DTO obtenerModelo(Gestor gestor);
    public abstract String consultarId(Gestor gestor);
    public abstract String consultarSeleccion(Gestor gestor);
    public abstract String consultarBusqueda(Gestor gestor);
    
    public String consultarAccion(int metodo, DAO dao, DTO dto){
        int resultado = 0;
        
        if(dto != null)
            dao.asignarParametros(dto);
        
        if(metodo == Globales.BD.INSERTAR.getLlave())
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
