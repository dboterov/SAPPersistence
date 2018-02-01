package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OITW")
@NamedQueries({
    @NamedQuery(name = "SaldoItemInventario.findAll", query = "SELECT s FROM SaldoItemInventario s")})
public class SaldoItemInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SaldoItemInventarioPK saldoItemInventarioPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OnHand")
    private BigDecimal onHand;
    @Column(name = "IsCommited")
    private BigDecimal isCommited;
    @Column(name = "OnOrder")
    private BigDecimal onOrder;
    @Column(name = "Consig")
    private BigDecimal consig;
    @Column(name = "Counted")
    private BigDecimal counted;
    @Column(name = "WasCounted")
    private Character wasCounted;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "MinStock")
    private BigDecimal minStock;
    @Column(name = "MaxStock")
    private BigDecimal maxStock;
    @Column(name = "MinOrder")
    private BigDecimal minOrder;
    @Column(name = "AvgPrice")
    private BigDecimal avgPrice;
    @Column(name = "Locked")
    private Character locked;
    @Size(max = 15)
    @Column(name = "BalInvntAc")
    private String balInvntAc;
    @Size(max = 15)
    @Column(name = "SaleCostAc")
    private String saleCostAc;
    @Size(max = 15)
    @Column(name = "TransferAc")
    private String transferAc;
    @Size(max = 15)
    @Column(name = "RevenuesAc")
    private String revenuesAc;
    @Size(max = 15)
    @Column(name = "VarianceAc")
    private String varianceAc;
    @Size(max = 15)
    @Column(name = "DecreasAc")
    private String decreasAc;
    @Size(max = 15)
    @Column(name = "IncreasAc")
    private String increasAc;
    @Size(max = 15)
    @Column(name = "ReturnAc")
    private String returnAc;
    @Size(max = 15)
    @Column(name = "ExpensesAc")
    private String expensesAc;
    @Size(max = 15)
    @Column(name = "EURevenuAc")
    private String eURevenuAc;
    @Size(max = 15)
    @Column(name = "EUExpensAc")
    private String eUExpensAc;
    @Size(max = 15)
    @Column(name = "FrRevenuAc")
    private String frRevenuAc;
    @Size(max = 15)
    @Column(name = "FrExpensAc")
    private String frExpensAc;
    @Size(max = 15)
    @Column(name = "ExmptIncom")
    private String exmptIncom;
    @Size(max = 15)
    @Column(name = "PriceDifAc")
    private String priceDifAc;
    @Size(max = 15)
    @Column(name = "ExchangeAc")
    private String exchangeAc;
    @Size(max = 15)
    @Column(name = "BalanceAcc")
    private String balanceAcc;
    @Size(max = 15)
    @Column(name = "PurchaseAc")
    private String purchaseAc;
    @Size(max = 15)
    @Column(name = "PAReturnAc")
    private String pAReturnAc;
    @Size(max = 15)
    @Column(name = "PurchOfsAc")
    private String purchOfsAc;
    @Size(max = 15)
    @Column(name = "ShpdGdsAct")
    private String shpdGdsAct;
    @Size(max = 15)
    @Column(name = "VatRevAct")
    private String vatRevAct;
    @Column(name = "StockValue")
    private BigDecimal stockValue;
    @Size(max = 15)
    @Column(name = "DecresGlAc")
    private String decresGlAc;
    @Size(max = 15)
    @Column(name = "IncresGlAc")
    private String incresGlAc;
    @Size(max = 15)
    @Column(name = "StokRvlAct")
    private String stokRvlAct;
    @Size(max = 15)
    @Column(name = "StkOffsAct")
    private String stkOffsAct;
    @Size(max = 15)
    @Column(name = "WipAcct")
    private String wipAcct;
    @Size(max = 15)
    @Column(name = "WipVarAcct")
    private String wipVarAcct;
    @Size(max = 15)
    @Column(name = "CostRvlAct")
    private String costRvlAct;
    @Size(max = 15)
    @Column(name = "CstOffsAct")
    private String cstOffsAct;
    @Size(max = 15)
    @Column(name = "ExpClrAct")
    private String expClrAct;
    @Size(max = 15)
    @Column(name = "ExpOfstAct")
    private String expOfstAct;
    @Size(max = 20)
    @Column(name = "Object")
    private String object;
    @Column(name = "logInstanc")
    private Integer logInstanc;
    @Column(name = "createDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "userSign2")
    private Short userSign2;
    @Column(name = "updateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Size(max = 15)
    @Column(name = "ARCMAct")
    private String aRCMAct;
    @Size(max = 15)
    @Column(name = "ARCMFrnAct")
    private String aRCMFrnAct;
    @Size(max = 15)
    @Column(name = "ARCMEUAct")
    private String aRCMEUAct;
    @Size(max = 15)
    @Column(name = "ARCMExpAct")
    private String aRCMExpAct;
    @Size(max = 15)
    @Column(name = "APCMAct")
    private String aPCMAct;
    @Size(max = 15)
    @Column(name = "APCMFrnAct")
    private String aPCMFrnAct;
    @Size(max = 15)
    @Column(name = "APCMEUAct")
    private String aPCMEUAct;
    @Size(max = 15)
    @Column(name = "RevRetAct")
    private String revRetAct;
    @Size(max = 15)
    @Column(name = "NegStckAct")
    private String negStckAct;
    @Size(max = 15)
    @Column(name = "StkInTnAct")
    private String stkInTnAct;
    @Size(max = 15)
    @Column(name = "PurBalAct")
    private String purBalAct;
    @Size(max = 15)
    @Column(name = "WhICenAct")
    private String whICenAct;
    @Size(max = 15)
    @Column(name = "WhOCenAct")
    private String whOCenAct;
    @Size(max = 15)
    @Column(name = "WipOffset")
    private String wipOffset;
    @Size(max = 15)
    @Column(name = "StockOffst")
    private String stockOffst;
    @Column(name = "DftBinAbs")
    private Integer dftBinAbs;
    @Column(name = "DftBinEnfd")
    private Character dftBinEnfd;
    @Column(name = "Freezed")
    private Character freezed;
    @Column(name = "FreezeDoc")
    private Integer freezeDoc;
    @Size(max = 15)
    @Column(name = "FreeChrgSA")
    private String freeChrgSA;
    @Size(max = 15)
    @Column(name = "FreeChrgPU")
    private String freeChrgPU;

    public SaldoItemInventario() {
    }

    public SaldoItemInventario(SaldoItemInventarioPK saldoItemInventarioPK) {
        this.saldoItemInventarioPK = saldoItemInventarioPK;
    }

    public SaldoItemInventario(String itemCode, Almacen whsCode) {
        this.saldoItemInventarioPK = new SaldoItemInventarioPK(itemCode, whsCode);
    }

    public SaldoItemInventarioPK getSaldoItemInventarioPK() {
        return saldoItemInventarioPK;
    }

    public void setSaldoItemInventarioPK(SaldoItemInventarioPK saldoItemInventarioPK) {
        this.saldoItemInventarioPK = saldoItemInventarioPK;
    }

    public int getOnHandAsInt() {
        return onHand.intValue();
    }

    public BigDecimal getOnHand() {
        return onHand;
    }

    public void setOnHand(BigDecimal onHand) {
        this.onHand = onHand;
    }

    public BigDecimal getIsCommited() {
        return isCommited;
    }

    public void setIsCommited(BigDecimal isCommited) {
        this.isCommited = isCommited;
    }

    public BigDecimal getOnOrder() {
        return onOrder;
    }

    public void setOnOrder(BigDecimal onOrder) {
        this.onOrder = onOrder;
    }

    public BigDecimal getConsig() {
        return consig;
    }

    public void setConsig(BigDecimal consig) {
        this.consig = consig;
    }

    public BigDecimal getCounted() {
        return counted;
    }

    public void setCounted(BigDecimal counted) {
        this.counted = counted;
    }

    public Character getWasCounted() {
        return wasCounted;
    }

    public void setWasCounted(Character wasCounted) {
        this.wasCounted = wasCounted;
    }

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public BigDecimal getMinStock() {
        return minStock;
    }

    public void setMinStock(BigDecimal minStock) {
        this.minStock = minStock;
    }

    public BigDecimal getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(BigDecimal maxStock) {
        this.maxStock = maxStock;
    }

    public BigDecimal getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(BigDecimal minOrder) {
        this.minOrder = minOrder;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Character getLocked() {
        return locked;
    }

    public void setLocked(Character locked) {
        this.locked = locked;
    }

    public String getBalInvntAc() {
        return balInvntAc;
    }

    public void setBalInvntAc(String balInvntAc) {
        this.balInvntAc = balInvntAc;
    }

    public String getSaleCostAc() {
        return saleCostAc;
    }

    public void setSaleCostAc(String saleCostAc) {
        this.saleCostAc = saleCostAc;
    }

    public String getTransferAc() {
        return transferAc;
    }

    public void setTransferAc(String transferAc) {
        this.transferAc = transferAc;
    }

    public String getRevenuesAc() {
        return revenuesAc;
    }

    public void setRevenuesAc(String revenuesAc) {
        this.revenuesAc = revenuesAc;
    }

    public String getVarianceAc() {
        return varianceAc;
    }

    public void setVarianceAc(String varianceAc) {
        this.varianceAc = varianceAc;
    }

    public String getDecreasAc() {
        return decreasAc;
    }

    public void setDecreasAc(String decreasAc) {
        this.decreasAc = decreasAc;
    }

    public String getIncreasAc() {
        return increasAc;
    }

    public void setIncreasAc(String increasAc) {
        this.increasAc = increasAc;
    }

    public String getReturnAc() {
        return returnAc;
    }

    public void setReturnAc(String returnAc) {
        this.returnAc = returnAc;
    }

    public String getExpensesAc() {
        return expensesAc;
    }

    public void setExpensesAc(String expensesAc) {
        this.expensesAc = expensesAc;
    }

    public String getEURevenuAc() {
        return eURevenuAc;
    }

    public void setEURevenuAc(String eURevenuAc) {
        this.eURevenuAc = eURevenuAc;
    }

    public String getEUExpensAc() {
        return eUExpensAc;
    }

    public void setEUExpensAc(String eUExpensAc) {
        this.eUExpensAc = eUExpensAc;
    }

    public String getFrRevenuAc() {
        return frRevenuAc;
    }

    public void setFrRevenuAc(String frRevenuAc) {
        this.frRevenuAc = frRevenuAc;
    }

    public String getFrExpensAc() {
        return frExpensAc;
    }

    public void setFrExpensAc(String frExpensAc) {
        this.frExpensAc = frExpensAc;
    }

    public String getExmptIncom() {
        return exmptIncom;
    }

    public void setExmptIncom(String exmptIncom) {
        this.exmptIncom = exmptIncom;
    }

    public String getPriceDifAc() {
        return priceDifAc;
    }

    public void setPriceDifAc(String priceDifAc) {
        this.priceDifAc = priceDifAc;
    }

    public String getExchangeAc() {
        return exchangeAc;
    }

    public void setExchangeAc(String exchangeAc) {
        this.exchangeAc = exchangeAc;
    }

    public String getBalanceAcc() {
        return balanceAcc;
    }

    public void setBalanceAcc(String balanceAcc) {
        this.balanceAcc = balanceAcc;
    }

    public String getPurchaseAc() {
        return purchaseAc;
    }

    public void setPurchaseAc(String purchaseAc) {
        this.purchaseAc = purchaseAc;
    }

    public String getPAReturnAc() {
        return pAReturnAc;
    }

    public void setPAReturnAc(String pAReturnAc) {
        this.pAReturnAc = pAReturnAc;
    }

    public String getPurchOfsAc() {
        return purchOfsAc;
    }

    public void setPurchOfsAc(String purchOfsAc) {
        this.purchOfsAc = purchOfsAc;
    }

    public String getShpdGdsAct() {
        return shpdGdsAct;
    }

    public void setShpdGdsAct(String shpdGdsAct) {
        this.shpdGdsAct = shpdGdsAct;
    }

    public String getVatRevAct() {
        return vatRevAct;
    }

    public void setVatRevAct(String vatRevAct) {
        this.vatRevAct = vatRevAct;
    }

    public BigDecimal getStockValue() {
        return stockValue;
    }

    public void setStockValue(BigDecimal stockValue) {
        this.stockValue = stockValue;
    }

    public String getDecresGlAc() {
        return decresGlAc;
    }

    public void setDecresGlAc(String decresGlAc) {
        this.decresGlAc = decresGlAc;
    }

    public String getIncresGlAc() {
        return incresGlAc;
    }

    public void setIncresGlAc(String incresGlAc) {
        this.incresGlAc = incresGlAc;
    }

    public String getStokRvlAct() {
        return stokRvlAct;
    }

    public void setStokRvlAct(String stokRvlAct) {
        this.stokRvlAct = stokRvlAct;
    }

    public String getStkOffsAct() {
        return stkOffsAct;
    }

    public void setStkOffsAct(String stkOffsAct) {
        this.stkOffsAct = stkOffsAct;
    }

    public String getWipAcct() {
        return wipAcct;
    }

    public void setWipAcct(String wipAcct) {
        this.wipAcct = wipAcct;
    }

    public String getWipVarAcct() {
        return wipVarAcct;
    }

    public void setWipVarAcct(String wipVarAcct) {
        this.wipVarAcct = wipVarAcct;
    }

    public String getCostRvlAct() {
        return costRvlAct;
    }

    public void setCostRvlAct(String costRvlAct) {
        this.costRvlAct = costRvlAct;
    }

    public String getCstOffsAct() {
        return cstOffsAct;
    }

    public void setCstOffsAct(String cstOffsAct) {
        this.cstOffsAct = cstOffsAct;
    }

    public String getExpClrAct() {
        return expClrAct;
    }

    public void setExpClrAct(String expClrAct) {
        this.expClrAct = expClrAct;
    }

    public String getExpOfstAct() {
        return expOfstAct;
    }

    public void setExpOfstAct(String expOfstAct) {
        this.expOfstAct = expOfstAct;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getUserSign2() {
        return userSign2;
    }

    public void setUserSign2(Short userSign2) {
        this.userSign2 = userSign2;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getARCMAct() {
        return aRCMAct;
    }

    public void setARCMAct(String aRCMAct) {
        this.aRCMAct = aRCMAct;
    }

    public String getARCMFrnAct() {
        return aRCMFrnAct;
    }

    public void setARCMFrnAct(String aRCMFrnAct) {
        this.aRCMFrnAct = aRCMFrnAct;
    }

    public String getARCMEUAct() {
        return aRCMEUAct;
    }

    public void setARCMEUAct(String aRCMEUAct) {
        this.aRCMEUAct = aRCMEUAct;
    }

    public String getARCMExpAct() {
        return aRCMExpAct;
    }

    public void setARCMExpAct(String aRCMExpAct) {
        this.aRCMExpAct = aRCMExpAct;
    }

    public String getAPCMAct() {
        return aPCMAct;
    }

    public void setAPCMAct(String aPCMAct) {
        this.aPCMAct = aPCMAct;
    }

    public String getAPCMFrnAct() {
        return aPCMFrnAct;
    }

    public void setAPCMFrnAct(String aPCMFrnAct) {
        this.aPCMFrnAct = aPCMFrnAct;
    }

    public String getAPCMEUAct() {
        return aPCMEUAct;
    }

    public void setAPCMEUAct(String aPCMEUAct) {
        this.aPCMEUAct = aPCMEUAct;
    }

    public String getRevRetAct() {
        return revRetAct;
    }

    public void setRevRetAct(String revRetAct) {
        this.revRetAct = revRetAct;
    }

    public String getNegStckAct() {
        return negStckAct;
    }

    public void setNegStckAct(String negStckAct) {
        this.negStckAct = negStckAct;
    }

    public String getStkInTnAct() {
        return stkInTnAct;
    }

    public void setStkInTnAct(String stkInTnAct) {
        this.stkInTnAct = stkInTnAct;
    }

    public String getPurBalAct() {
        return purBalAct;
    }

    public void setPurBalAct(String purBalAct) {
        this.purBalAct = purBalAct;
    }

    public String getWhICenAct() {
        return whICenAct;
    }

    public void setWhICenAct(String whICenAct) {
        this.whICenAct = whICenAct;
    }

    public String getWhOCenAct() {
        return whOCenAct;
    }

    public void setWhOCenAct(String whOCenAct) {
        this.whOCenAct = whOCenAct;
    }

    public String getWipOffset() {
        return wipOffset;
    }

    public void setWipOffset(String wipOffset) {
        this.wipOffset = wipOffset;
    }

    public String getStockOffst() {
        return stockOffst;
    }

    public void setStockOffst(String stockOffst) {
        this.stockOffst = stockOffst;
    }

    public Integer getDftBinAbs() {
        return dftBinAbs;
    }

    public void setDftBinAbs(Integer dftBinAbs) {
        this.dftBinAbs = dftBinAbs;
    }

    public Character getDftBinEnfd() {
        return dftBinEnfd;
    }

    public void setDftBinEnfd(Character dftBinEnfd) {
        this.dftBinEnfd = dftBinEnfd;
    }

    public Character getFreezed() {
        return freezed;
    }

    public void setFreezed(Character freezed) {
        this.freezed = freezed;
    }

    public Integer getFreezeDoc() {
        return freezeDoc;
    }

    public void setFreezeDoc(Integer freezeDoc) {
        this.freezeDoc = freezeDoc;
    }

    public String getFreeChrgSA() {
        return freeChrgSA;
    }

    public void setFreeChrgSA(String freeChrgSA) {
        this.freeChrgSA = freeChrgSA;
    }

    public String getFreeChrgPU() {
        return freeChrgPU;
    }

    public void setFreeChrgPU(String freeChrgPU) {
        this.freeChrgPU = freeChrgPU;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (saldoItemInventarioPK != null ? saldoItemInventarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SaldoItemInventario)) {
            return false;
        }
        SaldoItemInventario other = (SaldoItemInventario) object;
        if ((this.saldoItemInventarioPK == null && other.saldoItemInventarioPK != null) || (this.saldoItemInventarioPK != null && !this.saldoItemInventarioPK.equals(other.saldoItemInventarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.SaldoItemInventario[ saldoItemInventarioPK=" + saldoItemInventarioPK + " ]";
    }

}
