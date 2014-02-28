package rysi.sma.negocio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rysi.sma.negocio.modelo.Ticket;

/**
 *
 * @author Ivan
 */

public interface TicketDAO extends JpaRepository<Ticket, Integer>{}