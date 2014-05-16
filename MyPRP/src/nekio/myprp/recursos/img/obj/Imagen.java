package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import nekio.myprp.recursos.utilerias.Globales;
import nekio.myprp.recursos.utilerias.plantillas.DAO;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.ObjetoNegocio;

public class Imagen extends ObjetoNegocio{    
    @Override
    public String ejecutar(int metodo, Gestor gestor, ArrayList parametros){
        String resultado = super.consultarAccion(metodo, new ImagenDAO(), parametros);
        
        return resultado;
    }
    
    @Override
    public String consultarSeleccion(Gestor gestor) {
        ImagenDAO dao = new ImagenDAO();

        String idImagen = (String) gestor.getParametros().get(0);
        ImagenDTO imagen = (ImagenDTO) dao.leerUno("id_imagen = " + idImagen);
        
        //Operaciones con el DTO obtenido
        //....
        
        return Globales.RES_OK;
    }

    @Override
    public String consultarBusqueda(Gestor gestor) {
        String where =" WHERE 1=1 ";

        if(!busqueda.equals("") && getBusqueda() != null)
                where += "AND (nombre + descripcion) " +
                          "LIKE '%" +   getBusqueda() + "%'";

        System.out.println("Campo búsqueda:" + getBusqueda());

        DAO dao = new ImagenDAO();
        //conexion

        listaDTO = dao.leer(where);

        return Globales.RES_OK; 
    }
    
    @Override
    public ImagenDTO obtenerModelo(Gestor gestor){
        ImagenDTO dto = new ImagenDTO();
        
        return dto;
    }
}
