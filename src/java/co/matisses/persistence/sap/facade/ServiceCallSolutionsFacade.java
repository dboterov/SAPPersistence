package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.ServiceCallSolutions;
import co.matisses.persistence.sap.entity.ServiceCallSolutions_;
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
public class ServiceCallSolutionsFacade extends AbstractFacade<ServiceCallSolutions> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(ServiceCallSolutionsFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceCallSolutionsFacade() {
        super(ServiceCallSolutions.class);
    }

    public ServiceCallSolutions obtenerSolucion(Integer sltCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(ServiceCallSolutions.class);
        Root solucion = cq.from(ServiceCallSolutions.class);

        cq.where(cb.equal(solucion.get(ServiceCallSolutions_.sltCode), sltCode));

        try {
            return (ServiceCallSolutions) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la solucion. ", e);
        }
        return null;
    }

    public List<Object[]> obtenerSolucionesProcesar(List<Long> idsLlamadas) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(INT, t2.SltCode) AS SltCode, CONVERT(INT, t1.srvcCallID) AS srvcCallID ");
        sb.append("FROM   ASC1 t1 ");
        sb.append("INNER  JOIN OSLT t2 ON t2.SltCode = t1.solutionID ");
        if (idsLlamadas != null && !idsLlamadas.isEmpty()) {
            sb.append("WHERE  t2.SltCode NOT IN (");
            idsLlamadas.stream().forEach((id) -> {
                sb.append(id).append(",");
            });
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") ");
        }

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener las soluciones a procesar. ", e);
        }
        return null;
    }
}
