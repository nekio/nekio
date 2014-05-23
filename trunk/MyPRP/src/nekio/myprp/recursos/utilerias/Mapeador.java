package nekio.myprp.recursos.utilerias;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.img.obj.ImagenDTO;
import nekio.myprp.recursos.utilerias.gui.swing.CatalogoImagenes;
import nekio.myprp.recursos.utilerias.gui.swing.RecogedorImagen;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.SwingMaestro;
import nekio.myprp.sistema.acceso.vista.LoginGUI;

public class Mapeador {
    public static void abrir(Gestor gestor){
        String pagina = gestor.getPagina();
        SwingMaestro guiPadre = gestor.getGui();
        
        if(Globales.APP_DEBUG)
            System.out.println("\n|==========>  Mapeador abriendo la vista: " + pagina + "  <==========|");
        
        if(pagina.equals("login"))
            new LoginGUI();
        
        String entidad = null;
        switch(gestor.getModulo()){
            /*********************************************************/
            /* MODULO DE ACCESO */
            /*********************************************************/
            case Globales.MOD_ACCESO:
                if(pagina.equals("bienvenida")){
                    //new Pagina();
                }else if(pagina.equals("")){
                    
                }
            break;
                
            /*********************************************************/
            /* MODULO DE IMAGEN*/
            /*********************************************************/
            case Globales.MOD_IMAGEN:
                entidad = Globales.Entidad.Imagen.name();
                List<ImagenDTO> parametros = null;
                ImagenDTO parametro = null;
                
                if(pagina.equals(Globales.BD.LEER.getPagina()+entidad) || pagina.equals(Globales.BD.LEER_DESC.getPagina()+entidad)){
                    parametros = new ArrayList<ImagenDTO>();
                    for(DTO dto:gestor.getListaDTO())
                        parametros.add((ImagenDTO) dto);
                    
                    new CatalogoImagenes(parametros);
                }else if(pagina.equals(Globales.BD.LEER_UNO.getPagina()+entidad)){
                    parametro = (ImagenDTO) gestor.getDTO();
                    
                    new RecogedorImagen(guiPadre, parametro);
                }else if(pagina.equals(Globales.BD.NUEVO.getPagina()+entidad)){                    
                    new RecogedorImagen(guiPadre);
                }else if(pagina.equals(Globales.BD.MODIFICAR.getPagina()+entidad)){                    
                    gestor.recargarGUI();
                }else if(pagina.equals(Globales.BD.INSERTAR.getPagina()+entidad)){                    
                    gestor.recargarGUI();
                }else if(pagina.equals(Globales.BD.ELIMINAR.getPagina()+entidad)){                    
                    gestor.recargarGUI();
                }
                
                parametros = null;
                parametro = null;
            break;
            default:
                if(Globales.APP_DEBUG)
                    System.out.println("\nLa vista indicada no existe");
        }
    }
}
