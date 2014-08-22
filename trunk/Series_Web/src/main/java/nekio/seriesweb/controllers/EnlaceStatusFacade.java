package nekio.seriesweb.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.seriesweb.dto.EnlaceStatus;

/**
 *
 * @author SITI
 */
@Stateless
public class EnlaceStatusFacade extends AbstractFacade<EnlaceStatus> {
    @PersistenceContext(unitName = "nekio_Series_Web_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnlaceStatusFacade() {
        super(EnlaceStatus.class);
    }
    
}
