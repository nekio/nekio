
package lania.ado.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lania.ado.entidades.Autobuses;

/**
 *
 * @author Nekio
 */
@Stateless
public class AutobusesFacade extends AbstractFacade<Autobuses> implements AutobusesFacadeLocal {
    @PersistenceContext(unitName = "lania_ADO-ejb_ejb_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutobusesFacade() {
        super(Autobuses.class);
    }
    
}
