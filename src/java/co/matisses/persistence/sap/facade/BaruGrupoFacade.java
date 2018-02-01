package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruGrupo;
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
public class BaruGrupoFacade extends AbstractFacade<BaruGrupo> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruGrupoFacade() {
        super(BaruGrupo.class);
    }

    public List<BaruGrupo> findByDepartment(String departmentCode) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(BaruGrupo.class);
            Root<BaruGrupo> grupo = cq.from(BaruGrupo.class);
            cq.where(cb.equal(grupo.get("uCodDep"), departmentCode));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
