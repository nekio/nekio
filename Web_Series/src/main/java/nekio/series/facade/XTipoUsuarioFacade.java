package nekio.series.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nekio.series.dto.XTipoUsuario;

/**
 *
 * @author SITI
 */
@Stateless
public class XTipoUsuarioFacade extends AbstractFacade<XTipoUsuario> {
    @PersistenceContext(unitName = "nekio_Web_Series_war_1.0-BETAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public XTipoUsuarioFacade() {
        super(XTipoUsuario.class);
    }
    
}
