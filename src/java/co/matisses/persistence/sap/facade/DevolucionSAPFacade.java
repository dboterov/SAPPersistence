package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.DevolucionSAP;
import co.matisses.persistence.sap.entity.DevolucionSAP_;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class DevolucionSAPFacade extends AbstractFacade<DevolucionSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(DevolucionSAPFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DevolucionSAPFacade() {
        super(DevolucionSAP.class);
    }

    public List<DevolucionSAP> obtenerDevolucionesFactura(Integer docNum) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DevolucionSAP> cq = cb.createQuery(DevolucionSAP.class);
        Root<DevolucionSAP> devolucion = cq.from(DevolucionSAP.class);

        cq.where(cb.equal(devolucion.get("numAtCard"), docNum));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public DevolucionSAP obtenerDevolucion(Integer docNum) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DevolucionSAP> cq = cb.createQuery(DevolucionSAP.class);
        Root<DevolucionSAP> devolucion = cq.from(DevolucionSAP.class);

        cq.where(cb.equal(devolucion.get(DevolucionSAP_.docNum), docNum.toString()));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public Integer validarFacturar(Integer nroFactura) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT COUNT(1) regs ");
        sb.append("FROM   ORIN ");
        sb.append("WHERE  U_TipoNota = 'A' ");
        sb.append("AND    NumAtCard = ");
        sb.append(nroFactura);

        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron datos de notas credito para la FV {0}", nroFactura);
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los datos de notas credito. ", e);
        }
        return null;
    }

    public List<Integer> obtenerDevolucionesNoAplicablesComision(String asesor, Date fechaInicioDV, Date fechaFinDV) {
        /*Se obtiene la fecha anterior a la fecha a comisionar*/
        Date fechaInicioFV = new Date();
        Date fechaFinFV = new Date();

        Calendar calendarInicioFV = Calendar.getInstance();
        calendarInicioFV.setTime(fechaInicioDV);

        calendarInicioFV.add(Calendar.MONTH, -1);
        fechaInicioFV = calendarInicioFV.getTime();

        Calendar calendarFinFV = Calendar.getInstance();
        calendarFinFV.setTime(fechaFinDV);

        calendarFinFV.add(Calendar.MONTH, -1);
        fechaFinFV = calendarFinFV.getTime();

        Calendar calendarFinDV = Calendar.getInstance();
        calendarFinDV.setTime(fechaFinDV);

        calendarFinDV.set(Calendar.DATE, 8);
        fechaFinDV = calendarFinDV.getTime();

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(INT, devolucion.DocNum) AS DocNum ");
        sb.append("FROM   RIN1 detalle ");
        sb.append("INNER  JOIN ORIN devolucion ON devolucion.DocEntry = detalle.DocEntry ");
        sb.append("WHERE  (U_Vendedor1 = '");
        sb.append(asesor);
        sb.append("' ");
        sb.append("	   OR U_Vendedor2 = '");
        sb.append(asesor);
        sb.append("' ");
        sb.append("	   OR U_Vendedor3 = '");
        sb.append(asesor);
        sb.append("' ");
        sb.append("	   OR U_Vendedor4 = '");
        sb.append(asesor);
        sb.append("' ");
        sb.append("	   OR U_Vendedor5 = '");
        sb.append(asesor);
        sb.append("') ");
        sb.append("AND    devolucion.DocStatus = 'C' ");
        sb.append("AND    devolucion.CANCELED = 'N' ");
        if (fechaInicioDV != null && fechaFinDV != null) {
            sb.append("AND    devolucion.DocDate BETWEEN '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaInicioDV));
            sb.append("' AND '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaFinDV));
            sb.append("' ");
        }
        if (fechaInicioFV != null && fechaFinFV != null) {
            sb.append("AND    devolucion.NumAtCard IN (SELECT fac.DocNum FROM OINV fac WHERE fac.DocDate BETWEEN '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaInicioFV));
            sb.append("' AND '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaFinFV));
            sb.append("') ");
        }

        CONSOLE.log(Level.INFO, "{0}", sb.toString());

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las devoluciones que no aplican para comision. ", e);
        }

        return null;
    }

    public long obtenerTotalDatos(Integer asesor, String estado, String valor, Date fechaInicial, Date fechaFinal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DevolucionSAP> devolucion = cq.from(DevolucionSAP.class);

        if ((estado != null && !estado.isEmpty()) || (asesor != null && asesor != 0) || (fechaInicial != null && fechaFinal != null)
                || (valor != null && !valor.isEmpty())) {
            Predicate conjunction = cb.conjunction();
            Predicate disjunction = cb.disjunction();

            if (estado != null && !estado.isEmpty()) {
                conjunction.getExpressions().add(cb.equal(devolucion.get(DevolucionSAP_.uTipoNota), estado.charAt(0)));
            }
            if (asesor != null && asesor != 0) {
                conjunction.getExpressions().add(cb.equal(devolucion.get(DevolucionSAP_.slpCode), asesor));
            }
            if (fechaInicial != null && fechaFinal != null) {
                conjunction.getExpressions().add(cb.greaterThanOrEqualTo(devolucion.get(DevolucionSAP_.docDate), fechaInicial));
                conjunction.getExpressions().add(cb.lessThanOrEqualTo(devolucion.get(DevolucionSAP_.docDate), fechaFinal));
            }
            if (valor != null && !valor.isEmpty()) {
                conjunction.getExpressions().add(cb.like(devolucion.get(DevolucionSAP_.cardCode), "%" + valor + "%"));
                disjunction.getExpressions().add(cb.like(devolucion.get(DevolucionSAP_.cardName), "%" + valor + "%"));
            }

            cq.where(conjunction);
        }

        cq.select(cb.count(devolucion));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return 0;
        }
    }

    public List<DevolucionSAP> obtenerDevoluciones(int pageNum, int pageSize, Integer asesor, String estado, String valor, Date fechaInicial, Date fechaFinal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DevolucionSAP> cq = cb.createQuery(DevolucionSAP.class);
        Root<DevolucionSAP> devolucion = cq.from(DevolucionSAP.class);

        if ((estado != null && !estado.isEmpty()) || (asesor != null && asesor != 0) || (fechaInicial != null && fechaFinal != null)
                || (valor != null && !valor.isEmpty())) {
            Predicate conjunction = cb.conjunction();
            Predicate disjunction = cb.disjunction();

            if (estado != null && !estado.isEmpty()) {
                conjunction.getExpressions().add(cb.equal(devolucion.get(DevolucionSAP_.uTipoNota), estado.charAt(0)));
            }
            if (asesor != null && asesor != 0) {
                conjunction.getExpressions().add(cb.equal(devolucion.get(DevolucionSAP_.slpCode), asesor));
            }
            if (fechaInicial != null && fechaFinal != null) {
                conjunction.getExpressions().add(cb.greaterThanOrEqualTo(devolucion.get(DevolucionSAP_.docDate), fechaInicial));
                conjunction.getExpressions().add(cb.lessThanOrEqualTo(devolucion.get(DevolucionSAP_.docDate), fechaFinal));
            }
            if (valor != null && !valor.isEmpty()) {
                conjunction.getExpressions().add(cb.like(devolucion.get(DevolucionSAP_.cardCode), "%" + valor + "%"));
                disjunction.getExpressions().add(cb.like(devolucion.get(DevolucionSAP_.cardName), "%" + valor + "%"));
            }

            cq.where(conjunction);
        }

        cq.orderBy(cb.desc(devolucion.get(DevolucionSAP_.docEntry)));

        javax.persistence.Query query = em.createQuery(cq);
        query.setFirstResult((pageNum - 1) * pageSize);
        query.setMaxResults(pageSize);

        try {
            return query.getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Integer> obtenerAsesoresDevolucion(String estado, String valor, Date fechaInicial, Date fechaFinal) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(INT, asesor.SlpCode) AS slpCode ");
        sb.append("FROM   ORIN devolucion ");
        sb.append("INNER  JOIN OSLP asesor ON asesor.SlpCode = devolucion.SlpCode ");

        if ((estado != null && !estado.isEmpty()) || (fechaInicial != null && fechaFinal != null) || (valor != null && !valor.isEmpty())) {
            StringBuilder sb2 = new StringBuilder();

            if (estado != null && !estado.isEmpty()) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("devolucion.U_TipoNota = '");
                sb2.append(estado);
                sb2.append("' ");
            }
            if (fechaInicial != null && fechaFinal != null) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("devolucion.DocDate >= '");
                sb2.append(new SimpleDateFormat("yyyy-dd-MM HH:mm:sss").format(fechaInicial));
                sb2.append("' AND ");
                sb2.append("devolucion.DocDate <= '");
                sb2.append(new SimpleDateFormat("yyyy-dd-MM HH:mm:sss").format(fechaFinal));
                sb2.append("' ");
            }
            if (valor != null && !valor.isEmpty()) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("devolucion.CardCode LIKE '");
                sb2.append(valor);
                sb2.append("' ");
            }

            sb.append(sb2.toString());
        }

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }
    
    public DevolucionSAP obtenerDevolucionFactura(Integer numAtCard) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DevolucionSAP> cq = cb.createQuery(DevolucionSAP.class);
        Root<DevolucionSAP> devo = cq.from(DevolucionSAP.class);

        cq.where(cb.equal(devo.get("numAtCard"), numAtCard));

        try {
            return em.createQuery(cq).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
