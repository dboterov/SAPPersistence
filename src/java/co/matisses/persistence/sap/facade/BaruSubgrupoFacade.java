package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruSubgrupo;
import java.util.List;
import javax.ejb.LocalBean;
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
@LocalBean
public class BaruSubgrupoFacade extends AbstractFacade<BaruSubgrupo> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruSubgrupoFacade() {
        super(BaruSubgrupo.class);
    }

    public List<BaruSubgrupo> findByGroup(String groupCode) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(BaruSubgrupo.class);
            Root<BaruSubgrupo> subgrupo = cq.from(BaruSubgrupo.class);
            cq.where(cb.equal(subgrupo.get("uCodGrupo"), groupCode));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
