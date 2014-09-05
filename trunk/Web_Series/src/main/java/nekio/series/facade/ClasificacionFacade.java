package nekio.series.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.series.dto.Clasificacion;

/**
 *
 * @author Nekio
 */
@Stateless
public class ClasificacionFacade extends AbstractFacade<Clasificacion> {
    @PersistenceContext(unitName = "nekio_Web_Series_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasificacionFacade() {
        super(Clasificacion.class);
    }
    
}
