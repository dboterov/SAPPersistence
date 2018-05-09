package co.matisses.persistence.sap.facade;

import co.matisses.persistence.sap.entity.FacturaSAP;
import co.matisses.persistence.sap.entity.FacturaSAP_;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class FacturaSAPFacade extends AbstractFacade<FacturaSAP> {

    private static final Logger CONSOLE = Logger.getLogger(FacturaSAPFacade.class.getSimpleName());
    @PersistenceContext(unitName = "SAPPersistencePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaSAPFacade() {
        super(FacturaSAP.class);
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Object[]> listCustomerOrders(String nit) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(serie.BeginStr as varchar(4)) prefix ");
        sb.append(", cast(enc.docnum as varchar(10)) docnum ");
        sb.append(", cast(enc.DocDate as date) docdate ");
        sb.append(", cast(det.ItemCode as varchar(20)) itemcode ");
        sb.append(", cast(sum(det.Quantity) as int) cantidadF ");
        sb.append(", cast(round(det.PriceAfVAT, -1) as int) precio ");
        sb.append("from OINV enc ");
        sb.append("inner join INV1 det on det.DocEntry = enc.DocEntry ");
        sb.append("left join ORIN encDev on encDev.NumAtCard = enc.DocNum and encDev.U_TipoNota = 'A' ");
        sb.append("inner join NNM1 serie on serie.Series = enc.Series ");
        sb.append("where enc.CardCode = '");
        sb.append(nit);
        sb.append("' and enc.DocType = 'I' ");
        sb.append("and encDev.DocNum is null ");
        sb.append("group by serie.BeginStr, enc.docnum, enc.docdate, det.ItemCode, enc.DocDate, det.PriceAfVAT ");
        sb.append("order by 2, 3 ");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al listar las facturas de un cliente. ", e);
            return new ArrayList<>();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Integer getCreditNotesCount(Integer idTurnoCaja) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(1) notasCredito ");
        sb.append("from   ORIN ");
        sb.append("where  POSCashN = ");
        sb.append(idTurnoCaja);
        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No se pudieron consultar las anulaciones para el turno " + idTurnoCaja, e);
            return 0;
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Object[]> getInvoiceTaxInfoByDate(String serieFactura, String serieNotaCredito, String year, String month, String day, Integer idTurnoCaja) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DocNum, grpName, imp, SUM(base) base, SUM(despuesImpuesto) despuesImpuesto FROM (");
        sb.append("select cast(enc.docnum as varchar) DocNum ");
        sb.append("     , cast(d.ItmsGrpNam as varchar) grpName ");
        sb.append("     , cast(t.Name as varchar) imp ");
        sb.append("     , cast(sum(p1.Price * det.Quantity) as numeric(11,2)) base ");
        sb.append("     , cast(sum(p2.Price * det.Quantity) as numeric(11,2)) despuesImpuesto ");
        sb.append("from   OINV enc ");
        sb.append("inner join inv1 det on det.DocEntry = enc.DocEntry ");
        sb.append("inner join OITM i on i.itemcode = det.itemcode ");
        sb.append("inner join OITB d on d.ItmsGrpCod = i.ItmsGrpCod ");
        sb.append("inner join ITM1 p1 on p1.ItemCode = i.ItemCode and p1.PriceList = 1 ");
        sb.append("inner join ITM1 p2 on p2.ItemCode = i.ItemCode and p2.PriceList = 2 ");
        sb.append("inner join OSTC t on t.Code = det.TaxCode ");
        sb.append("where  enc.Series = ");
        sb.append(serieFactura);
        sb.append(" and    DATEPART(year,enc.docdate) = ");
        sb.append(year);
        sb.append(" and    DATEPART(month,enc.docdate) = ");
        sb.append(month);
        sb.append(" and    DATEPART(day,enc.docdate) = ");
        sb.append(day);
        if (idTurnoCaja != null) {
            sb.append(" and    enc.POSCashN = ");
            sb.append(idTurnoCaja);
        }
        sb.append(" group by enc.DocNum, d.ItmsGrpNam, t.Name ");
        sb.append("UNION ALL ");
        sb.append("select cast(enc.NumAtCard as varchar) DocNum ");
        sb.append("     , cast(d.ItmsGrpNam as varchar) grpName ");
        sb.append("     , cast(t.Name as varchar) imp ");
        sb.append("     , cast(sum(p1.Price * det.Quantity) as numeric(11,2))*-1 base ");
        sb.append("     , cast(sum(p2.Price * det.Quantity) as numeric(11,2))*-1 despuesImpuesto ");
        sb.append("from   ORIN enc ");
        sb.append("inner join RIN1 det on det.DocEntry = enc.DocEntry ");
        sb.append("inner join OITM i on i.itemcode = det.itemcode ");
        sb.append("inner join OITB d on d.ItmsGrpCod = i.ItmsGrpCod ");
        sb.append("inner join ITM1 p1 on p1.ItemCode = i.ItemCode and p1.PriceList = 1 ");
        sb.append("inner join ITM1 p2 on p2.ItemCode = i.ItemCode and p2.PriceList = 2 ");
        sb.append("inner join OSTC t on t.Code = det.TaxCode ");
        sb.append("where  enc.Series = ");
        sb.append(serieNotaCredito);
        sb.append(" and    DATEPART(year,enc.docdate) = ");
        sb.append(year);
        sb.append(" and    DATEPART(month,enc.docdate) = ");
        sb.append(month);
        sb.append(" and    DATEPART(day,enc.docdate) = ");
        sb.append(day);
        if (idTurnoCaja != null) {
            sb.append(" and    enc.POSCashN = ");
            sb.append(idTurnoCaja);
        }
        sb.append(" group by enc.NumAtCard, d.ItmsGrpNam, t.Name ");
        sb.append(") VENTASNETAS ");
        sb.append("GROUP BY DocNum, grpName, imp ");
        //sb.append("HAVING SUM(base) > 0 AND SUM(despuesImpuesto) > 0 ");

        try {
            return (List<Object[]>) em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error consultando los datos de impuestos de la factura. ", e);
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Object[]> getInvoiceBinAllocations(String invoiceNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(bin.WhsCode as varchar(10)) whsCode, cast(bin.BinCode as varchar(228)), cast(det.ItemCode as varchar(20)) itemCode, cast(binLog.Quantity as int) quantity ");
        sb.append("from   OINV enc ");
        sb.append("inner join INV1 det on det.DocEntry = enc.DocEntry ");
        sb.append("inner join OILM msg on msg.DocEntry = enc.DocEntry and msg.TransType = 13 and msg.DocLineNum = det.LineNum ");
        sb.append("inner join OBTL binLog on binLog.MessageID = msg.MessageID ");
        sb.append("inner join OBIN bin on bin.AbsEntry = binLog.BinAbs ");
        sb.append("where enc.DocNum = '");
        sb.append(invoiceNumber);
        sb.append("' order by bin.WhsCode, bin.BinCode, det.ItemCode ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las ubicaciones de la factura. ", e);
            return new ArrayList<>();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public FacturaSAP findNoTransaction(Integer docEntry) {
        try {
            return find(docEntry);
        } catch (Exception e) {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public FacturaSAP findByDocNum(Integer docNum) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FacturaSAP> cq = cb.createQuery(FacturaSAP.class);
        Root<FacturaSAP> root = cq.from(FacturaSAP.class);

        cq.where(cb.equal(root.get("docNum"), docNum));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No fue posible ejecutar la consulta de factura por numero de documento. ", e);
        }
        return null;
    }

    public List<Object[]> listVoidableInvoices(Integer turnoPOS) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(DocNum as int) DocNum, cast(CardCode as varchar(13)) CardCode, cast(CardName as varchar(100)) CardName, ");
        sb.append("cast(DocTotal as int) DocTotal, cast(BeginStr as varchar(10)) Prefijo ");
        sb.append("from OINV ");
        sb.append("inner join NNM1 on NNM1.Series = OINV.Series and NNM1.Locked = 'N' ");
        sb.append("where cast(DocDate as date) = cast(GETDATE() as date) ");
        sb.append("and POSCashN = ");
        sb.append(turnoPOS);
        sb.append(" and DocNum not in ( ");
        sb.append("select NumAtCard ");
        sb.append("from ORIN ");
        sb.append("where cast(DocDate as date) = cast(GETDATE() as date) ");
        sb.append("and POSCashN = ");
        sb.append(turnoPOS);
        sb.append(") ");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No se pudieron consultar las facturas anulables. [" + sb.toString() + "]. ", e);
            return new ArrayList<>();
        }
    }

    public List<Object[]> getVoidedInvoiceInfo(String invoiceNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(enc.CardCode as varchar(15)) nit ");
        sb.append(", cast(enc.CardName as varchar(100)) nombreCliente ");
        sb.append(", cast(rec.CashSum as int) pagoEfectivo ");
        sb.append(", cast(concat(rec.DocNum,tarjRec.LineID) as varchar(15)) idPagoTarjeta ");
        sb.append(", cast(substring(tarj.CompanyId,1,charindex(' ', tarj.CompanyId, 1)-1) as varchar(40)) tipoTarjeta ");
        sb.append(", cast(isnull(tarjRec.CreditSum,0) as int) pagoTarjeta ");
        sb.append(", cast(detFac.ItemCode as varchar(20)) referencia ");
        sb.append(", cast(detFac.Dscription as varchar(60)) nombreProducto ");
        sb.append(", cast(precio.Price as int) precio ");
        sb.append(", isnull(cast(rec.NoDocSum as int)*-1,0) pagoConSaldo ");
        sb.append(", cast(concat(serie.BeginStr,SUBSTRING(cast(enc.docnum as varchar(10)),3,len(enc.docnum-2))) as varchar(10)) factura ");
        sb.append(", sum(cast(detFac.Quantity as int)) cantidad ");
        sb.append("from   OINV enc ");
        sb.append("inner join INV1 detFac on detFac.DocEntry = enc.DocEntry ");
        sb.append("left join RCT2 facRec on facRec.DocEntry = enc.DocEntry ");
        sb.append("left join ORCT rec on rec.DocNum = facRec.DocNum ");
        sb.append("inner join ITM1 precio on precio.ItemCode = detFac.ItemCode and precio.PriceList = 2 ");
        sb.append("inner join NNM1 serie on serie.Series = enc.Series ");
        sb.append("left join RCT3 tarjRec on tarjRec.DocNum = rec.DocNum ");
        sb.append("left join OCRC tarj on tarj.CreditCard = tarjRec.CreditCard ");
        sb.append("where  enc.DocNum = '");
        sb.append(invoiceNumber);
        sb.append("' group by enc.CardCode, enc.CardName, rec.CashSum, tarjRec.LineID, rec.DocNum, tarj.CompanyId, tarjRec.CreditSum, ");
        sb.append("detFac.ItemCode, detFac.Dscription, precio.Price, rec.NoDocSum, serie.BeginStr, enc.docnum ");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No fue posible consultar los datos de la anulacion para la factura " + invoiceNumber, e);
            return new ArrayList<>();
        }
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Object[]> consultarDatosAsientoConsignacionFactura(Long docEntry) {
        StringBuilder sb = new StringBuilder();
        sb.append("select getdate() DueDate, convert(varchar(50),e.Memo+'-AJCS') Memo, cast(e.Ref1 as varchar(20)) Ref1, cast(e.Ref2 as varchar(20)) Ref2, cast(e.Ref3 as varchar(20)) Ref3 ");
        sb.append(", 'AJCS' TransCode, ROW_NUMBER() OVER(ORDER BY da.StockPrice, d.ShortName ASC) -1 Line_ID ");
        sb.append(", cast(case when d.ShortName=da.BalInvntAc then da.U_Cuenta_DB_Ajuste ");
        sb.append("when d.ShortName=da.SaleCostAc then da.U_Cuenta_CR_Ajuste end as varchar(20)) ShortName ");
        sb.append(", convert(varchar(50),d.LineMemo+'-AJCS') LineMemo, cast(d.OcrCode2 as varchar(10)) OcrCode2 ");
        sb.append(", cast(d.Project as varchar(10)) Project, cast(case when d.ShortName=da.BalInvntAc then ef.CardCode ");
        sb.append("when d.ShortName=da.SaleCostAc then da.Proveedor end as varchar(20)) U_InfoCo01 ");
        sb.append(", case when d.ShortName=da.BalInvntAc then da.StockPrice else 0 end Debit ");
        sb.append(", case when d.ShortName=da.SaleCostAc then da.StockPrice else 0 end Credit ");
        sb.append(", da.DocEntry, cast(da.GLMethod as varchar(10)) GLMethod from OJDT e inner join JDT1 d on d.TransId=e.TransId ");
        sb.append("inner join (select df.DocEntry, k.GLMethod ");
        sb.append(", case k.GLMethod when 'W' then a.BalInvntAc when 'C' then g.BalInvntAc when 'L' then i.BalInvntAc else '' end BalInvntAc ");
        sb.append(", case k.GLMethod when 'W' then a.SaleCostAc when 'C' then g.SaleCostAc when 'L' then i.SaleCostAc else '' end SaleCostAc ");
        sb.append(", case k.GLMethod when 'W' then csW.U_Cuenta_DB_Ajuste when 'C' then csC.U_Cuenta_DB_Ajuste when 'L' then csL.U_Cuenta_DB_Ajuste else '' end U_Cuenta_DB_Ajuste ");
        sb.append(", case k.GLMethod when 'W' then csW.U_Cuenta_CR_Ajuste when 'C' then csC.U_Cuenta_CR_Ajuste when 'L' then csL.U_Cuenta_CR_Ajuste else '' end U_Cuenta_CR_Ajuste ");
        sb.append(", k.CardCode Proveedor, convert(int,sum(df.StockPrice*df.Quantity)) StockPrice from INV1 df ");
        sb.append("inner join OITM k on k.ItemCode=df.ItemCode left join OWHS a on a.U_Consignacion='S' AND a.WhsCode=df.WhsCode ");
        sb.append("left join OITB g on g.U_Consignacion='S' AND g.ItmsGrpCod=k.ItmsGrpCod ");
        sb.append("left join OITW i on k.U_Consignacion='S' AND i.ItemCode=df.ItemCode AND i.WhsCode=df.WhsCode ");
        sb.append("left join [@BARU_CONSIG_MCIA] csW on k.GLMethod='W' AND csW.U_GLMethod=k.GLMethod AND LEFT(a.WhsCode,len(csW.U_WhsCode))=csW.U_WhsCode ");
        sb.append("left join [@BARU_CONSIG_MCIA] csC on k.GLMethod='C' AND csC.U_GLMethod=k.GLMethod AND csC.U_ItmsGrpCod=g.ItmsGrpCod ");
        sb.append("left join [@BARU_CONSIG_MCIA] csL on k.GLMethod='L' AND csL.U_GLMethod=k.GLMethod AND csL.U_ItemCode=i.ItemCode ");
        sb.append("where (a.whscode is not null or g.ItmsGrpCod is not null or i.ItemCode is not null) group by df.DocEntry, k.GLMethod ");
        sb.append(", case k.GLMethod when 'W' then a.BalInvntAc when 'C' then g.BalInvntAc when 'L' then i.BalInvntAc else '' end ");
        sb.append(", case k.GLMethod when 'W' then a.SaleCostAc when 'C' then g.SaleCostAc when 'L' then i.SaleCostAc else '' end ");
        sb.append(", case k.GLMethod when 'W' then csW.U_Cuenta_DB_Ajuste when 'C' then csC.U_Cuenta_DB_Ajuste when 'L' then csL.U_Cuenta_DB_Ajuste else '' end ");
        sb.append(", case k.GLMethod when 'W' then csW.U_Cuenta_CR_Ajuste when 'C' then csC.U_Cuenta_CR_Ajuste when 'L' then csL.U_Cuenta_CR_Ajuste else '' end ");
        sb.append(", k.CardCode) da on da.docentry=e.CreatedBy and (da.BalInvntAc=d.ShortName or da.SaleCostAc=d.ShortName) ");
        sb.append("inner join OINV ef on ef.DocEntry=da.DocEntry left join (select TransId, Ref1 from OJDT  where TransType=30) valida on valida.Ref1=e.Ref1 ");
        sb.append("where e.TransType='13' and e.CreatedBy=");
        sb.append(docEntry);
        sb.append(" and isnull(valida.TransId,'') <>convert(varchar,e.TransId) order by da.StockPrice");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los datos de asiento para mercancia en consignacion. ", e);
            return new ArrayList<>();
        }
    }

    public List<Object[]> consultarDatosAsientoConsignacionNotaCredito(Long docEntry) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ef.DocDate RefDate, ef.DocDueDate DueDate, ef.TaxDate, cast(e.Memo+'-AJCS' as varchar(100)) Memo, cast(e.Ref1 as varchar(10)) Ref1, ");
        sb.append("cast(e.TransId as varchar(10)) Ref2, cast(e.Ref3 as varchar(10)) Ref3, 'AJCS' TransCode, ");
        sb.append("ROW_NUMBER() OVER(ORDER BY da.StockPrice, d.ShortName ASC) -1 Line_ID, ");
        sb.append("cast(case when d.ShortName=da.BalInvntAc then da.U_Cuenta_DB_Ajuste when d.ShortName=da.SaleCostAc then da.U_Cuenta_CR_Ajuste ");
        sb.append("end as varchar(20)) ShortName, cast(d.LineMemo+'-AJCS' as varchar(100)) LineMemo, cast(d.OcrCode2 as varchar(10)) OcrCode2, ");
        sb.append("cast(d.Project as varchar(10)) Project, cast(case when d.ShortName=da.BalInvntAc then ef.CardCode when d.ShortName=da.SaleCostAc ");
        sb.append("then da.Proveedor end as varchar(20)) U_InfoCo01, case when d.ShortName=da.BalInvntAc then da.StockPrice else 0 end Credit, ");
        sb.append("case when d.ShortName=da.SaleCostAc then da.StockPrice else 0 end Debit, da.DocEntry, cast(da.GLMethod as varchar(10)) GLMethod ");
        sb.append("from OJDT e inner join JDT1 d on d.TransId=e.TransId inner join (select df.DocEntry, k.GLMethod ");
        sb.append(", case k.GLMethod when 'W' then a.BalInvntAc when 'C' then g.BalInvntAc when 'L' then i.BalInvntAc else '' end BalInvntAc ");
        sb.append(", case k.GLMethod when 'W' then a.SaleCostAc when 'C' then g.SaleCostAc when 'L' then i.SaleCostAc else '' end SaleCostAc ");
        sb.append(", case k.GLMethod when 'W' then csW.U_Cuenta_DB_Ajuste when 'C' then csC.U_Cuenta_DB_Ajuste when 'L' then csL.U_Cuenta_DB_Ajuste else '' end U_Cuenta_DB_Ajuste ");
        sb.append(", case k.GLMethod when 'W' then csW.U_Cuenta_CR_Ajuste when 'C' then csC.U_Cuenta_CR_Ajuste when 'L' then csL.U_Cuenta_CR_Ajuste else '' end U_Cuenta_CR_Ajuste ");
        sb.append(", k.CardCode Proveedor, convert(int,sum(df.StockPrice*df.Quantity)) StockPrice from RIN1 df ");
        sb.append("inner join OITM k on k.ItemCode=df.ItemCode left join OWHS a on a.U_Consignacion='S' AND a.WhsCode=df.WhsCode ");
        sb.append("left join OITB g on g.U_Consignacion='S' AND g.ItmsGrpCod=k.ItmsGrpCod ");
        sb.append("left join OITW i on k.U_Consignacion='S' AND i.ItemCode=df.ItemCode AND i.WhsCode=df.WhsCode ");
        sb.append("left join [@BARU_CONSIG_MCIA] csW on k.GLMethod='W' AND csW.U_GLMethod=k.GLMethod AND LEFT(a.WhsCode,len(csW.U_WhsCode))=csW.U_WhsCode ");
        sb.append("left join [@BARU_CONSIG_MCIA] csC on k.GLMethod='C' AND csC.U_GLMethod=k.GLMethod AND csC.U_ItmsGrpCod=g.ItmsGrpCod ");
        sb.append("left join [@BARU_CONSIG_MCIA] csL on k.GLMethod='L' AND csL.U_GLMethod=k.GLMethod AND csL.U_ItemCode=i.ItemCode ");
        sb.append("where (a.whscode is not null or g.ItmsGrpCod is not null or i.ItemCode is not null) group by df.DocEntry, k.GLMethod ");
        sb.append(", case k.GLMethod when 'W' then a.BalInvntAc when 'C' then g.BalInvntAc when 'L' then i.BalInvntAc else '' end ");
        sb.append(", case k.GLMethod when 'W' then a.SaleCostAc when 'C' then g.SaleCostAc when 'L' then i.SaleCostAc else '' end ");
        sb.append(", case k.GLMethod when 'W' then csW.U_Cuenta_DB_Ajuste when 'C' then csC.U_Cuenta_DB_Ajuste when 'L' then csL.U_Cuenta_DB_Ajuste else '' end ");
        sb.append(", case k.GLMethod when 'W' then csW.U_Cuenta_CR_Ajuste when 'C' then csC.U_Cuenta_CR_Ajuste when 'L' then csL.U_Cuenta_CR_Ajuste else '' end ");
        sb.append(", k.CardCode) da on da.docentry=e.CreatedBy and (da.BalInvntAc=d.ShortName or da.SaleCostAc=d.ShortName) ");
        sb.append("inner join ORIN ef on ef.DocEntry=da.DocEntry left join (select TransId, Ref1, Ref2 from OJDT  where TransType=30) valida on valida.Ref1=e.Ref1 ");
        sb.append("where e.TransType='14' and e.CreatedBy =");
        sb.append(docEntry);
        sb.append("and isnull(valida.Ref2,'')<>convert(varchar,e.TransId) order by da.StockPrice");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los datos de asiento para mercancia en consignacion. ", e);
            return new ArrayList<>();
        }
    }

    public Integer findByDocNumWithPrefixAndItemCode(String docNum, String itemCode) {
        if (docNum == null || docNum.trim().isEmpty() || !docNum.contains("0")) {
            return null;
        }
        String prefix = docNum.substring(0, docNum.indexOf("0"));
        String number = docNum.substring(docNum.indexOf("0"), docNum.length());
        StringBuilder sb = new StringBuilder();
        sb.append("select distinct fac.DocNum ");
        sb.append("from OINV fac ");
        sb.append("inner join INV1 det on det.DocEntry = fac.DocEntry ");
        sb.append("inner join NNM1 serie on serie.ObjectCode = fac.ObjType and serie.Locked = 'N' ");
        sb.append("where fac.docnum like '%");
        sb.append(number);
        sb.append("' and serie.BeginStr = '");
        sb.append(prefix);
        sb.append("' and det.ItemCode = '");
        sb.append(itemCode);
        sb.append("'");
        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la factura. ", e);
            return null;
        }
    }

    public List<Object[]> findByPrestashopOrderId(String orderId) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(enc.DocDate as date) DocDate, cast(enc.DocEntry as int) docEntry, enc.Series, cast(enc.CardCode as varchar(15)) cardCode, ");
        sb.append("cast(enc.Comments as varchar(254)) comments, enc.GroupNum, cast(enc.U_Vendedor1 as varchar(60)) vendedor1, ");
        sb.append("cast(enc.U_Vendedor2 as varchar(60)) vendedor2, cast(enc.U_Vendedor3 as varchar(60)) vendedor3, cast(enc.U_Vendedor4 as varchar(60)) vendedor4, ");
        sb.append("cast(enc.U_Vendedor5 as varchar(60)) vendedor5, cast(enc.U_ComVend1 as numeric(3,2)) comVend1, cast(enc.U_ComVend2 as numeric(3,2)) comVend2, ");
        sb.append("cast(enc.U_ComVend3 as numeric(3,2)) comVend3, cast(enc.U_ComVend4 as numeric(3,2)) comVend4, cast(enc.U_ComVend5 as numeric(3,2)) comVend5, ");
        sb.append("cast(enc.U_WUID as varchar(100)) wuid, isnull(enc.POSCashN, 0) POSCashN, cast(enc.U_Origen as varchar(1)) origen, cast(det.LineNum as int) lineNum, cast(det.ItemCode as varchar(20)) itemCode, ");
        sb.append("det.Quantity, cast(det.WhsCode as varchar(6)) whsCode, cast(det.OcrCode2 as varchar(8)) ocrCode2, cast(det.OcrCode3 as varchar(8)) ocrCode3, ");
        sb.append("cast(det.OcrCode4 as varchar(8)) ocrCode4, cast(det.Project as varchar(20)) project, cast(det.U_EstadoP as varchar(1)) estado, bin.AbsEntry binAbs, ");
        sb.append("cast(bin.BinCode as varchar(228)) binCode, cast(binLog.Quantity as int) quantity, ");
        sb.append("(select top 1 Series from NNM1 where SeriesName = 'NC' and Locked = 'N') serie, ");
        sb.append("cast(det.price as int) price, cast(enc.DocNum as varchar(10)) invoiceNumber ");
        sb.append("from   OINV enc ");
        sb.append("inner join INV1 det on det.docentry = enc.docentry ");
        sb.append("inner join OILM msg on msg.DocEntry = enc.DocEntry and msg.TransType = 13 and msg.DocLineNum = det.LineNum ");
        sb.append("inner join OBTL binLog on binLog.MessageID = msg.MessageID ");
        sb.append("inner join OBIN bin on bin.AbsEntry = binLog.BinAbs ");
        sb.append("where enc.U_PrestashopID = '");
        sb.append(orderId);
        sb.append("'");
        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar los datos de la factura. ", e);
            return new ArrayList<>();
        }
    }

    public Long consultarDocEntryReciboCaja(String nroFactura) {
        StringBuilder sb = new StringBuilder();
        sb.append("select enc.DocEntry from ORCT enc ");
        sb.append("inner join RCT2 fac on fac.DocNum = enc.DocNum ");
        sb.append("inner join OINV inv on inv.DocEntry = fac.DocEntry ");
        sb.append("where inv.DocNum = '");
        sb.append(nroFactura);
        sb.append("'");
        try {
            return ((Integer) em.createNativeQuery(sb.toString()).getSingleResult()).longValue();
        } catch (NoResultException e) {
            CONSOLE.log(Level.WARNING, "No existe un recibo de caja para la factura {0}", nroFactura);
            return null;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No se pudo consultar el recibo de caja para la factura " + nroFactura, e);
            return null;
        }
    }

    public Long consultarDocEntryOrden(String nroFactura) {
        StringBuilder sb = new StringBuilder();
        sb.append("select DocEntry ");
        sb.append("from ORDR ");
        sb.append("where NumAtCard = '");
        sb.append(nroFactura);
        sb.append("' and DocStatus = 'O' ");
        try {
            return ((Integer) em.createNativeQuery(sb.toString()).getSingleResult()).longValue();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la orden de venta. ", e);
            return null;
        }
    }

    public Object[] getVoidedInvoiceRelatedDocuments(String invoiceNumber) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(dev.DocNum AS VARCHAR(20)) notaCredito, CAST(ord.DocNum AS VARCHAR(20)) orden, CAST(sal.DocNum AS VARCHAR(20)) salida ");
        sb.append("FROM   OINV fac ");
        sb.append("INNER  JOIN ORIN dev ON dev.NumAtCard = fac.DocNum ");
        sb.append("LEFT   JOIN ORDR ord ON ord.NumAtCard = fac.DocNum ");
        sb.append("LEFT   JOIN OIGE sal ON sal.Ref2 = CAST(fac.DocNum AS VARCHAR(20)) ");
        sb.append("WHERE  fac.DocNum = '");
        sb.append(invoiceNumber);
        sb.append("'");

        try {
            return (Object[]) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "No se pudieron consultar los documentos relacionados con la anulacion de la factura " + invoiceNumber, e);
            return null;
        }
    }

    public Integer consultarDocNumReciboCaja(String docEntry) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DocNum FROM ORCT WHERE DocEntry = ");
        sb.append(docEntry);
        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar el DocNum para el recibo de caja con DocEntry=" + docEntry, e);
            return null;
        }
    }

    public long obtenerTotalDatos(Integer asesor, String estado, String valor, Date fechaInicial, Date fechaFinal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FacturaSAP> factura = cq.from(FacturaSAP.class);

        if ((estado != null && !estado.isEmpty()) || (asesor != null && asesor != 0) || (fechaInicial != null && fechaFinal != null)
                || (valor != null && !valor.isEmpty())) {
            Predicate conjunction = cb.conjunction();
            Predicate disjunction = cb.disjunction();

            if (estado != null && !estado.isEmpty()) {
                conjunction.getExpressions().add(cb.equal(factura.get(FacturaSAP_.docStatus), estado.charAt(0)));
            }
            if (asesor != null && asesor != 0) {
                conjunction.getExpressions().add(cb.equal(factura.get(FacturaSAP_.slpCode), asesor));
            }
            if (fechaInicial != null && fechaFinal != null) {
                conjunction.getExpressions().add(cb.greaterThanOrEqualTo(factura.get(FacturaSAP_.docDate), fechaInicial));
                conjunction.getExpressions().add(cb.lessThanOrEqualTo(factura.get(FacturaSAP_.docDate), fechaFinal));
            }
            if (valor != null && !valor.isEmpty()) {
                conjunction.getExpressions().add(cb.like(factura.get(FacturaSAP_.cardCode), "%" + valor + "%"));
                disjunction.getExpressions().add(cb.like(factura.get(FacturaSAP_.cardName), "%" + valor + "%"));
            }

            cq.where(conjunction);
        }

        cq.select(cb.count(factura));

        try {
            return em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return 0;
        }
    }

    public List<FacturaSAP> obtenerFacturas(int pageNum, int pageSize, Integer asesor, String estado, String valor, Date fechaInicial, Date fechaFinal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FacturaSAP> cq = cb.createQuery(FacturaSAP.class);
        Root<FacturaSAP> factura = cq.from(FacturaSAP.class);

        if ((estado != null && !estado.isEmpty()) || (asesor != null && asesor != 0) || (fechaInicial != null && fechaFinal != null)
                || (valor != null && !valor.isEmpty())) {
            Predicate conjunction = cb.conjunction();
            Predicate disjunction = cb.disjunction();

            if (estado != null && !estado.isEmpty()) {
                conjunction.getExpressions().add(cb.equal(factura.get(FacturaSAP_.docStatus), estado.charAt(0)));
            }
            if (asesor != null && asesor != 0) {
                conjunction.getExpressions().add(cb.equal(factura.get(FacturaSAP_.slpCode), asesor));
            }
            if (fechaInicial != null && fechaFinal != null) {
                conjunction.getExpressions().add(cb.greaterThanOrEqualTo(factura.get(FacturaSAP_.docDate), fechaInicial));
                conjunction.getExpressions().add(cb.lessThanOrEqualTo(factura.get(FacturaSAP_.docDate), fechaFinal));
            }
            if (valor != null && !valor.isEmpty()) {
                conjunction.getExpressions().add(cb.like(factura.get(FacturaSAP_.cardCode), "%" + valor + "%"));
                disjunction.getExpressions().add(cb.like(factura.get(FacturaSAP_.cardName), "%" + valor + "%"));
            }

            cq.where(conjunction);
        }

        cq.orderBy(cb.desc(factura.get(FacturaSAP_.docEntry)));

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

    public List<Integer> obtenerAsesoresFacturas(String estado, String valor, Date fechaInicial, Date fechaFinal) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(INT, asesor.SlpCode) AS slpCode ");
        sb.append("FROM   OINV factura ");
        sb.append("INNER  JOIN OSLP asesor ON asesor.SlpCode = factura.SlpCode ");

        if ((estado != null && !estado.isEmpty()) || (fechaInicial != null && fechaFinal != null) || (valor != null && !valor.isEmpty())) {
            StringBuilder sb2 = new StringBuilder();

            if (estado != null && !estado.isEmpty()) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("factura.DocStatus = '");
                sb2.append(estado);
                sb2.append("' ");
            }
            if (fechaInicial != null && fechaFinal != null) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("factura.DocDate >= '");
                sb2.append(new SimpleDateFormat("yyyy-dd-MM HH:mm:sss").format(fechaInicial));
                sb2.append("' AND ");
                sb2.append("factura.DocDate <= '");
                sb2.append(new SimpleDateFormat("yyyy-dd-MM HH:mm:sss").format(fechaFinal));
                sb2.append("' ");
            }
            if (valor != null && !valor.isEmpty()) {
                if (!sb2.toString().contains("WHERE")) {
                    sb2.append("WHERE ");
                } else {
                    sb2.append("AND ");
                }
                sb2.append("factura.CardCode LIKE '");
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

    public List<Object[]> consultarDatosFactura(String nroFactura) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cast(enc.DocDate as date) DocDate, cast(enc.DocEntry as int) docEntry, enc.Series, cast(enc.CardCode as varchar(15)) cardCode, ");
        sb.append("cast(enc.Comments as varchar(254)) comments, enc.GroupNum, cast(enc.U_Vendedor1 as varchar(60)) vendedor1, ");
        sb.append("cast(enc.U_Vendedor2 as varchar(60)) vendedor2, cast(enc.U_Vendedor3 as varchar(60)) vendedor3, cast(enc.U_Vendedor4 as varchar(60)) vendedor4, ");
        sb.append("cast(enc.U_Vendedor5 as varchar(60)) vendedor5, cast(enc.U_ComVend1 as numeric(3,2)) comVend1, cast(enc.U_ComVend2 as numeric(3,2)) comVend2, ");
        sb.append("cast(enc.U_ComVend3 as numeric(3,2)) comVend3, cast(enc.U_ComVend4 as numeric(3,2)) comVend4, cast(enc.U_ComVend5 as numeric(3,2)) comVend5, ");
        sb.append("cast(enc.U_WUID as varchar(100)) wuid, enc.POSCashN, cast(enc.U_Origen as varchar(1)) origen, cast(det.LineNum as int) lineNum, cast(det.ItemCode as varchar(20)) itemCode, ");
        sb.append("det.Quantity, cast(det.WhsCode as varchar(6)) whsCode, cast(det.OcrCode2 as varchar(8)) ocrCode2, cast(det.OcrCode3 as varchar(8)) ocrCode3, ");
        sb.append("cast(det.OcrCode4 as varchar(8)) ocrCode4, cast(det.Project as varchar(20)) project, cast(det.U_EstadoP as varchar(1)) estado, bin.AbsEntry binAbs, ");
        sb.append("cast(bin.BinCode as varchar(228)) binCode, cast(binLog.Quantity as int) quantity, ");
        sb.append("(select top 1 Series from NNM1 where SeriesName = 'NC' and Locked = 'N') serie, ");
        sb.append("cast(det.price as int) price, cast(det.DiscPrcnt as numeric(5,2)) disPerc ");
        sb.append("from   OINV enc ");
        sb.append("inner join INV1 det on det.docentry = enc.docentry ");
        sb.append("inner join OILM msg on msg.DocEntry = enc.DocEntry and msg.TransType = 13 and msg.DocLineNum = det.LineNum ");
        sb.append("inner join OBTL binLog on binLog.MessageID = msg.MessageID ");
        sb.append("inner join OBIN bin on bin.AbsEntry = binLog.BinAbs ");
        sb.append("where enc.DocNum = ");
        sb.append(nroFactura);

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la factura. ", e);
        }

        return null;
    }

    public List<Object[]> obtenerDocumentosAsesorComisionar(String slpName, Date fechaInicio, Date fechaFin, Date fechaFinMaxima) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(DATE, Fecha) AS Fecha, CONVERT(INT, t1.DocNum) AS DocNum, CONVERT(NUMERIC(18, 2), t1.Comision) AS Comision, ");
        sb.append("	  CONVERT(VARCHAR(20), t1.ItemCode) AS ItemCode, CONVERT(INT, t1.Quantity) AS Quantity, CONVERT(NUMERIC(5, 2), t1.VatPrcnt) AS VatPrcnt, ");
        sb.append("	  CONVERT(NUMERIC(18, 2), t1.PorcentajeComision) AS PorcentajeComision, CASE WHEN T1.SumApplied < 0 THEN 0 ELSE T1.SumApplied END AS TotalFv, ");
        sb.append("	  CONVERT(VARCHAR(2), tipo) AS Tipo, ");
        sb.append("	  CONVERT(NUMERIC(18, 2), CASE WHEN PorcentajeComision = 0.00 THEN TotalDetalle ELSE (TotalDetalle) * PorcentajeComision END) AS totalDetalle, ");
        sb.append("	  U_TipoNota ");
        sb.append("FROM   ( ");
        sb.append("SELECT CONVERT(DATE, factura.DocDate) AS Fecha, CONVERT(INT, factura.DocNum) AS DocNum, ");
        sb.append("	  CONVERT(NUMERIC(18, 2), (((detalle.Price * Quantity) * ((100 - ISNULL(factura.DiscPrcnt, 0)) / 100))) * ");
        sb.append("	  (CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor1 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend1 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor2 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend2 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor3 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend3 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor4 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend4 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor5 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend5 ELSE '0' END))) AS Comision, ");
        sb.append("	  CONVERT(VARCHAR(20), detalle.ItemCode) AS ItemCode, CONVERT(INT, detalle.Quantity) AS Quantity, CONVERT(NUMERIC(5, 2), detalle.VatPrcnt) AS VatPrcnt, ");
        sb.append("	  (CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor1 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend1 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor2 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend2 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor3 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend3 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor4 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend4 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor5 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend5 ELSE '0' END)) AS PorcentajeComision, ");
        sb.append("       CONVERT(NUMERIC(18, 2), factura.DocTotal - factura.VatSum) AS SumApplied, ");
        sb.append("       CONVERT(NUMERIC(18, 2), detalle.Price * Quantity) AS TotalDetalle, ");
        sb.append("       CONVERT(VARCHAR(2), 'FV') AS Tipo, ");
        sb.append("	  factura.U_TipoNota, ");
        sb.append("	  detalle.LineNum, ");
        sb.append("	  factura.DocNum AS NumAtCard ");
        sb.append("FROM   INV1 detalle ");
        sb.append("INNER  JOIN OINV factura ON factura.DocEntry = detalle.DocEntry ");
        sb.append("WHERE  (U_Vendedor1 = '");
        sb.append(slpName);
        sb.append("'");
        sb.append("	   OR U_Vendedor2 = '");
        sb.append(slpName);
        sb.append("'");
        sb.append("	   OR U_Vendedor3 = '");
        sb.append(slpName);
        sb.append("'");
        sb.append("	   OR U_Vendedor4 = '");
        sb.append(slpName);
        sb.append("'");
        sb.append("	   OR U_Vendedor5 = '");
        sb.append(slpName);
        sb.append("') ");
        //sb.append("AND    factura.DocStatus = 'C' ");
        if (fechaInicio != null && fechaFin != null) {
            sb.append("AND    factura.DocDate BETWEEN '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaInicio));
            sb.append("' AND '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaFin));
            sb.append("' ");
        }
        sb.append("UNION  ALL ");
        sb.append("SELECT CONVERT(DATE, devolucion.DocDate) AS Fecha, ");
        sb.append("       CONVERT(INT, devolucion.DocNum) AS DocNum, ");
        sb.append("       CONVERT(NUMERIC(18, 2), devolucion.DocTotal) AS Total, ");
        sb.append("       CONVERT(VARCHAR(20), ItemCode) AS ItemCode, ");
        sb.append("       CONVERT(INT, detalle.Quantity) AS Quantity, ");
        sb.append("       CONVERT(NUMERIC(5, 2), detalle.VatPrcnt) AS VatPrcnt, ");
        sb.append("	  (CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor1 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend1 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor2 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend2 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor3 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend3 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor4 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend4 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor5 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend5 ELSE '0' END)) AS PorcentajeComision, ");
        sb.append("       CONVERT(NUMERIC(18, 2), devolucion.DocTotal - devolucion.VatSum) AS SumApplied, ");
        sb.append("       CONVERT(NUMERIC(18, 2), detalle.Price * detalle.Quantity) AS TotalDetalle, ");
        sb.append("       CONVERT(VARCHAR(2), 'DV') AS Tipo, ");
        sb.append("       devolucion.U_TipoNota, ");
        sb.append("	  detalle.LineNum, ");
        sb.append("	  devolucion.NumAtCard ");
        sb.append("FROM   RIN1 detalle ");
        sb.append("INNER  JOIN ORIN devolucion ON devolucion.DocEntry = detalle.DocEntry ");
        sb.append("WHERE  (U_Vendedor1 = '");
        sb.append(slpName);
        sb.append("' ");
        sb.append("	   OR U_Vendedor2 = '");
        sb.append(slpName);
        sb.append("' ");
        sb.append("	   OR U_Vendedor3 = '");
        sb.append(slpName);
        sb.append("' ");
        sb.append("	   OR U_Vendedor4 = '");
        sb.append(slpName);
        sb.append("' ");
        sb.append("	   OR U_Vendedor5 = '");
        sb.append(slpName);
        sb.append("') ");
        //sb.append("AND    devolucion.DocStatus = 'C' ");
        sb.append("AND    devolucion.DocType = 'I' ");
        sb.append("AND    devolucion.CANCELED = 'N' ");
        if (fechaInicio != null && fechaFin != null) {
            sb.append("AND    devolucion.DocDate BETWEEN '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaInicio));
            sb.append("' AND '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaFinMaxima));
            sb.append("' AND    devolucion.NumAtCard IN (SELECT fac.DocNum FROM OINV fac WHERE fac.DocDate <= '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaFin));
            sb.append("') ");
        }
        sb.append("UNION  ALL ");
        sb.append("SELECT CONVERT(DATE, factura.DocDate) AS Fecha, CONVERT(INT, factura.DocNum) AS DocNum, ");
        sb.append("	  CONVERT(NUMERIC(18, 2), (((detalle.Price * Quantity) * ((100 - ISNULL(factura.DiscPrcnt, 0)) / 100))) * ");
        sb.append("	  (CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor1 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend1 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor2 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend2 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor3 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend3 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor4 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend4 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor5 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend5 ELSE '0' END))) AS Comision, ");
        sb.append("	  CONVERT(VARCHAR(20), detalle.ItemCode) AS ItemCode, CONVERT(INT, detalle.Quantity) AS Quantity, CONVERT(NUMERIC(5, 2), detalle.VatPrcnt) AS VatPrcnt, ");
        sb.append("	  (CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor1 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend1 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor2 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend2 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor3 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend3 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor4 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend4 ELSE '0' END) + ");
        sb.append("	   CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor5 = '");
        sb.append(slpName);
        sb.append("' THEN U_ComVend5 ELSE '0' END)) AS PorcentajeComision, ");
        sb.append("       CONVERT(NUMERIC(18, 2), factura.DocTotal - factura.VatSum) AS SumApplied, ");
        sb.append("       CONVERT(NUMERIC(18, 2), detalle.Price * Quantity) AS TotalDetalle, ");
        sb.append("       CONVERT(VARCHAR(2), 'FV') AS Tipo, ");
        sb.append("	  factura.U_TipoNota, ");
        sb.append("	  detalle.LineNum, ");
        sb.append("	  factura.DocNum AS NumAtCard ");
        sb.append("FROM   INV1 detalle ");
        sb.append("INNER  JOIN OINV factura ON factura.DocEntry = detalle.DocEntry ");
        sb.append("LEFT   JOIN RCT2 recFac ON recFac.DocEntry = factura.DocEntry ");
        sb.append("LEFT   JOIN ORCT rec ON (rec.DocNum = recFac.DocNum OR rec.DocNum = factura.ReceiptNum) AND rec.Canceled = 'N' AND factura.CardCode = rec.CardCode ");
        sb.append("LEFT   JOIN (SELECT reconEnc.ReconNum, reconDet.ReconSum, reconDet.SrcObjAbs, reconDet.SrcObjTyp, reconEnc.ReconDate ");
        sb.append("		FROM   ITR1 reconDet ");
        sb.append("		INNER  JOIN OITR reconEnc ON reconEnc.ReconNum = reconDet.ReconNum ");
        sb.append("		WHERE  reconDet.SrcObjTyp = '13' ");
        sb.append("		AND    reconEnc.ReconType = 0 ");
        sb.append("		AND    reconEnc.InitObjTyp IS NULL ");
        sb.append("		AND    reconEnc.Canceled = 'N') AS recon ON recon.SrcObjAbs = factura.DocEntry ");
        sb.append("WHERE  (U_Vendedor1 = '");
        sb.append(slpName);
        sb.append("' ");
        sb.append("	   OR U_Vendedor2 = '");
        sb.append(slpName);
        sb.append("' ");
        sb.append("	   OR U_Vendedor3 = '");
        sb.append(slpName);
        sb.append("' ");
        sb.append("	   OR U_Vendedor4 = '");
        sb.append(slpName);
        sb.append("' ");
        sb.append("	   OR U_Vendedor5 = '");
        sb.append(slpName);
        sb.append("') ");
        if (fechaInicio != null && fechaFin != null) {
            sb.append("AND    (rec.DocDate BETWEEN '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaInicio));
            sb.append("' AND '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaFin));
            sb.append("'  OR recon.ReconDate BETWEEN '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaInicio));
            sb.append("' AND '");
            sb.append(new SimpleDateFormat("yyyy-dd-MM").format(fechaFin));
            sb.append("')");
        }
        sb.append(") T1 ");
        sb.append("GROUP  BY Fecha, DocNum, Comision, ItemCode, Quantity, VatPrcnt, PorcentajeComision, TotalDetalle, Tipo, LineNum, U_TipoNota, NumAtCard, SumApplied ");
        sb.append("ORDER  BY Fecha, NumAtCard, Tipo DESC, DocNum, Comision, ItemCode, Quantity, VatPrcnt, PorcentajeComision, TotalDetalle, LineNum, U_TipoNota ");

        CONSOLE.log(Level.INFO, sb.toString());

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Object[]> consultarDatosFacturaAnulacion(String numeroFactura) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT enc.DocNum, ");
        sb.append("       CAST(enc.DocDate AS date) DocDate, ");
        sb.append("       CAST(enc.DocEntry AS INT) docEntry, ");
        sb.append("       enc.Series, ");
        sb.append("       CAST(enc.CardCode AS VARCHAR(15)) cardCode, ");
        sb.append("       CAST(enc.Comments AS VARCHAR(254)) comments, ");
        sb.append("       enc.GroupNum, ");
        sb.append("       CAST(enc.U_Vendedor1 AS VARCHAR(60)) vendedor1, ");
        sb.append("       CAST(enc.U_Vendedor2 AS VARCHAR(60)) vendedor2, ");
        sb.append("       CAST(enc.U_Vendedor3 AS VARCHAR(60)) vendedor3, ");
        sb.append("       CAST(enc.U_Vendedor4 AS VARCHAR(60)) vendedor4, ");
        sb.append("       CAST(enc.U_Vendedor5 AS VARCHAR(60)) vendedor5, ");
        sb.append("       CAST(enc.U_ComVend1 AS NUMERIC(3,2)) comVend1, ");
        sb.append("       CAST(enc.U_ComVend2 AS NUMERIC(3,2)) comVend2, ");
        sb.append("       CAST(enc.U_ComVend3 AS NUMERIC(3,2)) comVend3, ");
        sb.append("       CAST(enc.U_ComVend4 AS NUMERIC(3,2)) comVend4, ");
        sb.append("       CAST(enc.U_ComVend5 AS NUMERIC(3,2)) comVend5, ");
        sb.append("       CAST(enc.U_WUID AS VARCHAR(100)) wuid, ");
        sb.append("       enc.POSCashN, ");
        sb.append("       CAST(enc.U_Origen AS VARCHAR(1)) origen, ");
        sb.append("       CAST(det.LineNum AS INT) lineNum, ");
        sb.append("       CAST(det.ItemCode AS VARCHAR(20)) itemCode, ");
        sb.append("       det.Quantity, ");
        sb.append("       CAST(det.WhsCode AS VARCHAR(6)) whsCode, ");
        sb.append("       CAST(det.OcrCode2 AS VARCHAR(8)) ocrCode2, ");
        sb.append("       CAST(det.OcrCode3 AS VARCHAR(8)) ocrCode3, ");
        sb.append("       CAST(det.OcrCode4 AS VARCHAR(8)) ocrCode4, ");
        sb.append("       CAST(det.Project AS VARCHAR(20)) project, ");
        sb.append("       CAST(det.U_EstadoP AS VARCHAR(1)) estado, ");
        sb.append("       bin.AbsEntry binAbs, ");
        sb.append("       CAST(bin.BinCode AS VARCHAR(228)) binCode, ");
        sb.append("       CAST(binLog.Quantity AS INT) quantity, ");
        sb.append("       (SELECT TOP 1 Series ");
        sb.append("        FROM   NNM1 ");
        sb.append("        WHERE  SeriesName = 'NC' ");
        sb.append("        AND    Locked = 'N') serie, ");
        sb.append("       CAST(det.price AS INT) price, ");
        sb.append("       CAST(det.DiscPrcnt AS NUMERIC(5,2)) disPerc, ");
        sb.append("       CAST(det.priceAfvat AS NUMERIC(18,2)) priceAfVat, ");
        sb.append("       CAST(det.taxcode AS VARCHAR(50)) taxCode ");
        sb.append("FROM   OINV enc ");
        sb.append("INNER  JOIN INV1 det ON det.docentry = enc.docentry ");
        sb.append("INNER  JOIN OILM msg ON msg.DocEntry = enc.DocEntry AND msg.TransType = 13 AND msg.DocLineNum = det.LineNum ");
        sb.append("INNER  JOIN OBTL binLog ON binLog.MessageID = msg.MessageID ");
        sb.append("INNER  JOIN OBIN bin ON bin.AbsEntry = binLog.BinAbs ");
        sb.append("WHERE  enc.DocNum = ");
        sb.append(numeroFactura);

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar la factura. ", e);
            return null;
        }
    }

    public List<Integer> obtenerFacturasPendientes(String documentoCliente) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CONVERT(INT, DocNum) AS DocNum ");
        sb.append("FROM   OINV f ");
        sb.append("INNER  JOIN INV1 df ON df.DocEntry = f.DocEntry ");
        sb.append("WHERE  DocType <> 'S' ");
        sb.append("AND    df.U_EstadoP NOT IN ('DEMO','D') ");
        sb.append("AND    CONVERT(VARCHAR, f.DocNum) IN (SELECT DISTINCT CONVERT(VARCHAR, NumAtCard) AS NumAtCard ");
        sb.append("                                      FROM   ORDR ov ");
        sb.append("                                      INNER  JOIN RDR1 dov ON dov.DocEntry = ov.DocEntry AND dov.LineStatus <> 'C'  ");
        sb.append("                                      WHERE  DocType <> 'S' ");
        sb.append("                                      AND    ov.DocStatus <> 'C') ");
        sb.append("AND    CONVERT(VARCHAR, f.DocNum) NOT IN (SELECT DISTINCT CONVERT(VARCHAR, NumAtCard) AS NumAtCard ");
        sb.append("                                          FROM   ORIN nc ");
        sb.append("                                          WHERE  DocType <> 'S') ");
        sb.append("AND    CONVERT(VARCHAR, f.DocNum) NOT IN (SELECT DISTINCT CONVERT(VARCHAR, den.U_NWR_Base) AS U_NWR_Base ");
        sb.append("                                          FROM   ODLN en ");
        sb.append("                                          INNER  JOIN DLN1 den ON den.DocEntry = en.DocEntry ");
        sb.append("                                          WHERE  DocType <> 'S' ");
        sb.append("                                          AND    CONVERT(VARCHAR, U_NWR_Base) NOT LIKE '[B-SF]%') ");
        sb.append("AND    f.cardcode = '");
        sb.append(documentoCliente);
        sb.append("' ORDER BY DocNum ASC");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Object[]> consultarInformacionParaTirilla(String docNum) {
        List<Object[]> result = new ArrayList<>();
        return result;
    }

    public List<Object[]> obtenerPagosFactura(Integer docNum) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT T1.InvoiceNumber, T1.InvoiceTotal, T1.ReceiptDate, T1.ReceiptNumber, T1.ReceiptTotal, T1.PaidCash, T1.Retenciones, T1.OthersReceipts, ");
        sb.append("       T1.CreditNotes, T1.Cruces, T1.Egresos, T1.FechaCruceEgresos, T1.PaidCheck, SUM(T1.PaidCreditCard) AS PaidCreditCard, ");
        sb.append("       T1.PaidInternalRecon, T1.PaidBalance, T1.DateInternalRecon, T1.AjustePeso ");
        sb.append("FROM (SELECT fac.DocNum AS InvoiceNumber, ");
        sb.append("             fac.DocTotal AS InvoiceTotal, ");
        sb.append("             rec.DocDate AS ReceiptDate, ");
        sb.append("             rec.DocNum AS ReceiptNumber, ");
        sb.append("             rec.DocTotal + fac.WTSum AS ReceiptTotal,");
        sb.append("             ISNULL(rec.CashSum, 0.0) AS PaidCash, ");
        sb.append("             ISNULL(fac.WTSum, 0.0) AS Retenciones, ");
        sb.append("             ISNULL((SELECT SUM(SumApplied) * -1 FROM RCT2 WHERE DocNum = rec.DocNum AND InvType IN (24, 30)), 0.0) AS OthersReceipts, ");
        sb.append("             ISNULL((SELECT SUM(SumApplied) FROM RCT2 WHERE DocNum = rec.DocNum AND InvType = 14), 0.0) AS CreditNotes, ");
        sb.append("             ISNULL(rec.TrsfrSum, 0.0) AS Cruces, ");
        sb.append("             ISNULL((SELECT SUM(SumApplied) FROM VPM2 WHERE DocEntry = fac.DocEntry AND InvType = 13), 0.0) as Egresos, ");
        sb.append("             (SELECT MAX(enc.DocDate) FROM VPM2 det INNER JOIN OVPM enc ON enc.DocNum = det.DocNum WHERE det.DocEntry = fac.DocEntry AND InvType = 13) AS FechaCruceEgresos, ");
        sb.append("             ISNULL(SUM(cheq.CheckSum), 0.0) AS PaidCheck, ");
        sb.append("             ISNULL(/*SUM(*/tarj.CreditSum/*)*/, 0.0) AS PaidCreditCard, ");
        sb.append("             ISNULL(recon.ReconSum, 0.0) AS PaidInternalRecon, ");
        sb.append("             ISNULL(rec.NoDocSum * -1, 0.0) AS PaidBalance, ");
        sb.append("             recon.ReconDate AS DateInternalRecon, ");
        sb.append("             ISNULL(fac.RoundDif, 0.0) AS AjustePeso ");
        sb.append("      FROM   OINV fac ");
        sb.append("      LEFT   JOIN RCT2 recFac ON recFac.DocEntry = fac.DocEntry ");
        sb.append("      LEFT   JOIN ORCT rec ON (rec.DocNum = recFac.DocNum OR rec.DocNum = fac.ReceiptNum) AND rec.Canceled = 'N' AND fac.CardCode = rec.CardCode ");
        sb.append("      LEFT   JOIN RCT1 cheq ON cheq.DocNum = rec.DocNum ");
        sb.append("      LEFT   JOIN RCT3 tarj ON tarj.DocNum = rec.DocNum ");
        sb.append("      LEFT   JOIN (SELECT reconEnc.ReconNum, reconDet.ReconSum, reconDet.SrcObjAbs, reconDet.SrcObjTyp, reconEnc.ReconDate ");
        sb.append("                   FROM   ITR1 reconDet ");
        sb.append("                   INNER  JOIN OITR reconEnc ON reconEnc.ReconNum = reconDet.ReconNum ");
        sb.append("                   WHERE  reconDet.SrcObjTyp = '13' ");
        sb.append("                   AND    reconEnc.ReconType = 0 ");
        sb.append("                   AND    reconEnc.InitObjTyp IS NULL ");
        sb.append("                   AND    reconEnc.Canceled = 'N' ");
        sb.append("                   AND    reconDet.SrcObjAbs IN (SELECT docentry ");
        sb.append("                                                 FROM   OINV ");
        sb.append("                                                 WHERE  docnum = '");
        sb.append(docNum);
        sb.append("')) AS recon ON recon.SrcObjAbs = fac.DocEntry ");
        sb.append("WHERE  fac.docnum = '");
        sb.append(docNum);
        sb.append("' ");
        sb.append("     GROUP  BY fac.DocNum, fac.DocTotal, rec.DocNum, rec.DocTotal, rec.DocDate, rec.NoDocSum, rec.CashSum, tarj.CreditSum, recon.ReconSum, fac.DocEntry, rec.TrsfrSum, fac.RoundDif, ");
        sb.append("            recon.ReconDate, fac.WTSum) AS T1 ");
        sb.append("GROUP  BY t1.InvoiceNumber, InvoiceTotal, t1.ReceiptDate, t1.ReceiptNumber, t1.ReceiptTotal, t1.PaidCash, t1.Retenciones, T1.OthersReceipts, ");
        sb.append("T1.CreditNotes, T1.Cruces, T1.Egresos, T1.FechaCruceEgresos, T1.PaidCheck, T1.PaidInternalRecon, T1.PaidBalance, T1.DateInternalRecon, T1.AjustePeso ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Object[]> obtenerPagosFacturaV2(Integer docNum) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT T1.InvoiceNumber, T1.InvoiceTotal, T1.ReceiptDate, T1.ReceiptNumber, T1.ReceiptTotal, T1.PaidCash, T1.Retenciones, T1.OthersReceipts, ");
        sb.append("       T1.CreditNotes, T1.Cruces, T1.Egresos, T1.FechaCruceEgresos, T1.PaidCheck, SUM(T1.PaidCreditCard) AS PaidCreditCard, ");
        sb.append("       T1.PaidInternalRecon, T1.PaidBalance, T1.DateInternalRecon, T1.AjustePeso, T1.SumApplied ");
        sb.append("FROM (SELECT fac.DocNum AS InvoiceNumber, ");
        sb.append("             fac.DocTotal AS InvoiceTotal, ");
        sb.append("             rec.DocDate AS ReceiptDate, ");
        sb.append("             rec.DocNum AS ReceiptNumber, ");
        sb.append("             rec.DocTotal + fac.WTSum AS ReceiptTotal, ");
        sb.append("             recfac.SumApplied AS SumApplied, ");
        sb.append("             ISNULL(rec.CashSum, 0.0) AS PaidCash, ");
        sb.append("             ISNULL(fac.WTSum, 0.0) AS Retenciones, ");
        sb.append("             ISNULL((SELECT SUM(SumApplied) * -1 FROM RCT2 WHERE DocNum = rec.DocNum AND InvType IN (24, 30)), 0.0) AS OthersReceipts, ");
        sb.append("             ISNULL((SELECT SUM(SumApplied) FROM RCT2 WHERE DocNum = rec.DocNum AND InvType = 14), 0.0) AS CreditNotes, ");
        sb.append("             ISNULL(rec.TrsfrSum, 0.0) AS Cruces, ");
        sb.append("             ISNULL((SELECT SUM(SumApplied) FROM VPM2 WHERE DocEntry = fac.DocEntry AND InvType = 13), 0.0) as Egresos, ");
        sb.append("             (SELECT MAX(enc.DocDate) FROM VPM2 det INNER JOIN OVPM enc ON enc.DocNum = det.DocNum WHERE det.DocEntry = fac.DocEntry AND InvType = 13) AS FechaCruceEgresos, ");
        sb.append("             ISNULL(SUM(cheq.CheckSum), 0.0) AS PaidCheck, ");
        sb.append("             ISNULL(/*SUM(*/tarj.CreditSum/*)*/, 0.0) AS PaidCreditCard, ");
        sb.append("             ISNULL(recon.ReconSum, 0.0) AS PaidInternalRecon, ");
        sb.append("             ISNULL(rec.NoDocSum * -1, 0.0) AS PaidBalance, ");
        sb.append("             recon.ReconDate AS DateInternalRecon, ");
        sb.append("             ISNULL(fac.RoundDif, 0.0) AS AjustePeso ");
        sb.append("      FROM   OINV fac ");
        sb.append("      LEFT   JOIN RCT2 recFac ON recFac.DocEntry = fac.DocEntry ");
        sb.append("      INNER  JOIN ORCT rec ON (rec.DocNum = recFac.DocNum) AND rec.Canceled = 'N' ");
        sb.append("      LEFT   JOIN RCT1 cheq ON cheq.DocNum = rec.DocNum ");
        sb.append("      LEFT   JOIN RCT3 tarj ON tarj.DocNum = rec.DocNum ");
        sb.append("      LEFT   JOIN (SELECT reconEnc.ReconNum, reconDet.ReconSum, reconDet.SrcObjAbs, reconDet.SrcObjTyp, reconEnc.ReconDate ");
        sb.append("                   FROM   ITR1 reconDet ");
        sb.append("                   INNER  JOIN OITR reconEnc ON reconEnc.ReconNum = reconDet.ReconNum ");
        sb.append("                   WHERE  reconDet.SrcObjTyp = '13' ");
        sb.append("                   AND    reconEnc.ReconType = 0 ");
        sb.append("                   AND    reconEnc.InitObjTyp IS NULL ");
        sb.append("                   AND    reconEnc.Canceled = 'N' ");
        sb.append("                   AND    reconDet.SrcObjAbs IN (SELECT docentry ");
        sb.append("                                                 FROM   OINV ");
        sb.append("                                                 WHERE  docnum = '");
        sb.append(docNum);
        sb.append("')) AS recon ON recon.SrcObjAbs = fac.DocEntry ");
        sb.append("WHERE  fac.docnum = '");
        sb.append(docNum);
        sb.append("' ");
        sb.append("     GROUP  BY fac.DocNum, fac.DocTotal, rec.DocNum, rec.DocTotal, rec.DocDate, rec.NoDocSum, rec.CashSum, tarj.CreditSum, recon.ReconSum, fac.DocEntry, rec.TrsfrSum, fac.RoundDif, ");
        sb.append("            recon.ReconDate, fac.WTSum, recfac.SumApplied) AS T1 ");
        sb.append("GROUP  BY t1.InvoiceNumber, InvoiceTotal, t1.ReceiptDate, t1.ReceiptNumber, t1.ReceiptTotal, T1.SumApplied, t1.PaidCash, t1.Retenciones, T1.OthersReceipts, ");
        sb.append("T1.CreditNotes, T1.Cruces, T1.Egresos, T1.FechaCruceEgresos, T1.PaidCheck, T1.PaidInternalRecon, T1.PaidBalance, T1.DateInternalRecon, T1.AjustePeso ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public String obtenerPrefijoFactura(String docnum) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT cast(s.BeginStr as varchar) as BeginStr ");
        sb.append("FROM OINV f ");
        sb.append("INNER JOIN NNM1 s ON s.Series = f.Series ");
        sb.append("WHERE DocNum = ");
        sb.append(docnum);

        try {
            return (String) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public List<Object[]> obtenerReciboCaja(String nroFactura) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(enc.CashAcct AS VARCHAR(8)) cashAcct, CAST(enc.CashSum AS INT) cashSum, ");
        sb.append("       CAST(det.CreditSum AS INT) creditSum, CAST(det.CreditAcct AS VARCHAR(8)) creditCardAcct, ");
        sb.append("       CAST(det.CardValid AS DATE) cardValid, CAST(det.VoucherNum AS INT) voucherNum, ");
        sb.append("       CAST(det.CrTypeCode AS INT) crTypeCode, CAST(det.NumOfPmnts AS INT) numOfPayments, ");
        sb.append("       CAST(enc.CardCode AS VARCHAR(15)) cardCode, CAST(enc.NoDocSum AS INT) noDocSum, ");
        sb.append("       CAST(enc.DocTotal AS INT) docTotal, CAST(det.CreditCard AS INT) creditCard, ");
        sb.append("       CAST(dev.DocEntry AS INT) devDocEntry ");
        sb.append("FROM   OINV inv INNER JOIN RCT2 det2 ON det2.DocEntry = inv.DocEntry ");
        sb.append("INNER  JOIN ORIN dev ON dev.numatcard = inv.docnum ");
        sb.append("LEFT   JOIN ORCT enc ON enc.DocNum = det2.DocNum ");
        sb.append("LEFT   JOIN RCT3 det ON det.DocNum = enc.DocNum ");
        sb.append("WHERE  inv.DocNum = '");
        sb.append(nroFactura);
        sb.append("'");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "", e);
            return null;
        }
    }

    public Double obtenerCostoReferencia(Integer docEntry, String referencia, String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(NUMERIC(18, 2), GrossBuyPr) AS grossBuyPr ");
        sb.append("FROM   INV1 ");
        sb.append("WHERE  docentry = ");
        sb.append(docEntry);
        sb.append("AND    ItemCode = '");
        sb.append(referencia);
        sb.append("' ");
        sb.append("AND    WhsCode = '");
        sb.append(almacen);
        sb.append("' ");

        try {
            return ((BigDecimal) em.createNativeQuery(sb.toString()).getSingleResult()).doubleValue();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el costo del item. ", e);
        }
        return null;
    }

    public List<Object[]> validarDocumento(String tipo, String documento, String asesor) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(DATE, Fecha) AS Fecha, CONVERT(INT, t1.DocNum) AS DocNum, CONVERT(NUMERIC(18, 2), t1.Comision) AS Comision, ");
        sb.append("	  CONVERT(VARCHAR(20), t1.ItemCode) AS ItemCode, CONVERT(INT, t1.Quantity) AS Quantity, CONVERT(NUMERIC(5, 2), t1.VatPrcnt) AS VatPrcnt, ");
        sb.append("	  CONVERT(NUMERIC(18, 2), t1.PorcentajeComision) AS PorcentajeComision, CASE WHEN T1.SumApplied < 0 THEN 0 ELSE T1.SumApplied END AS TotalFv, ");
        sb.append("	  CONVERT(VARCHAR(2), tipo) AS Tipo, ");
        sb.append("	  CONVERT(NUMERIC(18, 2), CASE WHEN PorcentajeComision = 0.00 THEN TotalDetalle ELSE (TotalDetalle) * PorcentajeComision END) AS totalDetalle, ");
        sb.append("	  U_TipoNota ");
        sb.append("FROM   ( ");

        if (tipo.equals("FV")) {
            sb.append("SELECT CONVERT(DATE, factura.DocDate) AS Fecha, CONVERT(INT, factura.DocNum) AS DocNum, ");
            sb.append("	      CONVERT(NUMERIC(18, 2), (((detalle.Price * Quantity) * ((100 - ISNULL(factura.DiscPrcnt, 0)) / 100))) * ");
            sb.append("	      (CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor1 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend1 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor2 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend2 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor3 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend3 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor4 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend4 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor5 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend5 ELSE '0' END))) AS Comision, ");
            sb.append("	      CONVERT(VARCHAR(20), detalle.ItemCode) AS ItemCode, CONVERT(INT, detalle.Quantity) AS Quantity, CONVERT(NUMERIC(5, 2), detalle.VatPrcnt) AS VatPrcnt, ");
            sb.append("	      (CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor1 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend1 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor2 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend2 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor3 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend3 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor4 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend4 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor5 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend5 ELSE '0' END)) AS PorcentajeComision, ");
            sb.append("       CONVERT(NUMERIC(18, 2), factura.DocTotal - factura.VatSum) AS SumApplied, ");
            sb.append("       CONVERT(NUMERIC(18, 2), detalle.Price * Quantity) AS TotalDetalle, ");
            sb.append("       CONVERT(VARCHAR(2), 'FV') AS Tipo, ");
            sb.append("	      factura.U_TipoNota, ");
            sb.append("	      detalle.LineNum, ");
            sb.append("	      factura.DocNum AS NumAtCard ");
            sb.append("FROM   INV1 detalle ");
            sb.append("INNER  JOIN OINV factura ON factura.DocEntry = detalle.DocEntry ");
            sb.append("WHERE  DocNum = '");
            sb.append(documento);
            sb.append("'");
        } else if (tipo.equals("DV")) {
            sb.append("SELECT CONVERT(DATE, devolucion.DocDate) AS Fecha, ");
            sb.append("       CONVERT(INT, devolucion.DocNum) AS DocNum, ");
            sb.append("       CONVERT(NUMERIC(18, 2), devolucion.DocTotal) AS Comision, ");
            sb.append("       CONVERT(VARCHAR(20), ItemCode) AS ItemCode, ");
            sb.append("       CONVERT(INT, detalle.Quantity) AS Quantity, ");
            sb.append("       CONVERT(NUMERIC(5, 2), detalle.VatPrcnt) AS VatPrcnt, ");
            sb.append("	      (CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor1 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend1 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor2 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend2 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor3 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend3 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor4 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend4 ELSE '0' END) + ");
            sb.append("	       CONVERT(NUMERIC(18, 2), CASE WHEN U_Vendedor5 = '");
            sb.append(asesor);
            sb.append("' THEN U_ComVend5 ELSE '0' END)) AS PorcentajeComision, ");
            sb.append("       CONVERT(NUMERIC(18, 2), devolucion.DocTotal - devolucion.VatSum) AS SumApplied, ");
            sb.append("       CONVERT(NUMERIC(18, 2), detalle.Price * detalle.Quantity) AS TotalDetalle, ");
            sb.append("       CONVERT(VARCHAR(2), 'DV') AS Tipo, ");
            sb.append("       devolucion.U_TipoNota, ");
            sb.append("	      detalle.LineNum, ");
            sb.append("	      devolucion.NumAtCard ");
            sb.append("FROM   RIN1 detalle ");
            sb.append("INNER  JOIN ORIN devolucion ON devolucion.DocEntry = detalle.DocEntry ");
            sb.append("WHERE  DocNum = '");
            sb.append(documento);
            sb.append("' AND  devolucion.DocType = 'I' ");
            sb.append("AND    devolucion.CANCELED = 'N' ");
        }

        sb.append(") T1 ");
        sb.append("GROUP  BY Fecha, DocNum, Comision, ItemCode, Quantity, VatPrcnt, PorcentajeComision, TotalDetalle, Tipo, LineNum, U_TipoNota, NumAtCard, SumApplied ");
        sb.append("ORDER  BY Fecha, NumAtCard, Tipo DESC, DocNum, Comision, ItemCode, Quantity, VatPrcnt, PorcentajeComision, TotalDetalle, LineNum, U_TipoNota ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> obtenerVentasNetas(Integer startYear, Integer startMonth, Integer startDay, Integer endYear, Integer endMonth, Integer endDay) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT almacen, ");
        sb.append("       CONVERT(NUMERIC, SUM(ventas) - SUM(devuelto)) AS ventasnetas ");
        sb.append("FROM   ( ");
        sb.append("        SELECT fac.docdate, ");
        sb.append("               SUBSTRING(CONVERT(VARCHAR, fac.docnum), 1, 2) almacen, ");
        sb.append("               SUM(fac.DocTotal - fac.VatSum - fac.RoundDif) AS ventas, ");
        sb.append("               0 AS devuelto ");
        sb.append("        FROM   OINV fac ");
        sb.append("        WHERE  CONVERT(DATE, fac.DocDate) BETWEEN '");
        sb.append(startYear).append("-").append(startMonth).append("-").append(startDay).append("' ");
        sb.append("        AND '");
        sb.append(endYear).append("-").append(endMonth).append("-").append(endDay);
//        sb.append("        WHERE  YEAR(fac.DocDate) BETWEEN '");
//        sb.append(startYear);
//        sb.append("' AND '");
//        sb.append(endYear);
//        sb.append("'       AND    MONTH(fac.DocDate) BETWEEN '");
//        sb.append(startMonth);
//        sb.append("' AND '");
//        sb.append(endMonth);
//        sb.append("'       AND    DAY(fac.DocDate) BETWEEN '");
//        sb.append(startDay);
//        sb.append("' AND '");
//        sb.append(endDay);
        sb.append("'       AND    fac.DocNum NOT LIKE '2%' ");
        sb.append("        AND    fac.DocNum NOT LIKE '5%' ");
        sb.append("        AND    fac.DocType = 'I' ");
        sb.append("        AND    fac.DocSubType = '--' ");
        sb.append("        AND    DocNum NOT IN (SELECT U_DocNum ");
        sb.append("                              FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                              WHERE  U_TipoDocumento = 'Factura') ");
        sb.append("        GROUP  BY fac.docdate, SUBSTRING(CONVERT(VARCHAR, fac.docnum), 1, 2) ");
        sb.append("        UNION ");
        sb.append("        SELECT dev.docdate, ");
        sb.append("               SUBSTRING(CONVERT(VARCHAR, dev.NumAtCard), 1, 2) almacen, ");
        sb.append("               0 AS ventas, ");
        sb.append("               SUM(dev.DocTotal - dev.VatSum - dev.RoundDif) devuelto ");
        sb.append("        FROM   ORIN dev ");
        sb.append("        WHERE  CONVERT(DATE, dev.DocDate) BETWEEN '");
        sb.append(startYear).append("-").append(startMonth).append("-").append(startDay).append("' ");
        sb.append("        AND '");
        sb.append(endYear).append("-").append(endMonth).append("-").append(endDay);
//        sb.append("        WHERE  YEAR(dev.DocDate) BETWEEN '");
//        sb.append(startYear);
//        sb.append("' AND '");
//        sb.append(endYear);
//        sb.append("'       AND    MONTH(dev.DocDate) BETWEEN '");
//        sb.append(startMonth);
//        sb.append("' AND '");
//        sb.append(endMonth);
//        sb.append("'       AND    DAY(dev.DocDate) BETWEEN '");
//        sb.append(startDay);
//        sb.append("' AND '");
//        sb.append(endDay);
        sb.append("'       AND    dev.NumAtCard NOT LIKE '2%' ");
        sb.append("        AND    dev.NumAtCard NOT LIKE '5%' ");
        sb.append("        AND    dev.DocType = 'I' ");
        sb.append("        AND    numAtCard NOT IN (SELECT U_DocNum ");
        sb.append("                                 FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                                 WHERE  U_TipoDocumento = 'Devolucion') ");
        sb.append("        GROUP  BY dev.docdate, SUBSTRING(CONVERT(VARCHAR, dev.NumAtCard), 1, 2) ");
        sb.append("       ) AS ventasnetas ");
        sb.append("GROUP  BY almacen ");
        sb.append("HAVING SUM(ventas) - SUM(devuelto) <> 0 ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> obtenerVentasNetasJuanCamilo(Integer startYear, Integer startMonth, Integer startDay, Integer endYear, Integer endMonth, Integer endDay) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT almacen, ");
        sb.append("       CONVERT(NUMERIC, SUM(ventas) - SUM(devuelto)) AS ventasnetas ");
        sb.append("FROM   ( ");
        sb.append("        SELECT fac.docdate, ");
        sb.append("               SUBSTRING(CONVERT(VARCHAR, fac.docnum), 1, 2) almacen, ");
        sb.append("               SUM(fac.DocTotal - fac.VatSum - fac.RoundDif) AS ventas, ");
        sb.append("               0 AS devuelto ");
        sb.append("        FROM   OINV fac ");
        sb.append("        WHERE  CONVERT(DATE, fac.DocDate) BETWEEN '");
        sb.append(startYear).append("-").append(startMonth).append("-").append(startDay).append("' ");
        sb.append("        AND '");
        sb.append(endYear).append("-").append(endMonth).append("-").append(endDay);
//        sb.append("        WHERE  YEAR(fac.DocDate) BETWEEN '");
//        sb.append(startYear);
//        sb.append("' AND '");
//        sb.append(endYear);
//        sb.append("'       AND    MONTH(fac.DocDate) BETWEEN '");
//        sb.append(startMonth);
//        sb.append("' AND '");
//        sb.append(endMonth);
//        sb.append("'       AND    DAY(fac.DocDate) BETWEEN '");
//        sb.append(startDay);
//        sb.append("' AND '");
//        sb.append(endDay);
        sb.append("'       AND    fac.DocNum NOT LIKE '2%' ");
        sb.append("        AND    fac.DocNum NOT LIKE '5%' ");
        sb.append("        AND    fac.DocType = 'I' ");
        sb.append("        AND    fac.DocSubType = '--' ");
        sb.append("        AND    DocNum NOT IN (SELECT U_DocNum ");
        sb.append("                              FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                              WHERE  U_TipoDocumento = 'Factura') ");
        sb.append("        GROUP  BY fac.docdate, SUBSTRING(CONVERT(VARCHAR, fac.docnum), 1, 2) ");
        sb.append("        UNION ");
        sb.append("        SELECT dev.docdate, ");
        sb.append("               SUBSTRING(CONVERT(VARCHAR, dev.NumAtCard), 1, 2) almacen, ");
        sb.append("               0 AS ventas, ");
        sb.append("               SUM(dev.DocTotal - dev.VatSum - dev.RoundDif) devuelto ");
        sb.append("        FROM   ORIN dev ");
        sb.append("        WHERE  CONVERT(DATE, dev.DocDate) BETWEEN '");
        sb.append(startYear).append("-").append(startMonth).append("-").append(startDay).append("' ");
        sb.append("        AND '");
        sb.append(endYear).append("-").append(endMonth).append("-").append(endDay);
//        sb.append("        WHERE  YEAR(dev.DocDate) BETWEEN '");
//        sb.append(startYear);
//        sb.append("' AND '");
//        sb.append(endYear);
//        sb.append("'       AND    MONTH(dev.DocDate) BETWEEN '");
//        sb.append(startMonth);
//        sb.append("' AND '");
//        sb.append(endMonth);
//        sb.append("'       AND    DAY(dev.DocDate) BETWEEN '");
//        sb.append(startDay);
//        sb.append("' AND '");
//        sb.append(endDay);
        sb.append("'       AND    dev.NumAtCard NOT LIKE '2%' ");
        sb.append("        AND    dev.NumAtCard NOT LIKE '5%' ");
        sb.append("        AND    dev.DocType = 'I' ");
        sb.append("        AND    dev.U_TipoNota = 'A' ");
        sb.append("        AND    numAtCard NOT IN (SELECT U_DocNum ");
        sb.append("                                 FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                                 WHERE  U_TipoDocumento = 'Devolucion') ");
        sb.append("        GROUP  BY dev.docdate, SUBSTRING(CONVERT(VARCHAR, dev.NumAtCard), 1, 2) ");
        sb.append("       ) AS ventasnetas ");
        sb.append("GROUP  BY almacen ");
        sb.append("HAVING SUM(ventas) - SUM(devuelto) <> 0 ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> obtenerDatosVentas(Integer startYear, Integer startMonth, Integer startDay, Integer endYear, Integer endMonth, Integer endDay) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT SUBSTRING(CONVERT(VARCHAR, fac.docnum), 1, 2) almacen, ");
        sb.append("       'FV' AS tipo, CONVERT(VARCHAR, DocNum) Factura, ");
        sb.append("       CONVERT(INT, DocTotal - VatSum - RoundDif) AS neto, ");
        sb.append("       CONVERT(DATETIME, CONCAT(CONVERT(DATE, CAST(DocDate AS DATE), 102), ' ', CONCAT(SUBSTRING(RIGHT('000' + CAST(DocTime AS VARCHAR(4)), 4), 1, 2), ':', ");
        sb.append("       SUBSTRING(RIGHT('000' + CAST(DocTime AS VARCHAR(4)), 4), 3, 2))), 102) DocDate ");
        sb.append("FROM   OINV fac ");
        sb.append("WHERE  CONVERT(DATE, fac.DocDate) BETWEEN '");
        sb.append(startYear).append("-").append(startMonth).append("-").append(startDay).append("' ");
        sb.append("AND '");
        sb.append(endYear).append("-").append(endMonth).append("-").append(endDay);
//        sb.append("WHERE  YEAR(fac.DocDate) BETWEEN '");
//        sb.append(startYear);
//        sb.append("' AND '");
//        sb.append(endYear);
//        sb.append("' AND  MONTH(fac.DocDate) BETWEEN '");
//        sb.append(startMonth);
//        sb.append("' AND '");
//        sb.append(endMonth);
//        sb.append("' AND  DAY(fac.DocDate) BETWEEN '");
//        sb.append(startDay);
//        sb.append("' AND '");
//        sb.append(endDay);
        sb.append("' AND  DocNum NOT LIKE '2%' ");
        sb.append("AND    DocNum NOT LIKE '5%' ");
        sb.append("AND    DocSubType = '--' ");
        sb.append("AND    fac.DocType='I' ");
        sb.append("AND    DocNum NOT IN (SELECT U_DocNum ");
        sb.append("                      FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                      WHERE  U_TipoDocumento = 'Factura') ");
        sb.append("UNION  ALL ");
        sb.append("SELECT SUBSTRING(CONVERT(VARCHAR, NumAtCard), 1, 2) almacen, ");
        sb.append("       'DV' AS tipo, CONVERT(VARCHAR, NumAtCard) AS Factura, ");
        sb.append("       CONVERT(INT, -1 * (DocTotal - VatSum - RoundDif)) AS neto, ");
        sb.append("       CONVERT(DATETIME, CONCAT(CONVERT(DATE, CAST(DocDate AS DATE), 102), ' ', CONCAT(SUBSTRING(RIGHT('000' + CAST(DocTime AS VARCHAR(4)), 4), 1, 2), ':', ");
        sb.append("       SUBSTRING(RIGHT('000' + CAST(DocTime AS VARCHAR(4)), 4), 3, 2))), 102) DocDate ");
        sb.append("FROM   ORIN dev ");
        sb.append(" WHERE  CONVERT(DATE, dev.DocDate) BETWEEN '");
        sb.append(startYear).append("-").append(startMonth).append("-").append(startDay).append("' ");
        sb.append("AND '");
        sb.append(endYear).append("-").append(endMonth).append("-").append(endDay);
//        sb.append("WHERE  YEAR(dev.DocDate) BETWEEN '");
//        sb.append(startYear);
//        sb.append("' AND '");
//        sb.append(endYear);
//        sb.append("' AND  MONTH(dev.DocDate) BETWEEN '");
//        sb.append(startMonth);
//        sb.append("' AND '");
//        sb.append(endMonth);
//        sb.append("' AND  DAY(dev.DocDate) BETWEEN '");
//        sb.append(startDay);
//        sb.append("' AND '");
//        sb.append(endDay);
        sb.append("' AND  dev.NumAtCard NOT LIKE '2%' ");
        sb.append("AND    dev.NumAtCard NOT LIKE '5%' ");
        sb.append("AND    dev.DocType = 'I' ");
        sb.append("AND    numAtCard NOT IN (SELECT U_DocNum ");
        sb.append("                         FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                         WHERE  U_TipoDocumento = 'Devolucion') ");
        sb.append("ORDER  BY almacen, DocDate ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> obtenerVentasMensuales(String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DATEPART(YEAR, docdate) year, ");
        sb.append("       CONVERT(VARCHAR(2), DATEPART(MONTH, docdate)) mes, ");
        sb.append("       CONVERT(NUMERIC, SUM(neto)) AS total ");
        sb.append("FROM   (SELECT SUBSTRING(CONVERT(VARCHAR, docnum), 1, 2) almacen, ");
        sb.append("               'FV' AS tipo, ");
        sb.append("               DocNum, ");
        sb.append("               DocTotal, ");
        sb.append("               VatSum, ");
        sb.append("               RoundDif, ");
        sb.append("               DocTotal - VatSum - RoundDif AS neto, ");
        sb.append("               DocDate ");
        sb.append("        FROM   OINV ");
        sb.append("        WHERE  DocDate >= CONVERT(DATE, '2013-05-01') ");
        sb.append("        AND    DocNum NOT LIKE '2%' ");
        sb.append("        AND    DocNum NOT LIKE '5%' ");
        sb.append("        AND    DocType = 'I' ");
        sb.append("        AND    DocSubType = '--' ");
        sb.append("        AND    DocNum NOT IN (SELECT U_DocNum ");
        sb.append("                              FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                              WHERE  U_TipoDocumento = 'Factura') ");
        sb.append("        UNION ");
        sb.append("        SELECT SUBSTRING(CONVERT(VARCHAR, NumAtCard), 1, 2) almacen, ");
        sb.append("               'DV' AS tipo, ");
        sb.append("               NumAtCard AS docnum, ");
        sb.append("               DocTotal * -1, ");
        sb.append("               VatSum * -1, ");
        sb.append("               RoundDif * -1, ");
        sb.append("               -1 * (DocTotal - VatSum - RoundDif) AS neto, ");
        sb.append("               DocDate ");
        sb.append("        FROM   ORIN ");
        sb.append("        WHERE  DocDate >= CONVERT(DATE, '2013-05-01') ");
        sb.append("        AND    NumAtCard NOT LIKE '2%' ");
        sb.append("        AND    NumAtCard NOT LIKE '5%' ");
        sb.append("        AND    NumAtCard NOT IN (SELECT U_DocNum ");
        sb.append("                                 FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                                 WHERE  U_TipoDocumento = 'Devolucion') ");
        sb.append("        AND    DocType = 'I') datos_ventas ");
        if (almacen != null && !almacen.isEmpty()) {
            sb.append("WHERE  almacen = '");
            sb.append(almacen);
            sb.append("' ");
        }
        sb.append("GROUP  BY DATEPART(YEAR, docdate), CONVERT(VARCHAR(2), DATEPART(month, docdate)) ");
        sb.append("UNION ");
        sb.append("SELECT yearfac year, ");
        sb.append("       CONVERT(VARCHAR(2), monthfac) mes, ");
        sb.append("       SUM(precioU_siniva) total ");
        sb.append("FROM   DWB..ventasnetas ");
        sb.append("WHERE  fuente = 'gyg' ");
        sb.append("AND    factura NOT LIKE 'T2%' ");
        sb.append("AND    factura NOT LIKE 'B2%' ");
        if (almacen != null && !almacen.isEmpty()) {
            sb.append("AND    (CASE SUBSTRING(factura, 1, 1) WHEN '0' THEN '10' WHEN 'B' THEN '40' WHEN 'S' THEN '30' ELSE SUBSTRING(factura, 1, 1) END) = '");
            sb.append(almacen);
            sb.append("' ");
        }
        sb.append("GROUP  BY yearfac, CONVERT(VARCHAR(2), monthfac) ");
        sb.append("ORDER  BY year DESC, mes ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> obtenerVentasDiarias(String mes, String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DATEPART(YEAR, docdate) year, ");
        sb.append("       CONVERT(VARCHAR(2), DATEPART(MONTH, docdate)) mes, ");
        sb.append("       CONVERT(VARCHAR(2), DATEPART(DAY, docdate)) dia, ");
        sb.append("       CONVERT(NUMERIC, SUM(neto)) AS total ");
        sb.append("FROM   ( ");
        sb.append("        SELECT SUBSTRING(CONVERT(VARCHAR, docnum), 1, 2) almacen, ");
        sb.append("               'FV' AS tipo, ");
        sb.append("               DocNum, ");
        sb.append("               DocTotal, ");
        sb.append("               VatSum, ");
        sb.append("               RoundDif, ");
        sb.append("               DocTotal - VatSum - RoundDif AS neto, ");
        sb.append("               DocDate ");
        sb.append("        FROM   OINV ");
        sb.append("        WHERE  DATEPART(MONTH, DocDate) = ");
        sb.append(mes);
        sb.append("        AND    DocNum NOT LIKE '2%' ");
        sb.append("        AND    DocNum NOT LIKE '5%' ");
        sb.append("        AND    DocSubType = '--' ");
        sb.append("        AND    DocType = 'I' ");
        sb.append("        AND    DocNum NOT IN (SELECT U_DocNum ");
        sb.append("                              FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                              WHERE  U_TipoDocumento = 'Factura') ");
        sb.append("        UNION ");
        sb.append("        SELECT SUBSTRING(CONVERT(VARCHAR, NumAtCard), 1, 2) almacen, ");
        sb.append("               'DV' AS tipo, ");
        sb.append("               NumAtCard AS docnum, ");
        sb.append("               DocTotal * -1, ");
        sb.append("               VatSum * -1, ");
        sb.append("               RoundDif * -1, ");
        sb.append("               -1 * (DocTotal - VatSum - RoundDif) AS neto, ");
        sb.append("               DocDate ");
        sb.append("        FROM   ORIN ");
        sb.append("        WHERE  DATEPART(MONTH, DocDate) = ");
        sb.append(mes);
        sb.append("        AND    NumAtCard NOT LIKE '2%' ");
        sb.append("        AND    NumAtCard NOT LIKE '5%' ");
        sb.append("        AND    numAtCard NOT IN (SELECT U_DocNum ");
        sb.append("                                 FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                                 WHERE  U_TipoDocumento = 'Devolucion') ");
        sb.append("        AND    DocType = 'I' ");
        sb.append("       ) datos_ventas ");
        if (almacen != null && !almacen.isEmpty()) {
            sb.append("WHERE  almacen = '");
            sb.append(almacen);
            sb.append("' ");
        }
        sb.append("GROUP  BY DATEPART(YEAR, docdate), CONVERT(VARCHAR(2), DATEPART(MONTH, docdate)), CONVERT(VARCHAR(2), DATEPART(DAY, docdate)) ");
        sb.append("UNION ");
        sb.append("SELECT yearfac year, ");
        sb.append("       CONVERT(VARCHAR(2), monthfac) mes, ");
        sb.append("       CONVERT(VARCHAR(2), dayfac) dia, ");
        sb.append("       SUM(precioU_siniva) total ");
        sb.append("FROM   DWB..ventasnetas ");
        sb.append("WHERE  fuente = 'gyg' ");
        sb.append("AND    factura NOT LIKE 'T2%' ");
        sb.append("AND    factura NOT LIKE 'B2%' ");
        sb.append("AND    monthfac = ");
        sb.append(mes);
        if (almacen != null && !almacen.isEmpty()) {
            sb.append(" AND   (CASE SUBSTRING(factura, 1, 1) WHEN '0' THEN '10' WHEN 'B' THEN '40' WHEN 'S' THEN '30' ELSE SUBSTRING(factura, 1, 1) END) = '");
            sb.append(almacen);
            sb.append("' ");
        }
        sb.append(" GROUP BY yearfac, CONVERT(VARCHAR(2), monthfac), CONVERT(VARCHAR(2), dayfac) ");
        sb.append("ORDER  BY year DESC, mes, dia ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> obtenerVentasSemanales(Integer diaSemana, String almacen) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DATEPART(YEAR, docdate) year, ");
        sb.append("       DATEPART(WK, docdate) semana, ");
        sb.append("       CONVERT(NUMERIC, SUM(neto)) AS total ");
        sb.append("FROM   ( ");
        sb.append("        SELECT SUBSTRING(CONVERT(VARCHAR, docnum), 1, 2) almacen, ");
        sb.append("               'FV' AS tipo, ");
        sb.append("               DocNum, ");
        sb.append("               DocTotal, ");
        sb.append("               VatSum, ");
        sb.append("               RoundDif, ");
        sb.append("               DocTotal - VatSum - RoundDif AS neto, ");
        sb.append("               DocDate ");
        sb.append("        FROM   OINV ");
        sb.append("        WHERE  DocNum NOT LIKE '2%' ");
        sb.append("        AND    DocNum NOT LIKE '5%' ");
        sb.append("        AND    DocSubType = '--' ");
        sb.append("        AND    DocType = 'I' ");
        sb.append("        AND    DocNum NOT IN (SELECT U_DocNum ");
        sb.append("                              FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                              WHERE  U_TipoDocumento = 'Factura') ");
        if (diaSemana != null && diaSemana != 0) {
            sb.append("        AND    DATEPART(DW, DocDate) = ");
            sb.append(diaSemana);
        }
        sb.append("        UNION ");
        sb.append("        SELECT SUBSTRING(CONVERT(VARCHAR, NumAtCard), 1, 2) almacen, ");
        sb.append("               'DV' AS tipo, ");
        sb.append("               NumAtCard AS docnum, ");
        sb.append("               DocTotal * -1, ");
        sb.append("               VatSum * -1, ");
        sb.append("               RoundDif * -1, ");
        sb.append("               -1 * (DocTotal - VatSum - RoundDif) AS neto, ");
        sb.append("               DocDate ");
        sb.append("        FROM   ORIN ");
        sb.append("        WHERE  NumAtCard NOT LIKE '2%' ");
        if (diaSemana != null && diaSemana != 0) {
            sb.append("        AND    DATEPART(DW, DocDate) = ");
            sb.append(diaSemana);
        }
        sb.append("        AND    NumAtCard NOT LIKE '5%' ");
        sb.append("        AND    NumAtCard NOT IN (SELECT U_DocNum ");
        sb.append("                                 FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                                 WHERE  U_TipoDocumento = 'Devolucion') ");
        sb.append("        AND    DocType = 'I' ");
        sb.append("       ) datos_ventas ");
        if (almacen != null && !almacen.isEmpty()) {
            sb.append("WHERE  almacen = '");
            sb.append(almacen);
            sb.append("' ");
        }
        sb.append("GROUP  BY DATEPART(YEAR, docdate), CONVERT(VARCHAR(2), DATEPART(MONTH, docdate)), DATEPART(WK, docdate) ");
        sb.append("UNION ");
        sb.append("SELECT yearfac year, ");
        sb.append("       DATEPART(WK, fecha) semana, ");
        sb.append("       SUM(precioU_siniva) total ");
        sb.append("FROM   DWB..ventasnetas ");
        sb.append("WHERE  fuente = 'gyg' ");
        sb.append("AND    factura NOT LIKE 'T2%' ");
        sb.append("AND    factura NOT LIKE 'B2%' ");
        if (diaSemana != null && diaSemana != 0) {
            sb.append("AND    DATEPART(DW, fecha) = ");
            sb.append(diaSemana);
        }
        if (almacen != null && !almacen.isEmpty()) {
            sb.append(" AND   (CASE SUBSTRING(factura, 1, 1) WHEN '0' THEN '10' WHEN 'B' THEN '40' WHEN 'S' THEN '30' ELSE SUBSTRING(factura, 1, 1) END) = '");
            sb.append(almacen);
            sb.append("' ");
        }
        sb.append(" GROUP BY yearfac, CONVERT(VARCHAR(2), monthfac), DATEPART(WK, fecha) ");
        sb.append("ORDER  BY year DESC ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> obtenerVentasRuta(Integer startYear, Integer startMonth, Integer startDay, Integer endYear, Integer endMonth, Integer endDay) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT fecha, ruta, CONVERT(NUMERIC, SUM(subtotal)) AS total ");
        sb.append("FROM   ( ");
        sb.append("        SELECT CONVERT(VARCHAR(15), e.docdate, 105) AS fecha ");
        sb.append("               , CONVERT(VARCHAR(100), ISNULL(r.OcrName, 'DESCONOCIDA')) AS ruta ");
        sb.append("               , CONVERT(NUMERIC(18, 2), d.linetotal) AS subtotal ");
        sb.append("        FROM   INV1 d ");
        sb.append("        INNER  JOIN OINV e ON e.docentry = d.docentry ");
        sb.append("        LEFT   JOIN OOCR r ON r.OcrCode = d.ocrcode4 AND r.dimcode = 4 ");
        sb.append("        WHERE  e.doctype = 'i' ");
        sb.append("        AND    e.DocNum NOT LIKE '200%' ");
        sb.append("        AND    e.DocNum NOT LIKE '500%' ");
        sb.append("        AND    e.DocSubType = '--' ");
        sb.append("        AND    DocNum NOT IN (SELECT U_DocNum ");
        sb.append("				 FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("				 WHERE  U_TipoDocumento = 'Factura') ");
        sb.append("AND    YEAR(e.docdate) BETWEEN '");
        sb.append(startYear);
        sb.append("' AND '");
        sb.append(endYear);
        sb.append("' AND  MONTH(e.docdate) BETWEEN '");
        sb.append(startMonth);
        sb.append("' AND '");
        sb.append(endMonth);
        sb.append("' AND  DAY(e.docdate) BETWEEN '");
        sb.append(startDay);
        sb.append("' AND '");
        sb.append(endDay);
        sb.append("'");
        sb.append("        UNION  ALL  ");
        sb.append("        SELECT CONVERT(VARCHAR(15), e.docdate, 105) AS Fecha ");
        sb.append("               , CONVERT(VARCHAR(100), ISNULL(r.OcrName, 'DESCONOCIDA')) AS Ruta ");
        sb.append("               , CONVERT(NUMERIC(18, 2), -1 * d.linetotal) AS Subtotal ");
        sb.append("        FROM   RIN1 d ");
        sb.append("        INNER  JOIN ORIN e ON e.docentry = d.docentry ");
        sb.append("        LEFT   JOIN OOCR r ON r.OcrCode = d.ocrcode4 AND r.dimcode = 4 ");
        sb.append("        WHERE  e.doctype = 'i' ");
        sb.append("        AND    e.NumAtCard NOT LIKE '200%' ");
        sb.append("        AND    e.NumAtCard NOT LIKE '500%' ");
        sb.append("        AND    e.DocSubType = '--' ");
        sb.append("        AND    DocNum NOT IN (SELECT U_DocNum ");
        sb.append("                              FROM   [@BARU_DOC_EXCLUIDOS] ");
        sb.append("                              WHERE  U_TipoDocumento = 'Devolucion') ");
        sb.append("AND    YEAR(e.docdate) BETWEEN '");
        sb.append(startYear);
        sb.append("' AND '");
        sb.append(endYear);
        sb.append("' AND  MONTH(e.docdate) BETWEEN '");
        sb.append(startMonth);
        sb.append("' AND '");
        sb.append(endMonth);
        sb.append("' AND  DAY(e.docdate) BETWEEN '");
        sb.append(startDay);
        sb.append("' AND '");
        sb.append(endDay);
        sb.append("'");
        sb.append(") AS t ");
        sb.append("GROUP  BY fecha, ruta ");
        sb.append("ORDER  BY 1,2,3 ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Object[]> obtenerMovimientoDiarioTaller() {
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(VARCHAR, 'Salida') AS tipo, ");
        sb.append("       CONVERT(VARCHAR, CASE WHEN det.FromWhsCod LIKE '[0-9]%' THEN 'Venta' ");
        sb.append("	                            WHEN det.FromWhsCod LIKE 'CL%' THEN 'Clientes' ");
        sb.append("	                            WHEN det.FromWhsCod LIKE 'MU%' THEN 'Dotación' ELSE 'Otro' END) AS tipoOrigen, ");
        sb.append("       CONVERT(DATE, enc.TaxDate) AS fecha, ");
        sb.append("       CONVERT(VARCHAR(20), det.ItemCode) AS referencia, ");
        sb.append("       CONVERT(VARCHAR(8), LEFT(det.ItemCode, 3) + '*' + RIGHT(det.ItemCode, 4)) AS ref_corta, ");
        sb.append("       CONVERT(VARCHAR, k.ItemName) AS descripcion, ");
        sb.append("       CONVERT(NUMERIC(18, 2), det.StockPrice) AS costoUnitario, ");
        sb.append("       CONVERT(NUMERIC, SUM(det.Quantity)) AS cantidad ");
        sb.append("FROM   OWTR enc ");
        sb.append("INNER  JOIN WTR1 det ON det.docentry = enc.DocEntry ");
        sb.append("INNER  JOIN OITM k ON k.ItemCode = det.ItemCode ");
        sb.append("WHERE  det.ItemCode IS NOT NULL ");
        sb.append("AND    LEN(det.ItemCode) = 20 ");
        sb.append("AND    det.FromWhsCod = '9901' ");
        sb.append("AND    det.WhsCode LIKE '[0-9]%' ");
        sb.append("AND    CONVERT(DATE, enc.TaxDate) = '");
        sb.append(fecha);
        sb.append("' AND  det.FromWhsCod <> det.WhsCode ");
        sb.append("GROUP  BY CASE WHEN det.FromWhsCod LIKE '[0-9]%' THEN 'Venta' ");
        sb.append("		      WHEN det.FromWhsCod LIKE 'CL%' THEN 'Clientes' ");
        sb.append("		      WHEN det.FromWhsCod LIKE 'MU%' THEN 'Dotación' ELSE 'Otro' END, ");
        sb.append("          CONVERT(DATE, enc.TaxDate), ");
        sb.append("          det.ItemCode, ");
        sb.append("          k.ItemName, ");
        sb.append("          det.StockPrice ");
        sb.append("UNION  ALL ");
        sb.append("SELECT CONVERT(VARCHAR, 'Entrada') AS tipo, ");
        sb.append("	      CONVERT(VARCHAR, CASE WHEN det.FromWhsCod LIKE '[0-9]%' THEN 'Venta' ");
        sb.append("	                            WHEN det.FromWhsCod LIKE 'CL%' THEN 'Clientes' ");
        sb.append("	                            WHEN det.FromWhsCod LIKE 'MU%' THEN 'Dotación' ELSE 'Otro' END) AS tipoOrigen, ");
        sb.append("       CONVERT(DATE, enc.TaxDate) fecha, ");
        sb.append("       CONVERT(VARCHAR(20), det.ItemCode) AS referencia, ");
        sb.append("       CONVERT(VARCHAR(8), LEFT(det.ItemCode, 3) + '*' + right(det.ItemCode, 4)) AS ref_corta, ");
        sb.append("       CONVERT(VARCHAR, k.ItemName) AS descripcion, ");
        sb.append("       CONVERT(NUMERIC(18, 2), det.StockPrice) AS costoUnitario, ");
        sb.append("       CONVERT(INT, SUM(det.Quantity)) AS cantidad ");
        sb.append("FROM   OWTR enc ");
        sb.append("INNER  JOIN WTR1 det ON det.docentry=enc.DocEntry ");
        sb.append("INNER  JOIN OITM k ON k.ItemCode=det.ItemCode ");
        sb.append("WHERE  det.FromWhsCod LIKE '[0-9]%' ");
        sb.append("AND    det.WhsCode = '9901' ");
        sb.append("AND    CONVERT(DATE, enc.TaxDate) = '");
        sb.append(fecha);
        sb.append("' AND  det.FromWhsCod <> det.WhsCode ");
        sb.append("GROUP  BY CASE WHEN det.FromWhsCod LIKE '[0-9]%' THEN 'Venta' ");
        sb.append("		 WHEN det.FromWhsCod LIKE 'CL%' THEN 'Clientes' ");
        sb.append("		 WHEN det.FromWhsCod LIKE 'MU%' THEN 'Dotación' ELSE 'Otro' END, ");
        sb.append("          CONVERT(DATE, enc.TaxDate), ");
        sb.append("	         det.ItemCode, ");
        sb.append("          k.ItemName, ");
        sb.append("          det.StockPrice ");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los movimientos diarios del taller. ", e);
            return null;
        }
    }

    public List<Object[]> obtenerPedidos(String documento) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT DISTINCT CAST(ov.NumAtCard AS VARCHAR(20)) AS NroPedido,");
        sb.append("       CAST(ov.DocDate AS DATE) AS FechaPedido,");
        sb.append("       CASE WHEN ov.DocStatus = 'O' THEN 'PENDIENTE'");
        sb.append("            WHEN ov.DocStatus = 'C' THEN 'CERRADA' END AS OrdenVenta,");
        sb.append("       CAST(CASE WHEN nc.DocNum IS NULL  THEN 0 ELSE 1 END AS bit) AS Devolucion,");
        sb.append("       CAST(CASE WHEN en.DocNum IS NULL  THEN 0 ELSE 1 END AS bit) AS Despachado,");
        sb.append("       CAST(ov.DocTotal AS NUMERIC(18, 2)) AS Total ");
        sb.append("FROM ORDR ov ");
        sb.append("LEFT  JOIN ORIN nc ON ov.NumAtCard = nc.NumAtCard ");
        sb.append("LEFT  JOIN ODLN en ON ov.NumAtCard = en.NumAtCard ");
        sb.append("WHERE ov.CardCode = '");
        sb.append(documento);
        sb.append("' ORDER BY CAST(ov.DocDate AS date) ASC");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los pedidos del cliente " + documento, e);
            return null;
        }
    }

    public List<Object[]> obtenerDetallePedido(String factura, String item) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CAST(fv.DocNum AS VARCHAR(20)) AS nroPedido, ");
        sb.append("      CASE WHEN ov.DocStatus = 'O' THEN 'PENDIENTE' ");
        sb.append("           WHEN ov.DocStatus = 'C' THEN 'CERRADA' END AS ordenVenta, ");
        sb.append("      CAST(ISNULL(ov.DocDate,fv.DocDate) AS DATE) AS fechaPedido, ");
        sb.append("      CAST(sn.CardName AS VARCHAR(100)) AS cliente, ");
        sb.append("      CAST(dr.street AS VARCHAR(100)) AS direccionEntrega, ");
        sb.append("      CAST(dr.city AS VARCHAR(50)) AS ciudadEntrega, ");
        sb.append("      CAST(ISNULL(dp.Name,'') AS VARCHAR(50)) AS departamentoEntrega, ");
        sb.append("      CAST(dr.Block AS VARCHAR(50)) AS telefono, ");
        sb.append("      CAST(dr.Building AS VARCHAR(50)) AS celular, ");
        sb.append("      CAST((LEFT(df.ItemCode,3) + '.' + RIGHT(df.ItemCode,4)) AS VARCHAR(10)) AS item, ");
        sb.append("      CAST(ar.FrgnName AS VARCHAR(50)) AS producto, ");
        sb.append("      CAST(CASE WHEN (SELECT SUM(tj.CreditSum) ");
        sb.append("                FROM ORCT rc ");
        sb.append("		   LEFT JOIN RCT3 tj ON rc.DocEntry = tj.DocNum ");
        sb.append("		   WHERE tj.CreditSum <> 0 AND rc.DocEntry = fv.ReceiptNum) IS NULL THEN '' ELSE 'TARJETA|' END + ");
        sb.append("	 CASE WHEN (SELECT SUM(rc.CashSum) ");
        sb.append("		   FROM ORCT rc ");
        sb.append("		   WHERE rc.CashSum <> 0 AND rc.DocEntry = fv.ReceiptNum) IS NULL THEN '' ELSE 'CONTADO|' END + ");
        sb.append("      CASE WHEN (SELECT SUM(cq.CheckSum) ");
        sb.append("	           FROM ORCT rc LEFT JOIN RCT1 cq ON rc.DocEntry = cq.DocNum ");
        sb.append("		   WHERE cq.CheckSum <> 0 AND rc.DocEntry = fv.ReceiptNum) IS NULL THEN '' ELSE 'CHEQUE|' END + ");
        sb.append("      CASE WHEN (SELECT rc.TrsfrSum ");
        sb.append("		   FROM ORCT rc ");
        sb.append("		   WHERE rc.TrsfrSum <> 0 AND rc.DocEntry = fv.ReceiptNum) IS NULL THEN '' ELSE 'CRUCE' END AS VARCHAR(MAX)) AS metodoPago, ");
        sb.append("      CAST(df.Quantity AS INT) AS cantidad, ");
        sb.append("	 CAST(ISNULL(dn.Quantity,0) AS INT) AS devuelto, ");
        sb.append("      CAST(ISNULL(de.Quantity,0) AS INT) AS entregado, ");
        sb.append("      CASE WHEN df.U_EstadoP = 'D' THEN 'ENTREGADO' ");
        sb.append("           WHEN df.U_EstadoP = 'G' THEN 'GUARDADO' ");
        sb.append("	      WHEN df.U_EstadoP = 'P' THEN 'PENDIENTE' ELSE 'OTRO' END AS estadoItem, ");
        sb.append("      CAST(ROUND(df.PriceAfVAT,-1) AS NUMERIC(18,2)) AS precioUnitario, ");
        sb.append("      CAST(ROUND((df.PriceAfVAT*df.Quantity),1) AS NUMERIC(18,2)) AS subtotal, ");
        sb.append("      CAST(CASE WHEN dn.LineNum IS NULL THEN 0 ELSE 1 END AS bit) AS devolucion, ");
        sb.append("      CAST(CASE WHEN de.LineNum IS NULL THEN 0 ELSE 1 END AS bit) AS despachado, ");
        sb.append("      CAST(CASE WHEN DATEDIFF(MONTH, de.DocDate, CONVERT(date, GETDATE())) <= 12 THEN 1 ELSE 0 END AS bit) AS garantia ");
        sb.append("FROM  OINV fv ");
        sb.append("INNER JOIN INV1 df ON df.DocEntry = fv.DocEntry ");
        sb.append("INNER JOIN OITM ar ON ar.ItemCode = df.ItemCode ");
        sb.append("INNER JOIN OCRD sn ON sn.CardCode = fv.CardCode ");
        sb.append("INNER JOIN CRD1 dr ON dr.CardCode = sn.CardCode AND sn.ShipToDef = dr.Address ");
        sb.append("LEFT  JOIN (SELECT TOP 1 * FROM ORDR o WHERE o.NumAtCard = '");
        sb.append(factura);
        sb.append("' ORDER BY o.DocDate ASC) ov ON ov.NumAtCard = fv.DocNum ");
        sb.append("LEFT  JOIN OCST dp ON dp.Code = dr.State AND dp.Country = 'CO' ");
        sb.append("LEFT  JOIN ORIN nc ON fv.DocNum = nc.NumAtCard ");
        sb.append("LEFT  JOIN RIN1 dn ON nc.DocEntry = dn.DocEntry AND df.U_lineNumFv = dn.U_lineNumFv ");
        sb.append("LEFT  JOIN (SELECT TOP 1 * FROM DLN1 e WHERE e.U_NWR_Base = '");
        sb.append(factura);
        sb.append("' ORDER BY e.DocDate ASC) de ON de.U_NWR_Base = fv.DocNum AND df.U_lineNumFv = de.U_lineNumFv ");
        sb.append("WHERE dr.AdresType = 'S' AND fv.DocNum = '");
        sb.append(factura);
        if (item != null) {
            sb.append("' AND df.ItemCode = '");
            sb.append(item);
        }
        sb.append("' ORDER BY CAST(ov.DocDate AS date) ASC");

        try {
            return em.createNativeQuery(sb.toString()).getResultList();
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener el detalle del pedido nro " + factura, e);
            return null;
        }
    }

    public List<FacturaSAP> obtenerFacturasDecorador(String decorador, String cliente) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT fac.* ");
        sb.append("FROM   OINV fac ");
        sb.append("INNER  JOIN [@BARU_DECORADORES] dec ON dec.Name = fac.U_Diseno ");

        if (cliente != null && !cliente.isEmpty()) {
            sb.append("WHERE  dec.Code = '");
            sb.append(decorador);
            sb.append("' AND    fac.CardCode = '");
            sb.append(cliente);
            sb.append("' ");
        } else {
            sb.append("WHERE  dec.Code = '");
            sb.append(decorador);
            sb.append("' ");
        }

        try {
            return em.createNativeQuery(sb.toString(), FacturaSAP.class).getResultList();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al consultar las facturas para decorador. ", e);
        }
        return null;
    }

    public Integer obtenerMesesSinComisionarDecorador(String decorador, List<Integer> docNums) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT CONVERT(INT, DATEDIFF(MM, MAX(f.DocDate), GETDATE())) AS ultimaFecha ");
        sb.append("FROM   OINV f ");
        sb.append("INNER  JOIN [@BARU_DECORADORES] dec ON dec.Name = f.U_Diseno ");
        sb.append("WHERE  dec.Code = '");
        sb.append(decorador);
        sb.append("' ");

        if (docNums != null && !docNums.isEmpty()) {
            sb.append("AND   f.DocNum IN (");

            for (Integer d : docNums) {
                sb.append(d);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);

            sb.append(") ");
        }

        try {
            return (Integer) em.createNativeQuery(sb.toString()).getSingleResult();
        } catch (NoResultException e) {
        } catch (Exception e) {
            CONSOLE.log(Level.SEVERE, "Ocurrio un error al obtener los meses sin comisionar de un decorador. ", e);
        }
        return null;
    }
}
