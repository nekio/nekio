package nekio.seriesweb.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.seriesweb.dto.Web;

/**
 *
 * @author SITI
 */
@Stateless
public class WebFacade extends AbstractFacade<Web> {
    @PersistenceContext(unitName = "nekio_Series_Web_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WebFacade() {
        super(Web.class);
    }
    
}
