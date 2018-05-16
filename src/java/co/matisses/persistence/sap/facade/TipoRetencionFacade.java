package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.TipoRetencion;
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
public class TipoRetencionFacade extends AbstractFacade<TipoRetencion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRetencionFacade() {
        super(TipoRetencion.class);
    }

    public List<TipoRetencion> getSalesApplicable() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TipoRetencion> cq = cb.createQuery(TipoRetencion.class);
        Root<TipoRetencion> root = cq.from(TipoRetencion.class);
        cq.where(cb.and(cb.equal(root.get("category"), "P"),
                cb.equal(root.get("inactive"), "N")));
        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
