package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruCombinacionPrincipal;
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
public class BaruCombinacionPrincipalFacade extends AbstractFacade<BaruCombinacionPrincipal> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BaruCombinacionPrincipalFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruCombinacionPrincipalFacade() {
        super(BaruCombinacionPrincipal.class);
    }

    public List<BaruCombinacionPrincipal> obtenerCombinacionesModeloAnterior(String modelo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruCombinacionPrincipal> cq = cb.createQuery(BaruCombinacionPrincipal.class);
        Root<BaruCombinacionPrincipal> combinacion = cq.from(BaruCombinacionPrincipal.class);

        cq.where(cb.equal(combinacion.get("uModelo"), modelo));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}
