package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.BaruDetalleDocumentoPendiente;
import co.matisses.persistence.sap.entity.BaruDetalleDocumentoPendiente_;
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
public class BaruDetalleDocumentoPendienteFacade extends AbstractFacade<BaruDetalleDocumentoPendiente> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(BaruDetalleDocumentoPendienteFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BaruDetalleDocumentoPendienteFacade() {
        super(BaruDetalleDocumentoPendiente.class);
    }

    public List<BaruDetalleDocumentoPendiente> obtenerDetalleDocumento(String code) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BaruDetalleDocumentoPendiente> cq = cb.createQuery(BaruDetalleDocumentoPendiente.class);
        Root<BaruDetalleDocumentoPendiente> detalle = cq.from(BaruDetalleDocumentoPendiente.class);

        cq.where(cb.equal(detalle.get(BaruDetalleDocumentoPendiente_.uCodeDocPdte), code));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            console.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
