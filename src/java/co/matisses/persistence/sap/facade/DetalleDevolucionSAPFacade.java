package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DetalleDevolucionSAP;
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
public class DetalleDevolucionSAPFacade extends AbstractFacade<DetalleDevolucionSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(DetalleDevolucionSAPFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleDevolucionSAPFacade() {
        super(DetalleDevolucionSAP.class);
    }

    public List<DetalleDevolucionSAP> obtenerDetalleDevolucion(Integer docEntry) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DetalleDevolucionSAP> cq = cb.createQuery(DetalleDevolucionSAP.class);
        Root<DetalleDevolucionSAP> detalle = cq.from(DetalleDevolucionSAP.class);

        cq.where(cb.equal(detalle.get("detalleDevolucionSAPPK").get("docEntry"), docEntry));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}
