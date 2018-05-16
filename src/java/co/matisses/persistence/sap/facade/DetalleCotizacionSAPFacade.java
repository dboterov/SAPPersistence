package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DetalleCotizacionSAP;
import co.matisses.persistence.sap.entity.DetalleCotizacionSAPPK_;
import co.matisses.persistence.sap.entity.DetalleCotizacionSAP_;
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
public class DetalleCotizacionSAPFacade extends AbstractFacade<DetalleCotizacionSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(DetalleCotizacionSAPFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleCotizacionSAPFacade() {
        super(DetalleCotizacionSAP.class);
    }

    public List<DetalleCotizacionSAP> obtenerDetalleCotizacion(Long docEntry) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DetalleCotizacionSAP> cq = cb.createQuery(DetalleCotizacionSAP.class);
        Root<DetalleCotizacionSAP> detalle = cq.from(DetalleCotizacionSAP.class);

        cq.where(cb.equal(detalle.get(DetalleCotizacionSAP_.detalleCotizacionSAPPK).get(DetalleCotizacionSAPPK_.docEntry), docEntry));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
