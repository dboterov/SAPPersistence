package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Serie;
import co.matisses.persistence.sap.entity.Serie_;
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
public class SerieFacade extends AbstractFacade<Serie> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(SerieFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SerieFacade() {
        super(Serie.class);
    }

    public Serie obtenerSerieNotaCredito() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Serie> cq = cb.createQuery(Serie.class);
        Root<Serie> serie = cq.from(Serie.class);

        cq.where(cb.equal(serie.get(Serie_.seriesName), "NC"), cb.equal(serie.get(Serie_.locked), "N".charAt(0)));

        try {
            return em.createQuery(cq).setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la serie de numeracion para notas credito. ", e);
        }
        return null;
    }
}
