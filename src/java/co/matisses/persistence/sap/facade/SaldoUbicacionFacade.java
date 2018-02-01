package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.SaldoUbicacion;
import co.matisses.persistence.sap.entity.SaldoUbicacion_;
import co.matisses.persistence.sap.entity.UbicacionSAP;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author dbotero
 */
@Stateless
public class SaldoUbicacionFacade extends AbstractFacade<SaldoUbicacion> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(SaldoUbicacionFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SaldoUbicacionFacade() {
        super(SaldoUbicacion.class);
    }

    public List<SaldoUbicacion> findByItemCodeAndWhsCode(boolean incluirUbicacionesEspeciales, String itemCode, String whsCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoUbicacion> cq = cb.createQuery(SaldoUbicacion.class);
        Root<SaldoUbicacion> root = cq.from(SaldoUbicacion.class);
        Join<UbicacionSAP, SaldoUbicacion> detalle = root.join("ubicacion", JoinType.INNER);
        Predicate conjuncion = cb.conjunction();
        conjuncion.getExpressions().add(cb.equal(root.get("itemCode"), itemCode));
        conjuncion.getExpressions().add(cb.gt(root.<Integer>get("onHandQty"), 0));
        conjuncion.getExpressions().add(cb.equal(root.get("whsCode"), whsCode));
        if (!incluirUbicacionesEspeciales) {
            conjuncion.getExpressions().add(cb.equal(detalle.get("attr1Val"), "NO"));
        }
        cq.where(cb.and(conjuncion));
        cq.orderBy(cb.desc(root.get("onHandQty")));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public SaldoUbicacion obtenerSaldoXUbicacion(String referencia, String binCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoUbicacion> cq = cb.createQuery(SaldoUbicacion.class);
        Root<SaldoUbicacion> root = cq.from(SaldoUbicacion.class);

        cq.where(cb.equal(root.get("itemCode"), referencia), cb.equal(root.get("ubicacion").get("binCode"), binCode));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object[]> buscarPorReferencia(String referencia) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(saldoAlmacen.WhsCode as varchar(10)) almacen ");
        sb.append(", cast(saldoAlmacen.OnHand as int) saldoAlmacen ");
        sb.append(", cast(ubicacion.AbsEntry as int) codUbicacion ");
        sb.append(", cast(ubicacion.BinCode as varchar(30)) ubicacion ");
        sb.append(", cast(saldoUbicacion.OnHandQty as int) saldoUbicacion ");
        sb.append(", cast(case when almacen.U_Prioridad is null then 100 else almacen.U_Prioridad end as int) prioridad ");
        sb.append(", cast(almacen.U_Ciudad as varchar(5)) codCiudad ");
        sb.append("from OITW saldoAlmacen ");
        sb.append("inner join OWHS almacen on almacen.whscode = saldoAlmacen.WhsCode ");
        sb.append("inner join OIBQ saldoUbicacion on saldoUbicacion.WhsCode = saldoAlmacen.WhsCode and saldoUbicacion.OnHandQty > 0 ");
        sb.append("and saldoUbicacion.ItemCode = saldoAlmacen.ItemCode ");
        sb.append("inner join OBIN ubicacion on ubicacion.AbsEntry = saldoUbicacion.BinAbs ");
        sb.append("where saldoAlmacen.ItemCode = '");
        sb.append(referencia);
        sb.append("' and saldoAlmacen.OnHand > 0 ");
        sb.append("and saldoAlmacen.WhsCode like '0%' ");
        sb.append("and ubicacion.Attr1Val = 'No' ");
        sb.append("order by 7, 6, saldoAlmacen.OnHand desc, saldoUbicacion.OnHandQty desc ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "No fue posible consultar el saldo en ubicaciones para el item. ", e);
            return null;
        }
    }

    public List<SaldoUbicacion> buscarXReferencia(String referencia, boolean incluirNoVenta) {
        if (!incluirNoVenta) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SaldoUbicacion> cq = cb.createQuery(SaldoUbicacion.class);
            Root<SaldoUbicacion> root = cq.from(SaldoUbicacion.class);
            cq.where(cb.equal(root.get("itemCode"), referencia), cb.gt(root.<Integer>get("onHandQty"), 0));
            cq.orderBy(cb.asc(root.get("whsCode")));
            try {
                return em.createQuery(cq).getResultList();
            } catch (Exception e) {
                log.log(Level.SEVERE, e.getMessage());
                return null;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT CONVERT(INT, saldoUbicacion.AbsEntry) AS AbsEntry, ");
            sb.append("       CONVERT(VARCHAR(20), ISNULL(saldoUbicacion.ItemCode, saldoAlmacen.ItemCode)) AS ItemCode, ");
            sb.append("       CONVERT(INT, saldoUbicacion.BinAbs) AS BinAbs, ");
            sb.append("       CONVERT(NUMERIC(18, 2), ISNULL(saldoUbicacion.OnHandQty, saldoAlmacen.OnHand)) AS OnHand, ");
            sb.append("       CONVERT(VARCHAR(MAX), ISNULL(saldoUbicacion.WhsCode, saldoAlmacen.WhsCode)) AS WhsCode, ");
            sb.append("       CONVERT(CHAR(1), saldoUbicacion.Freezed) AS Freezed, ");
            sb.append("       CONVERT(INT, saldoUbicacion.FreezeDoc) AS FreezeDoc ");
            sb.append("FROM   (SELECT * ");
            sb.append("        FROM   OIBQ ");
            sb.append("        WHERE  OnHandQty > 0) saldoUbicacion ");
            sb.append("RIGHT  JOIN OITW saldoAlmacen ON saldoAlmacen.ItemCode = saldoUbicacion.ItemCode AND saldoAlmacen.WhsCode = saldoUbicacion.WhsCode ");
            sb.append("WHERE  saldoAlmacen.ItemCode = '");
            sb.append(referencia);
            sb.append("' ");
            sb.append("AND    saldoAlmacen.OnHand > 0 ");
            try {
                List<SaldoUbicacion> saldos = new ArrayList<>();
                List<Object[]> objs = em.createNativeQuery(sb.toString()).getResultList();
                if (objs != null && !objs.isEmpty()) {
                    for (Object[] obj : objs) {
                        saldos.add(new SaldoUbicacion((Integer) obj[0], (String) obj[1], obtenerDatosUbicacion((Integer) obj[2]),
                                (BigDecimal) obj[3], (String) obj[4], (Character) obj[5], (Integer) obj[6]));
                    }
                }
                return saldos;
            } catch (Exception e) {
                log.log(Level.SEVERE, "Error al consultar saldo de la referencia [{0}], error [{1}]", new Object[]{referencia, e.getMessage()});
                return null;
            }
        }
    }

    public UbicacionSAP obtenerDatosUbicacion(Integer binAbs) {
        if (binAbs != null && binAbs != 0) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<UbicacionSAP> cq = cb.createQuery(UbicacionSAP.class);
            Root<UbicacionSAP> ubicacion = cq.from(UbicacionSAP.class);

            cq.where(cb.equal(ubicacion.get("absEntry"), binAbs));
            try {
                return em.createQuery(cq).getSingleResult();
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<SaldoUbicacion> buscarXBinCode(String binCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoUbicacion> cq = cb.createQuery(SaldoUbicacion.class);
        Root<SaldoUbicacion> root = cq.from(SaldoUbicacion.class);

        cq.where(cb.equal(root.get("ubicacion").get("binCode"), binCode),
                cb.gt(root.<Integer>get("onHandQty"), 0));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<SaldoUbicacion> obtenerSaldoUbicacionAlmacen(String almacen, String ubicacion) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoUbicacion> cq = cb.createQuery(SaldoUbicacion.class);
        Root<SaldoUbicacion> saldo = cq.from(SaldoUbicacion.class);

        cq.where(cb.equal(saldo.get("ubicacion").get("binCode"), almacen + ubicacion),
                cb.gt(saldo.<Integer>get("onHandQty"), 0),
                cb.like(saldo.<String>get("whsCode"), almacen));
        cq.orderBy(cb.desc(saldo.get("onHandQty")));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean validarSlotDisponible(String codigoAsesor, String slot) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoUbicacion> cq = cb.createQuery(SaldoUbicacion.class);
        Root<SaldoUbicacion> saldo = cq.from(SaldoUbicacion.class);
        Join<UbicacionSAP, SaldoUbicacion> ubicacion = saldo.join("ubicacion", JoinType.INNER);

        cq.where(cb.and(cb.gt(saldo.get(SaldoUbicacion_.onHandQty), 0),
                cb.equal(ubicacion.get("sL1Code"), codigoAsesor),
                cb.equal(ubicacion.get("sL2Code"), slot),
                cb.like(ubicacion.<String>get("whsCode"), "DM%")));

        try {
            List<SaldoUbicacion> s = em.createQuery(cq).getResultList();

            if (s != null && !s.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return false;
        }
    }

    public List<SaldoUbicacion> obtenerSaldosDemostracion(String sL1Code, String sL2Code) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT saldo.* ");
        sb.append("FROM   OBIN ubicacion ");
        sb.append("INNER  JOIN OIBQ saldo ON saldo.BinAbs = ubicacion.AbsEntry ");
        sb.append("WHERE  saldo.WhsCode LIKE 'DM%' ");
        sb.append("AND    saldo.OnHandQty > 0 ");
        sb.append("AND    ubicacion.SL1Code = '");
        sb.append(sL1Code);
        sb.append("' AND    ubicacion.SL2Code = '");
        sb.append(sL2Code);
        sb.append("' ");

        try {
            return em.createNativeQuery(sb.toString(), SaldoUbicacion.class).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<SaldoUbicacion> obtenerSaldoCliente(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoUbicacion> cq = cb.createQuery(SaldoUbicacion.class);
        Root<SaldoUbicacion> saldo = cq.from(SaldoUbicacion.class);
        Join<UbicacionSAP, SaldoUbicacion> ubicacion = saldo.join("ubicacion", JoinType.INNER);

        cq.where(cb.gt(saldo.get(SaldoUbicacion_.onHandQty), 0),
                cb.equal(saldo.get(SaldoUbicacion_.itemCode), referencia),
                cb.like(ubicacion.<String>get("whsCode"), "CL%"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al consultar las ubicaciones de cliente. ", e);
        }
        return null;
    }

    public List<SaldoUbicacion> obtenerSaldoProveedor(String almacen, String ubicacion, String proveedor) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   OIBQ saldo ");
        sb.append("INNER  JOIN OBIN ubicacion ON ubicacion.AbsEntry = saldo.BinAbs ");
        sb.append("WHERE  ubicacion.BinCode = '");
        sb.append(almacen).append(ubicacion);
        sb.append("' ");
        sb.append("AND    OnHandQty > 0 ");
        sb.append("AND    LEFT(ItemCode, 3) = '");
        sb.append(proveedor);
        sb.append("' ");

        try {
            return em.createNativeQuery(sb.toString(), SaldoUbicacion.class).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return new ArrayList<>();
        }
    }
}
