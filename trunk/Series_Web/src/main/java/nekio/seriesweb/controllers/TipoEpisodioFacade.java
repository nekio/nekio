package nekio.seriesweb.controllers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.seriesweb.dto.TipoEpisodio;

/**
 *
 * @author SITI
 */
@Stateless
public class TipoEpisodioFacade extends AbstractFacade<TipoEpisodio> {
    @PersistenceContext(unitName = "nekio_Series_Web_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoEpisodioFacade() {
        super(TipoEpisodio.class);
    }
    
}
