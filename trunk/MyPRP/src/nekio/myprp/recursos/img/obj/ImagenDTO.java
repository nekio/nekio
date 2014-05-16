package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import java.awt.Image;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class ImagenDTO extends DTO{
    private int idImagen;
    private Image imagen;
    private String nombre;
    private String descripcion;
 
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }
 
    public Image getImagen() {
        return imagen;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
}
