package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ServiceCallHistory;
import co.matisses.persistence.sap.entity.ServiceCallHistory_;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class ServiceCallHistoryFacade extends AbstractFacade<ServiceCallHistory> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(ServiceCallHistoryFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceCallHistoryFacade() {
        super(ServiceCallHistory.class);
    }

    public List<ServiceCallHistory> obtenerHistorico(Integer callID) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(ServiceCallHistory.class);
        Root historico = cq.from(ServiceCallHistory.class);

        cq.where(cb.equal(historico.get(ServiceCallHistory_.callID), callID));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el historico de la llamada de servicio. ", e);
        }
        return null;
    }
}
