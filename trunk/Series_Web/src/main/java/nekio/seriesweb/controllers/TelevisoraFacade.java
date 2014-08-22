package nekio.seriesweb.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.seriesweb.dto.Televisora;

/**
 *
 * @author SITI
 */
@Stateless
public class TelevisoraFacade extends AbstractFacade<Televisora> {
    @PersistenceContext(unitName = "nekio_Series_Web_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelevisoraFacade() {
        super(Televisora.class);
    }
    
}
