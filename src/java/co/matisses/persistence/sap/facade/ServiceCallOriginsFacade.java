package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ServiceCallOrigins;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class ServiceCallOriginsFacade extends AbstractFacade<ServiceCallOrigins> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceCallOriginsFacade() {
        super(ServiceCallOrigins.class);
    }
}
