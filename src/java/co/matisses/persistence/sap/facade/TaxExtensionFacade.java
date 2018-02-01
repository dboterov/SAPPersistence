package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.TaxExtension;
import co.matisses.persistence.sap.entity.TaxExtension_;
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
public class TaxExtensionFacade extends AbstractFacade<TaxExtension> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(TaxExtensionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaxExtensionFacade() {
        super(TaxExtension.class);
    }

    public TaxExtension obtenerTaxExtensionCotizacion(Integer docEntry) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TaxExtension> cq = cb.createQuery(TaxExtension.class);
        Root<TaxExtension> tax = cq.from(TaxExtension.class);

        cq.where(cb.equal(tax.get(TaxExtension_.docEntry), docEntry));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
