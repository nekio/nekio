package rysi.sma.negocio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rysi.sma.negocio.modelo.TipoUsuario;

/**
 *
 * @author Ivan
 */

public interface TipoUsuarioDAO extends JpaRepository<TipoUsuario, Integer>{}
