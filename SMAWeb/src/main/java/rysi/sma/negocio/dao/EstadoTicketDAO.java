package rysi.sma.negocio.dao;

/**
 *
 * @author Ivan
 */

import org.springframework.data.jpa.repository.JpaRepository;
import rysi.sma.negocio.modelo.EstadoTicket;

public interface EstadoTicketDAO extends JpaRepository<EstadoTicket, Integer>{}
