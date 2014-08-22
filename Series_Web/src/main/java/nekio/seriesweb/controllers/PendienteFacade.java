package nekio.seriesweb.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.seriesweb.dto.Pendiente;

/**
 *
 * @author SITI
 */
@Stateless
public class PendienteFacade extends AbstractFacade<Pendiente> {
    @PersistenceContext(unitName = "nekio_Series_Web_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PendienteFacade() {
        super(Pendiente.class);
    }
    
}
