package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.EmpleoAnterior;
import co.matisses.persistence.sap.entity.EmpleoAnterior_;
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
public class EmpleoAnteriorFacade extends AbstractFacade<EmpleoAnterior> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(EmpleoAnteriorFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleoAnteriorFacade() {
        super(EmpleoAnterior.class);
    }

    public long obtenerTotalDatos(Integer idEmpleado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<EmpleoAnterior> empleo = cq.from(EmpleoAnterior.class);

        cq.where(cb.equal(empleo.get("empleoAnteriorPK").get("empID"), idEmpleado));

        cq.select(cb.count(empleo));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return 0;
        }
    }

    public List<EmpleoAnterior> obtenerEmpleosAnteriores(int pageNum, int pageSize, Integer idEmpleado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EmpleoAnterior> cq = cb.createQuery(EmpleoAnterior.class);
        Root<EmpleoAnterior> empleo = cq.from(EmpleoAnterior.class);

        cq.where(cb.equal(empleo.get("empleoAnteriorPK").get("empID"), idEmpleado));

        cq.orderBy(cb.desc(empleo.get(EmpleoAnterior_.fromDate)));

        javax.persistence.Query query = em.createQuery(cq);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron datos de empleos anteriores para el empleado con id {0}", idEmpleado);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los datos de empleos anterior para el empleado. ", e);
        }
        return null;
    }
}
