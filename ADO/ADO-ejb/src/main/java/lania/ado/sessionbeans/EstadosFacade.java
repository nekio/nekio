
package lania.ado.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lania.ado.entidades.Estados;

/**
 *
 * @author Nekio
 */
@Stateless
public class EstadosFacade extends AbstractFacade<Estados> implements EstadosFacadeLocal {
    @PersistenceContext(unitName = "lania_ADO-ejb_ejb_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosFacade() {
        super(Estados.class);
    }
    
}
