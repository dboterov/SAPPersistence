package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.TipoActividad;
import co.matisses.persistence.sap.entity.TipoActividad_;
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
public class TipoActividadFacade extends AbstractFacade<TipoActividad> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(TipoActividadFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoActividadFacade() {
        super(TipoActividad.class);
    }

    public List<TipoActividad> obtenerTiposActividad() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TipoActividad> cq = cb.createQuery(TipoActividad.class);
        Root<TipoActividad> tipo = cq.from(TipoActividad.class);

        cq.where(cb.equal(tipo.get(TipoActividad_.active), "Y".charAt(0)));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los tipos de actividad activos. ", e);
        }
        return null;
    }
}
