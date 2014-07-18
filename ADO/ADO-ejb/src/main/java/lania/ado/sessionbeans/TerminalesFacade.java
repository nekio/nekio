
package lania.ado.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lania.ado.entidades.Terminales;

/**
 *
 * @author Nekio
 */
@Stateless
public class TerminalesFacade extends AbstractFacade<Terminales> implements TerminalesFacadeLocal {
    @PersistenceContext(unitName = "lania_ADO-ejb_ejb_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TerminalesFacade() {
        super(Terminales.class);
    }
    
}
