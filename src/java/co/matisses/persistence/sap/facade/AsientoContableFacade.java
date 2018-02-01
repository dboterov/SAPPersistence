package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.AsientoContable;
import co.matisses.persistence.sap.entity.AsientoContable_;
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
public class AsientoContableFacade extends AbstractFacade<AsientoContable> {

    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;
    private static final Logger console = Logger.getLogger(AsientoContableFacade.class.getSimpleName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsientoContableFacade() {
        super(AsientoContable.class);
    }

    public List<AsientoContable> obtenerAsientosFactura(Integer docNum) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AsientoContable> cq = cb.createQuery(AsientoContable.class);
        Root<AsientoContable> asiento = cq.from(AsientoContable.class);

        cq.where(cb.equal(asiento.get(AsientoContable_.ref1), docNum.toString()));

        try {
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            console.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Object[]> consultarDatosAsientoConsignacion(Long docEntry) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT ef.DocDate RefDate, ");
        sb.append("       ef.DocDueDate DueDate, ");
        sb.append("       ef.TaxDate, ");
        sb.append("       CAST(e.Memo + '-AJCS' AS VARCHAR(100)) Memo, ");
        sb.append("       CAST(e.Ref1 AS VARCHAR(10)) Ref1, ");
        sb.append("       CAST(e.TransId AS VARCHAR(10)) Ref2, ");
        sb.append("       CAST(e.Ref3 AS VARCHAR(10)) Ref3, ");
        sb.append("       'AJCS' TransCode, ");
        sb.append("       ROW_NUMBER() OVER(ORDER BY da.StockPrice, d.ShortName ASC) -1 Line_ID, ");
        sb.append("       CAST(CASE WHEN d.ShortName = da.BalInvntAc THEN da.U_Cuenta_DB_Ajuste WHEN d.ShortName = da.SaleCostAc THEN da.U_Cuenta_CR_Ajuste END AS VARCHAR(20)) ShortName, ");
        sb.append("       CAST(d.LineMemo + '-AJCS' AS VARCHAR(100)) LineMemo, ");
        sb.append("       CAST(d.OcrCode2 AS VARCHAR(10)) OcrCode2, ");
        sb.append("       CAST(d.Project AS VARCHAR(10)) Project, ");
        sb.append("       CAST(CASE WHEN d.ShortName = da.BalInvntAc THEN ef.CardCode WHEN d.ShortName = da.SaleCostAc THEN da.Proveedor END AS VARCHAR(20)) U_InfoCo01, ");
        sb.append("       CASE WHEN d.ShortName = da.BalInvntAc THEN da.StockPrice ELSE 0 END Credit, ");
        sb.append("       CASE WHEN d.ShortName = da.SaleCostAc THEN da.StockPrice ELSE 0 END Debit, da.DocEntry, ");
        sb.append("       CAST(da.GLMethod AS VARCHAR(10)) GLMethod ");
        sb.append("FROM   OJDT e ");
        sb.append("INNER  JOIN JDT1 d ON d.TransId = e.TransId ");
        sb.append("INNER  JOIN (SELECT df.DocEntry, ");
        sb.append("                    k.GLMethod, ");
        sb.append("		       CASE k.GLMethod WHEN 'W' THEN a.BalInvntAc WHEN 'C' THEN g.BalInvntAc WHEN 'L' THEN i.BalInvntAc ELSE '' END BalInvntAc, ");
        sb.append("		       CASE k.GLMethod WHEN 'W' THEN a.SaleCostAc WHEN 'C' THEN g.SaleCostAc WHEN 'L' THEN i.SaleCostAc ELSE '' END SaleCostAc, ");
        sb.append("		       CASE k.GLMethod WHEN 'W' THEN csW.U_Cuenta_DB_Ajuste WHEN 'C' THEN csC.U_Cuenta_DB_Ajuste WHEN 'L' THEN csL.U_Cuenta_DB_Ajuste ELSE '' END U_Cuenta_DB_Ajuste, ");
        sb.append("		       CASE k.GLMethod WHEN 'W' THEN csW.U_Cuenta_CR_Ajuste WHEN 'C' THEN csC.U_Cuenta_CR_Ajuste WHEN 'L' THEN csL.U_Cuenta_CR_Ajuste ELSE '' END U_Cuenta_CR_Ajuste, ");
        sb.append("		       k.CardCode Proveedor, ");
        sb.append("		       CONVERT(INT, SUM(df.StockPrice * df.Quantity)) StockPrice ");
        sb.append("             FROM   RIN1 df ");
        sb.append("             INNER  JOIN OITM k ON k.ItemCode = df.ItemCode ");
        sb.append("             LEFT   JOIN OWHS a ON a.U_Consignacion = 'S' AND a.WhsCode = df.WhsCode ");
        sb.append("             LEFT   JOIN OITB g ON g.U_Consignacion = 'S' AND g.ItmsGrpCod = k.ItmsGrpCod ");
        sb.append("             LEFT   JOIN OITW i ON k.U_Consignacion = 'S' AND i.ItemCode = df.ItemCode AND i.WhsCode = df.WhsCode ");
        sb.append("             LEFT   JOIN [@BARU_CONSIG_MCIA] csW ON k.GLMethod = 'W' AND csW.U_GLMethod = k.GLMethod AND LEFT(a.WhsCode,LEN(csW.U_WhsCode)) = csW.U_WhsCode ");
        sb.append("             LEFT   JOIN [@BARU_CONSIG_MCIA] csC ON k.GLMethod = 'C' AND csC.U_GLMethod = k.GLMethod AND csC.U_ItmsGrpCod = g.ItmsGrpCod ");
        sb.append("             LEFT   JOIN [@BARU_CONSIG_MCIA] csL ON k.GLMethod = 'L' AND csL.U_GLMethod = k.GLMethod AND csL.U_ItemCode = i.ItemCode ");
        sb.append("             WHERE  (a.whscode IS NOT NULL OR g.ItmsGrpCod IS NOT NULL OR i.ItemCode IS NOT NULL) ");
        sb.append("             GROUP  BY df.DocEntry, ");
        sb.append("		       k.GLMethod, ");
        sb.append("		       CASE k.GLMethod WHEN 'W' THEN a.BalInvntAc WHEN 'C' THEN g.BalInvntAc WHEN 'L' THEN i.BalInvntAc ELSE '' END, ");
        sb.append("		       CASE k.GLMethod WHEN 'W' THEN a.SaleCostAc WHEN 'C' THEN g.SaleCostAc WHEN 'L' THEN i.SaleCostAc ELSE '' END, ");
        sb.append("		       CASE k.GLMethod WHEN 'W' THEN csW.U_Cuenta_DB_Ajuste WHEN 'C' THEN csC.U_Cuenta_DB_Ajuste WHEN 'L' THEN csL.U_Cuenta_DB_Ajuste ELSE '' END, ");
        sb.append("		       CASE k.GLMethod WHEN 'W' THEN csW.U_Cuenta_CR_Ajuste WHEN 'C' THEN csC.U_Cuenta_CR_Ajuste WHEN 'L' THEN csL.U_Cuenta_CR_Ajuste ELSE '' END, ");
        sb.append("		       k.CardCode) da ON da.docentry = e.CreatedBy AND (da.BalInvntAc = d.ShortName OR da.SaleCostAc = d.ShortName) ");
        sb.append("INNER  JOIN ORIN ef ON ef.DocEntry = da.DocEntry ");
        sb.append("LEFT   JOIN (SELECT TransId, ");
        sb.append("		       Ref1, ");
        sb.append("                    Ref2 ");
        sb.append("		FROM   OJDT ");
        sb.append("		WHERE  TransType = 30) valida ON valida.Ref1 = e.Ref1 ");
        sb.append("WHERE  e.TransType = '14' ");
        sb.append("AND    e.CreatedBy = ");
        sb.append(docEntry);
        sb.append(" AND    ISNULL(valida.Ref2, '') <> CONVERT(VARCHAR, e.TransId) ");
        sb.append("ORDER  BY da.StockPrice ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            console.log(Level.SEVERE, "", e);
            return null;
        }
    }
}
