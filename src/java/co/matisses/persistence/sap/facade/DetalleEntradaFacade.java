package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DetalleEntrada;
import co.matisses.persistence.sap.entity.DetalleEntrada_;
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
public class DetalleEntradaFacade extends AbstractFacade<DetalleEntrada> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(DetalleEntradaFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleEntradaFacade() {
        super(DetalleEntrada.class);
    }

    public List<DetalleEntrada> obtenerDetalleEntrada(Integer docEntry) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DetalleEntrada> cq = cb.createQuery(DetalleEntrada.class);
        Root<DetalleEntrada> detalle = cq.from(DetalleEntrada.class);

        cq.where(cb.equal(detalle.get(DetalleEntrada_.docEntry), docEntry));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            console.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
