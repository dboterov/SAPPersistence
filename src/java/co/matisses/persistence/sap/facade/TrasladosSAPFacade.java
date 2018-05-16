package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.TrasladosSAP;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class TrasladosSAPFacade extends AbstractFacade<TrasladosSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(TrasladosSAPFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrasladosSAPFacade() {
        super(TrasladosSAP.class);
    }
}
