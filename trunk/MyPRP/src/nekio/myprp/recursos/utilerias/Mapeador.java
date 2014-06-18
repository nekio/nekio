package nekio.myprp.recursos.utilerias;

/**
 *
 * @author Nekio
 */

import java.util.ArrayList;
import java.util.List;
import nekio.myprp.recursos.img.obj.ImagenDTO;
import nekio.myprp.recursos.img.obj.vista.CatalogoImagenes;
import nekio.myprp.recursos.img.obj.vista.RecogedorImagen;
import nekio.myprp.recursos.utilerias.plantillas.DTO;
import nekio.myprp.recursos.utilerias.plantillas.Gestor;
import nekio.myprp.recursos.utilerias.plantillas.swing.SwingMaestro;
import nekio.myprp.sistema.acceso.vista.LoginGUI;
import nekio.myprp.sistema.acceso.vista.Menu;
import nekio.myprp.sistema.modulos.series.dto.MensajePrivadoDTO;
import nekio.myprp.sistema.modulos.series.vista.MensajePrivadoSwing;

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
                    new Menu();
                }else if(pagina.equals("")){
                    
                }
            break;
                
            /*********************************************************/
            /* MODULO DE IMAGEN*/
            /*********************************************************/
            case Globales.MOD_IMAGEN:
                entidad = Globales.Entidad.Imagen.name();
                List<ImagenDTO> parametrosImg = null;
                ImagenDTO parametroImg = null;
                
                if(pagina.equals(Globales.BD.LEER.getPagina()+entidad) || pagina.equals(Globales.BD.LEER_DESC.getPagina()+entidad)){
                    parametrosImg = new ArrayList<ImagenDTO>();
                    for(DTO dto:gestor.getListaDTO())
                        parametrosImg.add((ImagenDTO) dto);
                    
                    new CatalogoImagenes(parametrosImg);
                }else if(pagina.equals(Globales.BD.LEER_UNO.getPagina()+entidad)){
                    parametroImg = (ImagenDTO) gestor.getDTO();
                    
                    new RecogedorImagen(guiPadre, parametroImg);
                }else if(pagina.equals(Globales.BD.NUEVO.getPagina()+entidad)){                    
                    new RecogedorImagen(guiPadre);
                }else if(pagina.equals(Globales.BD.MODIFICAR.getPagina()+entidad)){                    
                    gestor.recargarGUI();
                }else if(pagina.equals(Globales.BD.INSERTAR.getPagina()+entidad)){                    
                    gestor.recargarGUI();
                }else if(pagina.equals(Globales.BD.ELIMINAR.getPagina()+entidad)){                    
                    gestor.recargarGUI();
                }
                
                parametrosImg = null;
                parametroImg = null;
            break;
                
            /*********************************************************/
            /* MODULO DE SERIES*/
            /*********************************************************/
            case Globales.MOD_SERIES:
                entidad = Globales.Entidad.MensajePrivado.name();
                List<MensajePrivadoDTO> parametrosMP = null;
                MensajePrivadoDTO parametroMP = null;
                
                if(pagina.equals(Globales.BD.LEER.getPagina()+entidad) || pagina.equals(Globales.BD.LEER_DESC.getPagina()+entidad)){
                    parametrosMP = new ArrayList<MensajePrivadoDTO>();
                    for(DTO dto:gestor.getListaDTO())
                        parametrosMP.add((MensajePrivadoDTO) dto);
                    
                    new MensajePrivadoSwing(parametrosMP);
                }
                
                parametrosMP = null;
                parametroMP = null;
            break;
            default:
                if(Globales.APP_DEBUG)
                    System.out.println("\nLa vista indicada no existe");
        }
    }
}
