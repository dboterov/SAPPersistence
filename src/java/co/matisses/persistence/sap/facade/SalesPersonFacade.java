package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.SalesPerson;
import co.matisses.persistence.sap.entity.SalesPerson_;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
public class SalesPersonFacade extends AbstractFacade<SalesPerson> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(SalesPersonFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalesPersonFacade() {
        super(SalesPerson.class);
    }

    public String obtenerNombreAsesor(Integer slpCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SalesPerson> cq = cb.createQuery(SalesPerson.class);
        Root<SalesPerson> sales = cq.from(SalesPerson.class);

        cq.where(cb.equal(sales.get("slpCode"), slpCode));

        try {
            return em.createQuery(cq).getSingleResult().getSlpName();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public Short obtenerSlpCode(String slpName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SalesPerson> cq = cb.createQuery(SalesPerson.class);
        Root<SalesPerson> sales = cq.from(SalesPerson.class);

        cq.where(cb.equal(sales.get("slpName"), slpName));

        try {
            return em.createQuery(cq).getSingleResult().getSlpCode();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public SalesPerson cargarEmpleadoMercadoLibre() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SalesPerson> cq = cb.createQuery(SalesPerson.class);
        Root<SalesPerson> root = cq.from(SalesPerson.class);
        cq.where(cb.and(
                cb.equal(root.get(SalesPerson_.slpName), "MERCADOLIBRE"),
                cb.equal(root.get(SalesPerson_.active), 'Y')));
        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la informacion del vendedor MERCADOLIBRE", e);
            return null;
        }
    }
}
