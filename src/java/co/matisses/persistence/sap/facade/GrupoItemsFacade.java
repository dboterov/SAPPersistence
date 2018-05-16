package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.GrupoItems;
import co.matisses.persistence.sap.entity.GrupoItems_;
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
public class GrupoItemsFacade extends AbstractFacade<GrupoItems> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(GrupoItemsFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoItemsFacade() {
        super(GrupoItems.class);
    }

    public GrupoItems obtenerGrupo(String code) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GrupoItems> cq = cb.createQuery(GrupoItems.class);
        Root<GrupoItems> grupo = cq.from(GrupoItems.class);

        cq.where(cb.equal(grupo.get(GrupoItems_.itmsGrpCod), code));

        try {
            return (GrupoItems) em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el grupo. ", e);
        }
        return null;
    }
}
