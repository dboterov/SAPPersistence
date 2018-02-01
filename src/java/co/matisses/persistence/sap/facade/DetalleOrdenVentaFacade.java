package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DetalleOrdenVenta;
import co.matisses.persistence.sap.entity.DetalleOrdenVenta_;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class DetalleOrdenVentaFacade extends AbstractFacade<DetalleOrdenVenta> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(DetalleOrdenVentaFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleOrdenVentaFacade() {
        super(DetalleOrdenVenta.class);
    }

    public List<DetalleOrdenVenta> obtenerDetalleOrdenVenta(Integer docEntry) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DetalleOrdenVenta> cq = cb.createQuery(DetalleOrdenVenta.class);
        Root<DetalleOrdenVenta> detalle = cq.from(DetalleOrdenVenta.class);

        cq.where(cb.equal(detalle.get(DetalleOrdenVenta_.docEntry), docEntry));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            console.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
