package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ServiceCallProblemSubtype;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class ServiceCallProblemSubtypeFacade extends AbstractFacade<ServiceCallProblemSubtype> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceCallProblemSubtypeFacade() {
        super(ServiceCallProblemSubtype.class);
    }
}
