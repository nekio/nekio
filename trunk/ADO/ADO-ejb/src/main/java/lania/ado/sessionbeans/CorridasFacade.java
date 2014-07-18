
package lania.ado.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lania.ado.entidades.Corridas;

/**
 *
 * @author Nekio
 */
@Stateless
public class CorridasFacade extends AbstractFacade<Corridas> implements CorridasFacadeLocal {
    @PersistenceContext(unitName = "lania_ADO-ejb_ejb_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CorridasFacade() {
        super(Corridas.class);
    }
    
}
