
package lania.ado.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lania.ado.entidades.Itinerarios;

/**
 *
 * @author Nekio
 */
@Stateless
public class ItinerariosFacade extends AbstractFacade<Itinerarios> implements ItinerariosFacadeLocal {
    @PersistenceContext(unitName = "lania_ADO-ejb_ejb_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItinerariosFacade() {
        super(Itinerarios.class);
    }
    
}
