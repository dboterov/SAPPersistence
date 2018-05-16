package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruSubgrupo;
import co.matisses.persistence.sap.entity.BaruSubgrupo_;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    private static final Logger CONSOLE = Logger.getLogger(BaruSubgrupoFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruSubgrupoFacade() {
        super(BaruSubgrupo.class);
    }

    public List<BaruSubgrupo> findByGroup(String groupCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(BaruSubgrupo.class);
        Root<BaruSubgrupo> subgrupo = cq.from(BaruSubgrupo.class);

        cq.where(cb.equal(subgrupo.get("uCodGrupo"), groupCode));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public BaruSubgrupo obtenerSubgrupo(String code) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(BaruSubgrupo.class);
        Root<BaruSubgrupo> subgrupo = cq.from(BaruSubgrupo.class);

        cq.where(cb.equal(subgrupo.get(BaruSubgrupo_.code), code));

        try {
            return (BaruSubgrupo) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el subgrupo. ", e);
        }
        return null;
    }
}
