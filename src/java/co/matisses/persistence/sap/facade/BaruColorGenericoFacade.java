package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruColorGenerico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class BaruColorGenericoFacade extends AbstractFacade<BaruColorGenerico> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruColorGenericoFacade() {
        super(BaruColorGenerico.class);
    }
}
