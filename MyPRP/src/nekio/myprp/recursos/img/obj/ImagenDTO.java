package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import java.awt.Dimension;
import java.awt.Image;
import java.util.Date;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class ImagenDTO extends DTO{
    public static enum TipoImagen{
        VERTICAL(65, 95, 'V'),
        HORIZONTAL(95, 65, 'H'),
        AJUSTADO_CUADRADO(80, 80, 'A');
        
        private Dimension dimension;
        private char tipoImagen;
        
        private TipoImagen(int ancho, int alto, char tipoImagen){
            this.dimension = new Dimension(ancho, alto);
            this.tipoImagen = tipoImagen;
        }

        public Dimension getDimension() {
            return dimension;
        }

        public char getTipoImagen() {
            return tipoImagen;
        }
    }
    
    private int idImagen;
    private Image imagen;
    private String nombre;
    private char tipo;
    private Date fechaSubida;
    private String descripcion;
 
    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }
    
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
    
    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}
