package nekio.seriesweb.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.seriesweb.dto.Password;

/**
 *
 * @author SITI
 */
@Stateless
public class PasswordFacade extends AbstractFacade<Password> {
    @PersistenceContext(unitName = "nekio_Series_Web_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasswordFacade() {
        super(Password.class);
    }
    
}
