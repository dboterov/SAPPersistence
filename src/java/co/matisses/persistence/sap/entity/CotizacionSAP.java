package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OQUT")
@NamedQueries({
    @NamedQuery(name = "CotizacionSAP.findAll", query = "SELECT c FROM CotizacionSAP c")})
public class CotizacionSAP implements Serializable {

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
    @Column(name = "CANCELED")
    private Character canceled;
    @Column(name = "Handwrtten")
    private Character handwrtten;
    @Column(name = "Printed")
    private Character printed;
    @Column(name = "DocStatus")
    private Character docStatus;
    @Column(name = "InvntSttus")
    private Character invntSttus;
    @Column(name = "Transfered")
    private Character transfered;
    @Column(name = "ObjType")
    private String objType;
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
    @Column(name = "NumAtCard")
    private String numAtCard;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VatPercent")
    private BigDecimal vatPercent;
    @Column(name = "VatSum")
    private BigDecimal vatSum;
    @Column(name = "VatSumFC")
    private BigDecimal vatSumFC;
    @Column(name = "DiscPrcnt")
    private BigDecimal discPrcnt;
    @Column(name = "DiscSum")
    private BigDecimal discSum;
    @Column(name = "DiscSumFC")
    private BigDecimal discSumFC;
    @Column(name = "DocCur")
    private String docCur;
    @Column(name = "DocRate")
    private BigDecimal docRate;
    @Column(name = "DocTotal")
    private BigDecimal docTotal;
    @Column(name = "DocTotalFC")
    private BigDecimal docTotalFC;
    @Column(name = "PaidToDate")
    private BigDecimal paidToDate;
    @Column(name = "PaidFC")
    private BigDecimal paidFC;
    @Column(name = "GrosProfit")
    private BigDecimal grosProfit;
    @Column(name = "GrosProfFC")
    private BigDecimal grosProfFC;
    @Column(name = "Ref1")
    private String ref1;
    @Column(name = "Ref2")
    private String ref2;
    @Column(name = "Comments")
    private String comments;
    @Column(name = "JrnlMemo")
    private String jrnlMemo;
    @Column(name = "TransId")
    private Integer transId;
    @Column(name = "ReceiptNum")
    private Integer receiptNum;
    @Column(name = "GroupNum")
    private Short groupNum;
    @Column(name = "DocTime")
    private Short docTime;
    @Column(name = "SlpCode")
    private Short slpCode;
    @Column(name = "TrnspCode")
    private Short trnspCode;
    @Column(name = "PartSupply")
    private Character partSupply;
    @Column(name = "Confirmed")
    private Character confirmed;
    @Column(name = "GrossBase")
    private Short grossBase;
    @Column(name = "ImportEnt")
    private Integer importEnt;
    @Column(name = "CreateTran")
    private Character createTran;
    @Column(name = "SummryType")
    private Character summryType;
    @Column(name = "UpdInvnt")
    private Character updInvnt;
    @Column(name = "UpdCardBal")
    private Character updCardBal;
    @Basic(optional = false)
    @Column(name = "Instance")
    private short instance;
    @Column(name = "Flags")
    private Integer flags;
    @Column(name = "InvntDirec")
    private Character invntDirec;
    @Column(name = "CntctCode")
    private Integer cntctCode;
    @Column(name = "ShowSCN")
    private Character showSCN;
    @Column(name = "FatherCard")
    private String fatherCard;
    @Column(name = "SysRate")
    private BigDecimal sysRate;
    @Column(name = "CurSource")
    private Character curSource;
    @Column(name = "VatSumSy")
    private BigDecimal vatSumSy;
    @Column(name = "DiscSumSy")
    private BigDecimal discSumSy;
    @Column(name = "DocTotalSy")
    private BigDecimal docTotalSy;
    @Column(name = "PaidSys")
    private BigDecimal paidSys;
    @Column(name = "FatherType")
    private Character fatherType;
    @Column(name = "GrosProfSy")
    private BigDecimal grosProfSy;
    @Column(name = "UpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "IsICT")
    private Character isICT;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "Volume")
    private BigDecimal volume;
    @Column(name = "VolUnit")
    private Short volUnit;
    @Column(name = "Weight")
    private BigDecimal weight;
    @Column(name = "WeightUnit")
    private Short weightUnit;
    @Column(name = "Series")
    private Short series;
    @Column(name = "TaxDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taxDate;
    @Column(name = "Filler")
    private String filler;
    @Column(name = "DataSource")
    private Character dataSource;
    @Column(name = "StampNum")
    private String stampNum;
    @Column(name = "isCrin")
    private Character isCrin;
    @Column(name = "FinncPriod")
    private Integer finncPriod;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "selfInv")
    private Character selfInv;
    @Column(name = "VatPaid")
    private BigDecimal vatPaid;
    @Column(name = "VatPaidFC")
    private BigDecimal vatPaidFC;
    @Column(name = "VatPaidSys")
    private BigDecimal vatPaidSys;
    @Column(name = "UserSign2")
    private Short userSign2;
    @Column(name = "WddStatus")
    private Character wddStatus;
    @Column(name = "draftKey")
    private Integer draftKey;
    @Column(name = "TotalExpns")
    private BigDecimal totalExpns;
    @Column(name = "TotalExpFC")
    private BigDecimal totalExpFC;
    @Column(name = "TotalExpSC")
    private BigDecimal totalExpSC;
    @Column(name = "DunnLevel")
    private Integer dunnLevel;
    @Column(name = "Address2")
    private String address2;
    @Column(name = "LogInstanc")
    private Integer logInstanc;
    @Column(name = "Exported")
    private Character exported;
    @Column(name = "StationID")
    private Integer stationID;
    @Column(name = "Indicator")
    private String indicator;
    @Column(name = "NetProc")
    private Character netProc;
    @Column(name = "AqcsTax")
    private BigDecimal aqcsTax;
    @Column(name = "AqcsTaxFC")
    private BigDecimal aqcsTaxFC;
    @Column(name = "AqcsTaxSC")
    private BigDecimal aqcsTaxSC;
    @Column(name = "CashDiscPr")
    private BigDecimal cashDiscPr;
    @Column(name = "CashDiscnt")
    private BigDecimal cashDiscnt;
    @Column(name = "CashDiscFC")
    private BigDecimal cashDiscFC;
    @Column(name = "CashDiscSC")
    private BigDecimal cashDiscSC;
    @Column(name = "ShipToCode")
    private String shipToCode;
    @Column(name = "LicTradNum")
    private String licTradNum;
    @Column(name = "PaymentRef")
    private String paymentRef;
    @Column(name = "WTSum")
    private BigDecimal wTSum;
    @Column(name = "WTSumFC")
    private BigDecimal wTSumFC;
    @Column(name = "WTSumSC")
    private BigDecimal wTSumSC;
    @Column(name = "RoundDif")
    private BigDecimal roundDif;
    @Column(name = "RoundDifFC")
    private BigDecimal roundDifFC;
    @Column(name = "RoundDifSy")
    private BigDecimal roundDifSy;
    @Column(name = "CheckDigit")
    private Character checkDigit;
    @Column(name = "Form1099")
    private Integer form1099;
    @Column(name = "Box1099")
    private String box1099;
    @Column(name = "submitted")
    private Character submitted;
    @Column(name = "PoPrss")
    private Character poPrss;
    @Column(name = "Rounding")
    private Character rounding;
    @Column(name = "RevisionPo")
    private Character revisionPo;
    @Basic(optional = false)
    @Column(name = "Segment")
    private short segment;
    @Column(name = "ReqDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reqDate;
    @Column(name = "CancelDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelDate;
    @Column(name = "PickStatus")
    private Character pickStatus;
    @Column(name = "Pick")
    private Character pick;
    @Column(name = "BlockDunn")
    private Character blockDunn;
    @Column(name = "PeyMethod")
    private String peyMethod;
    @Column(name = "PayBlock")
    private Character payBlock;
    @Column(name = "PayBlckRef")
    private Integer payBlckRef;
    @Column(name = "MaxDscn")
    private Character maxDscn;
    @Column(name = "Reserve")
    private Character reserve;
    @Column(name = "Max1099")
    private BigDecimal max1099;
    @Column(name = "CntrlBnk")
    private String cntrlBnk;
    @Column(name = "PickRmrk")
    private String pickRmrk;
    @Column(name = "ISRCodLine")
    private String iSRCodLine;
    @Column(name = "ExpAppl")
    private BigDecimal expAppl;
    @Column(name = "ExpApplFC")
    private BigDecimal expApplFC;
    @Column(name = "ExpApplSC")
    private BigDecimal expApplSC;
    @Column(name = "Project")
    private String project;
    @Column(name = "DeferrTax")
    private Character deferrTax;
    @Column(name = "LetterNum")
    private String letterNum;
    @Column(name = "FromDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "ToDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @Column(name = "WTApplied")
    private BigDecimal wTApplied;
    @Column(name = "WTAppliedF")
    private BigDecimal wTAppliedF;
    @Column(name = "BoeReserev")
    private Character boeReserev;
    @Column(name = "AgentCode")
    private String agentCode;
    @Column(name = "WTAppliedS")
    private BigDecimal wTAppliedS;
    @Column(name = "EquVatSum")
    private BigDecimal equVatSum;
    @Column(name = "EquVatSumF")
    private BigDecimal equVatSumF;
    @Column(name = "EquVatSumS")
    private BigDecimal equVatSumS;
    @Column(name = "Installmnt")
    private Short installmnt;
    @Column(name = "VATFirst")
    private Character vATFirst;
    @Column(name = "NnSbAmnt")
    private BigDecimal nnSbAmnt;
    @Column(name = "NnSbAmntSC")
    private BigDecimal nnSbAmntSC;
    @Column(name = "NbSbAmntFC")
    private BigDecimal nbSbAmntFC;
    @Column(name = "ExepAmnt")
    private BigDecimal exepAmnt;
    @Column(name = "ExepAmntSC")
    private BigDecimal exepAmntSC;
    @Column(name = "ExepAmntFC")
    private BigDecimal exepAmntFC;
    @Column(name = "VatDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vatDate;
    @Column(name = "CorrExt")
    private String corrExt;
    @Column(name = "CorrInv")
    private Integer corrInv;
    @Column(name = "NCorrInv")
    private Integer nCorrInv;
    @Column(name = "CEECFlag")
    private Character cEECFlag;
    @Column(name = "BaseAmnt")
    private BigDecimal baseAmnt;
    @Column(name = "BaseAmntSC")
    private BigDecimal baseAmntSC;
    @Column(name = "BaseAmntFC")
    private BigDecimal baseAmntFC;
    @Column(name = "CtlAccount")
    private String ctlAccount;
    @Column(name = "BPLId")
    private Integer bPLId;
    @Column(name = "BPLName")
    private String bPLName;
    @Column(name = "VATRegNum")
    private String vATRegNum;
    @Column(name = "TxInvRptNo")
    private String txInvRptNo;
    @Column(name = "TxInvRptDt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date txInvRptDt;
    @Column(name = "KVVATCode")
    private String kVVATCode;
    @Column(name = "WTDetails")
    private String wTDetails;
    @Column(name = "SumAbsId")
    private Integer sumAbsId;
    @Column(name = "SumRptDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sumRptDate;
    @Basic(optional = false)
    @Column(name = "PIndicator")
    private String pIndicator;
    @Column(name = "ManualNum")
    private String manualNum;
    @Column(name = "UseShpdGd")
    private Character useShpdGd;
    @Column(name = "BaseVtAt")
    private BigDecimal baseVtAt;
    @Column(name = "BaseVtAtSC")
    private BigDecimal baseVtAtSC;
    @Column(name = "BaseVtAtFC")
    private BigDecimal baseVtAtFC;
    @Column(name = "NnSbVAt")
    private BigDecimal nnSbVAt;
    @Column(name = "NnSbVAtSC")
    private BigDecimal nnSbVAtSC;
    @Column(name = "NbSbVAtFC")
    private BigDecimal nbSbVAtFC;
    @Column(name = "ExptVAt")
    private BigDecimal exptVAt;
    @Column(name = "ExptVAtSC")
    private BigDecimal exptVAtSC;
    @Column(name = "ExptVAtFC")
    private BigDecimal exptVAtFC;
    @Column(name = "LYPmtAt")
    private BigDecimal lYPmtAt;
    @Column(name = "LYPmtAtSC")
    private BigDecimal lYPmtAtSC;
    @Column(name = "LYPmtAtFC")
    private BigDecimal lYPmtAtFC;
    @Column(name = "ExpAnSum")
    private BigDecimal expAnSum;
    @Column(name = "ExpAnSys")
    private BigDecimal expAnSys;
    @Column(name = "ExpAnFrgn")
    private BigDecimal expAnFrgn;
    @Basic(optional = false)
    @Column(name = "DocSubType")
    private String docSubType;
    @Column(name = "DpmStatus")
    private Character dpmStatus;
    @Column(name = "DpmAmnt")
    private BigDecimal dpmAmnt;
    @Column(name = "DpmAmntSC")
    private BigDecimal dpmAmntSC;
    @Column(name = "DpmAmntFC")
    private BigDecimal dpmAmntFC;
    @Column(name = "DpmDrawn")
    private Character dpmDrawn;
    @Column(name = "DpmPrcnt")
    private BigDecimal dpmPrcnt;
    @Column(name = "PaidSum")
    private BigDecimal paidSum;
    @Column(name = "PaidSumFc")
    private BigDecimal paidSumFc;
    @Column(name = "PaidSumSc")
    private BigDecimal paidSumSc;
    @Column(name = "FolioPref")
    private String folioPref;
    @Column(name = "FolioNum")
    private Integer folioNum;
    @Column(name = "DpmAppl")
    private BigDecimal dpmAppl;
    @Column(name = "DpmApplFc")
    private BigDecimal dpmApplFc;
    @Column(name = "DpmApplSc")
    private BigDecimal dpmApplSc;
    @Column(name = "LPgFolioN")
    private Integer lPgFolioN;
    @Column(name = "Header")
    private String header;
    @Column(name = "Footer")
    private String footer;
    @Column(name = "Posted")
    private Character posted;
    @Column(name = "OwnerCode")
    private Integer ownerCode;
    @Column(name = "BPChCode")
    private String bPChCode;
    @Column(name = "BPChCntc")
    private Integer bPChCntc;
    @Column(name = "PayToCode")
    private String payToCode;
    @Column(name = "IsPaytoBnk")
    private Character isPaytoBnk;
    @Column(name = "BnkCntry")
    private String bnkCntry;
    @Column(name = "BankCode")
    private String bankCode;
    @Column(name = "BnkAccount")
    private String bnkAccount;
    @Column(name = "BnkBranch")
    private String bnkBranch;
    @Column(name = "isIns")
    private Character isIns;
    @Column(name = "TrackNo")
    private String trackNo;
    @Column(name = "VersionNum")
    private String versionNum;
    @Column(name = "LangCode")
    private Integer langCode;
    @Column(name = "BPNameOW")
    private Character bPNameOW;
    @Column(name = "BillToOW")
    private Character billToOW;
    @Column(name = "ShipToOW")
    private Character shipToOW;
    @Column(name = "RetInvoice")
    private Character retInvoice;
    @Column(name = "ClsDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clsDate;
    @Column(name = "MInvNum")
    private Integer mInvNum;
    @Column(name = "MInvDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mInvDate;
    @Column(name = "SeqCode")
    private Short seqCode;
    @Column(name = "Serial")
    private Integer serial;
    @Column(name = "SeriesStr")
    private String seriesStr;
    @Column(name = "SubStr")
    private String subStr;
    @Column(name = "Model")
    private String model;
    @Column(name = "TaxOnExp")
    private BigDecimal taxOnExp;
    @Column(name = "TaxOnExpFc")
    private BigDecimal taxOnExpFc;
    @Column(name = "TaxOnExpSc")
    private BigDecimal taxOnExpSc;
    @Column(name = "TaxOnExAp")
    private BigDecimal taxOnExAp;
    @Column(name = "TaxOnExApF")
    private BigDecimal taxOnExApF;
    @Column(name = "TaxOnExApS")
    private BigDecimal taxOnExApS;
    @Column(name = "LastPmnTyp")
    private Character lastPmnTyp;
    @Column(name = "LndCstNum")
    private Integer lndCstNum;
    @Column(name = "UseCorrVat")
    private Character useCorrVat;
    @Column(name = "BlkCredMmo")
    private Character blkCredMmo;
    @Column(name = "OpenForLaC")
    private Character openForLaC;
    @Column(name = "Excised")
    private Character excised;
    @Column(name = "ExcRefDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date excRefDate;
    @Column(name = "ExcRmvTime")
    private String excRmvTime;
    @Column(name = "SrvGpPrcnt")
    private BigDecimal srvGpPrcnt;
    @Column(name = "DepositNum")
    private Integer depositNum;
    @Column(name = "CertNum")
    private String certNum;
    @Column(name = "DutyStatus")
    private Character dutyStatus;
    @Column(name = "AutoCrtFlw")
    private Character autoCrtFlw;
    @Column(name = "FlwRefDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date flwRefDate;
    @Column(name = "FlwRefNum")
    private String flwRefNum;
    @Column(name = "VatJENum")
    private Integer vatJENum;
    @Column(name = "DpmVat")
    private BigDecimal dpmVat;
    @Column(name = "DpmVatFc")
    private BigDecimal dpmVatFc;
    @Column(name = "DpmVatSc")
    private BigDecimal dpmVatSc;
    @Column(name = "DpmAppVat")
    private BigDecimal dpmAppVat;
    @Column(name = "DpmAppVatF")
    private BigDecimal dpmAppVatF;
    @Column(name = "DpmAppVatS")
    private BigDecimal dpmAppVatS;
    @Column(name = "InsurOp347")
    private Character insurOp347;
    @Column(name = "IgnRelDoc")
    private Character ignRelDoc;
    @Column(name = "BuildDesc")
    private String buildDesc;
    @Column(name = "ResidenNum")
    private Character residenNum;
    @Column(name = "Checker")
    private Integer checker;
    @Column(name = "Payee")
    private Integer payee;
    @Column(name = "CopyNumber")
    private Integer copyNumber;
    @Column(name = "SSIExmpt")
    private Character sSIExmpt;
    @Column(name = "PQTGrpSer")
    private Short pQTGrpSer;
    @Column(name = "PQTGrpNum")
    private Integer pQTGrpNum;
    @Column(name = "PQTGrpHW")
    private Character pQTGrpHW;
    @Column(name = "ReopOriDoc")
    private Character reopOriDoc;
    @Column(name = "ReopManCls")
    private Character reopManCls;
    @Column(name = "DocManClsd")
    private Character docManClsd;
    @Column(name = "ClosingOpt")
    private Short closingOpt;
    @Column(name = "SpecDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date specDate;
    @Column(name = "Ordered")
    private Character ordered;
    @Column(name = "NTSApprov")
    private Character nTSApprov;
    @Column(name = "NTSWebSite")
    private Short nTSWebSite;
    @Column(name = "NTSeTaxNo")
    private String nTSeTaxNo;
    @Column(name = "NTSApprNo")
    private String nTSApprNo;
    @Column(name = "PayDuMonth")
    private Character payDuMonth;
    @Column(name = "ExtraMonth")
    private Short extraMonth;
    @Column(name = "ExtraDays")
    private Short extraDays;
    @Column(name = "CdcOffset")
    private Short cdcOffset;
    @Column(name = "SignMsg")
    private String signMsg;
    @Column(name = "SignDigest")
    private String signDigest;
    @Column(name = "CertifNum")
    private String certifNum;
    @Column(name = "KeyVersion")
    private Integer keyVersion;
    @Column(name = "EDocGenTyp")
    private Character eDocGenTyp;
    @Column(name = "ESeries")
    private Short eSeries;
    @Column(name = "EDocNum")
    private String eDocNum;
    @Column(name = "EDocExpFrm")
    private Integer eDocExpFrm;
    @Column(name = "OnlineQuo")
    private Character onlineQuo;
    @Column(name = "POSEqNum")
    private String pOSEqNum;
    @Column(name = "POSManufSN")
    private String pOSManufSN;
    @Column(name = "POSCashN")
    private Integer pOSCashN;
    @Column(name = "EDocStatus")
    private Character eDocStatus;
    @Column(name = "EDocCntnt")
    private String eDocCntnt;
    @Column(name = "EDocProces")
    private Character eDocProces;
    @Column(name = "EDocErrCod")
    private String eDocErrCod;
    @Column(name = "EDocErrMsg")
    private String eDocErrMsg;
    @Column(name = "EDocCancel")
    private Character eDocCancel;
    @Column(name = "EDocTest")
    private Character eDocTest;
    @Column(name = "EDocPrefix")
    private String eDocPrefix;
    @Column(name = "CUP")
    private Integer cup;
    @Column(name = "CIG")
    private Integer cig;
    @Column(name = "DpmAsDscnt")
    private Character dpmAsDscnt;
    @Column(name = "Attachment")
    private String attachment;
    @Column(name = "AtcEntry")
    private Integer atcEntry;
    @Column(name = "SupplCode")
    private String supplCode;
    @Column(name = "GTSRlvnt")
    private Character gTSRlvnt;
    @Column(name = "BaseDisc")
    private BigDecimal baseDisc;
    @Column(name = "BaseDiscSc")
    private BigDecimal baseDiscSc;
    @Column(name = "BaseDiscFc")
    private BigDecimal baseDiscFc;
    @Column(name = "BaseDiscPr")
    private BigDecimal baseDiscPr;
    @Column(name = "CreateTS")
    private Integer createTS;
    @Column(name = "UpdateTS")
    private Integer updateTS;
    @Column(name = "SrvTaxRule")
    private Character srvTaxRule;
    @Column(name = "AnnInvDecR")
    private Integer annInvDecR;
    @Column(name = "Supplier")
    private String supplier;
    @Column(name = "Releaser")
    private Integer releaser;
    @Column(name = "Receiver")
    private Integer receiver;
    @Column(name = "U_Autorret")
    private Character uAutorret;
    @Column(name = "U_BPCOST")
    private String uBpcost;
    @Column(name = "U_WUID")
    private String uWuid;
    @Column(name = "U_TipoNota")
    private Character uTipoNota;
    @Column(name = "U_Retefue")
    private Character uRetefue;
    @Column(name = "U_ReteIca")
    private Character uReteIca;
    @Column(name = "U_OK1_IVAPA")
    private Character uOk1Ivapa;
    @Column(name = "U_OK1_IFRS")
    private Character uOk1Ifrs;
    @Column(name = "U_Diseno")
    private String uDiseno;
    @Column(name = "U_NWR_PicS")
    private String uNWRPicS;
    @Column(name = "U_NWR_BRet")
    private String uNWRBRet;
    @Column(name = "U_nwr_PAut")
    private Short unwrPAut;
    @Column(name = "U_nwr_Note")
    private String unwrNote;
    @Column(name = "U_nwr_Tag")
    private String unwrTag;
    @Column(name = "U_nwr_Frgt")
    private BigDecimal unwrFrgt;
    @Column(name = "U_NWR_NORM")
    private String uNwrNorm;
    @Column(name = "U_TypeExped")
    private String uTypeExped;
    @Column(name = "U_diseno2")
    private String uDecorador2;
    @Column(name = "U_Comdeco1")
    private String uComdeco1;
    @Column(name = "U_Comdeco2")
    private String uComdeco2;

    public CotizacionSAP() {
    }

    public CotizacionSAP(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public CotizacionSAP(Integer docEntry, int docNum, short instance, short segment, String pIndicator, String docSubType) {
        this.docEntry = docEntry;
        this.docNum = docNum;
        this.instance = instance;
        this.segment = segment;
        this.pIndicator = pIndicator;
        this.docSubType = docSubType;
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

    public Character getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(Character docStatus) {
        this.docStatus = docStatus;
    }

    public Character getInvntSttus() {
        return invntSttus;
    }

    public void setInvntSttus(Character invntSttus) {
        this.invntSttus = invntSttus;
    }

    public Character getTransfered() {
        return transfered;
    }

    public void setTransfered(Character transfered) {
        this.transfered = transfered;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
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

    public String getNumAtCard() {
        return numAtCard;
    }

    public void setNumAtCard(String numAtCard) {
        this.numAtCard = numAtCard;
    }

    public BigDecimal getVatPercent() {
        return vatPercent;
    }

    public void setVatPercent(BigDecimal vatPercent) {
        this.vatPercent = vatPercent;
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

    public BigDecimal getDiscPrcnt() {
        return discPrcnt;
    }

    public void setDiscPrcnt(BigDecimal discPrcnt) {
        this.discPrcnt = discPrcnt;
    }

    public BigDecimal getDiscSum() {
        return discSum;
    }

    public void setDiscSum(BigDecimal discSum) {
        this.discSum = discSum;
    }

    public BigDecimal getDiscSumFC() {
        return discSumFC;
    }

    public void setDiscSumFC(BigDecimal discSumFC) {
        this.discSumFC = discSumFC;
    }

    public String getDocCur() {
        return docCur;
    }

    public void setDocCur(String docCur) {
        this.docCur = docCur;
    }

    public BigDecimal getDocRate() {
        return docRate;
    }

    public void setDocRate(BigDecimal docRate) {
        this.docRate = docRate;
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

    public BigDecimal getPaidToDate() {
        return paidToDate;
    }

    public void setPaidToDate(BigDecimal paidToDate) {
        this.paidToDate = paidToDate;
    }

    public BigDecimal getPaidFC() {
        return paidFC;
    }

    public void setPaidFC(BigDecimal paidFC) {
        this.paidFC = paidFC;
    }

    public BigDecimal getGrosProfit() {
        return grosProfit;
    }

    public void setGrosProfit(BigDecimal grosProfit) {
        this.grosProfit = grosProfit;
    }

    public BigDecimal getGrosProfFC() {
        return grosProfFC;
    }

    public void setGrosProfFC(BigDecimal grosProfFC) {
        this.grosProfFC = grosProfFC;
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

    public Integer getReceiptNum() {
        return receiptNum;
    }

    public void setReceiptNum(Integer receiptNum) {
        this.receiptNum = receiptNum;
    }

    public Short getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(Short groupNum) {
        this.groupNum = groupNum;
    }

    public Short getDocTime() {
        return docTime;
    }

    public void setDocTime(Short docTime) {
        this.docTime = docTime;
    }

    public Short getSlpCode() {
        return slpCode;
    }

    public void setSlpCode(Short slpCode) {
        this.slpCode = slpCode;
    }

    public Short getTrnspCode() {
        return trnspCode;
    }

    public void setTrnspCode(Short trnspCode) {
        this.trnspCode = trnspCode;
    }

    public Character getPartSupply() {
        return partSupply;
    }

    public void setPartSupply(Character partSupply) {
        this.partSupply = partSupply;
    }

    public Character getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Character confirmed) {
        this.confirmed = confirmed;
    }

    public Short getGrossBase() {
        return grossBase;
    }

    public void setGrossBase(Short grossBase) {
        this.grossBase = grossBase;
    }

    public Integer getImportEnt() {
        return importEnt;
    }

    public void setImportEnt(Integer importEnt) {
        this.importEnt = importEnt;
    }

    public Character getCreateTran() {
        return createTran;
    }

    public void setCreateTran(Character createTran) {
        this.createTran = createTran;
    }

    public Character getSummryType() {
        return summryType;
    }

    public void setSummryType(Character summryType) {
        this.summryType = summryType;
    }

    public Character getUpdInvnt() {
        return updInvnt;
    }

    public void setUpdInvnt(Character updInvnt) {
        this.updInvnt = updInvnt;
    }

    public Character getUpdCardBal() {
        return updCardBal;
    }

    public void setUpdCardBal(Character updCardBal) {
        this.updCardBal = updCardBal;
    }

    public short getInstance() {
        return instance;
    }

    public void setInstance(short instance) {
        this.instance = instance;
    }

    public Integer getFlags() {
        return flags;
    }

    public void setFlags(Integer flags) {
        this.flags = flags;
    }

    public Character getInvntDirec() {
        return invntDirec;
    }

    public void setInvntDirec(Character invntDirec) {
        this.invntDirec = invntDirec;
    }

    public Integer getCntctCode() {
        return cntctCode;
    }

    public void setCntctCode(Integer cntctCode) {
        this.cntctCode = cntctCode;
    }

    public Character getShowSCN() {
        return showSCN;
    }

    public void setShowSCN(Character showSCN) {
        this.showSCN = showSCN;
    }

    public String getFatherCard() {
        return fatherCard;
    }

    public void setFatherCard(String fatherCard) {
        this.fatherCard = fatherCard;
    }

    public BigDecimal getSysRate() {
        return sysRate;
    }

    public void setSysRate(BigDecimal sysRate) {
        this.sysRate = sysRate;
    }

    public Character getCurSource() {
        return curSource;
    }

    public void setCurSource(Character curSource) {
        this.curSource = curSource;
    }

    public BigDecimal getVatSumSy() {
        return vatSumSy;
    }

    public void setVatSumSy(BigDecimal vatSumSy) {
        this.vatSumSy = vatSumSy;
    }

    public BigDecimal getDiscSumSy() {
        return discSumSy;
    }

    public void setDiscSumSy(BigDecimal discSumSy) {
        this.discSumSy = discSumSy;
    }

    public BigDecimal getDocTotalSy() {
        return docTotalSy;
    }

    public void setDocTotalSy(BigDecimal docTotalSy) {
        this.docTotalSy = docTotalSy;
    }

    public BigDecimal getPaidSys() {
        return paidSys;
    }

    public void setPaidSys(BigDecimal paidSys) {
        this.paidSys = paidSys;
    }

    public Character getFatherType() {
        return fatherType;
    }

    public void setFatherType(Character fatherType) {
        this.fatherType = fatherType;
    }

    public BigDecimal getGrosProfSy() {
        return grosProfSy;
    }

    public void setGrosProfSy(BigDecimal grosProfSy) {
        this.grosProfSy = grosProfSy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Character getIsICT() {
        return isICT;
    }

    public void setIsICT(Character isICT) {
        this.isICT = isICT;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Short getVolUnit() {
        return volUnit;
    }

    public void setVolUnit(Short volUnit) {
        this.volUnit = volUnit;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Short getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(Short weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Short getSeries() {
        return series;
    }

    public void setSeries(Short series) {
        this.series = series;
    }

    public Date getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(Date taxDate) {
        this.taxDate = taxDate;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public Character getDataSource() {
        return dataSource;
    }

    public void setDataSource(Character dataSource) {
        this.dataSource = dataSource;
    }

    public String getStampNum() {
        return stampNum;
    }

    public void setStampNum(String stampNum) {
        this.stampNum = stampNum;
    }

    public Character getIsCrin() {
        return isCrin;
    }

    public void setIsCrin(Character isCrin) {
        this.isCrin = isCrin;
    }

    public Integer getFinncPriod() {
        return finncPriod;
    }

    public void setFinncPriod(Integer finncPriod) {
        this.finncPriod = finncPriod;
    }

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public Character getSelfInv() {
        return selfInv;
    }

    public void setSelfInv(Character selfInv) {
        this.selfInv = selfInv;
    }

    public BigDecimal getVatPaid() {
        return vatPaid;
    }

    public void setVatPaid(BigDecimal vatPaid) {
        this.vatPaid = vatPaid;
    }

    public BigDecimal getVatPaidFC() {
        return vatPaidFC;
    }

    public void setVatPaidFC(BigDecimal vatPaidFC) {
        this.vatPaidFC = vatPaidFC;
    }

    public BigDecimal getVatPaidSys() {
        return vatPaidSys;
    }

    public void setVatPaidSys(BigDecimal vatPaidSys) {
        this.vatPaidSys = vatPaidSys;
    }

    public Short getUserSign2() {
        return userSign2;
    }

    public void setUserSign2(Short userSign2) {
        this.userSign2 = userSign2;
    }

    public Character getWddStatus() {
        return wddStatus;
    }

    public void setWddStatus(Character wddStatus) {
        this.wddStatus = wddStatus;
    }

    public Integer getDraftKey() {
        return draftKey;
    }

    public void setDraftKey(Integer draftKey) {
        this.draftKey = draftKey;
    }

    public BigDecimal getTotalExpns() {
        return totalExpns;
    }

    public void setTotalExpns(BigDecimal totalExpns) {
        this.totalExpns = totalExpns;
    }

    public BigDecimal getTotalExpFC() {
        return totalExpFC;
    }

    public void setTotalExpFC(BigDecimal totalExpFC) {
        this.totalExpFC = totalExpFC;
    }

    public BigDecimal getTotalExpSC() {
        return totalExpSC;
    }

    public void setTotalExpSC(BigDecimal totalExpSC) {
        this.totalExpSC = totalExpSC;
    }

    public Integer getDunnLevel() {
        return dunnLevel;
    }

    public void setDunnLevel(Integer dunnLevel) {
        this.dunnLevel = dunnLevel;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public Character getExported() {
        return exported;
    }

    public void setExported(Character exported) {
        this.exported = exported;
    }

    public Integer getStationID() {
        return stationID;
    }

    public void setStationID(Integer stationID) {
        this.stationID = stationID;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public Character getNetProc() {
        return netProc;
    }

    public void setNetProc(Character netProc) {
        this.netProc = netProc;
    }

    public BigDecimal getAqcsTax() {
        return aqcsTax;
    }

    public void setAqcsTax(BigDecimal aqcsTax) {
        this.aqcsTax = aqcsTax;
    }

    public BigDecimal getAqcsTaxFC() {
        return aqcsTaxFC;
    }

    public void setAqcsTaxFC(BigDecimal aqcsTaxFC) {
        this.aqcsTaxFC = aqcsTaxFC;
    }

    public BigDecimal getAqcsTaxSC() {
        return aqcsTaxSC;
    }

    public void setAqcsTaxSC(BigDecimal aqcsTaxSC) {
        this.aqcsTaxSC = aqcsTaxSC;
    }

    public BigDecimal getCashDiscPr() {
        return cashDiscPr;
    }

    public void setCashDiscPr(BigDecimal cashDiscPr) {
        this.cashDiscPr = cashDiscPr;
    }

    public BigDecimal getCashDiscnt() {
        return cashDiscnt;
    }

    public void setCashDiscnt(BigDecimal cashDiscnt) {
        this.cashDiscnt = cashDiscnt;
    }

    public BigDecimal getCashDiscFC() {
        return cashDiscFC;
    }

    public void setCashDiscFC(BigDecimal cashDiscFC) {
        this.cashDiscFC = cashDiscFC;
    }

    public BigDecimal getCashDiscSC() {
        return cashDiscSC;
    }

    public void setCashDiscSC(BigDecimal cashDiscSC) {
        this.cashDiscSC = cashDiscSC;
    }

    public String getShipToCode() {
        return shipToCode;
    }

    public void setShipToCode(String shipToCode) {
        this.shipToCode = shipToCode;
    }

    public String getLicTradNum() {
        return licTradNum;
    }

    public void setLicTradNum(String licTradNum) {
        this.licTradNum = licTradNum;
    }

    public String getPaymentRef() {
        return paymentRef;
    }

    public void setPaymentRef(String paymentRef) {
        this.paymentRef = paymentRef;
    }

    public BigDecimal getWTSum() {
        return wTSum;
    }

    public void setWTSum(BigDecimal wTSum) {
        this.wTSum = wTSum;
    }

    public BigDecimal getWTSumFC() {
        return wTSumFC;
    }

    public void setWTSumFC(BigDecimal wTSumFC) {
        this.wTSumFC = wTSumFC;
    }

    public BigDecimal getWTSumSC() {
        return wTSumSC;
    }

    public void setWTSumSC(BigDecimal wTSumSC) {
        this.wTSumSC = wTSumSC;
    }

    public BigDecimal getRoundDif() {
        return roundDif;
    }

    public void setRoundDif(BigDecimal roundDif) {
        this.roundDif = roundDif;
    }

    public BigDecimal getRoundDifFC() {
        return roundDifFC;
    }

    public void setRoundDifFC(BigDecimal roundDifFC) {
        this.roundDifFC = roundDifFC;
    }

    public BigDecimal getRoundDifSy() {
        return roundDifSy;
    }

    public void setRoundDifSy(BigDecimal roundDifSy) {
        this.roundDifSy = roundDifSy;
    }

    public Character getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(Character checkDigit) {
        this.checkDigit = checkDigit;
    }

    public Integer getForm1099() {
        return form1099;
    }

    public void setForm1099(Integer form1099) {
        this.form1099 = form1099;
    }

    public String getBox1099() {
        return box1099;
    }

    public void setBox1099(String box1099) {
        this.box1099 = box1099;
    }

    public Character getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Character submitted) {
        this.submitted = submitted;
    }

    public Character getPoPrss() {
        return poPrss;
    }

    public void setPoPrss(Character poPrss) {
        this.poPrss = poPrss;
    }

    public Character getRounding() {
        return rounding;
    }

    public void setRounding(Character rounding) {
        this.rounding = rounding;
    }

    public Character getRevisionPo() {
        return revisionPo;
    }

    public void setRevisionPo(Character revisionPo) {
        this.revisionPo = revisionPo;
    }

    public short getSegment() {
        return segment;
    }

    public void setSegment(short segment) {
        this.segment = segment;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Character getPickStatus() {
        return pickStatus;
    }

    public void setPickStatus(Character pickStatus) {
        this.pickStatus = pickStatus;
    }

    public Character getPick() {
        return pick;
    }

    public void setPick(Character pick) {
        this.pick = pick;
    }

    public Character getBlockDunn() {
        return blockDunn;
    }

    public void setBlockDunn(Character blockDunn) {
        this.blockDunn = blockDunn;
    }

    public String getPeyMethod() {
        return peyMethod;
    }

    public void setPeyMethod(String peyMethod) {
        this.peyMethod = peyMethod;
    }

    public Character getPayBlock() {
        return payBlock;
    }

    public void setPayBlock(Character payBlock) {
        this.payBlock = payBlock;
    }

    public Integer getPayBlckRef() {
        return payBlckRef;
    }

    public void setPayBlckRef(Integer payBlckRef) {
        this.payBlckRef = payBlckRef;
    }

    public Character getMaxDscn() {
        return maxDscn;
    }

    public void setMaxDscn(Character maxDscn) {
        this.maxDscn = maxDscn;
    }

    public Character getReserve() {
        return reserve;
    }

    public void setReserve(Character reserve) {
        this.reserve = reserve;
    }

    public BigDecimal getMax1099() {
        return max1099;
    }

    public void setMax1099(BigDecimal max1099) {
        this.max1099 = max1099;
    }

    public String getCntrlBnk() {
        return cntrlBnk;
    }

    public void setCntrlBnk(String cntrlBnk) {
        this.cntrlBnk = cntrlBnk;
    }

    public String getPickRmrk() {
        return pickRmrk;
    }

    public void setPickRmrk(String pickRmrk) {
        this.pickRmrk = pickRmrk;
    }

    public String getISRCodLine() {
        return iSRCodLine;
    }

    public void setISRCodLine(String iSRCodLine) {
        this.iSRCodLine = iSRCodLine;
    }

    public BigDecimal getExpAppl() {
        return expAppl;
    }

    public void setExpAppl(BigDecimal expAppl) {
        this.expAppl = expAppl;
    }

    public BigDecimal getExpApplFC() {
        return expApplFC;
    }

    public void setExpApplFC(BigDecimal expApplFC) {
        this.expApplFC = expApplFC;
    }

    public BigDecimal getExpApplSC() {
        return expApplSC;
    }

    public void setExpApplSC(BigDecimal expApplSC) {
        this.expApplSC = expApplSC;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Character getDeferrTax() {
        return deferrTax;
    }

    public void setDeferrTax(Character deferrTax) {
        this.deferrTax = deferrTax;
    }

    public String getLetterNum() {
        return letterNum;
    }

    public void setLetterNum(String letterNum) {
        this.letterNum = letterNum;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public BigDecimal getWTApplied() {
        return wTApplied;
    }

    public void setWTApplied(BigDecimal wTApplied) {
        this.wTApplied = wTApplied;
    }

    public BigDecimal getWTAppliedF() {
        return wTAppliedF;
    }

    public void setWTAppliedF(BigDecimal wTAppliedF) {
        this.wTAppliedF = wTAppliedF;
    }

    public Character getBoeReserev() {
        return boeReserev;
    }

    public void setBoeReserev(Character boeReserev) {
        this.boeReserev = boeReserev;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public BigDecimal getWTAppliedS() {
        return wTAppliedS;
    }

    public void setWTAppliedS(BigDecimal wTAppliedS) {
        this.wTAppliedS = wTAppliedS;
    }

    public BigDecimal getEquVatSum() {
        return equVatSum;
    }

    public void setEquVatSum(BigDecimal equVatSum) {
        this.equVatSum = equVatSum;
    }

    public BigDecimal getEquVatSumF() {
        return equVatSumF;
    }

    public void setEquVatSumF(BigDecimal equVatSumF) {
        this.equVatSumF = equVatSumF;
    }

    public BigDecimal getEquVatSumS() {
        return equVatSumS;
    }

    public void setEquVatSumS(BigDecimal equVatSumS) {
        this.equVatSumS = equVatSumS;
    }

    public Short getInstallmnt() {
        return installmnt;
    }

    public void setInstallmnt(Short installmnt) {
        this.installmnt = installmnt;
    }

    public Character getVATFirst() {
        return vATFirst;
    }

    public void setVATFirst(Character vATFirst) {
        this.vATFirst = vATFirst;
    }

    public BigDecimal getNnSbAmnt() {
        return nnSbAmnt;
    }

    public void setNnSbAmnt(BigDecimal nnSbAmnt) {
        this.nnSbAmnt = nnSbAmnt;
    }

    public BigDecimal getNnSbAmntSC() {
        return nnSbAmntSC;
    }

    public void setNnSbAmntSC(BigDecimal nnSbAmntSC) {
        this.nnSbAmntSC = nnSbAmntSC;
    }

    public BigDecimal getNbSbAmntFC() {
        return nbSbAmntFC;
    }

    public void setNbSbAmntFC(BigDecimal nbSbAmntFC) {
        this.nbSbAmntFC = nbSbAmntFC;
    }

    public BigDecimal getExepAmnt() {
        return exepAmnt;
    }

    public void setExepAmnt(BigDecimal exepAmnt) {
        this.exepAmnt = exepAmnt;
    }

    public BigDecimal getExepAmntSC() {
        return exepAmntSC;
    }

    public void setExepAmntSC(BigDecimal exepAmntSC) {
        this.exepAmntSC = exepAmntSC;
    }

    public BigDecimal getExepAmntFC() {
        return exepAmntFC;
    }

    public void setExepAmntFC(BigDecimal exepAmntFC) {
        this.exepAmntFC = exepAmntFC;
    }

    public Date getVatDate() {
        return vatDate;
    }

    public void setVatDate(Date vatDate) {
        this.vatDate = vatDate;
    }

    public String getCorrExt() {
        return corrExt;
    }

    public void setCorrExt(String corrExt) {
        this.corrExt = corrExt;
    }

    public Integer getCorrInv() {
        return corrInv;
    }

    public void setCorrInv(Integer corrInv) {
        this.corrInv = corrInv;
    }

    public Integer getNCorrInv() {
        return nCorrInv;
    }

    public void setNCorrInv(Integer nCorrInv) {
        this.nCorrInv = nCorrInv;
    }

    public Character getCEECFlag() {
        return cEECFlag;
    }

    public void setCEECFlag(Character cEECFlag) {
        this.cEECFlag = cEECFlag;
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

    public String getCtlAccount() {
        return ctlAccount;
    }

    public void setCtlAccount(String ctlAccount) {
        this.ctlAccount = ctlAccount;
    }

    public Integer getBPLId() {
        return bPLId;
    }

    public void setBPLId(Integer bPLId) {
        this.bPLId = bPLId;
    }

    public String getBPLName() {
        return bPLName;
    }

    public void setBPLName(String bPLName) {
        this.bPLName = bPLName;
    }

    public String getVATRegNum() {
        return vATRegNum;
    }

    public void setVATRegNum(String vATRegNum) {
        this.vATRegNum = vATRegNum;
    }

    public String getTxInvRptNo() {
        return txInvRptNo;
    }

    public void setTxInvRptNo(String txInvRptNo) {
        this.txInvRptNo = txInvRptNo;
    }

    public Date getTxInvRptDt() {
        return txInvRptDt;
    }

    public void setTxInvRptDt(Date txInvRptDt) {
        this.txInvRptDt = txInvRptDt;
    }

    public String getKVVATCode() {
        return kVVATCode;
    }

    public void setKVVATCode(String kVVATCode) {
        this.kVVATCode = kVVATCode;
    }

    public String getWTDetails() {
        return wTDetails;
    }

    public void setWTDetails(String wTDetails) {
        this.wTDetails = wTDetails;
    }

    public Integer getSumAbsId() {
        return sumAbsId;
    }

    public void setSumAbsId(Integer sumAbsId) {
        this.sumAbsId = sumAbsId;
    }

    public Date getSumRptDate() {
        return sumRptDate;
    }

    public void setSumRptDate(Date sumRptDate) {
        this.sumRptDate = sumRptDate;
    }

    public String getPIndicator() {
        return pIndicator;
    }

    public void setPIndicator(String pIndicator) {
        this.pIndicator = pIndicator;
    }

    public String getManualNum() {
        return manualNum;
    }

    public void setManualNum(String manualNum) {
        this.manualNum = manualNum;
    }

    public Character getUseShpdGd() {
        return useShpdGd;
    }

    public void setUseShpdGd(Character useShpdGd) {
        this.useShpdGd = useShpdGd;
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

    public BigDecimal getNnSbVAt() {
        return nnSbVAt;
    }

    public void setNnSbVAt(BigDecimal nnSbVAt) {
        this.nnSbVAt = nnSbVAt;
    }

    public BigDecimal getNnSbVAtSC() {
        return nnSbVAtSC;
    }

    public void setNnSbVAtSC(BigDecimal nnSbVAtSC) {
        this.nnSbVAtSC = nnSbVAtSC;
    }

    public BigDecimal getNbSbVAtFC() {
        return nbSbVAtFC;
    }

    public void setNbSbVAtFC(BigDecimal nbSbVAtFC) {
        this.nbSbVAtFC = nbSbVAtFC;
    }

    public BigDecimal getExptVAt() {
        return exptVAt;
    }

    public void setExptVAt(BigDecimal exptVAt) {
        this.exptVAt = exptVAt;
    }

    public BigDecimal getExptVAtSC() {
        return exptVAtSC;
    }

    public void setExptVAtSC(BigDecimal exptVAtSC) {
        this.exptVAtSC = exptVAtSC;
    }

    public BigDecimal getExptVAtFC() {
        return exptVAtFC;
    }

    public void setExptVAtFC(BigDecimal exptVAtFC) {
        this.exptVAtFC = exptVAtFC;
    }

    public BigDecimal getLYPmtAt() {
        return lYPmtAt;
    }

    public void setLYPmtAt(BigDecimal lYPmtAt) {
        this.lYPmtAt = lYPmtAt;
    }

    public BigDecimal getLYPmtAtSC() {
        return lYPmtAtSC;
    }

    public void setLYPmtAtSC(BigDecimal lYPmtAtSC) {
        this.lYPmtAtSC = lYPmtAtSC;
    }

    public BigDecimal getLYPmtAtFC() {
        return lYPmtAtFC;
    }

    public void setLYPmtAtFC(BigDecimal lYPmtAtFC) {
        this.lYPmtAtFC = lYPmtAtFC;
    }

    public BigDecimal getExpAnSum() {
        return expAnSum;
    }

    public void setExpAnSum(BigDecimal expAnSum) {
        this.expAnSum = expAnSum;
    }

    public BigDecimal getExpAnSys() {
        return expAnSys;
    }

    public void setExpAnSys(BigDecimal expAnSys) {
        this.expAnSys = expAnSys;
    }

    public BigDecimal getExpAnFrgn() {
        return expAnFrgn;
    }

    public void setExpAnFrgn(BigDecimal expAnFrgn) {
        this.expAnFrgn = expAnFrgn;
    }

    public String getDocSubType() {
        return docSubType;
    }

    public void setDocSubType(String docSubType) {
        this.docSubType = docSubType;
    }

    public Character getDpmStatus() {
        return dpmStatus;
    }

    public void setDpmStatus(Character dpmStatus) {
        this.dpmStatus = dpmStatus;
    }

    public BigDecimal getDpmAmnt() {
        return dpmAmnt;
    }

    public void setDpmAmnt(BigDecimal dpmAmnt) {
        this.dpmAmnt = dpmAmnt;
    }

    public BigDecimal getDpmAmntSC() {
        return dpmAmntSC;
    }

    public void setDpmAmntSC(BigDecimal dpmAmntSC) {
        this.dpmAmntSC = dpmAmntSC;
    }

    public BigDecimal getDpmAmntFC() {
        return dpmAmntFC;
    }

    public void setDpmAmntFC(BigDecimal dpmAmntFC) {
        this.dpmAmntFC = dpmAmntFC;
    }

    public Character getDpmDrawn() {
        return dpmDrawn;
    }

    public void setDpmDrawn(Character dpmDrawn) {
        this.dpmDrawn = dpmDrawn;
    }

    public BigDecimal getDpmPrcnt() {
        return dpmPrcnt;
    }

    public void setDpmPrcnt(BigDecimal dpmPrcnt) {
        this.dpmPrcnt = dpmPrcnt;
    }

    public BigDecimal getPaidSum() {
        return paidSum;
    }

    public void setPaidSum(BigDecimal paidSum) {
        this.paidSum = paidSum;
    }

    public BigDecimal getPaidSumFc() {
        return paidSumFc;
    }

    public void setPaidSumFc(BigDecimal paidSumFc) {
        this.paidSumFc = paidSumFc;
    }

    public BigDecimal getPaidSumSc() {
        return paidSumSc;
    }

    public void setPaidSumSc(BigDecimal paidSumSc) {
        this.paidSumSc = paidSumSc;
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

    public BigDecimal getDpmAppl() {
        return dpmAppl;
    }

    public void setDpmAppl(BigDecimal dpmAppl) {
        this.dpmAppl = dpmAppl;
    }

    public BigDecimal getDpmApplFc() {
        return dpmApplFc;
    }

    public void setDpmApplFc(BigDecimal dpmApplFc) {
        this.dpmApplFc = dpmApplFc;
    }

    public BigDecimal getDpmApplSc() {
        return dpmApplSc;
    }

    public void setDpmApplSc(BigDecimal dpmApplSc) {
        this.dpmApplSc = dpmApplSc;
    }

    public Integer getLPgFolioN() {
        return lPgFolioN;
    }

    public void setLPgFolioN(Integer lPgFolioN) {
        this.lPgFolioN = lPgFolioN;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Character getPosted() {
        return posted;
    }

    public void setPosted(Character posted) {
        this.posted = posted;
    }

    public Integer getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(Integer ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getBPChCode() {
        return bPChCode;
    }

    public void setBPChCode(String bPChCode) {
        this.bPChCode = bPChCode;
    }

    public Integer getBPChCntc() {
        return bPChCntc;
    }

    public void setBPChCntc(Integer bPChCntc) {
        this.bPChCntc = bPChCntc;
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

    public String getBnkCntry() {
        return bnkCntry;
    }

    public void setBnkCntry(String bnkCntry) {
        this.bnkCntry = bnkCntry;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBnkAccount() {
        return bnkAccount;
    }

    public void setBnkAccount(String bnkAccount) {
        this.bnkAccount = bnkAccount;
    }

    public String getBnkBranch() {
        return bnkBranch;
    }

    public void setBnkBranch(String bnkBranch) {
        this.bnkBranch = bnkBranch;
    }

    public Character getIsIns() {
        return isIns;
    }

    public void setIsIns(Character isIns) {
        this.isIns = isIns;
    }

    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public Integer getLangCode() {
        return langCode;
    }

    public void setLangCode(Integer langCode) {
        this.langCode = langCode;
    }

    public Character getBPNameOW() {
        return bPNameOW;
    }

    public void setBPNameOW(Character bPNameOW) {
        this.bPNameOW = bPNameOW;
    }

    public Character getBillToOW() {
        return billToOW;
    }

    public void setBillToOW(Character billToOW) {
        this.billToOW = billToOW;
    }

    public Character getShipToOW() {
        return shipToOW;
    }

    public void setShipToOW(Character shipToOW) {
        this.shipToOW = shipToOW;
    }

    public Character getRetInvoice() {
        return retInvoice;
    }

    public void setRetInvoice(Character retInvoice) {
        this.retInvoice = retInvoice;
    }

    public Date getClsDate() {
        return clsDate;
    }

    public void setClsDate(Date clsDate) {
        this.clsDate = clsDate;
    }

    public Integer getMInvNum() {
        return mInvNum;
    }

    public void setMInvNum(Integer mInvNum) {
        this.mInvNum = mInvNum;
    }

    public Date getMInvDate() {
        return mInvDate;
    }

    public void setMInvDate(Date mInvDate) {
        this.mInvDate = mInvDate;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getTaxOnExp() {
        return taxOnExp;
    }

    public void setTaxOnExp(BigDecimal taxOnExp) {
        this.taxOnExp = taxOnExp;
    }

    public BigDecimal getTaxOnExpFc() {
        return taxOnExpFc;
    }

    public void setTaxOnExpFc(BigDecimal taxOnExpFc) {
        this.taxOnExpFc = taxOnExpFc;
    }

    public BigDecimal getTaxOnExpSc() {
        return taxOnExpSc;
    }

    public void setTaxOnExpSc(BigDecimal taxOnExpSc) {
        this.taxOnExpSc = taxOnExpSc;
    }

    public BigDecimal getTaxOnExAp() {
        return taxOnExAp;
    }

    public void setTaxOnExAp(BigDecimal taxOnExAp) {
        this.taxOnExAp = taxOnExAp;
    }

    public BigDecimal getTaxOnExApF() {
        return taxOnExApF;
    }

    public void setTaxOnExApF(BigDecimal taxOnExApF) {
        this.taxOnExApF = taxOnExApF;
    }

    public BigDecimal getTaxOnExApS() {
        return taxOnExApS;
    }

    public void setTaxOnExApS(BigDecimal taxOnExApS) {
        this.taxOnExApS = taxOnExApS;
    }

    public Character getLastPmnTyp() {
        return lastPmnTyp;
    }

    public void setLastPmnTyp(Character lastPmnTyp) {
        this.lastPmnTyp = lastPmnTyp;
    }

    public Integer getLndCstNum() {
        return lndCstNum;
    }

    public void setLndCstNum(Integer lndCstNum) {
        this.lndCstNum = lndCstNum;
    }

    public Character getUseCorrVat() {
        return useCorrVat;
    }

    public void setUseCorrVat(Character useCorrVat) {
        this.useCorrVat = useCorrVat;
    }

    public Character getBlkCredMmo() {
        return blkCredMmo;
    }

    public void setBlkCredMmo(Character blkCredMmo) {
        this.blkCredMmo = blkCredMmo;
    }

    public Character getOpenForLaC() {
        return openForLaC;
    }

    public void setOpenForLaC(Character openForLaC) {
        this.openForLaC = openForLaC;
    }

    public Character getExcised() {
        return excised;
    }

    public void setExcised(Character excised) {
        this.excised = excised;
    }

    public Date getExcRefDate() {
        return excRefDate;
    }

    public void setExcRefDate(Date excRefDate) {
        this.excRefDate = excRefDate;
    }

    public String getExcRmvTime() {
        return excRmvTime;
    }

    public void setExcRmvTime(String excRmvTime) {
        this.excRmvTime = excRmvTime;
    }

    public BigDecimal getSrvGpPrcnt() {
        return srvGpPrcnt;
    }

    public void setSrvGpPrcnt(BigDecimal srvGpPrcnt) {
        this.srvGpPrcnt = srvGpPrcnt;
    }

    public Integer getDepositNum() {
        return depositNum;
    }

    public void setDepositNum(Integer depositNum) {
        this.depositNum = depositNum;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public Character getDutyStatus() {
        return dutyStatus;
    }

    public void setDutyStatus(Character dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    public Character getAutoCrtFlw() {
        return autoCrtFlw;
    }

    public void setAutoCrtFlw(Character autoCrtFlw) {
        this.autoCrtFlw = autoCrtFlw;
    }

    public Date getFlwRefDate() {
        return flwRefDate;
    }

    public void setFlwRefDate(Date flwRefDate) {
        this.flwRefDate = flwRefDate;
    }

    public String getFlwRefNum() {
        return flwRefNum;
    }

    public void setFlwRefNum(String flwRefNum) {
        this.flwRefNum = flwRefNum;
    }

    public Integer getVatJENum() {
        return vatJENum;
    }

    public void setVatJENum(Integer vatJENum) {
        this.vatJENum = vatJENum;
    }

    public BigDecimal getDpmVat() {
        return dpmVat;
    }

    public void setDpmVat(BigDecimal dpmVat) {
        this.dpmVat = dpmVat;
    }

    public BigDecimal getDpmVatFc() {
        return dpmVatFc;
    }

    public void setDpmVatFc(BigDecimal dpmVatFc) {
        this.dpmVatFc = dpmVatFc;
    }

    public BigDecimal getDpmVatSc() {
        return dpmVatSc;
    }

    public void setDpmVatSc(BigDecimal dpmVatSc) {
        this.dpmVatSc = dpmVatSc;
    }

    public BigDecimal getDpmAppVat() {
        return dpmAppVat;
    }

    public void setDpmAppVat(BigDecimal dpmAppVat) {
        this.dpmAppVat = dpmAppVat;
    }

    public BigDecimal getDpmAppVatF() {
        return dpmAppVatF;
    }

    public void setDpmAppVatF(BigDecimal dpmAppVatF) {
        this.dpmAppVatF = dpmAppVatF;
    }

    public BigDecimal getDpmAppVatS() {
        return dpmAppVatS;
    }

    public void setDpmAppVatS(BigDecimal dpmAppVatS) {
        this.dpmAppVatS = dpmAppVatS;
    }

    public Character getInsurOp347() {
        return insurOp347;
    }

    public void setInsurOp347(Character insurOp347) {
        this.insurOp347 = insurOp347;
    }

    public Character getIgnRelDoc() {
        return ignRelDoc;
    }

    public void setIgnRelDoc(Character ignRelDoc) {
        this.ignRelDoc = ignRelDoc;
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

    public Integer getChecker() {
        return checker;
    }

    public void setChecker(Integer checker) {
        this.checker = checker;
    }

    public Integer getPayee() {
        return payee;
    }

    public void setPayee(Integer payee) {
        this.payee = payee;
    }

    public Integer getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(Integer copyNumber) {
        this.copyNumber = copyNumber;
    }

    public Character getSSIExmpt() {
        return sSIExmpt;
    }

    public void setSSIExmpt(Character sSIExmpt) {
        this.sSIExmpt = sSIExmpt;
    }

    public Short getPQTGrpSer() {
        return pQTGrpSer;
    }

    public void setPQTGrpSer(Short pQTGrpSer) {
        this.pQTGrpSer = pQTGrpSer;
    }

    public Integer getPQTGrpNum() {
        return pQTGrpNum;
    }

    public void setPQTGrpNum(Integer pQTGrpNum) {
        this.pQTGrpNum = pQTGrpNum;
    }

    public Character getPQTGrpHW() {
        return pQTGrpHW;
    }

    public void setPQTGrpHW(Character pQTGrpHW) {
        this.pQTGrpHW = pQTGrpHW;
    }

    public Character getReopOriDoc() {
        return reopOriDoc;
    }

    public void setReopOriDoc(Character reopOriDoc) {
        this.reopOriDoc = reopOriDoc;
    }

    public Character getReopManCls() {
        return reopManCls;
    }

    public void setReopManCls(Character reopManCls) {
        this.reopManCls = reopManCls;
    }

    public Character getDocManClsd() {
        return docManClsd;
    }

    public void setDocManClsd(Character docManClsd) {
        this.docManClsd = docManClsd;
    }

    public Short getClosingOpt() {
        return closingOpt;
    }

    public void setClosingOpt(Short closingOpt) {
        this.closingOpt = closingOpt;
    }

    public Date getSpecDate() {
        return specDate;
    }

    public void setSpecDate(Date specDate) {
        this.specDate = specDate;
    }

    public Character getOrdered() {
        return ordered;
    }

    public void setOrdered(Character ordered) {
        this.ordered = ordered;
    }

    public Character getNTSApprov() {
        return nTSApprov;
    }

    public void setNTSApprov(Character nTSApprov) {
        this.nTSApprov = nTSApprov;
    }

    public Short getNTSWebSite() {
        return nTSWebSite;
    }

    public void setNTSWebSite(Short nTSWebSite) {
        this.nTSWebSite = nTSWebSite;
    }

    public String getNTSeTaxNo() {
        return nTSeTaxNo;
    }

    public void setNTSeTaxNo(String nTSeTaxNo) {
        this.nTSeTaxNo = nTSeTaxNo;
    }

    public String getNTSApprNo() {
        return nTSApprNo;
    }

    public void setNTSApprNo(String nTSApprNo) {
        this.nTSApprNo = nTSApprNo;
    }

    public Character getPayDuMonth() {
        return payDuMonth;
    }

    public void setPayDuMonth(Character payDuMonth) {
        this.payDuMonth = payDuMonth;
    }

    public Short getExtraMonth() {
        return extraMonth;
    }

    public void setExtraMonth(Short extraMonth) {
        this.extraMonth = extraMonth;
    }

    public Short getExtraDays() {
        return extraDays;
    }

    public void setExtraDays(Short extraDays) {
        this.extraDays = extraDays;
    }

    public Short getCdcOffset() {
        return cdcOffset;
    }

    public void setCdcOffset(Short cdcOffset) {
        this.cdcOffset = cdcOffset;
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

    public Character getEDocGenTyp() {
        return eDocGenTyp;
    }

    public void setEDocGenTyp(Character eDocGenTyp) {
        this.eDocGenTyp = eDocGenTyp;
    }

    public Short getESeries() {
        return eSeries;
    }

    public void setESeries(Short eSeries) {
        this.eSeries = eSeries;
    }

    public String getEDocNum() {
        return eDocNum;
    }

    public void setEDocNum(String eDocNum) {
        this.eDocNum = eDocNum;
    }

    public Integer getEDocExpFrm() {
        return eDocExpFrm;
    }

    public void setEDocExpFrm(Integer eDocExpFrm) {
        this.eDocExpFrm = eDocExpFrm;
    }

    public Character getOnlineQuo() {
        return onlineQuo;
    }

    public void setOnlineQuo(Character onlineQuo) {
        this.onlineQuo = onlineQuo;
    }

    public String getPOSEqNum() {
        return pOSEqNum;
    }

    public void setPOSEqNum(String pOSEqNum) {
        this.pOSEqNum = pOSEqNum;
    }

    public String getPOSManufSN() {
        return pOSManufSN;
    }

    public void setPOSManufSN(String pOSManufSN) {
        this.pOSManufSN = pOSManufSN;
    }

    public Integer getPOSCashN() {
        return pOSCashN;
    }

    public void setPOSCashN(Integer pOSCashN) {
        this.pOSCashN = pOSCashN;
    }

    public Character getEDocStatus() {
        return eDocStatus;
    }

    public void setEDocStatus(Character eDocStatus) {
        this.eDocStatus = eDocStatus;
    }

    public String getEDocCntnt() {
        return eDocCntnt;
    }

    public void setEDocCntnt(String eDocCntnt) {
        this.eDocCntnt = eDocCntnt;
    }

    public Character getEDocProces() {
        return eDocProces;
    }

    public void setEDocProces(Character eDocProces) {
        this.eDocProces = eDocProces;
    }

    public String getEDocErrCod() {
        return eDocErrCod;
    }

    public void setEDocErrCod(String eDocErrCod) {
        this.eDocErrCod = eDocErrCod;
    }

    public String getEDocErrMsg() {
        return eDocErrMsg;
    }

    public void setEDocErrMsg(String eDocErrMsg) {
        this.eDocErrMsg = eDocErrMsg;
    }

    public Character getEDocCancel() {
        return eDocCancel;
    }

    public void setEDocCancel(Character eDocCancel) {
        this.eDocCancel = eDocCancel;
    }

    public Character getEDocTest() {
        return eDocTest;
    }

    public void setEDocTest(Character eDocTest) {
        this.eDocTest = eDocTest;
    }

    public String getEDocPrefix() {
        return eDocPrefix;
    }

    public void setEDocPrefix(String eDocPrefix) {
        this.eDocPrefix = eDocPrefix;
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

    public Character getDpmAsDscnt() {
        return dpmAsDscnt;
    }

    public void setDpmAsDscnt(Character dpmAsDscnt) {
        this.dpmAsDscnt = dpmAsDscnt;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getAtcEntry() {
        return atcEntry;
    }

    public void setAtcEntry(Integer atcEntry) {
        this.atcEntry = atcEntry;
    }

    public String getSupplCode() {
        return supplCode;
    }

    public void setSupplCode(String supplCode) {
        this.supplCode = supplCode;
    }

    public Character getGTSRlvnt() {
        return gTSRlvnt;
    }

    public void setGTSRlvnt(Character gTSRlvnt) {
        this.gTSRlvnt = gTSRlvnt;
    }

    public BigDecimal getBaseDisc() {
        return baseDisc;
    }

    public void setBaseDisc(BigDecimal baseDisc) {
        this.baseDisc = baseDisc;
    }

    public BigDecimal getBaseDiscSc() {
        return baseDiscSc;
    }

    public void setBaseDiscSc(BigDecimal baseDiscSc) {
        this.baseDiscSc = baseDiscSc;
    }

    public BigDecimal getBaseDiscFc() {
        return baseDiscFc;
    }

    public void setBaseDiscFc(BigDecimal baseDiscFc) {
        this.baseDiscFc = baseDiscFc;
    }

    public BigDecimal getBaseDiscPr() {
        return baseDiscPr;
    }

    public void setBaseDiscPr(BigDecimal baseDiscPr) {
        this.baseDiscPr = baseDiscPr;
    }

    public Integer getCreateTS() {
        return createTS;
    }

    public void setCreateTS(Integer createTS) {
        this.createTS = createTS;
    }

    public Integer getUpdateTS() {
        return updateTS;
    }

    public void setUpdateTS(Integer updateTS) {
        this.updateTS = updateTS;
    }

    public Character getSrvTaxRule() {
        return srvTaxRule;
    }

    public void setSrvTaxRule(Character srvTaxRule) {
        this.srvTaxRule = srvTaxRule;
    }

    public Integer getAnnInvDecR() {
        return annInvDecR;
    }

    public void setAnnInvDecR(Integer annInvDecR) {
        this.annInvDecR = annInvDecR;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getReleaser() {
        return releaser;
    }

    public void setReleaser(Integer releaser) {
        this.releaser = releaser;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Character getUAutorret() {
        return uAutorret;
    }

    public void setUAutorret(Character uAutorret) {
        this.uAutorret = uAutorret;
    }

    public String getUBpcost() {
        return uBpcost;
    }

    public void setUBpcost(String uBpcost) {
        this.uBpcost = uBpcost;
    }

    public String getUWuid() {
        return uWuid;
    }

    public void setUWuid(String uWuid) {
        this.uWuid = uWuid;
    }

    public Character getUTipoNota() {
        return uTipoNota;
    }

    public void setUTipoNota(Character uTipoNota) {
        this.uTipoNota = uTipoNota;
    }

    public Character getURetefue() {
        return uRetefue;
    }

    public void setURetefue(Character uRetefue) {
        this.uRetefue = uRetefue;
    }

    public Character getUReteIca() {
        return uReteIca;
    }

    public void setUReteIca(Character uReteIca) {
        this.uReteIca = uReteIca;
    }

    public Character getUOk1Ivapa() {
        return uOk1Ivapa;
    }

    public void setUOk1Ivapa(Character uOk1Ivapa) {
        this.uOk1Ivapa = uOk1Ivapa;
    }

    public Character getUOk1Ifrs() {
        return uOk1Ifrs;
    }

    public void setUOk1Ifrs(Character uOk1Ifrs) {
        this.uOk1Ifrs = uOk1Ifrs;
    }

    public String getUDiseno() {
        return uDiseno;
    }

    public void setUDiseno(String uDiseno) {
        this.uDiseno = uDiseno;
    }

    public String getUNWRPicS() {
        return uNWRPicS;
    }

    public void setUNWRPicS(String uNWRPicS) {
        this.uNWRPicS = uNWRPicS;
    }

    public String getUNWRBRet() {
        return uNWRBRet;
    }

    public void setUNWRBRet(String uNWRBRet) {
        this.uNWRBRet = uNWRBRet;
    }

    public Short getUnwrPAut() {
        return unwrPAut;
    }

    public void setUnwrPAut(Short unwrPAut) {
        this.unwrPAut = unwrPAut;
    }

    public String getUnwrNote() {
        return unwrNote;
    }

    public void setUnwrNote(String unwrNote) {
        this.unwrNote = unwrNote;
    }

    public String getUnwrTag() {
        return unwrTag;
    }

    public void setUnwrTag(String unwrTag) {
        this.unwrTag = unwrTag;
    }

    public BigDecimal getUnwrFrgt() {
        return unwrFrgt;
    }

    public void setUnwrFrgt(BigDecimal unwrFrgt) {
        this.unwrFrgt = unwrFrgt;
    }

    public String getUNwrNorm() {
        return uNwrNorm;
    }

    public void setUNwrNorm(String uNwrNorm) {
        this.uNwrNorm = uNwrNorm;
    }

    public String getUTypeExped() {
        return uTypeExped;
    }

    public void setUTypeExped(String uTypeExped) {
        this.uTypeExped = uTypeExped;
    }

    public String getUDecorador2() {
        return uDecorador2;
    }

    public void setUDecorador2(String uDecorador2) {
        this.uDecorador2 = uDecorador2;
    }

    public String getUComdeco1() {
        return uComdeco1;
    }

    public void setUComdeco1(String uComdeco1) {
        this.uComdeco1 = uComdeco1;
    }

    public String getUComdeco2() {
        return uComdeco2;
    }

    public void setUComdeco2(String uComdeco2) {
        this.uComdeco2 = uComdeco2;
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
        if (!(object instanceof CotizacionSAP)) {
            return false;
        }
        CotizacionSAP other = (CotizacionSAP) object;
        if ((this.docEntry == null && other.docEntry != null) || (this.docEntry != null && !this.docEntry.equals(other.docEntry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.matisses.baruweb.entity.CotizacionSAP[ docEntry=" + docEntry + " ]";
    }
}
