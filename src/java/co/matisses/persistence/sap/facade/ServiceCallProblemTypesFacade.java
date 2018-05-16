package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ServiceCallProblemTypes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class ServiceCallProblemTypesFacade extends AbstractFacade<ServiceCallProblemTypes> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceCallProblemTypesFacade() {
        super(ServiceCallProblemTypes.class);
    }
}
