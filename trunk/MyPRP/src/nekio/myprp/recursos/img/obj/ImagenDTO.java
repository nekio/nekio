package nekio.myprp.recursos.img.obj;

/**
 *
 * @author Nekio
 */

import java.awt.Dimension;
import java.awt.Image;
import java.util.Date;
import nekio.myprp.recursos.utilerias.plantillas.DTO;

public class ImagenDTO implements DTO{   

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    public static enum TipoImagen{
        VERTICAL(65, 95, 'V', 0),
        HORIZONTAL(95, 65, 'H', 1),
        AJUSTADO_CUADRADO(80, 80, 'A', 2);
        
        private Dimension dimension;
        private Character tipo;
        private Integer indice;
        
        private TipoImagen(int ancho, int alto, char tipo, int indice){
            this.dimension = new Dimension(ancho, alto);
            this.tipo = tipo;
            this.indice = indice;
        }
        
        public static TipoImagen TipoImagen(char tipo){
            TipoImagen tipoImagen = null;
            switch(tipo){
                case 'V': 
                    tipoImagen = ImagenDTO.TipoImagen.VERTICAL;
                break;
                case 'H':
                    tipoImagen = ImagenDTO.TipoImagen.HORIZONTAL;
                break;
                case 'A':
                    tipoImagen = ImagenDTO.TipoImagen.AJUSTADO_CUADRADO;
                break;
            }
            
            return tipoImagen;
        }

        public Dimension getDimension() {
            return dimension;
        }

        public char getTipo() {
            return tipo;
        }

        public int getIndice() {
            return indice;
        }
    }

    private Integer idImagen;
    private Image imagen;
    private String nombre;
    private Character tipo;
    private Date fechaSubida;
    private String descripcion;
    
    private String rutaImagen; // Atributo artificial
 
    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
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
    
    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
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
