package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.TemaActividad;
import co.matisses.persistence.sap.entity.TemaActividad_;
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
public class TemaActividadFacade extends AbstractFacade<TemaActividad> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(TemaActividadFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TemaActividadFacade() {
        super(TemaActividad.class);
    }

    public List<TemaActividad> obtenerTemasActividad() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(TemaActividad.class);
        Root tema = cq.from(TemaActividad.class);

        cq.where(cb.equal(tema.get(TemaActividad_.active), "Y".charAt(0)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los temas de actividad activos. ", e);
        }
        return null;
    }
}
