package rysi.sma.negocio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rysi.sma.negocio.modelo.Usuario;

/**
 *
 * @author Ivan
 */

public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{}
