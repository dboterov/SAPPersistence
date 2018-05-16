package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ServiceCallSolutionStatuses;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class ServiceCallSolutionStatusesFacade extends AbstractFacade<ServiceCallSolutionStatuses> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceCallSolutionStatusesFacade() {
        super(ServiceCallSolutionStatuses.class);
    }
}
