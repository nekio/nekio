
package lania.ado.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lania.ado.entidades.Boletos;

/**
 *
 * @author Nekio
 */
@Stateless
public class BoletosFacade extends AbstractFacade<Boletos> implements BoletosFacadeLocal {
    @PersistenceContext(unitName = "lania_ADO-ejb_ejb_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoletosFacade() {
        super(Boletos.class);
    }
    
}
