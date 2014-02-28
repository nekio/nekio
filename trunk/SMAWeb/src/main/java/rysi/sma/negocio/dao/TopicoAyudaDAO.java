package rysi.sma.negocio.dao;

/**
 *
 * @author Ivan
 */

import org.springframework.data.jpa.repository.JpaRepository;
import rysi.sma.negocio.modelo.TopicoAyuda;

public interface TopicoAyudaDAO extends JpaRepository<TopicoAyuda, Integer>{}
