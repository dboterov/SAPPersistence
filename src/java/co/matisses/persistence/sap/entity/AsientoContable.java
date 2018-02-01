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
@Table(name = "OJDT")
public class AsientoContable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "BatchNum")
    private Integer batchNum;
    @Id
    @Basic(optional = false)
    @Column(name = "TransId")
    private Integer transId;
    @Column(name = "BtfStatus")
    private Character btfStatus;
    @Column(name = "TransType")
    private String transType;
    @Column(name = "BaseRef")
    private String baseRef;
    @Column(name = "RefDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date refDate;
    @Column(name = "Memo")
    private String memo;
    @Column(name = "Ref1")
    private String ref1;
    @Column(name = "Ref2")
    private String ref2;
    @Column(name = "CreatedBy")
    private Integer createdBy;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LocTotal")
    private BigDecimal locTotal;
    @Column(name = "FcTotal")
    private BigDecimal fcTotal;
    @Column(name = "SysTotal")
    private BigDecimal sysTotal;
    @Column(name = "TransCode")
    private String transCode;
    @Column(name = "OrignCurr")
    private String orignCurr;
    @Column(name = "TransRate")
    private BigDecimal transRate;
    @Column(name = "BtfLine")
    private Integer btfLine;
    @Column(name = "TransCurr")
    private String transCurr;
    @Column(name = "Project")
    private String project;
    @Column(name = "DueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Column(name = "TaxDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taxDate;
    @Column(name = "PCAddition")
    private Character pCAddition;
    @Column(name = "FinncPriod")
    private Integer finncPriod;
    @Column(name = "DataSource")
    private Character dataSource;
    @Column(name = "UpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "UserSign2")
    private Short userSign2;
    @Column(name = "RefndRprt")
    private Character refndRprt;
    @Column(name = "LogInstanc")
    private Integer logInstanc;
    @Column(name = "ObjType")
    private String objType;
    @Column(name = "Indicator")
    private String indicator;
    @Column(name = "AdjTran")
    private Character adjTran;
    @Column(name = "RevSource")
    private Character revSource;
    @Column(name = "StornoDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stornoDate;
    @Column(name = "StornoToTr")
    private Integer stornoToTr;
    @Column(name = "AutoStorno")
    private Character autoStorno;
    @Column(name = "Corisptivi")
    private Character corisptivi;
    @Column(name = "VatDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vatDate;
    @Column(name = "StampTax")
    private Character stampTax;
    @Basic(optional = false)
    @Column(name = "Series")
    private short series;
    @Basic(optional = false)
    @Column(name = "Number")
    private int number;
    @Column(name = "AutoVAT")
    private Character autoVAT;
    @Column(name = "DocSeries")
    private Short docSeries;
    @Column(name = "FolioPref")
    private String folioPref;
    @Column(name = "FolioNum")
    private Integer folioNum;
    @Column(name = "CreateTime")
    private Short createTime;
    @Column(name = "BlockDunn")
    private Character blockDunn;
    @Column(name = "ReportEU")
    private Character reportEU;
    @Column(name = "Report347")
    private Character report347;
    @Column(name = "Printed")
    private Character printed;
    @Column(name = "DocType")
    private String docType;
    @Column(name = "AttNum")
    private Integer attNum;
    @Column(name = "GenRegNo")
    private Character genRegNo;
    @Column(name = "RG23APart2")
    private Integer rG23APart2;
    @Column(name = "RG23CPart2")
    private Integer rG23CPart2;
    @Column(name = "MatType")
    private Integer matType;
    @Column(name = "Creator")
    private String creator;
    @Column(name = "Approver")
    private String approver;
    @Column(name = "Location")
    private Integer location;
    @Column(name = "SeqCode")
    private Short seqCode;
    @Column(name = "Serial")
    private Integer serial;
    @Column(name = "SeriesStr")
    private String seriesStr;
    @Column(name = "SubStr")
    private String subStr;
    @Column(name = "AutoWT")
    private Character autoWT;
    @Column(name = "WTSum")
    private BigDecimal wTSum;
    @Column(name = "WTSumSC")
    private BigDecimal wTSumSC;
    @Column(name = "WTSumFC")
    private BigDecimal wTSumFC;
    @Column(name = "WTApplied")
    private BigDecimal wTApplied;
    @Column(name = "WTAppliedS")
    private BigDecimal wTAppliedS;
    @Column(name = "WTAppliedF")
    private BigDecimal wTAppliedF;
    @Column(name = "BaseAmnt")
    private BigDecimal baseAmnt;
    @Column(name = "BaseAmntSC")
    private BigDecimal baseAmntSC;
    @Column(name = "BaseAmntFC")
    private BigDecimal baseAmntFC;
    @Column(name = "BaseVtAt")
    private BigDecimal baseVtAt;
    @Column(name = "BaseVtAtSC")
    private BigDecimal baseVtAtSC;
    @Column(name = "BaseVtAtFC")
    private BigDecimal baseVtAtFC;
    @Column(name = "VersionNum")
    private String versionNum;
    @Column(name = "BaseTrans")
    private Integer baseTrans;
    @Column(name = "ResidenNum")
    private Character residenNum;
    @Column(name = "OperatCode")
    private Character operatCode;
    @Column(name = "Ref3")
    private String ref3;
    @Column(name = "SSIExmpt")
    private Character sSIExmpt;
    @Column(name = "SignMsg")
    private String signMsg;
    @Column(name = "SignDigest")
    private String signDigest;
    @Column(name = "CertifNum")
    private String certifNum;
    @Column(name = "KeyVersion")
    private Integer keyVersion;
    @Column(name = "CUP")
    private Integer cup;
    @Column(name = "CIG")
    private Integer cig;
    @Column(name = "SupplCode")
    private String supplCode;
    @Column(name = "SPSrcType")
    private Integer sPSrcType;
    @Column(name = "SPSrcID")
    private Integer sPSrcID;
    @Column(name = "SPSrcDLN")
    private Integer sPSrcDLN;
    @Column(name = "DeferedTax")
    private Character deferedTax;
    @Column(name = "AgrNo")
    private Integer agrNo;
    @Column(name = "SeqNum")
    private Integer seqNum;
    @Column(name = "ECDPosTyp")
    private Character eCDPosTyp;
    @Column(name = "RptPeriod")
    private String rptPeriod;
    @Column(name = "RptMonth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rptMonth;
    @Column(name = "ExTransId")
    private Integer exTransId;
    @Column(name = "PrlLinked")
    private Character prlLinked;
    @Column(name = "PTICode")
    private String pTICode;
    @Column(name = "Letter")
    private Character letter;
    @Column(name = "FolNumFrom")
    private Integer folNumFrom;
    @Column(name = "FolNumTo")
    private Integer folNumTo;
    @Column(name = "U_ClaveDoc")
    private Integer uClaveDoc;
    @Column(name = "U_TipoDoc")
    private String uTipoDoc;
    @Column(name = "U_VrAct")
    private BigDecimal uVrAct;
    @Column(name = "U_BA_TCODE")
    private String uBaTcode;
    @Column(name = "U_BA_REF")
    private String uBaRef;
    @Column(name = "U_OK1_IFRS")
    private Character uOk1Ifrs;
    @Column(name = "U_SerieDoc")
    private String uSerieDoc;
    @Column(name = "U_DifCode")
    private String uDifCode;

    public AsientoContable() {
    }

    public AsientoContable(Integer transId) {
        this.transId = transId;
    }

    public AsientoContable(Integer batchNum, Integer transId, Character btfStatus, String transType, String baseRef, Date refDate, String memo, String ref1,
            String ref2, Integer createdBy, BigDecimal locTotal, BigDecimal fcTotal, BigDecimal sysTotal, String transCode, String orignCurr, BigDecimal transRate,
            Integer btfLine, String transCurr, String project, Date dueDate, Date taxDate, Character pCAddition, Integer finncPriod, Character dataSource,
            Date updateDate, Date createDate, Short userSign, Short userSign2, Character refndRprt, Integer logInstanc, String objType, String indicator,
            Character adjTran, Character revSource, Date stornoDate, Integer stornoToTr, Character autoStorno, Character corisptivi, Date vatDate, Character stampTax,
            short series, int number, Character autoVAT, Short docSeries, String folioPref, Integer folioNum, Short createTime, Character blockDunn, Character reportEU,
            Character report347, Character printed, String docType, Integer attNum, Character genRegNo, Integer rG23APart2, Integer rG23CPart2, Integer matType,
            String creator, String approver, Integer location, Short seqCode, Integer serial, String seriesStr, String subStr, Character autoWT, BigDecimal wTSum,
            BigDecimal wTSumSC, BigDecimal wTSumFC, BigDecimal wTApplied, BigDecimal wTAppliedS, BigDecimal wTAppliedF, BigDecimal baseAmnt, BigDecimal baseAmntSC,
            BigDecimal baseAmntFC, BigDecimal baseVtAt, BigDecimal baseVtAtSC, BigDecimal baseVtAtFC, String versionNum, Integer baseTrans, Character residenNum,
            Character operatCode, String ref3, Character sSIExmpt, String signMsg, String signDigest, String certifNum, Integer keyVersion, Integer cup, Integer cig,
            String supplCode, Integer sPSrcType, Integer sPSrcID, Integer sPSrcDLN, Character deferedTax, Integer agrNo, Integer seqNum, Character eCDPosTyp, String rptPeriod,
            Date rptMonth, Integer exTransId, Character prlLinked, String pTICode, Character letter, Integer folNumFrom, Integer folNumTo, Integer uClaveDoc, String uTipoDoc,
            BigDecimal uVrAct, String uBaTcode, String uBaRef, Character uOk1Ifrs, String uSerieDoc, String uDifCode) {
        this.batchNum = batchNum;
        this.transId = transId;
        this.btfStatus = btfStatus;
        this.transType = transType;
        this.baseRef = baseRef;
        this.refDate = refDate;
        this.memo = memo;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.createdBy = createdBy;
        this.locTotal = locTotal;
        this.fcTotal = fcTotal;
        this.sysTotal = sysTotal;
        this.transCode = transCode;
        this.orignCurr = orignCurr;
        this.transRate = transRate;
        this.btfLine = btfLine;
        this.transCurr = transCurr;
        this.project = project;
        this.dueDate = dueDate;
        this.taxDate = taxDate;
        this.pCAddition = pCAddition;
        this.finncPriod = finncPriod;
        this.dataSource = dataSource;
        this.updateDate = updateDate;
        this.createDate = createDate;
        this.userSign = userSign;
        this.userSign2 = userSign2;
        this.refndRprt = refndRprt;
        this.logInstanc = logInstanc;
        this.objType = objType;
        this.indicator = indicator;
        this.adjTran = adjTran;
        this.revSource = revSource;
        this.stornoDate = stornoDate;
        this.stornoToTr = stornoToTr;
        this.autoStorno = autoStorno;
        this.corisptivi = corisptivi;
        this.vatDate = vatDate;
        this.stampTax = stampTax;
        this.series = series;
        this.number = number;
        this.autoVAT = autoVAT;
        this.docSeries = docSeries;
        this.folioPref = folioPref;
        this.folioNum = folioNum;
        this.createTime = createTime;
        this.blockDunn = blockDunn;
        this.reportEU = reportEU;
        this.report347 = report347;
        this.printed = printed;
        this.docType = docType;
        this.attNum = attNum;
        this.genRegNo = genRegNo;
        this.rG23APart2 = rG23APart2;
        this.rG23CPart2 = rG23CPart2;
        this.matType = matType;
        this.creator = creator;
        this.approver = approver;
        this.location = location;
        this.seqCode = seqCode;
        this.serial = serial;
        this.seriesStr = seriesStr;
        this.subStr = subStr;
        this.autoWT = autoWT;
        this.wTSum = wTSum;
        this.wTSumSC = wTSumSC;
        this.wTSumFC = wTSumFC;
        this.wTApplied = wTApplied;
        this.wTAppliedS = wTAppliedS;
        this.wTAppliedF = wTAppliedF;
        this.baseAmnt = baseAmnt;
        this.baseAmntSC = baseAmntSC;
        this.baseAmntFC = baseAmntFC;
        this.baseVtAt = baseVtAt;
        this.baseVtAtSC = baseVtAtSC;
        this.baseVtAtFC = baseVtAtFC;
        this.versionNum = versionNum;
        this.baseTrans = baseTrans;
        this.residenNum = residenNum;
        this.operatCode = operatCode;
        this.ref3 = ref3;
        this.sSIExmpt = sSIExmpt;
        this.signMsg = signMsg;
        this.signDigest = signDigest;
        this.certifNum = certifNum;
        this.keyVersion = keyVersion;
        this.cup = cup;
        this.cig = cig;
        this.supplCode = supplCode;
        this.sPSrcType = sPSrcType;
        this.sPSrcID = sPSrcID;
        this.sPSrcDLN = sPSrcDLN;
        this.deferedTax = deferedTax;
        this.agrNo = agrNo;
        this.seqNum = seqNum;
        this.eCDPosTyp = eCDPosTyp;
        this.rptPeriod = rptPeriod;
        this.rptMonth = rptMonth;
        this.exTransId = exTransId;
        this.prlLinked = prlLinked;
        this.pTICode = pTICode;
        this.letter = letter;
        this.folNumFrom = folNumFrom;
        this.folNumTo = folNumTo;
        this.uClaveDoc = uClaveDoc;
        this.uTipoDoc = uTipoDoc;
        this.uVrAct = uVrAct;
        this.uBaTcode = uBaTcode;
        this.uBaRef = uBaRef;
        this.uOk1Ifrs = uOk1Ifrs;
        this.uSerieDoc = uSerieDoc;
        this.uDifCode = uDifCode;
    }

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public Character getBtfStatus() {
        return btfStatus;
    }

    public void setBtfStatus(Character btfStatus) {
        this.btfStatus = btfStatus;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getBaseRef() {
        return baseRef;
    }

    public void setBaseRef(String baseRef) {
        this.baseRef = baseRef;
    }

    public Date getRefDate() {
        return refDate;
    }

    public void setRefDate(Date refDate) {
        this.refDate = refDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public BigDecimal getLocTotal() {
        return locTotal;
    }

    public void setLocTotal(BigDecimal locTotal) {
        this.locTotal = locTotal;
    }

    public BigDecimal getFcTotal() {
        return fcTotal;
    }

    public void setFcTotal(BigDecimal fcTotal) {
        this.fcTotal = fcTotal;
    }

    public BigDecimal getSysTotal() {
        return sysTotal;
    }

    public void setSysTotal(BigDecimal sysTotal) {
        this.sysTotal = sysTotal;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getOrignCurr() {
        return orignCurr;
    }

    public void setOrignCurr(String orignCurr) {
        this.orignCurr = orignCurr;
    }

    public BigDecimal getTransRate() {
        return transRate;
    }

    public void setTransRate(BigDecimal transRate) {
        this.transRate = transRate;
    }

    public Integer getBtfLine() {
        return btfLine;
    }

    public void setBtfLine(Integer btfLine) {
        this.btfLine = btfLine;
    }

    public String getTransCurr() {
        return transCurr;
    }

    public void setTransCurr(String transCurr) {
        this.transCurr = transCurr;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(Date taxDate) {
        this.taxDate = taxDate;
    }

    public Character getpCAddition() {
        return pCAddition;
    }

    public void setpCAddition(Character pCAddition) {
        this.pCAddition = pCAddition;
    }

    public Integer getFinncPriod() {
        return finncPriod;
    }

    public void setFinncPriod(Integer finncPriod) {
        this.finncPriod = finncPriod;
    }

    public Character getDataSource() {
        return dataSource;
    }

    public void setDataSource(Character dataSource) {
        this.dataSource = dataSource;
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

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public Short getUserSign2() {
        return userSign2;
    }

    public void setUserSign2(Short userSign2) {
        this.userSign2 = userSign2;
    }

    public Character getRefndRprt() {
        return refndRprt;
    }

    public void setRefndRprt(Character refndRprt) {
        this.refndRprt = refndRprt;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public Character getAdjTran() {
        return adjTran;
    }

    public void setAdjTran(Character adjTran) {
        this.adjTran = adjTran;
    }

    public Character getRevSource() {
        return revSource;
    }

    public void setRevSource(Character revSource) {
        this.revSource = revSource;
    }

    public Date getStornoDate() {
        return stornoDate;
    }

    public void setStornoDate(Date stornoDate) {
        this.stornoDate = stornoDate;
    }

    public Integer getStornoToTr() {
        return stornoToTr;
    }

    public void setStornoToTr(Integer stornoToTr) {
        this.stornoToTr = stornoToTr;
    }

    public Character getAutoStorno() {
        return autoStorno;
    }

    public void setAutoStorno(Character autoStorno) {
        this.autoStorno = autoStorno;
    }

    public Character getCorisptivi() {
        return corisptivi;
    }

    public void setCorisptivi(Character corisptivi) {
        this.corisptivi = corisptivi;
    }

    public Date getVatDate() {
        return vatDate;
    }

    public void setVatDate(Date vatDate) {
        this.vatDate = vatDate;
    }

    public Character getStampTax() {
        return stampTax;
    }

    public void setStampTax(Character stampTax) {
        this.stampTax = stampTax;
    }

    public short getSeries() {
        return series;
    }

    public void setSeries(short series) {
        this.series = series;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Character getAutoVAT() {
        return autoVAT;
    }

    public void setAutoVAT(Character autoVAT) {
        this.autoVAT = autoVAT;
    }

    public Short getDocSeries() {
        return docSeries;
    }

    public void setDocSeries(Short docSeries) {
        this.docSeries = docSeries;
    }

    public String getFolioPref() {
        return folioPref;
    }

    public void setFolioPref(String folioPref) {
        this.folioPref = folioPref;
    }

    public Integer getFolioNum() {
        return folioNum;
    }

    public void setFolioNum(Integer folioNum) {
        this.folioNum = folioNum;
    }

    public Short getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Short createTime) {
        this.createTime = createTime;
    }

    public Character getBlockDunn() {
        return blockDunn;
    }

    public void setBlockDunn(Character blockDunn) {
        this.blockDunn = blockDunn;
    }

    public Character getReportEU() {
        return reportEU;
    }

    public void setReportEU(Character reportEU) {
        this.reportEU = reportEU;
    }

    public Character getReport347() {
        return report347;
    }

    public void setReport347(Character report347) {
        this.report347 = report347;
    }

    public Character getPrinted() {
        return printed;
    }

    public void setPrinted(Character printed) {
        this.printed = printed;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Integer getAttNum() {
        return attNum;
    }

    public void setAttNum(Integer attNum) {
        this.attNum = attNum;
    }

    public Character getGenRegNo() {
        return genRegNo;
    }

    public void setGenRegNo(Character genRegNo) {
        this.genRegNo = genRegNo;
    }

    public Integer getrG23APart2() {
        return rG23APart2;
    }

    public void setrG23APart2(Integer rG23APart2) {
        this.rG23APart2 = rG23APart2;
    }

    public Integer getrG23CPart2() {
        return rG23CPart2;
    }

    public void setrG23CPart2(Integer rG23CPart2) {
        this.rG23CPart2 = rG23CPart2;
    }

    public Integer getMatType() {
        return matType;
    }

    public void setMatType(Integer matType) {
        this.matType = matType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
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

    public Character getAutoWT() {
        return autoWT;
    }

    public void setAutoWT(Character autoWT) {
        this.autoWT = autoWT;
    }

    public BigDecimal getwTSum() {
        return wTSum;
    }

    public void setwTSum(BigDecimal wTSum) {
        this.wTSum = wTSum;
    }

    public BigDecimal getwTSumSC() {
        return wTSumSC;
    }

    public void setwTSumSC(BigDecimal wTSumSC) {
        this.wTSumSC = wTSumSC;
    }

    public BigDecimal getwTSumFC() {
        return wTSumFC;
    }

    public void setwTSumFC(BigDecimal wTSumFC) {
        this.wTSumFC = wTSumFC;
    }

    public BigDecimal getwTApplied() {
        return wTApplied;
    }

    public void setwTApplied(BigDecimal wTApplied) {
        this.wTApplied = wTApplied;
    }

    public BigDecimal getwTAppliedS() {
        return wTAppliedS;
    }

    public void setwTAppliedS(BigDecimal wTAppliedS) {
        this.wTAppliedS = wTAppliedS;
    }

    public BigDecimal getwTAppliedF() {
        return wTAppliedF;
    }

    public void setwTAppliedF(BigDecimal wTAppliedF) {
        this.wTAppliedF = wTAppliedF;
    }

    public BigDecimal getBaseAmnt() {
        return baseAmnt;
    }

    public void setBaseAmnt(BigDecimal baseAmnt) {
        this.baseAmnt = baseAmnt;
    }

    public BigDecimal getBaseAmntSC() {
        return baseAmntSC;
    }

    public void setBaseAmntSC(BigDecimal baseAmntSC) {
        this.baseAmntSC = baseAmntSC;
    }

    public BigDecimal getBaseAmntFC() {
        return baseAmntFC;
    }

    public void setBaseAmntFC(BigDecimal baseAmntFC) {
        this.baseAmntFC = baseAmntFC;
    }

    public BigDecimal getBaseVtAt() {
        return baseVtAt;
    }

    public void setBaseVtAt(BigDecimal baseVtAt) {
        this.baseVtAt = baseVtAt;
    }

    public BigDecimal getBaseVtAtSC() {
        return baseVtAtSC;
    }

    public void setBaseVtAtSC(BigDecimal baseVtAtSC) {
        this.baseVtAtSC = baseVtAtSC;
    }

    public BigDecimal getBaseVtAtFC() {
        return baseVtAtFC;
    }

    public void setBaseVtAtFC(BigDecimal baseVtAtFC) {
        this.baseVtAtFC = baseVtAtFC;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public Integer getBaseTrans() {
        return baseTrans;
    }

    public void setBaseTrans(Integer baseTrans) {
        this.baseTrans = baseTrans;
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

    public String getRef3() {
        return ref3;
    }

    public void setRef3(String ref3) {
        this.ref3 = ref3;
    }

    public Character getsSIExmpt() {
        return sSIExmpt;
    }

    public void setsSIExmpt(Character sSIExmpt) {
        this.sSIExmpt = sSIExmpt;
    }

    public String getSignMsg() {
        return signMsg;
    }

    public void setSignMsg(String signMsg) {
        this.signMsg = signMsg;
    }

    public String getSignDigest() {
        return signDigest;
    }

    public void setSignDigest(String signDigest) {
        this.signDigest = signDigest;
    }

    public String getCertifNum() {
        return certifNum;
    }

    public void setCertifNum(String certifNum) {
        this.certifNum = certifNum;
    }

    public Integer getKeyVersion() {
        return keyVersion;
    }

    public void setKeyVersion(Integer keyVersion) {
        this.keyVersion = keyVersion;
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

    public String getSupplCode() {
        return supplCode;
    }

    public void setSupplCode(String supplCode) {
        this.supplCode = supplCode;
    }

    public Integer getsPSrcType() {
        return sPSrcType;
    }

    public void setsPSrcType(Integer sPSrcType) {
        this.sPSrcType = sPSrcType;
    }

    public Integer getsPSrcID() {
        return sPSrcID;
    }

    public void setsPSrcID(Integer sPSrcID) {
        this.sPSrcID = sPSrcID;
    }

    public Integer getsPSrcDLN() {
        return sPSrcDLN;
    }

    public void setsPSrcDLN(Integer sPSrcDLN) {
        this.sPSrcDLN = sPSrcDLN;
    }

    public Character getDeferedTax() {
        return deferedTax;
    }

    public void setDeferedTax(Character deferedTax) {
        this.deferedTax = deferedTax;
    }

    public Integer getAgrNo() {
        return agrNo;
    }

    public void setAgrNo(Integer agrNo) {
        this.agrNo = agrNo;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public Character geteCDPosTyp() {
        return eCDPosTyp;
    }

    public void seteCDPosTyp(Character eCDPosTyp) {
        this.eCDPosTyp = eCDPosTyp;
    }

    public String getRptPeriod() {
        return rptPeriod;
    }

    public void setRptPeriod(String rptPeriod) {
        this.rptPeriod = rptPeriod;
    }

    public Date getRptMonth() {
        return rptMonth;
    }

    public void setRptMonth(Date rptMonth) {
        this.rptMonth = rptMonth;
    }

    public Integer getExTransId() {
        return exTransId;
    }

    public void setExTransId(Integer exTransId) {
        this.exTransId = exTransId;
    }

    public Character getPrlLinked() {
        return prlLinked;
    }

    public void setPrlLinked(Character prlLinked) {
        this.prlLinked = prlLinked;
    }

    public String getpTICode() {
        return pTICode;
    }

    public void setpTICode(String pTICode) {
        this.pTICode = pTICode;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public Integer getFolNumFrom() {
        return folNumFrom;
    }

    public void setFolNumFrom(Integer folNumFrom) {
        this.folNumFrom = folNumFrom;
    }

    public Integer getFolNumTo() {
        return folNumTo;
    }

    public void setFolNumTo(Integer folNumTo) {
        this.folNumTo = folNumTo;
    }

    public Integer getuClaveDoc() {
        return uClaveDoc;
    }

    public void setuClaveDoc(Integer uClaveDoc) {
        this.uClaveDoc = uClaveDoc;
    }

    public String getuTipoDoc() {
        return uTipoDoc;
    }

    public void setuTipoDoc(String uTipoDoc) {
        this.uTipoDoc = uTipoDoc;
    }

    public BigDecimal getuVrAct() {
        return uVrAct;
    }

    public void setuVrAct(BigDecimal uVrAct) {
        this.uVrAct = uVrAct;
    }

    public String getuBaTcode() {
        return uBaTcode;
    }

    public void setuBaTcode(String uBaTcode) {
        this.uBaTcode = uBaTcode;
    }

    public String getuBaRef() {
        return uBaRef;
    }

    public void setuBaRef(String uBaRef) {
        this.uBaRef = uBaRef;
    }

    public Character getuOk1Ifrs() {
        return uOk1Ifrs;
    }

    public void setuOk1Ifrs(Character uOk1Ifrs) {
        this.uOk1Ifrs = uOk1Ifrs;
    }

    public String getuSerieDoc() {
        return uSerieDoc;
    }

    public void setuSerieDoc(String uSerieDoc) {
        this.uSerieDoc = uSerieDoc;
    }

    public String getuDifCode() {
        return uDifCode;
    }

    public void setuDifCode(String uDifCode) {
        this.uDifCode = uDifCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transId != null ? transId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsientoContable)) {
            return false;
        }
        AsientoContable other = (AsientoContable) object;
        if ((this.transId == null && other.transId != null) || (this.transId != null && !this.transId.equals(other.transId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.persistence.sap.entity.AsientoContable[ TransId=" + transId + " ]";
    }
}
