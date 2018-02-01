package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruSeriesAlmacen;
import co.matisses.persistence.sap.entity.BaruSeriesAlmacen_;
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
public class BaruSeriesAlmacenFacade extends AbstractFacade<BaruSeriesAlmacen> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BaruSeriesAlmacenFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruSeriesAlmacenFacade() {
        super(BaruSeriesAlmacen.class);
    }

    public BaruSeriesAlmacen obtenerSerieFacturaAlmacen(String almacen) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruSeriesAlmacen> cq = cb.createQuery(BaruSeriesAlmacen.class);
        Root<BaruSeriesAlmacen> serie = cq.from(BaruSeriesAlmacen.class);

        cq.where(cb.equal(serie.get(BaruSeriesAlmacen_.uWhsCode), almacen),
                cb.equal(serie.get(BaruSeriesAlmacen_.uType), "13"),
                cb.equal(serie.get(BaruSeriesAlmacen_.uState), "Y"));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            log.log(Level.SEVERE, "No se encontro serie de numeracion para la sucursal {0}", almacen);
            return null;
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
