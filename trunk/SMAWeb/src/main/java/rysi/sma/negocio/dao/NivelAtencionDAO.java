package rysi.sma.negocio.dao;

/**
 *
 * @author Ivan
 */

import org.springframework.data.jpa.repository.JpaRepository;
import rysi.sma.negocio.modelo.NivelAtencion;

public interface NivelAtencionDAO extends JpaRepository<NivelAtencion, Integer>{}
