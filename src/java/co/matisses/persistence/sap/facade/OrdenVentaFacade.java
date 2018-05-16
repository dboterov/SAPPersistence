package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.OrdenVenta;
import co.matisses.persistence.sap.entity.OrdenVenta_;
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
public class OrdenVentaFacade extends AbstractFacade<OrdenVenta> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(OrdenVentaFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdenVentaFacade() {
        super(OrdenVenta.class);
    }

    public List<OrdenVenta> obtenerOrdenesVentaFactura(Integer docNum) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrdenVenta> cq = cb.createQuery(OrdenVenta.class);
        Root<OrdenVenta> orden = cq.from(OrdenVenta.class);

        cq.where(cb.equal(orden.get(OrdenVenta_.numAtCard), docNum.toString()));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public OrdenVenta obtenerOrdenVenta(String nroFactura) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrdenVenta> cq = cb.createQuery(OrdenVenta.class);
        Root<OrdenVenta> orden = cq.from(OrdenVenta.class);

        cq.where(cb.equal(orden.get(OrdenVenta_.numAtCard), nroFactura),
                cb.equal(orden.get(OrdenVenta_.docStatus), "O"));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontro orden de venta para la factura {0}.", nroFactura);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la orden de venta. ", e);
        }
        return null;
    }
}
