package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.CotizacionSAP;
import co.matisses.persistence.sap.entity.CotizacionSAP_;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dbotero
 */
@Stateless
public class CotizacionSAPFacade extends AbstractFacade<CotizacionSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(CotizacionSAPFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizacionSAPFacade() {
        super(CotizacionSAP.class);
    }

    public long obtenerTotalDatos(Integer asesor, String estado, String valor, Date fechaInicial, Date fechaFinal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<CotizacionSAP> cotizacion = cq.from(CotizacionSAP.class);

        if ((asesor != null && asesor != 0) || (estado != null && !estado.isEmpty()) || (valor != null && !valor.isEmpty())
                || (fechaInicial != null && fechaFinal != null) || (valor != null && !valor.isEmpty())) {
            Predicate conjunction = cb.conjunction();

            if (asesor != null && asesor != 0) {
                conjunction.getExpressions().add(cb.equal(cotizacion.get(CotizacionSAP_.slpCode), asesor));
            }
            if (estado != null && !estado.isEmpty()) {
                conjunction.getExpressions().add(cotizacion.get(CotizacionSAP_.docStatus).in(estado.charAt(0)));
            }
            if (fechaInicial != null && fechaFinal != null) {
                conjunction.getExpressions().add(cb.greaterThanOrEqualTo(cotizacion.get(CotizacionSAP_.docDate), fechaInicial));
                conjunction.getExpressions().add(cb.lessThanOrEqualTo(cotizacion.get(CotizacionSAP_.docDate), fechaFinal));
            }
            if (valor != null && !valor.isEmpty()) {
                conjunction.getExpressions().add(cb.like(cotizacion.get(CotizacionSAP_.cardCode), "%" + valor + "%"));
            }

            cq.where(conjunction);
        }

        cq.select(cb.count(cotizacion));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }

    public List<CotizacionSAP> obtenerCotizaciones(int pageNum, int pageSize, Integer asesor, String estado, String valor, Date fechaInicial, Date fechaFinal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CotizacionSAP> cq = cb.createQuery(CotizacionSAP.class);
        Root<CotizacionSAP> cotizacion = cq.from(CotizacionSAP.class);

        if ((estado != null && !estado.isEmpty()) || (asesor != null && asesor != 0) || (fechaInicial != null && fechaFinal != null)
                || (valor != null && !valor.isEmpty())) {
            Predicate conjunction = cb.conjunction();
            Predicate disjunction = cb.disjunction();

            if (estado != null && !estado.isEmpty()) {
                conjunction.getExpressions().add(cb.equal(cotizacion.get(CotizacionSAP_.docStatus), estado.charAt(0)));
            }
            if (asesor != null && asesor != 0) {
                conjunction.getExpressions().add(cb.equal(cotizacion.get(CotizacionSAP_.slpCode), asesor));
            }
            if (fechaInicial != null && fechaFinal != null) {
                conjunction.getExpressions().add(cb.greaterThanOrEqualTo(cotizacion.get(CotizacionSAP_.docDate), fechaInicial));
                conjunction.getExpressions().add(cb.lessThanOrEqualTo(cotizacion.get(CotizacionSAP_.docDate), fechaFinal));
            }
            if (valor != null && !valor.isEmpty()) {
                conjunction.getExpressions().add(cb.like(cotizacion.get(CotizacionSAP_.cardCode), "%" + valor + "%"));
                disjunction.getExpressions().add(cb.like(cotizacion.get(CotizacionSAP_.cardName), "%" + valor + "%"));
            }

            cq.where(conjunction);
        }

        cq.orderBy(cb.desc(cotizacion.get(CotizacionSAP_.docEntry)));

        javax.persistence.Query query = em.createQuery(cq);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        try {
            return query.getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Integer> obtenerAsesoresFacturas(String estado, String valor, Date fechaInicial, Date fechaFinal) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(INT, asesor.SlpCode) AS slpCode ");
        sb.append("FROM   OQUT cotizacion ");
        sb.append("INNER  JOIN OSLP asesor ON asesor.SlpCode = cotizacion.SlpCode ");

        if ((estado != null && !estado.isEmpty()) || (fechaInicial != null && fechaFinal != null) || (valor != null && !valor.isEmpty())) {
            StringBuilder sb2 = new StringBuilder();

            if (estado != null && !estado.isEmpty()) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("cotizacion.DocStatus = '");
                sb2.append(estado);
                sb2.append("' ");
            }
            if (fechaInicial != null && fechaFinal != null) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("cotizacion.DocDate >= '");
                sb2.append(new SimpleDateFormat("yyyy-dd-MM HH:mm:sss").format(fechaInicial));
                sb2.append("' AND ");
                sb2.append("cotizacion.DocDate <= '");
                sb2.append(new SimpleDateFormat("yyyy-dd-MM HH:mm:sss").format(fechaFinal));
                sb2.append("' ");
            }
            if (valor != null && !valor.isEmpty()) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("cotizacion.CardCode LIKE '");
                sb2.append(valor);
                sb2.append("' ");
            }

            sb.append(sb2.toString());
        }

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
