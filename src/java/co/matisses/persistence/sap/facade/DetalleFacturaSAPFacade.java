package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DetalleFacturaSAP;
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
 * @author jguisao
 */
@Stateless
public class DetalleFacturaSAPFacade extends AbstractFacade<DetalleFacturaSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(DetalleFacturaSAPFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleFacturaSAPFacade() {
        super(DetalleFacturaSAP.class);
    }

    public List<DetalleFacturaSAP> obtenerDetalleFactura(Double docEntry) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DetalleFacturaSAP> cq = cb.createQuery(DetalleFacturaSAP.class);
        Root<DetalleFacturaSAP> root = cq.from(DetalleFacturaSAP.class);

        cq.where(cb.equal(root.get("detalleFacturaSAPPK").get("docEntry"), docEntry));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
