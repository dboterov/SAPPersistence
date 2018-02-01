package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Ausentismo;
import co.matisses.persistence.sap.entity.Ausentismo_;
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
public class AusentismoFacade extends AbstractFacade<Ausentismo> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(AusentismoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AusentismoFacade() {
        super(Ausentismo.class);
    }

    public long obtenerTotalDatos(Integer idEmpleado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Ausentismo> ausentismo = cq.from(Ausentismo.class);

        cq.where(cb.equal(ausentismo.get("ausentismoPK").get("empID"), idEmpleado));

        cq.select(cb.count(ausentismo));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return 0;
        }
    }

    public List<Ausentismo> obtenerAusentismosEmpleado(int pageNum, int pageSize, Integer idEmpleado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Ausentismo> cq = cb.createQuery(Ausentismo.class);
        Root<Ausentismo> ausentismo = cq.from(Ausentismo.class);

        cq.where(cb.equal(ausentismo.get("ausentismoPK").get("empID"), idEmpleado));

        cq.orderBy(cb.desc(ausentismo.get(Ausentismo_.fromDate)));

        javax.persistence.Query query = em.createQuery(cq);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron datos de ausentismos para el empleado con id {0}", idEmpleado);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los datos de ausentismo para el empleado. ", e);
        }
        return null;
    }
}
