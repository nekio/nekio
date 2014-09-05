package nekio.tools.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.tools.dto.Sistema;

/**
 *
 * @author SITI
 */
@Stateless
public class SistemaFacade extends AbstractFacade<Sistema> {
    @PersistenceContext(unitName = "nekio_Web_Series_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SistemaFacade() {
        super(Sistema.class);
    }
    
}
