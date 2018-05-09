package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.Almacen;
import co.matisses.persistence.sap.entity.SaldoItemInventario;
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
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class SaldoItemInventarioFacade extends AbstractFacade<SaldoItemInventario> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger CONSOLE = Logger.getLogger(SaldoItemInventarioFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SaldoItemInventarioFacade() {
        super(SaldoItemInventario.class);
    }

    public List<SaldoItemInventario> buscarReferenciaSaldoBodega(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
        Root<SaldoItemInventario> item = cq.from(SaldoItemInventario.class);

        cq.where(cb.equal(item.get("saldoItemInventarioPK").get("itemCode"), referencia),
                cb.gt(item.<Integer>get("onHand"), 0),
                cb.like(item.get("saldoItemInventarioPK").get("whsCode").<String>get("whsCode"), "01%"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    public SaldoItemInventario buscarReferenciaSaldoBodega(String referencia, String almacen) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
        Root<SaldoItemInventario> item = cq.from(SaldoItemInventario.class);

        cq.where(cb.equal(item.get("saldoItemInventarioPK").get("itemCode"), referencia),
                cb.gt(item.<Integer>get("onHand"), 0),
                cb.equal(item.get("saldoItemInventarioPK").get("whsCode").get("whsCode"), almacen));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<String> obtenerOrdenBodegas(String tienda) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR(10), bodPrincipal) AS Bodega ");
        sb.append("FROM   ( ");
        sb.append("	   SELECT 1 AS Principal, ");
        sb.append("		  WhsCode AS BodPrincipal, ");
        sb.append("		  mun.Name, ");
        sb.append("		  CONVERT(INT, U_Velocidad) velocidad ");
        sb.append("	   FROM   OWHS alm ");
        sb.append("	   INNER  JOIN [@BPCO_MU] mun ON mun.Code = alm.U_Ciudad ");
        sb.append("	   WHERE  mun.Code = ( ");
        sb.append("			      SELECT TOP 1 U_Ciudad ");
        sb.append("			      FROM   OWHS ");
        sb.append("			      WHERE  WhsCode = '");
        sb.append(tienda);
        sb.append("') ");
        sb.append("	   AND    WhsCode LIKE '01%' ");
        sb.append("	   AND    U_Estado <> 'N' ");
        sb.append("	   UNION  ALL ");
        sb.append("	   SELECT 2 AS Principal, ");
        sb.append("		  WhsCode AS BodPrincipal, ");
        sb.append("		  mun.Name, ");
        sb.append("		  CONVERT(INT, U_Velocidad) velocidad ");
        sb.append("	   FROM   OWHS alm ");
        sb.append("	   INNER  JOIN [@BPCO_MU] mun ON mun.Code = alm.U_Ciudad ");
        sb.append("	   WHERE  mun.Code <> ( ");
        sb.append("			       SELECT TOP 1 U_Ciudad ");
        sb.append("			       FROM   OWHS ");
        sb.append("			       WHERE  WhsCode = '");
        sb.append(tienda);
        sb.append("') ");
        sb.append("	   AND    WhsCode LIKE '01%' ");
        sb.append("	   AND    U_Estado <> 'N' ");
        sb.append("       ) AS t ");
        sb.append("ORDER  BY Principal, velocidad ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    public List<SaldoItemInventario> findWithParameters(String reference, String whsPrefix, boolean ignoreZero) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
            Root<SaldoItemInventario> saldos = cq.from(SaldoItemInventario.class);
            Predicate conjuncion = cb.conjunction();
            conjuncion.getExpressions().add(cb.equal(saldos.get("saldoItemInventarioPK").
                    <String>get("itemCode"), reference));
            conjuncion.getExpressions().add(cb.like(saldos.get("saldoItemInventarioPK").
                    <String>get("whsCode").
                    <String>get("whsCode"), whsPrefix.concat("%")));
            conjuncion.getExpressions().add(cb.equal(saldos.get("saldoItemInventarioPK").
                    <Almacen>get("whsCode").<String>get("estado"), "S"));
            if (ignoreZero) {
                conjuncion.getExpressions().add(cb.gt(saldos.get("onHand").as(Integer.class), 0));
            }

            cq.where(conjuncion);

            List<Order> orderByList = new ArrayList<>();
            orderByList.add(cb.asc(saldos.get("saldoItemInventarioPK").
                    <String>get("whsCode").
                    <String>get("uPrioridad")));
            orderByList.add(cb.asc(saldos.get("saldoItemInventarioPK").
                    <String>get("whsCode").
                    <String>get("uVelocidad")));
            cq.orderBy(orderByList);

            List<SaldoItemInventario> result = em.createQuery(cq).getResultList();
            return result;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<SaldoItemInventario> obtenerSaldoAlmacen(String almacen) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
        Root<SaldoItemInventario> saldo = cq.from(SaldoItemInventario.class);

        cq.where(cb.equal(saldo.get("saldoItemInventarioPK").get("whsCode").get("whsCode"), almacen),
                cb.gt(saldo.get("onHand").as(Integer.class), 0));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<Object[]> obtenerSaldoAlmacenes(List<String> almacenes, String ubicacion, boolean parcial, Integer idConteo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(sa.ItemCode as varchar(20)) ItemCode, ");
        if (ubicacion != null) {
            sb.append("cast(su.OnHandQty as int) as saldo ");
        } else {
            sb.append("cast(sum(sa.OnHand) as int) as saldo");
        }
        sb.append("from OITW sa ");
        if (ubicacion != null) {
            sb.append("inner join OBIN u on u.BinCode = CONCAT(sa.WhsCode, 'COMPLEMENTOS') ");
            sb.append("inner join OIBQ su on su.WhsCode = sa.WhsCode and su.BinAbs = u.AbsEntry and su.OnHandQty > 0 and su.ItemCode = sa.ItemCode ");
        }
        sb.append("where sa.WhsCode IN (");
        for (String almacen : almacenes) {
            sb.append("'");
            sb.append(almacen);
            sb.append("',");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") and sa.OnHand > 0 ");
        if (parcial) {
            sb.append("and   sa.ItemCode collate database_default in ( ");
            sb.append("select distinct referencia ");
            sb.append("from   web.baru.dbo.detalle_conteo ");
            sb.append("where  idConteo =");
            sb.append(idConteo);
            sb.append(" )");
        }
        sb.append("order by sa.ItemCode ");

        CONSOLE.log(Level.INFO, sb.toString());
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error consultando el saldo disponible. ", e);
            return new ArrayList<>();
        }
    }

    public Integer obtenerSaldoStockCambiarModelo(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Double> cq = cb.createQuery(Double.class);
        Root<SaldoItemInventario> saldo = cq.from(SaldoItemInventario.class);

        cq.where(cb.equal(saldo.get("saldoItemInventarioPK").get("itemCode"), referencia),
                cb.notLike(saldo.<String>get("saldoItemInventarioPK").<String>get("whsCode").<String>get("whsCode"), "DM%"),
                cb.notLike(saldo.<String>get("saldoItemInventarioPK").<String>get("whsCode").<String>get("whsCode"), "CL%"));
        cq.select(cb.sumAsDouble(saldo.<Float>get("onHand")));

        try {
            return (Integer) em.createQuery(cq).getSingleResult().intValue();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return 0;
        }
    }

    public List<SaldoItemInventario> obtenerSaldosReferenciaCambiarModelo(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
        Root<SaldoItemInventario> saldo = cq.from(SaldoItemInventario.class);

        cq.where(cb.equal(saldo.get("saldoItemInventarioPK").get("itemCode"), referencia),
                cb.gt(saldo.<Integer>get("onHand"), 0),
                cb.notLike(saldo.<String>get("saldoItemInventarioPK").<String>get("whsCode").<String>get("whsCode"), "DM%"),
                cb.notLike(saldo.<String>get("saldoItemInventarioPK").<String>get("whsCode").<String>get("whsCode"), "CL%"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<SaldoItemInventario> obtenerSaldosAlmacen(String almacen) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
        Root<SaldoItemInventario> saldo = cq.from(SaldoItemInventario.class);
        cq.where(cb.gt(saldo.<Integer>get("onHand"), 0),
                cb.like(saldo.<String>get("saldoItemInventarioPK").<String>get("whsCode").<String>get("whsCode"), "%" + almacen + "%"));
        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public Integer obtenerTotalSaldoVenta(String referencia) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ISNULL(CAST(SUM(OnHand) AS INT),0) saldoTotal FROM OITW WHERE ItemCode = '");
        sb.append(referencia);
        sb.append("' AND WhsCode LIKE '0%' AND OnHand > 0");
        try {
            Integer saldo = (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
            return saldo;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el saldo del producto " + referencia, e);
            return 0;
        }
    }

    public List<SaldoItemInventario> obtenerSaldo(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
        Root<SaldoItemInventario> saldo = cq.from(SaldoItemInventario.class);

        cq.where(cb.gt(saldo.<Integer>get("onHand"), 0),
                cb.equal(saldo.get("saldoItemInventarioPK").get("itemCode"), referencia));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public Long consultarSaldoTarjetaRegalo(String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(saldo.OnHand AS INT) saldo ");
        sb.append("FROM   OITM itm ");
        sb.append("INNER  JOIN OITW saldo ON saldo.ItemCode = itm.ItemCode AND saldo.OnHand > 0 AND saldo.WhsCode = CONCAT('IN','");
        sb.append(almacen);
        sb.append("') WHERE itm.ItmsGrpCod = '113' ");
        sb.append("AND    itm.U_Grupo = '046' ");

        try {
            return ((Integer) em.createNativeQuery(sb.toString()).getSingleResult()).longValue();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el saldo de tarjetas de regalo en el almacen " + almacen, e);
            return 0L;
        }
    }

    public List<String> obtenerSaldosVenta() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(VARCHAR(20), ItemCode) AS ItemCode ");
        sb.append("FROM   OITW ");
        sb.append("WHERE  WhsCode LIKE '0%' ");
        sb.append("AND    OnHand > 0 ");
        sb.append("AND    LEN(ItemCode) = 20 ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Object[]> obtenerSaldosPrioridad(String referencia, String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   ( ");
        sb.append("        SELECT CONVERT(INT, 0) AS Principal, ");
        sb.append("               CONVERT(VARCHAR, alm.WhsCode) AS Almacen, ");
        sb.append("               0 AS Prioridad, ");
        sb.append("               0 AS Velocidad, ");
        sb.append("               CONVERT(NUMERIC(18, 2), u.OnHandQty) AS Saldo ");
        sb.append("        FROM   OITW s ");
        sb.append("        INNER  JOIN OIBQ u ON u.ItemCode = s.ItemCode ");
        sb.append("        INNER  JOIN OBIN d ON d.AbsEntry = u.BinAbs ");
        sb.append("        INNER  JOIN OWHS alm ON alm.WhsCode = d.WhsCode AND alm.WhsCode = s.WhsCode ");
        sb.append("        WHERE  u.OnHandQty > 0  ");
        sb.append("        AND    s.ItemCode = '");
        sb.append(referencia);
        sb.append("' ");
        sb.append("        AND    d.Attr10Val = 'Bodega' ");
        sb.append("        AND    alm.U_Estado = 'S' ");
        sb.append("        AND    alm.WhsCode = '");
        sb.append(almacen);
        sb.append("' ");
        sb.append("        UNION  ALL ");
        sb.append("        SELECT CONVERT(INT, 1) AS Principal, ");
        sb.append("		  CONVERT(VARCHAR, alm.WhsCode) AS Almacen, ");
        sb.append("		  CONVERT(INT, U_Prioridad) AS Prioridad, ");
        sb.append("		  CONVERT(INT, U_Velocidad) AS Velocidad, ");
        sb.append("		  CONVERT(NUMERIC(18, 2), saldo.OnHand) AS Saldo ");
        sb.append("        FROM   OWHS alm ");
        sb.append("        INNER  JOIN [@BPCO_MU] mun ON mun.Code = alm.U_Ciudad ");
        sb.append("        INNER  JOIN OITW saldo ON saldo.WhsCode = alm.WhsCode ");
        sb.append("        WHERE  mun.Code = (SELECT TOP 1 U_Ciudad ");
        sb.append("                           FROM   OWHS ");
        sb.append("                           WHERE  WhsCode = '");
        sb.append(almacen);
        sb.append("') ");
        sb.append("        AND    alm.WhsCode LIKE '01%' ");
        sb.append("        AND    U_Estado <> 'N' ");
        sb.append("        AND    saldo.ItemCode = '");
        sb.append(referencia);
        sb.append("'       AND    saldo.OnHand > 0 ");
        sb.append("        AND    alm.U_Estado = 'S' ");
        sb.append("        UNION  ALL ");
        sb.append("        SELECT CONVERT(INT, 2) AS Principal, ");
        sb.append("		  CONVERT(VARCHAR, alm.WhsCode) AS Almacen, ");
        sb.append("		  CONVERT(INT, U_Prioridad) AS Prioridad, ");
        sb.append("		  CONVERT(INT, U_Velocidad) AS Velocidad, ");
        sb.append("		  CONVERT(NUMERIC(18, 2), saldo.OnHand) AS Saldo ");
        sb.append("        FROM   OWHS alm ");
        sb.append("        INNER  JOIN [@BPCO_MU] mun ON mun.Code = alm.U_Ciudad ");
        sb.append("        INNER  JOIN OITW saldo ON saldo.WhsCode = alm.WhsCode ");
        sb.append("        WHERE  mun.Code <> (SELECT TOP 1 U_Ciudad ");
        sb.append("                            FROM   OWHS ");
        sb.append("                            WHERE  WhsCode = '");
        sb.append(almacen);
        sb.append("') ");
        sb.append("        AND    alm.WhsCode LIKE '01%' ");
        sb.append("        AND    U_Estado <> 'N' ");
        sb.append("        AND    saldo.ItemCode = '");
        sb.append(referencia);
        sb.append("'       AND    saldo.OnHand > 0 ");
        sb.append("        AND    alm.U_Estado = 'S' ");
        sb.append("       ) AS t ");
        sb.append("ORDER  BY Principal, Prioridad, Velocidad ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            CONSOLE.log(Level.SEVERE, "No se encontraron datos de la referencia {0} para el almacen {1} por prioridad", new Object[]{referencia, almacen});
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los datos de saldos por prioridad", e);
        }
        return null;
    }

    public Integer obtenerSaldoSolicitud(String referencia, String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT SUM(CONVERT(INT, saldoUbi.OnHandQty)) ");
        sb.append("FROM   OITW saldo ");
        sb.append("INNER  JOIN OIBQ saldoUbi ON saldoUbi.ItemCode = saldo.ItemCode AND saldoUbi.WhsCode = saldo.WhsCode ");
        sb.append("INNER  JOIN OBIN ubi ON ubi.AbsEntry = saldoUbi.BinAbs ");
        sb.append("WHERE  saldo.OnHand > 0 ");
        sb.append("AND    saldoUbi.OnHandQty > 0 ");
        sb.append("AND    ((ubi.Attr10Val = 'Bodega' AND ubi.WhsCode = '");
        sb.append(almacen);
        sb.append("') OR saldo.WhsCode LIKE '01%') ");
        sb.append("AND    LEN(saldo.ItemCode) = 20 ");
        sb.append("AND    saldo.ItemCode = '");
        sb.append(referencia);
        sb.append("' ");

        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    public List<SaldoItemInventario> buscarReferenciaSaldoBodegaVenta(String referencia) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
        Root<SaldoItemInventario> item = cq.from(SaldoItemInventario.class);

        cq.where(cb.equal(item.get("saldoItemInventarioPK").get("itemCode"), referencia),
                cb.gt(item.<Integer>get("onHand"), 0),
                cb.like(item.get("saldoItemInventarioPK").get("whsCode").<String>get("whsCode"), "0%"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    /**
     * POR INSTRUCCION DE JUAN DAVID GARCIA Y MARIA EUGENIA PERDONO SE
     * IMPLEMENTA ESTE UPDATE, EL PROGRAMADOR QUEDA LIBRE DE TODO PROBLEMA
     */
    public boolean alterarCostoAlmacen(Double costo, String referencia, String almacen) {
        StringBuilder sb = new StringBuilder();

        /*Actualizar costo en bodega de devoluciones*/
        sb.append("UPDATE OITW ");
        sb.append("SET    AvgPrice = ");
        sb.append(costo);
        sb.append(", StockValue = ");
        sb.append(costo);
        sb.append(" * 0, ");
        sb.append("OnHand = 0");
        sb.append("WHERE  WhsCode = '");
        sb.append(almacen);
        sb.append("' AND  ItemCode = '");
        sb.append(referencia);
        sb.append("'");

        try {
            em.createNativeQuery(sb.toString()).executeUpdate();
            return true;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al actualizar el costo del almacen. ", e);
            return false;
        }
    }

    public List<SaldoItemInventario> obtenerSaldoProveedor(String almacen, String proveedor) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   OITW ");
        sb.append("WHERE  WhsCode = '");
        sb.append(almacen);
        sb.append("' ");
        sb.append("AND    OnHand > 0 ");
        sb.append("AND    LEFT(ItemCode, 3) = '");
        sb.append(proveedor);
        sb.append("' ");

        try {
            return em.createNativeQuery(sb.toString(), SaldoItemInventario.class).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<String> obtenerProveedores() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(VARCHAR(3), LEFT(ItemCode, 3)) AS proveedor ");
        sb.append("FROM   OITW ");
        sb.append("WHERE  OnHand > 0 ");
        sb.append("AND    LEN(ItemCode) = 20 ");
        sb.append("ORDER  BY 1 ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los proveedores para los conteos de inventario. ", e);
            return null;
        }
    }

    public List<Object[]> getDiasAlmacen(String referencia) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR(20), det.WhsCode) AS whsCode, DATEDIFF(dd, MAX(enc.DocDate), GETDATE()) AS dias_exhibido ");
        sb.append("FROM   OWTR enc ");
        sb.append("INNER  JOIN WTR1 det ON det.DocEntry = enc.DocEntry  ");
        sb.append("WHERE  det.ItemCode = '");
        sb.append(referencia);
        sb.append("' GROUP BY det.ItemCode, det.WhsCode");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> getMovimientos(String itemCode, String whsCode) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR, t2.ItemCode) AS referencia, ");
        sb.append("       CONVERT(INT, t1.DocNum) AS numero, ");
        sb.append("       CONVERT(DATE, t1.DocDate) AS fecha, ");
        sb.append("       CONVERT(VARCHAR(MAX), t1.Comments) AS comentario, ");
        sb.append("       CONVERT(VARCHAR, t1.Filler) AS origen, ");
        sb.append("       CONVERT(VARCHAR, t2.WhsCode) AS destino, ");
        sb.append("       CONVERT(INT, SUM(t2.Quantity)) AS Cantidad ");
        sb.append("FROM   WTR1 t2 ");
        sb.append("INNER  JOIN OWTR t1 ON t1.DocEntry = t2.DocEntry ");
        sb.append("WHERE  t2.ItemCode = '");
        sb.append(itemCode);
        sb.append("' AND  (t2.WhsCode like '");
        sb.append(whsCode);
        sb.append("%' OR  t1.Filler LIKE '");
        sb.append(whsCode);
        sb.append("%') GROUP BY t2.ItemCode, t1.DocNum, t1.DocDate, t1.Comments, t1.Filler, t2.WhsCode, t2.ItemCode ");
        sb.append("ORDER  BY t1.DocDate, t2.ItemCode, t1.DocNum, t1.Filler");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    //diferenciar por referencia
    public List<SaldoItemInventario> obtenerSaldoAlmacenItemCode(String almacen, String itemCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SaldoItemInventario> cq = cb.createQuery(SaldoItemInventario.class);
        Root<SaldoItemInventario> saldo = cq.from(SaldoItemInventario.class);

        cq.where(cb.equal(saldo.get("saldoItemInventarioPK").get("whsCode").get("whsCode"), almacen),
                cb.gt(saldo.get("onHand").as(Integer.class), 0),
                cb.equal(saldo.get("saldoItemInventarioPK").get("itemCode"), itemCode)
        );

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }
}
