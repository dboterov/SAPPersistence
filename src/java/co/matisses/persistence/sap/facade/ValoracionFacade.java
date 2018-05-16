package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Valoracion;
import co.matisses.persistence.sap.entity.Valoracion_;
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
public class ValoracionFacade extends AbstractFacade<Valoracion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(ValoracionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ValoracionFacade() {
        super(Valoracion.class);
    }

    public long obtenerTotalDatos(Integer idEmpleado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Valoracion> valoracion = cq.from(Valoracion.class);

        cq.where(cb.equal(valoracion.get("valoracionPK").get("empID"), idEmpleado));

        cq.select(cb.count(valoracion));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return 0;
        }
    }

    public List<Valoracion> obtenerValoracionesEmpleado(int pageNum, int pageSize, Integer idEmpleado) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Valoracion> cq = cb.createQuery(Valoracion.class);
        Root<Valoracion> valoracion = cq.from(Valoracion.class);

        cq.where(cb.equal(valoracion.get("valoracionPK").get("empID"), idEmpleado));

        cq.orderBy(cb.desc(valoracion.get(Valoracion_.date)));

        javax.persistence.Query query = em.createQuery(cq);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron datos de valoraciones para el empleado con id {0}", idEmpleado);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los datos de valoraciones para el empleado. ", e);
        }
        return null;
    }
}
