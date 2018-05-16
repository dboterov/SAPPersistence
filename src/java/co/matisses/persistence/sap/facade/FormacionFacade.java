package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Formacion;
import co.matisses.persistence.sap.entity.Formacion_;
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
public class FormacionFacade extends AbstractFacade<Formacion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(FormacionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormacionFacade() {
        super(Formacion.class);
    }

    public long obtenerTotalDatos(Integer idEmpleado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Formacion> formacion = cq.from(Formacion.class);

        cq.where(cb.equal(formacion.get("formacionPK").get("empID"), idEmpleado));

        cq.select(cb.count(formacion));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el total de las formaciones del empleado. ", e);
        }
        return 0;
    }

    public List<Formacion> obtenerFormacionEmpleado(int pageNum, int pageSize, Integer idEmpleado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Formacion> cq = cb.createQuery(Formacion.class);
        Root<Formacion> formacion = cq.from(Formacion.class);

        cq.where(cb.equal(formacion.get("formacionPK").get("empID"), idEmpleado));

        cq.orderBy(cb.desc(formacion.get(Formacion_.fromDate)));

        javax.persistence.Query query = em.createQuery(cq);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las formaciones del empleado. ", e);
        }
        return null;
    }
}
