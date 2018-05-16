package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.TipoRetencionCliente;
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
public class TipoRetencionClienteFacade extends AbstractFacade<TipoRetencionCliente> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoRetencionClienteFacade() {
        super(TipoRetencionCliente.class);
    }

    public List<TipoRetencionCliente> findByCardCode(String cardCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TipoRetencionCliente> cq = cb.createQuery(TipoRetencionCliente.class);
        Root<TipoRetencionCliente> root = cq.from(TipoRetencionCliente.class);
        cq.where(cb.equal(root.get("tipoRetencionClientePK").get("cardCode"), cardCode));
        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            return new ArrayList();
        }
    }
}
