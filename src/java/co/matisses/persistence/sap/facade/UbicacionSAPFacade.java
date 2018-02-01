package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.UbicacionSAP;
import co.matisses.persistence.sap.entity.UbicacionSAP_;
import java.text.SimpleDateFormat;
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
import javax.persistence.criteria.Root;

/**
 *
 * @author ygil
 */
@Stateless
public class UbicacionSAPFacade extends AbstractFacade<UbicacionSAP> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger log = Logger.getLogger(UbicacionSAPFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UbicacionSAPFacade() {
        super(UbicacionSAP.class);
    }

    public boolean validarUbicacionesAlmacen(String almacen) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UbicacionSAP> cq = cb.createQuery(UbicacionSAP.class);
        Root<UbicacionSAP> ubicacion = cq.from(UbicacionSAP.class);

        cq.where(cb.equal(ubicacion.get("whsCode"), almacen));

        try {
            if (em.createQuery(cq).getResultList().size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    public UbicacionSAP obtenerDatosUbicacionBinCode(String binCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UbicacionSAP> cq = cb.createQuery(UbicacionSAP.class);
        Root<UbicacionSAP> ubicacion = cq.from(UbicacionSAP.class);

        cq.where(cb.equal(ubicacion.get("binCode"), binCode));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<UbicacionSAP> obtenerUbicacionesRelacionadas(String ubi) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UbicacionSAP> cq = cb.createQuery(UbicacionSAP.class);
        Root<UbicacionSAP> ubicacion = cq.from(UbicacionSAP.class);

        cq.where(cb.like(ubicacion.<String>get("binCode"), "%" + ubi));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<UbicacionSAP> obtenerUbicacionesAlmacen(String whsCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UbicacionSAP> cq = cb.createQuery(UbicacionSAP.class);
        Root<UbicacionSAP> ubicacion = cq.from(UbicacionSAP.class);

        cq.where(cb.equal(ubicacion.get("whsCode"), whsCode), cb.equal(ubicacion.get("attr1Val"), "No"));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public List<UbicacionSAP> obtenerUbicacionesDemostracion(String whsCodeDemostracion, String referencia) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT ubicacion.* ");
        sb.append("FROM   OBIN ubicacion ");
        sb.append("INNER  JOIN OIBQ saldo on saldo.BinAbs = ubicacion.AbsEntry ");
        sb.append("WHERE  saldo.WhsCode = '");
        sb.append(whsCodeDemostracion);
        sb.append("' AND  saldo.OnHandQty > 0 ");
        sb.append("AND    saldo.ItemCode = '");
        sb.append(referencia);
        sb.append("' ");

        try {
            return em.createNativeQuery(sb.toString(), UbicacionSAP.class).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return null;
        }
    }

    public Object[] obtenerSiguienteUbicacionInventarioCiclico(String whsCode) {
        try {
            //TODO: cambiar servidor vinculado antes de desplegar a produccion
            StringBuilder sb = new StringBuilder();
            sb.append("select ubicacion.AbsEntry as BinAbs");
            sb.append(", cast(ubicacion.BinCode as varchar(10)) BinCode");
            sb.append(", cast(ubicacion.WhsCode as varchar(10)) WhsCode");
            sb.append(", cast(CONCAT(ubicacion.SL1Code, ubicacion.SL2Code) as varchar(30)) ub");
            sb.append(", cast(max(msgLog.DocDate) as date) LastTransactionDate");
            sb.append(", (select MAX(fechaCreacionConteo) from [WEB].[baru].[dbo].[BITACORA_INVENTARIO_UBICACION] bitacora where bitacora.binCode collate database_default = ubicacion.BinCode) LastInventoryDate ");
            sb.append("from OBIN ubicacion ");
            sb.append("inner join OBTL binLog on binLog.BinAbs = ubicacion.AbsEntry ");
            sb.append("inner join OILM msgLog on msgLog.MessageID = binLog.MessageID ");
            sb.append("where ubicacion.Disabled = 'N' ");
            sb.append("and ubicacion.SysBin = 'N' ");
            sb.append("and ubicacion.WhsCode like '%");
            sb.append(whsCode);
            sb.append("' and ubicacion.Attr1Val = 'No' and ubicacion.WhsCode not like 'AF%' ");
            sb.append("group by ubicacion.AbsEntry, ubicacion.BinCode, ubicacion.WhsCode, ubicacion.SL1Code, ubicacion.SL2Code ");
            sb.append("order by LastInventoryDate asc, LastTransactionDate asc ");
            return (Object[]) em.createNativeQuery(sb.toString()).getResultList().get(0);
        } catch (Exception e) {
            log.log(Level.SEVERE, "No fue posible consultar la siguiente ubicacion para inventario ciclico. ", e);
            return null;
        }
    }

    public List<UbicacionSAP> obtenerUbicacionPalletAlmacenSaldo(String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT ubicacion.* ");
        sb.append("FROM   OBIN ubicacion ");
        sb.append("INNER  JOIN OIBQ saldo ON saldo.BinAbs = ubicacion.AbsEntry ");
        sb.append("WHERE  ubicacion.Attr9Val = 'Sí' ");
        sb.append("AND    saldo.OnHandQty > 0 ");
        sb.append("AND    ubicacion.WhsCode = '");
        sb.append(almacen);
        sb.append("' ");

        try {
            return em.createNativeQuery(sb.toString(), UbicacionSAP.class).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public boolean validarUbicacionExiste(String binCode) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UbicacionSAP> cq = cb.createQuery(UbicacionSAP.class);
        Root<UbicacionSAP> ubicacion = cq.from(UbicacionSAP.class);

        cq.where(cb.equal(ubicacion.get(UbicacionSAP_.binCode), binCode));

        try {
            UbicacionSAP ubi = em.createQuery(cq).getSingleResult();
            if (ubi != null && ubi.getAbsEntry() != null && ubi.getAbsEntry() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            log.log(Level.SEVERE, "No se encontraron datos.");
            return false;
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return false;
        }
    }

    public List<Object[]> obtenerDemosActivas(Integer codigoAsesor, String estado, String parametroBusqueda, Date fechaInicial, Date fechaFinal) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(INT, ubicacion.SL1Code) AS SL1Code, ");
        sb.append("       CONVERT(VARCHAR(4), ubicacion.SL2Code) AS SL2Code, ");
        sb.append("       CONVERT(VARCHAR(20), Attr2Val) AS Attr2Val, ");
        sb.append("       CONVERT(DATE, Attr3Val) AS Attr3Val, ");
        sb.append("       CONVERT(DATE, Attr4Val) AS Attr4Val, ");
        sb.append("       CONVERT(VARCHAR(20), Attr5Val) AS Attr5Val, ");
        sb.append("       CONVERT(VARCHAR(200), cliente.CardName) AS CardName, ");
        sb.append("       CONVERT(INT, Attr7Val) AS Attr7Val, ");
        sb.append("       CONVERT(INT, Attr8Val) AS Attr8Val ");
        sb.append("FROM   OBIN ubicacion ");
        sb.append("INNER  JOIN OIBQ saldo ON saldo.BinAbs = ubicacion.AbsEntry ");
        sb.append("INNER  JOIN OCRD cliente ON cliente.CardCode = ubicacion.Attr5Val ");
        sb.append("WHERE  saldo.WhsCode LIKE 'DM%' ");
        sb.append("AND    saldo.OnHandQty > 0 ");
        sb.append("AND    ubicacion.SL1Code NOT LIKE 'UBICACIÓN-DE-SISTEMA' ");
        if (codigoAsesor != null && codigoAsesor != 0) {
            sb.append("AND    ubicacion.SL1Code = '");
            sb.append(codigoAsesor);
            sb.append("' ");
        }
        if (estado != null && !estado.isEmpty()) {
            sb.append("AND    ubicacion.Attr4Val ");
            sb.append(estado.equals("v") ? "<" : ">=");
            sb.append(" '");
            sb.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            sb.append("' ");
        }
        if (fechaInicial != null && fechaFinal != null) {
            sb.append(" AND    ubicacion.Attr3Val BETWEEN '");
            sb.append(new SimpleDateFormat("yyyy-MM-dd").format(fechaInicial));
            sb.append("' AND '");
            sb.append(new SimpleDateFormat("yyyy-MM-dd").format(fechaFinal));
            sb.append("' ");
        }
        if (parametroBusqueda != null && !parametroBusqueda.isEmpty()) {
            sb.append("AND    (Attr3Val LIKE '%");
            sb.append(parametroBusqueda);
            sb.append("%' OR cliente.CardName LIKE '%");
            sb.append(parametroBusqueda);
            sb.append("%' OR Attr7Val LIKE '%");
            sb.append(parametroBusqueda);
            sb.append("%' OR Attr8Val LIKE '%");
            sb.append(parametroBusqueda);
            sb.append("%') ");
        }
        sb.append("GROUP  BY ubicacion.SL1Code, ubicacion.SL2Code, Attr2Val, Attr3Val, Attr4Val, Attr5Val, cliente.CardName, Attr7Val, Attr8Val ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Integer> obtenerAsesoresDemosActivas() {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(INT, ubicacion.SL1Code) AS SL1Code ");
        sb.append("FROM   OBIN ubicacion ");
        sb.append("INNER  JOIN OIBQ saldo ON saldo.BinAbs = ubicacion.AbsEntry ");
        sb.append("WHERE  saldo.WhsCode LIKE 'DM%' ");
        sb.append("AND    saldo.OnHandQty > 0 ");
        sb.append("AND    ubicacion.SL1Code NOT LIKE 'UBICACIÓN-DE-SISTEMA' ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public Object[] obtenerDemoXId(Integer idDemo) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(INT, ubicacion.SL1Code) AS SL1Code, ");
        sb.append("       CONVERT(VARCHAR(4), ubicacion.SL2Code) AS SL2Code, ");
        sb.append("       CONVERT(VARCHAR(20), Attr2Val) AS Attr2Val, ");
        sb.append("       CONVERT(DATE, Attr3Val) AS Attr3Val, ");
        sb.append("       CONVERT(DATE, Attr4Val) AS Attr4Val, ");
        sb.append("       CONVERT(VARCHAR(20), Attr5Val) AS Attr5Val, ");
        sb.append("       CONVERT(INT, Attr7Val) AS Attr7Val, ");
        sb.append("       CONVERT(INT, Attr8Val) AS Attr8Val ");
        sb.append("FROM   OBIN ubicacion ");
        sb.append("WHERE  Attr7Val = ");
        sb.append(idDemo);

        try {
            return (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            log.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<UbicacionSAP> obtenerUbicacionesReceptorasAlmacen(String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM   OBIN ");
        sb.append("WHERE  WhsCode = '");
        sb.append(almacen);
        sb.append("' ");
        sb.append("AND    (Attr10Val <> 'Bodega' OR Attr10Val IS NULL) ");
        sb.append("AND    Attr1Val <> 'Sí' ");
        sb.append("AND    SL1Code <> 'DEMO' ");

        try {
            return em.createNativeQuery(sb.toString(), UbicacionSAP.class).getResultList();
        } catch (NoResultException e) {
            log.log(Level.SEVERE, "No se encontraron ubicaciones receptoras para el almacen {0}", almacen);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Ocurrio un error al obtener las ubicaciones receptoras para el almacen", e);
        }
        return null;
    }
}
