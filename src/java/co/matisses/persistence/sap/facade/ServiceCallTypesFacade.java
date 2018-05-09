package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ServiceCallTypes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ygil
 */
@Stateless
public class ServiceCallTypesFacade extends AbstractFacade<ServiceCallTypes> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceCallTypesFacade() {
        super(ServiceCallTypes.class);
    }
}
