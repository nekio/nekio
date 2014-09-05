package nekio.series.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.series.dto.Enlace;

/**
 *
 * @author Nekio
 */
@Stateless
public class EnlaceFacade extends AbstractFacade<Enlace> {
    @PersistenceContext(unitName = "nekio_Web_Series_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnlaceFacade() {
        super(Enlace.class);
    }
    
}
