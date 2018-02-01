package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.PrecioVentaItem;
import java.math.BigDecimal;
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
 * @author dbotero
 */
@Stateless
public class PrecioVentaItemFacade extends AbstractFacade<PrecioVentaItem> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(PrecioVentaItemFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrecioVentaItemFacade() {
        super(PrecioVentaItem.class);
    }

    public Integer findByItemCodeTaxIncluded(String itemCode) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CONVERT(INT, PRICE) PRICE ");
        sb.append("FROM ITM1 ");
        sb.append("WHERE ITEMCODE = '");
        sb.append(itemCode);
        sb.append("' ");
        sb.append("AND PRICELIST = 2");
        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            return -1;
        }
    }

    public Double findByItemCode(String itemCode, boolean includeTax) {
        int listCode = 1;
        if (includeTax) {
            listCode = 2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT CONVERT(NUMERIC(18, 2), PRICE) PRICE ");
        sb.append("FROM ITM1 ");
        sb.append("WHERE ITEMCODE = '");
        sb.append(itemCode);
        sb.append("' ");
        sb.append("AND PRICELIST = ");
        sb.append(listCode);
        try {
            return ((BigDecimal) em.createNativeQuery(sb.toString()).getSingleResult()).doubleValue();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar el precio. ", e);
            return -1D;
        }
    }

    public List<PrecioVentaItem> obtenerListaPreciosActuales(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PrecioVentaItem> cq = cb.createQuery(PrecioVentaItem.class);
        Root<PrecioVentaItem> precio = cq.from(PrecioVentaItem.class);

        cq.where(cb.equal(precio.get("precioVentaItemPK").get("itemCode"),
                referencia), cb.gt(precio.<Integer>get("price"), 0));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}
