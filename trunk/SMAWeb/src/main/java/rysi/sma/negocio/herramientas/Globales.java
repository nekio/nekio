package rysi.sma.negocio.herramientas;

import rysi.sma.negocio.modelo.TipoUsuario;

/**
 *
 * @author Ivan
 */
public class Globales {
    public static String NOMBRE_SISTEMA = "SMA";
    
    public enum TipoUsuarios{
        ADMINISTRADOR(1), SUPERVISOR(2), SOPORTE_TECNICO(3),
        ADMINISTRATIVO(4), ACADEMICO(5), ESTUDIANTE(6), CLIENTE(7);
        
        private TipoUsuario id;
        
        private TipoUsuarios(int id){
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setIdTipoUsuario(id);
            
            this.id = tipoUsuario;
        }
        
        public TipoUsuario getId() {
            return id;
        }
    };
}
