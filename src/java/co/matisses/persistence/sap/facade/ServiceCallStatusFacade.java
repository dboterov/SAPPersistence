package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ServiceCallStatus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class ServiceCallStatusFacade extends AbstractFacade<ServiceCallStatus> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceCallStatusFacade() {
        super(ServiceCallStatus.class);
    }
}
