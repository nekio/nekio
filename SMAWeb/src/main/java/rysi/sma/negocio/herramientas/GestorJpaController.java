package rysi.sma.negocio.herramientas;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rysi.sma.negocio.dao.DepartamentoJpaController;
import rysi.sma.negocio.dao.EstadoTicketJpaController;
import rysi.sma.negocio.dao.NivelAtencionJpaController;
import rysi.sma.negocio.dao.TicketJpaController;
import rysi.sma.negocio.dao.TopicoAyudaJpaController;
import rysi.sma.negocio.dao.UsuarioJpaController;
import rysi.sma.negocio.dao.VSolicitudesTicketsDeptoJpaController;
import rysi.sma.negocio.dao.VTiempoPromedioResolucionTicketsJpaController;

/**
 *
 * @author Ivan
 */
public class GestorJpaController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("rysi.sma_SMAWeb_war_1.0-SNAPSHOTPU");
    
    private UsuarioJpaController usuarioJC;
    private TicketJpaController ticketJC;
    private EstadoTicketJpaController estadoTicketJC;
    private NivelAtencionJpaController nivelAtencionJC;
    private DepartamentoJpaController departamentoJC;
    private TopicoAyudaJpaController topicoAyudaJC;
    private VSolicitudesTicketsDeptoJpaController solicitudesTicketsDeptoJC;
    private VTiempoPromedioResolucionTicketsJpaController tiempoPromedioResolucionTicketsJC;

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public UsuarioJpaController getUsuarioJC() {
        return new UsuarioJpaController(emf);
    }

    public TicketJpaController getTicketJC() {
        return new TicketJpaController(emf);
    }

    public EstadoTicketJpaController getEstadoTicketJC() {
        return new EstadoTicketJpaController(emf);
    }

    public NivelAtencionJpaController getNivelAtencionJC() {
        return new NivelAtencionJpaController(emf);
    }

    public DepartamentoJpaController getDepartamentoJC() {
        return new DepartamentoJpaController(emf);
    }

    public TopicoAyudaJpaController getTopicoAyudaJC() {
        return new TopicoAyudaJpaController(emf);
    }   
    
    public VSolicitudesTicketsDeptoJpaController getSolicitudesTicketsDeptoJC() {
        return solicitudesTicketsDeptoJC;
    }

    public VTiempoPromedioResolucionTicketsJpaController getTiempoPromedioResolucionTicketsJC() {
        return tiempoPromedioResolucionTicketsJC;
    }
}
