package rysi.sma.negocio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rysi.sma.negocio.modelo.Departamento;

/**
 *
 * @author Ivan
 */

public interface DepartamentoDAO extends JpaRepository<Departamento, Integer>{}
