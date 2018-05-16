package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.SocioDeNegocios;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author dbotero
 */
@Stateless
public class SocioDeNegociosFacade extends AbstractFacade<SocioDeNegocios> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private final static Logger log = Logger.getLogger(SocioDeNegociosFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocioDeNegociosFacade() {
        super(SocioDeNegocios.class);
    }

    public SocioDeNegocios findByCardCode(String cardCode) {
        if (!cardCode.toUpperCase().endsWith("CL")) {
            cardCode = cardCode.concat("CL");
        }
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SocioDeNegocios> cq = cb.createQuery(SocioDeNegocios.class);
            Root<SocioDeNegocios> cliente = cq.from(SocioDeNegocios.class);
            cq.where(cb.equal(cliente.get("cardCode"), cardCode));

            SocioDeNegocios tmp = em.createQuery(cq).getSingleResult();
            if (tmp.getBalance().longValue() > 0) {
                tmp.setBalance(BigDecimal.ZERO);
            }
            return tmp;
        } catch (Exception e) {
            return null;
        }
    }

    public SocioDeNegocios findByEmail(String email) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SocioDeNegocios> cq = cb.createQuery(SocioDeNegocios.class);
            Root<SocioDeNegocios> cliente = cq.from(SocioDeNegocios.class);
            Expression<String> upper = cb.upper(cliente.<String>get("email"));
            cq.where(cb.and(
                    cb.equal(upper, email.toUpperCase()),
                    cb.equal(cliente.get("cardType"), 'C')));

            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            return new SocioDeNegocios();
        }
    }

    public Float obtenerSaldoFavor(String nit) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(SUM(debit) - SUM(credit) AS INT) * -1 AS saldo ");
        sb.append("FROM   JDT1 ");
        sb.append("WHERE  Account IN (13050501, 28050501, 28050502) ");
        sb.append("AND    ShortName = '");
        sb.append(nit);
        sb.append("' ");

        try {
            Integer saldo = (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
            log.log(Level.INFO, "El cliente {0} tiene {1} en saldo a favor", new Object[]{nit, saldo});
            return saldo.floatValue();
        } catch (Exception e) {
            log.log(Level.SEVERE, "No se pudo consultar el saldo a favor del cliente [" + nit + "]", e);
            return 0F;
        }
    }

    public String obtenerDireccionEntrgaCliente(String cardCode) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT TOP 1 CONVERT(VARCHAR(MAX), REPLACE(ISNULL(direccion.Street, '') + ' - ' + ISNULL(direccion.City, ''), '  ', '')) AS direccionEntrega ");
        sb.append("FROM   CRD1 direccion ");
        sb.append("INNER  JOIN OCRD cliente ON cliente.CardCode = direccion.CardCode ");
        sb.append("WHERE  cliente.CardCode = '");
        sb.append(cardCode);
        sb.append("' ");
        sb.append("AND    cliente.ShipToDef = direccion.Address ");
        sb.append("AND    direccion.AdresType = 'S' ");

        try {
            return (String) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar la direccion de entrega del cliente", e);
            return "";
        }
    }

    public Long obtenerTotalCompras(String cardCode) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(SUM(ISNULL(f.doctotal,0)) - SUM(ISNULL(d.doctotal,0)) AS NUMERIC(18,0)) AS totalVendido ");
        sb.append("FROM   OINV f ");
        sb.append("FULL   JOIN ORIN d ON d.numatcard = f.docnum ");
        sb.append("WHERE  f.cardcode = '");
        sb.append(cardCode);
        sb.append("' ");

        try {
            return ((BigDecimal) em.createNativeQuery(sb.toString()).getSingleResult()).longValue();
        } catch (NoResultException e) {
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al obtener el valor de compras. ", e);
        }
        return 0L;
    }
}