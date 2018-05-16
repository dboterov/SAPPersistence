package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruEstadoFactura;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author dbotero
 */
@Stateless
public class BaruEstadoFacturaFacade extends AbstractFacade<BaruEstadoFactura> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruEstadoFacturaFacade() {
        super(BaruEstadoFactura.class);
    }

    public List<BaruEstadoFactura> findByOrderNumber(String order) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<BaruEstadoFactura> cq = cb.createQuery(BaruEstadoFactura.class);
            Root<BaruEstadoFactura> root = cq.from(BaruEstadoFactura.class);
            cq.where(cb.equal(root.get("U_nroFactura"), order));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
