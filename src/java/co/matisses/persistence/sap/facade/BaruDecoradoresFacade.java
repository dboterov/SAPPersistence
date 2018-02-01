package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruDecoradores;
import co.matisses.persistence.sap.entity.BaruDecoradores_;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class BaruDecoradoresFacade extends AbstractFacade<BaruDecoradores> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BaruDecoradoresFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruDecoradoresFacade() {
        super(BaruDecoradores.class);
    }

    public List<BaruDecoradores> obtenerDecoradoresActivos() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruDecoradores> cq = cb.createQuery(BaruDecoradores.class);
        Root<BaruDecoradores> decoradores = cq.from(BaruDecoradores.class);

        cq.where(cb.equal(decoradores.get(BaruDecoradores_.uEstado), "A"));
        cq.orderBy(cb.asc(decoradores.get(BaruDecoradores_.name)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
