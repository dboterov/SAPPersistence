package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.LugarReunion;
import co.matisses.persistence.sap.entity.LugarReunion_;
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
public class LugarReunionFacade extends AbstractFacade<LugarReunion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(LugarReunionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LugarReunionFacade() {
        super(LugarReunion.class);
    }

    public List<LugarReunion> obtenerTiposActividad() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(LugarReunion.class);
        Root tipo = cq.from(LugarReunion.class);

        cq.where(cb.equal(tipo.get(LugarReunion_.locked), "N".charAt(0)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las localidades para la actividad. ", e);
        }
        return null;
    }
}
