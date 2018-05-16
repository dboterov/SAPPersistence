package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ygil
 */
@Entity
@Table(name = "ORCT")
public class ReciboCaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DocEntry")
    private Integer docEntry;
    @Basic(optional = false)
    @Column(name = "DocNum")
    private int docNum;
    @Column(name = "DocType")
    private Character docType;
    @Column(name = "Canceled")
    private Character canceled;
    @Column(name = "Handwrtten")
    private Character handwrtten;
    @Column(name = "Printed")
    private Character printed;
    @Column(name = "DocDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docDate;
    @Column(name = "DocDueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date docDueDate;
    @Column(name = "CardCode")
    private String cardCode;
    @Column(name = "CardName")
    private String cardName;
    @Column(name = "Address")
    private String address;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DdctPrcnt")
    private BigDecimal ddctPrcnt;
    @Column(name = "DdctSum")
    private BigDecimal ddctSum;
    @Column(name = "DdctSumFC")
    private BigDecimal ddctSumFC;
    @Column(name = "CashAcct")
    private String cashAcct;
    @Column(name = "CashSum")
    private BigDecimal cashSum;
    @Column(name = "CashSumFC")
    private BigDecimal cashSumFC;
    @Column(name = "CreditSum")
    private BigDecimal creditSum;
    @Column(name = "CredSumFC")
    private BigDecimal credSumFC;
    @Column(name = "CheckAcct")
    private String checkAcct;
    @Column(name = "CheckSum")
    private BigDecimal checkSum;
    @Column(name = "CheckSumFC")
    private BigDecimal checkSumFC;
    @Column(name = "TrsfrAcct")
    private String trsfrAcct;
    @Column(name = "TrsfrSum")
    private BigDecimal trsfrSum;
    @Column(name = "TrsfrSumFC")
    private BigDecimal trsfrSumFC;
    @Column(name = "TrsfrDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trsfrDate;
    @Column(name = "TrsfrRef")
    private String trsfrRef;
    @Column(name = "PayNoDoc")
    private Character payNoDoc;
    @Column(name = "NoDocSum")
    private BigDecimal noDocSum;
    @Column(name = "NoDocSumFC")
    private BigDecimal noDocSumFC;
    @Column(name = "DocCurr")
    private String docCurr;
    @Column(name = "DiffCurr")
    private Character diffCurr;
    @Column(name = "DocRate")
    private BigDecimal docRate;
    @Column(name = "SysRate")
    private BigDecimal sysRate;
    @Column(name = "DocTotal")
    private BigDecimal docTotal;
    @Column(name = "DocTotalFC")
    private BigDecimal docTotalFC;
    @Column(name = "Ref1")
    private String ref1;
    @Column(name = "Ref2")
    private String ref2;
    @Column(name = "CounterRef")
    private String counterRef;
    @Column(name = "Comments")
    private String comments;
    @Column(name = "JrnlMemo")
    private String jrnlMemo;
    @Column(name = "TransId")
    private Integer transId;
    @Column(name = "DocTime")
    private Short docTime;
    @Column(name = "ShowAtCard")
    private Character showAtCard;
    @Column(name = "SpiltTrans")
    private Character spiltTrans;
    @Column(name = "CreateTran")
    private Character createTran;
    @Column(name = "Flags")
    private Integer flags;
    @Column(name = "CntctCode")
    private Integer cntctCode;
    @Column(name = "DdctSumSy")
    private BigDecimal ddctSumSy;
    @Column(name = "CashSumSy")
    private BigDecimal cashSumSy;
    @Column(name = "CredSumSy")
    private BigDecimal credSumSy;
    @Column(name = "CheckSumSy")
    private BigDecimal checkSumSy;
    @Column(name = "TrsfrSumSy")
    private BigDecimal trsfrSumSy;
    @Column(name = "NoDocSumSy")
    private BigDecimal noDocSumSy;
    @Column(name = "DocTotalSy")
    private BigDecimal docTotalSy;
    @Column(name = "ObjType")
    private String objType;
    @Column(name = "StornoRate")
    private BigDecimal stornoRate;
    @Column(name = "UpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "ApplyVAT")
    private Character applyVAT;
    @Column(name = "TaxDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taxDate;
    @Column(name = "Series")
    private Short series;
    @Column(name = "confirmed")
    private Character confirmed;
    @Column(name = "ShowJDT")
    private Character showJDT;
    @Column(name = "BankCode")
    private String bankCode;
    @Column(name = "BankAcct")
    private String bankAcct;
    @Column(name = "DataSource")
    private Character dataSource;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "LogInstanc")
    private Integer logInstanc;
    @Column(name = "VatGroup")
    private String vatGroup;
    @Column(name = "VatSum")
    private BigDecimal vatSum;
    @Column(name = "VatSumFC")
    private BigDecimal vatSumFC;
    @Column(name = "VatSumSy")
    private BigDecimal vatSumSy;
    @Column(name = "FinncPriod")
    private Integer finncPriod;
    @Column(name = "VatPrcnt")
    private BigDecimal vatPrcnt;
    @Column(name = "Dcount")
    private BigDecimal dcount;
    @Column(name = "DcntSum")
    private BigDecimal dcntSum;
    @Column(name = "DcntSumFC")
    private BigDecimal dcntSumFC;
    @Column(name = "DcntSumSy")
    private BigDecimal dcntSumSy;
    @Column(name = "SpltCredLn")
    private Character spltCredLn;
    @Column(name = "PrjCode")
    private String prjCode;
    @Column(name = "PaymentRef")
    private String paymentRef;
    @Column(name = "Submitted")
    private Character submitted;
    @Column(name = "Status")
    private Character status;
    @Column(name = "PayMth")
    private String payMth;
    @Column(name = "BankCountr")
    private String bankCountr;
    @Column(name = "FreightSum")
    private BigDecimal freightSum;
    @Column(name = "FreigtFC")
    private BigDecimal freigtFC;
    @Column(name = "FreigtSC")
    private BigDecimal freigtSC;
    @Column(name = "BoeAcc")
    private String boeAcc;
    @Column(name = "BoeNum")
    private Integer boeNum;
    @Column(name = "BoeSum")
    private BigDecimal boeSum;
    @Column(name = "BoeSumFc")
    private BigDecimal boeSumFc;
    @Column(name = "BoeSumSc")
    private BigDecimal boeSumSc;
    @Column(name = "BoeAgent")
    private String boeAgent;
    @Column(name = "BoeStatus")
    private Character boeStatus;
    @Column(name = "WtCode")
    private String wtCode;
    @Column(name = "WtSum")
    private BigDecimal wtSum;
    @Column(name = "WtSumFrgn")
    private BigDecimal wtSumFrgn;
    @Column(name = "WtSumSys")
    private BigDecimal wtSumSys;
    @Column(name = "WtAccount")
    private String wtAccount;
    @Column(name = "WtBaseAmnt")
    private BigDecimal wtBaseAmnt;
    @Column(name = "Proforma")
    private Character proforma;
    @Column(name = "BoeAbs")
    private Integer boeAbs;
    @Column(name = "BpAct")
    private String bpAct;
    @Column(name = "BcgSum")
    private BigDecimal bcgSum;
    @Column(name = "BcgSumFC")
    private BigDecimal bcgSumFC;
    @Column(name = "BcgSumSy")
    private BigDecimal bcgSumSy;
    @Basic(optional = false)
    @Column(name = "PIndicator")
    private String pIndicator;
    @Column(name = "PaPriority")
    private Character paPriority;
    @Column(name = "PayToCode")
    private String payToCode;
    @Column(name = "IsPaytoBnk")
    private Character isPaytoBnk;
    @Column(name = "PBnkCnt")
    private String pBnkCnt;
    @Column(name = "PBnkCode")
    private String pBnkCode;
    @Column(name = "PBnkAccnt")
    private String pBnkAccnt;
    @Column(name = "PBnkBranch")
    private String pBnkBranch;
    @Column(name = "WizDunBlck")
    private Character wizDunBlck;
    @Column(name = "WtBaseSum")
    private BigDecimal wtBaseSum;
    @Column(name = "WtBaseSumF")
    private BigDecimal wtBaseSumF;
    @Column(name = "WtBaseSumS")
    private BigDecimal wtBaseSumS;
    @Column(name = "UndOvDiff")
    private BigDecimal undOvDiff;
    @Column(name = "UndOvDiffS")
    private BigDecimal undOvDiffS;
    @Column(name = "BankActKey")
    private Integer bankActKey;
    @Column(name = "VersionNum")
    private String versionNum;
    @Column(name = "VatDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vatDate;
    @Column(name = "TransCode")
    private String transCode;
    @Column(name = "PaymType")
    private Character paymType;
    @Column(name = "TfrRealAmt")
    private BigDecimal tfrRealAmt;
    @Column(name = "CancelDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;
    @Column(name = "OpenBal")
    private BigDecimal openBal;
    @Column(name = "OpenBalFc")
    private BigDecimal openBalFc;
    @Column(name = "OpenBalSc")
    private BigDecimal openBalSc;
    @Column(name = "BcgTaxSum")
    private BigDecimal bcgTaxSum;
    @Column(name = "BcgTaxSumF")
    private BigDecimal bcgTaxSumF;
    @Column(name = "BcgTaxSumS")
    private BigDecimal bcgTaxSumS;
    @Column(name = "TpwID")
    private Integer tpwID;
    @Column(name = "ChallanNo")
    private String challanNo;
    @Column(name = "ChallanBak")
    private String challanBak;
    @Column(name = "ChallanDat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date challanDat;
    @Column(name = "WddStatus")
    private Character wddStatus;
    @Column(name = "BcgVatGrp")
    private String bcgVatGrp;
    @Column(name = "BcgVatPcnt")
    private BigDecimal bcgVatPcnt;
    @Column(name = "SeqCode")
    private Short seqCode;
    @Column(name = "Serial")
    private Integer serial;
    @Column(name = "SeriesStr")
    private String seriesStr;
    @Column(name = "SubStr")
    private String subStr;
    @Column(name = "BSRCode")
    private String bSRCode;
    @Column(name = "LocCode")
    private Integer locCode;
    @Column(name = "WTOnhldPst")
    private BigDecimal wTOnhldPst;
    @Column(name = "UserSign2")
    private Short userSign2;
    @Column(name = "BuildDesc")
    private String buildDesc;
    @Column(name = "ResidenNum")
    private Character residenNum;
    @Column(name = "OperatCode")
    private Character operatCode;
    @Column(name = "UndOvDiffF")
    private BigDecimal undOvDiffF;
    @Column(name = "MIEntry")
    private Integer mIEntry;
    @Column(name = "FreeText1")
    private String freeText1;
    @Column(name = "FreeText2")
    private String freeText2;
    @Column(name = "FreeText3")
    private String freeText3;
    @Column(name = "ShowDocNo")
    private Character showDocNo;
    @Column(name = "TDSInterst")
    private BigDecimal tDSInterst;
    @Column(name = "TDSCharges")
    private BigDecimal tDSCharges;
    @Column(name = "CUP")
    private Integer cup;
    @Column(name = "CIG")
    private Integer cig;
    @Column(name = "MIType")
    private String mIType;
    @Column(name = "SupplCode")
    private String supplCode;
    @Column(name = "BPLId")
    private Integer bPLId;
    @Column(name = "BPLName")
    private String bPLName;
    @Column(name = "VATRegNum")
    private String vATRegNum;
    @Column(name = "BPLCentPmt")
    private Character bPLCentPmt;
    @Column(name = "DraftKey")
    private Integer draftKey;
    @Column(name = "TDSFee")
    private BigDecimal tDSFee;
    @Column(name = "MinHeadCL")
    private Integer minHeadCL;
    @Column(name = "SEPADate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sEPADate;
    @Column(name = "U_BP_Confd")
    private String uBPConfd;
    @Column(name = "U_BP_DocNr")
    private Integer uBPDocNr;
    @Column(name = "U_BP_Seque")
    private Integer uBPSeque;
    @Column(name = "U_OK1_IFRS")
    private Character uOk1Ifrs;
    @Column(name = "U_asesor")
    private String uasesor;
    @Column(name = "U_PagoBeneficiario")
    private String uPagoBeneficiario;

    public ReciboCaja() {
    }

    public ReciboCaja(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public ReciboCaja(Integer docEntry, Integer docNum) {
        this.docEntry = docEntry;
        this.docNum = docNum;
    }

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public int getDocNum() {
        return docNum;
    }

    public void setDocNum(int docNum) {
        this.docNum = docNum;
    }

    public Character getDocType() {
        return docType;
    }

    public void setDocType(Character docType) {
        this.docType = docType;
    }

    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
    }

    public Character getHandwrtten() {
        return handwrtten;
    }

    public void setHandwrtten(Character handwrtten) {
        this.handwrtten = handwrtten;
    }

    public Character getPrinted() {
        return printed;
    }

    public void setPrinted(Character printed) {
        this.printed = printed;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Date getDocDueDate() {
        return docDueDate;
    }

    public void setDocDueDate(Date docDueDate) {
        this.docDueDate = docDueDate;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getDdctPrcnt() {
        return ddctPrcnt;
    }

    public void setDdctPrcnt(BigDecimal ddctPrcnt) {
        this.ddctPrcnt = ddctPrcnt;
    }

    public BigDecimal getDdctSum() {
        return ddctSum;
    }

    public void setDdctSum(BigDecimal ddctSum) {
        this.ddctSum = ddctSum;
    }

    public BigDecimal getDdctSumFC() {
        return ddctSumFC;
    }

    public void setDdctSumFC(BigDecimal ddctSumFC) {
        this.ddctSumFC = ddctSumFC;
    }

    public String getCashAcct() {
        return cashAcct;
    }

    public void setCashAcct(String cashAcct) {
        this.cashAcct = cashAcct;
    }

    public BigDecimal getCashSum() {
        return cashSum;
    }

    public void setCashSum(BigDecimal cashSum) {
        this.cashSum = cashSum;
    }

    public BigDecimal getCashSumFC() {
        return cashSumFC;
    }

    public void setCashSumFC(BigDecimal cashSumFC) {
        this.cashSumFC = cashSumFC;
    }

    public BigDecimal getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(BigDecimal creditSum) {
        this.creditSum = creditSum;
    }

    public BigDecimal getCredSumFC() {
        return credSumFC;
    }

    public void setCredSumFC(BigDecimal credSumFC) {
        this.credSumFC = credSumFC;
    }

    public String getCheckAcct() {
        return checkAcct;
    }

    public void setCheckAcct(String checkAcct) {
        this.checkAcct = checkAcct;
    }

    public BigDecimal getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(BigDecimal checkSum) {
        this.checkSum = checkSum;
    }

    public BigDecimal getCheckSumFC() {
        return checkSumFC;
    }

    public void setCheckSumFC(BigDecimal checkSumFC) {
        this.checkSumFC = checkSumFC;
    }

    public String getTrsfrAcct() {
        return trsfrAcct;
    }

    public void setTrsfrAcct(String trsfrAcct) {
        this.trsfrAcct = trsfrAcct;
    }

    public BigDecimal getTrsfrSum() {
        return trsfrSum;
    }

    public void setTrsfrSum(BigDecimal trsfrSum) {
        this.trsfrSum = trsfrSum;
    }

    public BigDecimal getTrsfrSumFC() {
        return trsfrSumFC;
    }

    public void setTrsfrSumFC(BigDecimal trsfrSumFC) {
        this.trsfrSumFC = trsfrSumFC;
    }

    public Date getTrsfrDate() {
        return trsfrDate;
    }

    public void setTrsfrDate(Date trsfrDate) {
        this.trsfrDate = trsfrDate;
    }

    public String getTrsfrRef() {
        return trsfrRef;
    }

    public void setTrsfrRef(String trsfrRef) {
        this.trsfrRef = trsfrRef;
    }

    public Character getPayNoDoc() {
        return payNoDoc;
    }

    public void setPayNoDoc(Character payNoDoc) {
        this.payNoDoc = payNoDoc;
    }

    public BigDecimal getNoDocSum() {
        return noDocSum;
    }

    public void setNoDocSum(BigDecimal noDocSum) {
        this.noDocSum = noDocSum;
    }

    public BigDecimal getNoDocSumFC() {
        return noDocSumFC;
    }

    public void setNoDocSumFC(BigDecimal noDocSumFC) {
        this.noDocSumFC = noDocSumFC;
    }

    public String getDocCurr() {
        return docCurr;
    }

    public void setDocCurr(String docCurr) {
        this.docCurr = docCurr;
    }

    public Character getDiffCurr() {
        return diffCurr;
    }

    public void setDiffCurr(Character diffCurr) {
        this.diffCurr = diffCurr;
    }

    public BigDecimal getDocRate() {
        return docRate;
    }

    public void setDocRate(BigDecimal docRate) {
        this.docRate = docRate;
    }

    public BigDecimal getSysRate() {
        return sysRate;
    }

    public void setSysRate(BigDecimal sysRate) {
        this.sysRate = sysRate;
    }

    public BigDecimal getDocTotal() {
        return docTotal;
    }

    public void setDocTotal(BigDecimal docTotal) {
        this.docTotal = docTotal;
    }

    public BigDecimal getDocTotalFC() {
        return docTotalFC;
    }

    public void setDocTotalFC(BigDecimal docTotalFC) {
        this.docTotalFC = docTotalFC;
    }

    public String getRef1() {
        return ref1;
    }

    public void setRef1(String ref1) {
        this.ref1 = ref1;
    }

    public String getRef2() {
        return ref2;
    }

    public void setRef2(String ref2) {
        this.ref2 = ref2;
    }

    public String getCounterRef() {
        return counterRef;
    }

    public void setCounterRef(String counterRef) {
        this.counterRef = counterRef;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getJrnlMemo() {
        return jrnlMemo;
    }

    public void setJrnlMemo(String jrnlMemo) {
        this.jrnlMemo = jrnlMemo;
    }

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public Short getDocTime() {
        return docTime;
    }

    public void setDocTime(Short docTime) {
        this.docTime = docTime;
    }

    public Character getShowAtCard() {
        return showAtCard;
    }

    public void setShowAtCard(Character showAtCard) {
        this.showAtCard = showAtCard;
    }

    public Character getSpiltTrans() {
        return spiltTrans;
    }

    public void setSpiltTrans(Character spiltTrans) {
        this.spiltTrans = spiltTrans;
    }

    public Character getCreateTran() {
        return createTran;
    }

    public void setCreateTran(Character createTran) {
        this.createTran = createTran;
    }

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    public Integer getCntctCode() {
        return cntctCode;
    }

    public void setCntctCode(Integer cntctCode) {
        this.cntctCode = cntctCode;
    }

    public BigDecimal getDdctSumSy() {
        return ddctSumSy;
    }

    public void setDdctSumSy(BigDecimal ddctSumSy) {
        this.ddctSumSy = ddctSumSy;
    }

    public BigDecimal getCashSumSy() {
        return cashSumSy;
    }

    public void setCashSumSy(BigDecimal cashSumSy) {
        this.cashSumSy = cashSumSy;
    }

    public BigDecimal getCredSumSy() {
        return credSumSy;
    }

    public void setCredSumSy(BigDecimal credSumSy) {
        this.credSumSy = credSumSy;
    }

    public BigDecimal getCheckSumSy() {
        return checkSumSy;
    }

    public void setCheckSumSy(BigDecimal checkSumSy) {
        this.checkSumSy = checkSumSy;
    }

    public BigDecimal getTrsfrSumSy() {
        return trsfrSumSy;
    }

    public void setTrsfrSumSy(BigDecimal trsfrSumSy) {
        this.trsfrSumSy = trsfrSumSy;
    }

    public BigDecimal getNoDocSumSy() {
        return noDocSumSy;
    }

    public void setNoDocSumSy(BigDecimal noDocSumSy) {
        this.noDocSumSy = noDocSumSy;
    }

    public BigDecimal getDocTotalSy() {
        return docTotalSy;
    }

    public void setDocTotalSy(BigDecimal docTotalSy) {
        this.docTotalSy = docTotalSy;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public BigDecimal getStornoRate() {
        return stornoRate;
    }

    public void setStornoRate(BigDecimal stornoRate) {
        this.stornoRate = stornoRate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Character getApplyVAT() {
        return applyVAT;
    }

    public void setApplyVAT(Character applyVAT) {
        this.applyVAT = applyVAT;
    }

    public Date getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(Date taxDate) {
        this.taxDate = taxDate;
    }

    public Short getSeries() {
        return series;
    }

    public void setSeries(Short series) {
        this.series = series;
    }

    public Character getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Character confirmed) {
        this.confirmed = confirmed;
    }

    public Character getShowJDT() {
        return showJDT;
    }

    public void setShowJDT(Character showJDT) {
        this.showJDT = showJDT;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAcct() {
        return bankAcct;
    }

    public void setBankAcct(String bankAcct) {
        this.bankAcct = bankAcct;
    }

    public Character getDataSource() {
        return dataSource;
    }

    public void setDataSource(Character dataSource) {
        this.dataSource = dataSource;
    }

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public String getVatGroup() {
        return vatGroup;
    }

    public void setVatGroup(String vatGroup) {
        this.vatGroup = vatGroup;
    }

    public BigDecimal getVatSum() {
        return vatSum;
    }

    public void setVatSum(BigDecimal vatSum) {
        this.vatSum = vatSum;
    }

    public BigDecimal getVatSumFC() {
        return vatSumFC;
    }

    public void setVatSumFC(BigDecimal vatSumFC) {
        this.vatSumFC = vatSumFC;
    }

    public BigDecimal getVatSumSy() {
        return vatSumSy;
    }

    public void setVatSumSy(BigDecimal vatSumSy) {
        this.vatSumSy = vatSumSy;
    }

    public Integer getFinncPriod() {
        return finncPriod;
    }

    public void setFinncPriod(Integer finncPriod) {
        this.finncPriod = finncPriod;
    }

    public BigDecimal getVatPrcnt() {
        return vatPrcnt;
    }

    public void setVatPrcnt(BigDecimal vatPrcnt) {
        this.vatPrcnt = vatPrcnt;
    }

    public BigDecimal getDcount() {
        return dcount;
    }

    public void setDcount(BigDecimal dcount) {
        this.dcount = dcount;
    }

    public BigDecimal getDcntSum() {
        return dcntSum;
    }

    public void setDcntSum(BigDecimal dcntSum) {
        this.dcntSum = dcntSum;
    }

    public BigDecimal getDcntSumFC() {
        return dcntSumFC;
    }

    public void setDcntSumFC(BigDecimal dcntSumFC) {
        this.dcntSumFC = dcntSumFC;
    }

    public BigDecimal getDcntSumSy() {
        return dcntSumSy;
    }

    public void setDcntSumSy(BigDecimal dcntSumSy) {
        this.dcntSumSy = dcntSumSy;
    }

    public Character getSpltCredLn() {
        return spltCredLn;
    }

    public void setSpltCredLn(Character spltCredLn) {
        this.spltCredLn = spltCredLn;
    }

    public String getPrjCode() {
        return prjCode;
    }

    public void setPrjCode(String prjCode) {
        this.prjCode = prjCode;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public Character getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Character submitted) {
        this.submitted = submitted;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getPayMth() {
        return payMth;
    }

    public void setPayMth(String payMth) {
        this.payMth = payMth;
    }

    public String getBankCountr() {
        return bankCountr;
    }

    public void setBankCountr(String bankCountr) {
        this.bankCountr = bankCountr;
    }

    public BigDecimal getFreightSum() {
        return freightSum;
    }

    public void setFreightSum(BigDecimal freightSum) {
        this.freightSum = freightSum;
    }

    public BigDecimal getFreigtFC() {
        return freigtFC;
    }

    public void setFreigtFC(BigDecimal freigtFC) {
        this.freigtFC = freigtFC;
    }

    public BigDecimal getFreigtSC() {
        return freigtSC;
    }

    public void setFreigtSC(BigDecimal freigtSC) {
        this.freigtSC = freigtSC;
    }

    public String getBoeAcc() {
        return boeAcc;
    }

    public void setBoeAcc(String boeAcc) {
        this.boeAcc = boeAcc;
    }

    public Integer getBoeNum() {
        return boeNum;
    }

    public void setBoeNum(Integer boeNum) {
        this.boeNum = boeNum;
    }

    public BigDecimal getBoeSum() {
        return boeSum;
    }

    public void setBoeSum(BigDecimal boeSum) {
        this.boeSum = boeSum;
    }

    public BigDecimal getBoeSumFc() {
        return boeSumFc;
    }

    public void setBoeSumFc(BigDecimal boeSumFc) {
        this.boeSumFc = boeSumFc;
    }

    public BigDecimal getBoeSumSc() {
        return boeSumSc;
    }

    public void setBoeSumSc(BigDecimal boeSumSc) {
        this.boeSumSc = boeSumSc;
    }

    public String getBoeAgent() {
        return boeAgent;
    }

    public void setBoeAgent(String boeAgent) {
        this.boeAgent = boeAgent;
    }

    public Character getBoeStatus() {
        return boeStatus;
    }

    public void setBoeStatus(Character boeStatus) {
        this.boeStatus = boeStatus;
    }

    public String getWtCode() {
        return wtCode;
    }

    public void setWtCode(String wtCode) {
        this.wtCode = wtCode;
    }

    public BigDecimal getWtSum() {
        return wtSum;
    }

    public void setWtSum(BigDecimal wtSum) {
        this.wtSum = wtSum;
    }

    public BigDecimal getWtSumFrgn() {
        return wtSumFrgn;
    }

    public void setWtSumFrgn(BigDecimal wtSumFrgn) {
        this.wtSumFrgn = wtSumFrgn;
    }

    public BigDecimal getWtSumSys() {
        return wtSumSys;
    }

    public void setWtSumSys(BigDecimal wtSumSys) {
        this.wtSumSys = wtSumSys;
    }

    public String getWtAccount() {
        return wtAccount;
    }

    public void setWtAccount(String wtAccount) {
        this.wtAccount = wtAccount;
    }

    public BigDecimal getWtBaseAmnt() {
        return wtBaseAmnt;
    }

    public void setWtBaseAmnt(BigDecimal wtBaseAmnt) {
        this.wtBaseAmnt = wtBaseAmnt;
    }

    public Character getProforma() {
        return proforma;
    }

    public void setProforma(Character proforma) {
        this.proforma = proforma;
    }

    public Integer getBoeAbs() {
        return boeAbs;
    }

    public void setBoeAbs(Integer boeAbs) {
        this.boeAbs = boeAbs;
    }

    public String getBpAct() {
        return bpAct;
    }

    public void setBpAct(String bpAct) {
        this.bpAct = bpAct;
    }

    public BigDecimal getBcgSum() {
        return bcgSum;
    }

    public void setBcgSum(BigDecimal bcgSum) {
        this.bcgSum = bcgSum;
    }

    public BigDecimal getBcgSumFC() {
        return bcgSumFC;
    }

    public void setBcgSumFC(BigDecimal bcgSumFC) {
        this.bcgSumFC = bcgSumFC;
    }

    public BigDecimal getBcgSumSy() {
        return bcgSumSy;
    }

    public void setBcgSumSy(BigDecimal bcgSumSy) {
        this.bcgSumSy = bcgSumSy;
    }

    public String getpIndicator() {
        return pIndicator;
    }

    public void setpIndicator(String pIndicator) {
        this.pIndicator = pIndicator;
    }

    public Character getPaPriority() {
        return paPriority;
    }

    public void setPaPriority(Character paPriority) {
        this.paPriority = paPriority;
    }

    public String getPayToCode() {
        return payToCode;
    }

    public void setPayToCode(String payToCode) {
        this.payToCode = payToCode;
    }

    public Character getIsPaytoBnk() {
        return isPaytoBnk;
    }

    public void setIsPaytoBnk(Character isPaytoBnk) {
        this.isPaytoBnk = isPaytoBnk;
    }

    public String getpBnkCnt() {
        return pBnkCnt;
    }

    public void setpBnkCnt(String pBnkCnt) {
        this.pBnkCnt = pBnkCnt;
    }

    public String getpBnkCode() {
        return pBnkCode;
    }

    public void setpBnkCode(String pBnkCode) {
        this.pBnkCode = pBnkCode;
    }

    public String getpBnkAccnt() {
        return pBnkAccnt;
    }

    public void setpBnkAccnt(String pBnkAccnt) {
        this.pBnkAccnt = pBnkAccnt;
    }

    public String getpBnkBranch() {
        return pBnkBranch;
    }

    public void setpBnkBranch(String pBnkBranch) {
        this.pBnkBranch = pBnkBranch;
    }

    public Character getWizDunBlck() {
        return wizDunBlck;
    }

    public void setWizDunBlck(Character wizDunBlck) {
        this.wizDunBlck = wizDunBlck;
    }

    public BigDecimal getWtBaseSum() {
        return wtBaseSum;
    }

    public void setWtBaseSum(BigDecimal wtBaseSum) {
        this.wtBaseSum = wtBaseSum;
    }

    public BigDecimal getWtBaseSumF() {
        return wtBaseSumF;
    }

    public void setWtBaseSumF(BigDecimal wtBaseSumF) {
        this.wtBaseSumF = wtBaseSumF;
    }

    public BigDecimal getWtBaseSumS() {
        return wtBaseSumS;
    }

    public void setWtBaseSumS(BigDecimal wtBaseSumS) {
        this.wtBaseSumS = wtBaseSumS;
    }

    public BigDecimal getUndOvDiff() {
        return undOvDiff;
    }

    public void setUndOvDiff(BigDecimal undOvDiff) {
        this.undOvDiff = undOvDiff;
    }

    public BigDecimal getUndOvDiffS() {
        return undOvDiffS;
    }

    public void setUndOvDiffS(BigDecimal undOvDiffS) {
        this.undOvDiffS = undOvDiffS;
    }

    public Integer getBankActKey() {
        return bankActKey;
    }

    public void setBankActKey(Integer bankActKey) {
        this.bankActKey = bankActKey;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public Date getVatDate() {
        return vatDate;
    }

    public void setVatDate(Date vatDate) {
        this.vatDate = vatDate;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public Character getPaymType() {
        return paymType;
    }

    public void setPaymType(Character paymType) {
        this.paymType = paymType;
    }

    public BigDecimal getTfrRealAmt() {
        return tfrRealAmt;
    }

    public void setTfrRealAmt(BigDecimal tfrRealAmt) {
        this.tfrRealAmt = tfrRealAmt;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public BigDecimal getOpenBal() {
        return openBal;
    }

    public void setOpenBal(BigDecimal openBal) {
        this.openBal = openBal;
    }

    public BigDecimal getOpenBalFc() {
        return openBalFc;
    }

    public void setOpenBalFc(BigDecimal openBalFc) {
        this.openBalFc = openBalFc;
    }

    public BigDecimal getOpenBalSc() {
        return openBalSc;
    }

    public void setOpenBalSc(BigDecimal openBalSc) {
        this.openBalSc = openBalSc;
    }

    public BigDecimal getBcgTaxSum() {
        return bcgTaxSum;
    }

    public void setBcgTaxSum(BigDecimal bcgTaxSum) {
        this.bcgTaxSum = bcgTaxSum;
    }

    public BigDecimal getBcgTaxSumF() {
        return bcgTaxSumF;
    }

    public void setBcgTaxSumF(BigDecimal bcgTaxSumF) {
        this.bcgTaxSumF = bcgTaxSumF;
    }

    public BigDecimal getBcgTaxSumS() {
        return bcgTaxSumS;
    }

    public void setBcgTaxSumS(BigDecimal bcgTaxSumS) {
        this.bcgTaxSumS = bcgTaxSumS;
    }

    public Integer getTpwID() {
        return tpwID;
    }

    public void setTpwID(Integer tpwID) {
        this.tpwID = tpwID;
    }

    public String getChallanNo() {
        return challanNo;
    }

    public void setChallanNo(String challanNo) {
        this.challanNo = challanNo;
    }

    public String getChallanBak() {
        return challanBak;
    }

    public void setChallanBak(String challanBak) {
        this.challanBak = challanBak;
    }

    public Date getChallanDat() {
        return challanDat;
    }

    public void setChallanDat(Date challanDat) {
        this.challanDat = challanDat;
    }

    public Character getWddStatus() {
        return wddStatus;
    }

    public void setWddStatus(Character wddStatus) {
        this.wddStatus = wddStatus;
    }

    public String getBcgVatGrp() {
        return bcgVatGrp;
    }

    public void setBcgVatGrp(String bcgVatGrp) {
        this.bcgVatGrp = bcgVatGrp;
    }

    public BigDecimal getBcgVatPcnt() {
        return bcgVatPcnt;
    }

    public void setBcgVatPcnt(BigDecimal bcgVatPcnt) {
        this.bcgVatPcnt = bcgVatPcnt;
    }

    public Short getSeqCode() {
        return seqCode;
    }

    public void setSeqCode(Short seqCode) {
        this.seqCode = seqCode;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getSeriesStr() {
        return seriesStr;
    }

    public void setSeriesStr(String seriesStr) {
        this.seriesStr = seriesStr;
    }

    public String getSubStr() {
        return subStr;
    }

    public void setSubStr(String subStr) {
        this.subStr = subStr;
    }

    public String getbSRCode() {
        return bSRCode;
    }

    public void setbSRCode(String bSRCode) {
        this.bSRCode = bSRCode;
    }

    public Integer getLocCode() {
        return locCode;
    }

    public void setLocCode(Integer locCode) {
        this.locCode = locCode;
    }

    public BigDecimal getwTOnhldPst() {
        return wTOnhldPst;
    }

    public void setwTOnhldPst(BigDecimal wTOnhldPst) {
        this.wTOnhldPst = wTOnhldPst;
    }

    public Short getUserSign2() {
        return userSign2;
    }

    public void setUserSign2(Short userSign2) {
        this.userSign2 = userSign2;
    }

    public String getBuildDesc() {
        return buildDesc;
    }

    public void setBuildDesc(String buildDesc) {
        this.buildDesc = buildDesc;
    }

    public Character getResidenNum() {
        return residenNum;
    }

    public void setResidenNum(Character residenNum) {
        this.residenNum = residenNum;
    }

    public Character getOperatCode() {
        return operatCode;
    }

    public void setOperatCode(Character operatCode) {
        this.operatCode = operatCode;
    }

    public BigDecimal getUndOvDiffF() {
        return undOvDiffF;
    }

    public void setUndOvDiffF(BigDecimal undOvDiffF) {
        this.undOvDiffF = undOvDiffF;
    }

    public Integer getmIEntry() {
        return mIEntry;
    }

    public void setmIEntry(Integer mIEntry) {
        this.mIEntry = mIEntry;
    }

    public String getFreeText1() {
        return freeText1;
    }

    public void setFreeText1(String freeText1) {
        this.freeText1 = freeText1;
    }

    public String getFreeText2() {
        return freeText2;
    }

    public void setFreeText2(String freeText2) {
        this.freeText2 = freeText2;
    }

    public String getFreeText3() {
        return freeText3;
    }

    public void setFreeText3(String freeText3) {
        this.freeText3 = freeText3;
    }

    public Character getShowDocNo() {
        return showDocNo;
    }

    public void setShowDocNo(Character showDocNo) {
        this.showDocNo = showDocNo;
    }

    public BigDecimal gettDSInterst() {
        return tDSInterst;
    }

    public void settDSInterst(BigDecimal tDSInterst) {
        this.tDSInterst = tDSInterst;
    }

    public BigDecimal gettDSCharges() {
        return tDSCharges;
    }

    public void settDSCharges(BigDecimal tDSCharges) {
        this.tDSCharges = tDSCharges;
    }

    public Integer getCup() {
        return cup;
    }

    public void setCup(Integer cup) {
        this.cup = cup;
    }

    public Integer getCig() {
        return cig;
    }

    public void setCig(Integer cig) {
        this.cig = cig;
    }

    public String getmIType() {
        return mIType;
    }

    public void setmIType(String mIType) {
        this.mIType = mIType;
    }

    public String getSupplCode() {
        return supplCode;
    }

    public void setSupplCode(String supplCode) {
        this.supplCode = supplCode;
    }

    public Integer getbPLId() {
        return bPLId;
    }

    public void setbPLId(Integer bPLId) {
        this.bPLId = bPLId;
    }

    public String getbPLName() {
        return bPLName;
    }

    public void setbPLName(String bPLName) {
        this.bPLName = bPLName;
    }

    public String getvATRegNum() {
        return vATRegNum;
    }

    public void setvATRegNum(String vATRegNum) {
        this.vATRegNum = vATRegNum;
    }

    public Character getbPLCentPmt() {
        return bPLCentPmt;
    }

    public void setbPLCentPmt(Character bPLCentPmt) {
        this.bPLCentPmt = bPLCentPmt;
    }

    public Integer getDraftKey() {
        return draftKey;
    }

    public void setDraftKey(Integer draftKey) {
        this.draftKey = draftKey;
    }

    public BigDecimal gettDSFee() {
        return tDSFee;
    }

    public void settDSFee(BigDecimal tDSFee) {
        this.tDSFee = tDSFee;
    }

    public Integer getMinHeadCL() {
        return minHeadCL;
    }

    public void setMinHeadCL(Integer minHeadCL) {
        this.minHeadCL = minHeadCL;
    }

    public Date getsEPADate() {
        return sEPADate;
    }

    public void setsEPADate(Date sEPADate) {
        this.sEPADate = sEPADate;
    }

    public String getuBPConfd() {
        return uBPConfd;
    }

    public void setuBPConfd(String uBPConfd) {
        this.uBPConfd = uBPConfd;
    }

    public Integer getuBPDocNr() {
        return uBPDocNr;
    }

    public void setuBPDocNr(Integer uBPDocNr) {
        this.uBPDocNr = uBPDocNr;
    }

    public Integer getuBPSeque() {
        return uBPSeque;
    }

    public void setuBPSeque(Integer uBPSeque) {
        this.uBPSeque = uBPSeque;
    }

    public Character getuOk1Ifrs() {
        return uOk1Ifrs;
    }

    public void setuOk1Ifrs(Character uOk1Ifrs) {
        this.uOk1Ifrs = uOk1Ifrs;
    }

    public String getUasesor() {
        return uasesor;
    }

    public void setUasesor(String uasesor) {
        this.uasesor = uasesor;
    }

    public String getuPagoBeneficiario() {
        return uPagoBeneficiario;
    }

    public void setuPagoBeneficiario(String uPagoBeneficiario) {
        this.uPagoBeneficiario = uPagoBeneficiario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docEntry != null ? docEntry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReciboCaja)) {
            return false;
        }
        ReciboCaja other = (ReciboCaja) object;
        if ((this.docEntry == null && other.docEntry != null) || (this.docEntry != null && !this.docEntry.equals(other.docEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.sap.entity.ReciboCaja[ docEntry=" + docEntry + " ]";
    }
}
